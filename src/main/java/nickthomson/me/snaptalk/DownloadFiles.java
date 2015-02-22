package nickthomson.me.snaptalk;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import nickthomson.me.snaptalk.Webb.Webb;

/**
 * Created by Chase Roberts on 2/21/15.
 */
public class DownloadFiles extends AsyncTask<String, Void, String> {

    String retrievedHash = "";

    @Override
    protected String doInBackground(String... string) {
        Webb webb = Webb.create();

        JSONObject hash = webb.get("http://104.131.55.192:8080/")
                .param("get", "update")
                .param("user", string)
                .ensureSuccess()
                .asJsonObject().getBody();
        String hashString = "";
        try {
            hashString = hash.getString(string.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("UPDATE", hashString);
        retrievedHash = hashString;
        return hashString;
    }
}
