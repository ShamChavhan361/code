package com.example.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserAccount;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {

	
	
	  @Modifying //it used update record not select option
	  
	  @Transactional
	  // user non select operation without using pre-define method (if not write update operation will fail)
	  
	  @Query("update UserAccount set activeSw=:status where userId=:userId") public
	  void updateUserAccountStatus(@Param("userId") Integer userId, @Param("status") String status);
	  
	 
}
