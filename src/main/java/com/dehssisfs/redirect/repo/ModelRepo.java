package com.dehssisfs.redirect.repo;


import com.dehssisfs.redirect.model.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ModelRepo extends CrudRepository<Model, Long> {

    @Query(value = "select g from Model g where (g.LastUpdate = (select max(u.LastUpdate) from Model u where u.code = :code) and g.code = :code)")
    List<Model> findLastDataByCode(@Param("code") int code);

    @Query(value = "select DISTINCT u.code  from Model u")
    List<Integer> findAllCode();
}
