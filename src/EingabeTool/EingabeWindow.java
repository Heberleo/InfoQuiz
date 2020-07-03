package EingabeTool;

import GUI.resources.MyButton;
import GUI.resources.MyColor;
import Management.Question;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EingabeWindow extends JFrame {
	JList<Question> jList;
	QuestionListModel model;

	public EingabeWindow() {
		super();
		UIManager.put("List.selectionBackground", MyColor.uni);
		UIManager.put("List.selectionForeground", Color.WHITE);
		UIManager.put("List.focusCellHighlightBorder", Color.WHITE);
		UIManager.put("Button.background", MyColor.uni);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("ScrollPane.border", null);
		UIManager.put("ScrollBar.background", Color.WHITE);
		UIManager.put("ScrollBar.foreground", MyColor.uni);
		UIManager.put("ScrollBar.track", MyColor.uni);
		ImageIcon icon = new ImageIcon("lib/images/icon32.png");
		setIconImage(icon.getImage());
		JFrame owner = this;
		setSize(500,500);

		model = new QuestionListModel();
		model.init();
		model.addListDataListener(new ListDataListener() {
			@Override
			public void intervalAdded(ListDataEvent e) {
				jList.updateUI();
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				jList.updateUI();
			}

			@Override
			public void contentsChanged(ListDataEvent e) { jList.updateUI(); }
		});
		jList = new JList<Question>(model);
		JScrollPane scrollPane = new JScrollPane(jList);
		add(scrollPane, BorderLayout.CENTER);

		//Buttons
		JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlButton.setBackground(Color.WHITE);

		MyButton btnDelete = new MyButton("Delete");
		btnDelete.setPreferredSize(new Dimension(80, 30));
		btnDelete.setFocusable(false);
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jList.isSelectionEmpty());
				else if (JOptionPane.showConfirmDialog(owner, "Do you want to delete this question?", "Confirm deletion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) {
					model.remove(jList.getSelectedIndex());
				}
			}
		});
		pnlButton.add(btnDelete);

		MyButton btnAdd = new MyButton("Add");
		btnAdd.setPreferredSize(new Dimension(80, 30));
		btnAdd.setFocusable(false);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddDialog addDialog = new AddDialog(owner, model);
			}
		});
		pnlButton.add(btnAdd);

		MyButton btnDetails = new MyButton("Details");
		btnDetails.setPreferredSize(new Dimension(80, 30));
		btnDetails.setFocusable(false);
		btnDetails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (!jList.isSelectionEmpty()) {
				 	Question q = (Question) model.getQuestionAt(jList.getSelectedIndex());
				 	DetailDialog detailDialog = new DetailDialog(owner, model, q);
				 }
			}
		});
		pnlButton.add(btnDetails);

		add(pnlButton, BorderLayout.SOUTH);
		//

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
