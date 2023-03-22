package br.com.via.interfaces;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProductDto implements IProduct {

	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String imageUrl;
	private Integer inventory;
	private LocalDateTime registerDate;

	public ProductDto(Integer id, String name, String description, Double price, String imageUrl, Integer inventory,
			LocalDateTime registerDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.inventory = inventory;
		this.registerDate = registerDate;
	}

	public ProductDto() {

	}

	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", imageUrl=" + imageUrl + ", inventory=" + inventory + ", registerDate=" + registerDate + "]";
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public Double getPrice() {
		return this.price;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setInventory(Integer inventory) {
		this.inventory = inventory;

	}

	@Override
	public Integer getInventory() {
		return this.inventory;
	}

	@Override
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String getImageUrl() {
		return this.imageUrl;
	}

	@Override
	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	@JsonIgnore
	public Boolean isValidateId() {
		if (getId() > 0L) {
			return true;
		}
		return false;
	}

	@JsonIgnore
	public Boolean isValidateName() {
		Pattern pattern = Pattern.compile("[a-zA-Z ]{3,80}");
		Matcher matcher = pattern.matcher(this.name);
		return matcher.matches();
	}

	@JsonIgnore
	public Boolean isValidateDescrition() {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9, \\.]{3,80}");
		Matcher matcher = pattern.matcher(this.description);
		return matcher.matches();
	}

	@JsonIgnore
	public Boolean isValidateImageUrl() {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9, \\.]{3,80}");
		Matcher matcher = pattern.matcher(this.imageUrl);
		return matcher.matches();
	}

	@JsonIgnore
	public Boolean isValidateInventory() {
		if (this.getInventory() > 0) {
			return true;
		}
		return false;
	}

	@JsonIgnore
	public Boolean isValidateRegisterDate() {
		if (this.registerDate == null) {
			return false;
		}
		return true;
	}

	@JsonIgnore
	public Boolean isValidatePrice() {
		if (this.price > 0.) {
			return true;
		}
		return false;
	}

}
