package com.zti.bountyHunter.controllers;

import java.util.Collections;
import java.util.Dictionary;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Transactional
@RestController
public class RestApiController {

	@Autowired
	ContractInterface contractInterface;

	@GetMapping("/available_contracts")
	public Iterable<Contract> getContracts(Model model) {
		return contractInterface.findByStatus(0);
	}

	@PostMapping("/add_contract")
	public String postContracts(Model model, @RequestBody Contract contract) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		contract.setContractorId(authentication.getName());
		contract.setStatus(0);
		contractInterface.save(contract);
		return "";
	}

	@PutMapping("/accept_contract")
	public String acceptContract(Model model, @RequestBody Contract contract) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		contractInterface.accept(contract.getId(), authentication.getName());
		return "";
	}

	@PutMapping("/contract_status")
	public String changeContractStatus(Model model, @RequestBody Contract contract) {
		contractInterface.changeStatus(contract.getId(), contract.getStatus());
		return "";
	}

	@GetMapping("/my_contracts")
	public Iterable<Contract> getMyContracts(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("CONTRACTOR")))
			return contractInterface.findByContractorId(authentication.getName());
		else if (authentication != null && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("HUNTER")))
			return contractInterface.findByHunterId(authentication.getName());
		return Collections.emptyList();
	}
}
