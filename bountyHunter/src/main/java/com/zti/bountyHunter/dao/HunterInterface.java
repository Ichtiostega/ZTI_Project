package com.zti.bountyHunter.dao;

import com.zti.bountyHunter.models.Hunter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface HunterInterface extends JpaRepository<Hunter, Integer> {
    Iterable<Hunter> findByEmail(String email);
}