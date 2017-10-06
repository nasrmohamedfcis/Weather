package com.example.nasrf.weather.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nasrf.weather.R;
import com.example.nasrf.weather.Temperature.UpcomingDaysTemp;

import java.util.List;

/**
 * Created by nasrf on 5/10/2017.
 */

public class Adapter extends BaseAdapter {

    private Context context;
    private List<UpcomingDaysTemp> upcomingDaysTemps;

    public Adapter(Context context, List<UpcomingDaysTemp> upcomingDaysTemps) {
        this.context = context;
        this.upcomingDaysTemps = upcomingDaysTemps;
    }

    @Override
    public int getCount() {
        return upcomingDaysTemps.size();
    }

    @Override
    public Object getItem(int position) {
        return upcomingDaysTemps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.list_view,null);

        TextView day = (TextView) v.findViewById(R.id.tv_lvUpComingDays);
        TextView date = (TextView) v.findViewById(R.id.tv_lvUpComingDates);
        TextView forcast = (TextView) v.findViewById(R.id.lv_tvUpcomingTemp);
        TextView condition = (TextView) v.findViewById(R.id.lv_tvUpcomingCondition);

        //getting
        day.setText(upcomingDaysTemps.get(position).getDay());
        date.setText(upcomingDaysTemps.get(position).getDate());
        forcast.setText("forcast: "+upcomingDaysTemps.get(position).getHigh()+"/"+upcomingDaysTemps.get(position).getLow());
        condition.setText(upcomingDaysTemps.get(position).getCondition());
        return v;
    }
}
