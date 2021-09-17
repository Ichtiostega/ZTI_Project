package com.zti.bountyHunter.controllers;

import com.zti.bountyHunter.dao.AuthoritiesInterface;
import com.zti.bountyHunter.dao.ContractInterface;
import com.zti.bountyHunter.dao.UsersInterface;
import com.zti.bountyHunter.models.Authorities;
import com.zti.bountyHunter.models.Contract;
import com.zti.bountyHunter.models.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* A class that defines endpoints responsible for providing the different subpages of the app.
*/
@Controller
@Transactional
public class PageController {

	@Autowired
	private ContractInterface contractInterface;

	@Autowired
	UsersInterface usersInterface;

	@Autowired
	AuthoritiesInterface authoritiesInterface;

	@Autowired
	PasswordEncoder passwordEncoder;

	/**
	* Provides the main page of the application along with some key attributes.
	*
	* @param model	Current model
	* @return      	Page name
	*/
	@RequestMapping(method = RequestMethod.GET, value = "/dashboard")
	public String dashboard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Authorities auth = authoritiesInterface.getById(authentication.getName());
		model.addAttribute("role", auth.getAuthority());
		model.addAttribute("username", authentication.getName());
		if(auth.getAuthority().equals("HUNTER"))
		{
			Integer s=0, f=0;
			Iterable<Contract> contracts = contractInterface.findByHunterId(authentication.getName());
			for(Contract c : contracts)
			{
				if(c.getStatus() == 2)
					s += 1;
				if(c.getStatus() == 3)
					f += 1;
			}
			if(s+f >= 5 && f>s)
				model.addAttribute("trusted", false);
			else
				model.addAttribute("trusted", true);
		}
		return "dashboard";
	}

	/**
	* Provides the introduction page of the application with options to log in an register.
	*
	* @param model	Current model
	* @return      	Page name
	*/
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(Model model) {
		return "index";
	}

	/**
	* Provides the login page of the application.
	*
	* @param model	Current model
	* @return      	Page name
	*/
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(Model model) {
		return "login";
	}

	/**
	* Provides the register page of the application.
	*
	* @param model	Current model
	* @return      	Page name
	*/
	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public String register(Model model) {
		return "register";
	}

	/**
	* Performs registration of the user and returns him to the introduction page.
	*
	* @param username	New users username
	* @param password	New users password
	* @param role		New users role
	* @param model		Current model
	* @return      		Page name
	*/
	@RequestMapping(method = RequestMethod.POST, value = "/register")
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