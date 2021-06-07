package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Riddle;

public class MCInputView extends JPanel {

	private static final long serialVersionUID = -5405375207665157664L;
	private final static int WIDTH = 275;
	private final static int HEIGHT = 125;
	private ButtonGroup myAnswers;
	JRadioButton myAnswerOption1;
	JRadioButton myAnswerOption2;
	JRadioButton myAnswerOption3;
	JRadioButton myAnswerOption4;
	private String mySelectedAnswer;
	
	public MCInputView(Riddle theRiddle) {
		super();
		setOpaque(false);
		setLayout(new GridBagLayout());
		//setPreferredSize(new Dimension(WIDTH,HEIGHT));
		mySelectedAnswer = null;

		ArrayList<String >randomizedAnswers = randomizeAnswerOptions(theRiddle);
		setupButtons(randomizedAnswers);
		addListeners();
	}
	
	private ArrayList<String> randomizeAnswerOptions(Riddle theRiddle) {
		ArrayList<String> answerOptions = theRiddle.getWrongOptions();
		answerOptions.add(theRiddle.getAnswer());
		Collections.shuffle(answerOptions);
		return answerOptions;
	}
	
	private void setupButtons(ArrayList<String> theAnswers) {
		myAnswerOption1 = new JRadioButton(theAnswers.get(0));
		myAnswerOption1.setOpaque(false);
		myAnswerOption2 = new JRadioButton(theAnswers.get(1));
		myAnswerOption2.setOpaque(false);
		myAnswerOption3 = new JRadioButton(theAnswers.get(2));
		myAnswerOption3.setOpaque(false);
		myAnswerOption4 = new JRadioButton(theAnswers.get(3));
		myAnswerOption4.setOpaque(false);
		
		myAnswers = new ButtonGroup();
		
		myAnswers.add(myAnswerOption1);
		myAnswers.add(myAnswerOption2);
		myAnswers.add(myAnswerOption3);
		myAnswers.add(myAnswerOption4);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		this.add(myAnswerOption1, c);
		c.gridx = 1;
		c.gridy = 0;
		this.add(myAnswerOption2, c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(myAnswerOption3, c);
		c.gridx = 1;
		c.gridy = 1;
		this.add(myAnswerOption4, c);
		
	}
	
	private void addListeners() {
		myAnswerOption1.addActionListener(e -> mySelectedAnswer = myAnswerOption1.getText());
		
		myAnswerOption2.addActionListener(e -> mySelectedAnswer = myAnswerOption2.getText());
		
		myAnswerOption3.addActionListener(e -> mySelectedAnswer = myAnswerOption3.getText());
		
		myAnswerOption4.addActionListener(e -> mySelectedAnswer = myAnswerOption4.getText());
	}
	
	public String getAnswer() {
		return mySelectedAnswer;
	}
}
