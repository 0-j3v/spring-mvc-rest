package eu.jev.springmvcrest.controllers.v1;

import eu.jev.springmvcrest.api.v1.model.CategoryDTO;
import eu.jev.springmvcrest.api.v1.model.CategoryListDTO;
import eu.jev.springmvcrest.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Categories Controller", description = "Services for Categories")
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "List all the categories")
    @GetMapping
    public CategoryListDTO getAllCategories(){
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @Operation(summary = "Get a category by name")
    @GetMapping("/{name}")
    public CategoryDTO getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }
}
