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

package com.asekulsk.microservice.web.vaadin.view;

import com.asekulsk.microservice.web.vaadin.component.language.LanguageSelector;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Panel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.i18n.support.Translatable;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Sample user view to visualize sample data.
 *
 * @author Andreas Sekulski
 */
@SpringView(name = BugView.VIEW_NAME)
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BugView extends HorizontalLayout implements View, Translatable {

    /**
     * Defined unique view name to identify.
     */
    public static final String VIEW_NAME = "HomeView";

    /**
     * Label name from view.
     */
    public static final String VIEW_DESCRIPTION = "menu.home";

    /**
     * View menu icon.
     * <br>
     * Icons from https://vaadin.com/icons
     */
    public static final VaadinIcons VIEW_ICON = VaadinIcons.BUG;

    /**
     * Main panel from view.
     */
    private final Panel container = new Panel();

    /**
     * I18N
     */
    @Autowired
    private I18N i18n;

    /**
     * Language selector to set supported languages.
     */
    private final LanguageSelector languageSelector = new LanguageSelector();

    @PostConstruct
    void init() {
        this.setSizeFull();
        this.setMargin(true);

        container.setCaption(i18n.get("panel.bug", languageSelector.getLocale()));
        container.setIcon(VaadinIcons.BUG_O);
        container.setSizeFull();
        container.setContent(panelContent());

        this.addComponent(container);
        this.setExpandRatio(container, 1);
    }

    /**
     * Generates panel administration content.
     *
     * @return Component content from administration context.
     */
    private Component panelContent() {
        ThemeResource resource = new ThemeResource("img/bugs.jpg");
        Image image = new Image("Bugs bugs bugs", resource);
        image.setSizeFull();
        return image;
    }

    @Override
    public void updateMessageStrings(Locale locale) {
        container.setCaption(i18n.get("panel.bug", locale));
    }
}

