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

import com.asekulsk.microservice.web.spring.security.util.SecurityContextUtils;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Component;
import com.vaadin.ui.MenuBar;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.security.VaadinSecurity;

import java.util.Locale;

/**
 * Class to create an user menu which contains edit profile, preferences and sign out as interaction.
 *
 * @author Andreas Sekulski
 */
public class MenuContainerUser implements MenuContainer {

    /**
     * Vaadin security instance to operate with user.
     */
    private VaadinSecurity vaadinSecurity;

    /**
     * Internationalizing object.
     */
    private I18N i18N;

    /**
     * Menu bar to handle menu visualization.
     */
    private final MenuBar menuBar = new MenuBar();

    /**
     * Menu container for user management.
     *
     * @param vaadinSecurity Vaadin security object to handle user management.
     */
    public MenuContainerUser(VaadinSecurity vaadinSecurity) {
        this.vaadinSecurity = vaadinSecurity;
        this.i18N = null;
    }

    /**
     * Menu container for user management.
     *
     * @param vaadinSecurity Vaadin security object to handle user management.
     * @param i18N           Internationalizing object.
     * @param locale         Current locale to setup label value.
     */
    public MenuContainerUser(VaadinSecurity vaadinSecurity, I18N i18N, Locale locale) {
        this.vaadinSecurity = vaadinSecurity;
        this.i18N = i18N;
        I18N(locale);
    }

    @Override
    public Component build() {
        menuBar.addStyleName("user-menu");
        MenuBar.MenuItem menuBarItem = menuBar.addItem(SecurityContextUtils.getUser().getUsername(), VaadinIcons.USER, null);
        menuBarItem.addItem("Profile", null);
        menuBarItem.addItem("Preferences", null);
        menuBarItem.addSeparator();
        menuBarItem.addItem("Logout", e -> vaadinSecurity.logout());
        return menuBar;
    }

    @Override
    public void I18N(Locale locale) {
        if (i18N != null) {
            menuBar.removeItems();
            MenuBar.MenuItem menuBarItem = menuBar.addItem(SecurityContextUtils.getUser().getUsername(), VaadinIcons.USER, null);
            menuBarItem.addItem(i18N.get("menu.profile", locale), null);
            menuBarItem.addItem(i18N.get("menu.preferences", locale), null);
            menuBarItem.addSeparator();
            menuBarItem.addItem(i18N.get("menu.logout", locale), e -> vaadinSecurity.logout());
        }
    }
}