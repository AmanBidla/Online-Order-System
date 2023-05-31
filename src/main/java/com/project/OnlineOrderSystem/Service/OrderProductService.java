/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.DAO.OrderProductDao;
import com.project.OnlineOrderSystem.Model.OrderProduct;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.Model.ProductLine;
import java.util.HashSet;
import java.util.Set;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aman Bidla
 */
@Service
public class OrderProductService implements OrderProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public OrderProductService(DataSource dataSource) {

	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Set<List> FindOrderProductDetailsByProductCode(int ProductCode) throws DataAccessException {
        try{                
            Set<List> OrderProductSet = new HashSet<>();
            List<OrderProduct> orderproduct = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE ProductCode=?",(rs, rowNum) -> new OrderProduct(
                rs.getInt("OrderID"),
                rs.getInt("ProductCode"),
                rs.getInt("Quantity"),
                rs.getDouble("PriceEach")
                ),
                ProductCode);
            
            OrderProductSet.add(orderproduct);
            
            List<Order> order = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE ProductCode=?",(rs, rowNum) -> new Order(
                rs.getInt("CustomerID"),
                rs.getDate("OrderDate")
                ),
                ProductCode);
            
            OrderProductSet.add(order);
            
            List<Product> product = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE ProductCode=?",(rs, rowNum) -> new Product(
                rs.getString("Name"),
                rs.getString("vendor")                     
                ),
                ProductCode);             

            OrderProductSet.add(product);
            
            List<ProductLine> productline = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE ProductCode=?",(rs, rowNum) -> new ProductLine(
                rs.getString("TextDescription")                     
                ),
                ProductCode);
            
            OrderProductSet.add(productline);
            
             return OrderProductSet;
        }
        catch(DataAccessException ex){
            throw ex;
        }  
    }

    @Override
    public Set<List> FindOrderProductDetailsByTextDescription(String TextDescription) throws DataAccessException {
        try {
            Set<List> OrderProductSet = new HashSet<>();
            List<OrderProduct> orderproduct = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE TextDescription=?", (rs, rowNum) -> new OrderProduct(
                    rs.getInt("OrderID"),
                    rs.getInt("ProductCode"),
                    rs.getInt("Quantity"),
                    rs.getDouble("PriceEach")
            ),
            TextDescription);

            OrderProductSet.add(orderproduct);

            List<Order> order = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE TextDescription=?", (rs, rowNum) -> new Order(
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            ),
            TextDescription);

            OrderProductSet.add(order);

            List<Product> product = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE TextDescription=?", (rs, rowNum) -> new Product(
                    rs.getString("Name"),
                    rs.getString("vendor")
            ),
            TextDescription);

            OrderProductSet.add(product);

            List<ProductLine> productline = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE TextDescription=?", (rs, rowNum) -> new ProductLine(
                    rs.getString("TextDescription")
            ),
            TextDescription);

            OrderProductSet.add(productline);

            return OrderProductSet;
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public Set<List> FindOrderProductDetailsByCustomerID(int CustomerID) throws DataAccessException {
        try {
            Set<List> OrderProductSet = new HashSet<>();
            List<OrderProduct> orderproduct = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE CustomerID=?", (rs, rowNum) -> new OrderProduct(
                    rs.getInt("OrderID"),
                    rs.getInt("ProductCode"),
                    rs.getInt("Quantity"),
                    rs.getDouble("PriceEach")
            ),
            CustomerID);

            OrderProductSet.add(orderproduct);

            List<Order> order = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE CustomerID=?", (rs, rowNum) -> new Order(
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            ),
            CustomerID);

            OrderProductSet.add(order);

            List<Product> product = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE CustomerID=?", (rs, rowNum) -> new Product(
                    rs.getString("Name"),
                    rs.getString("vendor")
            ),
            CustomerID);

            OrderProductSet.add(product);

            List<ProductLine> productline = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE CustomerID=?", (rs, rowNum) -> new ProductLine(
                    rs.getString("TextDescription")
            ),
            CustomerID);

            OrderProductSet.add(productline);

            return OrderProductSet;
        } catch (DataAccessException ex) {
            throw ex;
        }    
    }

    @Override
    public Set<List> FindOrderProductDetailsByOrderID(int OrderID) throws DataAccessException {
        try {
            Set<List> OrderProductSet = new HashSet<>();
            List<OrderProduct> orderproduct = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderID=?", (rs, rowNum) -> new OrderProduct(
                    rs.getInt("OrderID"),
                    rs.getInt("ProductCode"),
                    rs.getInt("Quantity"),
                    rs.getDouble("PriceEach")
            ),
            OrderID);

            OrderProductSet.add(orderproduct);

            List<Order> order = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderID=?", (rs, rowNum) -> new Order(
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            ),
            OrderID);

            OrderProductSet.add(order);

            List<Product> product = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderID=?", (rs, rowNum) -> new Product(
                    rs.getString("Name"),
                    rs.getString("vendor")
            ),
            OrderID);

            OrderProductSet.add(product);

            List<ProductLine> productline = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderID=?", (rs, rowNum) -> new ProductLine(
                    rs.getString("TextDescription")
            ),
            OrderID);

            OrderProductSet.add(productline);

            return OrderProductSet;
        } catch (DataAccessException ex) {
            throw ex;
        }    
    }

    @Override
    public Set<List> FindOrderProductDetailsByOrderDate(Date OrderDate) throws DataAccessException {
        try {
            Set<List> OrderProductSet = new HashSet<>();
            List<OrderProduct> orderproduct = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderDate=?", (rs, rowNum) -> new OrderProduct(
                    rs.getInt("OrderID"),
                    rs.getInt("ProductCode"),
                    rs.getInt("Quantity"),
                    rs.getDouble("PriceEach")
            ),
            OrderDate);

            OrderProductSet.add(orderproduct);

            List<Order> order = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderDate=?", (rs, rowNum) -> new Order(
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            ),
            OrderDate);

            OrderProductSet.add(order);

            List<Product> product = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderDate=?", (rs, rowNum) -> new Product(
                    rs.getString("Name"),
                    rs.getString("vendor")
            ),
            OrderDate);

            OrderProductSet.add(product);

            List<ProductLine> productline = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderDate=?", (rs, rowNum) -> new ProductLine(
                    rs.getString("TextDescription")
            ),
            OrderDate);

            OrderProductSet.add(productline);

            return OrderProductSet;
        } catch (DataAccessException ex) {
            throw ex;
        }    
    }

    @Override
    public Set<List> FindOrderProductDetailsByPriceEach(double PriceEach) throws DataAccessException {
        try {
            Set<List> OrderProductSet = new HashSet<>();
            List<OrderProduct> orderproduct = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE PriceEach=?", (rs, rowNum) -> new OrderProduct(
                    rs.getInt("OrderID"),
                    rs.getInt("ProductCode"),
                    rs.getInt("Quantity"),
                    rs.getDouble("PriceEach")
            ),
            PriceEach);

            OrderProductSet.add(orderproduct);

            List<Order> order = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE PriceEach=?", (rs, rowNum) -> new Order(
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            ),
            PriceEach);

            OrderProductSet.add(order);

            List<Product> product = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE PriceEach=?", (rs, rowNum) -> new Product(
                    rs.getString("Name"),
                    rs.getString("vendor")
            ),
            PriceEach);

            OrderProductSet.add(product);

            List<ProductLine> productline = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE PriceEach=?", (rs, rowNum) -> new ProductLine(
                    rs.getString("TextDescription")
            ),
            PriceEach);

            OrderProductSet.add(productline);

            return OrderProductSet;
        } catch (DataAccessException ex) {
            throw ex;
        }    
    }    

    @Override
    public Set<List> AllOrderProductDetails() throws DataAccessException {
        try {
            Set<List> OrderProductSet = new HashSet<>();
            List<OrderProduct> orderproduct = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID ORDER BY ProductCode", (rs, rowNum) -> new OrderProduct(
                    rs.getInt("OrderID"),
                    rs.getInt("ProductCode"),
                    rs.getInt("Quantity"),
                    rs.getDouble("PriceEach")
            ));

            OrderProductSet.add(orderproduct);

            List<Order> order = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID ORDER BY ProductCode", (rs, rowNum) -> new Order(
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            ));
            
            OrderProductSet.add(order);

            List<Product> product = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID ORDER BY ProductCode", (rs, rowNum) -> new Product(
                    rs.getString("Name"),
                    rs.getString("vendor")
            ));

            OrderProductSet.add(product);

            List<ProductLine> productline = jdbcTemplate.query("SELECT ProductCode, [Name], TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID ORDER BY ProductCode", (rs, rowNum) -> new ProductLine(
                    rs.getString("TextDescription")
            ));

            OrderProductSet.add(productline);

            return OrderProductSet;
        } catch (DataAccessException ex) {
            throw ex;
        }
    }
}
