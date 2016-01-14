package neuhoff.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText zip;
    private Button button;
   // private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // preferences = this.getSharedPreferences("default", MODE_PRIVATE);

        zip = (EditText) findViewById(R.id.inputZip);
        button = (Button) findViewById(R.id.cont);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent();
            }
        });
    }

    private void startIntent() {
        Intent intent = new Intent(this, WeatherActivity.class);
        int zip = Integer.parseInt(this.zip.getText().toString());
        intent.putExtra("Zip", zip);
        startActivity(intent);
    }

   /* @Override
    protected void onPause() {
        super.onPause();
        //first we want to get values in editText to be able to save them
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("ZIPCODE", zip.getText().toString());
        editor.apply();
    }*/

}
