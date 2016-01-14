package neuhoff.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WeatherActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SharedPreferences preferences;
    private ArrayList<String> locations;
    WeatherLocationsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        preferences = this.getSharedPreferences("default", MODE_PRIVATE);

        Intent intent = getIntent();
        String zip = String.valueOf(intent.getIntExtra("Zip", 11210));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        locations = new ArrayList<String>();
        locations.add(zip);
        //preferences = this.getSharedPreferences("DEFAULT", MODE_PRIVATE);
        pagerAdapter = new WeatherLocationsPagerAdapter(viewPager, locations, this);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("ZIPS", (Set<String>) locations);
        editor.apply();
    }

    @Override
     public void onResume() {
        super.onResume();
        Set<String> list = preferences.getStringSet("ZIPS", new HashSet<String>());
        locations = new ArrayList<String>(list);
    }
}
