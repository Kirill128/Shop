package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.admin.AdminProviderDto;

import java.util.List;

public interface ProviderService {
    AdminProviderDto createProvider(AdminProviderDto user);
    AdminProviderDto find(long id);
    void update(AdminProviderDto user);
    void delete(long id);

    List<AdminProviderDto> getAllProviders();
}
