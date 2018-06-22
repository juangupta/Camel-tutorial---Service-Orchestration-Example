package co.com.bancolombia.service.resolveEnigma.model;

import java.util.Objects;
import co.com.bancolombia.service.resolveEnigma.model.ErrorDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JsonApiBodyResponseErrors
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-22T10:36:16.361-05:00")

public class JsonApiBodyResponseErrors   {
  @JsonProperty("errors")
  @Valid
  private List<ErrorDetail> errors = new ArrayList<ErrorDetail>();

  public JsonApiBodyResponseErrors errors(List<ErrorDetail> errors) {
    this.errors = errors;
    return this;
  }

  public JsonApiBodyResponseErrors addErrorsItem(ErrorDetail errorsItem) {
    this.errors.add(errorsItem);
    return this;
  }

  /**
   * Get errors
   * @return errors
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<ErrorDetail> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorDetail> errors) {
    this.errors = errors;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonApiBodyResponseErrors jsonApiBodyResponseErrors = (JsonApiBodyResponseErrors) o;
    return Objects.equals(this.errors, jsonApiBodyResponseErrors.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JsonApiBodyResponseErrors {\n");
    
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

