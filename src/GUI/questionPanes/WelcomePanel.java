package GUI.questionPanes;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;


public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        super();
        setBackground(Color.WHITE);
        JTextPane pane = new JTextPane();
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyledDocument doc = (StyledDocument) pane.getDocument();
        try {
            StyleConstants.setBold(attributeSet,true);
            StyleConstants.setFontSize(attributeSet,20);
            StyleConstants.setItalic(attributeSet,true);
            StyleConstants.setAlignment(attributeSet,StyleConstants.ALIGN_CENTER);
            doc.insertString(doc.getLength(),"\n\n\nWillkommen zu IQ dem Informatik Quiz",attributeSet);
            attributeSet = new SimpleAttributeSet();
            StyleConstants.setAlignment(attributeSet,StyleConstants.ALIGN_CENTER);
            doc.insertString(doc.getLength(),"\n\nWillkomen zu iQ dem Informatik Quiz.\n" +
                    "Das Quiz wird mit den Buttons unten rechts gesteuert.\n" +
                    "Mit dem nächste Button wird die nächste Frage aufgerufen.\n" +
                    "Mit dem markieren Button können Sie eine Frage markieren. \n" +
                    "Wenn Sie ihre markierten Fragen üben wollen können Sie diese oben links \n" +
                    "im Menü auswählen. Der Statistik Button liefert ihnen eine übersicht über die Fragen \n" +
                    "und wie Sie sie beantwortet haben. Oben rechts finden Sie den Punktestand.\n" +
                    "Wir wünschen Ihnen viel Spaß !!!", attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        pane.setFocusable(false);
        add(pane);
    }
}
