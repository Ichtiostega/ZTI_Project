package com.zti.bountyHunter.controllers;

import com.zti.bountyHunter.dao.ContractInterface;
import com.zti.bountyHunter.dao.HunterInterface;
import com.zti.bountyHunter.models.Contract;
import com.zti.bountyHunter.models.Hunter;
import com.zti.bountyHunter.requestBodies.AcceptContract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Transactional
public class PageController {

	@Autowired
	private ContractInterface contractInterface;
	@Autowired
	private HunterInterface hunterInterface;

	@GetMapping("/dashboard")
	public String dashboard(@RequestParam(name="email", required=true) String email, Model model) {
		Hunter h = hunterInterface.findByEmail(email).iterator().next();
		Iterable<Contract> cc = contractInterface.findByHunterId(h.getId());
		Iterable<Contract> ac = contractInterface.findByStatus(0);
		model.addAttribute("ccontracts", cc);
		model.addAttribute("acontracts", ac);
		model.addAttribute("email", email);
		return "dashboard";
	}

	@PostMapping("/accept_contract")
	public String accept_contract(@ModelAttribute AcceptContract body, Model model) {
		Hunter h = hunterInterface.findByEmail(body.getEmail()).iterator().next();
		Contract c = contractInterface.getById(body.getContractId());
		c.setStatus(1);
		c.setHunterId(h.getId());
		contractInterface.save(c);
		Iterable<Contract> cc = contractInterface.findByHunterId(h.getId());
		Iterable<Contract> ac = contractInterface.findByStatus(0);
		model.addAttribute("ccontracts", cc);
		model.addAttribute("acontracts", ac);
		model.addAttribute("email", body.getEmail());
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

}