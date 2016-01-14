package neuhoff.weather;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAsyncTask extends AsyncTask<String, Void, ExtendedWeather[]> {

    private TextView city;
    private RecyclerView recyclerView;
    private String zip;

    public WeatherAsyncTask(RecyclerView recyclerV, TextView cityName, String zipC) {
        city = cityName;
        recyclerView = recyclerV;
        zip = zipC;
    }



    @Override
    protected ExtendedWeather[] doInBackground(String... params) {

        ExtendedWeather[] weatherArray = new ExtendedWeather[2];
        weatherArray[0] = connectToNetwork0();
        weatherArray[1] = connectToNetwork1();

        return weatherArray;
    }

    @Override
    //what to do when it completes the thread
    protected void onPostExecute(ExtendedWeather[] results) {
        super.onPostExecute(results);

        city.setText(results[1].getCity().getName());

        WeatherRecyclerViewAdapter adapter = new WeatherRecyclerViewAdapter(results);
        recyclerView.setAdapter(adapter);
    }

    private ExtendedWeather connectToNetwork0(){

        ExtendedWeather currentWeather = null;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?zip=" +
                    zip + ",us&appid=b65b2b490380de20e8b12eb10dd85fb6&units=imperial");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            Gson gson = new Gson();

            currentWeather = gson.fromJson(br, ExtendedWeather.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentWeather;
    }

    private ExtendedWeather connectToNetwork1(){

        ExtendedWeather dailyWeather = null;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?zip=" +
                    zip + "&cnt=16&appid=b65b2b490380de20e8b12eb10dd85fb6&units=imperial");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            Gson gson = new Gson();

            dailyWeather = gson.fromJson(br, ExtendedWeather.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dailyWeather;
    }

}
