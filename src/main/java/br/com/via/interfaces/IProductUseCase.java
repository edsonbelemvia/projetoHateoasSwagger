package br.com.via.interfaces;

import java.time.LocalDateTime;

public interface IProductUseCase {

	public Integer getId();

	public void setId(Integer id);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public Double getPrice();

	public void setPrice(Double price);

	public String getImageUrl();

	public void setImageUrl(String imageUrl);

	public Integer getInventory();

	public void setInventory(Integer inventory);

	public LocalDateTime getRegisterDate();

	public void setRegisterDate(LocalDateTime registerDate);

	public Integer getCategoryId();

	public void setCategoryId(Integer categoryID);

	public Boolean isId();

	public Boolean isName();

	public Boolean isDescrition();

	public Boolean isImageUrl();

	public Boolean isInventory();

	public Boolean isRegisterDate();

	public Boolean isCategoryId();

	public Boolean isPrice();

}
