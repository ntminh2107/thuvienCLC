package com.example.thuvienCLC.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.thuvienCLC.model.Book;

public class BookDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/thuvien";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "12345678";
    private static final String GET_ALL_BOOKS ="SELECT * FROM book";
    private static final String GET_A_BOOK_BY_ID ="SELECT * FROM book WHERE id = ?";
    private static final String CHECK_BOOK_EXISTED ="SELECT * FROM book WHERE title = ? AND author = ?"; 
    private static final String ADD_A_BOOK= "INSERT INTO book(title,author,category,publishdate,numberofpage,sold,description,cover) VALUES(?,?,?,?,?,0,?,?)";
    private static final String DELETE_A_BOOK ="DELETE FROM book WHERE id = ?";
    private static final String UPDATE_A_BOOK ="UPDATE book SET title = ?, author = ?, description = ?, publishdate = ?, numberofpage = ?, cover = ? WHERE id = ?";
    public BookDAO() {}
    
    protected Connection getConnection() {
    	Connection connection = null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassWord);
            
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return connection;
	}
    
    public List<Book> getBooks()
    {
    	List<Book> books = new ArrayList<>();
    	try {
    		Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BOOKS);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()) {
    			Book book = new Book();
    			book.setId(resultSet.getInt("id"));
    			book.setAuthor(resultSet.getString("author"));
    			book.setCategory(resultSet.getString("category"));
    			book.setCover(resultSet.getString("cover"));
    			book.setDescription(resultSet.getString("description"));
    			book.setNumberofpage(resultSet.getInt("numberofpage"));
    			book.setPublishdate(resultSet.getDate("publishdate"));
    			book.setSold(resultSet.getInt("sold"));
    			book.setTitle(resultSet.getString("title"));
    			books.add(book);
   
    			
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}
		return books;
    }
    
    public Book getBookById(int id) {
    	Book book = new Book();
    	try {
    		Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(GET_A_BOOK_BY_ID);
    		preparedStatement.setInt(1, id);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			book.setId(resultSet.getInt("id"));
    			book.setAuthor(resultSet.getString("author"));
    			book.setCategory(resultSet.getString("category"));
    			book.setCover(resultSet.getString("cover"));
    			book.setDescription(resultSet.getString("description"));
    			book.setNumberofpage(resultSet.getInt("numberofpage"));
    			book.setPublishdate(resultSet.getDate("publishdate"));
    			book.setSold(resultSet.getInt("sold"));
    			book.setTitle(resultSet.getString("title"));		
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}
    	return book;
		
	}
    
    public boolean updateBook(Book book) {
    	boolean result = false;
    	try {
    		Connection connection = getConnection();
    		PreparedStatement preparedStatement= connection.prepareStatement(UPDATE_A_BOOK);
    		preparedStatement.setString(1, book.getTitle());
    		preparedStatement.setString(2,book.getAuthor());
    		preparedStatement.setString(3, book.getDescription());
    		preparedStatement.setDate(4, book.getPublishdate());
    		preparedStatement.setInt(5, book.getNumberofpage());
    		preparedStatement.setString(6, book.getCover());
    		preparedStatement.setInt(7, book.getId());
    		preparedStatement.execute();
    		preparedStatement.close();
    		result = true;
    		
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	return result;
    }
    
    public boolean checkExist(Book book) {
    	boolean notExist = true;
    	try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CHECK_BOOK_EXISTED);
			preparedStatement.setString(1, book.getTitle());
			preparedStatement.setString(2, book.getAuthor());
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				notExist = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	return notExist;
    }
    
    public boolean addBook(Book book)
    {
    	boolean result = false;
    	try {
    		Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(ADD_A_BOOK);
    		preparedStatement.setString(1, book.getTitle());
    		preparedStatement.setString(2,book.getAuthor());
    		preparedStatement.setString(3, book.getDescription());
    		preparedStatement.setDate(4, book.getPublishdate());
    		preparedStatement.setInt(5, book.getNumberofpage());
    		preparedStatement.setString(6, book.getDescription());
    		preparedStatement.setString(7, book.getCover());
    		preparedStatement.execute();
    		preparedStatement.close();
    		result = true;
    		
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	return result;
    }
    
    public boolean deleteABook(int id) {
        boolean result = false;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_A_BOOK);
            ps.setInt(1,Integer.valueOf(id));
            ps.execute();
            ps.close();
            result = true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
}
