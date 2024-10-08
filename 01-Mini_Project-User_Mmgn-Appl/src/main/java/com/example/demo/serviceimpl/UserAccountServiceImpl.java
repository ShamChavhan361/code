package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repo.UserAccountRepo;
import com.example.demo.service.UserAccountService;


@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountRepo userRepo;
	
	
	
	@Override
	public String saveOrUpdateUserAcc(UserAccount userAcc) {
		
		Integer userId = userAcc.getUserId();
		
		//method which is used to  Save or Update record is called as UPSERT method
		 userRepo.save(userAcc);
		
	
	
		
		if(userId!=null)
		{
			return " User Account Updated";
		}
		else
		{
			return "Record submitted";
		}
		
		
		
	}

	@Override
	public List<UserAccount> getAllUserAccount() {
		
		return userRepo.findAll();
	}

	@Override
	public UserAccount getUserAcc(Integer userId) {
		
		
		Optional<UserAccount> acoount=userRepo.findById(userId);
		
		if(acoount.isPresent())
		{
			return acoount.get();
		}
		return null;
		
	}

	@Override
	public boolean deleteUserAcc(Integer userId) {
		
		boolean existsById = userRepo.existsById(userId);
		
				if(existsById)
				{
					userRepo.deleteById(userId);
					return true;
				}
				else
				{
					return false;
				}
		
	
	}

	
	@Override
	public boolean updateUserAccStatus(Integer userId, String status) {

		try {

			userRepo.updateUserAccountStatus(userId, status);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	 
	 

}
