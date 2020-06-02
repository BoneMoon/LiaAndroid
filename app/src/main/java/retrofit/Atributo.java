package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atributo {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("marca")
    @Expose
    private String marca;

    @SerializedName("modelo")
    @Expose
    private String modelo;

    @SerializedName("serial_number")
    @Expose
    private Integer serial_number;

    @SerializedName("fotografia_caminho")
    @Expose
    private String fotogradia_caminho;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    public Atributo(String marca, String modelo, String descricao){
        this.marca = marca;
        this.modelo = modelo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(Integer serial_number) {
        this.serial_number = serial_number;
    }

    public String getFotogradia_caminho() {
        return fotogradia_caminho;
    }

    public void setFotogradia_caminho(String fotogradia_caminho) {
        this.fotogradia_caminho = fotogradia_caminho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
