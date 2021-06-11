package view;

import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * A JPanel to display true/false riddles and trivia.
 * Goes on top of the InputPanel, which goes on top of the RiddlePanel.
 * @author Heather Finch, Katelynn Oleson, Ken Smith.
 * @version 1.0.
 */
public class TFInputView extends JPanel {
	// A serialized ID for serialization.
	private static final long serialVersionUID = -5405375207665157664L;
	private ButtonGroup myAnswers;
	private JRadioButton myAnswerOption1;
	private JRadioButton myAnswerOption2;
	private String mySelectedAnswer;
	
	/**
	 * Constructs the TFInputView.
	 */
	public TFInputView() {
		super();
		setOpaque(false);
		setLayout(new GridBagLayout());
		mySelectedAnswer = null;
		setupButtons();
		addListeners();
	}
	
	// Helper method sets up and adds the buttons.
	// There are 2 buttons: "True" and "False"
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
	
	// Adds listeners to the buttons. 
	private void addListeners() {
		myAnswerOption1.addActionListener(e -> mySelectedAnswer = myAnswerOption1.getText());	
		myAnswerOption2.addActionListener(e -> mySelectedAnswer = myAnswerOption2.getText());
	}
	
	/**
	 * Getter for the players answer selection.
	 * @return String The answer the player selected to this Riddle
	 */
	String getAnswer() {
		return mySelectedAnswer;
	}
}