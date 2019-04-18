package org.fastrackit.onlineshopapi.persistence;

import org.fastrackit.onlineshopapi.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


// Long is wrapper for primitive long
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findByNameContaining (String partialName, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(
            String partialName, int minimumQuantity, Pageable pageable);

    //cauta dupa interval pret si cantitate
    Page<Product> findByPriceBetweenAndQuantityGreaterThanEqual(
            double minimumPrice, double maximumPrice,
            int minimumQuantity, Pageable pageable);

    Page<Product> findByNameContainingAndPriceBetweenAndQuantityGreaterThanEqual(
            String partialName, double minimumPrice, double maximumPrice,
            int minimumQuantity, Pageable pageable);



    //same result as the method above
//    @Query("SELECT id, name FROM Product product WHERE name LIKE '%?2'")
//    Page<Product> findByPartialName(String partialName, Pageable pageable);


}
