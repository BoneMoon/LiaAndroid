package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Kit.
 */
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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets id ipvc.
     *
     * @return the id ipvc
     */
    public String getId_ipvc() {
        return id_ipvc;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets id atributos.
     *
     * @return the id atributos
     */
    public Integer getId_atributos() {
        return id_atributos;
    }

    /**
     * Gets categoria id.
     *
     * @return the categoria id
     */
    public Integer getCategoria_id() {
        return categoria_id;
    }

    /**
     * Gets observacoes.
     *
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }
}
