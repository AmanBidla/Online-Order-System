/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.DAO;

import com.project.OnlineOrderSystem.Model.Product;
import java.util.List;
import org.springframework.dao.DataAccessException;


/**
 *
 * @author Aman Bidla
 */
public interface ProductDao{
    Product FindProductByID(int ProductCode) throws DataAccessException;
    List<Product> AllProducts() throws DataAccessException;
    Product CreateProduct(Product product) throws DataAccessException;
    Product UpdateProduct(Product product) throws DataAccessException;
}
