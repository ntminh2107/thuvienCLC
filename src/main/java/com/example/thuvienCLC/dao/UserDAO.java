package com.example.thuvienCLC.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.thuvienCLC.model.User;

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/thuvien";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "12345678";
    
    
   private static final String CHECK_LOGIN_QUERRY ="SELECT * FROM account WHERE username = ? AND password = ?";
   private static final String CHECK_USER_EXIST ="SELECT * FROM account WHERE username = ?";
   private static final String REGISTER_NEW_USER ="INSERT INTO account(name, email, username, password, role) VALUES (?, ?, ?, ?, 'user')";
    public UserDAO() {
		// TODO Auto-generated constructor stub
	}
    
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
    public User checkUser(String username, String password) {
    	User user = new User();
    	try {
    		Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN_QUERRY);
    		preparedStatement.setString(1, username);
    		preparedStatement.setString(2, password);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while(resultSet.next())
    		{
    			user.setEmail(resultSet.getString("email"));
    			user.setId(resultSet.getInt("id"));
    			user.setName(resultSet.getString("name"));
    			user.setPassword(resultSet.getString("password"));
    			user.setRole(resultSet.getString("role"));
    			user.setUsername(resultSet.getString("username"));
    		}	
    }catch(Exception e) {
    	e.printStackTrace();
    }
    	return user;
}
    
    public boolean checkExist(String username) {
    	boolean result = false;
    	try {
    		Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_EXIST);
    		preparedStatement.setString(1, username);
    		ResultSet resultSet = null;
    		resultSet = preparedStatement.executeQuery();
    		if(resultSet.next())
    		{
    			result = true;
    			
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
			// TODO: handle exception
		}
    	return result;
    	
    }
    
    public boolean register(User user) {
    	boolean result = false;
    	try {
    		Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(REGISTER_NEW_USER);
    		preparedStatement.setString(1, user.getName());
    		preparedStatement.setString(2, user.getEmail());
    		preparedStatement.setString(3, user.getUsername());
    		preparedStatement.setString(4, user.getPassword());
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
