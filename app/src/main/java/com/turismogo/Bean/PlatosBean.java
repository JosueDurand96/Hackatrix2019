package com.turismogo.Bean;

public class PlatosBean {

    String menu;
    String url;
    String precio;

    public PlatosBean() {
    }

    public PlatosBean(String menu, String url, String precio) {
        this.menu = menu;
        this.url = url;
        this.precio = precio;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
