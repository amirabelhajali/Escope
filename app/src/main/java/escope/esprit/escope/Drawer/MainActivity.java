package escope.esprit.escope.Drawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

 import escope.esprit.escope.NewsRSS.NewsFragment;
import escope.esprit.escope.R;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl ;
    private ActionBarDrawerToggle toggle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = (DrawerLayout) findViewById(R.id.dl);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.flcontent,new NewsFragment(),"MyFragment").commit();


        toggle = new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);
        dl.addDrawerListener(toggle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        toggle.syncState();
        setupDrawerContent(nvDrawer);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mains, menu);
        return true;
    }

    public void selectItemDrawer(MenuItem menuItem) {
        Fragment myFragment = null;
        Class fragmentClass;


        switch (menuItem.getItemId()){


            case R.id.aboutus:
                fragmentClass = AboutUsFragment.class;
                break;

            case R.id.listpatient:
                fragmentClass = MakeAnalysisFragment.class;
                break;



            default:
                fragmentClass = AboutUsFragment.class;
        }
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent,myFragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        dl.closeDrawers();


    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

}


