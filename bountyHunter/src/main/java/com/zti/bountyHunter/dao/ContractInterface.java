package com.zti.bountyHunter.dao;

import com.zti.bountyHunter.models.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource(collectionResourceRel = "contract", path = "contract")
@EnableJpaRepositories
public interface ContractInterface extends JpaRepository<Contract, Integer> {
    @Query(value="SELECT * FROM contract c WHERE c.hunter_id = ?1", nativeQuery=true)
    Iterable<Contract> findByHunterId(String hunterId);
    @Query(value="SELECT * FROM contract c WHERE c.contractor_id = ?1", nativeQuery=true)
    Iterable<Contract> findByContractorId(String contractorId);
    @Modifying
    @Query(value="UPDATE contract SET status = 1, hunter_id = ?2 WHERE id = ?1", nativeQuery=true)
    @Transactional
    void accept(Integer id, String hunterId);
    @Modifying
    @Query(value="UPDATE contract SET status = ?2 WHERE id = ?1", nativeQuery=true)
    @Transactional
    void changeStatus(Integer id, Integer status);
    Iterable<Contract> findByStatus(Integer status);
}