package br.com.via.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.via.model.Link;
import br.com.via.model.Product;
import br.com.via.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	private ArrayList<Link> lista = new ArrayList<Link>();

	public ArrayList<Link> getListagem(int id) {

		Link link1 = new Link();
		link1.setRel("self");
		link1.setHref("http://localhost:9000/products/" + id);
		link1.setType("application/json");
		link1.setAction("GET");
		lista.add(link1);

		Link link3 = new Link();
		link3.setRel("get");
		link3.setHref("http://localhost:9000/products/" + id);
		link3.setType("application/json");
		link3.setAction("GET");
		lista.add(link3);
		Link link4 = new Link();
		link4.setRel("put");
		link4.setHref("http://localhost:9000/products/" + id);
		link4.setType("application/json");
		link4.setAction("PUT");
		lista.add(link4);
		Link link5 = new Link();
		link5.setRel("delete");
		link5.setHref("http://localhost:9000/products/" + id);
		link5.setType("application/json");
		link5.setAction("DELETE");
		lista.add(link5);
		return lista;
	}

	public void limpar() {
		lista.clear();
	}

	public Product createProduct(Product product) {
		Link link0 = new Link();
		int id = 0;
		if (!productRepository.findAll().isEmpty()) {
			List<Integer> numeros = productRepository.findAll().stream().map((p) -> p.getId())
					.sorted((p1, p2) -> p2.compareTo(p1)).collect(Collectors.toList());
			System.out.println(numeros);
			id = numeros.get(0);
			id++;
			product.setId(id);
			link0.setRel("post");
			link0.setHref("http://localhost:9000/products/" + id);
			link0.setType("application/json");
			link0.setAction("POST");
			link0.setRel("post");
			link0.setType("application/json");
			link0.setAction("POST");
		} else {
			id = 0;
			id++;
			link0.setRel("post");
			link0.setHref("http://localhost:9000/products/" + id);
			link0.setType("application/json");
			link0.setAction("POST");
			link0.setRel("post");
			link0.setType("application/json");
			link0.setAction("POST");
			product.setId(id);
		}
		lista.add(link0);

		product.setLinks(getListagem(id));
		Product savedProduct = productRepository.save(product);
		if (savedProduct.getName() == null) {
			throw new IllegalArgumentException("product not created !");
		}
		limpar();
		return savedProduct;
	}

	public List<Product> getProducts() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	public Product getProductById(Integer id) {
		Product resposta = productRepository.findById(id).get();

		if (resposta == null) {
			throw new IllegalArgumentException("404");
		}

		Product product = productRepository.findById(id).get();

		return product;
	}

	public Product updateProduct(Integer id, Product product) {
		Product resposta = productRepository.findById(id).get();

		if (resposta == null) {
			throw new IllegalArgumentException("404");
		}

		Product updatedProduct = productRepository.findById(id).map(p -> {
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			p.setDescription(product.getDescription());
			p.setImageUrl(product.getImageUrl());
			p.setInventory(product.getInventory());
			p.setRegisterDate(product.getRegisterDate());
			p.setLinks(p.getLinks());
			return productRepository.save(p);
		}).orElseThrow(() -> new IllegalArgumentException("500"));
		return updatedProduct;
	}

	public void deleteProduct(Integer id) {
		Product resposta = productRepository.findById(id).get();

		if (resposta == null) {
			throw new IllegalArgumentException("404");
		}

		productRepository.deleteById(id);
	}

	public Product patchProductIdName(Integer id, String name) {
		Product resposta = productRepository.findById(id).get();

		if (resposta == null) {
			throw new IllegalArgumentException("404");
		}

		Product updatedProduct = productRepository.findById(id).map(p -> {
			p.setName(name);
			p.setPrice(p.getPrice());
			p.setDescription(p.getDescription());
			p.setImageUrl(p.getImageUrl());
			p.setInventory(p.getInventory());
			p.setRegisterDate(p.getRegisterDate());
			p.setLinks(p.getLinks());
			return productRepository.save(p);
		}).orElseThrow(() -> new IllegalArgumentException("Alteracao Invalida !!!"));
		return updatedProduct;
	}

	public Page<Product> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable);
	}

}
