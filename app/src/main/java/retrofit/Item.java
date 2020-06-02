package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("id_ipvc")
    @Expose
    private String id_ipvc;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("categoria_id")
    @Expose
    private Integer categoria_id;

    @SerializedName("id_atributos")
    @Expose
    private Integer id_atributos;

    @SerializedName("observacoes")
    @Expose
    private String observacoes;

    @SerializedName("atributo")
    @Expose
    private Atributo atributo;

    public Item(String name, Integer id_atributos, String observacoes){
        this.name = name;
        this.id_atributos = id_atributos;
        this.observacoes = observacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getId_ipvc() {
        return id_ipvc;
    }

    public void setId_ipvc(String id_ipvc) {
        this.id_ipvc = id_ipvc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Integer getId_atributos() {
        return id_atributos;
    }

    public void setId_atributos(Integer id_atributos) {
        this.id_atributos = id_atributos;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
