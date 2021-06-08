package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FBInputView extends JPanel {
	
	private static final long serialVersionUID = -5405375207665157664L;
	private final static int WIDTH = 275;
	private final static int HEIGHT = 125;
	
	/** The number of columns in width of the TextField. */
    private static final int TEXT_FIELD_COLUMNS = 15;
	private String myTypedAnswer;
	JTextField myTextField;
	
	public FBInputView() {
		super();
		setOpaque(false);
		setLayout(new GridBagLayout());
		//setPreferredSize(new Dimension(WIDTH,HEIGHT));
		myTypedAnswer = null;
		setupTextField();
		addListeners();
	}
	
	private void setupTextField() {
        myTextField = new JTextField("Type your answer here", TEXT_FIELD_COLUMNS);
        myTextField.setEditable(true);
        myTextField.setHorizontalAlignment(JTextField.CENTER);
        this.add(myTextField);
	}
	
	private void addListeners() {
		myTextField.addFocusListener(new FocusListener() {

		    @Override
		    public void focusGained(FocusEvent e) {
		        myTextField.setText(null); // Empty the text field when it receives focus
		    }

			@Override
			public void focusLost(FocusEvent e) {
				myTypedAnswer = myTextField.getText(); // Grab the text when it loses focus				
			}
		});
	}
	
	public String getAnswer() {
		return myTypedAnswer;
	}
}
