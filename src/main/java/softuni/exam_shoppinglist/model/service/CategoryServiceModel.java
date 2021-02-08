package softuni.exam_shoppinglist.model.service;

import softuni.exam_shoppinglist.model.entity.entityenum.CategoryEnum;

public class CategoryServiceModel {

    private Long id;
    private CategoryEnum name;
    private String description;

    public CategoryServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryEnum getName() {
        return name;
    }

    public void setName(CategoryEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
