package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserAccountService;
import com.example.demo.serviceimpl.UserAccountServiceImpl;


@Controller
public class HomeController {
	
	
	
	@Autowired
	private UserAccountService service;
	
	
	/*
	 * @Autowired private UserAccountServiceImpl userSer;
	 * 
	 * 
	 * @GetMapping("/") public String index(Model model) { List<UserAccount>
	 * userAccounts = userSer.getAllUserAccount();
	 * model.addAttribute("userAccounts", userAccounts); return "index"; }
	 * 
	 * @GetMapping("/register") public String createUser() { return "register"; }
	 * 
	 * @PostMapping("/register") public String registerUser(@ModelAttribute
	 * UserAccount userAccount) { userSer.saveOrUpdateUserAcc(userAccount); return
	 * "redirect:/userAccounts"; }
	 */
	
	
	
	@GetMapping("/")
	public String index(Model model)
	{
		 model.addAttribute("user", new UserAccount()); 
		 
		 return "index";
	}
	
	@PostMapping("/save-user")
	public String submitBtn(@ModelAttribute("user") UserAccount user, Model model )
	{
		
		System.out.println(user);
		String msg = service.saveOrUpdateUserAcc(user);
		model.addAttribute("msg", msg);
		return "index";
		
	}
	
	@GetMapping("/view")
	public String getAllUser(Model model)
	{
		
	List<UserAccount> userList = service.getAllUserAccount();
	model.addAttribute("users", userList);
	
		return "view-users";
		
	}
	
}
