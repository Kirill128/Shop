package by.itacademy.shop.rest;

import by.itacademy.shop.api.dto.admin.ProviderDto;
import by.itacademy.shop.api.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderControllerImpl {
    private ProviderService providerService;

    @Autowired
    public ProviderControllerImpl(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping
    public ModelAndView getProviders(){
        ModelAndView modelAndView=new ModelAndView("/admin/providers");
        List<ProviderDto> providerDtos=this.providerService.getAllProviders();
        modelAndView.addObject("providers",providerDtos);
        modelAndView.addObject("newProvider",new ProviderDto());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProvider(@ModelAttribute ProviderDto providerDto){
        ModelAndView modelAndView=new ModelAndView("redirect:/providers");
        this.providerService.createProvider(providerDto);
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView updateProvider(@ModelAttribute ProviderDto providerDto){
        ModelAndView modelAndView=new ModelAndView("redirect:/providers");
        this.providerService.update(providerDto);
        return modelAndView;
    }
    @PostMapping("/delete")
    public ModelAndView deleteProvider(@ModelAttribute ProviderDto providerDto){
        ModelAndView modelAndView=new ModelAndView("redirect:/providers");
        this.providerService.delete(providerDto.getId());
        return modelAndView;
    }

}
