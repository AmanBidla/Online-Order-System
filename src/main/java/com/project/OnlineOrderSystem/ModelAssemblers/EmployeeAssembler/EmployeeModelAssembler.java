/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.ModelAssemblers.EmployeeAssembler;

import com.project.OnlineOrderSystem.Model.Employee;
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
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>>{
  
  @Override
  public EntityModel<Employee> toModel(Employee employee) {

    return EntityModel.of(employee,
        linkTo(methodOn(EmployeeController.class).FindEmployeeByID(employee.getID())).withSelfRel(),
        linkTo(methodOn(EmployeeController.class).ListOfEmployees()).withRel("Employees"));
  }
     
}
