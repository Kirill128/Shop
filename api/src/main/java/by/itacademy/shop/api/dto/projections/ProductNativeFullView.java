package by.itacademy.shop.api.dto.projections;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Provider;

import java.util.Map;

public interface ProductNativeFullView {
    Long getId();
    Map<String,String> getShortDescription();
    Double getPrice();
    String getBarcode();
    Integer getQuantityInStorage();
    Category getCategory();
    Photo getPhoto();
    Map<String,String> getAttributes();
    Provider getProvider();

}

