package co.com.bancolombia.service.resolveEnigma.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.resolveEnigma.model.client.ClientJsonApiBodyResponseSuccess;

@Component
public class GetStepOneClientRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("direct:get-step-one")
		.routeId("getStepOne")
			.setHeader(Exchange.HTTP_METHOD, constant("POST"))
	        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .to("freemarker:templates/GetStepOneClientTemplate.ftl")
        	//.log("Request microservice step one ${body}")
        
    	.hystrix()
	    .hystrixConfiguration().executionTimeoutInMilliseconds(2000).end()
        .to("http4://localhost:8090/EnigmaSteps/getStep")
        	.convertBodyTo(String.class)
        	.unmarshal().json(JsonLibrary.Jackson, ClientJsonApiBodyResponseSuccess.class)
        	//.log("Java Response microservice step one ${body}")
        .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				ClientJsonApiBodyResponseSuccess stepOneResponse = (ClientJsonApiBodyResponseSuccess) exchange.getIn().getBody();
				if (stepOneResponse.getData().get(0).getStep().equalsIgnoreCase("1")) 
				{
					exchange.setProperty("Step1", stepOneResponse.getData().get(0).getStepDescription());
				}
				else 
				{
					exchange.setProperty("Error", "0001");
					exchange.setProperty("descError", "Step one is not valid");
				}				
			}        	
        })
		.endHystrix()
	    .onFallback()
	    .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.setProperty("Error", "0002");
				exchange.setProperty("descError", "Error consulting the step one");
				
			}        	
        })
	    .end()
        .log("Response code ${exchangeProperty[Error]}")
        .log("Response description ${exchangeProperty[descError]}")	
        .log("Response description ${exchangeProperty[Step1]}");
	
	}
}
