package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lia.R;

import java.util.List;
import retrofit.Kit;

/**
 * Custom adapter kit for List<Kit>
 */
public class CustomAdapterKit extends ArrayAdapter<Kit> {

    /**
     * Instantiates a new Custom adapter kit
     *
     * Para se fazer um  Custom adapter temos que em primeiro
     * lugar que ir buscar o layout que se quer associar, que neste
     * caso é o layout_kit.
     *
     * Logo depois ir-se buscar a esse mesmo layout os ids dos vários
     * TextView e depois associá-los aos atributos que estão na lista
     *
     * @param context the context
     * @param kits    the kits
     */
    public CustomAdapterKit(Context context, List<Kit> kits) {
        super(context, 0, kits);
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Kit k = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_kit, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.ipvc)).setText(k.getId_ipvc());
        ((TextView) convertView.findViewById(R.id.nomeKit)).setText(k.getName());

        return convertView;
    }
}
