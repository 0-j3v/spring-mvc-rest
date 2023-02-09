package eu.jev.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @Schema(required = true, description = "Firstname", example = "Freddy")
    private String firstname;
    @Schema(required = true, description = "Lastname", example = "Meyers")
    private String lastname;
    @Schema(description = "Customer's URL", example = "/api/v1/customers/1")
    @JsonProperty("customer_url")
    private String customerUrl;
}
