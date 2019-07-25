package com.example.ana.diary;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    Integer[] fruits = {R.mipmap.pic_jackfruit_fav_round, R.mipmap.pic_strawberry_fav_round, R.mipmap.pic_starfruit_fav_round,
            R.mipmap.pic_persimmon_round};

    String[] fruits_names = {"Jackfruit", "Strawberry", "Starfruit", "Persimmon"};

    String[] fruits_order = {"first", "second", "third", "fourth"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setActionBarTitle("Ana's Diary");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("EXIT");
                builder.setMessage("Are you sure to exit?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        FloatingActionButton fab_returnmain = findViewById(R.id.fab_returnmain);
        fab_returnmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActionBarTitle("Ana's Diary");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMain()).commit();
            }
        });

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMain()).commit();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_aboutme) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutMeFragment()).commit();
        } else if (id == R.id.nav_favfruit) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavFruitFragment()).commit();
        } else if (id == R.id.nav_favbook) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavBookFragment()).commit();
        } else if (id == R.id.nav_share) {
            Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this,"Send",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_returnmain) {
            setActionBarTitle("Ana's Diary");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMain()).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
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

    //buttons methods
    public void openAboutMe(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutMeFragment()).commit();
    }

    public void openFavFruit(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavFruitFragment()).commit();
    }

    public void openFavBook(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavBookFragment()).commit();
    }

    int fruitCount = 0;
    public void changeFruit(View view) {

        int rotateFruit = fruitCount%4;

        ImageView imageView = findViewById(R.id.img_favFruit);
        TextView textView = findViewById(R.id.name_favFruit);

        imageView.setImageResource(fruits[rotateFruit]);
        textView.setText(fruits_names[rotateFruit]);

        fruitCount++;
    }

    public void setActionBarTitle(String title) {

        getSupportActionBar().setTitle(title);
    }
}
