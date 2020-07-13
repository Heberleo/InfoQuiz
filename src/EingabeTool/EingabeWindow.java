package EingabeTool;

import Database.LoadSaveException;
import GUI.resources.MyButton;
import GUI.resources.MyColor;
import Management.Question;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;

public class EingabeWindow extends JFrame {
	JList<Question> jList;
	QuestionListModel model;

	public EingabeWindow() throws LoadSaveException {
		super();
		UIManager.put("List.selectionBackground", MyColor.uni);
		UIManager.put("List.selectionForeground", Color.WHITE);
		UIManager.put("List.focusCellHighlightBorder", false);
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
		jList = new JList<>(model);
		JScrollPane scrollPane = new JScrollPane(jList);
		add(scrollPane, BorderLayout.CENTER);

		//Buttons
		JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlButton.setBackground(Color.WHITE);

		MyButton btnDelete = new MyButton("Löschen");
		btnDelete.setPreferredSize(new Dimension(110, 30));
		btnDelete.setFocusable(false);
		btnDelete.addActionListener(e -> {
			if (jList.isSelectionEmpty()) return;
			else if (JOptionPane.showConfirmDialog(owner, "Diese Frage löschen?", "Bestätigen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.OK_OPTION) {
				model.remove(jList.getSelectedIndex());
			}
		});
		pnlButton.add(btnDelete);

		MyButton btnAdd = new MyButton("Hinzufügen");
		btnAdd.setPreferredSize(new Dimension(110, 30));
		btnAdd.setFocusable(false);
		btnAdd.addActionListener(e -> new AddDialog(owner, model));
		pnlButton.add(btnAdd);

		MyButton btnDetails = new MyButton("Details");
		btnDetails.setPreferredSize(new Dimension(110, 30));
		btnDetails.setFocusable(false);
		btnDetails.addActionListener(e -> {
			 if (!jList.isSelectionEmpty()) {
				 Question q = model.getElementAt(jList.getSelectedIndex());
				 DetailDialog detailDialog = new DetailDialog(owner, model, q);
			 }
		});
		pnlButton.add(btnDetails);

		add(pnlButton, BorderLayout.SOUTH);
		//

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(MyColor.uni);
		menuBar.setVisible(true);
		menuBar.setFocusable(false);
		add(menuBar, BorderLayout.NORTH);

		JMenu resetMenu = new JMenu("Zurücksetzen");
		resetMenu.setBackground(MyColor.uni);
		resetMenu.setForeground(Color.WHITE);
		resetMenu.setFocusPainted(false);
		resetMenu.setFocusable(false);
		menuBar.add(resetMenu);

		JMenuItem resetStats = new JMenuItem("Statistik");
		resetStats.setBackground(MyColor.uni);
		resetStats.setForeground(Color.WHITE);
		resetStats.setFocusPainted(false);
		resetStats.setFocusable(false);
		resetStats.addActionListener(e -> {
			try {
				model.resetStats();
			} catch (LoadSaveException loadSaveException) {
				loadSaveException.printStackTrace();
			}
		});
		resetMenu.add(resetStats);

		JMenuItem resetScore = new JMenuItem("Score");
		resetScore.setBackground(MyColor.uni);
		resetScore.setForeground(Color.WHITE);
		resetScore.setFocusPainted(false);
		resetScore.setFocusable(false);
		resetScore.addActionListener(e -> {
			try {
				model.resetScore();
			} catch (LoadSaveException loadSaveException) {
				loadSaveException.printStackTrace();
			}
		});
		resetMenu.add(resetScore);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
