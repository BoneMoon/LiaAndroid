package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

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

    public Reserva(String razao_pedido, String data_inicio, String data_fim, String curso_disciplina){
        this.razao_pedido = razao_pedido;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.curso_disciplina = curso_disciplina;
    }

    public Integer getId() {
        return id;
    }

    public String getRazao_pedido() {
        return razao_pedido;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }

    public String getCurso_disciplina() {
        return curso_disciplina;
    }
}
