package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.PhotoDto;
import by.itacademy.shop.api.dto.UserDto;

import java.util.List;

public interface PhotoService {
    PhotoDto createPhoto(PhotoDto user);
    PhotoDto find(long id);
    void update(UserDto user);
    void delete(long id);

    List<PhotoDto> getAllPhotos();
}
