package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lia.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Item;
import retrofit2.Callback;

/**
 * Custom adapter for List<Item>
 *
 * Esta classe serve para se fazer um custom adapter para
 * a lista que nós quisermos
 */
public class CustomAdapter extends ArrayAdapter<Item> {

    /**
     * Instantiates a new Custom adapter
     *
     * Para se fazer um  Custom adapter temos que em primeiro
     * lugar que ir buscar o layout que se quer associar, que neste
     * caso é o layout_item.
     *
     * Logo depois ir-se buscar a esse mesmo layout os ids dos vários
     * TextView e depois associá-los aos atributos que estão na lista
     *
     * @param context the context
     * @param items   the items
     */
    public CustomAdapter(Context context, List<Item> items){
        super(context, 0, items);
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Item i = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_item, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.ipvc)).setText(i.getId_ipvc());
        ((TextView) convertView.findViewById(R.id.nomeItem)).setText(i.getName());

        return convertView;
    }
}
