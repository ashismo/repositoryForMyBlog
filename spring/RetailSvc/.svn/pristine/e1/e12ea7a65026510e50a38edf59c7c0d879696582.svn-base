package com.org.coop.retail.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.org.coop.retail.entities.GlLedgerDtl;

public interface GlLedgerDtlRepository extends JpaRepository<GlLedgerDtl, Integer> {
	
	@Query("select gld from GlLedgerDtl gld where gld.glLedgerHrd.glTranId = :glTranId")
	public List<GlLedgerDtl> findByGlTranId(@Param("glTranId") int glTranId);
}
