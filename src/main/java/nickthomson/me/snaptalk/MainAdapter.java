package nickthomson.me.snaptalk;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainAdapter extends ArrayAdapter<RecievedMessage> {

    private Context context;
    private int resource;
    private ArrayList<RecievedMessage> objects;

    private boolean playing = false;

    public MainAdapter(Context context, int resource, RecievedMessage[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = new ArrayList<RecievedMessage>(Arrays.asList(objects));
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);
        TextView from = (TextView) row.findViewById(R.id.from);
        TextView messageContent = (TextView) row.findViewById(R.id.message_content);

        from.setText((CharSequence) objects.get(position).getSender());
        String status = objects.get(position).getStatus();
        if (status.equals("")) {
            messageContent.setText((CharSequence) objects.get(position).getPassedTime());
        } else {
            messageContent.setText(status);
        }
        return row;
    }

    public void setContent(int position, String value) {
        objects.get(position).setStatus(value);
        notifyDataSetChanged();
    }

    public void removeItem(int index) {
        objects.remove(index);
        notifyDataSetChanged();
    }


    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
