package mobi.idappthat.phms_main.Application;

import android.content.Intent;

import com.orm.SugarApp;
import com.parse.Parse;
import com.parse.ParseUser;

import mobi.idappthat.phms_main.Activities.MenuMain;

/**
 *
 */
public class BaseApplication extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        //Parse.initialize(this, "IawLg6MMPCvFDPafIEyRD8PScVakg6UAfAzHxPti", "zVLI3xQp31IPs06RGZ2b077ubacH196BbmOP1feR"); //mobi
        Parse.initialize(this, "anm3FekmjZLTvOaayxRcxpOtYUsSizliWTBQ9ize", "j2onD1jvcpC8TC42gWbrJsrrTPkc40uVFYjXGBNl");

        if(ParseUser.getCurrentUser() != null) {
            Intent i = new Intent(this, MenuMain.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
    }
}
