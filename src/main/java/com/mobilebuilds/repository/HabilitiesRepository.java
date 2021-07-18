package com.mobilebuilds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobilebuilds.model.Habilities;

@Repository
@Transactional
public interface HabilitiesRepository extends JpaRepository<Habilities, Long> {

	@Query("select h from Habilities h where h.nome = 'passive' and h.champs.id = :ChampID")
	public Habilities FindPassiveHabilite( @Param("ChampID") Long idChamp );
	
	@Query("select h from Habilities h where h.nome = 'q' and h.champs.id = :ChampID")
	public Habilities FindQHability(@Param("ChampID") Long idChamp);

	@Query("select h from Habilities h where h.nome = 'w' and h.champs.id = :ChampID")
	public Habilities FindWHability(@Param("ChampID") Long idChamp);
	
	@Query("select h from Habilities h where h.nome = 'e' and h.champs.id = :ChampID")
	public Habilities FindEHability(@Param("ChampID") Long idChamp);

	@Query("select h from Habilities h where h.nome = 'r' and h.champs.id = :ChampID")
	public Habilities FindRHability(@Param("ChampID") Long idChamp);
	
}
