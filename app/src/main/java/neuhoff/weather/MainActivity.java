package neuhoff.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SharedPreferences preferences;
    private ArrayList<String> locations;
    WeatherLocationsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);
        preferences = this.getSharedPreferences("default", MODE_PRIVATE);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        locations = new ArrayList<String>();
        pagerAdapter = new WeatherLocationsPagerAdapter(viewPager, locations, this);
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < locations.size(); i++){
            sb.append(locations.get(i) + ";");
        }
        editor.putString("ZIPS", sb.toString().trim());
        editor.apply();
    }

    @Override
     public void onResume() {
        super.onResume();
        String temp1 = preferences.getString("ZIPS", "11210");
        String[] temp2 = temp1.split(";");
        for (String s : temp2) {
            locations.add(s);
            pagerAdapter.notifyDataSetChanged();
        }
    }

}
