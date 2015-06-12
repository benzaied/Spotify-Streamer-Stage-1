package com.benzi.benzaied.sopo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;
import retrofit.RetrofitError;


/**
 * A placeholder fragment containing a simple view.
 */
public class ListTopTrackFragment extends Fragment {
    private ListTrucksArrayAdapter adaper;

    public ListTopTrackFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vv= inflater.inflate(R.layout.fragment_lis_top_tracks, container, false);
SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getActivity());
        String coucode=pref.getString("example_text","US");
        adaper=new ListTrucksArrayAdapter(getActivity(), R.id.action0, new ArrayList<Artisttracks>());

        Intent ii=getActivity().getIntent();

        if((ii!=null)&&(ii.hasExtra(Intent.EXTRA_TEXT))){

            new Task2().execute(ii.getStringExtra(Intent.EXTRA_TEXT),coucode);
        }

        ListView lis=(ListView)vv.findViewById(R.id.listView2);
        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Intent ii=new Intent(getActivity(),Liremusic.class);
               // ii.putExtra();
               // startActivity(ii)
            }
        });
        lis.setAdapter(adaper);
        return vv;
    }



    //----------------------------------------------------------------------------------------------------------------------
    public class Task2 extends AsyncTask<String, Void, Artisttracks[]> {

        private Artisttracks[] upd(String params, String param2) {

            Map testMap = new HashMap<String,String>();
            testMap.put("country", param2);

            Artisttracks[] mmm;
            SpotifyApi api = new SpotifyApi();

             SpotifyService spotify = api.getService();

             Tracks pag= spotify.getArtistTopTrack(params,testMap);

            List<Track> mtracks=pag.tracks;




            mmm = new Artisttracks[mtracks.size()];

            for (int c = 0; c < mtracks.size(); c++) {



               // String track_name=mtracks.get(c).name;

                //String album_name=mtracks.get(c).album.name;

                List<Image> Listimage = mtracks.get(c).album.images;
                Image min = Listimage.get(0);
                if (Listimage.size() > 0) {

                    for (Image s : Listimage) {
                        if (s.width < min.width) {
                            min = s;
                        }
                    }
                }
                Image max = Listimage.get(0);
                    if (Listimage.size() > 0) {

                        for (Image s : Listimage) {
                            if ((s.width == 640) || (s.height == 640)) {
                                max = s;
                            } else if (s.width > max.width) {

                                max = s;
                            }
                        }
                    }
                        Artisttracks mo = new Artisttracks(mtracks.get(c).name
                                ,mtracks.get(c).album.name ,min.url,max.url);
                    Log.d("Artists url", min.url);

                    mmm[c] = mo;



            }


            Log.d("Artists success", "ali" + mmm.length);
            return mmm;


        }


        @Override
        protected Artisttracks[] doInBackground(String... params) {

            try {
                return upd(params[0],params[1]);
            } catch (RetrofitError e) {
                Log.e("LOG_TAG", e.getMessage(), e);
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Artisttracks[] strings) {

            if (strings != null) {
                if (strings.length==1) {

                    LinearLayout holder = (LinearLayout) getActivity().findViewById(R.id.hh);

                    holder.removeAllViews();

                    holder.addView(View.inflate(getActivity(), R.layout.oneresultracks, null));
                    ImageView ico=(ImageView)holder.findViewById(R.id.iconnn);

                    TextView gg=(TextView) holder.findViewById(R.id.albumm);
                    gg.setText(strings[0].getId());

                    TextView g=(TextView) holder.findViewById(R.id.trackk);
                    g.setText(strings[0].getNameg());

                    Picasso.with(getActivity()).load(strings[0].getUrlmin() ).into(ico);


                } else if(strings.length==0){
                    Toast.makeText(getActivity(),"No artist has been found",
                            Toast.LENGTH_SHORT).show();
                    Log.d("strings is null", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
                }

                else {

                    Log.d("Artisbbbbbbbood", "aille" + strings.length);
                    adaper.clear();
                    for (Artisttracks d : strings) {
                        adaper.add(d);

                    }
                    Log.d("strings no null", " vvv");
                    Log.d("strings  bbb", " nobbre" + adaper.getCount());

                }
            }

        }
    }


}
