/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Service;

import com.project.OnlineOrderSystem.DAO.ProductDao;
import com.project.OnlineOrderSystem.Model.Product;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
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
public class ProductService implements ProductDao{
        
    @Autowired
    private JdbcTemplate jdbcTemplate;
	 
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
    @Autowired
    public ProductService(DataSource dataSource) {

	this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private Logger logger = Logger.getLogger(ProductService.class.toString());
    
    @Override
    public Product FindProductByID(int ProductCode) throws DataAccessException{
        try{ 
          Product Product = jdbcTemplate.queryForObject("SELECT * FROM Product WHERE Code=?",(resultSet, rowNum) -> {
            Product product= new Product();
            product.setCode(resultSet.getInt("Code"));
            product.setProductLineID(resultSet.getInt("ProductLineID"));
            product.setName(resultSet.getString("Name"));
            product.setVendor(resultSet.getString("vendor"));
            product.setDescription(resultSet.getString("Description"));
            product.setQuantity(resultSet.getInt("Quantity"));
            product.setBuyPrice(resultSet.getDouble("BuyPrice"));
            product.setMSRP(resultSet.getDouble("MSRP"));
            return product;
        },
        ProductCode);
        return Product;
       }
       catch(DataAccessException ex){
           throw ex;
       }
    }

    @Override
    public List<Product> AllProducts() throws DataAccessException {
        try{
            List<Product> product = jdbcTemplate.query("SELECT * FROM Product", (rs, rowNum) -> new Product(
                rs.getInt("Code"),
                rs.getInt("ProductLineID"),
                rs.getString("Name"),
                rs.getString("vendor"),
                rs.getString("Description"),
                rs.getInt("Quantity"),
                rs.getDouble("BuyPrice"),
                rs.getDouble("MSRP")
                ));
             return product;
        }
        catch(DataAccessException ex){
            throw ex;
        }    
    }

    @Override
    public Product CreateProduct(Product product) throws DataAccessException {
            String message = Integer.toString(product.getProductLineID())+" "+product.getName()+" "+product.getVendor()+" "+product.getDescription()+" "+Integer.toString(product.getQuantity())+" "+Double.toString(product.getBuyPrice())+" "+Double.toString(product.getMSRP());
            logger.info(message);
            try{ 
                jdbcTemplate.update("INSERT INTO Product (ProductLineID, Name, vendor, Description, Quantity, BuyPrice, MSRP) "
                                  + "VALUES (?, ?, ?, ?, ?, ?, ?)", 
                                    new Object[] 
                                    {product.getProductLineID(), product.getName(), product.getVendor(), product.getDescription(), product.getQuantity(), product.getBuyPrice(), product.getMSRP()});
                }
                catch(DataAccessException ex){
                    throw ex;
            }

            try{
                Product Product = jdbcTemplate.queryForObject("SELECT * FROM Product WHERE Name=? AND ProductLineID=? AND Vendor=? AND Description=? AND Quantity=? AND BuyPrice=? AND MSRP=?",(resultSet, rowNum) -> {
                Product newproduct = new Product();
                newproduct.setCode(resultSet.getInt("Code"));
                newproduct.setProductLineID(resultSet.getInt("ProductLineID"));
                newproduct.setName(resultSet.getString("Name"));
                newproduct.setVendor(resultSet.getString("vendor"));
                newproduct.setDescription(resultSet.getString("Description"));
                newproduct.setQuantity(resultSet.getInt("Quantity"));
                newproduct.setBuyPrice(resultSet.getDouble("BuyPrice"));
                newproduct.setMSRP(resultSet.getDouble("MSRP"));
                return newproduct;
                },
                product.getName(), product.getProductLineID(), product.getVendor(), product.getDescription(), product.getQuantity(), product.getBuyPrice(), product.getMSRP());
                return Product;
            }
            catch(DataAccessException ex){
                throw ex;
            }
        }

    @Override
    public Product UpdateProduct(Product product) throws DataAccessException {
            try{ 
                jdbcTemplate.update("UPDATE Product SET ProductLineID=?, Name=?, vendor=?, Description=?, Quantity=?, BuyPrice=?, MSRP=? WHERE Code=?",
                                    new Object[] 
                                    {product.getProductLineID(), product.getName(), product.getVendor(), product.getDescription(), product.getQuantity(), product.getBuyPrice(), product.getMSRP(), product.getCode()});
                }
                catch(DataAccessException ex){
                    throw ex;
            }
            try{ 
                Product Product = FindProductByID(product.getCode());
                }
                catch(DataAccessException ex){
                    throw ex;
            }
            return product;
    }
}
