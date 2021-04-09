package by.itacademy.shop.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.util.Map;

public class JsonMapConverter implements AttributeConverter<Map<String,String>,String> {
    @Override
    public String convertToDatabaseColumn(Map<String, String> source) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResult="{}";
        try {
            jsonResult = objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String source) {
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,String> mapResult=null;
        try {
            mapResult=objectMapper.readValue(source, new TypeReference<Map<String,String>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return mapResult;
    }
}
