package com.asekulsk.microservice.web.component.menu;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import org.vaadin.spring.i18n.I18N;

import java.util.Locale;

/**
 * Class to create an menu label.
 *
 * @author Andreas Sekulski
 */
public class MenuContainerLabel implements MenuContainer {

    /**
     * Label name
     **/
    private String text;

    /**
     * Cascading Style Sheet from label
     **/
    private String css;

    /**
     * Internationalizing object.
     */
    private I18N i18N;

    /**
     * Label to store for modifications.
     */
    private final Label label = new Label();

    /**
     * Constructor to create an menu label as plain an text or html element
     *
     * Example:
     * <h3> Hello World </h3> - ContentMode HTML
     * Hello World - ContentMode TEXT
     *
     * @param text name from menu label
     * @param css  class name from label
     */
    public MenuContainerLabel(String text, String css) {
        this.text = text;
        this.css = css;
        this.i18N = null;
    }

    /**
     * Constructor to create an menu label based in internationalizing.
     *
     * @param textI18N Text code to use for I18N
     * @param css      Class name from label
     * @param i18N     Internationalizing object.
     * @param locale   Current locale to setup label value.
     */
    public MenuContainerLabel(String textI18N, String css, I18N i18N, Locale locale) {
        this.text = textI18N;
        this.css = css;
        this.i18N = i18N;
        I18N(locale);
    }

    @Override
    public Component build() {

        if (i18N == null) {
            label.setValue(text);
        }

        label.setPrimaryStyleName(this.css);
        label.setSizeUndefined();
        return label;
    }

    @Override
    public void I18N(Locale locale) {

        if (i18N != null) {
            label.setValue(i18N.get(text, locale));
        }

    }
}
