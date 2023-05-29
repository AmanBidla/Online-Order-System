/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.OfficeDao;
import com.project.OnlineOrderSystem.Exception.OfficeExceptions.OfficeNotFoundException;
import com.project.OnlineOrderSystem.ModelAssemblers.OfficeAssembler.OfficeModelAssembler;
import com.project.OnlineOrderSystem.Model.Employee;
import com.project.OnlineOrderSystem.Model.Office;
import com.project.OnlineOrderSystem.ModelAssemblers.EmployeeAssembler.EmployeeModelAssembler;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import java.util.logging.Level;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Aman Bidla
 */
@RestController
@RequestMapping(value = "/api/")
public class OfficeController {
    
    private final OfficeDao Office;
    private final OfficeModelAssembler Assembler;
    private final EmployeeModelAssembler EmployeeAssembler;
    private Logger logger = Logger.getLogger(OfficeController.class.toString());

    
    @Autowired
    public OfficeController(OfficeDao Office, OfficeModelAssembler Assembler, EmployeeModelAssembler EmployeeAssembler) {
        this.Office = Office;
        this.Assembler = Assembler;
        this.EmployeeAssembler = EmployeeAssembler;
    }
     
    @RequestMapping(value = "/OfficeEmployee/{code}", method = RequestMethod.GET)
    public CollectionModel<EntityModel> FindEmployeebyOfficeCode(@PathVariable int code) throws DataAccessException {
      try{       
        Map<Employee, Office> EmployeesOffice =  Office.FindEmployeebyOfficeCode(code);
        List<EntityModel> ModelAssembler = new ArrayList<>();
        int count=0;
        for(Map.Entry<Employee, Office> value : EmployeesOffice.entrySet()){ 
           ModelAssembler.add(EmployeeAssembler.toModel(value.getKey())); 
           if(count<1){
            ModelAssembler.add(Assembler.toModel(value.getValue()));
           }
           count++;
        }
        return CollectionModel.of(ModelAssembler,
                linkTo(methodOn(OfficeController.class).FindEmployeebyOfficeCode(code)).withSelfRel());
      }
      catch(DataAccessException ex){
         throw new OfficeNotFoundException(ex);
      }
    }
    
    @RequestMapping(value = "/AllOffices", method = RequestMethod.GET)
    public CollectionModel<EntityModel<Office>> FindAllOffices() throws DataAccessException {
    
       try{
         List<EntityModel<Office>> Offices = Office.FindAllOffices().stream()
              .map(Assembler::toModel)
              .collect(Collectors.toList());

        return CollectionModel.of(Offices,
                linkTo(methodOn(OfficeController.class).FindAllOffices()).withSelfRel());
        }
       catch(DataAccessException ex){
         throw new OfficeNotFoundException(ex);
      }
    }
    
    @RequestMapping(value = "/Office/{code}", method = RequestMethod.GET)
    public EntityModel<Office> FindOfficeByCode(@PathVariable int code){
      try{ 
        Office office =  Office.FindOfficeByCode(code);
         return Assembler.toModel(office);      
      }
      catch(DataAccessException ex){
         throw new OfficeNotFoundException(ex);
      }
    }

    @RequestMapping(value = "/NewOffice", method = RequestMethod.POST)
    public ResponseEntity<?> CreateNewOffice(@RequestBody Office newOffice) {
        
        int OfficeCode=0;
        
        try{
            
            OfficeCode = Office.AddNewOffice(newOffice);
            
            if(OfficeCode!=0){
                EntityModel<Office> office = Assembler.toModel(Office.FindOfficeByCode(OfficeCode));
                
            
                return ResponseEntity
                        .created(office.getRequiredLink(IanaLinkRelations.SELF).toUri())
                        .body(office);
            }
            else {
                return ResponseEntity.ok("Failed to Add Office");
            }
        }
        catch(DataAccessException ex){
          throw new OfficeNotFoundException(ex);
        }
    }    
}
