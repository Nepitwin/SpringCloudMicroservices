package com.asekulsk.microservice.web.component.menu;

import com.asekulsk.microservice.web.listener.NavigatorClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.i18n.support.Translatable;

import java.util.Locale;

/**
 * Class to create an menu item container.
 *
 * @author Andreas Sekulski
 */
public class MenuContainerView implements MenuContainer {

    /**
     * View class to show
     **/
    private Class<? extends View> view;

    /**
     * Name from menu
     **/
    private String label;

    /**
     * View identification to change from navigator
     **/
    private String viewID;

    /**
     * Menu icon
     **/
    private VaadinIcons icon;

    /**
     * Navigator to switch view
     **/
    private Navigator navigator;

    /**
     * Internationalizing object.
     */
    private I18N i18N;

    /**
     * Navigation button to switch view.
     */
    private final Button navigationButton = new Button();

    /**
     * Creates an menu item container.
     *
     * @param view      Class Object which should be shown
     * @param viewID    Identification name from view
     * @param label     Name from menu
     * @param icon      Icon from menu
     * @param navigator Navigator to change view
     */
    public MenuContainerView(Class<? extends View> view, String viewID, String label, VaadinIcons icon, Navigator navigator) {
        /* Responding view class to show */
        this.view = view;
        this.label = label;
        this.icon = icon;
        this.navigator = navigator;
        this.viewID = viewID;
        this.navigator.addView(this.viewID, this.view);
        this.i18N = null;
    }

    /**
     * Creates an menu item container with I18N support.
     *
     * @param view      Class Object which should be shown
     * @param viewID    Identification name from view
     * @param label     I18N id for menu label
     * @param icon      Icon from menu
     * @param navigator Navigator to change view
     * @param i18N      Internationalizing object.
     * @param locale    Current locale to setup label value.
     */
    public MenuContainerView(Class<? extends View> view, String viewID, String label, VaadinIcons icon, Navigator navigator, I18N i18N, Locale locale) {
        /* Responding view class to show */
        this.view = view;
        this.label = label;
        this.icon = icon;
        this.navigator = navigator;
        this.viewID = viewID;
        this.navigator.addView(this.viewID, this.view);
        this.i18N = i18N;
        I18N(locale);
    }

    @Override
    public Component build() {
        if(i18N == null) {
            navigationButton.setCaption(this.label);
        }
        navigationButton.addClickListener(new NavigatorClickListener(this.viewID, this.navigator));
        navigationButton.setCaptionAsHtml(true);
        navigationButton.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        navigationButton.setIcon(this.icon);
        return navigationButton;
    }

    @Override
    public void I18N(Locale locale) {
        if(i18N != null) {
            navigationButton.setCaption(i18N.get(label, locale));
            if(navigator.getCurrentView() instanceof Translatable) {
                ((Translatable) navigator.getCurrentView()).updateMessageStrings(locale);
            }
        }
    }
}