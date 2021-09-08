package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProductDao;
import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.Provider;
import by.itacademy.shop.util.NativeQueryStringBuilder;
import by.itacademy.shop.utilenum.SortDirection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

    public ProductDaoImpl() {
        super(Product.class);
    }

    @Override
    public SimplePage<Product> getProductsPageByCriteria(ProductSearchCriteria productSearchCriteria) {
        NativeQueryStringBuilder nativeQueryStringBuilder = new NativeQueryStringBuilder()
                .select(new String[]{"p.id as pid", "p.short_description as pshordescr",
                        "p.barcode as pbar", "p.quantity_in_storage as pquant",
                        "p.price as pprice",
                        "p.category_id as pcatid", "p.photo_id as pphotoid", "p.provider_id as pprovid"})
                .select(new String[]{"c.title as ctitle"})
                .select("pr.\"name\" as prname")
                .select("ph.url as phurl")
                .from("product as p")
                .join("LEFT", "category as c", "p.category_id=c.id")
                .join("LEFT", "provider as pr", "p.provider_id=pr.id")
                .join("LEFT", "photo as ph", "p.photo_id=ph.id")
                .limitOffset(productSearchCriteria.getPageSize(),
                        productSearchCriteria.getPageSize() * (productSearchCriteria.getPageNum()-1));

        this.dynamicPartOfQuery(nativeQueryStringBuilder, productSearchCriteria);

        Query query = entityManager.createNativeQuery(nativeQueryStringBuilder.build());
        return new SimplePage<>(castListObjectsToListProducts(query.getResultList()));

    }

    private void dynamicPartOfQuery(NativeQueryStringBuilder nativeQueryStringBuilder,
                                    ProductSearchCriteria productSearchCriteria) {
        if (productSearchCriteria.getSortBy() != null) {
            nativeQueryStringBuilder.orderBy("p." + productSearchCriteria.getSortBy());
        }
        String name = this.createNameForFind(productSearchCriteria.getPartsOfName());
        if (!name.equals("%")) {
            nativeQueryStringBuilder.where(
                    String.format("LOWER(p.short_description ->> '%s') LIKE LOWER('%s')",
                            productSearchCriteria.getLang().value, name)
            );
        }
        if (productSearchCriteria.getCategoryId() != null) {
            nativeQueryStringBuilder.and().where(String.format("p.category_id=%d",productSearchCriteria.getCategoryId()));
        }
        if (productSearchCriteria.getSortBy() != null) {
            StringBuilder orderByStr = new StringBuilder(productSearchCriteria.getSortBy());
            if (productSearchCriteria.getSortDirection() != null) {
                orderByStr.append(
                        (productSearchCriteria.getSortDirection().equals(SortDirection.INCREASE)) ? " ASC " : " DESC "
                );
            }
            nativeQueryStringBuilder.orderBy(orderByStr.toString());
        }
    }

    private List<Product> castListObjectsToListProducts(List<Object> source) {
        List<Product> result = new ArrayList<>(source.size());
        Iterator<Object> itr = source.iterator();
        while (itr.hasNext()) {
            try {
                result.add(this.buildProduct((Object[]) itr.next()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Product buildProduct(Object[] obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Category category = null;
        Provider provider = null;
        Photo photo = null;
        if (obj[5] != null) {
            category = Category.builder()
                    .id(Long.valueOf((Integer) obj[5]))
                    .title((objectMapper.readValue(objectMapper.writeValueAsString(obj[8]),
                            new TypeReference<HashMap<String, String>>() {})))
                    .build();
        }
        if (obj[6] != null) {
            photo = Photo.builder()
                    .id(Long.valueOf((Integer) obj[6]))
                    .url((String) obj[9])
                    .build();
        }
        if (obj[7] != null) {
            provider = Provider.builder()
                    .id(Long.valueOf((Integer) obj[7]))
                    .name((String) obj[10])
                    .build();
        }
        return Product.builder()
                .id(Long.valueOf((Integer) obj[0]))
                .shortDescription((objectMapper.readValue(objectMapper.writeValueAsString(obj[1]),
                        new TypeReference<HashMap<String, String>>() {})))
                .barcode((String) obj[2])
                .quantityInStorage((Integer) obj[3])
                .price(((BigDecimal) obj[4]).doubleValue())
                .category(category)
                .photo(photo)
                .provider(provider)
                .build();
    }

    private String createNameForFind(List<String> source) {
        StringBuilder searchName = new StringBuilder("%");
        if (source == null) {
            return searchName.toString();
        }
        source.forEach( e -> searchName.append(e).append("%"));
        return searchName.toString();
    }
}
