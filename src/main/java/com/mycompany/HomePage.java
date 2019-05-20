package com.mycompany;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage
    extends WebPage
{
    private static final long serialVersionUID = 1L;
    
    
    public HomePage(final PageParameters parameters)
    {
        super(parameters);
        
        final Panel dropDownPanel = new Dpanel("toReplace");

        dropDownPanel.setOutputMarkupId(true);
        add(dropDownPanel);

        
    }

    
}
