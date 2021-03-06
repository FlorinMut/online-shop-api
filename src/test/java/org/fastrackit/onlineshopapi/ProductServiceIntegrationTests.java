package org.fastrackit.onlineshopapi;

import org.fastrackit.onlineshopapi.domain.Product;
import org.fastrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fastrackit.onlineshopapi.service.ProductService;
import org.fastrackit.onlineshopapi.transfer.product.CreateProductRequest;
import org.fastrackit.onlineshopapi.transfer.product.GetProductsRequest;
import org.fastrackit.onlineshopapi.transfer.product.UpdateProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testCreateProduct_whenValidRequest_thenReturnProductWithId() {
        Product product = createProduct();

        assertThat(product, notNullValue());
        assertThat(product.getId(), greaterThan(0L));

    }

    private Product createProduct() {
        CreateProductRequest request = new CreateProductRequest();
        request.setName("Laptop");
        request.setPrice(10);
        request.setQuantity(3);
        request.setSku("fda332d232");

        return productService.createProduct(request);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProduct_whenProductNotFound_thenThrowResourceNotFoundException() throws ResourceNotFoundException {

        productService.getProduct(0);

    }

    @Test
    public void testGetProduct_whenExistingId_thenReturnMatchingProduct() throws ResourceNotFoundException {
        Product product = createProduct();

        Product retrievedProduct = productService.getProduct(product.getId());

        assertThat(retrievedProduct.getId(), is(product.getId()));
        assertThat(retrievedProduct.getName(), is(product.getName()));

    }


    @Test
    public void testUpdateProduct_whenValidRequestWithAllFields_thenReturnUpdatedProduct() throws ResourceNotFoundException {
        Product createdProduct = createProduct();

        UpdateProductRequest request = new UpdateProductRequest();
        request.setName(createdProduct.getName() + "_Edited");
        request.setPrice(createdProduct.getPrice() + 10);
        request.setQuantity(createdProduct.getQuantity() + 10);
        request.setSku(createdProduct.getSku() + "_Edited");

        Product updatedProduct = productService.updateProduct(createdProduct.getId(), request);

        assertThat(updatedProduct.getName(), is(request.getName()));
        assertThat(updatedProduct.getName(), not(is(createdProduct.getName())));

        assertThat(updatedProduct.getPrice(), is(request.getPrice()));
        assertThat(updatedProduct.getQuantity(), is(request.getQuantity()));
        assertThat(updatedProduct.getSku(), is(request.getSku()));

        assertThat(updatedProduct.getId(), is(createdProduct.getId()));


    }

    // todo: Implement negative tests for update and tests for update with some fields only

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteProduct_whenExistingId_thenProductIsDeleted() throws ResourceNotFoundException {
        Product createdProduct = createProduct();

        productService.deleteProduct(createdProduct.getId());

        productService.getProduct(createdProduct.getId());


    }
    @Test
    public void testGetProducts_whenAllCriteriaProvidedAndMatching_thenReturnFilteredResults(){
        Product createdProduct = createProduct();

        GetProductsRequest request = new GetProductsRequest();
        request.setPartialName("top");
        request.setMinimumPrice(3.5);
        request.setMaximumPrice(10.1);
        request.setMinimumQuantity(1);


        //acesta e un test facut in graba, assert-ul este minim, poate fi completat
        Page<Product> products =
                productService.getProducts(request, PageRequest.of(0, 10));


        assertThat(products.getTotalElements(), greaterThanOrEqualTo(1L));

        //todo: for each product from the response assert that all criteria are matched


    }

}
