package nickthomson.me.snaptalk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainAdapter extends ArrayAdapter<ListItemData> {

    private Context context;
    private int resource;
    private ListItemData[] objects;

    public MainAdapter(Context context, int resource, ListItemData[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);
        TextView title = (TextView) row.findViewById(R.id.title);
        TextView number = (TextView) row.findViewById(R.id.number);

        title.setText((CharSequence) objects[position].myTitle);
        number.setText((CharSequence)
                Integer.toString(objects[position].myNum));
        return row;
    }
}
