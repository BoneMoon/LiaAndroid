package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Kit id.
 */
public class KitId {
    @SerializedName("itens")
    @Expose
    private List<Item> itens;

    @SerializedName("atributos")
    @Expose
    private Atributo atributo;

    /**
     * Gets itens.
     *
     * @return the itens
     */
    public List<Item> getItens() {
        return itens;
    }

    /**
     * Gets atributo.
     *
     * @return the atributo
     */
    public Atributo getAtributo() {
        return atributo;
    }
}
