package by.itacademy.shop.api.dto;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.ProductOrder;
import by.itacademy.shop.entities.Provider;

import java.util.List;
import java.util.Map;

public class UserProductDto {

    private String shortDescription;

    private Double price;

    private Category category;

    private Photo photo;

    public UserProductDto(String shortDescription, Double price, Category category, Photo photo) {
        this.shortDescription = shortDescription;
        this.price = price;
        this.category = category;
        this.photo = photo;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
