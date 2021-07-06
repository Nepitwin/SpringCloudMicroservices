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

package com.asekulsk.microservice.web.vaadin.ui;

import com.asekulsk.microservice.web.vaadin.component.language.LanguageSelector;
import com.asekulsk.microservice.web.vaadin.util.HTML;
import com.asekulsk.microservice.web.vaadin.util.V18NTheme;
import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.i18n.support.TranslatableUI;
import org.vaadin.spring.security.shared.VaadinSharedSecurity;

import java.util.Locale;

/**
 * Vaadin login user interface.
 *
 * @author Andreas Sekulski
 */
@SpringUI(path = "/login")
@Theme("V18N")
public class LoginUI extends TranslatableUI {

    /**
     * Internationalizing
     */
    @Autowired
    private I18N i18n;

    /**
     * Vaadin based spring web security setup
     */
    @Autowired
    private VaadinSharedSecurity vaadinSecurity;

    /**
     * Notification for login, logout or error messages.
     */
    private Notification notification;

    /**
     * Username as textfield
     */
    private final TextField usernameField = new TextField();

    /**
     * Password field.
     */
    private final PasswordField passwordField = new PasswordField();

    /**
     * Login button.
     */
    private final Button login = new Button();

    /**
     * Sign in label text.
     */
    private final Label labelSignIn = new Label();

    /**
     * Information label which contains static data.
     */
    private final Label informationLabel = new Label();

    /**
     * Sign in layout.
     */
    private final VerticalLayout signInLayout = new VerticalLayout();

    /**
     * Login layout box.
     */
    private final VerticalLayout loginLayout = new VerticalLayout();

    /**
     * Information box layout.
     */
    private final VerticalLayout information = new VerticalLayout();

    /**
     * Information box which contains login box and information.
     */
    private final VerticalLayout infoBox = new VerticalLayout();

    /**
     * Left information box layout.
     */
    private final VerticalLayout infoLayout = new VerticalLayout();

    /**
     * Root layout from login.
     */
    private final HorizontalLayout rootLayout = new HorizontalLayout();

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
        FormLayout loginForm = new FormLayout();
        loginForm.setSizeUndefined();

        usernameField.setIcon(VaadinIcons.USER);
        passwordField.setIcon(VaadinIcons.PASSWORD);

        login.addStyleName(ValoTheme.BUTTON_PRIMARY + " " + V18NTheme.LOGIN_BUTTON);
        login.setDisableOnClick(true);
        login.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        login.addClickListener(e -> login());

        loginForm.addComponent(usernameField);
        loginForm.addComponent(passwordField);
        loginForm.addComponent(login);

        labelSignIn.addStyleName(ValoTheme.LABEL_H1 + " " + V18NTheme.NO_MARGIN);

        signInLayout.setSizeFull();
        signInLayout.setMargin(false);
        signInLayout.addComponent(labelSignIn);
        signInLayout.addStyleName(V18NTheme.TEXT_ALIGN_CENTER);

        loginLayout.setSpacing(false);
        loginLayout.setSizeUndefined();
        loginLayout.addStyleName(V18NTheme.LOGIN_FORM);
        loginLayout.addComponent(signInLayout);
        loginLayout.addComponent(loginForm);
        loginLayout.setComponentAlignment(loginForm, Alignment.TOP_CENTER);

        // Get static html file from resources folder
        informationLabel.setContentMode(ContentMode.HTML);
        informationLabel.addStyleName(V18NTheme.TEXT_COLOR_LOGIN);
        informationLabel.setWidth("100%");

        information.addComponent(informationLabel);
        information.addComponent(loginLayout);

        infoBox.setMargin(false);
        infoBox.setSpacing(false);
        infoBox.setSizeFull();
        infoBox.addComponent(information);
        infoBox.addStyleName(V18NTheme.INFO_BOX);

        infoLayout.addComponent(infoBox);
        infoLayout.addComponent(languageSelector);
        infoLayout.setWidth("50%");
        infoLayout.setHeight("100%");
        infoLayout.setExpandRatio(infoBox, 0.95f);

        rootLayout.addComponent(infoLayout);
        rootLayout.setSizeFull();
        rootLayout.setComponentAlignment(infoLayout, Alignment.TOP_LEFT);
        rootLayout.addStyleName(V18NTheme.LOGIN_BACKGROUND);
        setContent(rootLayout);
        setSizeFull();

        // Set focus to username
        usernameField.focus();

        if (request.getParameter("logout") != null) {
            // Show log out notification
            notification = new Notification(i18n.get("account", getLocale()),
                    i18n.get("account.logout", getLocale()),
                    Notification.Type.TRAY_NOTIFICATION);
            notification.setDelayMsec(2000);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());
        }
    }

    @Override
    protected void updateMessageStrings() {
        Locale locale = languageSelector.getLocale();

        getPage().setTitle(i18n.get("login", locale));
        usernameField.setPlaceholder(i18n.get("username", locale));
        passwordField.setPlaceholder(i18n.get("password", locale));
        login.setCaption(i18n.get("login", locale));
        labelSignIn.setValue(i18n.get("sign", locale));

        // Get static html file from resources folder
        String informationHTML = "static/information";
        informationLabel.setValue(HTML.loadStaticHTML(getClass().getClassLoader(), informationHTML, locale));
    }

    /**
     * Login method to try to log in.
     */
    private void login() {
        try {
            vaadinSecurity.login(usernameField.getValue(), passwordField.getValue(), true);
        } catch (AuthenticationException ex) {
            passwordField.focus();
            usernameField.selectAll();
            passwordField.setValue("");

            // Show log in failde notification
            notification = new Notification("",
                    i18n.get("login.failed", languageSelector.getLocale()),
                    Notification.Type.ERROR_MESSAGE);
            notification.setDelayMsec(2000);
            notification.setPosition(Position.TOP_CENTER);
            notification.show(Page.getCurrent());

        } catch (Exception ex) {
            Notification.show(i18n.get("error.message.unknown", languageSelector.getLocale()),
                    ex.getMessage(),
                    Notification.Type.ERROR_MESSAGE);
            LoggerFactory.getLogger(getClass()).error("Unexpected error while logging in", ex);
        } finally {
            login.setEnabled(true);
        }
    }
}
