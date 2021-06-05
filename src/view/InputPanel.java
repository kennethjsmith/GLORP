package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
//	 private JButton myRetreatButton;
	 private ArrayList<Component> myAnswerOptions;
	 private boolean hasSubmitted;
//	 private boolean hasRetreated;
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
		
		JTextField textField = new JTextField(5);
		// TODO: this breaks focus
        add(new JLabel("How do you answer ..."));
        
        myAnswer = null;
        myAnswerOptions = new ArrayList<Component>();
        hasSubmitted = false;
        mySubmitButton = new JButton("Submit");
//        myRetreatButton = new JButton("Retreat");
        
        mySubmitButton.addMouseListener(new submitMouseClickListener());
//        myRetreatButton.addActionListener(new retreatActionListener());
                
        this.add(mySubmitButton);
//        this.add(myRetreatButton);
        
        for(Component c : myAnswerOptions) {
            this.add(c);
        }
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
    
//    public boolean hasRetreated() {
//        return hasRetreated;
//    }
    
    public void reset() {
        hasSubmitted = false;
        myAnswer = null;
//        hasRetreated = false;
    }
    
    /**
     * Sets to be either buttons or a text field
     * @param theAnswerOptions
     */
    public void setAnswerOptions(ArrayList<Component> theAnswerOptions) {
        for(Component c : theAnswerOptions) { //remove old answer options
            this.remove(c);
        }
        
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

