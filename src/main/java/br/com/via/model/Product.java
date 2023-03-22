package br.com.via.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ApiModelProperty(name = "id", notes = "id", value = "10", position = 1)
	@JsonProperty(value = "id")
	@Column(name = "id")
	private Integer id;

	@ApiModelProperty(name = "name", notes = "name", value = "book one", position = 2)
	@JsonProperty(value = "name")
	@Column(name = "name", length = 250, columnDefinition = "varchar(250) not null default 'PENDING'")
	private String name;

	@ApiModelProperty(name = "description", notes = "description", value = "description book", position = 3)
	@Column(name = "description", length = 250)
	private String description;

	@ApiModelProperty(name = "price", notes = "price", value = "100.50", position = 4)
	@Column(name = "price")
	private Double price;

	@ApiModelProperty(notes = "imageUrl", example = "foto_product.jpg", required = true, position = 5)
	@JsonProperty("image_url")
	@Column(name = "image_Url")
	private String imageUrl;

	@ApiModelProperty(notes = "inventory", example = "100", required = true, position = 6)
	@JsonProperty("inventory")
	@Column(name = "inventory")
	private Integer inventory;

	@ApiModelProperty(notes = "registerDate", example = "2023-01-24T12:10:00", required = false, position = 7)
	@JsonProperty("register_date")
	@Column(name = "register_date")
	private LocalDateTime registerDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "link_id")
	private List<Link> links = new ArrayList<Link>();

	public Product() {

	}

	public void init() {
		registerDate = LocalDateTime.now();
	}

	public Product(Integer id, String name, String description, Double price, String imageUrl, Integer inventory) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
		this.inventory = inventory;
		this.init();
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", imageUrl=" + imageUrl + ", inventory=" + inventory + ", registerDate=" + registerDate + "]";
	}

	public String getName() {
		return name;
	}

	public Product setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Product setDescription(String description) {
		this.description = description;
		return this;
	}

	public Double getPrice() {
		return price;
	}

	public Product setPrice(Double price) {
		this.price = price;
		return this;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Product setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public Integer getInventory() {
		return inventory;
	}

	public Product setInventory(Integer inventory) {
		this.inventory = inventory;
		return this;
	}

	public LocalDateTime getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDateTime registerDate) {
		this.registerDate = registerDate;
	}

	public void addLink(Link link) {
		getLinks().add(link);
	}

	public void addAllLink(List<Link> links) {
		getLinks().addAll(links);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}
