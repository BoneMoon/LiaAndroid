package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Id item kit.
 */
public class IdItemKit {
    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     * Instantiates a new Id item kit.
     *
     * @param id the id
     */
    public IdItemKit(Integer id){
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }
}
