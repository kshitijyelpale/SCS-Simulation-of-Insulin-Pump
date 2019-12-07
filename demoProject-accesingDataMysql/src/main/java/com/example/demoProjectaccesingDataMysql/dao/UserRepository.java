package com.example.demoProjectaccesingDataMysql.dao;

import com.example.demoProjectaccesingDataMysql.domain.User;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
