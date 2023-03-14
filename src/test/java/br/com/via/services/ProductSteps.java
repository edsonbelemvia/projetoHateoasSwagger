package br.com.via.services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.time.LocalDateTime;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.via.model.Product;
import br.com.via.repository.ProductRepository;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class ProductSteps {

    ResultActions resultActions;
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;
    String body;
	
     private ResultActions result;
     private Product product;
   
   
	  @Autowired
	  private WebApplicationContext context;

	  @Before(value = "")
	  public void setup() {
	    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	  }

	  @Dado("Dado as seguintes informacoes do Product")
	  public void dadoAsSeguintesInformacoesDoProduct() throws Exception{
	       product = new Product();
	       product.setName("product_four");
	       product.setPrice(200.);
	       product.setImageUrl("product4.jpg");
           product.setInventory(100);
           product.setRegisterDate(LocalDateTime.now());
           product.setProductId(4);
           
           body = objectMapper.writeValueAsString(product);
	  }

	  @Quando("enviar uma solicitacao POST para url de gravacao")
	  public void enviarUmaSolicitacaoPOSTParaURLDeGravacao() throws Exception {
 	        HttpHeaders httpHeaders = new HttpHeaders();
	        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
	                post(URI.create("http://localhost:9000/products"))
	                .content(body)
	                .headers(httpHeaders)
	                .contentType("application/json");
	        resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
	  }

	  @Entao("eu devo receber uma resposta com codigo HTTP Correta")
	  public void euDevoReceberUmaRespostaComCodigoHTTPCorreta() throws Exception {
 
		     resultActions.andExpect(status().is(201));
	  }

	  @E("o product criado deve ser retornado na resposta")
	  public void oProductCriadoDeveSerRetornadoNaResposta() throws Exception {
 
 
	    result.andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(product)));
	  }
 }
