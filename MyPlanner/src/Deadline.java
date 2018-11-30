import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Abdul Ghiasy
 * <br/>
 * <b>Note:</b><br/> This class consists of the specified variables, and involves
 * the specified getter/setter methods to achieve encapsulation for the deadline class;
 */

public class Deadline {
    String deadlineId = UUID.randomUUID().toString();
    String deadlineName;
    String deadlineDescription;
    String deadlineDate;
    ArrayList<Action> actions = new ArrayList<Action>();

    public Deadline(String deadlineName, String deadlineDescription, String deadlineDate) {
        this.deadlineName = deadlineName;
        this.deadlineDescription = deadlineDescription;
        this.deadlineDate = deadlineDate;
        deadlineId = UUID.randomUUID().toString();
    }
    public Deadline(String deadlineName, String deadlineDescription) {
        this.deadlineName = deadlineName;
        this.deadlineDescription = deadlineDescription;
        deadlineDate = new SimpleDateFormat("yyyy/MM/dd").toString();
    }

    public String getDeadlineId() {
        return deadlineId;
    }

    public String getDeadlineName() {
        return deadlineName;
    }

    public String getDeadlineDescription() {
        return deadlineDescription;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineName(String deadlineName) {
        this.deadlineName = deadlineName;
    }

    public void setDeadlineDescription(String deadlineDescription) {
        this.deadlineDescription = deadlineDescription;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void removeAction(String actionId) {
        for(int i = 0; i < actions.size(); i++) {
            if(actions.get(i).actionId == actionId) {
                actions.remove(actions.get(i));
            }
        }
    }
}
