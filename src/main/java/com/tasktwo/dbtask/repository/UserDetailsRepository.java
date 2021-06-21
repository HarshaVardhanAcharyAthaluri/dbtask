package com.tasktwo.dbtask.repository;

import org.springframework.data.repository.CrudRepository;

import com.tasktwo.dbtask.model.UserDetails;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long>{

}
