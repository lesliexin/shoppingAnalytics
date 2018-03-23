package live.cibc.shoppinganalytics;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import live.cibc.shoppinganalytics.recommender.API;
import live.cibc.shoppinganalytics.recommender.Recommendation;
import live.cibc.shoppinganalytics.recommender.SavingsDisplayObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Spinner spinner;
    private Spinner futureTime;
    TextView retailer1, sale1, confidence1, weeks1;
    TextView retailer2, sale2, confidence2, weeks2;
    TextView retailer3, sale3, confidence3, weeks3;
    private static final String[] options = new String[]{"Clothing", "Food", "Dining", "Entertainment", "Other"};
    private static final String[] times = new String[]{"2 Weeks","4 Weeks", "6 Weeks", "8 Weeks"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        spinner = (Spinner)findViewById(R.id.spinner);
        futureTime = (Spinner)findViewById(R.id.time);

        retailer1 = (TextView)findViewById(R.id.retailer1);
        confidence1 = (TextView)findViewById(R.id.confidence1);
        sale1 = (TextView)findViewById(R.id.sale1);
        weeks1 = (TextView)findViewById(R.id.weeks1);

        ArrayAdapter<String>adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,options);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,times);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        futureTime.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(onItemSelectedListener);

    }

    AdapterView.OnItemSelectedListener onItemSelectedListener =
            new AdapterView.OnItemSelectedListener(){

                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int position, long id) {

                    String text = spinner.getSelectedItem().toString();
                    String text2 = futureTime.getSelectedItem().toString();

//                    SavingsDisplayObject response = API.requestSavings(2);
                    if (text.equalsIgnoreCase("Clothing")){
                        retailer1.setText("ZARA");
                        sale1.setText("40%");
                        confidence1.setText("30%");
                        weeks1.setText("1 Week");

//                        retailer1.setText(response.clothing.get(0).retailerName);
//                        sale1.setText(response.clothing.get(0).weeksFromNow);
//                        sale1.setText(response.clothing.get(0).percentBelowAvg);

//                        retailer1.setText(response.clothing.get(1).retailerName);
//                        sale1.setText(response.clothing.get(1).weeksFromNow);
//                        sale1.setText(response.clothing.get(1).percentBelowAvg);

//                        retailer1.setText(response.clothing.get(2).retailerName);
//                        sale1.setText(response.clothing.get(2).weeksFromNow);
//                        sale1.setText(response.clothing.get(2).percentBelowAvg);
                    }
//                    else if (text.equalsIgnoreCase("Food")){
//                        retailer1.setText(response.groceries.get(0).retailerName);
//                        sale1.setText(response.clothing.get(0).weeksFromNow);
//                        sale1.setText(response.clothing.get(0).percentBelowAvg);

//                        retailer1.setText(response.clothing.get(1).retailerName);
//                        sale1.setText(response.clothing.get(1).weeksFromNow);
//                        sale1.setText(response.clothing.get(1).percentBelowAvg);

//                        retailer1.setText(response.clothing.get(2).retailerName);
//                        sale1.setText(response.clothing.get(2).weeksFromNow);
//                        sale1.setText(response.clothing.get(2).percentBelowAvg);
//                    }
//                    else if (text.equalsIgnoreCase("Dining")){
//                        retailer1.setText(response.dining.get(0).retailerName);
//                        sale1.setText(response.clothing.get(0).weeksFromNow);
//                        sale1.setText(response.clothing.get(0).percentBelowAvg);

//                        retailer1.setText(response.clothing.get(1).retailerName);
//                        sale1.setText(response.clothing.get(1).weeksFromNow);
//                        sale1.setText(response.clothing.get(1).percentBelowAvg);

//                        retailer1.setText(response.clothing.get(2).retailerName);
//                        sale1.setText(response.clothing.get(2).weeksFromNow);
//                        sale1.setText(response.clothing.get(2).percentBelowAvg);
//                    }
//                    else {
//                        retailer1.setText(response.other.get(0).retailerName);
//                        sale1.setText(response.clothing.get(0).weeksFromNow);
//                        sale1.setText(response.clothing.get(0).percentBelowAvg);

//                        retailer1.setText(response.clothing.get(1).retailerName);
//                        sale1.setText(response.clothing.get(1).weeksFromNow);
//                        sale1.setText(response.clothing.get(1).percentBelowAvg);

//                        retailer1.setText(response.clothing.get(2).retailerName);
//                        sale1.setText(response.clothing.get(2).weeksFromNow);
//                        sale1.setText(response.clothing.get(2).percentBelowAvg);
//                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            };

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
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return true;
    }
}
