/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.ModelAssemblers.OrderAssembler;

import com.project.OnlineOrderSystem.ModelAssemblers.OfficeAssembler.*;
import com.project.OnlineOrderSystem.ModelAssemblers.EmployeeAssembler.*;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Office;
import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.RestController.EmployeeController;
import com.project.OnlineOrderSystem.RestController.OfficeController;
import com.project.OnlineOrderSystem.RestController.OrderController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

/**
 *
 * @author Aman Bidla
 */
@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>>{
  
  @Override
  public EntityModel<Order> toModel(Order order) {

    return EntityModel.of(order,
        linkTo(methodOn(OrderController.class).FindOrderByID(order.getID())).withSelfRel(),
        linkTo(methodOn(OrderController.class).AllOrders()).withRel("Orders"));
  } 
  

}