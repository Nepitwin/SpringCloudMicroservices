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

package com.asekulsk.microservice.web.vaadin.component.menu;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.vaadin.spring.i18n.I18N;

import java.util.Locale;

/**
 * Class to create an menu title element.
 *
 * @author Andreas Sekulski
 */
public class MenuContainerTitle implements MenuContainer {

    /**
     * Test message value
     **/
    private String text;

    /**
     * Cascading Style Sheet from label
     **/
    private String css;

    /**
     * Content mode if HTML or Plain Text
     **/
    private ContentMode contentMode;

    /**
     * Internationalizing object.
     */
    private I18N i18N;

    /**
     * Title label to create content.
     */
    private final Label title = new Label();

    /**
     * Constructor to create an menu title element as an plain text or html element
     *
     * @param text        Text from menu label
     * @param css         Class name from label
     * @param contentMode Content mode if html or text
     */
    public MenuContainerTitle(String text, String css, ContentMode contentMode) {
        this.text = text;
        this.css = css;
        this.contentMode = contentMode;
        this.i18N = null;
    }

    /**
     * Constructor to create a I18N based menu container title component.
     *
     * @param text        Text from menu label
     * @param css         Class name from label
     * @param contentMode Content mode if html or text
     * @param i18N        Internationalizing for container
     * @param locale      Current locale to setup label value.
     */
    public MenuContainerTitle(String text, String css, ContentMode contentMode, I18N i18N, Locale locale) {
        this.text = text;
        this.css = css;
        this.contentMode = contentMode;
        this.i18N = i18N;
        I18N(locale);
    }


    @Override
    public Component build() {
        HorizontalLayout menuTitle = new HorizontalLayout();

        if(i18N == null) {
            title.setValue(this.text);
        }

        title.setContentMode(this.contentMode);
        title.setSizeUndefined();

        menuTitle.setWidth("100%");
        menuTitle.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        menuTitle.addStyleName(this.css);
        menuTitle.addComponent(title);
        menuTitle.setExpandRatio(title, 1);

        return menuTitle;
    }

    @Override
    public void I18N(Locale locale) {

        if(i18N != null) {
            title.setValue(i18N.get(text, locale));
        }

    }
}