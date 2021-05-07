package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.admin.ProviderDto;
import by.itacademy.shop.entities.Provider;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ProviderMapper {
    public ProviderDto mapProviderToProviderDto(Provider source){
        return ProviderDto.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public Provider mapProviderDtoToProvider(ProviderDto source){
        return Provider.builder().
                id(source.getId()).
                name(source.getName()).
                build();
    }
    public List<ProviderDto> mapProvidersToProviderDtos(List<Provider> providers){
        return providers.stream().map(ProviderMapper::mapProviderToProviderDto).collect(Collectors.toList());
    }
    public List<Provider> mapProviderDtosToProviders(List<ProviderDto> providers){
        return providers.stream().map(ProviderMapper::mapProviderDtoToProvider).collect(Collectors.toList());
    }
}
