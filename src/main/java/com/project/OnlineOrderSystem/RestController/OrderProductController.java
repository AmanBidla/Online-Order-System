/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.RestController;

import com.project.OnlineOrderSystem.DAO.OrderProductDao;
import com.project.OnlineOrderSystem.Exception.OrderProductExceptions.OrderProductNotFoundException;
import com.project.OnlineOrderSystem.Model.OrderProduct;
import com.project.OnlineOrderSystem.Model.Order;
import com.project.OnlineOrderSystem.Model.Product;
import com.project.OnlineOrderSystem.Model.ProductLine;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderProductAssembler.OrderProductModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductAssembler.ProductModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.ProductLineAssembler.ProductLineModelAssembler;
import com.project.OnlineOrderSystem.ModelAssemblers.OrderAssembler.OrderModelAssembler;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import org.springframework.hateoas.AffordanceModel;
import org.springframework.hateoas.CollectionModel;

/**
 *
 * @author Aman Bidla
 */
@RestController
@RequestMapping(value = "/api/")
public class OrderProductController {

    private final OrderProductDao OrderProductDao;
    private final OrderProductModelAssembler Assembler;
    private final OrderModelAssembler OrderAssembler;
    private final ProductModelAssembler ProductAssembler;
    private final ProductLineModelAssembler ProductLineAssembler;

    private Logger logger = Logger.getLogger(OrderProductController.class.toString());

    @Autowired
    public OrderProductController(OrderProductDao OrderProductDao, OrderProductModelAssembler Assembler, OrderModelAssembler OrderAssembler, ProductModelAssembler ProductAssembler, ProductLineModelAssembler ProductLineAssembler) {
        this.OrderProductDao = OrderProductDao;
        this.Assembler = Assembler;
        this.OrderAssembler = OrderAssembler;
        this.ProductAssembler = ProductAssembler;
        this.ProductLineAssembler = ProductLineAssembler;
    }

    @RequestMapping(value = "/OrderProductDetailsByProductCode/{ProductCode}", method = RequestMethod.GET)
    public CollectionModel<CollectionModel<EntityModel>> FindOrderProductDetailsByProductCode(@PathVariable int ProductCode) throws DataAccessException {
        try {
           List<List<EntityModel>> Models = OrderProductDao.FindOrderProductDetailsByProductCode(ProductCode);
           List<CollectionModel<EntityModel>> Collection = new ArrayList<>();
           
           for(List<EntityModel> value : Models){
                Collection.add(CollectionModel.of(value));
           };
                 
           //PagedModel.of(CollectionModel.of(Models,
            //        linkTo(methodOn(OrderProductController.class).FindOrderProductDetailsByProductCode(ProductCode)).withSelfRel())); 
           /*logger.info("OrderProductSet Size: " + OrderProductSet.size());
           logger.info("OrderProductSet Size: " + OrderProductSet);
           List<EntityModel> ModelAssembler = new ArrayList<>();
            
           List<OrderProduct> orderproduct = new ArrayList<>();
            logger.info("orderproductlist Size before: " + orderproduct.size());
            List<Order> order = new ArrayList<>();
            logger.info("orderlist Size before: " + order.size());
            List<Product> product = new ArrayList<>();
            logger.info("productlist Size before: " + product.size());
            List<ProductLine> productline = new ArrayList<>();
            logger.info("productlinelist Size before: " + productline.size());
            
            OrderProduct OrderProduct = new OrderProduct();
            Order Order = new Order(); 
            Product Product = new Product();
            ProductLine ProductLine = new ProductLine();
            List<List> OrderProductSetsList = new ArrayList<>(OrderProductSet);
            logger.info("OrderProductSetsList: " + OrderProductSetsList.toString());
            
            for (List Listvalue : OrderProductSetsList) {                
                if (Listvalue.contains(OrderProduct.getPriceEach())) {
                    orderproduct = Listvalue;
                    logger.info("orderproductlist Size after: " + orderproduct.size());
                    for (OrderProduct value : orderproduct) {
                        ModelAssembler.add(Assembler.toModel(value));
                    }
                } 
                else if (Listvalue.contains(Order.getID())) {
                    order = Listvalue;
                    logger.info("orderlist Size after:" + order.size());
                    for (Order value : order) {
                        ModelAssembler.add(OrderAssembler.toModel(value));
                    }
                }
                else if (Listvalue.contains(Product.getCode())) {
                    product = Listvalue;
                    logger.info("productlist Size after: " + product.size());            
                    for (Product value : product) {
                        ModelAssembler.add(ProductAssembler.toModel(value));
                    }
                } else if (Listvalue.contains(ProductLine.getID())) {
                    productline = Listvalue;
                    logger.info("productlinelist Size before: " + productline.size());
                    for (ProductLine value : productline) {
                        ModelAssembler.add(ProductLineAssembler.toModel(value));
                    }
                }
            }
            logger.info("modelassembler Size: " + ModelAssembler.size());*/
            return CollectionModel.of(Collection,
                    linkTo(methodOn(OrderProductController.class).FindOrderProductDetailsByProductCode(ProductCode)).withSelfRel());
        } catch (DataAccessException ex) {
            throw new OrderProductNotFoundException(ex);
        }
    }

