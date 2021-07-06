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

import com.vaadin.ui.HorizontalLayout;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Language selector component to select supported languages.
 *
 * @author Andreas Sekulski
 */
@Component
public class LanguageSelector extends HorizontalLayout {

    /**
     * I18N locale support.
     */
    private static Locale locale;

    /**
     * Language selector constructor to create default selector.
     */
    public LanguageSelector() {

        if(locale == null) {
            locale = Locale.ENGLISH;
        }

        Country germany = new Country("DE", this, Locale.GERMAN);
        Country english = new Country("GB", this, Locale.ENGLISH);

        addComponent(germany);
        addComponent(english);
    }

    /**
     * Sets given locale to language selector.
     *
     * @param locale Local to set.
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        getUI().setLocale(locale);
    }

    /**
     * Get current selected locale.
     * @return Current selected locale for example english.
     **/
    public Locale getLocale() {
        return locale;
    }
}