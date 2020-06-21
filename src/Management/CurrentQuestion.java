package Management;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CurrentQuestion {
    Question q;
    PropertyChangeSupport propertyChangeSupport;

    public CurrentQuestion() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void setQuestion(Question q) {
       if (!checkQuestion(q)) throw  new IllegalArgumentException("Question may not be null.");
       propertyChangeSupport.firePropertyChange("question", this.q, q);
        this.q = q;
    }

    public Question getQuestion() {
        return q;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    private boolean checkQuestion (Question q) {
        return q != null;
    }
}
