package eu.jev.springmvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Vendor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTO {

    @Schema(required = true, description = "Vendor's name", example = "Vendor Ltd.")
    private String name;
    @Schema(description = "Vendor's URL", example = "/api/v1/vendors/1")
    @JsonProperty("vendor_url")
    private String vendorUrl;
}
