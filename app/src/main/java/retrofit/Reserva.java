package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * The type Reserva.
 */
public class Reserva {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("razao_pedido")
    @Expose
    private String razao_pedido;

    @SerializedName("data_inicio")
    @Expose
    private String data_inicio;

    @SerializedName("data_fim")
    @Expose
    private String data_fim;

    @SerializedName("curso_disciplina")
    @Expose
    private String curso_disciplina;

    /**
     * Instantiates a new Reserva.
     *
     * @param razao_pedido     the razao pedido
     * @param data_inicio      the data inicio
     * @param data_fim         the data fim
     * @param curso_disciplina the curso disciplina
     */
    public Reserva(String razao_pedido, String data_inicio, String data_fim, String curso_disciplina){
        this.razao_pedido = razao_pedido;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.curso_disciplina = curso_disciplina;
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
     * Gets razao pedido.
     *
     * @return the razao pedido
     */
    public String getRazao_pedido() {
        return razao_pedido;
    }

    /**
     * Gets data inicio.
     *
     * @return the data inicio
     */
    public String getData_inicio() {
        return data_inicio;
    }

    /**
     * Gets data fim.
     *
     * @return the data fim
     */
    public String getData_fim() {
        return data_fim;
    }

    /**
     * Gets curso disciplina.
     *
     * @return the curso disciplina
     */
    public String getCurso_disciplina() {
        return curso_disciplina;
    }
}
