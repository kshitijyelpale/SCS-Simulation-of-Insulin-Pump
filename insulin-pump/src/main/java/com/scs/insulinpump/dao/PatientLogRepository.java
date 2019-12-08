package com.scs.insulinpump.dao;

import com.scs.insulinpump.domain.PatientLog;
import org.springframework.data.repository.CrudRepository;

public interface PatientLogRepository extends CrudRepository<PatientLog, Integer> {
}
