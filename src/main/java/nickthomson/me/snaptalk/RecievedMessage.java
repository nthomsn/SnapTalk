package nickthomson.me.snaptalk;

import android.os.Environment;

import java.io.File;
import java.util.Date;

public class RecievedMessage {
    Date timeRecieved;
    String sender = "Bob";
    String fileName = "blurp";
    String status = "";

    public RecievedMessage() {
        timeRecieved = new Date();
    }

    public RecievedMessage(Date fakeDate, String fakeSender) {
        timeRecieved = fakeDate;
        sender = fakeSender;
    }

    public String getPassedTime() {
        long millisecondsPassed = new Date().getTime() - timeRecieved.getTime();
        long minutesPassed = millisecondsPassed / 60000;
        if (minutesPassed > 60) {
            return "An hour ago";
        }
        if (minutesPassed > 30) {
            return "Half an hour ago";
        }
        if (minutesPassed > 10) {
            return "A few minutes ago";
        }
        if (minutesPassed > 1) {
            return "A moment ago";
        }
        return "Just now";
    }

    private File getFile() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        path += "/" + fileName + ".3gp";
        return new File(path);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setFileName(String fileName) { this.fileName = fileName; }

    public void setStatus(String status) { this.status = status; }

    public String getStatus() { return this.status; }

}
