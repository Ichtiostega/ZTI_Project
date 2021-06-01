package com.zti.bountyHunter.controllers;

import com.zti.bountyHunter.dao.ContractInterface;
import com.zti.bountyHunter.dao.HunterInterface;
import com.zti.bountyHunter.models.Contract;
import com.zti.bountyHunter.models.Hunter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

	@Autowired
	private ContractInterface contractInterface;
	@Autowired
	private HunterInterface hunterInterface;

	@GetMapping("/ongoing_contracts")
	public String ongoing_contracts(@RequestParam(name="surname", required=true) String surname, Model model) {
		Hunter h = hunterInterface.findBySurname(surname).iterator().next();
		Iterable<Contract> c = contractInterface.findByHunterId(h.getId());
		model.addAttribute("contracts", c);
		return "ongoing_contracts";
	}

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}

}