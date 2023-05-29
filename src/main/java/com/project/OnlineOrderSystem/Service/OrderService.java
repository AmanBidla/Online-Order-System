/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.DAO.OrderDao;
import com.project.OnlineOrderSystem.DAO.ProductDao;
import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.Model.Product;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aman Bidla
 */
@Service
public class OrderService implements OrderDao {


    @Autowired
    private ProductDao ProductDao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public OrderService(DataSource dataSource) {

	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Order FindOrderByID(int ID) throws DataAccessException {
       try{
        Order Order = jdbcTemplate.queryForObject("SELECT * FROM [Order] WHERE ID=?",(resultSet, rowNum) -> {
            Order order = new Order();
            order.setID(resultSet.getInt("ID"));
            order.setCustomerID(resultSet.getInt("CustomerID"));
            order.setOrderDate(resultSet.getDate("OrderDate"));
            order.setRequiredDate(resultSet.getDate("RequiredDate"));
            order.setShipped(resultSet.getDate("Shipped"));
            order.setStatus(resultSet.getString("Status"));
            order.setComments(resultSet.getString("Comments"));
            return order;
        },
        ID);
        return Order;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public List<Order> FindOrderByCustomerID(int CustomerID) throws DataAccessException {
        try{
            List<Order> orders = jdbcTemplate.query("SELECT * FROM [Order] WHERE CustomerID=?", (rs, rowNum) -> new Order(
                rs.getInt("ID"),
                rs.getInt("CustomerID"),
                rs.getDate("OrderDate"),
                rs.getDate("RequiredDate"),
                rs.getDate("Shipped"),
                rs.getString("Status"),
                rs.getString("Comments")
                ),
                CustomerID);
             return orders;
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public Order FindOrderByIDCustomerID(int ID, int CustomerID) throws DataAccessException {
       try{
        Order Order = jdbcTemplate.queryForObject("SELECT * FROM [Order] WHERE ID=? AND CustomerID=?",(resultSet, rowNum) -> {
            Order order = new Order();
            order.setID(resultSet.getInt("ID"));
            order.setCustomerID(resultSet.getInt("CustomerID"));
            order.setOrderDate(resultSet.getDate("OrderDate"));
            order.setRequiredDate(resultSet.getDate("RequiredDate"));
            order.setShipped(resultSet.getDate("Shipped"));
            order.setStatus(resultSet.getString("Status"));
            order.setComments(resultSet.getString("Comments"));
            return order;
        },
        ID, CustomerID);
        return Order;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public Order FindOrderByIDCustomerIDOrderDate(int ID, int CustomerID, Date OrderDate) throws DataAccessException {
       try{
        Order Order = jdbcTemplate.queryForObject("SELECT * FROM [Order] WHERE ID=? AND CustomerID=? AND OrderDate=?",(resultSet, rowNum) -> {
            Order order = new Order();
            order.setID(resultSet.getInt("ID"));
            order.setCustomerID(resultSet.getInt("CustomerID"));
            order.setOrderDate(resultSet.getDate("OrderDate"));
            order.setRequiredDate(resultSet.getDate("RequiredDate"));
            order.setShipped(resultSet.getDate("Shipped"));
            order.setStatus(resultSet.getString("Status"));
            order.setComments(resultSet.getString("Comments"));
            return order;
        },
        ID, CustomerID, OrderDate);
        return Order;
       }
       catch(DataAccessException ex){
           throw ex;
       }    
    }

    @Override
    public List<Order> FindOrderByCustomerIDOrderDateStatus(int CustomerID, Date OrderDate, String status) throws DataAccessException {
        try{
            List<Order> orders = jdbcTemplate.query("SELECT * FROM [Order] WHERE CustomerID=? AND OrderDate=? AND Status=?", (rs, rowNum) -> new Order(
                rs.getInt("ID"),
                rs.getInt("CustomerID"),
                rs.getDate("OrderDate"),
                rs.getDate("RequiredDate"),
                rs.getDate("Shipped"),
                rs.getString("Status"),
                rs.getString("Comments")
                ),
                CustomerID, OrderDate, status);
             return orders;
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public List<Order> FindOrderByCustomerIDStatus(int CustomerID, String status) throws DataAccessException {
        try{
            List<Order> orders = jdbcTemplate.query("SELECT * FROM [Order] WHERE CustomerID=? AND Status=?", (rs, rowNum) -> new Order(
                rs.getInt("ID"),
                rs.getInt("CustomerID"),
                rs.getDate("OrderDate"),
                rs.getDate("RequiredDate"),
                rs.getDate("Shipped"),
                rs.getString("Status"),
                rs.getString("Comments")
                ),
                CustomerID, status);
             return orders;
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public List<Order> FindOrderByCustomerIDShipDate(int CustomerID, Date ShipDate) throws DataAccessException {
        try{
            List<Order> orders = jdbcTemplate.query("SELECT * FROM [Order] WHERE CustomerID=? AND Shipped=?", (rs, rowNum) -> new Order(
                rs.getInt("ID"),
                rs.getInt("CustomerID"),
                rs.getDate("OrderDate"),
                rs.getDate("RequiredDate"),
                rs.getDate("Shipped"),
                rs.getString("Status"),
                rs.getString("Comments")
                ),
                CustomerID, ShipDate);
             return orders;
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public List<Order> AllOrders() throws DataAccessException {
        try{
            List<Order> orders = jdbcTemplate.query("SELECT * FROM [Order]", (rs, rowNum) -> new Order(
                rs.getInt("ID"),
                rs.getInt("CustomerID"),
                rs.getDate("OrderDate"),
                rs.getDate("RequiredDate"),
                rs.getDate("Shipped"),
                rs.getString("Status"),
                rs.getString("Comments")
                ));
             return orders;
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public Order CreateNewOrder(int CustomerID, int ProductCode, int Quantity, String Comments, Date OrderDate, Date RequiredDate, String ChequeNo) throws DataAccessException {
        
        Date Shipped=null;
        String Status="Order Placed";
        Product Product =ProductDao.FindProductByID(ProductCode);
        double PriceEach = (Product.getMSRP()/Product.getQuantity());
        double Amount = PriceEach*Quantity; 
        
        try{ 
              jdbcTemplate.update("INSERT INTO [ORDER] (CustomerID, OrderDate, RequiredDate, Shipped , Status, Comments) "
                              + "VALUES (?, ?, ?, ?, ?, ?)", 
                                new Object[] 
                                {CustomerID, OrderDate, RequiredDate, Shipped , Status, Comments});
            }
            catch(DataAccessException ex){
                throw ex;
            }
        
        Order Order;
        try{
            Order = jdbcTemplate.queryForObject("SELECT * FROM [Order] WHERE CustomerID=? AND OrderDate=? AND RequiredDate=?, Shipped=?, Status=?, Comments=?",(resultSet, rowNum) -> {
            Order order = new Order();
            order.setID(resultSet.getInt("ID"));
            order.setCustomerID(resultSet.getInt("CustomerID"));
            order.setOrderDate(resultSet.getDate("OrderDate"));
            order.setRequiredDate(resultSet.getDate("RequiredDate"));
            order.setShipped(resultSet.getDate("Shipped"));
            order.setStatus(resultSet.getString("Status"));
            order.setComments(resultSet.getString("Comments"));
            return order;
            },
            CustomerID, OrderDate, RequiredDate, Shipped , Status, Comments);
        }
        catch(DataAccessException ex){
            throw ex;
        }
        
        try{ 
              jdbcTemplate.update("INSERT INTO Order_Product (OrderID, ProductCode, Quantity, PriceEach) "
                              + "VALUES (?, ?, ?, ?)", 
                                new Object[] 
                                {Order.getID(), ProductCode, Quantity, PriceEach});
            }
        catch(DataAccessException ex){
                throw ex;
        }
        
        try{ 
              jdbcTemplate.update("INSERT INTO Payment(ChequeNo, CustomerID, PaymentDate, Amount) "
                              + "VALUES (?, ?, ?, ?)", 
                                new Object[] 
                                {ChequeNo, CustomerID, OrderDate, Amount});
            }
        catch(DataAccessException ex){
                throw ex;
        }                      
        return Order;
    }

}
