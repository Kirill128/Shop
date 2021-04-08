package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.ProviderDao;
import by.itacademy.shop.entities.Provider;

public class ProviderDaoImpl extends GenericDaoImpl<Provider> implements ProviderDao {
    protected ProviderDaoImpl() {
        super(Provider.class);
    }
}
