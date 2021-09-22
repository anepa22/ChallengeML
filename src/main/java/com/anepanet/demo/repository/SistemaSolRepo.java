package com.anepanet.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anepanet.demo.entity.DiaSistemaSolar;

public interface SistemaSolRepo extends JpaRepository<DiaSistemaSolar, Integer> {
	@Query("select a from DiaSistemaSolar a where a.dia = :dia") 
	public Optional<DiaSistemaSolar> findByDay(@Param("dia") int dia);
	
	@Query("select a from DiaSistemaSolar a where (a.distP1eP2+a.distP2eP3+a.distP3eP1) = (select max(a.distP1eP2+a.distP2eP3+a.distP3eP1) from DiaSistemaSolar a"
			+ " where a.solDentroDelTriangulo = 1)") 
	public Optional<DiaSistemaSolar> findDiaMaslluvioso();

	@Query("select a from DiaSistemaSolar a where a.solDentroDelTriangulo = 1")
	public List<DiaSistemaSolar> findDiasConlluvia();
	
	@Query("select a from DiaSistemaSolar a where a.pendEP1P2 = a.pendEP2P3 and a.pendEP3P1 = a.pendEP1P2"
			+ " and (a.pendEP1Sol <> a.pendEP2Sol or a.pendEP3Sol <> a.pendEP1Sol) order by a.dia")
	public List<DiaSistemaSolar> findDiasConOptimaPresionYTemp();
	
	@Query("select a from DiaSistemaSolar a where a.pendEP1Sol = a.pendEP2Sol and a.pendEP1Sol = a.pendEP3Sol order by a.dia")
	public List<DiaSistemaSolar> findDiasConSequia();
}
