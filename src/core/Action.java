package core;

// perform returns true if the action appears to be complete
public interface Action {
    boolean perform(Engine eng);
}