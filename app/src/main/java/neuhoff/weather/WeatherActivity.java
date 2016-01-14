package neuhoff.weather;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity {

    private ViewPager viewPager;
    //private SharedPreferences preferences;
    private ArrayList<String> locations;
    WeatherLocationsPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        Intent intent = getIntent();
        String zip = String.valueOf(intent.getIntExtra("Zip", 11210));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        locations = new ArrayList<String>();
        locations.add(zip);
        //preferences = this.getSharedPreferences("DEFAULT", MODE_PRIVATE);
        pagerAdapter = new WeatherLocationsPagerAdapter(locations, this);
        viewPager.setAdapter(pagerAdapter);
    }
}
