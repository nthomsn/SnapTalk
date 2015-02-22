package nickthomson.me.snaptalk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private int resource;
    private Contact[] contacts;

    public ContactAdapter(Context context, int resource, Contact[] contacts) {
        super(context, resource, contacts);
        this.context = context;
        this.resource = resource;
        this.contacts = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);
        TextView name = (TextView) row.findViewById(R.id.contact_name);

        name.setText(contacts[position].getName());
        return row;
    }
}
