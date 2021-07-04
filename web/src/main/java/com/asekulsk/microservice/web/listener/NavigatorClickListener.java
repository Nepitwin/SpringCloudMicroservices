package com.asekulsk.microservice.web.listener;

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