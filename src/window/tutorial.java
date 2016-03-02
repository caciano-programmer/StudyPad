package window;

import java.awt.Panel;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * A simple class that displays a window with instructions on how to user the UI
 */
public class tutorial extends JFrame
{
    public void tutorial()
    {
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Caciano's StudyPad - Tutorial");  
        makeTutorial();
        super.setVisible(true);
    }
    private void makeTutorial()
    {
        Panel panel = new Panel();
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        String tutorial = "Welcome to Caciano's StudyPad application."
                + " This application was designed to keep and maintain notes. "
                + "When it is opened as you have seen, you get the option to "
                + "make a new file or search for an existing file. "
                + "\n\nMaking a new file\n\n If you click"
                + " new file you will get a window where you can type in your'e"
                + " notes. When you are done, on the top left there "
                + "is a button "
                + "that says menu, when menu is clicked you have the option to "
                + "undo or submit. When you hit submit you will get the option "
                + "to save your'e file. When you save a file you will be"
                + " prompted to enter a keyword, this keyword is what you will "
                + "use to search for files, you may enter anywhere from 1 to 3 "
                + "keywords. For example if your'e reading a textbook you might"
                + " enter chapter.1 and section 2 as keywords.  \n\nSearching "
                + "for Files\n\nNow if you want "
                + "to search for a file just hit the search file on the main "
                + "window and you will be asked to search by keyword or if you "
                + "forgot your'e keyword or you simply want to see all your'e "
                + "files that match a certian time period."
                + " You could also search "
                + "by date. A search by date will require a "
                + "valid month and year"
                + " in which the file was made. After you have enter e valid "
                + "date or keyword(s) you will be shown a screen that "
                + "will display all the files "
                + "that match your'e search criteria the files "
                + "will be in the following "
                + "\nformat: numer of file, date of file, keywords "
                + "associated to file.\n After you see which file you want enter"
                + " the number of the file in the dialog box and your'e "
                + "file will be opened.\n\n I hope this apllication "
                + "will be of benefit to you.";
        text.setText(tutorial);
        this.add( panel.add(text) );
    }
}
