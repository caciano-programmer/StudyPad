package Directory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class createFile
{
    //Check if a file exists if so it slightly changes the new file name
    public boolean exist(String name)
    {
        File file = new File("C:\\CacianoStudyPad\\" + name + ".txt");
        return file.exists();
    }
    
    //Creates a new file with given name and given text
    public void  file(String name, String text) 
    {
        File file = new File("C:\\CacianoStudyPad\\" + name + ".txt");
        
        if(file.exists())
        {
            file = new File("C:\\CacianoStudyPad\\" + name + "_x" + ".txt");
        }
        try(FileWriter write = new FileWriter(file))
        {
            write.write(text);
            write.flush();
        }
        catch(IOException x)
        {
            JOptionPane.showMessageDialog(null, "Sorry but there was an error "
                    + "creating the file, it is probably a windows security "
                    + "feature, please contact Caciano for assistance");
        } 
    }
}
