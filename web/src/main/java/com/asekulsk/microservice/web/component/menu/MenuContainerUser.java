package com.asekulsk.microservice.web.component.menu;

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
        MenuBar.MenuItem menuBarItem = menuBar.addItem(/*SecurityContextUtils.getUser().getUsername()*/ "Max Mustermann", VaadinIcons.USER, null);
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
            MenuBar.MenuItem menuBarItem = menuBar.addItem(/*SecurityContextUtils.getUser().getUsername()*/ "Max Mustermann", VaadinIcons.USER, null);
            menuBarItem.addItem(i18N.get("menu.profile", locale), null);
            menuBarItem.addItem(i18N.get("menu.preferences", locale), null);
            menuBarItem.addSeparator();
            menuBarItem.addItem(i18N.get("menu.logout", locale), e -> vaadinSecurity.logout());
        }
    }
}