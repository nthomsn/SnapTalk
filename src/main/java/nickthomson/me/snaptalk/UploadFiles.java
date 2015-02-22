package nickthomson.me.snaptalk;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import nickthomson.me.snaptalk.Webb.Webb;

/**
 * Created by Chase Roberts on 2/21/15.
 */
public class UploadFiles extends AsyncTask<File, Void, Void> {
    protected Void doInBackground(File... file) {

        String encodedFile = "ENCODINGERROR";
        try {
            encodedFile = Base64.encodeObject(file);
            Log.d("enc", encodedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Webb webb = Webb.create();

        String response = webb.get("http://104.131.55.192:8080/")
                .param("post", "audio")
                .param("audio", file)
                .param("user", "Nick")
                .param("other", "Chase")
                .ensureSuccess().asString().getBody();
        Log.d("UPLOAD", response);

        return null;
    }
}
