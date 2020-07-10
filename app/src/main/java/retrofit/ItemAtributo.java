package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Item atributo.
 */
public class ItemAtributo {
    @SerializedName("itens")
    @Expose
    private List<Item> itensatributos;

    /**
     * Gets itensatributos.
     *
     * @return the itensatributos
     */
    public List<Item> getItensatributos() {
        return itensatributos;
    }
}
