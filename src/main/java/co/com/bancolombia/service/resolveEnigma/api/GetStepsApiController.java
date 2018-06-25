package co.com.bancolombia.service.resolveEnigma.api;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyRequest;
import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyResponseSuccess;
import io.swagger.annotations.ApiParam;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-22T10:36:16.361-05:00")

@Controller
public class GetStepsApiController implements GetStepsApi {

    private static final Logger log = LoggerFactory.getLogger(GetStepsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private Object response;
    
	@EndpointInject(uri="direct:resolve-enigma")
    private FluentProducerTemplate producerTemplateResolveEnigma;

    @org.springframework.beans.factory.annotation.Autowired
    public GetStepsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<?> getStepsPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
       try {
    	   
    	   response=producerTemplateResolveEnigma.withBody(body).request(); 
    	   Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	   System.out.println(timestamp + "Respuesta del Servicio");
    	   return new ResponseEntity<JsonApiBodyResponseSuccess>((JsonApiBodyResponseSuccess)response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<JsonApiBodyResponseErrors>((JsonApiBodyResponseErrors)response, HttpStatus.FAILED_DEPENDENCY);
        }
}

}
