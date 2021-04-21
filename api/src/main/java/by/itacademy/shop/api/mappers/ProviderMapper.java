package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.ProviderDto;
import by.itacademy.shop.entities.Provider;
import org.mapstruct.factory.Mappers;

public interface ProviderMapper {
    ProviderMapper INSTANCE = Mappers.getMapper( ProviderMapper.class );
    ProviderDto mapUserDto(Provider source);
    Provider mapUser(ProviderDto source);
}
