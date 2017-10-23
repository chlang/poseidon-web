package edu.mum.se.poseidon.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import edu.mum.se.poseidon.web.mapper.UserMapper;
import edu.mum.se.poseidon.web.models.User;
import edu.mum.se.poseidon.web.services.UserService;
import edu.mum.se.poseidon.web.services.dto.UserDto;

/**
 * Created by Munkhtsogt Tsogbadrakh on 10/11/2017.
 *
 * @author Munkhtsogt Tsogbadrakh
 */

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @RequestMapping(path = "/admin/user", method = RequestMethod.GET)
    public String index(Model model) {
        List<UserDto> users;
		try {
			users = userService.getUsers();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
            return "error";
		}
        model.addAttribute("users", users);
        return "admin/user/index";
    }

    @RequestMapping(path = "/admin/user/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/create";
    }

    @RequestMapping(path = "/admin/user/create", method = RequestMethod.POST)
    public String createPOST(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @Valid Model model){

        if (bindingResult.hasErrors()) {
            return "admin/user/create";
        }
        try {
			userService.createUser(userMapper.getUserDto(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
            return "error";
		}
        return "redirect:/admin/user";
    }

    @RequestMapping(path = "/admin/user/{id}/edit")
    public String edit(@PathVariable long id, Model model){
        UserDto udo;
		try {
			udo = userService.getUser(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
            return "error";
		}
        User user = userMapper.getUser(udo);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @RequestMapping(path = "/admin/user/{id}/edit", method = RequestMethod.POST)
    public String editPOST(@ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult, @Valid Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/user/edit";
        }
        try {
			userService.editUser(userMapper.getUserDto(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
            return "error";
		}
        return "redirect:/admin/user";
    }

    @RequestMapping(path = "/admin/user/{id}/delete")
    public String delete(@PathVariable long id, Model model) throws Exception {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }
}
