package com.simoes.mario.brsservice.Classes;

import android.graphics.Bitmap;
import android.net.Uri;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Mário on 27/09/2016.
 */
@IgnoreExtraProperties
public class Ocorrencia {
    private String uid;
    private Uri downloadUrl;
    private Bitmap bitmapFoto;
    private String endereco;
    private int latitude;
    private int longitude;
    private boolean aberta;

    public Ocorrencia() {
    }

    public Ocorrencia(String uid, String endereco, Boolean aberta) {
        this.uid = uid;
        this.endereco = endereco;
        this.aberta = aberta;
    }


    public String getUid() {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }

    public Bitmap getBitmapFoto() {
        return bitmapFoto;
    }

    public void setBitmapFoto(Bitmap bitmapFoto) {
        this.bitmapFoto = bitmapFoto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public boolean isAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public Uri getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(Uri downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
