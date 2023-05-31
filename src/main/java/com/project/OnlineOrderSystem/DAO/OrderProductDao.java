/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.DAO;

import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.Model.OrderProduct;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.Model.ProductLine;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface OrderProductDao{
    Set<List> FindOrderProductDetailsByProductCode(int ProductCode) throws DataAccessException;
    Set<List> FindOrderProductDetailsByTextDescription(String TextDescription) throws DataAccessException;
    Set<List> FindOrderProductDetailsByCustomerID(int CustomerID) throws DataAccessException;
    Set<List> FindOrderProductDetailsByOrderID(int OrderID) throws DataAccessException;
    Set<List> FindOrderProductDetailsByOrderDate(Date OrderDate) throws DataAccessException;
    Set<List> FindOrderProductDetailsByPriceEach(double PriceEach) throws DataAccessException;
    Set<List> AllOrderProductDetails() throws DataAccessException;
}
