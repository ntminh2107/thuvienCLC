package com.example.thuvienCLC.controller;

import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.thuvienCLC.dao.BookDAO;
import com.example.thuvienCLC.dao.RatingDAO;
import com.example.thuvienCLC.model.Book;
import com.example.thuvienCLC.model.Rating;
import com.example.thuvienCLC.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserBookController {
	private BookDAO bookDAO = new BookDAO();
	private RatingDAO ratingDAO = new RatingDAO();
	private static String UPLOAD_DIRECT ="src/main/resources/static/uploads/";
	
	@GetMapping("/user")
	public String getUserBook(Model model, HttpSession httpSession)
	{
		List<Book> books = bookDAO.getBooks();
		model.addAttribute("books",books);
		boolean UserLogin = false;
		User user = (User)httpSession.getAttribute("user");
		if(user != null && user.getLogin() && user.getRole().equals("user"))
		{
			UserLogin = true;
			model.addAttribute("login", UserLogin);
			model.addAttribute("user",user);
			return "user";
		}
		else {
			model.addAttribute("login", UserLogin);
			return "user";
		}
		
	}
	
	@GetMapping("/user/view/{id}")
	public String getBookUser(Model model, HttpSession httpSession,@PathVariable String id)
	{
		User user = (User) httpSession.getAttribute("user");
		if(user !=null && user.getLogin() && user.getRole().equals("user")) {
			Book book = bookDAO.getBookById(Integer.valueOf(id));
			List<Rating> ratings = ratingDAO.getRatingBook(Integer.valueOf(id));
			model.addAttribute("user", user);
			model.addAttribute("ratings",ratings);
			model.addAttribute("book", book);
			return "bookdetail1";
			
		}
		return "error";
	}
	
	@PostMapping("/rate")
	public RedirectView ratingView(Model model, HttpSession httpSession,
	@RequestParam("userid") String userid,
	@RequestParam("username") String username,
	@RequestParam("bookid") String bookid,
	@RequestParam("rating") String rating,
	@RequestParam("comment") String comment) {
		
		Rating rating2 = new Rating();
		rating2.setBookid(Integer.valueOf(bookid));
		rating2.setComment(comment);
		rating2.setRating(Integer.valueOf(rating));
		rating2.setUserid(Integer.valueOf(userid));
		rating2.setUsername(username);
		boolean success = false;
		 int id = ratingDAO.getRatingofUser(Integer.valueOf(bookid), Integer.valueOf(userid));
		 if(id == -1 ) {
			 success = ratingDAO.NewRating(rating2);
		 } else {
			 rating2.setId(id);
			 success = ratingDAO.UpdateRating(rating2);
		 }
		String redirectURL = "/user/view/" + bookid;
		if(success) {
			return new RedirectView(redirectURL);
		}
		
		return new RedirectView("/error");
	}
	
	
	
	
	

}
