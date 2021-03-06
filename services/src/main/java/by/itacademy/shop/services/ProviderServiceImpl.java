package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.ProviderDao;
import by.itacademy.shop.api.dto.admin.AdminProviderDto;
import by.itacademy.shop.api.mappers.ProviderMapper;
import by.itacademy.shop.api.services.ProviderService;
import by.itacademy.shop.entities.Provider;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {
    private ProviderDao providerDao;

    public ProviderServiceImpl(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    @Override
    public AdminProviderDto createProvider(AdminProviderDto providerDto) throws JsonProcessingException {
        return ProviderMapper.mapProviderToProviderDto(this.providerDao.create(ProviderMapper.mapProviderDtoToProvider(providerDto)));
    }

    @Override
    public AdminProviderDto find(long id) {
        return ProviderMapper.mapProviderToProviderDto(this.providerDao.find(id));
    }

    @Override
    public void update(AdminProviderDto providerDto) {
        this.providerDao.update(ProviderMapper.mapProviderDtoToProvider(providerDto));
    }

    @Override
    public void delete(long id) {
        Provider provider=this.providerDao.find(id);
        this.providerDao.delete(provider);
    }

    @Override
    public List<AdminProviderDto> getAllProviders() {
        return ProviderMapper.mapProvidersToProviderDtos(this.providerDao.findAll());
    }
}
