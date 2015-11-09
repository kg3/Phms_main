package mobi.idappthat.test_noteapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.ParseUser;

import mobi.idappthat.test_noteapp.Adapters.TabsAdapter;
import mobi.idappthat.test_noteapp.R;

/**
 * Created by Cameron on 11/6/15.
 */
public class MainActivity extends AppCompatActivity {

    public static final String FLASH_WELCOME = "flash_welcome";

    public static final int CODE_NOTES = 0;
    public static final int CODE_TODO = 1;

    public static final int ACTION_CANCEL = 0;
    public static final int ACTION_CREATE = 1;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check for intents
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            if(extras.getBoolean(FLASH_WELCOME, false)) {
                View v = findViewById(R.id.root_view);

                Snackbar.make(v, "Welcome!", Snackbar.LENGTH_LONG)
                    .setAction("Tutorial", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //nothing
                        }
                    }).show();

            }
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;

            case R.id.action_logout:
                ParseUser.logOut();
                Intent i = new Intent(this, StartActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
