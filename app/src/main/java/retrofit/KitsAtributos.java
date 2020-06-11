package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KitsAtributos {
    @SerializedName("kits")
    @Expose
    private List<Kit> kitsatributos;

    public List<Kit> getKitsatributos() {
        return kitsatributos;
    }
}
