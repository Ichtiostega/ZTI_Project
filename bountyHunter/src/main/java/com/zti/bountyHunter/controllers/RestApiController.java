package com.zti.bountyHunter.controllers;

import java.util.Collections;

import com.zti.bountyHunter.dao.ContractInterface;
import com.zti.bountyHunter.models.Contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Transactional
@RestController
public class RestApiController {

	@Autowired
	ContractInterface contractInterface;

	@RequestMapping(method = RequestMethod.GET, value = "/available_contracts")
	public Iterable<Contract> getContracts(Model model) {
		return contractInterface.findByStatus(0);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/add_contract")
	public String postContracts(Model model, @RequestBody Contract contract) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		contract.setContractorId(authentication.getName());
		contract.setStatus(0);
		contractInterface.save(contract);
		return "";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/accept_contract")
	public String acceptContract(Model model, @RequestBody Contract contract) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		contractInterface.accept(contract.getId(), authentication.getName());
		return "";
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/contract_status")
	public String changeContractStatus(Model model, @RequestBody Contract contract) {
		contractInterface.changeStatus(contract.getId(), contract.getStatus());
		return "";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/my_contracts")
	public Iterable<Contract> getMyContracts(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CONTRACTOR")))
			return contractInterface.findByContractorId(authentication.getName());
		else if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("HUNTER")))
			return contractInterface.findByHunterId(authentication.getName());
		return Collections.emptyList();
	}
}
