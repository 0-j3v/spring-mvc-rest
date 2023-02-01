package eu.jev.springmvcrest.api.v1.mapper;

import eu.jev.springmvcrest.api.v1.model.CategoryDTO;
import eu.jev.springmvcrest.domain.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    List<CategoryDTO> categoryListToCategoryDTOList(List<Category> categoryList);

    CategoryDTO categoryToCategoryDTO(Category category);
}
