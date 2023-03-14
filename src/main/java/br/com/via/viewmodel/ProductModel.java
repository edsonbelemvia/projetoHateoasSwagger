package br.com.via.viewmodel;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.hateoas.RepresentationModel;

import br.com.via.model.Product;

public class ProductModel extends RepresentationModel<ProductModel> {

	private Integer id;
	private String name;
	private Double price;
	private String description;
	private String imageUrl;
	private Integer inventory;
	private LocalDateTime registerDate;
	private Integer productId;

	public ProductModel() {
		// TODO Auto-generated constructor stub
	}

	public ProductModel(Integer id, String name,  String description, Double price,
			          String imageUrl, Integer inventory,
			          LocalDateTime registerDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUrl = imageUrl;
		this.inventory = inventory;
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "DtoProduct [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", imageUrl=" + imageUrl + ", inventory=" + inventory + ", registerDate=" + registerDate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Product toProduct(Product  model) {
		Product product = new Product();
		product.setId(model.getId());
		product.setName(model.getName());
		product.setInventory(model.getInventory());
		product.setPrice(model.getPrice());
		product.setRegisterDate(LocalDateTime.now());
		product.setImageUrl(model.getImageUrl());
		product.setProductId(model.getId());
		return product;
	}
 

}
