package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyReserva {
    @SerializedName("reserva")
    @Expose
    private List<Reserva> reserva;

    public List<Reserva> getReserva() {
        return reserva;
    }
}
