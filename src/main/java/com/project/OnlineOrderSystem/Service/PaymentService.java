/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.Model.Payment;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.project.OnlineOrderSystem.DAO.PaymentDao;

/**
 *
 * @author Aman Bidla
 */
@Service
public class PaymentService implements PaymentDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public PaymentService(DataSource dataSource) {

	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
      
    @Override
    public List<Payment> FindPaymentDetailsByCustID(int CustomerID) throws DataAccessException{
        return null;
    }
    
    @Override
    public List<Payment> FindPayementDetailsByChequeNo(String ChequeNo) throws DataAccessException {
        return null;
    }
    
}
