package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Linha carrinho.
 */
public class linhaCarrinho {
    @SerializedName("itens")
    @Expose
    private List<Item> itens;

    @SerializedName("kits")
    @Expose
    private List<Kit> kits;

    /**
     * Gets itens.
     *
     * @return the itens
     */
    public List<Item> getItens() {
        return itens;
    }

    /**
     * Gets kits.
     *
     * @return the kits
     */
    public List<Kit> getKits() {
        return kits;
    }
}
