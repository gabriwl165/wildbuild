package com.mobilebuilds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobilebuilds.model.Champs;

@Repository
@Transactional
public interface ChampRepository extends JpaRepository<Champs, Long> {

}
