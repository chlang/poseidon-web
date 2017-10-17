package edu.mum.se.poseidon.web.controllers;

import java.util.List;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.se.poseidon.web.services.dto.UserDto;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ScheduleController {

	@RequestMapping(path = "/admin/schedule", method = RequestMethod.GET)
	public String index(Model model) throws Exception {
		return "admin/schedule/index";
	}
}
