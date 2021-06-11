/*
 * GLORP: Revenge of the Sphinx
 */

package view;

import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A JPanel to display Fill-in-the-blank riddles and trivia.
 * Goes on top of the InputPanel, which goes on top of the RiddlePanel.
 * @author Heather Finch, Katelynn Oleson, Ken Smith.
 * @version 1.0.
 *
 */
public class FBInputView extends JPanel {
	// A serialized ID for serialization.
	private static final long serialVersionUID = -5405375207665157664L;
	
	// The number of columns in width of the TextField.
    private static final int TEXT_FIELD_COLUMNS = 15;
	private String myTypedAnswer;
	private JTextField myTextField;
	
	/**
	 * Constructs the fill-in-the-blank input panel
	 */
	public FBInputView() {
		super();
		setOpaque(false);
		setLayout(new GridBagLayout());
		myTypedAnswer = null;
		setupTextField();
		addListeners();
	}
	
	// Sets up the text field, where the user will type their answer.
	private void setupTextField() {
        myTextField = new JTextField("Type your answer here", TEXT_FIELD_COLUMNS);
        myTextField.setEditable(true);
        myTextField.setHorizontalAlignment(JTextField.CENTER);
        this.add(myTextField);
	}
	
	// Adds a focus listener to the text field.
	private void addListeners() {
		myTextField.addFocusListener(new FocusListener() {

		    @Override
		    public void focusGained(FocusEvent e) {
		        myTextField.setText(null); // Empty the text field when it receives focus.
		    }
		    
			@Override
			public void focusLost(FocusEvent e) {
				myTypedAnswer = myTextField.getText(); // Grab the text when it loses focus.				
			}
		});
	}
	
	/**
	 * Returns the answer the user typed in the text field.
	 * @return String The answer the user typed in the text field
	 */
	String getAnswer() {
		return myTypedAnswer;
	}
}
