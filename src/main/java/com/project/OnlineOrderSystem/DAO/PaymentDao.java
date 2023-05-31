/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.DAO;

import com.project.OnlineOrderSystem.Model.Payment;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface PaymentDao{
    List<Payment> FindPaymentDetailsByCustID(int CustomerID) throws DataAccessException;
    Payment FindPayementDetailsByChequeNo(String ChequeNo) throws DataAccessException;
    List<Payment> AllPaymentDetails() throws DataAccessException;
}
