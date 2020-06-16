package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IdItemKit {
    @SerializedName("id")
    @Expose
    private Integer id;

    public IdItemKit(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
