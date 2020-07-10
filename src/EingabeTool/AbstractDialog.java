package EingabeTool;

import GUI.resources.MyButton;
import GUI.resources.MyColor;
import Management.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractDialog extends JDialog {
	private JComboBox<Questiontype> cbType;
	private JPanel pnlCenter;
	private JPanel pnlButton;
	private MyButton btnFlex;
	private JTextField txtTitleFB;
	private JTextField txtTitleMP;
	private JComboBox<Integer> cbTimeFB;
	private JComboBox<Integer> cbTimeMC;
	private JTextField txtCorrectFB;
	private JTextField[] txtAnswersMC;
	private JCheckBox[] cbxCorrectMC;

	private boolean editState;

	public AbstractDialog(Frame owner) {
		super(owner);
		ImageIcon icon = new ImageIcon("lib/images/icon32.png");
		setIconImage(icon.getImage());
		LineBorder myBorder = new LineBorder(MyColor.uni, 2);
		GridBagConstraints gbc;
		UIManager.put("TextField.inactiveForeground", Color.BLACK);
		UIManager.put("ComboBox.SelectionBackground", MyColor.uni);
		UIManager.put("ComboBox.disabledBackground", Color.WHITE);
		UIManager.put("ComboBox.disabledForeground", Color.BLACK);

		//pnlCenter
		CardLayout cardLayout = new CardLayout();
		pnlCenter = new JPanel(cardLayout);
		JPanel pnlFB = new JPanel(new GridBagLayout());
			pnlFB.setBackground(Color.WHITE);
			txtTitleFB = new JTextField();
			txtTitleFB.setBorder(BorderFactory.createTitledBorder(myBorder, "Titel"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlFB.add(txtTitleFB, gbc);

			cbTimeFB = new JComboBox<>();
			cbTimeFB.setBackground(Color.WHITE);
			cbTimeFB.setFont(new Font(cbTimeFB.getFont().getName(), Font.PLAIN, cbTimeFB.getFont().getSize()));
			cbTimeFB.setFocusable(false);
			for (int j = 30; j < 300; j += 30) {
				cbTimeFB.addItem(j);
			}
			cbTimeFB.setBorder(BorderFactory.createTitledBorder(myBorder, "Zeit"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlFB.add(cbTimeFB, gbc);

			txtCorrectFB = new JTextField();
			txtCorrectFB.setBorder(BorderFactory.createTitledBorder(myBorder, "Richtige Antwort"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.weightx = 1.0;
			gbc.weighty = 1.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlFB.add(txtCorrectFB, gbc);

		pnlCenter.add(pnlFB, Questiontype.FillBlank.toString());


		JPanel pnlMP = new JPanel();
			pnlMP.setLayout(new GridBagLayout());
			pnlMP.setBackground(Color.WHITE);

			txtTitleMP = new JTextField();
			txtTitleMP.setBackground(Color.WHITE);
			txtTitleMP.setBorder(BorderFactory.createTitledBorder(myBorder, "Titel"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 4;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlMP.add(txtTitleMP, gbc);

			cbTimeMC = new JComboBox<>();
			cbTimeMC.setBackground(Color.WHITE);
			cbTimeMC.setFont(new Font(cbTimeMC.getFont().getName(), Font.PLAIN, cbTimeMC.getFont().getSize()));
			cbTimeMC.setFocusable(false);
			for (int j = 30; j < 300; j+= 30) {
				cbTimeMC.addItem(j);
			}
			cbTimeMC.setBorder(BorderFactory.createTitledBorder(myBorder, "Zeit"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 4;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlMP.add(cbTimeMC, gbc);

			txtAnswersMC = new JTextField[4];

			txtAnswersMC[0] = new JTextField();
			txtAnswersMC[0].setBorder(BorderFactory.createTitledBorder(myBorder, "Antwort 1"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 4;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlMP.add(txtAnswersMC[0], gbc);

			txtAnswersMC[1] = new JTextField();
			txtAnswersMC[1].setBorder(BorderFactory.createTitledBorder(myBorder, "Antwort 2"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 4;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlMP.add(txtAnswersMC[1], gbc);

			txtAnswersMC[2] = new JTextField();
			txtAnswersMC[2].setBorder(BorderFactory.createTitledBorder(myBorder, "Antwort 3"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.gridwidth = 4;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlMP.add(txtAnswersMC[2], gbc);

			txtAnswersMC[3] = new JTextField();
			txtAnswersMC[3].setBorder(BorderFactory.createTitledBorder(myBorder, "Antwort 4"));
			gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 5;
			gbc.gridwidth = 4;
			gbc.weightx = 1.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.FIRST_LINE_START;
			pnlMP.add(txtAnswersMC[3], gbc);

			cbxCorrectMC = new JCheckBox[4];
			for(int i = 0; i < 4; i++) {
				cbxCorrectMC[i] = new JCheckBox("Antwort " + (i + 1));
				cbxCorrectMC[i].setBorder(BorderFactory.createTitledBorder(myBorder, "Antwort " + (i + 1)));
				cbxCorrectMC[i].setBackground(Color.WHITE);
				cbxCorrectMC[i].setFocusable(false);
				gbc = new GridBagConstraints();
				gbc.gridx = i;
				gbc.gridy = 6;
				gbc.weightx = 1.0;
				gbc.weighty = 1.0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.anchor = GridBagConstraints.FIRST_LINE_START;
				pnlMP.add(cbxCorrectMC[i], gbc);
			}

		pnlCenter.add(pnlMP, Questiontype.MultipleChoice.toString());
		add(pnlCenter, BorderLayout.CENTER);

		// ComboBox cbType
		cbType = new JComboBox<>(Questiontype.values());
		cbType.setBorder(BorderFactory.createTitledBorder(myBorder, "Typ"));
		cbType.setBackground(Color.WHITE);
		cbType.setFont(new Font(cbType.getFont().getName(), Font.PLAIN,cbType.getFont().getSize()));
		cbType.setFocusable(false);
		cbType.addActionListener(e -> {
			Questiontype type = (Questiontype) ((JComboBox<Questiontype>) e.getSource()).getSelectedItem();
			cardLayout.show(pnlCenter, type.toString());
			if (type.toString().equals(Questiontype.FillBlank.toString())) txtTitleFB.grabFocus();
			else txtTitleMP.grabFocus();
		});
		add(cbType, BorderLayout.NORTH);

		// pnlButton
		pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnlButton.setBackground(Color.WHITE);
		add(pnlButton,BorderLayout.SOUTH);

		btnFlex = new MyButton("");
		btnFlex.setPreferredSize(new Dimension(120, 30));
		btnFlex.setFocusable(false);
		pnlButton.add(btnFlex);

		//
		setBackground(Color.WHITE);
		this.setSize(700, 500);
		this.setVisible(true);
		cardLayout.show(pnlCenter, cbType.getSelectedItem().toString());
	}

	public void enableAll (boolean aFlag) {
		editState = aFlag;
		txtTitleMP.setEnabled(aFlag);
		txtTitleFB.setEnabled(aFlag);
		cbTimeMC.setEnabled(aFlag);
		cbTimeFB.setEnabled(aFlag);
		txtCorrectFB.setEnabled(aFlag);
		for (int i = 0; i < 4; ++i) {
			txtAnswersMC[i].setEnabled(aFlag);
			cbxCorrectMC[i].setEnabled(aFlag);
		}
	}

	public void enableType(boolean aFlag) {
		cbType.setEnabled(aFlag);
	}

	public boolean isOpen() {
		return editState;
	}

	public void setButton(String text) {
		btnFlex.setText(text);
	}

	public void addButtonListener(ActionListener a) {
		btnFlex.addActionListener(a);
	}

	public String getQuestionTitle() {
		if (cbType.getSelectedItem().toString().equals(Questiontype.FillBlank.toString()))
			return txtTitleFB.getText();
		return txtTitleMP.getText();
	}
	private void setQuestionTitle(String title) {
		if (cbType.getSelectedItem().toString().equals(Questiontype.FillBlank.toString())) {
			txtTitleFB.setText(title);
			return;
		}
		txtTitleMP.setText(title);
	}

	public void showQuestion(Question q) {
		setType(q.getType());
		setQuestionTitle(q.getTitle());
		enableAll(false);
		if (cbType.getSelectedItem().toString().equals(Questiontype.FillBlank.toString())) {
			setAnswersFB(((FillBlank) q).getCorrectAnswer());
		} else {
			setAnswersMC(((MultipleChoice) q).getAnswers(), ((MultipleChoice) q).getCorrectAnswers());
		}
		setTime(q.getTime());

	}

	private void setType(Questiontype type) {
		cbType.setSelectedItem(type);
	}

	private void setTime(int time) {
		if (cbType.getSelectedItem().toString().equals(Questiontype.FillBlank.toString())) {
			cbTimeFB.setSelectedItem(time);
			return;
		}
		cbTimeMC.setSelectedItem(time);

	}

	public int getTime() {
		if (cbType.getSelectedItem().toString().equals(Questiontype.FillBlank.toString())) {
			return (cbTimeFB.getSelectedIndex() + 1) * 30;
		}
		return (cbTimeMC.getSelectedIndex() + 1) * 30;
	}
	private void setAnswersMC(String[] answers, String correct) {
		for (int i = 0; i < 4; i++) {
			txtAnswersMC[i].setText(answers[i]);
		}
		StringBuilder sb = new StringBuilder(correct);
		while (!(sb.length() == 0)) {
			char c = sb.charAt(0);
			sb = sb.deleteCharAt(0);
			int i = Integer.parseInt(c + "");
			cbxCorrectMC[i - 1].setSelected(true);
		}
	}

	private void setAnswersFB(String answer) {
		txtCorrectFB.setText(answer);
	}

	public String[] getAnswers() {
		String[] temp = new String[4];
		for(int i = 0; i < 4; ++i) {
			temp[i] = txtAnswersMC[i].getText();
		}
		return temp;
	}

	public String getCorrectAnswer() {
		if (cbType.getSelectedItem().toString().equals(Questiontype.FillBlank.toString())) {
			return txtCorrectFB.getText();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			if(cbxCorrectMC[i].isSelected()) sb.append(i + 1);
		}
		return sb.toString();
	}

	public Questiontype getQuestionType() {
		if (cbType.getSelectedItem().toString().equals(Questiontype.FillBlank.toString())) {
			return Questiontype.FillBlank;
		}
		return Questiontype.MultipleChoice;
	}
}
