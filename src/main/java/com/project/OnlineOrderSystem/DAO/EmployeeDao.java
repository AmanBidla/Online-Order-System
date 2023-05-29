/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.DAO;

import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Office;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface EmployeeDao{
    
    Employee FindEmployeeByID(int ID) throws DataAccessException;
    Office FindEmployeesOfficeByEmpID(int ID) throws DataAccessException;
    List<Employee> AllEmployees() throws DataAccessException;
    int CreateNewEmployee(Employee newEmployee) throws DataAccessException;
}
