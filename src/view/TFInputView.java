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
		setLayout(new GridBagLayout());
		//setPreferredSize(new Dimension(WIDTH,HEIGHT));
		mySelectedAnswer = null;
		setupButtons();
		addListeners();
	}
	
	private void setupButtons() {
		myAnswerOption1 = new JRadioButton("True");
		myAnswerOption2 = new JRadioButton("False");
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
	
	public String getAnswer() {
		return mySelectedAnswer;
	}
}