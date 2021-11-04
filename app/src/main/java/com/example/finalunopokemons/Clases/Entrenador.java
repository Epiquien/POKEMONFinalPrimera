package com.example.finalunopokemons.Clases;

import com.google.gson.annotations.SerializedName;

public class Entrenador {
    @SerializedName("nombres")
    public String nombres;
    @SerializedName("pueblo")
    public String pueblo;
    @SerializedName("imagen")
    public String imagen;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPueblo() {
        return pueblo;
    }

    public void setPueblo(String pueblo) {
        this.pueblo = pueblo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
