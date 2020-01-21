package com.inglpump.repository;

import com.inglpump.domain.Reservoir;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReservoirRepository extends PagingAndSortingRepository<Reservoir, Integer> {

    @Query(value = "Select * From reservoir res where res.patientID = ?1 and res.type = ?2", nativeQuery = true)
    public List<Reservoir> findByPatientIdAndType(Integer userId, Integer type);
}
