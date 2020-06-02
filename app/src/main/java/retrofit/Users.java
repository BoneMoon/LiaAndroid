package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("grupo_id")
    @Expose
    private Integer grupo_id;

    @SerializedName("numero_mecanografico")
    @Expose
    private String numero_mecanografico;

    @SerializedName("numero_telemovel")
    @Expose
    private String numero_telemovel;

    @SerializedName("api_token")
    @Expose
    private String api_token;

    public Users(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(Integer grupo_id) {
        this.grupo_id = grupo_id;
    }

    public String getNumero_mecanografico() {
        return numero_mecanografico;
    }

    public void setNumero_mecanografico(String numero_mecanografico) {
        this.numero_mecanografico = numero_mecanografico;
    }

    public String getNumero_telemovel() {
        return numero_telemovel;
    }

    public void setNumero_telemovel(String numero_telemovel) {
        this.numero_telemovel = numero_telemovel;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
