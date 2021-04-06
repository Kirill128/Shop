package by.itacademy.shop.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.Map;

public class JsonMapConverter implements AttributeConverter<Map<String,String>,String> {
    @Override
    public String convertToDatabaseColumn(Map<String, String> source) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);
        return objectMapper.readValue();
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String source) {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.readValue(source);
    }
}
