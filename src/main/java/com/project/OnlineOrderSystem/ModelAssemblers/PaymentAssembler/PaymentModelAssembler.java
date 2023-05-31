/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.ModelAssemblers.PaymentAssembler;

import com.project.OnlineOrderSystem.ModelAssemblers.OrderProductAssembler.*;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderAssembler.*;
import com.project.OnlineOrderSystem.ModelAssemblers.OfficeAssembler.*;
import com.project.OnlineOrderSystem.ModelAssemblers.EmployeeAssembler.*;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Office;
import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.Model.OrderProduct;
import com.project.OnlineOrderSystem.Model.Payment;
import com.project.OnlineOrderSystem.RestController.EmployeeController;
import com.project.OnlineOrderSystem.RestController.OfficeController;
import com.project.OnlineOrderSystem.RestController.OrderController;
import com.project.OnlineOrderSystem.RestController.OrderProductController;
import com.project.OnlineOrderSystem.RestController.PaymentController;
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
public class PaymentModelAssembler implements RepresentationModelAssembler<Payment, EntityModel<Payment>>{
  
  @Override
  public EntityModel<Payment> toModel(Payment payment) {

    return EntityModel.of(payment,
        linkTo(methodOn(PaymentController.class).FindPaymentDetailsByChequeNo(payment.getChequeNo())).withSelfRel(),
        linkTo(methodOn(PaymentController.class).AllPaymentDetails()).withRel("Payments"));
  } 
}