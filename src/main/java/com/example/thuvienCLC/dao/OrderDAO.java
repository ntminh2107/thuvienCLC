package com.example.thuvienCLC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.thuvienCLC.model.Order;
import com.example.thuvienCLC.model.User;

public class OrderDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/thuvien";
    private String jdbcUserName = "root";
    private String jdbcPassWord = "12345678";
    
    
    private static final String GET_ALL_ORDER ="SELECT * FROM userorder WHERE userid = ? AND status = 1";
    private static final String GET_ALL_CANCEL_ORDER ="SELECT * FROM userorder WHERE userid =? AND status = 0";
    private static final String CREATE_NEW_ORDER ="INSERT INTO userorder(userid,bookid,bookname,quantity,status) VALUES(?,?,?,?,1)";
    private static final String CANCEL_ORDER="UPDATE userorder SET status = 0 WHERE id =?";
    public OrderDAO() {
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
    
    public List<Order> getallOrders(User user)
    {
    	List<Order> orders = new ArrayList<>();
    	try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDER);
			preparedStatement.setInt(1, user.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Order order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setUserid(resultSet.getInt("userid"));
				order.setBookid(resultSet.getInt("bookid"));
				order.setBookname(resultSet.getString("bookname"));
				order.setQuantity(resultSet.getInt("quantity"));
				order.setStatus(resultSet.getInt("status"));
				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	return orders;
 
    }
    
    public List<Order> getallCancleOrders(User user)
    {
    	List<Order> orders = new ArrayList<>();
    	try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CANCEL_ORDER);
			preparedStatement.setInt(1, user.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Order order = new Order();
				order.setId(resultSet.getInt("id"));
				order.setUserid(resultSet.getInt("userid"));
				order.setBookid(resultSet.getInt("bookid"));
				order.setBookname(resultSet.getString("bookname"));
				order.setQuantity(resultSet.getInt("quantity"));
				order.setStatus(resultSet.getInt("status"));
				orders.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	return orders;
 
    }
    public boolean createNewOrder(Order order) {
    	boolean result = false;
    	try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CREATE_NEW_ORDER);
			preparedStatement.setInt(1, order.getUserid());
			preparedStatement.setInt(2, order.getBookid());
			preparedStatement.setString(3, order.getBookname());
			preparedStatement.setInt(4, order.getQuantity());
			preparedStatement.execute();
			preparedStatement.close();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	return result;
    }
    
    public boolean cancelOrder(int id) {
    	boolean result = false;
    	try {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_ORDER);
			preparedStatement.setInt(1, id);
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
