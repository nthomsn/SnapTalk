package nickthomson.me.snaptalk;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;


public class SelectContactActivity extends ActionBarActivity {

    Contact[] contacts = new Contact[] {
            new Contact("Nick"),
            new Contact("Chase"),
            new Contact("Steve Jobs"),
            new Contact("Lennin")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_contact_layout);

        ContactAdapter adapter = new ContactAdapter(this, R.layout.contact_row,
                contacts);
        ListView contactList = (ListView) findViewById(R.id.contactlistView);
        contactList.setAdapter(adapter);
        contactList.setClickable(true);
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File audio = new File(getFileName());
                UploadFiles test = new UploadFiles();
                test.execute(audio);
                ((Activity) view.getContext()).finish();
            }
        });
    }

    private String getFileName() {
        String fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/blurp.3gp";
        return fileName;
    }

}
