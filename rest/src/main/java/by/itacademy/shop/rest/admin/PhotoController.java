package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.ExceptionCatchable;
import by.itacademy.shop.api.annotations.Log;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.forall.GuestProductPhotoDto;
import by.itacademy.shop.api.services.PhotoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(Constants.ROLE_ADMIN_ACCOUNT_PHOTOS)
public class PhotoController {
    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public ModelAndView getAllPhoto(){
        return new ModelAndView("/admin/photos")
                .addObject("photos",this.photoService.getAllPhotos());
    }
    @PostMapping("/create")
    @ExceptionCatchable
    @Log
    public ModelAndView createPhoto(@RequestParam("imageFileCr") MultipartFile photoFile) throws IOException {
        this.photoService.createPhoto(photoFile);
        return new ModelAndView("redirect:"+Constants.ROLE_ADMIN_ACCOUNT_PHOTOS);
    }

    @PostMapping("/update")
    @ExceptionCatchable
    @Log
    public ModelAndView updatePhoto(@ModelAttribute GuestProductPhotoDto photoDto,@RequestParam("imageFileUpd") MultipartFile photoFile) throws IOException {
        this.photoService.update(photoDto,photoFile);
        return new ModelAndView("redirect:"+Constants.ROLE_ADMIN_ACCOUNT_PHOTOS);
    }

    @PostMapping("/delete")
    @ExceptionCatchable
    @Log
    public ModelAndView deletePhoto(@ModelAttribute GuestProductPhotoDto photoDto) throws IOException {
        this.photoService.delete(photoDto.getId());
        return new ModelAndView("redirect:"+Constants.ROLE_ADMIN_ACCOUNT_PHOTOS);
    }
}
