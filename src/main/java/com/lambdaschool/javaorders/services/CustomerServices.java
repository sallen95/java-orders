package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    Customer findById(long id);
    List<Customer> findCustomers();
    List<Customer> findByNameLike(String substring);
}
