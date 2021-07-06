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

package com.asekulsk.microservice.web.vaadin.listener;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;

/**
 * Class to generate an click event listener to change an view.
 *
 * @author Andreas Sekulski
 */
public class NavigatorClickListener implements Button.ClickListener {

    /**
     * View identification to change view in navigator
     */
    private String view;

    /**
     * Navigator to change views.
     */
    private Navigator navigator;

    /**
     * Constructs an navigation click event listener
     *
     * @param view      identification from view to change
     * @param navigator navigator to change views.
     */
    public NavigatorClickListener(String view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        navigator.navigateTo(view);
    }
}