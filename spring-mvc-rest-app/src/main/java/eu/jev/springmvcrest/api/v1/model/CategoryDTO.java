package eu.jev.springmvcrest.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Category")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    @Schema(required = true, description = "Category's name", example = "Category")
    private String name;
}
