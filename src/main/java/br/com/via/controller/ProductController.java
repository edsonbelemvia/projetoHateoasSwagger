package br.com.via.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.via.exception.ProductResponseException;
import br.com.via.model.Product;
import br.com.via.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(value = "ProductController")
@ResponseBody
@RestController
@RequestMapping
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(summary = "Save new product")
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = {
					@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }), })
	@PostMapping("products")
	public ResponseEntity<?> createProducts(@RequestBody Product product) {
		try {
			return ResponseEntity.status(201).body(productService.createProduct(product));
		} catch (Exception ex) {
			ex.printStackTrace();
			ProductResponseException error = new ProductResponseException("500", "Internal Server Error");
			return ResponseEntity.status(500).body(error);
		}
	}

	@Operation(summary = "Find all products")
	@ApiResponses({ @ApiResponse(responseCode = "200", content = {
			@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }) })
	@GetMapping("products")
	public ResponseEntity<?> getProducts() {
		return ResponseEntity.ok(productService.getProducts());
	}

	@Operation(summary = "Find product by Id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = {
					@Content(schema = @Schema(implementation = ProductResponseException.class), mediaType = "application/json") }) })
	@GetMapping("products/{id}")
	public ResponseEntity<?> getProduct(@PathVariable("id") Integer id) {
		try {
			return ResponseEntity.status(200).body(productService.getProductById(id));
		} catch (Exception ex) {
			ProductResponseException error = new ProductResponseException("404", "Product Not Found");
			return ResponseEntity.status(404).body(error);
		}
	}

	@Operation(summary = "Delete product by Id")
	@ApiResponses({
			@ApiResponse(responseCode = "204", content = {
					@Content(schema = @Schema(), mediaType = "application/HAL+json") }),
			@ApiResponse(responseCode = "404", content = {
					@Content(schema = @Schema(implementation = ProductResponseException.class), mediaType = "application/json") }) })
	@DeleteMapping("products/{id}")
	public ResponseEntity<?> deleteProducts(@PathVariable("id") Integer id) {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.status(204).build();
		} catch (Exception ex) {
			ProductResponseException error = new ProductResponseException("404", "Product Not Found");
			return ResponseEntity.status(404).body(error);
		}
	}

	@Operation(summary = "Update product by Id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = {
					@Content(schema = @Schema(implementation = ProductResponseException.class), mediaType = "application/json") }) })
	@ApiResponse(responseCode = "500", content = {
			@Content(schema = @Schema(implementation = ProductResponseException.class), mediaType = "application/json") })
	@PutMapping("products/{id}")
	public ResponseEntity<?> updateProducts(@PathVariable("id") Integer id, @RequestBody Product product) {
		try {
			Product updatedProduct = productService.updateProduct(id, product);
			return ResponseEntity.ok(updatedProduct);
		} catch (Exception ex) {
			ProductResponseException error;
			System.out.println(ex.getMessage());
			if (ex.getMessage().equals("No value present")) {
				error = new ProductResponseException("404", "Product Not Found");
				return ResponseEntity.status(404).body(error);
			} else {
				error = new ProductResponseException("500", "Internal Server Error");
				return ResponseEntity.status(500).body(error);
			}
		}
	}

	@Operation(summary = "Update name of product by Id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = {
					@Content(schema = @Schema(implementation = ProductResponseException.class), mediaType = "application/json") }) })
			@ApiResponse(responseCode = "500", content = {
					@Content(schema = @Schema(implementation = ProductResponseException.class), mediaType = "application/json") })
	@PatchMapping("/products/{id}/{name}")
	public ResponseEntity<?> updateNome(@PathVariable("id") Integer id, @PathVariable("name") String name) {
		try {
			Product product = productService.patchProductIdName(id, name);
			return ResponseEntity.status(200).body(product);
		} catch (Exception ex) {
			ex.printStackTrace();
			ProductResponseException error;
			if (ex.getMessage().equals("No value present")) {
				error = new ProductResponseException("404", "Product Not Found");
				return ResponseEntity.status(404).body(error);
			} else {
				error = new ProductResponseException("500", " Internal Server Error");
				return ResponseEntity.status(500).body(error);
			}
		}
	}

	@ApiOperation(value = "Returns a paginated list of all products")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = {
					@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }) })
	@GetMapping("productsPaginate")
	public ResponseEntity<Page<Product>> getAllProdutos(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size) {
		Page<Product> produtos = productService.findAll(page, size);
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

}
