package nickthomson.me.snaptalk;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;


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

    }

}
