package com.asekulsk.microservice.web.layout;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Creates an horizontal navigator layout with an vertical menu bar and an side by side content.
 *
 * @author Andreas Sekulski
 */
public class NavigatorLayout extends HorizontalLayout {

    /**
     * Content area
     **/
    CssLayout contentArea = new CssLayout();

    /**
     * Menu area
     **/
    CssLayout menuArea = new CssLayout();

    /**
     * Creates an navigator layout in an valo theme
     */
    public NavigatorLayout() {
        setSizeFull();

        menuArea.setPrimaryStyleName(ValoTheme.MENU_ROOT);

        contentArea.setSizeFull();

        addComponents(menuArea, contentArea);
        setExpandRatio(contentArea, 1);
    }

    /**
     * Get the container element
     *
     * @return get container element from navigation layout
     */
    public ComponentContainer getContentContainer() {
        return contentArea;
    }

    /**
     * Adds an menu to the menuArea
     *
     * @param menu an menu component which should be added
     */
    public void addMenu(Component menu) {
        menu.addStyleName(ValoTheme.MENU_PART);
        menuArea.addComponent(menu);
    }
}