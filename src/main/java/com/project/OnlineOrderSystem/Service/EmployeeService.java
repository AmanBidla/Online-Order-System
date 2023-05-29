/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.DAO.EmployeeDao;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Office;
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
public class EmployeeService implements EmployeeDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public EmployeeService(DataSource dataSource) {

	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Employee FindEmployeeByID(int ID) throws DataAccessException{
       try{ 
          Employee Employee = jdbcTemplate.queryForObject("SELECT * FROM Employee WHERE ID=?",(resultSet, rowNum) -> {
            Employee employee= new Employee();
            employee.setID(resultSet.getInt("ID"));
            employee.setOfficeCode(resultSet.getInt("OfficeCode"));
            employee.setReportsTo(resultSet.getInt("ReportsTo"));
            employee.setFirstName(resultSet.getString("FirstName"));
            employee.setLastName(resultSet.getString("LastName"));
            employee.setEmail(resultSet.getString("Email"));
            employee.setTitle(resultSet.getString("Title"));
            employee.setDeskPhoneNo(resultSet.getString("DeskPhoneNo"));
            employee.setCellPhoneNo(resultSet.getString("CellPhoneNo"));
            return employee;
        },
        ID);
        return Employee;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }
    
    @Override
    public Office FindEmployeesOfficeByEmpID(int ID) throws DataAccessException{
        Employee employee = FindEmployeeByID(ID);        
        try{
            Office Office = jdbcTemplate.queryForObject("SELECT * FROM Office WHERE Code=?",(resultSet, rowNum) -> {
                Office office= new Office();
                office.setCode(resultSet.getInt("Code"));
                office.setAddress1(resultSet.getString("Address1"));
                office.setAddress2(resultSet.getString("Address2"));
                office.setCity(resultSet.getString("City"));
                office.setPostalCode(resultSet.getString("PostalCode"));
                office.setPhone(resultSet.getString("Phone"));
                return office;
        },
            employee.getOfficeCode());
            return Office;
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }
    
    @Override
    public List<Employee> AllEmployees() throws DataAccessException{
        
        try{
            List<Employee> employees = jdbcTemplate.query("SELECT * FROM Employee", (rs, rowNum) -> new Employee(
                rs.getInt("ID"),
                rs.getInt("OfficeCode"),
                rs.getInt("ReportsTo"),
                rs.getString("FirstName"),
                rs.getString("LastName"),
                rs.getString("Email"),
                rs.getString("Title"),
                rs.getString("DeskPhoneNo"),  
                rs.getString("CellPhoneNo")
                ));
             return employees;
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }
    
    @Override
    public int CreateNewEmployee(Employee newEmployee) throws DataAccessException{
        try{ 
              jdbcTemplate.update("INSERT INTO Employee (OfficeCode, ReportsTo, FirstName, LastName , Email, Title, DeskPhoneNo, CellPhoneNo) "
                              + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
                                new Object[] 
                                {newEmployee.getOfficeCode(), newEmployee.getReportsTo(), newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getEmail(), newEmployee.getTitle(), newEmployee.getDeskPhoneNo(), newEmployee.getCellPhoneNo()});
            }
            catch(DataAccessException ex){
                throw ex;
            }
        
        try{
            Employee Employee = jdbcTemplate.queryForObject("SELECT * FROM Employee WHERE Email=?",(resultSet, rowNum) -> {
            Employee employee = new Employee();
            employee.setID(resultSet.getInt("ID"));
            employee.setOfficeCode(resultSet.getInt("OfficeCode"));
            employee.setReportsTo(resultSet.getInt("ReportsTo"));
            employee.setFirstName(resultSet.getString("FirstName"));
            employee.setLastName(resultSet.getString("LastName"));
            employee.setEmail(resultSet.getString("Email"));
            employee.setTitle(resultSet.getString("Title"));
            employee.setDeskPhoneNo(resultSet.getString("DeskPhoneNo"));
            employee.setCellPhoneNo(resultSet.getString("CellPhoneNo"));
            return employee;
            },
            newEmployee.getEmail());
            return Employee.getID();
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }
}
