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
 * Created by benzaied on 07/06/2015.
 */
public class ListAtistArrayAdapter extends ArrayAdapter<Artistprofile> {

    Context context;

    public ListAtistArrayAdapter(Context context, int resourceId, List<Artistprofile> items) {
        super(context, resourceId, items);
        this.context = context;
    }



    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Artistprofile milist = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.artistmodel, null);
            holder = new ViewHolder();

            holder.txtTitle = (TextView) convertView.findViewById(R.id.titlee);
            holder.imageView = (ImageView) convertView.findViewById(R.id.iconn);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();


            holder.txtTitle.setText(milist.getNameg());

            // holder.imageView.setImageResource(milist.getUrlg());
             if(milist.getUrlg()==null){
             holder.imageView.setImageResource(R.mipmap.ic_launcher);
             }else {

              Picasso.with(context).load(milist.getUrlg().toString()).into(holder.imageView);
             }
        }
        return convertView;





}
}
