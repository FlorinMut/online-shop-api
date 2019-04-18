package org.fastrackit.onlineshopapi.web;

import org.fastrackit.onlineshopapi.domain.Product;
import org.fastrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fastrackit.onlineshopapi.service.ProductService;
import org.fastrackit.onlineshopapi.transfer.product.CreateProductRequest;
import org.fastrackit.onlineshopapi.transfer.product.GetProductsRequest;
import org.fastrackit.onlineshopapi.transfer.product.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws ResourceNotFoundException {
        Product response = productService.getProduct(id);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid CreateProductRequest request){
        Product response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable ("id") long id,
                                        @RequestBody @Valid UpdateProductRequest request) throws ResourceNotFoundException {

        productService.updateProduct(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable ("id") long id) {
        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(
            @Valid GetProductsRequest request, Pageable pageable){

        Page<Product> response = productService.getProducts(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
