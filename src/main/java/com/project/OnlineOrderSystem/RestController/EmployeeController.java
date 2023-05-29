/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.EmployeeDao;
import com.project.OnlineOrderSystem.Exception.EmployeeExceptions.EmployeeNotFoundException;
import com.project.OnlineOrderSystem.Model.Customer;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.ModelAssemblers.EmployeeAssembler.EmployeeModelAssembler;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aman Bidla
 */
@RestController
@RequestMapping(value = "/api/")
public class EmployeeController {
    
    private final EmployeeDao Employee;
    private final EmployeeModelAssembler Assembler;
    private Logger logger = Logger.getLogger(EmployeeController.class.toString());

    
    @Autowired
    public EmployeeController(EmployeeDao Employee, EmployeeModelAssembler Assembler) {
        this.Employee = Employee;
        this.Assembler = Assembler;
    }
    
    @RequestMapping(value = "/Employee/{id}", method = RequestMethod.GET)
    public EntityModel<Employee> FindEmployeeByID(@PathVariable int id){
        try{ 
        Employee employee =  Employee.FindEmployeeByID(id);
         return Assembler.toModel(employee);      
      }
      catch(DataAccessException ex){
         throw new EmployeeNotFoundException(ex);
      }
    }
    
    @RequestMapping(value = "/AllEmployees", method = RequestMethod.GET)
    public CollectionModel<EntityModel<Employee>> ListOfEmployees() throws DataAccessException {
    
       try{
         List<EntityModel<Employee>> Employees = Employee.AllEmployees().stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Employees,
                linkTo(methodOn(EmployeeController.class).ListOfEmployees()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new EmployeeNotFoundException(ex);
      }
    } 
    
    
    @RequestMapping(value = "/NewEmployee", method = RequestMethod.POST)
    public ResponseEntity<?> CreateNewEmployee(@RequestBody Employee newEmployee) {
        
        int EmployeeID=newEmployee.getID();
        
        try{
            
            EmployeeID = Employee.CreateNewEmployee(newEmployee);
            
            if(EmployeeID!=0){
                EntityModel<Employee> employee = Assembler.toModel(Employee.FindEmployeeByID(EmployeeID));
                
            
                return ResponseEntity
                        .created(employee.getRequiredLink(IanaLinkRelations.SELF).toUri())
                        .body(employee);
            }
            else {
                return ResponseEntity.ok("Failed to Create Employee");
            }
        }
        catch(DataAccessException ex){
          throw new EmployeeNotFoundException(ex);
        }
    }
}
