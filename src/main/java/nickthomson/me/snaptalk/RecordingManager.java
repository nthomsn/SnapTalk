package nickthomson.me.snaptalk;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class RecordingManager {
    private boolean isRecording = false;
    private MediaRecorder mediaRecorder;

    public void toggleRecording(Activity fromActivity) {
        if(isRecording) {
            stopRecording(fromActivity);
        } else {
            startRecording();
        }
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(getFileName());
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaRecorder.start();
        isRecording = true;
    }

    private void stopRecording(Activity fromActivity) {
        mediaRecorder.stop();
        mediaRecorder.release();
        isRecording = false;
        Intent intent = new Intent(fromActivity, SelectContactActivity.class);
        fromActivity.startActivity(intent);
    }

    private String getFileName() {
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/blurp.3gp";
        return fileName;
    }
}
