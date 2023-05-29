/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.CustomerDao;
import com.project.OnlineOrderSystem.Exception.CustomerExceptions.CustomerNotFoundException;
import com.project.OnlineOrderSystem.Model.Customer;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.ModelAssemblers.CustomerAssembler.CustomerModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.EmployeeAssembler.EmployeeModelAssembler;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Aman Bidla
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/")
public class CustomerController {
    
    private final CustomerDao Customer;
    private final CustomerModelAssembler CustomerAssembler;
    private final EmployeeModelAssembler EmployeeAssembler;
    private Logger logger = Logger.getLogger(CustomerController.class.toString());

    
    @Autowired
    public CustomerController(CustomerDao Customer, CustomerModelAssembler CustomerAssembler, EmployeeModelAssembler EmployeeAssembler) {
        this.Customer = Customer;
        this.CustomerAssembler = CustomerAssembler;
        this.EmployeeAssembler = EmployeeAssembler;
    }
    
    @RequestMapping(value = "/Customer/{id}", method = RequestMethod.GET)
    public EntityModel<Customer> FindCustomerByID(@PathVariable int id){
      try{ 
        Customer customer =  Customer.FindCustomerByID(id);
             // .orElseThrow(()-> new CustomerNotFoundException(id));
         return CustomerAssembler.toModel(customer);      
      }
      catch(DataAccessException ex){
         throw new CustomerNotFoundException(ex);
      }
    }
    
    @RequestMapping(value = "/AllCustomers", method = RequestMethod.GET)
    public CollectionModel<EntityModel<Customer>> ListOfCustomers() throws DataAccessException {
       /* List<EntityModel<Customer>> Customers = Customer.ListOfCustomers().stream()
              .map(customer -> EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).FindCustomerByID(customer.getID())).withSelfRel(),      
                linkTo(methodOn(CustomerController.class).ListOfCustomers()).withRel("Customers")))
                .collect(Collectors.toList()); */
      try{
         List<EntityModel<Customer>> Customers = Customer.ListOfCustomers().stream()
              .map(CustomerAssembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Customers,
                linkTo(methodOn(CustomerController.class).ListOfCustomers()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new CustomerNotFoundException(ex);
      }
    } 
    
    
    @RequestMapping(value = "/NewCustomer", method = RequestMethod.POST)
    public ResponseEntity<?> CreateNewCustomer(@RequestBody Customer newCustomer) {
        
        Map<Integer, Employee> CustomerRepDetails = new HashMap<>();       
        int CustomerID=0;
        
        try{        
            CustomerRepDetails = Customer.CreateNewCustomer(newCustomer);
                    
             for(int value : CustomerRepDetails.keySet()){
                CustomerID = value;
              }
             Customer customer = Customer.FindCustomerByID(CustomerID);
             Employee employee = CustomerRepDetails.get(CustomerID);
            if(CustomerID!=0){
                EntityModel<Customer> customerEntityModel = CustomerAssembler.toModel(customer);
                EntityModel<Employee> employeeEntityModel = EmployeeAssembler.toModel(employee);
                //String test="{"+"id"+": "+"1016,"+"\nphone: "+ "1-306-351-1784"+"}";
               // ResponseEntity.ok(customer.getRequiredLink(IanaLinkRelations.SELF).toUri());
                return ResponseEntity
                        .created(customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri().relativize(employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()))
                        .body(customerEntityModel);
            }
            else {
                return ResponseEntity.ok("Failed to Create Customer");
            }
        }
        catch(DataAccessException ex){
          throw new CustomerNotFoundException(ex);
        }
    }

}
