package com.aniket.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aniket.app.model.evStation;

public interface evRepo extends JpaRepository<evStation, Long> {

}
