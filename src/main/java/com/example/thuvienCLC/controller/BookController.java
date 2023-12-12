package com.example.thuvienCLC.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.thuvienCLC.dao.BookDAO;
import com.example.thuvienCLC.model.User;
import com.example.thuvienCLC.model.Book;

import jakarta.servlet.http.HttpSession;
@Controller
public class BookController {
		private BookDAO bookDAO = new BookDAO();
		private static String UPLOAD_DIRECT ="src/main/resources/static/uploads/";
		@GetMapping("/admin")
		public String getAdminBook(Model model, HttpSession httpSession)
		{
			List<Book> books = bookDAO.getBooks();
			model.addAttribute("books",books);
			boolean AdminLogin = false;
			User user = (User)httpSession.getAttribute("user");
			if(user != null && user.getLogin() && user.getRole().equals("admin"))
			{
				AdminLogin = true;
				model.addAttribute("login", AdminLogin);
				model.addAttribute("user",user);
				return "admin";
			}
			else {
				model.addAttribute("login", AdminLogin);
				return "admin";
			}
			
		}
		@GetMapping("/admin/view/{id}")
		public String getBookAdmin(Model model, HttpSession httpSession,@PathVariable String id)
		{
			User user = (User) httpSession.getAttribute("user");
			if(user !=null && user.getLogin() && user.getRole().equals("admin")) {
				Book book = bookDAO.getBookById(Integer.valueOf(id));
				model.addAttribute("book", book);
				return "edit";
			}
			return "error";
		}
		
		@PostMapping("/admin/save/{id}")
		public String saveBookAdmin(Model model, HttpSession httpSession, @PathVariable String id,
				@RequestParam("title") String title,
				@RequestParam("author") String author,
				@RequestParam("category") String category,
				@RequestParam("description") String description,
				@RequestParam("publishdate") String publishdate,
				@RequestParam("numberofpage") String numberofpage,
				@RequestParam("cover") MultipartFile cover) throws IOException {
			User user = (User)httpSession.getAttribute("user");
			if(user != null && user.getLogin() && user.getRole().equals("admin"))
			{
				Book book = new Book();
				book.setId(Integer.valueOf(id));
				book.setTitle(title);
				book.setAuthor(author);
				book.setCategory(category);
				book.setDescription(description);
				book.setPublishdate(Date.valueOf(publishdate));
				book.setNumberofpage(Integer.valueOf(numberofpage));
				if(!cover.isEmpty())
				{
					File uploadDir = new File(UPLOAD_DIRECT);
					if(!uploadDir.exists()) {
						uploadDir.mkdir();
						
					}
					byte[] bytes = cover.getBytes();
					Path path = Paths.get(UPLOAD_DIRECT + cover.getOriginalFilename());
					Files.write(path, bytes);
					book.setCover(cover.getOriginalFilename());
				}
				boolean success = bookDAO.updateBook(book);
				if(success)
				{
					return "redirect:/admin";
				}
	
			}
		return "error";
		}
	
		@GetMapping("/admin/new")
		public String getNewBookAdmin(Model model, HttpSession httpSession) {
			User user = (User) httpSession.getAttribute("user");
			if(user != null && user.getLogin() && user.getRole().equals("admin")) {
				return "addbook";
			}
			return "error";
		}
		
		@PostMapping("/admin/new")
		public String saveBookAdmin(Model model, HttpSession httpSession, 
				@RequestParam("title") String title,
				@RequestParam("author") String author,
				@RequestParam("category") String category,
				@RequestParam("description") String description,
				@RequestParam("publishdate") String publishdate,
				@RequestParam("numberofpage") String numberofpage,
				@RequestParam("cover") MultipartFile cover) throws IOException {
			User user = (User)httpSession.getAttribute("user");
			if(user != null && user.getLogin() && user.getRole().equals("admin"))
			{
				Book book = new Book();
			
				book.setTitle(title);
				book.setAuthor(author);
				book.setCategory(category);
				book.setDescription(description);
				book.setPublishdate(Date.valueOf(publishdate));
				book.setNumberofpage(Integer.valueOf(numberofpage));
				if(!cover.isEmpty())
				{
					File uploadDir = new File(UPLOAD_DIRECT);
					if(!uploadDir.exists()) {
						uploadDir.mkdir();
						
					}
					byte[] bytes = cover.getBytes();
					Path path = Paths.get(UPLOAD_DIRECT + cover.getOriginalFilename());
					Files.write(path, bytes);
					book.setCover(cover.getOriginalFilename());
				}
				if(bookDAO.checkExist(book)) {
	                boolean result = bookDAO.addBook(book);
	                if(result) {
	                    return "redirect:/admin";
	                }
	            }else {
	                return "bookexisted";
	            }
	
			}
		return "error";
		}
		
		@PostMapping("/admin/delete")
	    public String deleteABook(Model model, HttpSession httpSession, @RequestParam("id") String id){
	        User user = (User) httpSession.getAttribute("user");
	        if(user != null && user.getLogin() && user.getRole().equals("admin")) {
	            boolean success = bookDAO.deleteABook(Integer.valueOf(id));
	            if(success) {
	                return "redirect:/admin";
	            }
	        }
	        return "error";
	    }
		
	
		
}
