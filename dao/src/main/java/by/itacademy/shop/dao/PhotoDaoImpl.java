package by.itacademy.shop.dao;

import by.itacademy.shop.api.dao.PhotoDao;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.entities.Product;
import org.springframework.stereotype.Repository;

@Repository

public class PhotoDaoImpl extends GenericDaoImpl<Photo> implements PhotoDao {

    public PhotoDaoImpl() {
        super(Photo.class);
    }
}
