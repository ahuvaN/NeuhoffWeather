package neuhoff.weather;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

public class CurrentWeatherViewHolder extends RecyclerView.ViewHolder {

    private TextView desc, temp, max, min;
    private ImageView icon;


    public CurrentWeatherViewHolder(View itemView){
        super(itemView);
        temp = (TextView) itemView.findViewById(R.id.temp);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        desc = (TextView) itemView.findViewById(R.id.desc);
        max = (TextView) itemView.findViewById(R.id.maxCurrent);
        min = (TextView) itemView.findViewById(R.id.minCurrent);

    }

    public void bind(ExtendedWeather extWeather, Context context) {

        desc.setTypeface(EasyFonts.caviarDreamsBoldItalic(context));
        temp.setTypeface(EasyFonts.caviarDreams(context));
        max.setTypeface(EasyFonts.caviarDreamsBold(context));
        min.setTypeface(EasyFonts.caviarDreamsBold(context));

        int x = (int) extWeather.getMain().getTemp();
        temp.setText(String.valueOf(x) + "°");

        String iconNum = extWeather.getWeather()[0].getIcon();

        Picasso.with(context)
                .load("http://openweathermap.org/img/w/" + iconNum + ".png")
                .into(icon);

        desc.setText(extWeather.getWeather()[0].getDescription());
        max.setText(extWeather.getMain().getMax() + "°");
        min.setText(extWeather.getMain().getMin() + "°");


    }
}