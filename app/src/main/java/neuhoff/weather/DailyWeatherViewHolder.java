package neuhoff.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

public class DailyWeatherViewHolder extends RecyclerView.ViewHolder {
        private TextView day;
        private TextView max;
        private TextView min;
        private ImageView icon;

        public DailyWeatherViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            max = (TextView) itemView.findViewById(R.id.maxDay);
            min = (TextView) itemView.findViewById(R.id.minDay);
            icon = (ImageView) itemView.findViewById(R.id.dailyIcon);
        }

        public void bind(ExtendedWeather extWeather, int position, Context context) {

            day.setTypeface(EasyFonts.caviarDreams(context));
            min.setTypeface(EasyFonts.caviarDreams(context));
            max.setTypeface(EasyFonts.caviarDreams(context));

            day.setText(extWeather.getList()[position].getDate());
            max.setText(extWeather.getList()[position].getTemp().getMax() + "°");
            min.setText(extWeather.getList()[position].getTemp().getMin() + "°");

            String iconNum = extWeather.getList()[position].getWeather()[0].getIcon();

            Picasso.with(context)
                    .load("http://openweathermap.org/img/w/" + iconNum + ".png")
                    .into(icon);
        }
    }