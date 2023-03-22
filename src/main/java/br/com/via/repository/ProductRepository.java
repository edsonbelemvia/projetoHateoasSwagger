package br.com.via.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.via.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Page<Product> findAll(Pageable pageable);

	@Query(value = "select MAX(id) from product order by id desc limit 1", nativeQuery = true)
	public Integer findLastID();

}
