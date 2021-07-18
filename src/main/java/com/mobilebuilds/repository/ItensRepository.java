package com.mobilebuilds.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobilebuilds.model.Items;

@Repository
@Transactional
public interface ItensRepository extends JpaRepository<Items, Long>{

	@Query("select i from Items i where i.tipo = 'ATIVAVEL'")
	public List<Items> getEnchatments();
	
	@Query("select i from Items i where i.tipo <> 'ATIVAVEL' ")
	public List<Items> getAllItensWhitoutEnchment();
	
}
