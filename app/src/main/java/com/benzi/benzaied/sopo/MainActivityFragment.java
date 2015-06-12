package com.benzi.benzaied.sopo;

import android.content.Intent;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
 import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


import kaaes.spotify.webapi.android.SpotifyApi;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;
import kaaes.spotify.webapi.android.models.Pager;

import retrofit.RetrofitError;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnKeyListener {

    EditText nam;
    private Timer timer = new Timer();
    private final long DELAY = 1000; // in ms
    private ListAtistArrayAdapter adpa;

    public MainActivityFragment() {
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if ((event.getAction() == KeyEvent.ACTION_DOWN)
                && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            new Task().execute(nam.getText().toString());

            }
        return false;
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);

        nam = (EditText) v.findViewById(R.id.artisname);
        nam.setOnKeyListener(this);


                adpa = new ListAtistArrayAdapter(getActivity(), R.id.action0, new ArrayList<Artistprofile>());


        ListView recherche = (ListView) v.findViewById(R.id.listView);
        recherche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Artistprofile m=adpa.getItem(position);
                Intent i = new Intent(getActivity(), ListTopTrackActivity.class);
                i.putExtra(Intent.EXTRA_TEXT,m.getId());
                startActivity(i);
            }
        });


        recherche.setAdapter(adpa);


            return v;


    }
//----------------------------------------------------------------------------------------------------------------------
    public class Task extends AsyncTask<String, Void, Artistprofile[]> {

        private Artistprofile[] upd(String params) {
            Artistprofile[] mmm;
            SpotifyApi api = new SpotifyApi();
            // api.setAccessToken("myAccessToken");
            SpotifyService spotify = api.getService();
            //  try {
            ArtistsPager aric = spotify.searchArtists(params );


            Pager<Artist> artist = aric.artists;

            List<Artist> ListArtist = artist.items;

            mmm = new Artistprofile[ListArtist.size()];

            for (int c = 0; c < ListArtist.size(); c++) {
                Artist w = ListArtist.get(c);
                List<Image> Listimage = w.images;

                if (Listimage.size() > 0) {
                    Image n = Listimage.get(0);
                    for (Image s : Listimage) {
                        if (s.width < n.width) {
                            n = s;
                        }
                    }

                    Artistprofile mo = new Artistprofile(w.name,w.id ,n.url);
                    Log.d("Artists url", n.url);

                    mmm[c] = mo;


                } else {
                    Artistprofile mo = new Artistprofile(w.name,w.id, null);
                    mmm[c] = mo;

                }
            }


            Log.d("Artists success", "ali" + mmm.length);
            return mmm;


        }


        @Override
        protected Artistprofile[] doInBackground(String... params) {

            try {
                return upd(params[0]);
            } catch (RetrofitError e) {
                Log.e("LOG_TAG", e.getMessage(), e);
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Artistprofile[] strings) {

            if (strings != null) {
                if (strings.length==1) {

                     LinearLayout holder = (LinearLayout) getActivity().findViewById(R.id.witlis);

                    holder.removeAllViews();

                    holder.addView(View.inflate(getActivity(), R.layout.oneresulartisit, null));
                    ImageView ico=(ImageView)holder.findViewById(R.id.icon);

                    Picasso.with(getActivity()).load(strings[0].getUrlg() ).into(ico);
                    TextView g=(TextView) holder.findViewById(R.id.title);
                    g.setText(strings[0].getNameg());

                    MainActivity.ID=strings[0].getId();

                } else if(strings.length==0){
                    Toast.makeText(getActivity(),"No artist has been found",
                            Toast.LENGTH_SHORT).show();
                    Log.d("strings is null", "vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
                }
                else {

                    Log.d("Artisbbbbbbbood", "aille" + strings.length);
                    adpa.clear();
                    for (Artistprofile d : strings) {
                        adpa.add(d);

                    }
                    Log.d("strings no null", " vvv");
                    Log.d("strings  bbb", " nobbre" + adpa.getCount());

                }
            }

        }
    }




}