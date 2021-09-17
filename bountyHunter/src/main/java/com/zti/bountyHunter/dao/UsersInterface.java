package com.zti.bountyHunter.dao;

import com.zti.bountyHunter.models.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
* Class that defines the database interface for the users relation.
*/
@EnableJpaRepositories
public interface UsersInterface extends JpaRepository<Users, String> {}