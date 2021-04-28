package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProviderDao;
import by.itacademy.shop.entities.Provider;
import org.springframework.stereotype.Repository;

@Repository

public class ProviderDaoImpl extends GenericDaoImpl<Provider> implements ProviderDao {
    public ProviderDaoImpl() {
        super(Provider.class);
    }
}
