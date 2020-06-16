package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class linhaCarrinho {
    @SerializedName("itens")
    @Expose
    private List<Item> itens;

    @SerializedName("kits")
    @Expose
    private List<Kit> kits;

    public List<Item> getItens() {
        return itens;
    }

    public List<Kit> getKits() {
        return kits;
    }
}
