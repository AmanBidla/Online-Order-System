/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.ModelAssemblers.CustomerAssembler;

import com.project.OnlineOrderSystem.Model.Customer;
import com.project.OnlineOrderSystem.RestController.CustomerController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
/**
 *
 * @author Aman Bidla
 */

@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>>{
  
  @Override
  public EntityModel<Customer> toModel(Customer customer) {

    return EntityModel.of(customer,
        linkTo(methodOn(CustomerController.class).FindCustomerByID(customer.getID())).withSelfRel(),
        linkTo(methodOn(CustomerController.class).ListOfCustomers()).withRel("Customers"));
  } 
}
