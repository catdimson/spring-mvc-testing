package ru.kotik.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class SpringMVCDispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // метод не используется, по этому ставим null
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // указываем свой конфигурационный класс
        return new Class[] { SpringConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    /*@Override
    public Filter[] getServletFilters() {
        return new Filter[] { characterSetFilter };
    }*/
}
