/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.OrderDao;
import com.project.OnlineOrderSystem.Exception.OrderExceptions.OrderNotFoundException;
import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderAssembler.OrderModelAssembler;
import java.util.Date;
import java.util.List;
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
public class OrderController {
    
    private final OrderDao Order;
    private final OrderModelAssembler Assembler;
    private Logger logger = Logger.getLogger(OfficeController.class.toString());

    
    @Autowired
    public OrderController(OrderDao Order, OrderModelAssembler Assembler) {
        this.Order = Order;
        this.Assembler = Assembler;
    }
    
   @RequestMapping(value = "/Order/{id}", method = RequestMethod.GET)
   public EntityModel<Order> FindOrderByID(@PathVariable int id) throws DataAccessException {
      try{ 
        Order order =  Order.FindOrderByID(id);
         return Assembler.toModel(order);      
      }
      catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }    
   }
   
   @RequestMapping(value = "/AllOrders", method = RequestMethod.GET)
   public CollectionModel<EntityModel<Order>> AllOrders() throws DataAccessException{
       try{
         List<EntityModel<Order>> Orders = Order.AllOrders().stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Orders,
                linkTo(methodOn(OrderController.class).AllOrders()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }       
   }
   
   @RequestMapping(value = "/CustomerOrders/{CustomerID}", method = RequestMethod.GET)
   public CollectionModel<EntityModel<Order>> FindOrderByCustomerID(@PathVariable int CustomerID) throws DataAccessException{
       try{
         List<EntityModel<Order>> Orders = Order.FindOrderByCustomerID(CustomerID).stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Orders,
                linkTo(methodOn(OrderController.class).AllOrders()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }   
    }
   
   @RequestMapping(value = "/CustomersOrder/{OrderID}/{CustomerID}", method = RequestMethod.GET)
   public EntityModel<Order> FindOrderByIDCustomerID(@PathVariable(name = "OrderID") int OrderID, @PathVariable(name = "CustomerID") int CustomerID) throws DataAccessException {
      try{ 
        Order order =  Order.FindOrderByIDCustomerID(OrderID, CustomerID);
         return Assembler.toModel(order);      
      }
      catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }    
   }
   
   @RequestMapping(value = "/CustomersOrderOrderDate/{OrderID}/{CustomerID}/{OrderDate}", method = RequestMethod.GET)
   public EntityModel<Order> FindOrderByIDCustomerIDOrderDate(@PathVariable(name = "OrderID") int OrderID, @PathVariable(name = "CustomerID") int CustomerID, @PathVariable(name = "OrderDate") Date OrderDate) throws DataAccessException {
      try{ 
        Order order =  Order.FindOrderByIDCustomerIDOrderDate(OrderID, CustomerID, OrderDate);
         return Assembler.toModel(order);      
      }
      catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }    
   }

   @RequestMapping(value = "/CustomerOrdersOrderDateStatus/{CustomerID}/{OrderDate}/{Status}", method = RequestMethod.GET)
   public CollectionModel<EntityModel<Order>> FindOrderByCustomerIDOrderDateStatus(@PathVariable(name = "CustomerID") int CustomerID, @PathVariable(name = "OrderDate") Date OrderDate, @PathVariable(name = "Status") String Status) throws DataAccessException{
       try{
         List<EntityModel<Order>> Orders = Order.FindOrderByCustomerIDOrderDateStatus(CustomerID, OrderDate, Status).stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Orders,
                linkTo(methodOn(OrderController.class).AllOrders()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }   
    }

   @RequestMapping(value = "/CustomerOrdersStatus/{CustomerID}/{Status}", method = RequestMethod.GET)
   public CollectionModel<EntityModel<Order>> FindOrderByCustomerIDStatus(@PathVariable(name = "CustomerID") int CustomerID, @PathVariable(name = "Status") String Status) throws DataAccessException{
       try{
         List<EntityModel<Order>> Orders = Order.FindOrderByCustomerIDStatus(CustomerID, Status).stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Orders,
                linkTo(methodOn(OrderController.class).AllOrders()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }   
    }
      
   @RequestMapping(value = "/CustomerOrdersShippingDate/{CustomerID}/{ShipDate}", method = RequestMethod.GET)
   public CollectionModel<EntityModel<Order>> FindOrderByCustomerIDShipDate(@PathVariable(name = "CustomerID") int CustomerID, @PathVariable(name = "ShipDate") Date ShipDate) throws DataAccessException{
       try{
         List<EntityModel<Order>> Orders = Order.FindOrderByCustomerIDShipDate(CustomerID, ShipDate).stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Orders,
                linkTo(methodOn(OrderController.class).AllOrders()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }  
    }

   @RequestMapping(value = "/NewOrder/{CustomerID}/{ProductCode}/{Quantity}/{Comments}/{OrderDate}/{RequiredDate}/{ChequeNo}", method = RequestMethod.GET)
   public EntityModel<Order> CreateOrder(@PathVariable(name = "CustomerID") int CustomerID, @PathVariable(name = "ProductCode") int ProductCode, @PathVariable(name = "Quantity") int Quantity, @PathVariable(name = "Comments") String Comments, @PathVariable(name = "OrderDate") Date OrderDate, @PathVariable(name = "RequiredDate") Date RequiredDate, @PathVariable(name = "ChequeNo") String ChequeNo) throws DataAccessException {
      try{ 
        Order order =  Order.CreateNewOrder(CustomerID, ProductCode, Quantity, Comments, OrderDate, RequiredDate, ChequeNo);
         return Assembler.toModel(order);      
      }
      catch(DataAccessException ex){
         throw new OrderNotFoundException(ex);
      }    
   }   
}
