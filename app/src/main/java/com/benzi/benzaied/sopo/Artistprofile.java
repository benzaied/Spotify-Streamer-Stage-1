package com.benzi.benzaied.sopo;

/**
 * Created by benzaied on 07/06/2015.
 */
public class Artistprofile {
private String nameg;
    private String id;
private String urlg;



        public Artistprofile(String name,String _id ,String url) {
            this.urlg = url;
            this.id=_id;
            this.nameg = name;

        }



    public String getId() {
        return id;
    }
    public void setId(String _id) {
        this.id = _id;
    }

        public String getNameg() {
            return nameg;
        }
        public void setNameg(String name) {
            this.nameg = name;
        }


        public String getUrlg() {
            return urlg;
        }
        public void setUrlg (String url) {
            this.urlg = url;
        }
        @Override
        public String toString() {
            return urlg +"\n"+nameg ;
        }
}
