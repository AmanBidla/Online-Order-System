/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.OrderProductDao;
import com.project.OnlineOrderSystem.Exception.OrderProductExceptions.OrderProductNotFoundException;
import com.project.OnlineOrderSystem.Model.OrderProduct;
import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.Model.ProductLine;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderProductAssembler.OrderProductModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductAssembler.ProductModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductLineAssembler.ProductLineModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderAssembler.OrderModelAssembler;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
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
public class OrderProductController {
    
    private final OrderProductDao OrderProductDao;
    private final OrderProductModelAssembler Assembler;
    private final OrderModelAssembler OrderAssembler;
    private final ProductModelAssembler ProductAssembler;
    private final ProductLineModelAssembler ProductLineAssembler;
        
    private Logger logger = Logger.getLogger(OrderProductController.class.toString());
  
    @Autowired
    public OrderProductController(OrderProductDao OrderProductDao, OrderProductModelAssembler Assembler, OrderModelAssembler OrderAssembler, ProductModelAssembler ProductAssembler, ProductLineModelAssembler ProductLineAssembler) {
        this.OrderProductDao = OrderProductDao;
        this.Assembler = Assembler;
        this.OrderAssembler = OrderAssembler;
        this.ProductAssembler = ProductAssembler;
        this.ProductLineAssembler = ProductLineAssembler;
    }
    
