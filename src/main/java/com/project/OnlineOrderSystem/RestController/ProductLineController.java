/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.ProductLineDao;
import com.project.OnlineOrderSystem.Exception.ProductExceptions.ProductNotFoundException;
import com.project.OnlineOrderSystem.Exception.ProductLineExceptions.ProductLineNotFoundException;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.Model.ProductLine;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductLineAssembler.ProductLineModelAssembler;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aman Bidla
 */

@RestController
@RequestMapping(value = "/api/")
public class ProductLineController {
    
    
    private final ProductLineDao ProductLine;
    private final ProductLineModelAssembler Assembler;   
    private Logger logger = Logger.getLogger(ProductLineController.class.toString());
  
    @Autowired
    public ProductLineController(ProductLineDao ProductLine, ProductLineModelAssembler Assembler) {
        this.ProductLine = ProductLine;
        this.Assembler = Assembler;
    }  
    
    @RequestMapping(value = "/FindProductLineByID/{ID}", method = RequestMethod.GET)
    public EntityModel<ProductLine> FindProductLineByID(@PathVariable int ID) throws DataAccessException{
      try{ 
        ProductLine productline =  ProductLine.FindProductLineByID(ID);
         return Assembler.toModel(productline);      
      }
      catch(DataAccessException ex){
         throw new ProductLineNotFoundException(ex);
      }    
    }
    
    @RequestMapping(value = "/AllProductLine", method = RequestMethod.GET)
    public CollectionModel<EntityModel<ProductLine>> AllProductLines() throws DataAccessException{
       try{
         List<EntityModel<ProductLine>> productline = ProductLine.AllProductLines().stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(productline,
                linkTo(methodOn(ProductLineController.class).AllProductLines()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new ProductLineNotFoundException(ex);
      }        
    }
    
    
}
