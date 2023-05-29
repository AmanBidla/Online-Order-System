/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.ProductDao;
import com.project.OnlineOrderSystem.Exception.EmployeeExceptions.EmployeeNotFoundException;
import com.project.OnlineOrderSystem.Exception.ProductExceptions.ProductNotFoundException;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderAssembler.OrderModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderProductAssembler.OrderProductModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductAssembler.ProductModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductLineAssembler.ProductLineModelAssembler;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aman Bidla
 */
@RestController
@RequestMapping(value = "/api/")
public class ProductController {
    
    private final ProductDao Product;
    private final ProductModelAssembler Assembler;   
    private Logger logger = Logger.getLogger(ProductController.class.toString());
  
    @Autowired
    public ProductController(ProductDao Product, ProductModelAssembler Assembler) {
        this.Product = Product;
        this.Assembler = Assembler;
    }  
    
    @RequestMapping(value = "/FindProductID/{ProductCode}", method = RequestMethod.GET)
    public EntityModel<Product> FindProductByID(@PathVariable int ProductCode) throws DataAccessException{
      try{ 
        Product product =  Product.FindProductByID(ProductCode);
         return Assembler.toModel(product);      
      }
      catch(DataAccessException ex){
         throw new ProductNotFoundException(ex);
      }    
    }
    
    @RequestMapping(value = "/AllProducts", method = RequestMethod.GET)
    public CollectionModel<EntityModel<Product>> AllProducts() throws DataAccessException{
       try{
         List<EntityModel<Product>> Products = Product.AllProducts().stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Products,
                linkTo(methodOn(ProductController.class).AllProducts()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new ProductNotFoundException(ex);
      }        
    }

    @RequestMapping(value = "/NewProduct", method = RequestMethod.POST)
    public ResponseEntity<?> CreateNewProduct(@RequestBody Product newProduct) {
        
        int ProductCode=newProduct.getCode();
        
        try{
            
            ProductCode = Product.CreateProduct(newProduct);
            
            if(ProductCode!=0){
                EntityModel<Product> product = Assembler.toModel(Product.FindProductByID(ProductCode));
                
            
                return ResponseEntity
                        .created(product.getRequiredLink(IanaLinkRelations.SELF).toUri())
                        .body(product);
            }
            else {
                return ResponseEntity.ok("Failed to Create Product");
            }
        }
        catch(DataAccessException ex){
          throw new ProductNotFoundException(ex);
        }
    }

    @RequestMapping(value = "/UpdateProduct", method = RequestMethod.POST)
    public ResponseEntity<?> UpdateProduct(@RequestBody Product newProduct) {
        try{          
            Product.UpdateProduct(newProduct);
            
            EntityModel<Product> product = Assembler.toModel(Product.FindProductByID(newProduct.getCode()));
                
            
            return ResponseEntity
                    .created(product.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(product);
            }
        catch(DataAccessException ex){
          throw new ProductNotFoundException(ex);
        }
    }    
}
