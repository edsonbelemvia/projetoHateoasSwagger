package br.com.via;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.via.model.Link;
import br.com.via.service.ProductService;

@SpringBootApplication
public class SwaggerProjectApplication implements CommandLineRunner {

	@Autowired
	private ProductService service;
	private ArrayList<Link> lista = new ArrayList<Link>();

	public static void main(String[] args) {
		SpringApplication.run(SwaggerProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

}
