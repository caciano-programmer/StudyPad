package window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/** 
 * This class will open a window that will present the user with their options
 *which are search for a file, make a new file, or view the tutorial, when the 
 * user chooses one then its respective class is called so everything starts 
 * through this class.
 */
public class display extends JFrame
{
    //The three options a user may select
    JButton button1 = new JButton("Search File");
    JButton button2 = new JButton("New File");
    JButton button3 = new JButton("Tutorial");
    
    public display()
    {
        //Constructor makes an empty window with following properties
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Caciano's StudyPad");       
        Components();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * This method creates all the labels, panels, and other components that
     * will be put into the JFrame
     */
    private void Components()
    {
        JPanel panel_1 = new JPanel();
        JPanel panel_2 = new JPanel();
        panel_1.setLayout(new BorderLayout());
        JLabel label_1 = new JLabel("<html><div style=\"text-align: center;\">"
                + "WHAT WOULD YOU<br>LIKE TO DO?</html>",SwingConstants.CENTER);
        label_1.setFont(new Font("Serif", Font.BOLD, 40));
        button1.setPreferredSize(new Dimension(150, 100));
        button2.setPreferredSize(new Dimension(150, 100));
        button3.setPreferredSize(new Dimension(150, 100));
        panel_2.add(button1);
        panel_2.add(button2);
        panel_2.add(button3);
        panel_1.add(label_1, BorderLayout.NORTH);
        panel_1.add(panel_2, BorderLayout.SOUTH);
        actions act = new actions();
        button1.addActionListener(act);
        button2.addActionListener(act);
        button3.addActionListener(act);
        this.add(panel_1);
    }
    
     //This inner class is used to listen to when the user makes their choice
    private class actions implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            tutorial help = new tutorial();
            newFile file = new newFile();
            search find = new search();
            
            if(e.getSource() == button1)
                find.getFileName();
            else if(e.getSource() == button2)
                file.File();
            else if(e.getSource() == button3)
                help.tutorial();
        }
    }

}
