package com.glaxmac.myapplication.Bean;

public class HuariqueBean {
    String nombre;
    String promociones;
    String distrito;
    String distancia;
    String mensaje;

    public HuariqueBean() {
    }

    public HuariqueBean(String nombre, String promociones, String distrito, String distancia,String mensaje) {
        this.nombre = nombre;
        this.promociones = promociones;
        this.distrito = distrito;
        this.distancia = distancia;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPromociones() {
        return promociones;
    }

    public void setPromociones(String promociones) {
        this.promociones = promociones;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
