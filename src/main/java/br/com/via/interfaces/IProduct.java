package br.com.via.interfaces;

import java.time.LocalDateTime;

public interface IProduct {

	public Integer getId();

	public void setId(Integer id);

	public String getName();

	public void setName(String name);

	public void setPrice(Double price);

	public Double getPrice();

	public String getDescription();

	public void setDescription(String description);

	public void setInventory(Integer inventory);

	public Integer getInventory();

	public void setImageUrl(String imageUrl);

	public String getImageUrl();

	public void setRegisterDate(LocalDateTime registerDate);

	public LocalDateTime getRegisterDate();

	public Boolean isValidateId();

	public Boolean isValidateName();

	public Boolean isValidateDescrition();

	public Boolean isValidateInventory();

	public Boolean isValidatePrice();

	public Boolean isValidateImageUrl();

	public Boolean isValidateRegisterDate();

}
