package com.mobilebuilds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobilebuilds.model.Build;
import com.mobilebuilds.model.Champs;

@Repository
@Transactional
public interface BuildRepository extends JpaRepository<Build, Long> {

	@Query("select b from Build b where b.champ.id = :idChamp")
	public List<Build> findByIdChamp(@Param("idChamp") Long idChamp);
	
	@Query("select DISTINCT(b.champ) from Build b where role = 'MID'")
	public List<Champs> findMidChamp();
	
	@Query("select DISTINCT(b.champ) from Build b where role = 'BARON'")
	public List<Champs> findBaronChamp();
	
	@Query("select DISTINCT(b.champ) from Build b where role = 'DRAGON'")
	public List<Champs> findDragonChamp();
	
	@Query("select DISTINCT(b.champ) from Build b where role = 'SUPORTE'")
	public List<Champs> findSuporteChamp();
	
	@Query("select DISTINCT(b.champ) from Build b where role = 'JUNGLE'")
	public List<Champs> findJungleChamp();
	
}