    @RequestMapping(value = "/OrderProductDetailsByProductCode/{ProductCode}", method = RequestMethod.GET)
    public CollectionModel<EntityModel> FindOrderProductDetailsByProductCode(@PathVariable int ProductCode) throws DataAccessException{
       try{
         Set<List> OrderProductSet = OrderProductDao.FindOrderProductDetailsByProductCode(ProductCode);
         
         Set<EntityModel> ModelAssembler = new HashSet<>();
         
         List<OrderProduct> orderproduct=null;
         List<Order> order=null;
         List<Product> product=null;
         List<ProductLine> productline=null;
         
         for(int i=0; i<OrderProductSet.size(); i++){
            if(OrderProductSet.contains(orderproduct)){
                for(OrderProduct value : orderproduct){
                    ModelAssembler.add(Assembler.toModel(value));    
                }                
            }
            else if(OrderProductSet.contains(order)){
                for(Order value : order){
                    ModelAssembler.add(OrderAssembler.toModel(value));    
                }
            }             
            else if(OrderProductSet.contains(product)){
                for(Product value : product){
                    ModelAssembler.add(ProductAssembler.toModel(value));    
                }           
            }
            else if(OrderProductSet.contains(productline)){
                for(ProductLine value : productline){
                    ModelAssembler.add(ProductLineAssembler.toModel(value));    
                }             
            }
     
        }
        return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(OrderProductController.class).FindOrderProductDetailsByProductCode(ProductCode)).withSelfRel());
      }
       catch(DataAccessException ex){
         throw new OrderProductNotFoundException(ex);
      }             
    }

    @RequestMapping(value = "/AllOrderProductDetails", method = RequestMethod.GET)
    public CollectionModel<EntityModel> AllOrderProductDetails() throws DataAccessException{
       try{
         Set<List> OrderProductSet = OrderProductDao.AllOrderProductDetails();
         
         Set <EntityModel> ModelAssembler = OrderProductDetailsPackaging(OrderProductSet);
        
         return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());
      }
       catch(DataAccessException ex){
         throw new OrderProductNotFoundException(ex);
      }    
    }

    @RequestMapping(value = "/OrderProductDetailsByTextDescription/{TextDescription}", method = RequestMethod.GET)    
    public CollectionModel<EntityModel> FindOrderProductDetailsByTextDescription(@PathVariable String TextDescription) throws DataAccessException{
       try{
         Set<List> OrderProductSet = OrderProductDao.FindOrderProductDetailsByTextDescription(TextDescription);
         
         Set <EntityModel> ModelAssembler = OrderProductDetailsPackaging(OrderProductSet);
        
         return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());
      }
       catch(DataAccessException ex){
         throw new OrderProductNotFoundException(ex);
      }        
    }
    
    @RequestMapping(value = "/OrderProductDetailsByCustomerID/{CustomerID}", method = RequestMethod.GET)
    public CollectionModel<EntityModel> FindOrderProductDetailsByCustomerID(@PathVariable int CustomerID) throws DataAccessException{
       try{
         Set<List> OrderProductSet = OrderProductDao.FindOrderProductDetailsByCustomerID(CustomerID);
         
         Set <EntityModel> ModelAssembler = OrderProductDetailsPackaging(OrderProductSet);
        
         return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());
      }
       catch(DataAccessException ex){
         throw new OrderProductNotFoundException(ex);
      }            
    }
    
    @RequestMapping(value = "/OrderProductDetailsByOrderID/{OrderID}", method = RequestMethod.GET)
    public CollectionModel<EntityModel> FindOrderProductDetailsByOrderID(@PathVariable int OrderID) throws DataAccessException{
       try{
         Set<List> OrderProductSet = OrderProductDao.FindOrderProductDetailsByOrderID(OrderID);
         
         Set <EntityModel> ModelAssembler = OrderProductDetailsPackaging(OrderProductSet);
        
         return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());
      }
       catch(DataAccessException ex){
         throw new OrderProductNotFoundException(ex);
      }    
    }
    
    @RequestMapping(value = "/OrderProductDetailsByOrderDate/{OrderDate}", method = RequestMethod.GET)
    public CollectionModel<EntityModel> FindOrderProductDetailsByOrderDate(@PathVariable Date OrderDate) throws DataAccessException{
       try{
         Set<List> OrderProductSet = OrderProductDao.FindOrderProductDetailsByOrderDate(OrderDate);
         
         Set <EntityModel> ModelAssembler = OrderProductDetailsPackaging(OrderProductSet);
        
         return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());
      }
       catch(DataAccessException ex){
         throw new OrderProductNotFoundException(ex);
      }        
    }

    @RequestMapping(value = "/OrderProductDetailsByPriceEach/{PriceEach}", method = RequestMethod.GET)
    public CollectionModel<EntityModel> FindOrderProductDetailsByPriceEach(@PathVariable double PriceEach) throws DataAccessException{
       try{
         Set<List> OrderProductSet = OrderProductDao.FindOrderProductDetailsByPriceEach(PriceEach);
         
         Set <EntityModel> ModelAssembler = OrderProductDetailsPackaging(OrderProductSet);
        
         return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());
      }
       catch(DataAccessException ex){
         throw new OrderProductNotFoundException(ex);
      }     
    }
    
    public Set<EntityModel> OrderProductDetailsPackaging(Set<List> OrderProductSet){
         
         Set<EntityModel> ModelAssembler = new HashSet<>();
         
         List<OrderProduct> orderproduct=null;
         List<Order> order=null;
         List<Product> product=null;
         List<ProductLine> productline=null;
         
         for(int i=0; i<OrderProductSet.size(); i++){
            if(OrderProductSet.contains(orderproduct)){
                for(OrderProduct value : orderproduct){
                    ModelAssembler.add(Assembler.toModel(value));    
                }                
            }
            else if(OrderProductSet.contains(order)){
                for(Order value : order){
                    ModelAssembler.add(OrderAssembler.toModel(value));    
                }
            }             
            else if(OrderProductSet.contains(product)){
                for(Product value : product){
                    ModelAssembler.add(ProductAssembler.toModel(value));    
                }           
            }
            else if(OrderProductSet.contains(productline)){
                for(ProductLine value : productline){
                    ModelAssembler.add(ProductLineAssembler.toModel(value));    
                }             
            }
     
        }
        return ModelAssembler;
    }
}
