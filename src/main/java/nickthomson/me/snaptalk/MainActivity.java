package nickthomson.me.snaptalk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Date;


public class MainActivity extends ActionBarActivity {

    MainAdapter mainAdapter;
    ListView mainList;

    RecordingManager recordingManager;
    String loginName = "Nick";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        long currentTime = new Date().getTime();
        RecievedMessage[] listData = new RecievedMessage[] {
                new RecievedMessage(new Date(), "Billy"),
                new RecievedMessage(new Date(currentTime - 1000 * 120), "Anna"),
                new RecievedMessage(new Date(currentTime - 1000 * 3700), "Frank")
        };

        mainAdapter = new MainAdapter(this, R.layout.list_row,
                listData);
        mainList = (ListView) findViewById(R.id.listView);
        mainList.setAdapter(mainAdapter);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!mainAdapter.isPlaying()) {
                    new MessagePlayer(mainAdapter, position).playFile("blurp");
                }
            }
        });

        recordingManager = new RecordingManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.set_person:
                setPerson();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* Set our device's contact name */
    private void setPerson() {
        final EditText input = new EditText(this);
        input.setWidth(100);
        new AlertDialog.Builder(this).setTitle("Change your name")
            .setView(input)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Editable value = input.getText();
                    loginName = value.toString();
                }
            }).show();
    }

    /* Called when the user wants to start recording */
    public void userRecording(View view) {
        ImageButton imageButton = (ImageButton) view;
        if (recordingManager.isCurrentlyRecording()) {
            imageButton.setBackgroundResource(R.drawable.voice);
        } else {
            imageButton.setBackgroundResource(R.drawable.voice_recording);
        }

        recordingManager.toggleRecording(this);
    }

}
