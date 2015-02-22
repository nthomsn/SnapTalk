package nickthomson.me.snaptalk;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.PresetReverb;
import android.os.Environment;

import java.io.IOException;

public final class MessagePlayer implements MediaPlayer.OnCompletionListener {
    MainAdapter adapter;
    int position;

    public MessagePlayer(MainAdapter adapter, int position) {
        this.adapter = adapter;
        this.position = position;
    }

    public void playFile(String fileName) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(getFilePath(fileName));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(this);
        setTime();
        adapter.setPlaying(true);
    }

    public void onCompletion(MediaPlayer player) {
        adapter.removeItem(position);
        adapter.setPlaying(false);
    }

    private void setTime() {
        adapter.setContent(position, "Playing...");
    }

    private static String getFilePath(String fileName) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path += "/" + fileName + ".3gp";
        return path;
    }
}
