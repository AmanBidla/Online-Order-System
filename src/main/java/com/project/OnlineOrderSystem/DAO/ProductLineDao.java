/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.DAO;

import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.Model.ProductLine;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aman Bidla
 */
public interface ProductLineDao {
    
    ProductLine FindProductLineByID(int ID) throws DataAccessException;
    List<ProductLine> AllProductLines() throws DataAccessException;
}
