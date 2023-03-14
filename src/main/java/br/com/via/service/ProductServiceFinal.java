package br.com.via.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.via.model.Product;
import br.com.via.repository.ProductRepository;
@Service
public class ProductServiceFinal {

	     private final ProductRepository productRepository;
	     private final EntityLinks entityLinks;

	    public ProductServiceFinal(ProductRepository productRepository, EntityLinks entityLinks) {
	        this.productRepository = productRepository;
	        this.entityLinks = entityLinks;
	    }

	    public EntityModel<Product> createProduct(Product product) {
	    	product.setProductId(product.getId());
	        Product savedProduct = productRepository.save(product);
	        Link selfLink = entityLinks.linkToItemResource(Product.class, savedProduct.getId()).withSelfRel();
	        return EntityModel.of(savedProduct, selfLink);
	    }

    public CollectionModel<EntityModel<Product>> getProducts() {
        List<Product> productList = productRepository.findAll();
        for(Product p: productList) {
        	p.setProductId(p.getId());
        }
        List<EntityModel<Product>> productModelList = productList.stream()
                .map(product ->EntityModel.of(product, entityLinks.linkToItemResource(Product.class, product.getId()).withSelfRel()))
                .collect(Collectors.toList());
        Link link = entityLinks.linkToCollectionResource(Product.class).withSelfRel();
        return CollectionModel.of(productModelList, link);
    }
    

    public EntityModel<Product> getProductById(Integer id) {
        Product  product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Link selfLink = entityLinks.linkToItemResource(Product.class, product.getId()).withSelfRel();
        return EntityModel.of(product, selfLink);
    }

    
    public EntityModel<Product> updateProduct(Integer id, Product product) {
        Product updatedProduct = productRepository.findById(id).map(p -> {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
            return productRepository.save(p);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Link selfLink = entityLinks.linkToItemResource(Product.class, updatedProduct.getId()).withSelfRel();
        return EntityModel.of(updatedProduct, selfLink);
    }

    
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
    
    public EntityModel<Product> patchProductIdName(Integer id, String name) {
        Product updatedProduct = productRepository.findById(id).map(p -> {
            p.setName(name);
            return productRepository.save(p);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Link selfLink = entityLinks.linkToItemResource(Product.class, updatedProduct.getId()).withSelfRel();
        return EntityModel.of(updatedProduct, selfLink);
    }
    
    public Page<Product> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }
    

}
