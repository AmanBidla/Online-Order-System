/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Exception.CustomerExceptions;

/**
 *
 * @author Aman Bidla
 */
public class CustomerNotFoundException extends RuntimeException {
  
  public CustomerNotFoundException(Exception ex) {
    super(ex);
  }
}
