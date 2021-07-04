package com.asekulsk.microservice.web.component.language;

import com.vaadin.event.LayoutEvents;
import com.vaadin.ui.HorizontalLayout;
import org.vaadin.addons.countryflag.CountryFlag;

import java.util.Locale;

/**
 * Custom component for country handling.
 *
 * @author Andreas Sekulski
 */
public class Country extends HorizontalLayout {

    /**
     * Constructor to create specific country component.
     *
     * @param iso ISO 3166-1-alpha-2 country code needed.
     */
    public Country(String iso, LanguageSelector selector, Locale locale) {
        CountryFlag flag = new CountryFlag(iso);

        addLayoutClickListener((LayoutEvents.LayoutClickListener) event -> selector.setLocale(locale));
        setSpacing(false);
        setMargin(false);
        addComponent(flag);

        flag.setWidth("40px");
        flag.setHeight("40px");
    }
}
