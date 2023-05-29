/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.DAO;

import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Office;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface OfficeDao{
    
    Map<Employee, Office> FindEmployeebyOfficeCode(int Code) throws DataAccessException;
    List<Office> FindAllOffices() throws DataAccessException;
    Office FindOfficeByCode(int Code) throws DataAccessException;
    int AddNewOffice(Office newOffice) throws DataAccessException;
}
