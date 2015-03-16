package com.wararyo.pikaraconsole;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.EventListener;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    Drawer drawer;
    Drawer.Result drawerResult;
    View viewDrawerHeaderRoof;
    ImageView ivDrawerHeader;

    private OnMyDrawerListenerInterface drawerlistener = null;

    public Drawer.Result getDrawerResult(){
        return drawerResult;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("PiKaraConsole", "ababa");
        super.onCreate(savedInstanceState);
        Log.v("PiKaraConsole", "babaaba");
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, (Fragment) new MainFragment())
                    .commit();
        }
        Log.v("PiKaraConsole", "yubaaba");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(false);
        //toolbar.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        this.drawerlistener = emptylistener;
        drawer = new Drawer().withActivity(this)
                //.withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                //.withDisplayBelowToolbar(true)
                //.withHeader(drawer_header)
                .withHeader(R.layout.drawer_header)
                .withHeaderDivider(true)
                .addDrawerItems(
                        //new PrimaryDrawerItem().setEnabled(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_home).withIcon(FontAwesome.Icon.faw_home),
                        new SectionDrawerItem().withName(R.string.drawer_song),
                        new SecondaryDrawerItem().withName(R.string.drawer_search).withIcon(FontAwesome.Icon.faw_search),
                        new SecondaryDrawerItem().withName(R.string.drawer_history).withIcon(FontAwesome.Icon.faw_history),
                        new SecondaryDrawerItem().withName(R.string.drawer_new).withIcon(FontAwesome.Icon.faw_asterisk),
                        new SecondaryDrawerItem().withName(R.string.drawer_artist).withIcon(FontAwesome.Icon.faw_male),
                        new SecondaryDrawerItem().withName(R.string.drawer_genre).withIcon(FontAwesome.Icon.faw_music),
                        new SectionDrawerItem().withName(R.string.drawer_other),
                        new SecondaryDrawerItem().withName(R.string.drawer_setting_pikara).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_setting_application).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_info).withIcon(FontAwesome.Icon.faw_info)
                ).withSelectedItem(1);
        drawer.withOnDrawerListener(new Drawer.OnDrawerListener() {
            @Override
            public void onDrawerOpened(View view) {
                Log.v("PiKaraConsole","openedaho");
                drawerlistener.onDrawerOpened();
            }

            @Override
            public void onDrawerClosed(View view) {
                Log.v("PiKaraConsole","closedaho");
               drawerlistener.onDrawerClosed();
            }
        });
        //drawer.withAccountHeader(drawer_ah.build());
        drawerResult = drawer.build();

       viewDrawerHeaderRoof = findViewById(R.id.drawer_header_roof);
       viewDrawerHeaderRoof.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              Log.v("PiKaraConsole","makeconnection");
           }
       });
    }

    public interface OnMyDrawerListenerInterface extends EventListener {
        public void onDrawerClosed();
        public void onDrawerOpened();
    }

    OnMyDrawerListenerInterface emptylistener = new OnMyDrawerListenerInterface() {
        @Override
        public void onDrawerClosed() {

        }

        @Override
        public void onDrawerOpened() {

        }
    };

    public void setOnMyDrawerListener(OnMyDrawerListenerInterface listener){
        this.drawerlistener = listener;
    }
    public void removeOnMyDrawerListener(){
        this.drawerlistener = emptylistener;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }*/
}
