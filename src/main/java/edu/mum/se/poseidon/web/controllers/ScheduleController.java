package edu.mum.se.poseidon.web.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.mum.se.poseidon.web.mapper.EntryMapper;
import edu.mum.se.poseidon.web.mapper.ScheduleMapper;
import edu.mum.se.poseidon.web.models.EntryModel;
import edu.mum.se.poseidon.web.models.User;
import edu.mum.se.poseidon.web.models.schedule.ScheduleCreateModel;
import edu.mum.se.poseidon.web.models.schedule.ScheduleEditModel;
import edu.mum.se.poseidon.web.models.schedule.ScheduleModel;
import edu.mum.se.poseidon.web.models.schedule.ScheduleStatus;
import edu.mum.se.poseidon.web.services.EntryService;
import edu.mum.se.poseidon.web.services.ScheduleService;
import edu.mum.se.poseidon.web.services.dto.ScheduleDto;
import edu.mum.se.poseidon.web.services.dto.ScheduleGenerateDto;
import edu.mum.se.poseidon.web.services.dto.UserDto;
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

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ScheduleController {

    private ScheduleService scheduleService;
    private ScheduleMapper scheduleMapper;
    private EntryMapper entryMapper;
    private EntryService entryService;

    @Autowired
    public ScheduleController(
            ScheduleService scheduleService,
            ScheduleMapper scheduleMapper,
            EntryMapper entryMapper,
            EntryService entryService) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
        this.entryMapper = entryMapper;
        this.entryService = entryService;
    }

    // Index
    @RequestMapping(path = "/admin/schedule", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        List<ScheduleModel> sched = scheduleService.getSchedules()
                .stream()
                .map(x -> scheduleMapper.getSchedule(x))
                .collect(Collectors.toList());

        ScheduleCreateModel scheduleCreateModel = new ScheduleCreateModel();
        List<EntryModel> entries = entryService.getEntries()
                .stream()
                .map(x -> entryMapper.getEntryModelFrom(x))
                .collect(Collectors.toList());

        scheduleCreateModel.setEntries(entries);

        model.addAttribute("schedules", sched);
        model.addAttribute("scheduleCreateModel", scheduleCreateModel);
        return "admin/schedule/index";
    }

    // Generate
    @RequestMapping(path = "/admin/schedule/generate", method = RequestMethod.POST)
    public String generate(@ModelAttribute("scheduleCreateModel") @Valid ScheduleCreateModel schedule,
                           BindingResult bindingResult, @Valid Model model) throws Exception {

        ScheduleGenerateDto dto = new ScheduleGenerateDto();
        dto.setEntryId(schedule.getEntryId());

        scheduleService.generate(dto);
        return "redirect:/admin/schedule";
    }

    // Edit
    @RequestMapping(path = "/admin/schedule/{id}/edit")
    public String edit(@PathVariable long id, Model model) throws Exception {
        ScheduleEditModel schedule = new ScheduleEditModel();
        schedule.setId(id);
        schedule.setDisplayName(schedule.getDisplayName());

        model.addAttribute("scheduleEditModel", schedule);
        return "admin/schedule/edit";
    }

    @RequestMapping(path = "/admin/schedule/{id}/edit", method = RequestMethod.POST)
    public String editPOST(@ModelAttribute("scheduleEditModel") @Valid ScheduleEditModel scheduleEditModel,
                           BindingResult bindingResult, @Valid Model model) throws Exception {

        if (bindingResult.hasErrors()) {
            return "admin/schedule/" + scheduleEditModel.getId() + "/edit";
        }
        ScheduleDto dto = scheduleMapper.getScheduleDto(scheduleEditModel);
        scheduleService.edit(dto);
        return "redirect:/admin/schedule";
    }

    // Delete
    @RequestMapping(path = "/admin/schedule/{id}/delete")
    public String delete(@PathVariable long id, Model model) throws Exception {
        scheduleService.delete(id);
        return "redirect:/admin/schedule";
    }
}
