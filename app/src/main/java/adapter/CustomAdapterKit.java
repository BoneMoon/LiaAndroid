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

public class CustomAdapterKit extends ArrayAdapter<Kit> {

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
