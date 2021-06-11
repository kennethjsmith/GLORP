package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Riddle;

/**
 * A JPanel to display multiple choice riddles and trivia.
 * Goes on top of the InputPanel, which goes on top of the RiddlePanel.
 * @author Heather Finch, Katelynn Oleson, Ken Smith.
 * @version 1.0.
 *
 */
public class MCInputView extends JPanel {

	private static final long serialVersionUID = -5405375207665157664L;
	private ButtonGroup myAnswers;
	private JRadioButton myAnswerOption1;
	private JRadioButton myAnswerOption2;
	private JRadioButton myAnswerOption3;
	private JRadioButton myAnswerOption4;
	private String mySelectedAnswer;
	
	/**
	 * Constructs the MCInputView based on the Riddle provided.
	 * @param Riddle The Riddle whose answer options we will display on MCInputView
	 */
	public MCInputView(Riddle theRiddle) {
		super();
		Objects.requireNonNull(theRiddle);
		setOpaque(false);
		setLayout(new GridBagLayout());
		mySelectedAnswer = null;

		HashSet<String >randomizedAnswers = randomizeAnswerOptions(theRiddle);
		setupButtons(randomizedAnswers);
		addListeners();
	}
	
	// Randomizes the answer options.
	// We use a set instead of ArrayList because set displays the answers in a different order for each Riddle.
	// Using Collections.Shuffle with an array list does not work due to using multiple threads.
	// When using Collections.Shuffle the same answer option would show multiple times on the GUI.
	private HashSet<String> randomizeAnswerOptions(Riddle theRiddle) {
		HashSet<String> answerOptions = new HashSet<>();
		for(String s : theRiddle.getWrongOptions()) answerOptions.add(s);
		answerOptions.add(theRiddle.getAnswer());
		return answerOptions;
	}
	
	// Set up and add all of the buttons.
	// There are 4 buttons. Their text is based off of the Riddle
	// recieved in the constructor.
	private void setupButtons(HashSet<String> theAnswers) {
		ArrayList<String> tempList = new ArrayList<>(); // We convert to ArrayList so we can grab the options with their index.
		for(String s : theAnswers) tempList.add(s);
		
		myAnswerOption1 = new JRadioButton(tempList.get(0));
		myAnswerOption1.setOpaque(false);
		myAnswerOption1.setFocusPainted(false);
		myAnswerOption2 = new JRadioButton(tempList.get(1));
		myAnswerOption2.setOpaque(false);
		myAnswerOption2.setFocusPainted(false);
		myAnswerOption3 = new JRadioButton(tempList.get(2));
		myAnswerOption3.setOpaque(false);
		myAnswerOption3.setFocusPainted(false);
		myAnswerOption4 = new JRadioButton(tempList.get(3));
		myAnswerOption4.setOpaque(false);
		myAnswerOption4.setFocusPainted(false);
		
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
	
	// Add listeners to each button.
	private void addListeners() {
		myAnswerOption1.addActionListener(e -> mySelectedAnswer = myAnswerOption1.getText());
		myAnswerOption2.addActionListener(e -> mySelectedAnswer = myAnswerOption2.getText());
		myAnswerOption3.addActionListener(e -> mySelectedAnswer = myAnswerOption3.getText());
		myAnswerOption4.addActionListener(e -> mySelectedAnswer = myAnswerOption4.getText());
	}
	
	/**
	 * Getter for the answer that the Player selected.
	 * @return String The answer the player selected
	 */
	String getAnswer() {
		return mySelectedAnswer;
	}
}
