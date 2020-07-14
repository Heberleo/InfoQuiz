package GUI.questionPanes;

import GUI.resources.MyColor;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLabelUI;
import javax.swing.text.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        super();
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
            doc.insertString(doc.getLength(),"\n\nWillkomen zu IQ dem Informatik Quiz.\n" +
                    "Das Quiz wird mit den Buttons unten rechts gesteuert.\n" +
                    "Mit dem nächste Button wird die nächste Frage aufgerufen.\n" +
                    "Mit dem markieren Button können sie eine Frage markieren. \n" +
                    "Wenn sie ihre markierten Fragen üben wollen können sie diese oben links \n" +
                    "im Menü auswählen. Der Statistik Button liefert ihnen eine übersicht über die Fragen \n" +
                    "und wie sie sie beantwortet haben. Oben rechts finden sie den Punktestand.\n" +
                    "Wir wünschen ihnen viel Spaß !!", attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        pane.setFocusable(false);
        add(pane);
    }
}



 /*   setLayout(new GridLayout(2,1));
        ImageIcon icon = new ImageIcon(("lib/images/icon.png"));
        setBackground(Color.WHITE);

        JLabel lblHello = new JLabel();
        lblHello.setIcon(icon);
        lblHello.setForeground(MyColor.uni);
        lblHello.setAlignmentX(JLabel.CENTER);
        add(lblHello);

        TextArea taHello = new TextArea("Hallo");
        add(taHello); */