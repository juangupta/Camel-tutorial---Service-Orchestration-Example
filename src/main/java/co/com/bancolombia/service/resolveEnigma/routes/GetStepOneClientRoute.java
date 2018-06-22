package co.com.bancolombia.service.resolveEnigma.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.resolveEnigma.model.client.ClientJsonApiBodyResponseSuccess;

@Component
public class GetStepOneClientRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("direct:get-step-one")
		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .to("freemarker:templates/GetStepOneClientTemplate.ftl")
        .log("Request microservice step one ${body}")
        .to("http4://localhost:8090/EnigmaSteps/getStep")
        .convertBodyTo(String.class)
		.log("String Response microservice step one ${body}")
		.unmarshal().json(JsonLibrary.Jackson, ClientJsonApiBodyResponseSuccess.class)
		.log("Java Response microservice step one ${body}");
		
	}
}
