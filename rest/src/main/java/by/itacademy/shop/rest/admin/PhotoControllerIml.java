package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.dto.forall.GuestProductPhotoDto;
import by.itacademy.shop.api.services.PhotoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin/photos")
public class PhotoControllerIml {
    private PhotoService photoService;

    public PhotoControllerIml(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/{id}")
    public ModelAndView findPhoto(@PathVariable int id){
        ModelAndView modelAndView=new ModelAndView();

        return modelAndView;
    }
    @GetMapping
    public ModelAndView getAllPhoto(){
        ModelAndView modelAndView=new ModelAndView("/admin/photos");
        List<GuestProductPhotoDto> photos=this.photoService.getAllPhotos();
        modelAndView.addObject("photos",photos);
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView createPhoto(@RequestParam("imageFileCr") MultipartFile photoFile) throws IOException {
        this.photoService.createPhoto(photoFile);
        return new ModelAndView("redirect:/admin/photos");
    }

    @PostMapping("/update")
    public ModelAndView updatePhoto(@ModelAttribute GuestProductPhotoDto photoDto,@RequestParam("imageFileUpd") MultipartFile photoFile) throws IOException {
        this.photoService.update(photoDto,photoFile);
        return new ModelAndView("redirect:/admin/photos");
    }
    @PostMapping("/delete")
    public ModelAndView deletePhoto(@ModelAttribute GuestProductPhotoDto photoDto) throws IOException {
        this.photoService.delete(photoDto.getId());
        return new ModelAndView("redirect:/admin/photos");
    }
}
