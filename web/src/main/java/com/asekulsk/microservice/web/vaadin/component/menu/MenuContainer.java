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