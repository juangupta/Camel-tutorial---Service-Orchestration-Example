/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package co.com.bancolombia.service.resolveEnigma.api;

import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyRequest;
import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyResponseErrors;
import co.com.bancolombia.service.resolveEnigma.model.JsonApiBodyResponseSuccess;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-22T10:36:16.361-05:00")

@Api(value = "getSteps", description = "the getSteps API")
public interface GetStepsApi {

    @ApiOperation(value = "Get  answer enigma API", nickname = "getStepsPost", notes = "Get  answer enigma API", response = JsonApiBodyResponseSuccess.class, tags={ "resolve-enigma-api-controller", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Inquery step success", response = JsonApiBodyResponseSuccess.class),
        @ApiResponse(code = 424, message = "Invalid Input. please put correct request", response = JsonApiBodyResponseErrors.class) })
    @RequestMapping(value = "/getSteps",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<JsonApiBodyResponseSuccess> getStepsPost(@ApiParam(value = "body" ,required=true )  @Valid @RequestBody JsonApiBodyRequest body);

}