    @RequestMapping(value = "/AllOrderProductDetails", method = RequestMethod.GET)
    public CollectionModel<CollectionModel<EntityModel>> AllOrderProductDetails() throws DataAccessException {
        try {
           List<List<EntityModel>> Models = OrderProductDao.AllOrderProductDetails();
           List<CollectionModel<EntityModel>> Collection = new ArrayList<>();
           
           for(List<EntityModel> value : Models){
                Collection.add(CollectionModel.of(value));
           };
            return CollectionModel.of(Collection,
                    linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());        
        } catch (DataAccessException ex) {
            throw new OrderProductNotFoundException(ex);
        }
    }

    @RequestMapping(value = "/OrderProductDetailsByTextDescription/{TextDescription}", method = RequestMethod.GET)
    public CollectionModel<CollectionModel<EntityModel>> FindOrderProductDetailsByTextDescription(@PathVariable String TextDescription) throws DataAccessException {
        try {
           List<List<EntityModel>> Models = OrderProductDao.FindOrderProductDetailsByTextDescription(TextDescription);
           List<CollectionModel<EntityModel>> Collection = new ArrayList<>();
           
           for(List<EntityModel> value : Models){
                Collection.add(CollectionModel.of(value));
           };
            return CollectionModel.of(Collection,
                    linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());        
        } catch (DataAccessException ex) {
            throw new OrderProductNotFoundException(ex);
        }
    }

    @RequestMapping(value = "/OrderProductDetailsByCustomerID/{CustomerID}", method = RequestMethod.GET)
    public CollectionModel<CollectionModel<EntityModel>> FindOrderProductDetailsByCustomerID(@PathVariable int CustomerID) throws DataAccessException {
        try {
           List<List<EntityModel>> Models = OrderProductDao.FindOrderProductDetailsByCustomerID(CustomerID);
           List<CollectionModel<EntityModel>> Collection = new ArrayList<>();
           
           for(List<EntityModel> value : Models){
                Collection.add(CollectionModel.of(value));
           };
            return CollectionModel.of(Collection,
                    linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());        
        } catch (DataAccessException ex) {
            throw new OrderProductNotFoundException(ex);
        }
    }

    @RequestMapping(value = "/OrderProductDetailsByOrderID/{OrderID}", method = RequestMethod.GET)
    public CollectionModel<CollectionModel<EntityModel>> FindOrderProductDetailsByOrderID(@PathVariable int OrderID) throws DataAccessException {
        try {
           List<List<EntityModel>> Models = OrderProductDao.FindOrderProductDetailsByOrderID(OrderID);
           List<CollectionModel<EntityModel>> Collection = new ArrayList<>();
           
           for(List<EntityModel> value : Models){
                Collection.add(CollectionModel.of(value));
           };
            return CollectionModel.of(Collection,
                    linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());        
        } catch (DataAccessException ex) {
            throw new OrderProductNotFoundException(ex);
        }
    }

