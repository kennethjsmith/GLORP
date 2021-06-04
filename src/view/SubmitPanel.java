package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SubmitPanel extends JPanel{
    private JButton mySubmitButton;
    private JButton myRetreatButton;
    private boolean hasSubmitted;
    private boolean hasRetreated;
    
    public SubmitPanel() {
        mySubmitButton = new JButton("Submit");
        myRetreatButton = new JButton("Retreat");
        
        mySubmitButton.addActionListener(new submitActionListener());
        myRetreatButton.addActionListener(new retreatActionListener());
                
        this.add(mySubmitButton);
        this.add(myRetreatButton);
    }

    public boolean hasSubmitted() {
        return hasSubmitted;
    }
    
    public boolean hasRetreated() {
        return hasRetreated;
    }
    
    public void reset() {
        hasSubmitted = false;
        hasRetreated = false;
    }
    
    private class submitActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            hasRetreated = true;
        }
    }
    
    private class retreatActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            hasSubmitted = true;  
        } 
    }
    

}
