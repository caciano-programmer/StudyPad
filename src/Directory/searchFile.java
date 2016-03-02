package Directory;
import window.newFile;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class actually completes the search for files that match a given 
 * criteria, in this last class i made the inner class extend JFrame since i 
 * read that its bad practice to make a class extend JFrame because during 
 * large projects it might cause bugs
 */
public class searchFile
{  
    private String[] inputs;
    private final ArrayList<String> files = new ArrayList<>();
    private final boolean word = true;
    private final boolean date = false;
    
    //Gets all the files that match the search criteria
    public void getFiles(String x)
    {
        inputs = x.split("_");
        ArrayList<String> realInput = new ArrayList<>();
        //If an input is non-empty add it to the real input list
        for(String element: inputs)
            if(element.matches(".(.*)"))
                realInput.add(element);
        //If input are keywords finds valid files else if input is a date
        //then finds its respective files
        if(fileType() == word)
            matchWord(realInput);
        else
            matchDate(inputs[0] + inputs[1]);
        if(matches())
        {
            chooseFile file = new chooseFile();
            file.components();
        }
        else
            JOptionPane.showMessageDialog(null, "<html>Sorry, but there are no "
                    + "files<br> matching your'e search criteria.<br> Please"
                    + "try a different key word or different date.");
    }
    
    //If there was atleast one file matching the search criteria return true
    private boolean matches()
    {
        return files.size() >= 1;
    }
    
    //Decides whether search criteria is a date or keywords
    private boolean fileType()
    {
        try
        {
            Integer.parseInt(inputs[0]);
            return date;
        }
        catch(NumberFormatException error) { return word; }
    }

    //Adds all files that match the criteria
    private void matchWord(ArrayList<String> x)
    {
        File dir = new File("C:\\CacianoStudyPad");
        String[] temp = dir.list();
        if(temp.length > 0)
            for(String input: x)
                for(String file: temp)
                    if( file.toLowerCase().matches("(.*)_" + input.toLowerCase()
                            + "_(.*)") )
                    {
                        for(int index = 0; index < files.size(); index++)
                        {
                            if(files.get(index).equals(file))
                                files.remove(index);
                        }
                        files.add(file);
                    }                   
    }
    
    private void matchDate(String x)
    {
        File dir = new File("C:\\CacianoStudyPad");
        String[] temp = dir.list();
        if(temp.length > 0)
            for(String e1: temp)
            {
                String[] split = e1.split("_");
                if( (split[0] + split[2]).equals(x) )
                    files.add(e1);
            }
    }
    
    //This inner class creates a UI in which the user may see all the matche 
    //files, and be able to choose one
    private class chooseFile extends JFrame 
    {
        public chooseFile()
        {
            this.setSize(500, 400);
            this.setLocationRelativeTo(null);
            this.setTitle("Caciano's StudyPad");     
            this.setVisible(true);
        }
        
        public String aFile(String x)
        {
            String result = "Date: ";
            String[] temp = x.split("_");
            for(int i = 0; i < temp.length - 1; i++)
            {
                if(i < 2)
                    result = result + temp[i] + "-";
                else if(i == 2)
                    result = result + temp[i] + ",  Key Words: ";
                else
                   result = result + " " + temp[i];
            }   
            return result;
        }
        
                
        public void choice(String x)
        {
            if(choiceHelp(x))
            {
                int var = Integer.parseInt(x);
                if(var >= 1 &&  var <= files.size())  
                {
                    newFile file = new newFile();
                    file.File();
                    file.writeFile(files.get(var - 1));
                    this.dispose();
                }
                else
                    JOptionPane.showMessageDialog( null, "<html>You have to "
                        + "make sure that<br> you're input is between 1 " + 
                            files.size() + ".</html>");
            } 
            else
                JOptionPane.showMessageDialog( null, "<html>You have to make "
                        + "sure that<br> you're input is a number.</html>");
        }
                
        private boolean choiceHelp(String x)
        {
            try { Integer.parseInt(x); }
            catch(NumberFormatException e) { return false; }
            return true;
        }
        
        public void components()
        {
            JPanel panel = new JPanel(new BorderLayout());
            JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel label = new JLabel("<html>Please enter the number of the<br>"
                    + " file you wish to open.</html>");
            JTextArea text = new JTextArea(300,400);
            JTextField field = new JTextField(3);
            text.setFont(new Font("Serif", Font.PLAIN, 14));
            for(int i = 0; i < files.size(); i++)
                text.append( (i+1) + ".  " + aFile(files.get(i) ) + "\n");
            panel.add(text);
            text.setEditable(false);
            JScrollPane scroll = new JScrollPane(text, 
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            field.addActionListener((ActionEvent e) ->
            {
                if(e.getSource() == field)
                    choice(field.getText());
            });
            top.add(label, BorderLayout.EAST);
            top.add(Box.createHorizontalStrut(35));
            top.add(field);
            panel.add(top, BorderLayout.NORTH);
            panel.add(scroll);
            this.add(panel);
        }
    }
}
