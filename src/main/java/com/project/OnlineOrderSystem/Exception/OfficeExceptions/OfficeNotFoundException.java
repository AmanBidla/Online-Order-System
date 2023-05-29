/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Exception.OfficeExceptions;

import com.project.OnlineOrderSystem.Exception.OfficeExceptions.*;


/**
 *
 * @author Aman Bidla
 */
public class OfficeNotFoundException extends RuntimeException {
  
  public OfficeNotFoundException(Exception ex) {
      super(ex);
  } 
}
