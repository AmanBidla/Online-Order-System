/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.DAO;

import com.project.OnlineOrderSystem.Model.Order;
import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;


/**
 *
 * @author Aman Bidla
 */
public interface OrderDao{
    Order FindOrderByID(int ID) throws DataAccessException;
    List<Order> AllOrders() throws DataAccessException;
    List<Order> FindOrderByCustomerID(int CustomerID) throws DataAccessException;
    Order FindOrderByIDCustomerID(int ID, int CustomerID) throws DataAccessException;
    Order FindOrderByIDCustomerIDOrderDate(int ID, int CustomerID, Date OrderDate) throws DataAccessException;
    List<Order> FindOrderByCustomerIDOrderDateStatus(int CustomerID, Date OrderDate, String status) throws DataAccessException;
    List<Order> FindOrderByCustomerIDStatus(int CustomerID, String status) throws DataAccessException;
    List<Order> FindOrderByCustomerIDShipDate(int CustomerID, Date ShipDate) throws DataAccessException;
    Order CreateNewOrder(int CustomerID, int ProductCode, int Quantity, String Comments, Date OrderDate, Date RequiredDate, String ChequeNo) throws DataAccessException;
}
