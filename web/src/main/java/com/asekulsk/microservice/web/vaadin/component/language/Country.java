/*
 * Copyright 2018 Andreas Sekulski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.asekulsk.microservice.web.vaadin.component.language;

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
