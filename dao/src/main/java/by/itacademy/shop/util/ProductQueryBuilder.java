package by.itacademy.shop.util;

import by.itacademy.shop.api.dto.forall.ProductSearchCriteria;
import by.itacademy.shop.api.dto.forall.SimplePage;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.util.NativeQueryStringBuilder;
import by.itacademy.shop.utilenum.SortDirection;

import java.util.List;


public class ProductQueryBuilder  {
    public static String makeStringProductSearchJpqlQuery(ProductSearchCriteria productSearchCriteria){
        return new String();
    }

    public static String makeStringProductSearchNativeQuery(ProductSearchCriteria productSearchCriteria) {
        NativeQueryStringBuilder nativeQueryStringBuilder = new NativeQueryStringBuilder()
                .select(new String[]{"p.id as pid", "p.short_description as pshordescr",
                        "p.barcode as pbar", "p.quantity_in_storage as pquant",
                        "p.price as pprice", "p.\"attributes\" as pattr",
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

        dynamicPartOfQuery(nativeQueryStringBuilder, productSearchCriteria);

        return nativeQueryStringBuilder.build();

    }


    private static void dynamicPartOfQuery(NativeQueryStringBuilder nativeQueryStringBuilder,
                                    ProductSearchCriteria productSearchCriteria) {
        if (productSearchCriteria.getSortBy() != null) {
            nativeQueryStringBuilder.orderBy("p." + productSearchCriteria.getSortBy());
        }
        String name = createNameForFind(productSearchCriteria.getPartsOfName());
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



    private  static String createNameForFind(List<String> source) {
        StringBuilder searchName = new StringBuilder("%");
        if (source == null) {
            return searchName.toString();
        }
        source.forEach( e -> searchName.append(e).append("%"));
        return searchName.toString();
    }
}
