package co.com.bancolombia.service.resolveEnigma.api;

import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyRequest;
import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyResponseSuccess;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-22T10:36:16.361-05:00")

@Controller
public class GetStepsApiController implements GetStepsApi {

    private static final Logger log = LoggerFactory.getLogger(GetStepsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
	@EndpointInject(uri="direct:get-step-one")
    private FluentProducerTemplate producerTemplateResolveEnigma;

    @org.springframework.beans.factory.annotation.Autowired
    public GetStepsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<JsonApiBodyResponseSuccess> getStepsPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body) {
       try {
    	   producerTemplateResolveEnigma.request(); 
    	   return new ResponseEntity<JsonApiBodyResponseSuccess>(objectMapper.readValue("{  \"data\" : [ {    \"answer\" : \"answer\",    \"header\" : {      \"id\" : \"id\",      \"type\" : \"type\"    }  }, {    \"answer\" : \"answer\",    \"header\" : {      \"id\" : \"id\",      \"type\" : \"type\"    }  } ]}", JsonApiBodyResponseSuccess.class), HttpStatus.NOT_IMPLEMENTED);
        } catch (IOException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<JsonApiBodyResponseSuccess>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}

}
