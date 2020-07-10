package retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * The type My reserva.
 */
public class MyReserva {
    @SerializedName("reserva")
    @Expose
    private List<Reserva> reserva;

    /**
     * Gets reserva.
     *
     * @return the reserva
     */
    public List<Reserva> getReserva() {
        return reserva;
    }
}
