/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.DAO.CustomerDao;
import com.project.OnlineOrderSystem.DAO.EmployeeDao;
import com.project.OnlineOrderSystem.Model.Customer;
import com.project.OnlineOrderSystem.Model.Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;
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
public class CustomerService implements CustomerDao {
    
    private final EmployeeDao Employee;
    private Logger logger = Logger.getLogger(CustomerService.class.toString());
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public CustomerService(DataSource dataSource, EmployeeDao Employee) {

	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.Employee = Employee;
    }
    
    @Override
    public Customer FindCustomerByID(int ID) throws DataAccessException {
        Customer Customer = jdbcTemplate.queryForObject("SELECT * FROM Customer WHERE ID=?",(resultSet, rowNum) -> {
            Customer customer= new Customer();
            customer.setID(resultSet.getInt("ID"));
            customer.setSalesRepEmpNo(resultSet.getInt("SalesRepEmpNo"));
            customer.setFirstName(resultSet.getString("FirstName"));
            customer.setLastName(resultSet.getString("LastName"));
            customer.setPhone(resultSet.getString("Phone"));
            customer.setAddress1(resultSet.getString("Address1"));
            customer.setAddress2(resultSet.getString("Address2"));
            customer.setCity(resultSet.getString("City"));
            customer.setProvince(resultSet.getString("Province"));
            customer.setPostalCode(resultSet.getString("PostalCode"));
            customer.setCreditLimit(resultSet.getDouble("CreditLimit"));
            return customer;
        },
        ID);
        return Customer;
    }
    
    @Override
    public List<Customer> ListOfCustomers() throws DataAccessException {
        List<Customer> Customers;  
          Customers = jdbcTemplate.query("SELECT * FROM Customer", (rs, rowNum) -> new Customer(
                rs.getInt("ID"),
                rs.getInt("SalesRepEmpNo"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("Phone"),
                rs.getString("Address1"),
                rs.getString("Address2"),
                rs.getString("City"),  
                rs.getString("Province"),
                rs.getString("PostalCode"),
                rs.getDouble("CreditLimit")
                ));       
        return Customers;
    }
    
    
    @Override
    public Map<Integer, Employee> CreateNewCustomer(Customer customer) throws DataAccessException{
        
        Random random = new Random(); 
        List<Employee> AllEmployees;
        Map<Integer,Employee> CustomerRepDetails = new HashMap<>();
        Employee Representative = new Employee();
        int CustomerID=0;
        
        try{
            AllEmployees=Employee.AllEmployees();
            
            Representative.setID(1);
            while(Representative.getID()==1){
                Representative=AllEmployees.get(random.nextInt(AllEmployees.size()));
                logger.info("Rep id: "+Integer.toString(Representative.getID()));
            }
            
            try{ 
                jdbcTemplate.update("INSERT INTO Customer (SalesRepEmpNo, FirstName, LastName , Phone, Address1, Address2, City, Province, PostalCode, CreditLimit) "
                              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", 
                                new Object[] 
                                {Representative.getID(), customer.getFirstName(), customer.getLastName(), customer.getPhone(), customer.getAddress1(), customer.getAddress2(), customer.getCity(), customer.getProvince(), customer.getPostalCode(), customer.getCreditLimit()});
            }
            catch(DataAccessException ex){
                throw ex;
            }
        
            try{
                CustomerID = jdbcTemplate.queryForObject("SELECT ID FROM Customer WHERE FirstName=? AND LastName=? AND Phone=? AND SalesRepEmpNo=?",(resultSet, rowNum) -> {
                    customer.setID(resultSet.getInt("ID"));
                    return customer.getID();
                },customer.getFirstName(), customer.getLastName(), customer.getPhone(), Representative.getID());
            }
            catch(DataAccessException ex){
                throw ex;
            }         
        }
        catch(Exception ex){
          logger.info("Error fetching list of Employees com.project.OnlineOrderSystem.Service.CustomerService.CreateNewCustomer " + ex);   
        } 
        
        CustomerRepDetails.put(CustomerID, Representative);
        
        return CustomerRepDetails;
    }
}
