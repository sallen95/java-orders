package com.lambdaschool.javaorders.controllers;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrdersControllers
{
    @Autowired
    private OrderServices orderServices;

    // http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long orderid)
    {
        Order order = orderServices.findByOrderId(orderid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // POST /orders/order
    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addOrder(@Validated @RequestBody Order newOrder)
    {
        newOrder.setOrdnum(0);
        newOrder = orderServices.save(newOrder);

        HttpHeaders responseOrderHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{ordnum}")
            .buildAndExpand(newOrder.getOrdnum())
            .toUri();
        responseOrderHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(newOrder, responseOrderHeaders, HttpStatus.CREATED);
    }

    // PUT /orders/order/{ordnum}
    @PutMapping(value = "/order/{ordnum}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> replaceOrderById(@PathVariable long ordnum, @Validated @RequestBody Order replaceOrder)
    {
        replaceOrder.setOrdnum(ordnum);
        Order o = orderServices.save(replaceOrder);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    // DELETE /orders/order/{ordnum}
    @DeleteMapping(value = "/order/{ordnum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordnum)
    {
        orderServices.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
