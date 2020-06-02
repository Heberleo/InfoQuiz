package mainWindow.questionPanes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {
   public Test() {
       setSize(500, 500);
       setLocation(500,200);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);

       QuestionPanel questionPanel = new QuestionPanel();
       add(questionPanel, BorderLayout.CENTER);



       JButton btnTest = new JButton("Test");
       btnTest.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               questionPanel.switchPanel();
           }
       });
       add(btnTest, BorderLayout.SOUTH);
       setVisible(true);
   }
}
