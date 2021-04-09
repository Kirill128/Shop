package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.PhotoDao;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;

public class PhotoDaoImpl extends GenericDaoImpl<Photo> implements PhotoDao {

    protected PhotoDaoImpl() {
        super(Photo.class);
    }
}
