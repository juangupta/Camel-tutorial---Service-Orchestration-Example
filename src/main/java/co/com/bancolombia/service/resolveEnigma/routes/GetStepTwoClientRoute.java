package co.com.bancolombia.service.resolveEnigma.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.resolveEnigma.model.client.ClientJsonApiBodyResponseSuccess;

@Component
public class GetStepTwoClientRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("direct:get-step-two")
		.routeId("getStepTwo")
			.setHeader(Exchange.HTTP_METHOD, constant("POST"))
	        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .to("freemarker:templates/GetStepTwoClientTemplate.ftl")
        	//.log("Request microservice step two ${body}")
        
    	.hystrix()
	    .hystrixConfiguration().executionTimeoutInMilliseconds(2000).end()
        .to("http4://localhost:8090/EnigmaSteps/getStep")
        	.convertBodyTo(String.class)
        	.unmarshal().json(JsonLibrary.Jackson, ClientJsonApiBodyResponseSuccess.class)
        	//.log("Java Response microservice step two ${body}")
        .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				ClientJsonApiBodyResponseSuccess stepTwoResponse = (ClientJsonApiBodyResponseSuccess) exchange.getIn().getBody();
				if (stepTwoResponse.getData().get(0).getStep().equalsIgnoreCase("2")) 
				{
					exchange.setProperty("Step2", stepTwoResponse.getData().get(0).getStepDescription());
				}
				else 
				{
					exchange.setProperty("Error", "0005");
					exchange.setProperty("descError", "Step Two is not valid");
				}				
			}        	
        })
		.endHystrix()
	    .onFallback()
	    .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.setProperty("Error", "0006");
				exchange.setProperty("descError", "Error consulting the step Two");
				
			}        	
        })
	    .end()
        .log("Response code ${exchangeProperty[Error]}")
        .log("Response description ${exchangeProperty[descError]}")	
        .log("Response description ${exchangeProperty[Step2]}");	
	
	}
}
