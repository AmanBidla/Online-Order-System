/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.DAO.OrderProductDao;
import com.project.OnlineOrderSystem.DAO.ProductDao;
import com.project.OnlineOrderSystem.DAO.OrderDao;
import com.project.OnlineOrderSystem.DAO.ProductLineDao;
import com.project.OnlineOrderSystem.DAO.CustomerDao;
import com.project.OnlineOrderSystem.Model.Customer;
import com.project.OnlineOrderSystem.Model.OrderProduct;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.Model.ProductLine;
import com.project.OnlineOrderSystem.ModelAssemblers.CustomerAssembler.CustomerModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderAssembler.OrderModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderProductAssembler.OrderProductModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductAssembler.ProductModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductLineAssembler.ProductLineModelAssembler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import javax.sql.RowSet;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
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
    
    private final OrderProductModelAssembler OrderProductAssembler;
    private final OrderModelAssembler OrderAssembler;
    private final ProductModelAssembler ProductAssembler;
    private final ProductLineModelAssembler ProductLineAssembler;
    private final CustomerModelAssembler CustomerAssembler;
    private final ProductDao ProductDao;
    private final ProductLineDao ProductLineDao;
    private final OrderDao OrderDao;
    private final CustomerDao CustomerDao;
    private Logger logger = Logger.getLogger(OrderProductService.class.toString());

    @Autowired
    public OrderProductService(DataSource dataSource, OrderProductModelAssembler OrderProductAssembler, OrderModelAssembler OrderAssembler, ProductModelAssembler ProductAssembler, ProductLineModelAssembler ProductLineAssembler, CustomerModelAssembler CustomerAssembler, ProductDao ProductDao, ProductLineDao ProductLineDao, OrderDao OrderDao, CustomerDao CustomerDao) {
	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.ProductAssembler = ProductAssembler;
        this.ProductLineAssembler = ProductLineAssembler;
        this.OrderProductAssembler = OrderProductAssembler;
        this.OrderAssembler = OrderAssembler;
        this.CustomerAssembler = CustomerAssembler;
        this.ProductDao = ProductDao;
        this.ProductLineDao = ProductLineDao;
        this.OrderDao = OrderDao;
        this.CustomerDao = CustomerDao;
    }

    @Override
    public List<List<EntityModel>> FindOrderProductDetailsByProductCode(int ProductCode) {
        try{ 
            
            String query = "SELECT ProductCode, [Name], ProductLineID, TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE ProductCode="+ProductCode+" ORDER BY ProductCode"; 
            SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
            List<List<EntityModel>> ModelAssembler = new ArrayList<>();
            
            
            while(rs.next()){
             List<EntityModel> ObjectAssembler = new ArrayList<>();
               Product Product = ProductDao.FindProductByID(rs.getInt("ProductCode"));
               ProductLine ProductLine = ProductLineDao.FindProductLineByID(rs.getInt("ProductLineID"));
               Order Order = OrderDao.FindOrderByID(rs.getInt("OrderID"));
               Customer Customer = CustomerDao.FindCustomerByID(rs.getInt("CustomerID"));
               OrderProduct OrderProduct = new OrderProduct(rs.getInt("Quantity"),rs.getDouble("PriceEach")); 
               ObjectAssembler.add(ProductAssembler.toModel(Product));
               ObjectAssembler.add(ProductLineAssembler.toModel(ProductLine));
               ObjectAssembler.add(OrderAssembler.toModel(Order)); 
               ObjectAssembler.add(OrderProductAssembler.toModel(OrderProduct));
               ObjectAssembler.add(CustomerAssembler.toModel(Customer));
               ModelAssembler.add(ObjectAssembler);
            }
            
            return ModelAssembler;
        }
        catch(DataAccessException ex){
            throw ex;
        } 
    }

    @Override
    public List<List<EntityModel>> FindOrderProductDetailsByTextDescription(String TextDescription) throws DataAccessException {
        try {
            String query = "SELECT ProductCode, [Name], ProductLineID, TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE TextDescription='"+TextDescription +"' ORDER BY ProductLineID"; 
            SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
            List<List<EntityModel>> ModelAssembler = new ArrayList<>();
            
            
            while(rs.next()){
             List<EntityModel> ObjectAssembler = new ArrayList<>();
               Product Product = ProductDao.FindProductByID(rs.getInt("ProductCode"));
               ProductLine ProductLine = ProductLineDao.FindProductLineByID(rs.getInt("ProductLineID"));
               Order Order = OrderDao.FindOrderByID(rs.getInt("OrderID"));
               Customer Customer = CustomerDao.FindCustomerByID(rs.getInt("CustomerID"));
               OrderProduct OrderProduct = new OrderProduct(rs.getInt("Quantity"),rs.getDouble("PriceEach")); 
               ObjectAssembler.add(ProductAssembler.toModel(Product));
               ObjectAssembler.add(ProductLineAssembler.toModel(ProductLine));
               ObjectAssembler.add(OrderAssembler.toModel(Order)); 
               ObjectAssembler.add(OrderProductAssembler.toModel(OrderProduct));
               ObjectAssembler.add(CustomerAssembler.toModel(Customer));
               ModelAssembler.add(ObjectAssembler);
            }
            
            return ModelAssembler;          
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public List<List<EntityModel>> FindOrderProductDetailsByCustomerID(int CustomerID) throws DataAccessException {
        try {
            String query = "SELECT ProductCode, [Name], ProductLineID, TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE CustomerID="+CustomerID +" ORDER BY OrderDate"; 
            SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
            List<List<EntityModel>> ModelAssembler = new ArrayList<>();
            
            
            while(rs.next()){
             List<EntityModel> ObjectAssembler = new ArrayList<>();
               Product Product = ProductDao.FindProductByID(rs.getInt("ProductCode"));
               ProductLine ProductLine = ProductLineDao.FindProductLineByID(rs.getInt("ProductLineID"));
               Order Order = OrderDao.FindOrderByID(rs.getInt("OrderID"));
               Customer Customer = CustomerDao.FindCustomerByID(rs.getInt("CustomerID"));
               OrderProduct OrderProduct = new OrderProduct(rs.getInt("Quantity"),rs.getDouble("PriceEach")); 
               ObjectAssembler.add(ProductAssembler.toModel(Product));
               ObjectAssembler.add(ProductLineAssembler.toModel(ProductLine));
               ObjectAssembler.add(OrderAssembler.toModel(Order)); 
               ObjectAssembler.add(OrderProductAssembler.toModel(OrderProduct));
               ObjectAssembler.add(CustomerAssembler.toModel(Customer));
               ModelAssembler.add(ObjectAssembler);
            }
            
            return ModelAssembler;         
        } 
        catch (DataAccessException ex) {
            throw ex;
        }    
    }

    @Override
    public List<List<EntityModel>> FindOrderProductDetailsByOrderID(int OrderID) throws DataAccessException {
        try {
            String query = "SELECT ProductCode, [Name], ProductLineID, TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderID="+OrderID+" ORDER BY OrderDate"; 
            SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
            List<List<EntityModel>> ModelAssembler = new ArrayList<>();
            
            
            while(rs.next()){
             List<EntityModel> ObjectAssembler = new ArrayList<>();
               Product Product = ProductDao.FindProductByID(rs.getInt("ProductCode"));
               ProductLine ProductLine = ProductLineDao.FindProductLineByID(rs.getInt("ProductLineID"));
               Order Order = OrderDao.FindOrderByID(rs.getInt("OrderID"));
               Customer Customer = CustomerDao.FindCustomerByID(rs.getInt("CustomerID"));
               OrderProduct OrderProduct = new OrderProduct(rs.getInt("Quantity"),rs.getDouble("PriceEach")); 
               ObjectAssembler.add(ProductAssembler.toModel(Product));
               ObjectAssembler.add(ProductLineAssembler.toModel(ProductLine));
               ObjectAssembler.add(OrderAssembler.toModel(Order)); 
               ObjectAssembler.add(OrderProductAssembler.toModel(OrderProduct));
               ObjectAssembler.add(CustomerAssembler.toModel(Customer));
               ModelAssembler.add(ObjectAssembler);
            }
            
            return ModelAssembler;  
        } 
        catch (DataAccessException ex) {
            throw ex;
        }    
    }

    @Override
    public List<List<EntityModel>> FindOrderProductDetailsByOrderDate(Date OrderDate) throws DataAccessException {
        try {
            java.sql.Date Date = new java.sql.Date(OrderDate.getTime());        
            String query = "SELECT ProductCode, [Name], ProductLineID, TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE OrderDate='"+Date+"' ORDER BY ProductCode"; 
            SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
            List<List<EntityModel>> ModelAssembler = new ArrayList<>();
            
            
            while(rs.next()){
             List<EntityModel> ObjectAssembler = new ArrayList<>();
               Product Product = ProductDao.FindProductByID(rs.getInt("ProductCode"));
               ProductLine ProductLine = ProductLineDao.FindProductLineByID(rs.getInt("ProductLineID"));
               Order Order = OrderDao.FindOrderByID(rs.getInt("OrderID"));
               Customer Customer = CustomerDao.FindCustomerByID(rs.getInt("CustomerID"));
               OrderProduct OrderProduct = new OrderProduct(rs.getInt("Quantity"),rs.getDouble("PriceEach")); 
               ObjectAssembler.add(ProductAssembler.toModel(Product));
               ObjectAssembler.add(ProductLineAssembler.toModel(ProductLine));
               ObjectAssembler.add(OrderAssembler.toModel(Order)); 
               ObjectAssembler.add(OrderProductAssembler.toModel(OrderProduct));
               ObjectAssembler.add(CustomerAssembler.toModel(Customer));
               ModelAssembler.add(ObjectAssembler);
            }
            
            return ModelAssembler;
        } catch (DataAccessException ex) {
            throw ex;
        }    
    }

    @Override
    public List<List<EntityModel>> FindOrderProductDetailsByPriceEach(double PriceEach) throws DataAccessException {
        try {
            String query = "SELECT ProductCode, [Name], ProductLineID, TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID WHERE PriceEach="+PriceEach+" ORDER BY PriceEach"; 
            SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
            List<List<EntityModel>> ModelAssembler = new ArrayList<>();
            
            
            while(rs.next()){
             List<EntityModel> ObjectAssembler = new ArrayList<>();
               Product Product = ProductDao.FindProductByID(rs.getInt("ProductCode"));
               ProductLine ProductLine = ProductLineDao.FindProductLineByID(rs.getInt("ProductLineID"));
               Order Order = OrderDao.FindOrderByID(rs.getInt("OrderID"));
               Customer Customer = CustomerDao.FindCustomerByID(rs.getInt("CustomerID"));
               OrderProduct OrderProduct = new OrderProduct(rs.getInt("Quantity"),rs.getDouble("PriceEach")); 
               ObjectAssembler.add(ProductAssembler.toModel(Product));
               ObjectAssembler.add(ProductLineAssembler.toModel(ProductLine));
               ObjectAssembler.add(OrderAssembler.toModel(Order)); 
               ObjectAssembler.add(OrderProductAssembler.toModel(OrderProduct));
               ObjectAssembler.add(CustomerAssembler.toModel(Customer));
               ModelAssembler.add(ObjectAssembler);
            }
            
            return ModelAssembler;
        } catch (DataAccessException ex) {
            throw ex;
        }    
    }    

    @Override
    public List<List<EntityModel>> AllOrderProductDetails() throws DataAccessException {
        try {
            String query = "SELECT ProductCode, [Name], ProductLineID, TextDescription, vendor, CustomerID, OrderID, OrderDate, Order_Product.Quantity, PriceEach FROM [Order] join Order_Product ON [Order].ID=Order_Product.OrderID join Product ON Product.Code=Order_Product.ProductCode join ProductLine ON ProductLine.ID=Product.ProductLineID ORDER BY Quantity"; 
            SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
            List<List<EntityModel>> ModelAssembler = new ArrayList<>();
            
            
            while(rs.next()){
             List<EntityModel> ObjectAssembler = new ArrayList<>();
               Product Product = ProductDao.FindProductByID(rs.getInt("ProductCode"));
               ProductLine ProductLine = ProductLineDao.FindProductLineByID(rs.getInt("ProductLineID"));
               Order Order = OrderDao.FindOrderByID(rs.getInt("OrderID"));
               Customer Customer = CustomerDao.FindCustomerByID(rs.getInt("CustomerID"));
               OrderProduct OrderProduct = new OrderProduct(rs.getInt("Quantity"),rs.getDouble("PriceEach")); 
               ObjectAssembler.add(ProductAssembler.toModel(Product));
               ObjectAssembler.add(ProductLineAssembler.toModel(ProductLine));
               ObjectAssembler.add(OrderAssembler.toModel(Order)); 
               ObjectAssembler.add(OrderProductAssembler.toModel(OrderProduct));
               ObjectAssembler.add(CustomerAssembler.toModel(Customer));
               ModelAssembler.add(ObjectAssembler);
            }
            
            return ModelAssembler;
        } catch (DataAccessException ex) {
            throw ex;
        }
    }
}
