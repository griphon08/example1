/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yournamehere.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import org.yournamehere.client.sampleservice.GWTService;
import org.yournamehere.client.sampleservice.GWTServiceAsync;

/**
 * Main entry point.
 *
 * @author nbuser
 */
public class MainEntryPoint implements EntryPoint {

    /**
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
    }

    public static GWTServiceAsync getService() {
        // Create the client proxy. Note that although you are creating the
        // service interface proper, you cast the result to the asynchronous
        // version of the interface. The cast is always safe because the
        // generated proxy implements the asynchronous interface automatically.

        return GWT.create(GWTService.class);
    }

    /** 
     * The entry point method, called automatically by loading a module
     * that declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
        final Label quoteText = new Label();

        Timer timer = new Timer() {

            public void run() {
                //create an async callback to handle the result:
                AsyncCallback callback = new AsyncCallback() {

                    public void onFailure(Throwable arg0) {
                        //display error text if we can't get the quote:
                        quoteText.setText("Failed to get a quote");
                    }

            public void onSuccess(Object result) {
                //display the retrieved quote in the label:
                quoteText.setText((String) result);
                quoteText.setStyleName("quoteLabel");
            }
        };
        getService().myMethod(callback);
            }
        };

        timer.scheduleRepeating(1000);
        RootPanel.get().add(quoteText);

    }
}
