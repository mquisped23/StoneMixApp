package Model;

public class MainModel {
    String nombreMaterial;
     String descripcionMaterial;
     String categoriaMaterial;
     String cantidadMaterial;
     String urlMaterial;


    public MainModel() {
    }

    public MainModel(String nombreMaterial, String descripcionMaterial, String cantidadMaterial, String urlMaterial) {
        this.nombreMaterial = nombreMaterial;
        this.descripcionMaterial = descripcionMaterial;
        this.cantidadMaterial = cantidadMaterial;
        this.urlMaterial = urlMaterial;
    }

    public MainModel(String nombreMaterial, String descripcionMaterial, String categoriaMaterial, String cantidadMaterial, String urlMaterial) {
        this.nombreMaterial = nombreMaterial;
        this.descripcionMaterial = descripcionMaterial;
        this.categoriaMaterial = categoriaMaterial;
        this.cantidadMaterial = cantidadMaterial;
        this.urlMaterial = urlMaterial;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public String getDescripcionMaterial() {
        return descripcionMaterial;
    }

    public void setDescripcionMaterial(String descripcionMaterial) {
        this.descripcionMaterial = descripcionMaterial;
    }

    public String getCategoriaMaterial() {
        return categoriaMaterial;
    }

    public void setCategoriaMaterial(String categoriaMaterial) {
        this.categoriaMaterial = categoriaMaterial;
    }

    public String getCantidadMaterial() {
        return cantidadMaterial;
    }

    public void setCantidadMaterial(String cantidadMaterial) {
        this.cantidadMaterial = cantidadMaterial;
    }

    public String getUrlMaterial() {
        return urlMaterial;
    }

    public void setUrlMaterial(String urlMaterial) {
        this.urlMaterial = urlMaterial;
    }
}
