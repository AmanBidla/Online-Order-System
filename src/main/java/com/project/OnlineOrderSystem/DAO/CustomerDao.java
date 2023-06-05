/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.DAO;

import com.project.OnlineOrderSystem.Model.Customer;
import com.project.OnlineOrderSystem.Model.Employee;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface CustomerDao{
    
    Customer FindCustomerByID(int ID) throws DataAccessException;
    List<Customer> ListOfCustomers() throws DataAccessException;
    Map<Integer, Employee> CreateNewCustomer(Customer customer) throws DataAccessException;   
}
