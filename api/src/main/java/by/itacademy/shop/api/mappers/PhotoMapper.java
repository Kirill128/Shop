package by.itacademy.shop.api.mappers;

import by.itacademy.shop.api.dto.forall.GuestProductPhotoDto;
import by.itacademy.shop.entities.Photo;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PhotoMapper {
    public GuestProductPhotoDto mapProductPhotoToGuestProductPhotoDto(Photo photo){
        return GuestProductPhotoDto.builder().
                id(photo.getId()).
                url(photo.getUrl()).
                build();
    }
    public Photo mapGuestProductPhotoDtoToPhoto(GuestProductPhotoDto photo){
        return Photo.builder().
                id(photo.getId()).
                url(photo.getUrl()).
                build();
    }
    public List<GuestProductPhotoDto> mapProductPhotosToGuestProductPhotoDtos(List<Photo> photos){
        return photos.stream().map(PhotoMapper::mapProductPhotoToGuestProductPhotoDto).collect(Collectors.toList());
    }
    public List<Photo> mapGuestProductPhotosDtoToPhotos(List<GuestProductPhotoDto> photos){
        return photos.stream().map(PhotoMapper::mapGuestProductPhotoDtoToPhoto).collect(Collectors.toList());
    }
}
