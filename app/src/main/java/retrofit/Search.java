package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Search.
 */
public class Search {
    @SerializedName("data_entre_inicio")
    @Expose
    private String data_entre_inicio;

    @SerializedName("data_entre_fim")
    @Expose
    private String data_entre_fim;

    /**
     * Instantiates a new Search.
     *
     * @param data_entre_inicio the data entre inicio
     * @param data_entre_fim    the data entre fim
     */
    public Search(String data_entre_inicio, String data_entre_fim){
        this.data_entre_inicio = data_entre_inicio;
        this.data_entre_fim = data_entre_fim;
    }

    /**
     * Gets data entre inicio.
     *
     * @return the data entre inicio
     */
    public String getData_entre_inicio() {
        return data_entre_inicio;
    }

    /**
     * Gets data entre fim.
     *
     * @return the data entre fim
     */
    public String getData_entre_fim() {
        return data_entre_fim;
    }
}
