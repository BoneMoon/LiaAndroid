package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type Kits atributos.
 */
public class KitsAtributos {
    @SerializedName("kits")
    @Expose
    private List<Kit> kitsatributos;

    /**
     * Gets kitsatributos.
     *
     * @return the kitsatributos
     */
    public List<Kit> getKitsatributos() {
        return kitsatributos;
    }
}