    @RequestMapping(value = "/OrderProductDetailsByOrderDate/{OrderDate}", method = RequestMethod.GET)
    public CollectionModel<CollectionModel<EntityModel>> FindOrderProductDetailsByOrderDate(@PathVariable Date OrderDate) throws DataAccessException {
        try {
           List<List<EntityModel>> Models = OrderProductDao.FindOrderProductDetailsByOrderDate(OrderDate);
           List<CollectionModel<EntityModel>> Collection = new ArrayList<>();
           
           for(List<EntityModel> value : Models){
                Collection.add(CollectionModel.of(value));
           };
            return CollectionModel.of(Collection,
                    linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());        
        } catch (DataAccessException ex) {
            throw new OrderProductNotFoundException(ex);
        }
    }

    @RequestMapping(value = "/OrderProductDetailsByPriceEach/{PriceEach}", method = RequestMethod.GET)
    public CollectionModel<CollectionModel<EntityModel>> FindOrderProductDetailsByPriceEach(@PathVariable double PriceEach) throws DataAccessException {
        try {
           List<List<EntityModel>> Models = OrderProductDao.FindOrderProductDetailsByPriceEach(PriceEach);
           List<CollectionModel<EntityModel>> Collection = new ArrayList<>();
           
           for(List<EntityModel> value : Models){
                Collection.add(CollectionModel.of(value));
           };
            return CollectionModel.of(Collection,
                    linkTo(methodOn(OrderProductController.class).AllOrderProductDetails()).withSelfRel());        
        } catch (DataAccessException ex) {
            throw new OrderProductNotFoundException(ex);
        }
    }

/*    public List<EntityModel> OrderProductDetailsPackaging(Set<List> OrderProductSet) {

           logger.info("OrderProductSet Size: " + OrderProductSet.size());
           logger.info("OrderProductSet Size: " + OrderProductSet);
           List<EntityModel> ModelAssembler = new ArrayList<>();

           List<OrderProduct> orderproduct = new ArrayList<>();
            logger.info("orderproductlist Size before: " + orderproduct.size());
            List<Order> order = new ArrayList<>();
            logger.info("orderlist Size before: " + order.size());
            List<Product> product = new ArrayList<>();
            logger.info("productlist Size before: " + product.size());
            List<ProductLine> productline = new ArrayList<>();
            logger.info("productlinelist Size before: " + productline.size());
            
            OrderProduct OrderProduct = new OrderProduct();
            Order Order = new Order(); 
            Product Product = new Product();
            ProductLine ProductLine = new ProductLine();
            List<List> OrderProductSetsList = new ArrayList<>(OrderProductSet);
            logger.info("OrderProductSetsList: " + OrderProductSetsList.toString());
            
            for (List Listvalue : OrderProductSetsList) {                
                if (Listvalue.contains(OrderProduct.getPriceEach())) {
                    orderproduct = Listvalue;
                    logger.info("orderproductlist Size after: " + orderproduct.size());
                    for (OrderProduct value : orderproduct) {
                        ModelAssembler.add(Assembler.toModel(value));
                    }
                } 
                else if (Listvalue.contains(Order.getID())) {
                    order = Listvalue;
                    logger.info("orderlist Size after:" + order.size());
                    for (Order value : order) {
                        ModelAssembler.add(OrderAssembler.toModel(value));
                    }
                }
                else if (Listvalue.contains(Product.getCode())) {
                    product = Listvalue;
                    logger.info("productlist Size after: " + product.size());            
                    for (Product value : product) {
                        ModelAssembler.add(ProductAssembler.toModel(value));
                    }
                } else if (Listvalue.contains(ProductLine.getID())) {
                    productline = Listvalue;
                    logger.info("productlinelist Size before: " + productline.size());
                    for (ProductLine value : productline) {
                        ModelAssembler.add(ProductLineAssembler.toModel(value));
                    }
                }
            }
            logger.info("modelassembler Size: " + ModelAssembler.size());
            return ModelAssembler;
    }*/
}
