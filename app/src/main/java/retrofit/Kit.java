package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kit {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("id_ipvc")
    @Expose
    private String id_ipvc;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("id_atributos")
    @Expose
    private Integer id_atributos;

    @SerializedName("categoria_id")
    @Expose
    private Integer categoria_id;

    @SerializedName("observacoes")
    @Expose
    private String observacoes;

    @SerializedName("atributo")
    @Expose
    private Atributo atributo;

    public Integer getId() {
        return id;
    }

    public String getId_ipvc() {
        return id_ipvc;
    }

    public String getName() {
        return name;
    }

    public Integer getId_atributos() {
        return id_atributos;
    }

    public Integer getCategoria_id() {
        return categoria_id;
    }

    public String getObservacoes() {
        return observacoes;
    }
}
