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
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.EntityModel;

/**
 *
 * @author Aman Bidla
 */
public interface OrderProductDao{
    List<List<EntityModel>> FindOrderProductDetailsByProductCode(int ProductCode) throws DataAccessException;
    List<List<EntityModel>> FindOrderProductDetailsByTextDescription(String TextDescription) throws DataAccessException;
    List<List<EntityModel>> FindOrderProductDetailsByCustomerID(int CustomerID) throws DataAccessException;
    List<List<EntityModel>> FindOrderProductDetailsByOrderID(int OrderID) throws DataAccessException;
    List<List<EntityModel>> FindOrderProductDetailsByOrderDate(Date OrderDate) throws DataAccessException;
    List<List<EntityModel>> FindOrderProductDetailsByPriceEach(double PriceEach) throws DataAccessException;
    List<List<EntityModel>> AllOrderProductDetails() throws DataAccessException;
}
