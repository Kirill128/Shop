package by.itacademy.shop.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ShopSpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {//or interface WebApplicationInitializer
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RestConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{RestConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
