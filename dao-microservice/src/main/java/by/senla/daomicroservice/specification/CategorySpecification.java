package by.senla.daomicroservice.specification;

import by.senla.daomicroservice.entities.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategorySpecification {
    public static Specification<Category> parentCategoriesSpecification(){
        return (root,cq,cb)->cb.isNull(root.get("parentCategory"));
    }
    public static Specification<Category> subCategoriesSpecification(){
        return (root,cq,cb)->cb.isNotNull(root.get("parentCategory"));
    }

}
