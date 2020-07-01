package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.lia.R;

import java.util.List;

import retrofit.Reserva;

/**
 * Custom adapter reserva for List<Reserva>
 */
public class CustomAdapterReserva extends ArrayAdapter<Reserva> {

    /**
     * Instantiates a new Custom adapter reserva
     *
     * Para se fazer um  Custom adapter temos que em primeiro
     * lugar que ir buscar o layout que se quer associar, que neste
     * caso é o layout_minha_reserva.
     *
     * Logo depois ir-se buscar a esse mesmo layout os ids dos vários
     * TextView e depois associá-los aos atributos que estão na lista
     *
     * @param context  the context
     * @param reservas the reservas
     */
    public CustomAdapterReserva(Context context, List<Reserva> reservas) {
        super(context, 0, reservas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reserva r = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_minha_reserva, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.cursoDisciplina)).setText(r.getCurso_disciplina());
        ((TextView) convertView.findViewById(R.id.razao)).setText(r.getRazao_pedido());
        ((TextView) convertView.findViewById(R.id.data1)).setText(r.getData_inicio());
        ((TextView) convertView.findViewById(R.id.data2)).setText(r.getData_fim());

        return convertView;
    }
}
