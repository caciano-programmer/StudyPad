package run;
import Directory.Directory;
import window.display;

public class main 
{
    //If the directory is not already created, creates a directory, and then 
    //calls studypad constructor through which the UI is created
    public static void main(String[] args)
    {
        Directory directory = new Directory();
        directory.makeDirectory();
        display studyPad = new display();
    }
            
}
