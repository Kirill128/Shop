package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.ProviderDto;
import by.itacademy.shop.api.dto.UserDto;

import java.util.List;

public interface ProviderService {
    ProviderDto createProvider(ProviderDto user);
    ProviderDto find(long id);
    void update(ProviderDto user);
    void delete(long id);

    List<ProviderDto> getAllProviders();
}
