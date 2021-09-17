package com.zti.bountyHunter.dao;

import java.sql.Date;

import com.zti.bountyHunter.models.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

/**
* Class that defines the database interface for the contract relation.
*/
@RepositoryRestResource(collectionResourceRel = "contract", path = "contract")
@EnableJpaRepositories
public interface ContractInterface extends JpaRepository<Contract, Integer> {
    /**
    * Returns all contracts assigned to given hunter.
    * @param hunterId Name of the hunter
    * @return List of contracts
    */
    @Query(value="SELECT * FROM contract c WHERE c.hunter_id = ?1", nativeQuery=true)
    Iterable<Contract> findByHunterId(String hunterId);
    /**
    * Returns all contracts assigned to given contractor.
    * @param contractorId Name of the contractor
    * @return List of contracts
    */
    @Query(value="SELECT * FROM contract c WHERE c.contractor_id = ?1", nativeQuery=true)
    Iterable<Contract> findByContractorId(String contractorId);
    /**
    * Changes the given contracts status to ongoing and asigns the hunter to it.
    * @param id         Contract id
    * @param hunterId   Name of the hunter
    */
    @Modifying
    @Query(value="UPDATE contract SET status = 1, hunter_id = ?2 WHERE id = ?1", nativeQuery=true)
    @Transactional
    void accept(Integer id, String hunterId);
    /**
    * Changes the given contracts status to the provided status and sets the end_date.
    * @param id     Contract id
    * @param status The status to be set
    * @param date   The end date
    */
    @Modifying
    @Query(value="UPDATE contract SET status = ?2, end_date = ?3 WHERE id = ?1", nativeQuery=true)
    @Transactional
    void changeStatus(Integer id, Integer status, Date date);
    Iterable<Contract> findByStatus(Integer status);
}