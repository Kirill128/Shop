package by.itacademy.shop.api.dto;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.ProductOrder;
import by.itacademy.shop.entities.Provider;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private Map<String,String> attributes;

    private Map<String,String> shortDescription;

    private Double price;

    private Integer quantityInStorage;

    private Category category;

    private Photo photo;

    private Provider provider;

    private List<ProductOrder> productOrders;

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", attributes=" + attributes +
                ", shortDescription=" + shortDescription +
                ", price=" + price +
                ", quantityInStorage=" + quantityInStorage +
                ", category=" + category +
                ", photo=" + photo +
                ", provider=" + provider +
                ", productOrders=" + productOrders +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public Map<String, String> getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(Map<String, String> shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantityInStorage() {
        return quantityInStorage;
    }

    public void setQuantityInStorage(Integer quantityInStorage) {
        this.quantityInStorage = quantityInStorage;
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }
}


