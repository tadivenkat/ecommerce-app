package com.venkat.ecommerce.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import org.springframework.http.HttpStatus;

import com.venkat.ecommerce.productservice.dto.ProductDTO;
import com.venkat.ecommerce.productservice.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO savedResource = productService.createProduct(productDTO);
        // Construct the URI of the newly created resource
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedResource.id())
                .toUri();
        return ResponseEntity.created(location).body(savedResource);    
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok().body(productService.updateProduct(id, productDTO));    
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iterable<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());    
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(productService.getProduct(id));    
    }
}
