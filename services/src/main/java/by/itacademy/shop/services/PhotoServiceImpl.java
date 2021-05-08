package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.PhotoDao;
import by.itacademy.shop.api.dto.forall.GuestProductPhotoDto;
import by.itacademy.shop.api.dto.user.UserDto;
import by.itacademy.shop.api.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PhotoServiceImpl implements PhotoService {
    private PhotoDao photoDao;

    @Autowired
    public PhotoServiceImpl(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }


    @Override
    public GuestProductPhotoDto createPhoto(GuestProductPhotoDto photoDto) {
        return null;
    }

    @Override
    public GuestProductPhotoDto find(long id) {
        return null;
    }

    @Override
    public void update(UserDto user) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<GuestProductPhotoDto> getAllPhotos() {
        return null;
    }
}
