package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Class Atributo.
 */
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

    /**
     * Instantiates a new Atributo.
     *
     * @param marca     the marca
     * @param modelo    the modelo
     * @param descricao the descricao
     */
    public Atributo(String marca, String modelo, String descricao){
        this.marca = marca;
        this.modelo = modelo;
        this.descricao = descricao;
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
     * Gets marca.
     *
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Sets marca.
     *
     * @param marca the marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Gets modelo.
     *
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Sets modelo.
     *
     * @param modelo the modelo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Gets serial number.
     *
     * @return the serial number
     */
    public Integer getSerial_number() {
        return serial_number;
    }

    /**
     * Sets serial number.
     *
     * @param serial_number the serial number
     */
    public void setSerial_number(Integer serial_number) {
        this.serial_number = serial_number;
    }

    /**
     * Gets fotogradia caminho.
     *
     * @return the fotogradia caminho
     */
    public String getFotogradia_caminho() {
        return fotogradia_caminho;
    }

    /**
     * Sets fotogradia caminho.
     *
     * @param fotogradia_caminho the fotogradia caminho
     */
    public void setFotogradia_caminho(String fotogradia_caminho) {
        this.fotogradia_caminho = fotogradia_caminho;
    }

    /**
     * Gets descricao.
     *
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets descricao.
     *
     * @param descricao the descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
