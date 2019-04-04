package org.fastrackit.onlineshopapi.persistence;

import org.fastrackit.onlineshopapi.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

// Long is wrapper for primitive long
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {




}
