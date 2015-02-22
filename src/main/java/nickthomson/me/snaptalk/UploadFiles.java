package nickthomson.me.snaptalk;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Chase Roberts on 2/21/15.
 */
public class UploadFiles extends AsyncTask<File, Void, Void> {
    protected Void doInBackground(File... file) {
        String url = "http://104.131.55.192:8080/";
        try {
            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httppost = new HttpPost(url);

            InputStreamEntity reqEntity = new InputStreamEntity(
                    new FileInputStream(file[0]), -1);
            reqEntity.setContentType("binary/octet-stream");
            reqEntity.setChunked(true); // Send in multiple parts if needed
            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            Log.d("res", response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
