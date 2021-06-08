package com.zti.bountyHunter.dao;

import com.zti.bountyHunter.models.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface ContractInterface extends JpaRepository<Contract, Integer> {
    @Query(value="SELECT * FROM contract c WHERE c.hunter_id = ?1", nativeQuery=true)
    Iterable<Contract> findByHunterId(Integer hunterId);
    Iterable<Contract> findByStatus(Integer status);
}