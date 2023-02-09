package eu.jev.springmvcrest.api.v1.mapper;

import eu.jev.springmvcrest.api.v1.model.CategoryDTO;
import eu.jev.springmvcrest.domain.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    public void categoryListToCategoryDTOList() {
        Category category = Category.builder().id(ID).name(NAME).build();
        List<Category> categoryList = Arrays.asList(category, category);

        List<CategoryDTO> categoryDTOList = categoryMapper.categoryListToCategoryDTOList(categoryList);

        assertEquals(2, categoryDTOList.size());
    }

    @Test
    public void categoryToCategoryDTO() {
        Category category = Category.builder().id(ID).name(NAME).build();

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}