package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class TFInputView extends JPanel {

	private static final long serialVersionUID = -5405375207665157664L;
	private final static int WIDTH = 275;
	private final static int HEIGHT = 125;
	private ButtonGroup myAnswers;
	JRadioButton myAnswerOption1;
	JRadioButton myAnswerOption2;
	private String mySelectedAnswer;
	
	public TFInputView() {
		super();
		setOpaque(false);
		setLayout(new GridBagLayout());
		//setPreferredSize(new Dimension(WIDTH,HEIGHT));
		mySelectedAnswer = null;
		setupButtons();
		addListeners();
	}
	
	private void setupButtons() {
		myAnswerOption1 = new JRadioButton("True");
		myAnswerOption1.setFocusPainted(false);
		myAnswerOption1.setOpaque(false);
		myAnswerOption2 = new JRadioButton("False");
		myAnswerOption2.setFocusPainted(false);
		myAnswerOption2.setOpaque(false);
		
		myAnswers = new ButtonGroup();
		myAnswers.add(myAnswerOption1);
		myAnswers.add(myAnswerOption2);
		this.add(myAnswerOption1);
		this.add(myAnswerOption2);
	}
	
	private void addListeners() {
		myAnswerOption1.addActionListener(e ->{
			mySelectedAnswer = myAnswerOption1.getText();
		});
		
		myAnswerOption2.addActionListener(e -> {
			mySelectedAnswer = myAnswerOption2.getText();
		});
	}
	// TODO: why is this public?
	public String getAnswer() {
		return mySelectedAnswer;
	}
}