package by.senla.daomicroservice.util.mappers;

import by.senla.daomicroservice.entities.Photo;
import by.senla.daomicroservice.microservices.dto.forall.GuestProductPhotoDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class PhotoMapper {
    public GuestProductPhotoDto mapProductPhotoToGuestProductPhotoDto(Photo photo){
        if(photo==null)return null;
        return GuestProductPhotoDto.builder().
                id(photo.getId()).
                url(photo.getUrl()).
                build();
    }
    public Photo mapGuestProductPhotoDtoToPhoto(GuestProductPhotoDto photo){
        if(photo==null)return null;
        return Photo.builder().
                id(photo.getId()).
                url(photo.getUrl()).
                build();
    }
    public List<GuestProductPhotoDto> mapProductPhotosToGuestProductPhotoDtos(List<Photo> photos){
        if(photos==null)return new ArrayList<>();
        return photos.stream().map(PhotoMapper::mapProductPhotoToGuestProductPhotoDto).collect(Collectors.toList());
    }
    public List<Photo> mapGuestProductPhotosDtoToPhotos(List<GuestProductPhotoDto> photos){
        if(photos==null)return new ArrayList<>();
        return photos.stream().map(PhotoMapper::mapGuestProductPhotoDtoToPhoto).collect(Collectors.toList());
    }
}
