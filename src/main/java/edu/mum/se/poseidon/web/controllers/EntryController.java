package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.services.dto.EntryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.mum.se.poseidon.web.mapper.EntryMapper;
import edu.mum.se.poseidon.web.models.EntryModel;
import edu.mum.se.poseidon.web.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class EntryController {

    private EntryService entryService;
    private EntryMapper entryMapper;
    private static final Logger logger = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    public EntryController(EntryService entryService, EntryMapper entryMapper) {
        this.entryService = entryService;
        this.entryMapper = entryMapper;
    }

    @RequestMapping(path = "/admin/entry", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        List<EntryDto> entryDtoList = entryService.getEntries();
        List<EntryModel> entryModelList = entryDtoList.stream()
                .map(e -> entryMapper.getEntryModelFrom(e))
                .collect(Collectors.toList());
        model.addAttribute("entries", entryModelList);
        return "admin/entry/index";
    }

    @RequestMapping(path = "/admin/entry/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("entryModel", new EntryModel());
        return "admin/entry/create";
    }

    @RequestMapping(path = "/admin/entry/create", method = RequestMethod.POST)
    public String createPost(@ModelAttribute("entryModel") @Valid EntryModel entryModel,
                             BindingResult bindingResult, @Valid Model model) throws Exception {
        try {
            if (bindingResult.hasErrors()) {
                return "admin/entry/create";
            }
            entryService.createEntry(entryMapper.getEntryDtoFrom(entryModel));
            return "redirect:/admin/entry";
        } catch (Exception e) {
            if (e.getMessage().contains("400")) {
                model.addAttribute("errorMessage", "Entry's data is invalid. Please check entry's data.");
            } else if (e.getMessage().contains("409")) {
                model.addAttribute("errorMessage", "Entry is already exist. Please check entry's data.");
            } else {
                model.addAttribute("errorMessage", e.getMessage());
            }
            return "error";
        }
    }

    @RequestMapping(path = "/admin/entry/edit/{id}")
    public String edit(@PathVariable long id, Model model) throws Exception {
        try {
            EntryDto entryDto = entryService.getEntry(id);
            EntryModel entryModel = entryMapper.getEntryModelFrom(entryDto);
            model.addAttribute("entryModel", entryModel);
            return "admin/entry/edit";
        } catch (Exception e) {
            if (e.getMessage().contains("404")) {
                model.addAttribute("errorMessage", "Entry is not found. Please check entry's data.");
            } else {
                model.addAttribute("errorMessage", e.getMessage());
            }
            return "error";
        }
    }

    @RequestMapping(path = "/admin/entry/edit/{id}", method = RequestMethod.POST)
    public String editPOST(@ModelAttribute("entryModel") @Valid EntryModel entryModel,
                           BindingResult bindingResult, @Valid Model model) throws Exception {
        try {
            if (bindingResult.hasErrors()) {
                return "admin/entry/edit";
            }
            entryService.editEntry(entryMapper.getEntryDtoFrom(entryModel));
            return "redirect:/admin/entry";
        } catch (Exception e) {
            if (e.getMessage().contains("400")) {
                model.addAttribute("errorMessage", "Entry's data is invalid. Please check entry's data.");
            } else if (e.getMessage().contains("409")) {
                model.addAttribute("errorMessage", "Entry is already exist. Please check entry's data.");
            } else if (e.getMessage().contains("404")) {
                model.addAttribute("errorMessage", "Entry is not found. Please check entry's data.");
            } else {
                model.addAttribute("errorMessage", e.getMessage());
            }
            return "error";
        }
    }

    @RequestMapping(path = "/admin/entry/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, Model model) throws Exception {
        try {
            entryService.deleteEntry(id);
            return "redirect:/admin/entry";
        } catch (Exception e) {
            if (e.getMessage().contains("400")) {
                model.addAttribute("errorMessage", "Entry's data is invalid. Please check entry's data.");
            } else if (e.getMessage().contains("404")) {
                model.addAttribute("errorMessage", "Entry is not found. Please check entry's data.");
            } else {
                model.addAttribute("errorMessage", e.getMessage());
            }
            return "error";
        }
    }
}
