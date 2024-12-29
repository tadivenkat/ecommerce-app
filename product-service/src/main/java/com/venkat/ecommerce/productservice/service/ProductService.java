package com.venkat.ecommerce.productservice.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.venkat.ecommerce.productservice.dataobject.ProductDO;
import com.venkat.ecommerce.productservice.dto.ProductDTO;
import com.venkat.ecommerce.productservice.mapper.ProductMapper;
import com.venkat.ecommerce.productservice.repository.ProductRepository;

@Service
public class ProductService {

    private static Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductDTO product = productMapper.map(productRepository.save(productMapper.map(productDTO)));
        logger.info("Product created with id:{}", productDTO.id());
        return product;
    }

    public ProductDTO getProduct(String id) {
        return productMapper.map(productRepository.findById(id).get());
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
        logger.info("Product deleted with id:{}", id);
    }

    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Optional<ProductDO> productDOOptional = productRepository.findById(id);
        if (!productDOOptional.isPresent()) {
            throw new IllegalArgumentException("Product not found for id:" + id);
        }
        ProductDO currentProductDO = productDOOptional.get();
        if (StringUtils.isNotEmpty(productDTO.description())) {
            currentProductDO.setDescription(productDTO.description());
        }
        if (StringUtils.isNotEmpty(productDTO.name())) {
            currentProductDO.setName(productDTO.name());
        }
        if (productDTO.price() != null) {
            currentProductDO.setPrice(productDTO.price());
        }
        ProductDTO updatedProductDTO = productMapper.map(productRepository.save(currentProductDO));
        logger.info("Product updated with id:{}", productDTO.id());
        return updatedProductDTO;
    }

    public Iterable<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::map).toList();
    }
}
