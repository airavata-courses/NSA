package com.nsa.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nsa.app.model.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User,Integer>{
	User findByuserID (String userID);
	 
	User findByLastName (String username);
	 
	boolean existsById (int id);
	 
}