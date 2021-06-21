package by.senla.daomicroservice.specification;

import by.senla.daomicroservice.dto.ProductSearchCriteria;
import by.senla.microservices.constants.Lang;
import by.senla.daomicroservice.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    public static Specification<Product> partsOfNameLikeCriteria(ProductSearchCriteria productSearchCriteria){
        if(productSearchCriteria.getPartsOfName()==null || productSearchCriteria.getPartsOfName().isEmpty()){
            return null;
        }
        return (root,cq,cb)-> cb.like(cb.function("jsonb_extract_path_text",
                String.class, root.<String>get("shortDescription"),
                cb.literal(productSearchCriteria.getLang().value)),
                createNameForFind(productSearchCriteria.getPartsOfName())
        );
    }

    public static Specification<Product> categoryIdIs(ProductSearchCriteria productSearchCriteria){
        if(productSearchCriteria.getCategoryId()==null || productSearchCriteria.getCategoryId()<=0){
           return null;
        }
        return (root,cq,cb)-> cb.equal(root.get("category").get("id"),productSearchCriteria.getCategoryId());
    }

    private static String createNameForFindFullJsonString(List<String> source, Lang lang) {
        return String.format("{\"%s\":\"%s\"}",lang.value, createNameForFind(source));
    }
    private static String createNameForFind(List<String> source) {
        StringBuilder searchName = new StringBuilder("%");
        if (source == null) {
            return searchName.toString();
        }
        source.forEach( e -> searchName.append(e).append("%"));
        return searchName.toString();
    }
}
