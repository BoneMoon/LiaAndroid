package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {
    @SerializedName("data_entre_inicio")
    @Expose
    private String data_entre_inicio;

    @SerializedName("data_entre_fim")
    @Expose
    private String data_entre_fim;

    public Search(String data_entre_inicio, String data_entre_fim){
        this.data_entre_inicio = data_entre_inicio;
        this.data_entre_fim = data_entre_fim;
    }

    public String getData_entre_inicio() {
        return data_entre_inicio;
    }

    public String getData_entre_fim() {
        return data_entre_fim;
    }
}
