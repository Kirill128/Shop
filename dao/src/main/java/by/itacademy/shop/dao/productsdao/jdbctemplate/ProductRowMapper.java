package by.itacademy.shop.dao.productsdao.jdbctemplate;

import by.itacademy.shop.entities.Category;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import by.itacademy.shop.entities.Provider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ProductRowMapper implements RowMapper<Product> {
    @SneakyThrows
    @Override
    public Product mapRow(ResultSet set, int i) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        Category category = null;
        Provider provider = null;
        Photo photo = null;
        category = Category.builder()
                .id(set.getLong("pcatid"))
                .title((objectMapper.readValue(set.getString("ctitle"),
                        new TypeReference<HashMap<String, String>>() {
                        })))
                .build();
        photo = Photo.builder()
                .id(set.getLong("pphotoid"))
                .url(set.getString("phurl"))
                .build();
        provider = Provider.builder()
                .id(set.getLong("pprovid"))
                .name(set.getString("prname"))
                .build();
        return Product.builder()
                .id(set.getLong("pid"))
                .shortDescription((objectMapper.readValue(set.getString("pshordescr"),
                        new TypeReference<HashMap<String, String>>() {
                        }))
                )
                .barcode(set.getString("pbar"))
                .quantityInStorage(set.getInt("pquant"))
                .price(set.getDouble("pprice"))
                .attributes((objectMapper.readValue(set.getString("pattr"),
                        new TypeReference<HashMap<String, String>>() {
                        })))
                .category(category)
                .photo(photo)
                .provider(provider)
                .build();
    }
}
