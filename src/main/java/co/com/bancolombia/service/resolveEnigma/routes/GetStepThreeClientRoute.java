package co.com.bancolombia.service.resolveEnigma.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import co.com.bancolombia.service.resolveEnigma.model.client.ClientJsonApiBodyResponseSuccess;

@Component
public class GetStepThreeClientRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		from("direct:get-step-three")
		.routeId("getStepThree")
			.setHeader(Exchange.HTTP_METHOD, constant("POST"))
	        .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
        .to("freemarker:templates/GetStepThreeClientTemplate.ftl")
        	//.log("Request microservice step Three ${body}")
        
    	.hystrix()
	    .hystrixConfiguration().executionTimeoutInMilliseconds(2000).end()
        .to("http4://localhost:8090/EnigmaSteps/getStep")
        	.convertBodyTo(String.class)
        	.unmarshal().json(JsonLibrary.Jackson, ClientJsonApiBodyResponseSuccess.class)
        	//.log("Java Response microservice step Three ${body}")
        .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				ClientJsonApiBodyResponseSuccess stepThreeResponse = (ClientJsonApiBodyResponseSuccess) exchange.getIn().getBody();
				if (stepThreeResponse.getData().get(0).getStep().equalsIgnoreCase("3")) 
				{
					exchange.setProperty("Step3", stepThreeResponse.getData().get(0).getStepDescription());
				}
				else 
				{
					exchange.setProperty("Error", "0003");
					exchange.setProperty("descError", "Step Three is not valid");
				}				
			}        	
        })
		.endHystrix()
	    .onFallback()
	    .process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				exchange.setProperty("Error", "0004");
				exchange.setProperty("descError", "Error consulting the step Three");
				
			}        	
        })
	    .end()
        .log("Response code ${exchangeProperty[Error]}")
        .log("Response description ${exchangeProperty[descError]}")	
        .log("Response description ${exchangeProperty[Step3]}");	
	
	}
}
