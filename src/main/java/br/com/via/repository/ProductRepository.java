package br.com.via.repository;

 

 

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.via.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public Page<Product> findAll(Pageable pageable);
  
}
