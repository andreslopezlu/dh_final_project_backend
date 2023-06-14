package com.dh.final_project_backend.entity;

public class Domicilio {
    private Integer id;
    private String calle;
    private Integer numero;
    private String provincia;
    private String pais;
    private Integer codigoPostal;

    public Domicilio() {
    }

    public Domicilio(String calle, Integer numero, String provincia, String pais, Integer codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.provincia = provincia;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
    }

    public Domicilio(Integer id, String calle, Integer numero, String provincia, String pais, Integer codigoPostal) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.provincia = provincia;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", provincia='" + provincia + '\'' +
                ", pais='" + pais + '\'' +
                ", codigoPostal=" + codigoPostal +
                '}';
    }
}
