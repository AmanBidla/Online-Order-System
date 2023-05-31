/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.CustomerDao;
import com.project.OnlineOrderSystem.DAO.PaymentDao;
import com.project.OnlineOrderSystem.Exception.PaymentExceptions.PaymentNotFoundException;
import com.project.OnlineOrderSystem.Model.Customer;
import com.project.OnlineOrderSystem.Model.Payment;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.ModelAssemblers.CustomerAssembler.CustomerModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.PaymentAssembler.PaymentModelAssembler;
import java.util.ArrayList;
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
public class PaymentController {

    private final PaymentDao Payment;
    private final CustomerDao Customer;
    private final CustomerModelAssembler CustomerAssembler;
    private final PaymentModelAssembler Assembler;   
    private Logger logger = Logger.getLogger(ProductController.class.toString());
  
    @Autowired
    public PaymentController(PaymentDao Payment, CustomerDao Customer, PaymentModelAssembler Assembler, CustomerModelAssembler CustomerAssembler) {
        this.Customer = Customer;
        this.Payment = Payment;
        this.Assembler = Assembler;
        this.CustomerAssembler = CustomerAssembler;
    }  
    
    @RequestMapping(value = "/FindPaymentByCustomerID/{CustomerID}", method = RequestMethod.GET)
    public CollectionModel<EntityModel> FindPaymentByCustomerID(@PathVariable int CustomerID) throws DataAccessException{
      try{
        List<EntityModel> ModelAssembler = new ArrayList<>();
        Customer customer = Customer.FindCustomerByID(CustomerID);
        ModelAssembler.add(CustomerAssembler.toModel(customer));
        List<Payment> Payments =  Payment.FindPaymentDetailsByCustID(CustomerID);
        
        for(Payment value : Payments){
            ModelAssembler.add(Assembler.toModel(value));
        }

        return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(PaymentController.class).FindPaymentByCustomerID(CustomerID)).withSelfRel());
           
      }
      catch(DataAccessException ex){
         throw new PaymentNotFoundException(ex);
      }    
    }

    @RequestMapping(value = "/FindPaymentDetailsByChequeNo/{ChequeNo}", method = RequestMethod.GET)
    public EntityModel<Payment> FindPaymentDetailsByChequeNo(@PathVariable String ChequeNo) throws DataAccessException{
      try{ 
        Payment payment =  Payment.FindPayementDetailsByChequeNo(ChequeNo);
         return Assembler.toModel(payment);      
      }
      catch(DataAccessException ex){
         throw new PaymentNotFoundException(ex);
      }    
    }
    
   @RequestMapping(value = "/AllPayments", method = RequestMethod.GET)
   public CollectionModel<EntityModel<Payment>> AllPaymentDetails() throws DataAccessException{
       try{
         List<EntityModel<Payment>> Payments = Payment.AllPaymentDetails().stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Payments,
                linkTo(methodOn(PaymentController.class).AllPaymentDetails()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new PaymentNotFoundException(ex);
      }       
   }        
}
