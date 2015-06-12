package com.benzi.benzaied.sopo;

/**
 * Created by benzaied on 11/06/2015.
 */
public class Artisttracks {
    private String trackname;
private String albumname;
private String urlmin;
    private String url640;



        public Artisttracks(String name, String _id, String urlmi, String url) {
            this.urlmin = urlmi;
            this.albumname=_id;
            this.trackname = name;
            this.url640 = url ;
        }



        public String getId() {
            return albumname;
        }
        public void setId(String _id) {
            this.albumname = _id;
        }

        public String getNameg() {
            return trackname;
        }
        public void setNameg(String name) {
            this.trackname = name;
        }

    public String getUrl640() {
        return url640;
    }
    public void setUrl640 (String url) {
        this.url640 = url;
    }

        public String getUrlmin() {
            return urlmin;
        }
        public void setUrlmin (String url) {
            this.urlmin = url;
        }
        @Override
        public String toString() {
            return urlmin +"\n"+trackname ;
        }
}
