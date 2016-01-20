package s112011.galgelegsapp.domæne;

import java.io.Serializable;


public class OrdDTO implements Serializable {

    String ord, kategori, hint;

    // Tom konstruktør som er påkrævet
    public OrdDTO(){    }
    public OrdDTO(String ord, String kategori, String hint){
        this.ord = ord;
        this.kategori = kategori;
        this.hint = hint;
    }


    public String getOrd(){
        return ord;
    }
    public String getKategori(){
        return kategori;
    }
    public String getHint(){
        return hint;
    }
}


