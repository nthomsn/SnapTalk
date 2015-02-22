package nickthomson.me.snaptalk;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    MainAdapter mainAdapter;
    ListView mainList;

    RecordingManager recordingManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        long currentTime = new Date().getTime();
        RecievedMessage[] listData = new RecievedMessage[] {
                new RecievedMessage(new Date(), "Billy"),
                new RecievedMessage(new Date(currentTime - 1000 * 120), "Mom"),
                new RecievedMessage(new Date(currentTime - 1000 * 3700), "Fraklin")
        };

        mainAdapter = new MainAdapter(this, R.layout.list_row,
                listData);
        mainList = (ListView) findViewById(R.id.listView);
        mainList.setAdapter(mainAdapter);
        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainAdapter.removeItem(position);
            }
        });

        recordingManager = new RecordingManager();
    }

    /* Called when user wants to make a new Talk */
    public void newTalk(View view) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(getFileName());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    private String getFileName() {
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/blurp.3gp";
        return fileName;
    }

    /* Called when the user wants to start recording */
    public void userRecording(View viw) {
        recordingManager.toggleRecording(this);
    }

}
