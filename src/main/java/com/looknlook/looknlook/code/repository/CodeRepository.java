package com.looknlook.looknlook.code.repository;

import com.looknlook.looknlook.code.domain.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long>{

    List<Code> findAllByCdTy(String cdTy);

    @Query(value = "SELECT c.cd_num FROM code c WHERE c.cd_nm = ?1", nativeQuery = true)
    String findByCdNm(String cdNm);

    @Query(value = "SELECT c.cd_nm FROM code c WHERE c.cd_num = ?1", nativeQuery = true)
    String findByCdNum(String cdNum);
}
