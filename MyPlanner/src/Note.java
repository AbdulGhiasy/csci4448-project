import java.util.UUID;

/**
 * @author Abdul Ghiasy
 * <br/>
 * <b>Note:</b><br/> This class consists of the specified variables, and involves
 * the specified getter/setter methods to achieve encapsulation for the note class;
 */

public class Note {
    String noteId = UUID.randomUUID().toString();
    String noteName;
    String noteDescription;

    public Note(String noteName, String noteDescription) {
        this.noteName = noteName;
        this.noteDescription = noteDescription;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }
}

