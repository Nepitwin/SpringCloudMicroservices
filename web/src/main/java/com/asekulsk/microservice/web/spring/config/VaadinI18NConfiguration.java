package com.asekulsk.microservice.web.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.vaadin.spring.i18n.MessageProvider;
import org.vaadin.spring.i18n.ResourceBundleMessageProvider;

/**
 * I18N configuration class from vaaadin i18n addon.
 *
 * @author Andreas Sekulski
 */
@Configuration
@EnableWebMvc
public class VaadinI18NConfiguration {

    /**
     * Message bean provider to access i18n property files.
     *
     * @return Bundler for i18n handling.
     */
    @Bean
    MessageProvider messageProvider() {
        return new ResourceBundleMessageProvider("i18n.messages");
    }
}
