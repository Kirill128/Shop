package by.itacademy.shop.api.services;

import by.itacademy.shop.api.dto.forall.GuestProductPhotoDto;
import by.itacademy.shop.api.dto.user.UserDto;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PhotoService {
    GuestProductPhotoDto createPhoto( MultipartFile file) throws IOException;
    GuestProductPhotoDto find(long id);
    void update(GuestProductPhotoDto photoDto, MultipartFile file) throws IOException;
    void delete(long id) throws IOException;

    List<GuestProductPhotoDto> getAllPhotos();
}
