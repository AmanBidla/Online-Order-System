/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Exception.PaymentExceptions;

import com.project.OnlineOrderSystem.Exception.EmployeeExceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Aman Bidla
 */
@ControllerAdvice
public class PaymentNotFoundAdvice {
    
  @ResponseBody
  @ExceptionHandler(PaymentNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String PaymentNotFoundHandler(PaymentNotFoundException ex) {
    return ex.getMessage();
  }

}
