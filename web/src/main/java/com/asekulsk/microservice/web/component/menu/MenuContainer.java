package com.asekulsk.microservice.web.component.menu;

import com.vaadin.ui.Component;

import java.util.Locale;

/**
 * Interface to create an menu item component like views, user-menu or menu subtitles.
 *
 * @author Andreas Sekulski
 */
public interface MenuContainer {

    /**
     * Builds menu-component.
     *
     * @return component
     **/
    Component build();

    /**
     * Internationalizing from component.
     *
     * @param locale Locale to use for I18N.
     */
    void I18N(Locale locale);
}