package edu.mum.se.poseidon.web.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.web.mapper.BlockMapper;
import edu.mum.se.poseidon.web.mapper.EntryMapper;
import edu.mum.se.poseidon.web.models.Block;
import edu.mum.se.poseidon.web.models.EntryModel;
import edu.mum.se.poseidon.web.services.BlockService;
import edu.mum.se.poseidon.web.services.EntryService;
import edu.mum.se.poseidon.web.services.dto.BlockDto;
import edu.mum.se.poseidon.web.services.dto.EntryDto;

/**
 * Created by Munkhtsogt Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsogt Tsogbadrakh
 */

@Controller
public class BlockController {

	private BlockService blockService;
	private BlockMapper blockMapper;
	private EntryService entryService;
	private EntryMapper entryMapper;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public BlockController(BlockService blockService, BlockMapper blockMapper,
			EntryService entryService, EntryMapper entryMapper) {
		this.blockService = blockService;
		this.entryService = entryService;
		this.blockMapper = blockMapper;
		this.entryMapper = entryMapper;
	}
	
	@RequestMapping(path = "/admin/block", method = RequestMethod.GET)
	public String index(Model model) throws Exception {
		List<BlockDto> blocks = blockService.getBlocks();
		model.addAttribute("blocks", blocks);
		return "admin/block/index";
	}
	
	@RequestMapping(path = "/admin/block/create", method = RequestMethod.GET)
	public String create(Model model) throws Exception {
		List<EntryDto> edtos = entryService.getEntries();
		List<EntryModel> entries = edtos.stream()
										.map(e -> entryMapper.getEntryModelFrom(e))
										.collect(Collectors.toList());
		
		model.addAttribute("entries", entries);
		model.addAttribute("block", new Block());
		return "admin/block/create";
	}
	
	@RequestMapping(path = "/admin/block/create", method = RequestMethod.POST)
	public String createPOST(@ModelAttribute("block") @Valid Block block, 
			BindingResult bindingResult, @Valid Model model) throws Exception {
		if(bindingResult.hasErrors()) {
			List<EntryDto> edtos = entryService.getEntries();
			List<EntryModel> entries = edtos.stream()
											.map(e -> entryMapper.getEntryModelFrom(e))
											.collect(Collectors.toList());
			
			model.addAttribute("entries", entries);
			return "admin/block/create";
		}
		blockService.createBlock(blockMapper.getBlockDto(block));
		return "redirect:/admin/block";
	}
	
	@RequestMapping(path = "/admin/block/edit/{id}")
	public String edit(@PathVariable long id, Model model) throws Exception {
		BlockDto bdo = blockService.getBlock(id);
		Block block = blockMapper.getBlock(bdo);
		List<EntryDto> edtos = entryService.getEntries();
		List<EntryModel> entries = edtos.stream()
										.map(e -> entryMapper.getEntryModelFrom(e))
										.collect(Collectors.toList());
		
		model.addAttribute("entries", entries);
		model.addAttribute("block", block);
		return "admin/block/edit";
	}
	
	@RequestMapping(path = "/admin/block/edit/{id}", method = RequestMethod.POST)
	public String editPOST(@ModelAttribute("block") @Valid Block block, 
    		BindingResult bindingResult, @Valid Model model) throws Exception {
		
		if(bindingResult.hasErrors()) {
			List<EntryDto> edtos = entryService.getEntries();
			List<EntryModel> entries = edtos.stream()
											.map(e -> entryMapper.getEntryModelFrom(e))
											.collect(Collectors.toList());
			
			model.addAttribute("entries", entries);
			return "admin/block/edit";
		}
		blockService.editBlock(blockMapper.getBlockDto(block));
		return "redirect:/admin/block";
	}
	
	@RequestMapping(path = "/admin/block/delete/{id}")
	public String delete(@PathVariable long id, Model model) throws Exception {
		blockService.deleteBlock(id);
		return "redirect:/admin/block";
	}
}
