package br.com.via.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.via.model.Product;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private static final String AUTHOR_MAIL = "edsonbelem@gmail.com";
	private static final String API_TITLE = "Swagger API";
	private static final String API_DESCRIPTION = "Via Varejo";
	private static final String API_BASE_PACKAGE = "br.com.via.controller";

	@SuppressWarnings("deprecation")
	private ApiInfo getApiInfo() {
		return new ApiInfo(API_TITLE, API_DESCRIPTION, "V3", "", AUTHOR_MAIL, "", "");
	}

	@SuppressWarnings("deprecation")
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(API_BASE_PACKAGE)).paths(PathSelectors.any()).build()
				.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, responseMessageForGET());
	}

	@SuppressWarnings({ "deprecation", "serial" })
	private List<ResponseMessage> responseMessageForGET() {
		return new ArrayList<ResponseMessage>() {
			{
				add(new ResponseMessageBuilder().code(500).message("500 message").responseModel(new ModelRef("Error"))
						.build());
				add(new ResponseMessageBuilder().code(403).message("Forbidden!").build());
			}
		};
	}
}
