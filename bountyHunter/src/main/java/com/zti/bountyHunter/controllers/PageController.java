package com.zti.bountyHunter.controllers;

import com.zti.bountyHunter.dao.AuthoritiesInterface;
import com.zti.bountyHunter.dao.ContractInterface;
import com.zti.bountyHunter.dao.HunterInterface;
import com.zti.bountyHunter.dao.UsersInterface;
import com.zti.bountyHunter.models.Authorities;
import com.zti.bountyHunter.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Transactional
public class PageController {

	@Autowired
	private ContractInterface contractInterface;
	
	@Autowired
	private HunterInterface hunterInterface;

	@Autowired
	UsersInterface usersInterface;

	@Autowired
	AuthoritiesInterface authoritiesInterface;

	@Autowired
	PasswordEncoder passwordEncoder;

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Authorities auth = authoritiesInterface.getById(authentication.getName());
		model.addAttribute("role", auth.getAuthority());
		return "dashboard";
	}

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		return "register";
	}

	@PostMapping("/register")
	public String register(
		@RequestParam(name="username", required=true) String username, 
		@RequestParam(name="password", required=true) String password, 
		@RequestParam(name="role", required=true) String role, 
		Model model) 
	{
		usersInterface.save(new Users(username, passwordEncoder.encode(password)));
		authoritiesInterface.save(new Authorities(username, role));
		return "index";
	}
}