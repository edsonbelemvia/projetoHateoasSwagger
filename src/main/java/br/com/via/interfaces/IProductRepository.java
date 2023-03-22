package br.com.via.interfaces;

import java.util.List;
import java.util.Optional;

import br.com.via.model.Product;

public interface IProductRepository {

	public Optional<Product> save(Product product);

	public List<Product> findAll();

	public Optional<Product> findById(Integer id);

	public Integer deleteProductById(Integer id);

	public Optional<Product> update(Product product);

}
