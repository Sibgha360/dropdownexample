package com.mycompany;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.googlecode.wicket.jquery.ui.panel.JQueryFeedbackPanel;

public class Dpanel extends Panel
{
    private static final long serialVersionUID = 1L;
    private static final List<String> GENRES = Arrays.asList( "Accuracy", "Precision", "Recall","F1");
    
    private Form<Void> form;
    private AjaxLink<Void> link;

    public Dpanel(String aId)
    {
        super(aId);
        form = new Form<Void>("form");
        form.setOutputMarkupId(true);
        //this.add(form);

        // FeedbackPanel //
        final JQueryFeedbackPanel feedback = new JQueryFeedbackPanel("feedback");
        //form.
        add(feedback);

        // SelectMenu //
        final DropDownChoice<String> dropdown = new DropDownChoice<String>("select", new Model<String>(), new ListModel<String>(GENRES));
        //form.

        
        dropdown.setRequired(true);
        dropdown.setOutputMarkupId(true);
 
        dropdown.add(new AjaxFormComponentUpdatingBehavior("onchange") {
            /**
             * 
             */
            private static final long serialVersionUID = -6744838136235652577L;

            protected void onUpdate(AjaxRequestTarget target) {
                System.out.println("Changed");

            }
        }); 
 
        
         add(dropdown.setOutputMarkupId(true));
        
        link = new AjaxLink<Void>("link")
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target)
            {
                String linkText = (String) getBody().getObject();

                System.out.println(linkText);
                System.out.println(link.getMarkupId());


                if ("Hide".equals(linkText)) {
                    // toHide.setDefaultModelObject("New value");
                    Effects.hide(target, dropdown);
                    target.appendJavaScript( "document.getElementById('"+link.getMarkupId()+"').classList.remove('glyphicon-chevron-left');" );
                    target.appendJavaScript( "document.getElementById('"+link.getMarkupId()+"').classList.add('glyphicon-chevron-right');" );
                    link.setBody(Model.of("Show"));

                }
                else {

                    Effects.show(target, dropdown);
                    target.appendJavaScript( "document.getElementById('"+link.getMarkupId()+"').classList.remove('glyphicon-chevron-right');" );
                    target.appendJavaScript( "document.getElementById('"+link.getMarkupId()+"').classList.add('glyphicon-chevron-left');" );
                    link.setBody(Model.of("Hide"));

                }

            }
        };
        link.setBody(Model.of(""));

        link.setBody(Model.of("Hide"));
        link.setOutputMarkupId(true);
        //form.
        add(link);
    }
    
    @Override
    public void renderHead(IHeaderResponse aResponse)
    {
        super.renderHead(aResponse);
    }
    
    public String getFormMarkupId()
    {
        return form.getMarkupId();
    }
    
    private static class Effects
    {

        private static void hide(AjaxRequestTarget target, Component component)
        {
            component.add(new DisplayNoneBehavior());
            String js = "$('#"+component.getMarkupId()+"').animate({'width': '-=170'},  100); $('#"+((DropDownChoice)component).getOutputMarkupId()+"').hide();";
            target.prependJavaScript(js);
        }

        private static void show(AjaxRequestTarget target, Component component)
        {
            component.add(new DisplayNoneBehavior());

            String js = "$('#"+component.getMarkupId()+"').animate({'width': '+=170'},  100); $('#"+((DropDownChoice)component).getOutputMarkupId()+"').show();";
            target.prependJavaScript(js);
        }
    }

    private static class DisplayNoneBehavior
        extends AttributeModifier
    {

        private DisplayNoneBehavior()
        {
            super("style", Model.of("display: none"));
        }

        @Override
        public boolean isTemporary(Component component)
        {
            return true;
        }
    }
}
