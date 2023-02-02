package eu.jev.springmvcrest.api.v1.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Response with a list of customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDTO {

    @ArraySchema(schema = @Schema(description = "List of customers"))
    List<CustomerDTO> customers;
}
