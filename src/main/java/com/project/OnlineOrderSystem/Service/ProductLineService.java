/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.DAO.ProductLineDao;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.Model.ProductLine;
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
public class ProductLineService implements ProductLineDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public ProductLineService(DataSource dataSource) {

	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ProductLine FindProductLineByID(int id) throws DataAccessException {
        try{ 
          ProductLine ProductLine = jdbcTemplate.queryForObject("SELECT * FROM ProductLine WHERE ID=?",(resultSet, rowNum) -> {
            ProductLine productline= new ProductLine();
            productline.setID(resultSet.getInt("ID"));
            productline.setTextDescription(resultSet.getString("TextDescription"));
            productline.setHTMLDescription(resultSet.getString("HTMLDescription"));
            productline.setImage(resultSet.getString("Image"));
            return productline;
        },
        id);
        return ProductLine;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public List<ProductLine> AllProductLines() throws DataAccessException {
        try{
            List<ProductLine> productline = jdbcTemplate.query("SELECT * FROM ProductLine", (rs, rowNum) -> new ProductLine(
                rs.getInt("ID"),
                rs.getString("TextDescription"),
                rs.getString("HTMLDescription"),
                rs.getString("Image")
                ));
             return productline;
        }
        catch(DataAccessException ex){
            throw ex;
        }  
    }
      

    
}
