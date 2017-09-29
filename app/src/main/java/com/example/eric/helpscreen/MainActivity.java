package com.example.eric.helpscreen;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.lang.reflect.Field;

/**
 * https://github.com/amlcurran/ShowcaseView
 *
 * https://stackoverflow.com/questions/33379121/using-showcaseview-to-target-action-bar-menu-item/36146972
 https://stackoverflow.com/questions/23126265/how-to-using-showcaseview-in-gridview-or-listview
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ShowcaseView showcase;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private int menuDrawerIndex = 0;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

//        drawer.openDrawer(Gravity.LEFT, true);



//        showcase = new ShowcaseView.Builder(this)
//                .withHoloShowcase()
//                .setTarget(new ViewTarget(((View) findViewById(R.id.nav_view))))
//                .setContentTitle("ShowcaseView")
//                .setContentText("Access this menu for available shortcuts")
//                .hideOnTouchOutside()
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "You click here", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        showcase = new ShowcaseView.Builder(this)
//                .withHoloShowcase()
//                .setTarget(new ViewTarget(((View) findViewById(R.id.fab))))
//                .setContentTitle("ShowcaseView")
//                .setContentText("This is highlighting the Home button")
//                .hideOnTouchOutside()
//                .build();
//
//        showcase.hideButton();
        help();
    }

//    https://github.com/amlcurran/ShowcaseView/tree/master/sample/src/main/java/com/github/amlcurran/showcaseview/sample
    private void help(){

        if (showcase != null) {
            showcase = null;
        }

        Field field = null;
        try {
            field = Toolbar.class.getDeclaredField("mNavButtonView");
            field.setAccessible(true);

            View navigationView1 = (View) field.get(toolbar);
            ViewTarget target = new ViewTarget(navigationView1);
            showcase = new ShowcaseView.Builder(this)
                    .withHoloShowcase()
                    .setTarget(target)
//                    .setTarget(new ViewTarget(((View) findViewById(R.id.nav_view))))
                    .setStyle(R.style.CustomShowcaseTheme2)
                    .setContentTitle("Menu Utama")
                    .setContentText("Klik menu ini untuk membuka daftar menu")
                    .replaceEndButton(R.layout.showcase_button_custom)
                    .setShowcaseEventListener(new OnShowcaseEventListener() {
                        @Override
                        public void onShowcaseViewHide(ShowcaseView showcaseView) {

                        }

                        @Override
                        public void onShowcaseViewDidHide(ShowcaseView showcaseView) {

                        }

                        @Override
                        public void onShowcaseViewShow(ShowcaseView showcaseView) {

                        }

                        @Override
                        public void onShowcaseViewTouchBlocked(MotionEvent motionEvent) {

                        }
                    })
//                    .hideOnTouchOutside()
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            menuDrawerIndex += 1;

                            switch (menuDrawerIndex) {
                                case 1:
                                    helpHome(false);
                                    break;
                                case 2:
                                    helpListDKH(false);
                                    break;
                                case 3:
                                    helpMainFab(false);
                                    break;
                                case 4:
                                     helpContextMenu(true);
//                                    helpMainFab();
                                    break;
                                default:
                                    menuDrawerIndex = 0;
                                    showcase.hide();
                            }

                        }
                    })
                    .build();

            showcase.setButtonText("Selanjutnya");
//            showcase.forceTextPosition(ShowcaseView.ABOVE);

//            https://github.com/amlcurran/ShowcaseView/blob/master/sample/src/main/java/com/github/amlcurran/showcaseview/sample/MainActivity.java
            RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            lps.addRule(RelativeLayout.CENTER_HORIZONTAL);
            int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
            lps.setMargins(margin, margin, margin, margin);
            showcase.setButtonPosition(lps);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

//    https://github.com/amlcurran/ShowcaseView/blob/master/sample/src/main/java/com/github/amlcurran/showcaseview/sample/animations/AnimationSampleActivity.java
    private void helpDrawer(final int itemIndex1, final String title, final String content) {

        drawer.openDrawer(Gravity.LEFT, true);

        NavigationMenuView navView = (NavigationMenuView) navigationView.getChildAt(0);
        ViewTarget target = new ViewTarget(navView.getChildAt(itemIndex1));
        showcase.setShowcase(target, true);

        showcase.setContentTitle(title);
        showcase.setContentText(content);

    }

    private void helpHome(boolean lastHelp) {

        drawer.openDrawer(Gravity.LEFT, true);

        NavigationMenuView navView = (NavigationMenuView) navigationView.getChildAt(0);
        ViewTarget target = new ViewTarget(navView.getChildAt(1));
        showcase.setShowcase(target, true);
        showcase.forceTextPosition(ShowcaseView.BELOW_SHOWCASE);
        showcase.setContentTitle("HOME");
        showcase.setContentText("Menu untuk melihat data collector");

        showcase.setButtonText(lastHelp ? "Tutup" : "Selanjutnya");
    }

    private void helpListDKH(boolean lastHelp) {

        drawer.openDrawer(Gravity.LEFT, true);

        NavigationMenuView navView = (NavigationMenuView) navigationView.getChildAt(0);
        ViewTarget target = new ViewTarget(navView.getChildAt(2));
        showcase.setShowcase(target, true);
        showcase.forceTextPosition(ShowcaseView.BELOW_SHOWCASE);
        showcase.setContentTitle("LIST OF ASSIGNMENT");
        showcase.setContentText("Menu untuk melihat list DKH");

        showcase.setButtonText(lastHelp ? "Tutup" : "Selanjutnya");
    }

    private void helpPaymentEntry(boolean lastHelp) {

        drawer.openDrawer(Gravity.LEFT, true);

        NavigationMenuView navView = (NavigationMenuView) navigationView.getChildAt(0);
        ViewTarget target = new ViewTarget(navView.getChildAt(3));
        showcase.setShowcase(target, true);
        showcase.forceTextPosition(ShowcaseView.ABOVE_SHOWCASE);

        showcase.setContentTitle("PAYMENT ENTRY");
        showcase.setContentText("Menu untuk melakukan penginputan konsumen Non DKH");

        showcase.setButtonText(lastHelp ? "Tutup" : "Selanjutnya");
    }

    private void helpMainFab(boolean lastHelp) {
        drawer.closeDrawers();

        ViewTarget target = new ViewTarget(((View) findViewById(R.id.fab)));
        showcase.setShowcase(target, true);

        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lps.addRule(RelativeLayout.ALIGN_LEFT);
        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
        lps.setMargins(margin, margin, margin, margin);
        showcase.setButtonPosition(lps);
        showcase.forceTextPosition(ShowcaseView.ABOVE_SHOWCASE);
//        showcase.setTextAlignment(center);
        showcase.setContentTitle("GO TO LIST DKH");
        showcase.setContentText("Klik tombol ini untuk melihat List DKH");

        showcase.setButtonText(lastHelp ? "Tutup" : "Selanjutnya");

    }

    private void helpContextMenu(boolean lastHelp) {
        drawer.closeDrawers();

//        https://stackoverflow.com/questions/33379121/using-showcaseview-to-target-action-bar-menu-item
        Target target = new Target() {
            @Override
            public Point getPoint() {
                // Get approximate position of home icon's center
                int actionBarSize = toolbar.getHeight();
                int x = toolbar.getWidth();
//                int x = actionBarSize / 2;
                int y = actionBarSize / 2;
                return new Point(x, y);
            }
        };

        showcase.setShowcase(target, true);

        RelativeLayout.LayoutParams lps = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lps.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lps.addRule(RelativeLayout.CENTER_HORIZONTAL);
        int margin = ((Number) (getResources().getDisplayMetrics().density * 12)).intValue();
        lps.setMargins(margin, margin, margin, margin);
        showcase.setButtonPosition(lps);
        showcase.forceTextPosition(ShowcaseView.BELOW_SHOWCASE);
        showcase.setContentTitle("GO TO Menu");
        showcase.setContentText("Klik tombol ini untuk bantuan lainnya");

        showcase.setButtonText(lastHelp ? "Tutup" : "Selanjutnya");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        else if (id == R.id.action_help) {
            help();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
