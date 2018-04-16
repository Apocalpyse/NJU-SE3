package PO.marketPO;

/**
 * Created by A on 2017/5/21.
 */
public class SimpleNotePO {
    private String noteID;
    private String title;
    private String account;

    public SimpleNotePO() {
    }

    public SimpleNotePO(String noteID, String title, String account) {
        this.noteID = noteID;
        this.title = title;
        this.account = account;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
