package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.LogExceptionCatchable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.forall.GuestProductPhotoDto;
import by.itacademy.shop.api.services.PhotoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping(Constants.ROLE_ADMIN_ACCOUNT_PHOTOS_ROOT)
    public ModelAndView getAllPhoto(){
        return new ModelAndView("/admin/photos")
                .addObject("photos",this.photoService.getAllPhotos());
    }
    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PHOTOS_CREATE)
    @LogExceptionCatchable
    public ModelAndView createPhoto(@RequestParam("imageFileCr") MultipartFile photoFile) throws IOException {
        this.photoService.createPhoto(photoFile);
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PHOTOS_ROOT);
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PHOTOS_UPDATE)
    @LogExceptionCatchable
    public ModelAndView updatePhoto(@ModelAttribute GuestProductPhotoDto photoDto,@RequestParam("imageFileUpd") MultipartFile photoFile) throws IOException {
        this.photoService.update(photoDto,photoFile);
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PHOTOS_ROOT);
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PHOTOS_DELETE)
    @LogExceptionCatchable
    public ModelAndView deletePhoto(@ModelAttribute GuestProductPhotoDto photoDto) throws IOException {
        this.photoService.delete(photoDto.getId());
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PHOTOS_ROOT);
    }
}
