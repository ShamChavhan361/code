package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserAccount;

public interface UserAccountService {
	
	
	public String saveOrUpdateUserAcc(UserAccount userAcc);
	
	public List<UserAccount> getAllUserAccount( );
	
	public UserAccount getUserAcc(Integer userId);
	
	public boolean deleteUserAcc(Integer userId);
	
	public boolean updateUserAccStatus(Integer userId, String status);

	
	

}
