package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.StatusDao;
import by.itacademy.shop.entities.Status;

public class StatusDaoImpl extends GenericDaoImpl<Status> implements StatusDao {
    protected StatusDaoImpl() {
        super(Status.class);
    }
}
