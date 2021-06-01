package com.zti.bountyHunter.dao;

import com.zti.bountyHunter.models.Target;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface TargetInterface extends JpaRepository<Target, Integer> {}