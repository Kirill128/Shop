package by.itacademy.shop.api.dto.forall;

import java.util.List;
import java.util.Map;

import by.itacademy.shop.utilenum.SortDirection;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchCriteria {
    private int pageSize;
    private int pageNum;

    private SortDirection sortDirection;
    private String sortBy;

    private Map<String,String> attributesCriteria;
    private String partOfName;
    private List<String> partsOfName;
    private Long categoryId;

}
