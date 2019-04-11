package org.fastrackit.onlineshopapi.persistence;

import org.fastrackit.onlineshopapi.domain.Customer;
import org.fastrackit.onlineshopapi.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {




}