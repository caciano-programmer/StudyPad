package window;

import Directory.searchFile;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class does not actually do a search but merely asks the user how they
 * would want to search either by date or keyword, and then validates that their
 * search criteria is valid
 */
public class search 
{
    public void getFileName()
    {
        String fileName = decision();
        if(fileName != null)
        {
            searchFile file = new searchFile();
            file.getFiles(fileName);
        }
    }
    
    //Asks the user to either search by date or keyword
    private String decision()
    {
        String fileName = null;
        Object[] options = {"Search by Date", "Search by KeyWord", "cancel"};
        int result = JOptionPane.showOptionDialog(null, 
                "Search by Date or KeyWord?", "Search", 
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, options, options[2]);
        if(result == 0)
             fileName = searchDate();
        if(result == 1)
            fileName = searchKeyWord();
        return fileName;
    }
    
    //Gets the date the user wants to search 
    private String searchDate()
    {
        int result;
        String output_1, output_2;
        JPanel panel = new JPanel();
        JLabel month = new JLabel("Month");
        JLabel year = new JLabel("Year");
        JTextField input_1 = new JTextField(2);
        JTextField input_2 = new JTextField(4);
        panel.add(month);
        panel.add(input_1);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(year);
        panel.add(input_2);
        panel.add(Box.createHorizontalStrut(20));
        
        do
        {
            result = JOptionPane.showConfirmDialog(null, panel, "Date",
                    JOptionPane.OK_CANCEL_OPTION);
            output_1 = input_1.getText();
            output_2 = input_2.getText();
            if(!isValidDate(output_1, output_2) && 
                    result == JOptionPane.OK_OPTION)
                JOptionPane.showMessageDialog(null, "Enter a valid date!");
        }
        while(result == JOptionPane.OK_OPTION && 
                !isValidDate(output_1, output_2));
        if(result == JOptionPane.OK_OPTION)
        {
            return output_1 + "_" + output_2 + "_";
        }
        return null;
    }
    
    //Helper method to check if input date is valid
    public boolean isValidDate(String mon, String year)
    {
        int M, Y;
        try
        {
            M = Integer.parseInt(mon);
            Y = Integer.parseInt(year);
        }
        catch(NumberFormatException | NullPointerException x) { return false; }
        
        return (M >= 1 && M < 13 && Y > 2015 && Y < 3000);
    }
    
    //Helper method to check if KeyWord is valid
    public boolean isValidWord(String X, String Y, String Z)
    {
        if(X.equals("") && Y.equals("") && Z.equals(""))
            return false;
        else if(!X.equals("") && !X.matches(".*[a-zA-Z].*"))
            return false;
        else if(!Y.equals("") && !Y.matches(".*[a-zA-Z].*"))
            return false;
        else return !(!Z.equals("") && !Z.matches(".*[a-zA-Z].*"));
    }
    
    //Gets the keywords the user wishes to search by, up to three KeyWords
    private String searchKeyWord()
    {
        int result;
        String output_1, output_2, output_3, fileName;
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
        do
        {
            result = JOptionPane.showConfirmDialog(null, panel, "KeyWords",
                    JOptionPane.OK_CANCEL_OPTION);
            output_1 = input_1.getText();
            output_2 = input_2.getText();
            output_3 = input_3.getText();
            if( result == JOptionPane.OK_OPTION && !isValidWord(output_1, 
                    output_2, output_3) )
                JOptionPane.showMessageDialog(null, "<html>You must enter "
                        + "atleast 1 KeyWord, and also all<br> valid KeyWords "
                        + "must contain atleast one alphabetic letter,</html>");
        }
        while(result == JOptionPane.OK_OPTION && !isValidWord(output_1, 
                output_2, output_3));
        if(result == JOptionPane.OK_OPTION)
        {
            fileName = output_1 + "_" + output_2 + "_" + output_3 + "_";
            return fileName;
        }
        return null;
    }
    
}
