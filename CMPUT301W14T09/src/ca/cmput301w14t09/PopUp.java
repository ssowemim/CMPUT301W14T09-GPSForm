package ca.cmput301w14t09;

import android.app.Activity;
import android.app.Dialog;


public class PopUp {
    protected Dialog dialog;
    protected Activity caller;  
    
    public PopUp(Activity caller) {
        this.caller = caller;
    }  
}
