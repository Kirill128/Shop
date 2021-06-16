package by.itacademy.shop.services;

import by.itacademy.shop.api.dao.PhotoDao;
import by.itacademy.shop.api.dto.forall.GuestProductPhotoDto;
import by.itacademy.shop.api.mappers.PhotoMapper;
import by.itacademy.shop.api.services.PhotoService;
import by.itacademy.shop.entities.Photo;
import by.itacademy.shop.utils.ImageUploader;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {

    private PhotoDao photoDao;

    public PhotoServiceImpl(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    @Override
    public GuestProductPhotoDto createPhoto( MultipartFile file)throws IOException {
        String filePath= ImageUploader.updateOrCreateImg(file,null);
        Photo photo=Photo.builder().url(filePath).build();
        return PhotoMapper.mapProductPhotoToGuestProductPhotoDto(this.photoDao.create(photo));
    }

    @Override
    public GuestProductPhotoDto find(long id) {
        return PhotoMapper.mapProductPhotoToGuestProductPhotoDto(this.photoDao.find(id));

    }

    @Override
    public void update(GuestProductPhotoDto photoDto, MultipartFile file) throws IOException {
        String filePath= ImageUploader.updateOrCreateImg(file,photoDto.getUrl());
        photoDto.setUrl(filePath);
        this.photoDao.update(PhotoMapper.mapGuestProductPhotoDtoToPhoto(photoDto));
    }

    @Override
    public void delete(long id) throws IOException {
        Photo foundGuestProdDto=this.photoDao.find(id);
        Files.deleteIfExists(Paths.get(foundGuestProdDto.getUrl()));
        Photo photo=this.photoDao.find(id);
        this.photoDao.delete(photo);
    }

    @Override
    public List<GuestProductPhotoDto> getAllPhotos() {
        return PhotoMapper.mapProductPhotosToGuestProductPhotoDtos(this.photoDao.findAll());
    }
}
