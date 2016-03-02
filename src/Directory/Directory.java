package Directory;
import java.io.File;
import javax.swing.JOptionPane;


public class Directory
{
    //Creates a directory if not already created
    public void makeDirectory()
    {
        File file = new File("C:\\CacianoStudyPad");
        if(!file.exists())
        {
            file.mkdir();
            JOptionPane.showMessageDialog(null, "<html>If this is your'e first "
                    + "time here<br>please read the tutorial!");
        }
    }
}
