package SmartBoard;

public class Checklist {
    public Checklist(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked() {
        if(checked) {
            checked = false;
        } else {
            checked = true;
        }
    }
    public boolean getChecked() {
        return checked;
    }

    String message;
    boolean checked = false;


}
