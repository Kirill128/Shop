package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.AdminProviderDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProviderService {
    AdminProviderDto createProvider(AdminProviderDto user) throws JsonProcessingException;
    AdminProviderDto find(long id);
    void update(AdminProviderDto user);
    void delete(long id);

    List<AdminProviderDto> getAllProviders();
}
