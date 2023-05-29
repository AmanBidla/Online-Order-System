/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.ModelAssemblers.OfficeAssembler;

import com.project.OnlineOrderSystem.ModelAssemblers.EmployeeAssembler.*;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Office;
import com.project.OnlineOrderSystem.RestController.EmployeeController;
import com.project.OnlineOrderSystem.RestController.OfficeController;
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
public class OfficeModelAssembler implements RepresentationModelAssembler<Office, EntityModel<Office>>{
  
  @Override
  public EntityModel<Office> toModel(Office office) {

    return EntityModel.of(office,
        linkTo(methodOn(OfficeController.class).FindOfficeByCode(office.getCode())).withSelfRel(),
        linkTo(methodOn(OfficeController.class).FindAllOffices()).withRel("Offices"));
  } 
  

}