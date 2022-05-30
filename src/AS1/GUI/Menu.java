package AS1.GUI;

import AS1.Maze.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {

    JFrame GuiFrame;

    int hsp; //--evaluate these, i altered the int.parse calls, but do you really need to store them?
    int wsp;

    MainGUI MazeGUI = null; //--Dont change this. MazeGUI shouldn't exist if the maze panel hasn't been made yet

    
    DummyClasses buttoncalls = new DummyClasses();
    public void HomeGUI() {

        //region Creation of GuiFrame
        GuiFrame = new JFrame("Mazebuilder3000");
        GuiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane pane=new JTabbedPane();
        //endregion

        //region control creation
        //Gui for drawing a maze from scratch
        JPanel MfromScratch = new JPanel();

        JButton Draw = new JButton("Draw Maze!");

        MfromScratch.add(Draw);


        MfromScratch.add(new JLabel("Please Enter Height"));
        JTextField HeightField = new JTextField(2);
        MfromScratch.add(HeightField);



        MfromScratch.add(new JLabel("Please Enter Width"));
        JTextField WidthField = new JTextField(2);
        MfromScratch.add(WidthField);

        JButton Path = new JButton("Check Maze Path");
        MfromScratch.add(Path);


        JTextField Name = new JTextField("Maze Name", 6);
        MfromScratch.add(Name);


        MfromScratch.add(new JTextField("Author Name"));
        JRadioButton aLogo = new JRadioButton("Auto place logo");

        MfromScratch.add(aLogo);

        JButton placeLogo = new JButton("Place Image");
        MfromScratch.add(placeLogo);

        JButton saveMaze = new JButton("Save Maze");
        MfromScratch.add(saveMaze);

        //Gui for drawing a maze automatically
        JPanel MAutoGen = new JPanel();


        JButton autogenbut = new JButton("Generate a Maze!");
        MAutoGen.add(autogenbut);

        MAutoGen.add(new JLabel("Please Enter Height"));
        MAutoGen.add(new JTextField(2));
        MAutoGen.add(new JLabel("Please Enter Width"));
        MAutoGen.add(new JTextField(2));



        JButton AutoPath = new JButton("Check Maze Path");
        MAutoGen.add(AutoPath);

        MAutoGen.add(new JTextField("Maze Name"));
        MAutoGen.add(new JTextField("Author Name"));

        JRadioButton agLogo = new JRadioButton("Auto place logo");
        MAutoGen.add(agLogo);

        JButton gplaceLogo = new JButton("Place Image");
        MAutoGen.add(gplaceLogo);

        JButton gsaveMaze = new JButton("Save Maze");
        MAutoGen.add(gsaveMaze);
        //endregion

        //region Listener Event stuff
        //region listener creation
        ActionListener drawbutton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //calls method from dummy classes to draw

                //--wrap stuff like this. Those input fields allow text, text wont parse. you need to catch that exception
                try{
                    hsp = Integer.parseInt(HeightField.getText());
                    wsp = Integer.parseInt(WidthField.getText());

                    MazeGUI = new MainGUI(new Maze(hsp,wsp));//--changed MainGUI so it auto sizes the render panel to fit the cells
                }
                catch (NumberFormatException parseException){
                    //--do something about the exception, or ignore them.
                    //--Honestly, we should make an error popup prompt for things like this, just to say "this string aint a number! fix it!"
                }
            }
        };
        ActionListener generatebutton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                //calls method from dummy classes to auto generate

            }
        };
        ActionListener checkbutton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        ActionListener savebutton = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //calls method from dummy classes to save maze

                if(MazeGUI == null) //--with the changes I made, this needs to be checked to avoid a "you pressed the button but there was no maze yet!"
                    return;

                String SaveName = (Name.getText() + ".png");
                MazeGUI.MazeRPanel.SaveBuffer2File(SaveName);//--Avoid making any "new" panels cause the MainGUI now provides the panel and frame as an accessible final variable
            }
        };
        ActionListener generatepath = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //calls method from dummy classes to check path
            }
        };
        ActionListener placeimage = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //calls method from dummy classes to place image
            }
        };
        ActionListener autoplace = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //calls method from dummy classes to auto place image
            }
        };
        //endregion

        //region listener assignment
        Draw.addActionListener(drawbutton);
        HeightField.addActionListener(drawbutton);
        Path.addActionListener(generatepath);
        aLogo.addActionListener(autoplace);
        placeLogo.addActionListener(placeimage);
        saveMaze.addActionListener(savebutton);
        autogenbut.addActionListener(generatebutton);
        AutoPath.addActionListener(generatepath);
        agLogo.addActionListener(autoplace);
        gplaceLogo.addActionListener(placeimage);
        gplaceLogo.addActionListener(placeimage);
        gsaveMaze.addActionListener(savebutton);
        //endregion
        //endregion

        //Gui for maze browser

        //table names
        String[] columnNames = {"Maze Name", "Author","Time Created","Last Edited","Difficulty"};

        //temp set of objects for table
        Object[][] mazelist = {{"maze 1", "maze man","01/01/22","28/04/22", "Hard"}, {"maze 2", "maze woman","02/01/19","25/04/22", "Hard"}};
        JPanel MazeBrowser = new JPanel(new BorderLayout());
        JTable MazeTable = new JTable(mazelist, columnNames);
        MazeBrowser.add(MazeTable, BorderLayout.CENTER);
        MazeBrowser.add(MazeTable.getTableHeader(), BorderLayout.NORTH);
        JMenuBar mb = new JMenuBar();
        JMenu eo = new JMenu("Maze Export Options");
        JMenuItem exporttofile = new JMenuItem("Export Selected to File");
        JMenuItem exporttodb = new JMenuItem("Export Selected to DataBase");
        eo.add(exporttofile); eo.add(exporttodb);
        mb.add(eo);
        MazeBrowser.add(mb, BorderLayout.SOUTH);


        pane.add("Draw a Maze from Scratch",MfromScratch);
        pane.add("Generate a Maze", MAutoGen);
        pane.add("Maze List", MazeBrowser);

        GuiFrame.getContentPane().add(pane);
        GuiFrame.setPreferredSize(new Dimension(450,300));
        GuiFrame.setSize(450, 300);

        GuiFrame.setVisible(true);
    }
}
