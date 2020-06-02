package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemAtributo {
    @SerializedName("itens")
    @Expose
    private List<Item> itensatributos;

    public List<Item> getItensatributos() {
        return itensatributos;
    }
}
