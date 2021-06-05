package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;


/**
 * The panel for player input.
 * @author Ken Smith, Heather Finch, Katelynn Oleson 
 * @version 
 */
public class InputPanel extends JPanel implements KeyListener{
	private final static int WIDTH = 285;
	private final static int HEIGHT = 125;
	
	 private JButton mySubmitButton;
	 private JLabel myTitle;
	 private ArrayList<Component> myAnswerOptions;
	 private boolean hasSubmitted;
	 private String myAnswer;
	
	/**
	 * 
	 */
	public InputPanel() {
		super();
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		// Create a border
	    Border whiteline = BorderFactory.createLineBorder(Color.WHITE);
	    setBorder(whiteline);
		
		//JTextField textField = new JTextField(5);
		// TODO: this breaks focus
	    myTitle = new JLabel("How do you answer ...");

        myAnswer = null;
        myAnswerOptions = new ArrayList<Component>();
        hasSubmitted = false;
        mySubmitButton = new JButton("Submit");
        mySubmitButton.addMouseListener(new submitMouseClickListener());   
        
        layoutComponents();
        
	}
	
	/**
     * Setup and add the GUI components for this panel. 
     */
    private void layoutComponents() {
//        setLayout(new GridBagLayout()); 
//        GridBagConstraints c = new GridBagConstraints();
//        c.fill = GridBagConstraints.NONE;
//        
//        c.insets = new Insets(20,0,0,0);
//        c.gridx = 0;
//        c.gridy = 0;
          this.add(myTitle);
        
//        c.gridx = 0;
//        c.gridy = 1;
//        c.insets = new Insets(300,0,0,0);
        for(Component comp : myAnswerOptions) {
            this.add(comp);
        }
        
//        c.gridx = 0;
//        c.gridy = 2;
//        c.insets = new Insets(300,0,0,0);
//        c.anchor = GridBagConstraints.PAGE_END;
        this.add(mySubmitButton);
    }
	
	/**
	 * 
	 */
	@Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    }
	
	public String getResponse() {
	    return myAnswer;
	}
	
	public boolean hasSubmitted() {
        return hasSubmitted;
    }
    
    public void reset() {
        hasSubmitted = false;
        myAnswer = null;
    }
    
    /**
     * Sets to be either buttons or a text field
     * @param theAnswerOptions
     */
    public void setAnswerOptions(ArrayList<Component> theAnswerOptions) {
        for(Component c : myAnswerOptions) { //remove old answer options
            this.remove(c);
        }
        
        reset(); // reset answer flags
        
        myAnswerOptions = theAnswerOptions;
        
        for(Component c : theAnswerOptions) { //add new answer options
            if(c instanceof JRadioButton) {
                ((JRadioButton) c).addMouseListener(new buttonMouseClickListener(((JRadioButton) c).getText()));
//                ((JRadioButton) c).addActionListener(ae -> {
//                        myAnswer = ((JRadioButton) c).getText();
//                        System.out.println(myAnswer);
//                    } 
//                );
                this.add(c);
            }else if(c instanceof JTextField) {
                ((JTextField) c).addActionListener(ae -> {
                    myAnswer = ((JTextField) c).getText();
                });
                this.add(c); 
            }         
            
        }          
    }
    
//    private class retreatActionListener implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            hasRetreated = true;
//            System.out.println("retreated");
//        }
//    }
    
    
 // mouse listener bc want to save key listener for room panel
    private class submitMouseClickListener implements MouseListener{ 
        @Override
        public void mouseClicked(MouseEvent e) {
            if(getResponse() != null) {
                hasSubmitted = true;  
                System.out.println("Submitted");
            }else
                System.out.println("You must select and answer before submitting");
        }
        
        // how to make it so we dont have to implement all of these

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }
    }
    
 // mouse listener bc want to save key listener for room panel
    private class buttonMouseClickListener implements MouseListener{ 
            String myResponse;
            
            private buttonMouseClickListener(String theResponse) {
                myResponse = theResponse;
            }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            myAnswer = myResponse; 
            System.out.println(myAnswer);
            
        }
        
        // how to make it so we dont have to implement all of these

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // lose focus 
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    

}

