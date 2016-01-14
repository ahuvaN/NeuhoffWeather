package neuhoff.weather;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.vstechlab.easyfonts.EasyFonts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherLocationsPagerAdapter extends PagerAdapter {
    private ArrayList<String> locations;
    private Context context;
    private TextView city, time;
    private ImageView bkgd;
    private EditText newZip;
    private Button add;

    public WeatherLocationsPagerAdapter(ArrayList<String> list, Context parent) {
        locations = list;
        context = parent;

    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.weather_pager_adapter, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.weatherCity);
        recyclerView.setLayoutManager(layoutManager);

        bkgd = (ImageView) view.findViewById(R.id.background);
        city = (TextView) view.findViewById(R.id.city);
        time = (android.widget.TextClock) view.findViewById(R.id.time);
        add = (Button) view.findViewById(R.id.plus);

        add.setBackgroundResource(R.drawable.add);
        add.getBackground().setAlpha(95);

        city.setTypeface(EasyFonts.caviarDreamsItalic(context));
        time.setTypeface(EasyFonts.caviarDreamsBold(context));

        SimpleDateFormat formatter = new SimpleDateFormat("EEEE hh:mm:ss aa");
        Date today = new Date();


        time.setText(formatter.format(today).toString());


        Picasso.with(context).load("http://lorempixel.com/600/825/nature/").memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE).into(bkgd);


        WeatherAsyncTask task = new WeatherAsyncTask(recyclerView, city, locations.get(position));
        task.execute();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dialogInflater = LayoutInflater.from(context);
                View dialogView = dialogInflater.inflate(R.layout.new_zip, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Enter Zipcode:");
                builder.setView(dialogView);
                newZip = (EditText) dialogView.findViewById(R.id.newZip);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        locations.add(String.valueOf(newZip.getText()));
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}
