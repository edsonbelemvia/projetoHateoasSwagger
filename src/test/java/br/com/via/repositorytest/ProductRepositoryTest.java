package br.com.via.repositorytest;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.via.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void test2_TestarasAquantidadedeDadosDoBanco() {
		assertNull(productRepository.findAll());
	}

}
