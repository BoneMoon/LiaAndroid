package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Item.
 */
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

    /**
     * Instantiates a new Item.
     *
     * @param id           the id
     * @param name         the name
     * @param id_atributos the id atributos
     * @param observacoes  the observacoes
     */
    public Item(Integer id, String name, Integer id_atributos, String observacoes){
        this.id = id;
        this.name = name;
        this.id_atributos = id_atributos;
        this.observacoes = observacoes;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * Sets id ipvc.
     *
     * @param id_ipvc the id ipvc
     */
    public void setId_ipvc(String id_ipvc) {
        this.id_ipvc = id_ipvc;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets categoria id.
     *
     * @param categoria_id the categoria id
     */
    public void setCategoria_id(Integer categoria_id) {
        this.categoria_id = categoria_id;
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
     * Sets id atributos.
     *
     * @param id_atributos the id atributos
     */
    public void setId_atributos(Integer id_atributos) {
        this.id_atributos = id_atributos;
    }

    /**
     * Gets observacoes.
     *
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * Sets observacoes.
     *
     * @param observacoes the observacoes
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
