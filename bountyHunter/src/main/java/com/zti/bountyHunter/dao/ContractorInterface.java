package com.zti.bountyHunter.dao;

import com.zti.bountyHunter.models.Contractor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ContractorInterface extends JpaRepository<Contractor, Integer> {
    Iterable<Contractor> findBySurname(String surname);
}