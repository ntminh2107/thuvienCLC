package com.example.thuvienCLC.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.thuvienCLC.dao.UserDAO;
import com.example.thuvienCLC.model.User;

import jakarta.servlet.http.HttpSession;
@Controller
public class LoginController {
	private UserDAO userDAO = new UserDAO();
	@GetMapping("/login")
	public String login(Model model, HttpSession httpSession) throws IOException{
		User user = (User) httpSession.getAttribute("user");
		if(user != null && user.getLogin())
		{
			if(user.getRole().equals("admin"))
			{
				return "redirect:/admin";
			}
			else return "redirect:/user";
		}
		return "login";
	}
	@PostMapping("/login")
	public String login(HttpSession httpSession,@RequestParam("username") String username,@RequestParam("password") String password, Model model) {
		User user = userDAO.checkUser(username, password);
		if(user.getId() != -1) {
			user.setLogin(true);
			httpSession.setAttribute("user", user);
			if(user.getRole().equals("admin"))
			{
				return "redirect:/admin";
			}
			else return "redirect:/user";
		}
		return "error";
	}
	
	@GetMapping("/register")
	public String register(Model model) throws IOException{
		return "register";
	}
	@PostMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("email") String email,@RequestParam("username") String username,@RequestParam("password") String password, Model model) {
		boolean existed = userDAO.checkExist(username);
		if(!existed)
		{
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setUsername(username);
			user.setPassword(password);
			boolean registerSucess = userDAO.register(user);
			if (registerSucess) {
				return "success";
			}
		}
		return "error";
	}
	
	@GetMapping("/logout")
	public String logout(Model model, HttpSession httpSession) throws IOException{
		User user = (User) httpSession.getAttribute("user");
		if(user !=null)
		{
			user.setLogin(false);
		}
		httpSession.invalidate();
		return "redirect:/login";
				
	}
	
}
