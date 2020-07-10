package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Users.
 */
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

    /**
     * Instantiates a new Users.
     *
     * @param email    the email
     * @param password the password
     */
    public Users(String email, String password){
        this.email = email;
        this.password = password;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets grupo id.
     *
     * @return the grupo id
     */
    public Integer getGrupo_id() {
        return grupo_id;
    }

    /**
     * Sets grupo id.
     *
     * @param grupo_id the grupo id
     */
    public void setGrupo_id(Integer grupo_id) {
        this.grupo_id = grupo_id;
    }

    /**
     * Gets numero mecanografico.
     *
     * @return the numero mecanografico
     */
    public String getNumero_mecanografico() {
        return numero_mecanografico;
    }

    /**
     * Sets numero mecanografico.
     *
     * @param numero_mecanografico the numero mecanografico
     */
    public void setNumero_mecanografico(String numero_mecanografico) {
        this.numero_mecanografico = numero_mecanografico;
    }

    /**
     * Gets numero telemovel.
     *
     * @return the numero telemovel
     */
    public String getNumero_telemovel() {
        return numero_telemovel;
    }

    /**
     * Sets numero telemovel.
     *
     * @param numero_telemovel the numero telemovel
     */
    public void setNumero_telemovel(String numero_telemovel) {
        this.numero_telemovel = numero_telemovel;
    }

    /**
     * Gets api token.
     *
     * @return the api token
     */
    public String getApi_token() {
        return api_token;
    }

    /**
     * Sets api token.
     *
     * @param api_token the api token
     */
    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
