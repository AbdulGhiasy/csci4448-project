import java.util.UUID;

public class Action {
    String actionId = UUID.randomUUID().toString();

    public String getActionId() {
        return actionId;
    }

}

