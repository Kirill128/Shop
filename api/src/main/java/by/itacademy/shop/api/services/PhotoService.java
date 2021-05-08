package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.forall.GuestProductPhotoDto;
import by.itacademy.shop.api.dto.user.UserDto;

import java.util.List;

public interface PhotoService {
    GuestProductPhotoDto createPhoto(GuestProductPhotoDto photoDto);
    GuestProductPhotoDto find(long id);
    void update(UserDto user);
    void delete(long id);

    List<GuestProductPhotoDto> getAllPhotos();
}
