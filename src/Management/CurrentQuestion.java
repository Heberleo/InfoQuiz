package Management;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CurrentQuestion {
    private Question q;
    private PropertyChangeSupport propertyChangeSupport;

    public CurrentQuestion() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Fires a PropertyChangeEvent that there is a new question.
     * @param q may be null
     */
    public void setQuestion(Question q) {
       propertyChangeSupport.firePropertyChange("question", this.q, q);
       this.q = q;
    }

    public Question getQuestion() {
        return q;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public boolean isEmpty() {
        return q == null;
    }
}
