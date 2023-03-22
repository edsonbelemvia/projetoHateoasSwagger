package br.com.via.entitytest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import br.com.via.interfaces.IProduct;
import br.com.via.interfaces.ProductDto;

public class ProductTest {

	private static IProduct product = null;

	static {
		product = new ProductDto(1, "product one", "product description", 100., "productone.jpg", 100,
				LocalDateTime.now());
	}

	@Order(1)
	@DisplayName("Id is True")
	@Test
	public void test1_IdIstrue() {
		assertTrue(product.isValidateId());
	}

	@Order(2)
	@DisplayName("Name is True")
	@Test
	public void test2_NameIstrue() {
		assertTrue(product.isValidateName());
	}

	@Order(3)
	@DisplayName("Price is True")
	@Test
	public void test3_PriceIstrue() {
		assertTrue(product.isValidatePrice());
	}

	@Order(4)
	@DisplayName("Description is True")
	@Test
	public void test4_DescriptionIstrue() {
		assertTrue(product.isValidateDescrition());
	}

	@Order(5)
	@DisplayName("ImageUrl is True")
	@Test
	public void test5_ImageUrlIstrue() {
		assertTrue(product.isValidateImageUrl());
	}

	@Order(6)
	@DisplayName("Inventory is True")
	@Test
	public void test6_Istrue() {
		assertTrue(product.isValidateInventory());
	}

	@Order(7)
	@DisplayName("Inventory is True")
	@Test
	public void test7_Istrue() {
		assertTrue(product.isValidateRegisterDate());
	}
}
