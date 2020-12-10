package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;

import java.util.List;

public interface CustomerServices
{
    Customer findByCustomerId(long id);
    List<Customer> findCustomers();
    List<Customer> findByNameLike(String substring);

    Customer save(Customer customer); // POST

    void delete(long id);

    Customer update(Customer customer, long id);

    void deleteAll();
}
