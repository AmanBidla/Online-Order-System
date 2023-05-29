/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Exception.EmployeeExceptions;


/**
 *
 * @author Aman Bidla
 */
public class EmployeeNotFoundException extends RuntimeException {
  
  public EmployeeNotFoundException(Exception ex) {
      super(ex);
  } 
}
