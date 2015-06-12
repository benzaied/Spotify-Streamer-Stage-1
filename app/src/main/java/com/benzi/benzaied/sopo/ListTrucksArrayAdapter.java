package com.benzi.benzaied.sopo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by benzaied on 11/06/2015.
 */
public class ListTrucksArrayAdapter extends ArrayAdapter<Artisttracks> {

    Context context;

    public ListTrucksArrayAdapter(Context context, int resourceId, List<Artisttracks> items) {
        super(context, resourceId, items);
        this.context = context;
    }



    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView albom;
       TextView track;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Artisttracks mil = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tracksmodel, null);
            holder = new ViewHolder();

            holder.albom = (TextView) convertView.findViewById(R.id.album);
            holder.track = (TextView) convertView.findViewById(R.id.track);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iconnn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();


            holder.albom.setText(mil.getId());
            holder.track.setText(mil.getNameg());

            // holder.imageView.setImageResource(milist.getUrlg());


                Picasso.with(context).load(mil.getUrlmin().toString()).into(holder.imageView);

        }
        return convertView;





    }
}
