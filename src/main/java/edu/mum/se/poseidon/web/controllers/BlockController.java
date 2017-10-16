package edu.mum.se.poseidon.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.web.models.Block;

/**
 * Created by Munkhtsogt Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsogt Tsogbadrakh
 */

@Controller
public class BlockController {

	@RequestMapping(path = "/admin/block", method = RequestMethod.GET)
	public String index() {
		
		return "admin/block/index";
	}
	
	@RequestMapping(path = "/admin/block/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("block", new Block());
		return "admin/block/create";
	}
}
