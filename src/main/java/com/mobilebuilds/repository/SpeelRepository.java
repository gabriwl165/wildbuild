package com.mobilebuilds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobilebuilds.model.Speels;

@Repository
@Transactional
public interface SpeelRepository extends JpaRepository<Speels, Long> {

}
