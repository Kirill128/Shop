package by.itacademy.shop.rest.admin;

import by.itacademy.shop.api.annotations.LogExceptionCatchable;

import by.itacademy.shop.api.dto.admin.AdminProviderDto;
import by.itacademy.shop.api.services.ProviderService;
import by.senla.daomicroservice.microservices.constants.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
@Profile("release")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_ROOT)
    public ModelAndView getProviders(){
        return new ModelAndView("/admin/providers")
                .addObject("providers",this.providerService.getAllProviders())
                .addObject("newProvider",new AdminProviderDto());
    }

    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_CREATE)
    @LogExceptionCatchable
    public ModelAndView createProvider(@ModelAttribute AdminProviderDto providerDto) throws JsonProcessingException {
        this.providerService.createProvider(providerDto);
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_ROOT);
    }
    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_UPDATE)
    @LogExceptionCatchable
    public ModelAndView updateProvider(@ModelAttribute AdminProviderDto providerDto){
        this.providerService.update(providerDto);
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_ROOT);
    }
    @PostMapping(Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_DELETE)
    @LogExceptionCatchable
    public ModelAndView deleteProvider(@ModelAttribute AdminProviderDto providerDto){
        this.providerService.delete(providerDto.getId());
        return new ModelAndView(Constants.REDIRECT+Constants.ROLE_ADMIN_ACCOUNT_PROVIDERS_ROOT);
    }

}
