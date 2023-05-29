/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.DAO.EmployeeDao;
import com.project.OnlineOrderSystem.DAO.OfficeDao;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Office;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class OfficeService implements OfficeDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
   
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private EmployeeDao EmployeeDao;
    
    @Autowired
    public OfficeService(DataSource dataSource) {

	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Map<Employee, Office> FindEmployeebyOfficeCode(int Code) throws DataAccessException {
        Map<Employee, Office> EmployeeOffice = new HashMap<>();
        List<Employee> employees = new ArrayList<>();
        Office office = new Office();
        try{
            employees = jdbcTemplate.query("SELECT * FROM Employee Where OfficeCode=?", (rs, rowNum) -> new Employee(
                    rs.getInt("ID"),
                    rs.getInt("OfficeCode"),
                    rs.getInt("ReportsTo"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("Email"),
                    rs.getString("Title"),
                     rs.getString("DeskPhoneNo"),  
                    rs.getString("CellPhoneNo")
                    ),
                    Code);
        }
        catch(DataAccessException ex){
           throw ex;
        }
           for(Employee employee : employees){
               Office offices = EmployeeDao.FindEmployeesOfficeByEmpID(employee.getID());
               EmployeeOffice.put(employee, offices);
           }   
        return EmployeeOffice;
    } 

    @Override
    public List<Office> FindAllOffices() throws DataAccessException {
        
        try{
            List<Office> offices = jdbcTemplate.query("SELECT * FROM Office", (rs, rowNum) -> new Office(
                rs.getInt("Code"),
                rs.getString("Address1"),
                rs.getString("Address2"),
                rs.getString("City"),
                rs.getString("PostalCode"),
                rs.getString("Phone")
                ));
             return offices;
        }
        catch(DataAccessException ex){
            throw ex;
        }  
    }

    @Override
    public Office FindOfficeByCode(int code) throws DataAccessException {
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
            code);
            return Office;
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }

    @Override
    public int AddNewOffice(Office newOffice) throws DataAccessException {
        try{ 
              jdbcTemplate.update("INSERT INTO Office (Address1, Address2, City, PostalCode, Phone) "
                              + "VALUES (?, ?, ?, ?, ?)", 
                                new Object[] 
                                {newOffice.getAddress1(), newOffice.getAddress2(), newOffice.getCity(), newOffice.getPostalCode(), newOffice.getPhone()});
            }
            catch(DataAccessException ex){
                throw ex;
            }
        
        try{
            Office Office = jdbcTemplate.queryForObject("SELECT * FROM Office WHERE Phone=?",(resultSet, rowNum) -> {
            Office office= new Office();
            office.setCode(resultSet.getInt("Code"));
            office.setAddress1(resultSet.getString("Address1"));
            office.setAddress2(resultSet.getString("Address2"));
            office.setCity(resultSet.getString("City"));
            office.setPostalCode(resultSet.getString("PostalCode"));
            office.setPhone(resultSet.getString("Phone"));
            return office;
            },
            newOffice.getPhone());
            return Office.getCode();
        }
        catch(DataAccessException ex){
            throw ex;
        }
    }
}
