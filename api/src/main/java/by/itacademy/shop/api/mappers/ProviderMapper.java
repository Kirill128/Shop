package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.admin.AdminProviderDto;
import by.itacademy.shop.entities.Provider;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProviderMapper {
    public AdminProviderDto mapProviderToProviderDto(Provider source){
        if(source==null)return null;
        return AdminProviderDto.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public Provider mapProviderDtoToProvider(AdminProviderDto source){
        if(source==null)return null;
        return Provider.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public List<AdminProviderDto> mapProvidersToProviderDtos(List<Provider> providers){
        if(providers==null)return new ArrayList<>();
        return providers.stream().map(ProviderMapper::mapProviderToProviderDto).collect(Collectors.toList());
    }
    public List<Provider> mapProviderDtosToProviders(List<AdminProviderDto> providers){
        if(providers==null)return new ArrayList<>();
        return providers.stream().map(ProviderMapper::mapProviderDtoToProvider).collect(Collectors.toList());
    }
}
