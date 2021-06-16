package by.itacademy.shop.specification;

import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.utilenum.Lang;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {
    public static Specification<Product> partsOfNameLike(ProductSearchCriteria productSearchCriteria){
        if(productSearchCriteria.getPartsOfName()==null || productSearchCriteria.getPartsOfName().isEmpty()){
           return null;
        }
        return (root,cq,cb)->  cb.like(root.get("shortDescription"),
                createNameForFind(productSearchCriteria.getPartsOfName(),productSearchCriteria.getLang()));
    }
    public static Specification<Product> categoryIdIs(ProductSearchCriteria productSearchCriteria){
        if(productSearchCriteria.getCategoryId()==null || productSearchCriteria.getCategoryId()<=0){
           return null;
        }
        return (root,cq,cb)-> cb.equal(root.get("category").get("id"),productSearchCriteria.getCategoryId());
    }


    private static String createNameForFind(List<String> source, Lang lang) {
        StringBuilder searchName = new StringBuilder("%");
        if (source == null) {
            return searchName.toString();
        }
        source.forEach( e -> searchName.append(e).append("%"));
        return String.format("{\"%s\":\"%s\"}",lang.value,searchName.toString());
    }
}
