package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.LogExceptionCatchable;
import by.itacademy.shop.api.constants.Constants;
import by.itacademy.shop.api.dto.admin.AdminProviderDto;
import by.itacademy.shop.api.services.ProviderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS)
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_ROOT)
    @LogExceptionCatchable
    public ModelAndView getProviders(){
        return new ModelAndView("/admin/providers")
                .addObject("providers",this.providerService.getAllProviders())
                .addObject("newProvider",new AdminProviderDto());
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_CREATE)
    @LogExceptionCatchable
    public ModelAndView createProvider(@ModelAttribute AdminProviderDto providerDto){
        this.providerService.createProvider(providerDto);
        return new ModelAndView("redirect:"+Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS);
    }
    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_UPDATE)
    @LogExceptionCatchable
    public ModelAndView updateProvider(@ModelAttribute AdminProviderDto providerDto){
        this.providerService.update(providerDto);
        return new ModelAndView("redirect:"+Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS);
    }
    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_DELETE)
    @LogExceptionCatchable
    public ModelAndView deleteProvider(@ModelAttribute AdminProviderDto providerDto){
        this.providerService.delete(providerDto.getId());
        return new ModelAndView("redirect:"+Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS);
    }

}
