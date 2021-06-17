package by.itacademy.shop.api.dto.projections;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;

import java.util.Map;

public interface ProductNativeGuestView {
    Long getId();
    Map<String,String> getShortDescription();
    Double getPrice();
    Category getCategory();
    Photo getPhoto();
    Map<String,String> getAttributes();
}
