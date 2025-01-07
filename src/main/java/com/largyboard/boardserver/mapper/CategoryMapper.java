package com.largyboard.boardserver.mapper;

import com.largyboard.boardserver.dto.CategoryDTO;

public interface CategoryMapper {

    public int register(CategoryDTO productDTO);

    public void updateCategory(CategoryDTO categoryDTO);

    public void deleteCategory(int categoryId);
}
