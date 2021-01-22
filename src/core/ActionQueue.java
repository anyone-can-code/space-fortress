// PriorityQueue.java
package core;

import java.util.ArrayList;

public class ActionQueue {

    private ArrayList<Action> actionsToDo;
    Action currentAction;
    Action defaultAction;

    public ActionQueue() {
        actionsToDo = new ArrayList<Action>();
        defaultAction = new DoNothing();
    }

    public void tick(Engine eng) {
        if (currentAction == null) {
            if (actionsToDo.get(0) == null) {
                add(defaultAction);
            }
            currentAction = actionsToDo.remove(0);
        }

        // perform the action
        if (currentAction.perform(eng))
            currentAction = null;
    }

    public void add(Action a) {
        actionsToDo.add(a);
    }

    public void addToFront(Action a) {
        actionsToDo.add(0, a);
    }

    public void setDefaultAction(Action a) {
        defaultAction = a;
    }

    private class DoNothing implements Action {
        @Override
        public boolean perform(Engine eng) {
            return true;
        }
    }
    
}
