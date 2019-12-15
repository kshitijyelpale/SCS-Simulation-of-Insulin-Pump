package com.scs.insulinpump.dao;

import com.scs.insulinpump.domain.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    @Query(value = "Select * From Patient where username = ?1", nativeQuery = true)
    public List<Patient> findByUsername(String username);

    @Query(value = "Select * from Patient where username = ?1 and password = ?2", nativeQuery = true)
    public List<Patient> findByUsernameAndPassword(String username, String password);
}
