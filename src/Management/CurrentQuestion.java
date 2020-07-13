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
     * Fires a PropertyChangeEvent that there is a new question. If the new question equals the old question, the PropertyChangeEvent
     * will still be fired with the oldValue attribute set to null.
     * @param q may be null
     */
    public void setQuestion(Question q) {
        if(q == null) propertyChangeSupport.firePropertyChange("question",null,null);
        else if (q.equals(this.q)) propertyChangeSupport.firePropertyChange("question", null, q);
        else propertyChangeSupport.firePropertyChange("question", this.q, q);
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
