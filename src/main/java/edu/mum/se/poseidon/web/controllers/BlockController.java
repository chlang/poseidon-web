package edu.mum.se.poseidon.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}