package com.zti.bountyHunter.dao;

import com.zti.bountyHunter.models.Authorities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
* Class that defines the database interface for the autorities relation.
*/
@EnableJpaRepositories
public interface AuthoritiesInterface extends JpaRepository<Authorities, String> {}