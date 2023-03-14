package br.com.via.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

import br.com.via.model.Product;
import br.com.via.service.ProductServiceFinal;
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
	 private   ProductServiceFinal productService;
 
    
   
	@Operation(summary = "Grava novo produto")
	@ApiResponses({ 
		@ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }), })
	@PostMapping("/products")
	public ResponseEntity<?>  createProducts(@RequestBody Product   product) {
		try {
		  return ResponseEntity.status(200).body(productService.createProduct(product));
		} catch (Exception ex) {
			return ResponseEntity.status(500).body("Error de Servidor Interno");
		}
	}
	
	
	@Operation(summary = "Lista todos os produtos")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }) })
   @GetMapping("/products")
	public ResponseEntity<CollectionModel<EntityModel<Product>>> getProducts() {
          //productService.getProducts();
        return ResponseEntity.ok( productService.getProducts());
    }

	
	 
	@Operation(summary = "Busca produto pelo id")
 	@ApiResponses({
 			@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
 			@ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
 			@ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }) })
 	@GetMapping("/products/{id}")
 	public ResponseEntity<?> getProduct(@PathVariable("id") Integer id) {
 		try { 			
      
 		  return ResponseEntity.status(200).body(productService.getProductById(id));
 
 		} catch (Exception ex) {
 			return ResponseEntity.status(406).body("Error ENcontrado na Busca ");
 		}
 	}

 
	@Operation(summary = "Deleta um produto pelo codigo")
	@ApiResponses({
			@ApiResponse(responseCode = "204", content = {@Content(schema = @Schema(), mediaType = "application/HAL+json") }),
			@ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }) })
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> deleteProducts(@PathVariable("id") Integer id) {
		try {
        productService.deleteProduct(id);
	   return ResponseEntity.status(204).build();
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).body("erro na exclusao do codigo");
		}
	}	

	@Operation(summary = "Altera um Produto pelo codigo")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }) })
	@PutMapping("products/{id}")
	public ResponseEntity<?> updateProducts(@PathVariable("id") Integer id, 
											@RequestBody Product product) {
		try {
			  EntityModel<Product> updatedProduct = productService.updateProduct(id, product);
		      return ResponseEntity.ok(updatedProduct);
			 
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).body("erro na alteração do id");
		}
	}	
	
	@Operation(summary = "Altera o nome pelo id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }) })
	@PatchMapping("/products/{id}/{name}")
	public ResponseEntity<?> updateNome(@PathVariable("id")  Integer id, @PathVariable("name") String name){
	try {	
		 EntityModel<Product> product  =  productService.patchProductIdName(id, name);
        if (product == null) {
            throw new Exception("Nao Encontrado");
        }
        return ResponseEntity.status(200).body(product);

    } catch (Exception ex) {
        return ResponseEntity.status(500).body("Error " + ex.getMessage());
    }
}
 
	@ApiOperation(value = "Retorna uma lista paginada de todos os produtos")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200",  content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") }),
	        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema(implementation = ErrorResponse.class), mediaType = "application/json") })
	})
	@GetMapping("/productPaginate")
	public ResponseEntity<Page<Product>> getAllProdutos(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "20") int size) {
	    Page<Product> produtos = productService.findAll(page, size);
	    return new ResponseEntity<>(produtos, HttpStatus.OK);
	}
	
}
