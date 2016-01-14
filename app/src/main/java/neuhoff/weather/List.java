package neuhoff.weather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class List {
    private int dt;
    private Temp temp;
    private Weather[] weather;

    public String getDate() {
        return new SimpleDateFormat("EEEE").format(new Date(dt * 1000L));
    }

    public Temp getTemp() {
        return temp;
    }

    public Weather[] getWeather() {
        return weather;
    }
}