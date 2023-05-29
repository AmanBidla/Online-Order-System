/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Exception.OrderProductExceptions;

import com.project.OnlineOrderSystem.Exception.OrderExceptions.*;


/**
 *
 * @author Aman Bidla
 */
public class OrderProductNotFoundException extends RuntimeException {
  
  public OrderProductNotFoundException(Exception ex) {
      super(ex);
  } 
}
