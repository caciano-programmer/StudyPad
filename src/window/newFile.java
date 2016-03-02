package window;

import Directory.createFile;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 * This class is used to open a window in which the user may type their notes or
 * view existing notes and then be able to save their progress as well
 */
public class newFile extends JFrame 
{
    private JMenu menu;
    private final JMenuItem submit = new JMenuItem("Submit");
    private final JMenuItem undo = new JMenuItem("undo");
    private JTextArea area;
    private final UndoManager manager = new UndoManager();
    
    //Creates a window and calls the components
    public void File()
    {
        this.setSize(600, 500);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("Caciano's StudyPad");
        components();
        this.setVisible(true);
    }
    
    //This method opens a file that already exits inside the 
    //"C:\\CacianoStudyPad\\" directory, the file name is given through the 
    //parameter x
    public void writeFile(String x)
    {
        area.setText("");
        try(FileReader reader = new FileReader("C:\\CacianoStudyPad\\" + x))
        {
            area.read(reader, x);
        }
        catch(FileNotFoundException ex) {}
        catch(IOException ex) {}
    }
    
    //Create all the components to go inside JFrame
    private void components()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        area = new JTextArea("Type Here...");
        area.setFont(new Font("Serif", Font.PLAIN, 12));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(area, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JMenuBar menubar = new JMenuBar();
        menu = new JMenu("Menu");
        menu.add(submit);
        menu.add(undo);
        listen action = new listen();
        submit.addActionListener(action);
        undo.addActionListener(action);
        area.getDocument().addUndoableEditListener(new listen());
        menubar.add(menu);
        this.setJMenuBar(menubar);
        this.add(panel);
        this.add(scroll);
    }
    
    //Used to close window when called
    private void closeView()
    {
        this.dispose();
    }
    
    /**
     * This inner class implements the listeners that will listen if a user
     * clicks on submit or if the user wishes to undo an action
     */
    private class listen implements ActionListener, UndoableEditListener
    {
        @Override
        public void undoableEditHappened(UndoableEditEvent e)
        {
            manager.addEdit(e.getEdit());
        }
        @Override
        public void actionPerformed( ActionEvent e ) 
        {
            int result;
            Calendar times = Calendar.getInstance();
            createFile file = new createFile(); 
            String output_1, output_2, output_3, fileName;
            search find = new search();
            JPanel panel = new JPanel();          
            JTextField input_1 = new JTextField(15);
            JTextField input_2 = new JTextField(15);
            JTextField input_3 = new JTextField(15);
            JLabel word_1 = new JLabel("KeyWord:");
            JLabel word_2 = new JLabel("KeyWord:");
            JLabel word_3 = new JLabel("KeyWord:");
            panel.add(word_1);
            panel.add(input_1);
            panel.add(Box.createHorizontalStrut(20));
            panel.add(word_2);
            panel.add(input_2);
            panel.add(Box.createHorizontalStrut(20));
            panel.add(word_3);
            panel.add(input_3);
            
            if(e.getSource() == submit)
            {
                do
                {
                    result = JOptionPane.showConfirmDialog(null, panel,
                            "KeyWords", JOptionPane.OK_CANCEL_OPTION);
                    output_1 = input_1.getText();
                    output_2 = input_2.getText();
                    output_3 = input_3.getText();
                    if( result == JOptionPane.OK_OPTION
                            && !find.isValidWord(output_1, output_2, output_3) )
                    {
                        JOptionPane.showMessageDialog(null, "<html>You must "
                                + "enter atleast 1 KeyWord, and also all<br> "
                                + "valid KeyWords must contain atleast one "
                                + "alphabetic letter,</html>");
                    }
                }
                while(result == JOptionPane.OK_OPTION && 
                        !find.isValidWord(output_1, output_2, output_3));
                if(result == JOptionPane.OK_OPTION)
                {
                    fileName = (times.get(Calendar.MONTH) + 1) + "_" + 
                            times.get(Calendar.DAY_OF_MONTH) + "_" + 
                            times.get(Calendar.YEAR) + "_" + output_1 + "_" +
                            output_2 + "_" + output_3 + "_";
                    file.file(fileName, area.getText());
                    closeView();
                }             
            }
            else if(e.getSource() == undo)
            {
                try
                {
                    if( manager.canUndo() )
                        manager.undo();
                } catch(CannotUndoException exp) {}
            }
        }
    }
}
