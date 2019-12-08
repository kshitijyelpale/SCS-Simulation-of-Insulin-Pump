package com.scs.insulinpump.dao;

import com.scs.insulinpump.domain.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    @Query(value = "From Patient where username = ?0", nativeQuery = true)
    public Optional<Patient> findByName(String username);
}
