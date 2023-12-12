package com.example.thuvienCLC.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.thuvienCLC.model.Rating;

public class RatingDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/thuvien";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "12345678";
    
    private static final String GET_RATING_A_BOOK ="SELECT * FROM rating WHERE bookid = ?";
    private static final String GET_RATING_OF_USER_FOR_BOOK = "SELECT * FROM rating WHERE bookid = ? AND userid = ?";
    private static final String NEW_RATING = "INSERT INTO rating(userid, username, bookid, rating, comment) VALUES (?,?,?,?,?)";
    private static final String UPDATE_RATING ="UPDATE rating SET rating = ? comment = ? WHERE id =?";
    
    public RatingDAO() {
		// TODO Auto-generated constructor stub
	}
    
    protected Connection getConnection() {
    	Connection connection = null;
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassWord);
    		
    	}catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}
    	return connection;
    	
    }
    
    public List<Rating> getRatingBook(int id){
    	List<Rating> ratings = new ArrayList<>();
    	try {
    		Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(GET_RATING_A_BOOK);
    		preparedStatement.setInt(1, id);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()) {
    			Rating rating = new Rating();
    			rating.setBookid(id);
    			rating.setComment(resultSet.getString("comment"));
    			rating.setId(resultSet.getInt("id"));
    			rating.setRating(resultSet.getInt("rating"));
    			rating.setUserid(resultSet.getInt("userid"));
    			rating.setUsername(resultSet.getString("username"));
    			ratings.add(rating);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}
    	return ratings;
    	
    }

	public int getRatingofUser(int bookid, int userid) {
		int result = -1;
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_RATING_OF_USER_FOR_BOOK);
			preparedStatement.setInt(1, bookid);
			preparedStatement.setInt(2, userid);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				result = resultSet.getInt("id");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public boolean NewRating(Rating rating)
	{
		boolean result = false;
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_RATING);
			preparedStatement.setInt(1, rating.getUserid());
			preparedStatement.setString(2, rating.getUsername());
			preparedStatement.setInt(3, rating.getBookid());
			preparedStatement.setInt(4, rating.getRating());
			preparedStatement.setString(5, rating.getComment());
			preparedStatement.execute();
			preparedStatement.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}
    
	public boolean UpdateRating(Rating rating)
	{
		boolean result = false;
		try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_RATING);
			
			preparedStatement.setInt(1, rating.getRating());
			preparedStatement.setString(2, rating.getComment());
			preparedStatement.setInt(3, rating.getId());
			preparedStatement.execute();
			preparedStatement.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}
    
}
