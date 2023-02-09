package eu.jev.springmvcrest.services;

import eu.jev.springmvcrest.api.v1.mapper.CategoryMapper;
import eu.jev.springmvcrest.api.v1.model.CategoryDTO;
import eu.jev.springmvcrest.domain.Category;
import eu.jev.springmvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";

    CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository categoryRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, Mappers.getMapper(CategoryMapper.class));
    }

    @Test
    void getAllCategories() {
        Category category = Category.builder().build();
        List<Category> categories = Arrays.asList(category, category, category);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();

        assertEquals(3, categoryDTOs.size());
    }

    @Test
    void getCategory() {
        Category category = Category.builder().id(ID).name(NAME).build();

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}