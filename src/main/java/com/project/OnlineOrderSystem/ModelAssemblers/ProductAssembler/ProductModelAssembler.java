/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.ModelAssemblers.ProductAssembler;

import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.RestController.ProductController;
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
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>>{
  
  @Override
  public EntityModel<Product> toModel(Product product) {

    return EntityModel.of(product,
        linkTo(methodOn(ProductController.class).FindProductByID(product.getCode())).withSelfRel(),
        linkTo(methodOn(ProductController.class).AllProducts()).withRel("Products"));
  } 
  

}