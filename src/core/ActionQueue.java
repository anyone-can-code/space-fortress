// PriorityQueue.java
package core;

import java.util.ArrayList;

/*
Process of using an ActionQueue:
1. Give an Entity an ActionQueue
2. Define Actions as private classes in an Entity
    1. Actions have access to everything in the Entity, so they can manipulate the Entity as if they are the Entity
    2. The actional action takes place in the perform function of Action, which should be overridden when defining
    3. Actions should return False if they're still in-progress, or True if their complete
3. In the Entity's tick(), add any neccesary-for-survival or player-requested actions to the ActionQueue
4. Call ActionQueue.tick() at the end of the Entity's tick()
*/

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
            if (actionsToDo.size() == 0) {
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
