package com.asekulsk.microservice.web.ui;

import com.asekulsk.microservice.web.component.language.LanguageSelector;
import com.asekulsk.microservice.web.component.menu.*;
import com.asekulsk.microservice.web.layout.NavigatorLayout;
import com.asekulsk.microservice.web.view.AdminView;
import com.asekulsk.microservice.web.view.BugView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.i18n.support.TranslatableUI;
import org.vaadin.spring.security.VaadinSecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Example main interface to access management data.
 *
 * @author Andreas Sekulski
 */
@SpringUI(path = "/")
@Theme("V18N")
public class MainUI extends TranslatableUI {

    /**
     * I18N
     */
    @Autowired
    private I18N i18n;

    /**
     * Vaadin security provider to access user management.
     */
    //@Autowired
    //private VaadinSecurity vaadinSecurity;

    /**
     * View provider to switch views.
     */
    @Autowired
    private SpringViewProvider viewProvider;

    /**
     * Navigator to switch view in Administration menu
     **/
    private Navigator navigator;

    /**
     * Layout design from administration user interface
     **/
    private NavigatorLayout adminLayout;

    /**
     * Defined view component from administration page
     **/
    private List<MenuContainer> menuContainers;

    /**
     * Language selector to set supported languages.
     */
    private final LanguageSelector languageSelector = new LanguageSelector();

    @Override
    public void setLocale(Locale locale) {
        updateMessageStrings();
    }

    @Override
    protected void initUI(VaadinRequest request) {
        adminLayout = new NavigatorLayout();

        // Create a navigator to control the views
        navigator = new Navigator(this, adminLayout.getContentContainer());
        navigator.addProvider(viewProvider);

        // Add menu component
        menuContainers = new ArrayList<MenuContainer>();
        menuContainers.add(new MenuContainerTitle("menu.title", ValoTheme.MENU_TITLE, ContentMode.HTML, i18n, languageSelector.getLocale()));
        //menuContainers.add(new MenuContainerUser(vaadinSecurity, i18n, languageSelector.getLocale()));
        menuContainers.add(new MenuContainerLabel("menu.name", ValoTheme.MENU_SUBTITLE, i18n, languageSelector.getLocale()));

        //if(SecurityContextUtils.hasRole(RoleType.ROLE_ADMIN)) {
            // Only if admin is logged in
            menuContainers.add(new MenuContainerView(AdminView.class, AdminView.VIEW_NAME, AdminView.VIEW_DESCRIPTION, AdminView.VIEW_ICON, navigator, i18n, languageSelector.getLocale()));
        //}

        menuContainers.add(new MenuContainerView(BugView.class, BugView.VIEW_NAME, BugView.VIEW_DESCRIPTION, BugView.VIEW_ICON, navigator, i18n, languageSelector.getLocale()));

        // Default view
        navigator.navigateTo(BugView.VIEW_NAME);

        adminLayout.setSizeFull();
        adminLayout.addMenu(buildMenu());

        setContent(adminLayout);
    }

    @Override
    protected void updateMessageStrings() {
        Locale locale = languageSelector.getLocale();

        getPage().setTitle(i18n.get("main.ui.name", locale));
        // I18N for all menu components
        for(MenuContainer container : menuContainers) {
            container.I18N(locale);
        }


    }

    /**
     * Generates the navigation menu layout
     *
     * @return am navigation menu layout
     */
    private CssLayout buildMenu() {
        CssLayout menu = new CssLayout();
        CssLayout menuItemsLayout = new CssLayout();

        // Generate for each menu component an button in menu bar
        for (MenuContainer container : menuContainers) {
            menuItemsLayout.addComponent(container.build());
        }

        VerticalLayout languageLayout = new VerticalLayout();
        languageLayout.addComponent(languageSelector);
        languageLayout.setComponentAlignment(languageSelector, Alignment.TOP_CENTER);
        menuItemsLayout.addComponent(languageLayout);

        menu.addComponent(menuItemsLayout);
        return menu;
    }
}
