package neuhoff.weather;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ExtendedWeather currentWeather, dailyWeather ;
    private Context context;

    public WeatherRecyclerViewAdapter(ExtendedWeather[] weather) {

        currentWeather = weather[0];
        dailyWeather = weather[1];
    }

    @Override
    public int getItemViewType(int position) {

        return position == 0 ? 0 : 1;
    }

    @Override
    public int getItemCount() {

        return dailyWeather.getList().length;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater  = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        switch(viewType){
            case 0:
                View itemView =
                    inflater.inflate(R.layout.current_weather, parent, false);
                itemView.setMinimumHeight(parent.getMeasuredHeight());
                holder = new CurrentWeatherViewHolder(itemView);
                break;
            case 1:
                itemView =
                    inflater.inflate(R.layout.daily_weather, parent, false);
                holder = new DailyWeatherViewHolder(itemView);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (position == 0) {
            CurrentWeatherViewHolder current = (CurrentWeatherViewHolder) holder;
            current.bind(currentWeather, context);
        }
        else{
            DailyWeatherViewHolder daily = (DailyWeatherViewHolder) holder;
            daily.bind(dailyWeather, position, context);
        }


    }
}
