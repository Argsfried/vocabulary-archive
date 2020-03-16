 /*
@author Arukane
Title: Vocabulary Library
Comment: My Vocabulary Library
--------------------------------------------------------------------------------
Interface Content:
A - Home: Displays random word of the day.
B - Add Vocabulary: Add words in the ArrayList and in the Database.
C - List of Vacublary: Shows the entire ArrayList content.
D - Remove Vocabulary: Removes word in the ArrayList.
E - Search Vocabulary: Search Word in the ArrayList.

Additional Learnings:
- Toplevel Containers - JFrame
- Lightweight Containers - JPanel
 */
package vocabulary_library;
import java.sql.*;
import javax.sql.DataSource;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

//Import End//
class MainInterface 
{
    MainInterface()
    {
        
//        autoRun();autoRun
        selection();
        databaseInteraction();
        MainInterfaceContent();
        MainFrameWork.setVisible(true);
    }
    vocabularyInterface vocabs = new vocabularyInterface();
    VocabularyArchive vocab = new VocabularyArchive("","");
    
    private JFrame MainFrameWork;
    //MainFrameWork Components//
    private JLabel menuBarWallpaper;
    private JPanel MenuBar;
    private JButton HomeMenuButton;
    private JButton goToAdd;
    private JButton goToHome;
    private JButton goToList;
    private JButton goToRemove;
    private JButton goToSearch;
    private JButton AddMenuButtonALT;
    private JButton HomeMenuButtonALT;
    private JButton ListMenuButtonALT;
    private JButton RemoveMenuButtonALT;
    private JButton SearchMenuButtonALT;
    private ImageIcon AllmenuBarContent = new ImageIcon(getClass().getResource("/Images/backMenuBase.png"));
    private ImageIcon menuBarIcon = new ImageIcon(getClass().getResource("/Images/menuBarWallpaper.jpg"));
    private ImageIcon goToAddIcon = new ImageIcon(getClass().getResource("/Images/addBase.png"));
    private ImageIcon goToHomeIcon = new ImageIcon(getClass().getResource("/Images/homeBase.png"));
    private ImageIcon goToListIcon = new ImageIcon(getClass().getResource("/Images/listBase.png"));
    private ImageIcon menuBarContent = new ImageIcon(getClass().getResource("/Images/menuBase.png"));
    private ImageIcon goToSearchIcon = new ImageIcon(getClass().getResource("/Images/searchBase.png"));
    private ImageIcon goToRemoveIcon = new ImageIcon(getClass().getResource("/Images/removeBase.png"));
    
    private JPanel HomeFrameWork;
    //HomeFrameWork Components//
    private JLabel homeDesign;
    private JButton wordPerDay;
    private Random rand = new Random();
    private ImageIcon wordPerDayIcon = new ImageIcon(getClass().getResource("/Images/bookBase.png"));
    private ImageIcon homeImage = new ImageIcon(getClass().getResource("/Images/homeWallPaper.jpg"));
    private Image homeImage1 = homeImage.getImage();
    private Image homeImage2 = homeImage1.getScaledInstance(homeImage1.getWidth(homeDesign),homeImage1.getHeight(homeDesign),Image.SCALE_SMOOTH);
    private ImageIcon homeImage3 = new ImageIcon(homeImage2);
    
    private JPanel AddFrameWork;
    //AddFrameWork Components//
    private JLabel addDesign;
     private JButton submitWord;
    private JButton AddMenuButton;
    private JTextField wordContainer;
    private JTextArea descriptionContainer;
    private JScrollPane descriptionScrollPane;
    private String word;
    private ImageIcon addWallpaper = new ImageIcon(getClass().getResource("/Images/addWallpaper.jpg"));
    
    private JPanel ListFrameWork;
    //ListFrameWork Components//
    private JLabel listDesign;
    private JPanel listPanel;
    private JButton ListMenuButton;
    private JButton showList;
    private JButton refreshButton;
    private JScrollPane listPanelScroll;
    private JTextField wordList[];
    private JTextArea descriptionList[];
    private JScrollPane descriptionListPane[];
    private JLabel numbering[];
    private boolean switchB = true;
    private int wordListLength;
    private ImageIcon listWallpaper = new ImageIcon(getClass().getResource("/Images/listWallpaper.jpg"));
    private ImageIcon refreshIcon = new ImageIcon(getClass().getResource("/Images/refreshBase.png"));
    
    private JPanel RemoveFrameWork;
    //RemoveFrameWork Components//
    private JLabel removeDesign;
    private JButton RemoveMenuButton;
    private JButton removeWord; 
    private JTextField removeContainer;
    private ImageIcon removeWallpaper = new ImageIcon(getClass().getResource("/Images/removeWallpaper.jpg"));
    
    private JPanel SearchFrameWork;
    //SearchFrameWork Components//
    private JButton SearchMenuButton;
    private JLabel searchDesign;
    private JButton searchButton;
    private JTextField searchWordContainer;
    private JTextArea searchDescriptionContainer;
    private JScrollPane searchScrollPane;
    private ImageIcon searchWallpaper = new ImageIcon(getClass().getResource("/Images/searchWallpaper.jpg"));
    
    private JOptionPane option[] = new JOptionPane[16];
    
    //Database//
    Connection con;
    Statement stat;
    ResultSet res,ress;
    PreparedStatement pst,pstt;
    
    public void selection()
    {
        try
        {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:angelopogil.db";
        String username = "root";
        String password = "iloveyou167";
//              Class.forName("org.apache.derby.jdbc.ClientDriver");
           
        con = (Connection) DriverManager.getConnection(url); 
        }
        catch(ClassNotFoundException ex)
        {
            Logger.getLogger(Vocabulary_Library.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
//    
//    public void autoRun()
//    {
//        String command = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqld.exe";
//        try
//        {
//            Process process = Runtime.getRuntime().exec(command);
//        } 
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//    }
    
    public void databaseInteraction()
    {
        try
        {
        String sql = "SELECT * FROM wordlist";
        pst = con.prepareStatement(sql);
        res = pst.executeQuery();
        while (res.next())
        {
            String holder = res.getString("word");
            if(vocabs.wordContain(holder)==true)
            {
                
            }
            else if (vocabs.wordContain(holder)==false)
            {
                vocabs.setWordHolder(holder);
                vocabs.sortAscending();
                vocabs.sortAscendingB();
                wordList = new JTextField[vocabs.size()+1];
                descriptionList = new JTextArea[vocabs.size()+1];
                descriptionListPane = new JScrollPane[vocabs.size()+1];
                numbering = new JLabel[vocabs.size()+1];
                wordListLength=wordList.length;
            }
        }
        res.close();
        
        String sql2 = "SELECT * FROM descriptionlist";
        pstt = con.prepareStatement(sql2);
        ress = pstt.executeQuery();
        while (ress.next())
        {            
            String holderr = ress.getString("description");

            if(vocabs.descriptionContain(holderr)==true)
            {
                
            }
            else if(vocabs.descriptionContain(word)==false)
            {
                vocabs.setDescriptionHolder(holderr);
                vocabs.sortAscending();
                vocabs.sortAscendingB();
                wordList = new JTextField[vocabs.size()+1];
                descriptionList = new JTextArea[vocabs.size()+1];
                descriptionListPane = new JScrollPane[vocabs.size()+1];
                numbering = new JLabel[vocabs.size()+1];
                wordListLength=wordList.length;
            }
        }
        ress.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    //Database//
    
    private void MainInterfaceContent()
    {
        //Components//
        //MainFrameWork Components//
        UIManager.put("OptionPane.messageFont", new Font("Comic Sans MS",Font.PLAIN,18));
        UIManager.put("OptionPane.background", new Color(0,0,0));
        UIManager.put("OptionPane.foreground", new Color(51, 147, 255));
        UIManager.put("OptionPane.border", new LineBorder(Color.white, 1));
        
        HomeMenuButton = new JButton();
        HomeMenuButton.setBounds(230,135,125,50);
        // HomeMenuButton.setBackground(new Color(111,111,111));
        HomeMenuButton.setIcon(menuBarContent);
        HomeMenuButton.setBorder(new RoundedBorder(50));
        HomeMenuButton.setContentAreaFilled(false);
        HomeMenuButton.setFocusPainted(false);
        HomeMenuButton.setBorderPainted(false);
        HomeMenuButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        HomeMenuButton.validate();
        //        HomeMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        HomeMenuButton.addActionListener(this::HomeMenuButton);
        HomeMenuButton.addMouseListener(new MouseAdapter()
        { 
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                MenuBarContentClicked(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                MenuBarContentEntered(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                MenuBarContentExited(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                MenuBarContentPressed(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                MenuBarContentReleased(evt);
            }
        });
        HomeMenuButton.setVisible(true);
        
        HomeMenuButtonALT = new JButton();
        HomeMenuButtonALT.setBounds(230,135,125,50);
        //        HomeMenuButtonALT.setBackground(new Color(0,0,0,150));
        HomeMenuButtonALT.setIcon(AllmenuBarContent);
        HomeMenuButtonALT.setBorder(new RoundedBorder(100));
        HomeMenuButtonALT.setContentAreaFilled(false);
        HomeMenuButtonALT.setFocusPainted(false);
        HomeMenuButtonALT.setBorderPainted(false);
        HomeMenuButtonALT.setLayout(null);
        HomeMenuButtonALT.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        HomeMenuButtonALT.validate();
        HomeMenuButtonALT.addActionListener(this::HomeMenuALT);
        HomeMenuButtonALT.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                HomeALTClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                HomeALTPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                HomeALTExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                HomeALTEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                HomeALTReleased(evt);
            }
        });
        HomeMenuButtonALT.setVisible(true);
        
        AddMenuButton = new JButton();
        AddMenuButton.setBounds(230,135,125,50);
         // AddMenuButton.setBackground(new Color(111,111,111));
        AddMenuButton.setIcon(menuBarContent);
        AddMenuButton.setBorder(new RoundedBorder(50));
        AddMenuButton.setContentAreaFilled(false);
        AddMenuButton.setFocusPainted(false);
        AddMenuButton.setBorderPainted(false);
        AddMenuButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        AddMenuButton.validate();
        //        AddMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        AddMenuButton.addActionListener(this::AddMenuButton);
        AddMenuButton.addMouseListener(new MouseAdapter()
        { 
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                MenuBarContentAddClicked(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                MenuBarContentAddEntered(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                MenuBarContentAddExited(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                MenuBarContentAddPressed(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                MenuBarContentAddReleased(evt);
            }
        });
        AddMenuButton.setVisible(false);
        
        AddMenuButtonALT = new JButton();
        AddMenuButtonALT.setBounds(230,135,125,50);
        //        AddMenuButtonALT.setBackground(new Color(0,0,0,150));
        AddMenuButtonALT.setIcon(AllmenuBarContent);
        AddMenuButtonALT.setBorder(new RoundedBorder(100));
        AddMenuButtonALT.setContentAreaFilled(false);
        AddMenuButtonALT.setFocusPainted(false);
        AddMenuButtonALT.setBorderPainted(false);
        AddMenuButtonALT.setLayout(null);
        AddMenuButtonALT.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        AddMenuButtonALT.validate();
        AddMenuButtonALT.addActionListener(this::AddMenuALT);
        AddMenuButtonALT.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                AddALTClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                AddALTPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                AddALTExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                AddALTEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                AddALTReleased(evt);
            }
        });
        AddMenuButtonALT.setVisible(false);
        
        ListMenuButton = new JButton();
        ListMenuButton.setBounds(230,90,125,50);
        //        ListMenuButton.setBackground(new Color(0,0,0,150));
        ListMenuButton.setIcon(menuBarContent);
        ListMenuButton.setBorder(new RoundedBorder(100));
        ListMenuButton.setContentAreaFilled(false);
        ListMenuButton.setFocusPainted(false);
        ListMenuButton.setBorderPainted(false);
        ListMenuButton.setLayout(null);
        ListMenuButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        ListMenuButton.validate();
        ListMenuButton.addActionListener(this::ListMenuButton);
        ListMenuButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                ListClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                ListPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                ListExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                ListEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                ListReleased(evt);
            }
        });
        ListMenuButton.setVisible(false);
        
        ListMenuButtonALT = new JButton();
        ListMenuButtonALT.setBounds(230,135,125,50);
        //        ListMenuButtonALT.setBackground(new Color(0,0,0,150));
        ListMenuButtonALT.setIcon(AllmenuBarContent);
        ListMenuButtonALT.setBorder(new RoundedBorder(100));
        ListMenuButtonALT.setContentAreaFilled(false);
        ListMenuButtonALT.setFocusPainted(false);
        ListMenuButtonALT.setBorderPainted(false);
        ListMenuButtonALT.setLayout(null);
        ListMenuButtonALT.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        ListMenuButtonALT.validate();
        ListMenuButtonALT.addActionListener(this::ListMenuALT);
        ListMenuButtonALT.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                ListALTClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                ListALTPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                ListALTExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                ListALTEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                ListALTReleased(evt);
            }
        });
        ListMenuButtonALT.setVisible(false);
        
        RemoveMenuButton = new JButton();
        RemoveMenuButton.setBounds(230,135,125,50);
        // RemoveMenuButton.setBackground(new Color(111,111,111));
        RemoveMenuButton.setIcon(menuBarContent);
        RemoveMenuButton.setBorder(new RoundedBorder(50));
        RemoveMenuButton.setContentAreaFilled(false);
        RemoveMenuButton.setFocusPainted(false);
        RemoveMenuButton.setBorderPainted(false);
        RemoveMenuButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        RemoveMenuButton.validate();
        //        RemoveMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        RemoveMenuButton.addActionListener(this::RemoveMenuButton);
        RemoveMenuButton.addMouseListener(new MouseAdapter()
        { 
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                RemoveClicked(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                RemoveEntered(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                RemoveExited(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                RemovePressed(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                RemoveReleased(evt);
            }
        });
        RemoveMenuButton.setVisible(false);
        
        RemoveMenuButtonALT = new JButton();
        RemoveMenuButtonALT.setBounds(230,135,125,50);
        //        RemoveMenuButtonALT.setBackground(new Color(0,0,0,150));
        RemoveMenuButtonALT.setIcon(AllmenuBarContent);
        RemoveMenuButtonALT.setBorder(new RoundedBorder(100));
        RemoveMenuButtonALT.setContentAreaFilled(false);
        RemoveMenuButtonALT.setFocusPainted(false);
        RemoveMenuButtonALT.setBorderPainted(false);
        RemoveMenuButtonALT.setLayout(null);
        RemoveMenuButtonALT.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        RemoveMenuButtonALT.validate();
        RemoveMenuButtonALT.addActionListener(this::RemoveMenuALT);
        RemoveMenuButtonALT.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                RemoveALTClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                RemoveALTPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                RemoveALTExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                RemoveALTEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                RemoveALTReleased(evt);
            }
        });
        RemoveMenuButtonALT.setVisible(false);
        
        SearchMenuButton = new JButton();
        SearchMenuButton.setBounds(230,135,125,50);
        // SearchMenuButton.setBackground(new Color(111,111,111));
        SearchMenuButton.setIcon(menuBarContent);
        SearchMenuButton.setBorder(new RoundedBorder(50));
        SearchMenuButton.setContentAreaFilled(false);
        SearchMenuButton.setFocusPainted(false);
        SearchMenuButton.setBorderPainted(false);
        SearchMenuButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        SearchMenuButton.validate();
        //        SearchMenuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SearchMenuButton.addActionListener(this::SearchMenuButton);
        SearchMenuButton.addMouseListener(new MouseAdapter()
        { 
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                SearchClicked(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                SearchEntered(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                SearchExited(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                SearchPressed(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                SearchReleased(evt);
            }
        });
        SearchMenuButton.setVisible(false);
        
        SearchMenuButtonALT = new JButton();
        SearchMenuButtonALT.setBounds(230,135,125,50);
        //        SearchMenuButtonALT.setBackground(new Color(0,0,0,150));
        SearchMenuButtonALT.setIcon(AllmenuBarContent);
        SearchMenuButtonALT.setBorder(new RoundedBorder(100));
        SearchMenuButtonALT.setContentAreaFilled(false);
        SearchMenuButtonALT.setFocusPainted(false);
        SearchMenuButtonALT.setBorderPainted(false);
        SearchMenuButtonALT.setLayout(null);
        SearchMenuButtonALT.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        SearchMenuButtonALT.validate();
        SearchMenuButtonALT.addActionListener(this::SearchMenuALT);
        SearchMenuButtonALT.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                SearchALTClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                SearchALTPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                SearchALTExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                SearchALTEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                SearchALTReleased(evt);
            }
        });
        SearchMenuButtonALT.setVisible(false);
        
        goToList = new JButton();
        goToList.setBounds(230,345,125,50);
        //        goToList.setBackground(new Color(0,0,0,150));
        goToList.setIcon(goToListIcon);
        goToList.setBorder(new RoundedBorder(100));
        goToList.setContentAreaFilled(false);
        goToList.setFocusPainted(false);
        goToList.setBorderPainted(false);
        goToList.setLayout(null);
        goToList.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        goToList.validate();
        goToList.addActionListener(this::goToList);
        goToList.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                goToListClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                goToListPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                goToListExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                goToListEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                goToListReleased(evt);
            }
        });
        goToList.setVisible(false);
        
        goToHome = new JButton();
        goToHome.setBounds(230,205,125,50);
        //        goToHome.setBackground(new Color(0,0,0,150));
        goToHome.setIcon(goToHomeIcon);
        goToHome.setBorder(new RoundedBorder(100));
        goToHome.setContentAreaFilled(false);
        goToHome.setFocusPainted(false);
        goToHome.setBorderPainted(false);
        goToHome.setLayout(null);
        goToHome.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        goToHome.validate();
        goToHome.addActionListener(this::goToHome);
        goToHome.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                goToHomeClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                goToHomePressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                goToHomeExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                goToHomeEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                goToHomeReleased(evt);
            }
        });
        goToHome.setVisible(false);
        
        goToAdd = new JButton();
        goToAdd.setBounds(230,275,125,50);
        //        goToAdd.setBackground(new Color(0,0,0,150));
        goToAdd.setIcon(goToAddIcon);
        goToAdd.setBorder(new RoundedBorder(100));
        goToAdd.setContentAreaFilled(false);
        goToAdd.setFocusPainted(false);
        goToAdd.setBorderPainted(false);
        goToAdd.setLayout(null);
        goToAdd.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        goToAdd.validate();
        goToAdd.addActionListener(this::goToAdd);
        goToAdd.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                goToAddClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                goToAddPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                goToAddExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                goToAddEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                goToAddReleased(evt);
            }
        });
        goToAdd.setVisible(false);
        
        goToRemove = new JButton();
        goToRemove.setBounds(223,415,140,50);
        //        goToRemove.setBackground(new Color(0,0,0,150));
        goToRemove.setIcon(goToRemoveIcon);
        goToRemove.setBorder(new RoundedBorder(100));
        goToRemove.setContentAreaFilled(false);
        goToRemove.setFocusPainted(false);
        goToRemove.setBorderPainted(false);
        goToRemove.setLayout(null);
        goToRemove.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        goToRemove.validate();
        goToRemove.addActionListener(this::goToRemove);
        goToRemove.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                goToRemoveClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                goToRemovePressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                goToRemoveExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                goToRemoveEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                goToRemoveReleased(evt);
            }
        });
        goToRemove.setVisible(false);
        
        goToSearch = new JButton();
        goToSearch.setBounds(223,485,140,50);
        //        goToSearch.setBackground(new Color(0,0,0,150));
        goToSearch.setIcon(goToSearchIcon);
        goToSearch.setBorder(new RoundedBorder(100));
        goToSearch.setContentAreaFilled(false);
        goToSearch.setFocusPainted(false);
        goToSearch.setBorderPainted(false);
        goToSearch.setLayout(null);
        goToSearch.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        goToSearch.validate();
        goToSearch.addActionListener(this::goToSearch);
        goToSearch.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                goToSearchClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                goToSearchPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                goToSearchExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                goToSearchEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                goToSearchReleased(evt);
            }
        });
        goToSearch.setVisible(false);
        
        menuBarWallpaper = new JLabel();
        menuBarWallpaper.setSize(600,700);
        menuBarWallpaper.setIcon(menuBarIcon);
        menuBarWallpaper.validate();
        menuBarWallpaper.setLayout(null);
        menuBarWallpaper.setVisible(true);
        
        MenuBar = new JPanel();
        MenuBar.setSize(600,700);
        //        MenuBar.setBackground(new Color(0,0,0,150));
        MenuBar.setEnabled(true);
        MenuBar.setLayout(null);
        MenuBar.setVisible(false);
        
        //MainFrameWork//
        MainFrameWork = new JFrame();
        MainFrameWork.setSize(606,728);
        MainFrameWork.setResizable(false);
        MainFrameWork.setTitle("Vocabulary Library");
        MainFrameWork.setEnabled(true);
        MainFrameWork.setLayout(null);
        MainFrameWork.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/booko.png")));
        MainFrameWork.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainFrameWork.setVisible(false);
        
        //HomeFrameWork Components//
        homeDesign = new JLabel();
        //        homeDesign.setBackground(new Color(100,100,100));
        homeDesign.setSize(600,700);
        homeDesign.setIcon(homeImage3);
        homeDesign.setLayout(null);
        homeDesign.validate();
        homeDesign.setVisible(true);
        
        wordPerDay = new JButton();
        wordPerDay.setBounds(223,550,140,50);
        //        wordPerDay.setBackground(new Color(0,0,0,150));
        wordPerDay.setIcon(wordPerDayIcon);
        wordPerDay.setBorder(new RoundedBorder(100));
        wordPerDay.setContentAreaFilled(false);
        wordPerDay.setFocusPainted(false);
        wordPerDay.setBorderPainted(false);
        wordPerDay.setLayout(null);
        wordPerDay.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        wordPerDay.validate();
        wordPerDay.addActionListener(this::wordPerDay);
        wordPerDay.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                wordPerDayClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                wordPerDayPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                wordPerDayExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                wordPerDayEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                wordPerDayReleased(evt);
            }
        });
        wordPerDay.setVisible(true);
        
        //HomeFrameWork//
        HomeFrameWork = new JPanel();
        HomeFrameWork.setSize(600,700);
        //        HomeFrameWork.setBackground(new Color(0,0,0,200));
        HomeFrameWork.setLayout(null);
        HomeFrameWork.setEnabled(true);
        HomeFrameWork.setVisible(true);
 
        //AddFrameWork Components//
        addDesign = new JLabel();
        //        addDesign.setBackground(new Color(100,100,100));
        addDesign.setSize(600,700);
        addDesign.setIcon(addWallpaper);
        addDesign.setLayout(null);
        addDesign.validate();
        addDesign.setVisible(true);
        
        wordContainer = new JTextField();
        wordContainer.setBackground(new Color(0,0,0));
        wordContainer.setFont(new Font("Comic Sans MS",1,20));
        wordContainer.setForeground(new Color(51, 147, 255));
        wordContainer.setOpaque(true);
        wordContainer.setLayout(null);
        wordContainer.setBounds(50,230,350,50);
        wordContainer.setText("");
        wordContainer.setEnabled(true);
        wordContainer.setVisible(false);
        
        descriptionContainer = new JTextArea();
        //        descriptionContainer.setColumns(20);
        //        descriptionContainer.setRows(5);
        descriptionContainer.setBackground(new Color(0,0,0));
        descriptionContainer.setFont(new Font("Comic Sans MS",1,16));
        descriptionContainer.setForeground(new Color(51, 147, 255));
        //        descriptionContainer.append("");
        descriptionContainer.setBorder(new LineBorder(Color.white, 1));    
        descriptionContainer.setBounds(50,310,500,350);
        descriptionContainer.setText("");
        descriptionContainer.setEnabled(true);
        descriptionContainer.setVisible(false);
        
        descriptionScrollPane = new JScrollPane();
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setBounds(50,310,500,350);
        descriptionScrollPane.getViewport().setBackground(Color.black);
        descriptionScrollPane.getViewport().add(descriptionContainer);
        
        submitWord = new JButton();
        submitWord.setBounds(420,230,125,50);
        //        submitWord.setBackground(new Color(0,0,0,150));
        submitWord.setIcon(goToAddIcon);
        submitWord.setBorder(new RoundedBorder(100));
        submitWord.setContentAreaFilled(false);
        submitWord.setFocusPainted(false);
        submitWord.setBorderPainted(false);
        submitWord.setLayout(null);
        submitWord.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        submitWord.validate();
        submitWord.addActionListener(this::submitWord);
        submitWord.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                submitWordClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                submitWordPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                submitWordExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                submitWordEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                submitWordReleased(evt);
            }
        });
        submitWord.setVisible(false);
        
        //AddFrameWork//
        AddFrameWork = new JPanel();
        AddFrameWork.setSize(600,700);
        //        AddFrameWork.setBackground(new Color(0,0,0,200));
        //        AddFrameWork.setEnabled(false);
        //        AddFrameWork.setBorder(new EmptyBorder(5,5,5,5));
        AddFrameWork.setLayout(null);
        AddFrameWork.setVisible(false);

        //ListFrameWork Components//
        listDesign = new JLabel();
        //        homeDesign.setBackground(new Color(100,100,100));
        listDesign.setSize(600,700);
        listDesign.setIcon(listWallpaper);
        listDesign.setLayout(null);
        listDesign.validate();
        listDesign.setVisible(true);
        
        refreshButton = new JButton();
        refreshButton.setBounds(290,145,150,50);
        //        refreshButton.setBackground(new Color(0,0,0,150));
        refreshButton.setIcon(refreshIcon);
        //        refreshButton.setBorder(new RoundedBorder(100));
        refreshButton.setContentAreaFilled(false);
        refreshButton.setFocusPainted(false);
        refreshButton.setBorderPainted(false);
        refreshButton.setLayout(null);
        //        refreshButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        refreshButton.validate();
        refreshButton.addActionListener(this::refreshButton);
        refreshButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                refreshButtonClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                refreshButtonPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                refreshButtonExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                refreshButtonEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                refreshButtonReleased(evt);
            }
        });
        refreshButton.setVisible(false);
        
        showList = new JButton();
        showList.setBounds(160,145,125,50);
        //        showList.setBackground(new Color(0,0,0,150));
        showList.setIcon(goToListIcon);
        //        showList.setBorder(new RoundedBorder(100));
        showList.setContentAreaFilled(false);
        showList.setFocusPainted(false);
        showList.setBorderPainted(false);
        showList.setLayout(null);
        //        showList.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        showList.validate();
        showList.addActionListener(this::showList);
        showList.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                showListClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                showListPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                showListExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                showListEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                showListReleased(evt);
            }
        });
        showList.setVisible(false);
        
        listPanel = new JPanel(new MigLayout("gap 15"));
        listPanel.setBackground(Color.black);
        listPanel.setBorder(new LineBorder(Color.white,1));
        listPanel.setBounds(25,200,550,475);
        listPanel.setVisible(false);
        
        listPanelScroll = new JScrollPane();
        listPanelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        listPanelScroll.setBounds(25,200,550,475);
        listPanelScroll.getViewport().setBackground(Color.black);
        listPanelScroll.getViewport().add(listPanel);
        
        //ListFrameWork//
        ListFrameWork = new JPanel();
        ListFrameWork.setSize(600,700);
        //        HomeFrameWork.setBackground(new Color(0,0,0,200));
        ListFrameWork.setLayout(null);
        ListFrameWork.setEnabled(true);
        ListFrameWork.setVisible(true);

        //RemoveFrameWork Components//
        removeDesign = new JLabel();
        //        homeDesign.setBackground(new Color(100,100,100));
        removeDesign.setSize(600,700);
        removeDesign.setIcon(removeWallpaper);
        removeDesign.setLayout(null);
        removeDesign.validate();
        removeDesign.setVisible(true);
        
        removeContainer = new JTextField();
        removeContainer.setBackground(new Color(0,0,0));
        removeContainer.setFont(new Font("Comic Sans MS",1,20));
        removeContainer.setForeground(new Color(51, 147, 255));
        removeContainer.setOpaque(true);
        removeContainer.setLayout(null);
        removeContainer.setBounds(115,230,350,50);
        removeContainer.setText("");
        removeContainer.setEnabled(true);
        removeContainer.setVisible(false);
        
        removeWord = new JButton();
        removeWord.setBounds(223,330,140,50);
        //        removeWord.setBackground(new Color(0,0,0,150));
        removeWord.setIcon(goToRemoveIcon);
        removeWord.setBorder(new RoundedBorder(100));
        removeWord.setContentAreaFilled(false);
        removeWord.setFocusPainted(false);
        removeWord.setBorderPainted(false);
        removeWord.setLayout(null);
        removeWord.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        removeWord.validate();
        removeWord.addActionListener(this::removeWord);
        removeWord.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                removeWordClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                removeWordPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                removeWordExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                removeWordEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                removeWordReleased(evt);
            }
        });
        removeWord.setVisible(false);
        
        //RemoveFrameWork//
        RemoveFrameWork = new JPanel();
        RemoveFrameWork.setSize(600,700);
        //        HomeFrameWork.setBackground(new Color(0,0,0,200));
        RemoveFrameWork.setLayout(null);
        RemoveFrameWork.setEnabled(true);
        RemoveFrameWork.setVisible(true);
        
        //SearchFrameWork Components//
        searchDesign = new JLabel();
        //        homeDesign.setBackground(new Color(100,100,100));
        searchDesign.setSize(600,700);
        searchDesign.setIcon(searchWallpaper);
        searchDesign.setLayout(null);
        searchDesign.validate();
        searchDesign.setVisible(true);
        
        searchWordContainer = new JTextField();
        searchWordContainer.setBackground(new Color(0,0,0));
        searchWordContainer.setFont(new Font("Comic Sans MS",1,20));
        searchWordContainer.setForeground(new Color(51, 147, 255));
        searchWordContainer.setOpaque(true);
        searchWordContainer.setLayout(null);
        searchWordContainer.setBounds(50,230,350,50);
        searchWordContainer.setText("");
        searchWordContainer.setEnabled(true);
        searchWordContainer.setVisible(false);
        
        searchDescriptionContainer = new JTextArea();
        //        searchDescriptionContainer.setColumns(20);
        //        searchDescriptionContainer.setRows(5);
        searchDescriptionContainer.setBackground(new Color(0,0,0));
        searchDescriptionContainer.setFont(new Font("Comic Sans MS",1,16));
        searchDescriptionContainer.setForeground(new Color(51, 147, 255));
        //        searchDescriptionContainer.append("");
        searchDescriptionContainer.setBorder(new LineBorder(Color.white, 1));    
        searchDescriptionContainer.setBounds(50,310,500,350);
        searchDescriptionContainer.setText("");
        searchDescriptionContainer.setEnabled(true);
        searchDescriptionContainer.setVisible(false);
        
        searchScrollPane = new JScrollPane();
        searchScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        searchScrollPane.setBounds(50,310,500,350);
        searchScrollPane.getViewport().setBackground(Color.black);
        searchScrollPane.getViewport().add(searchDescriptionContainer);
        
        searchButton = new JButton();
        searchButton.setBounds(410,230,136,50);
        //        searchButton.setBackground(new Color(0,0,0,150));
        searchButton.setIcon(goToSearchIcon);
        searchButton.setBorder(new RoundedBorder(100));
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setLayout(null);
        searchButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        searchButton.validate();
        searchButton.addActionListener(this::searchButton);
        searchButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                searchButtonClicked(evt);
            }
            @Override
            public void mousePressed(MouseEvent evt)
            {
                searchButtonPressed(evt);
            }
            @Override
            public void mouseExited(MouseEvent evt)
            {
                searchButtonExited(evt);
            }
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                searchButtonEntered(evt);
            }
            @Override
            public void mouseReleased(MouseEvent evt)
            {
                searchButtonReleased(evt);
            }
        });
        searchButton.setVisible(false);

        //SearchFrameWork//
        SearchFrameWork = new JPanel();
        SearchFrameWork.setSize(600,700);
        //        HomeFrameWork.setBackground(new Color(0,0,0,200));
        SearchFrameWork.setLayout(null);
        SearchFrameWork.setEnabled(true);
        SearchFrameWork.setVisible(true);
        
        //Insert//
        //MainFrameWork Insert//
        MainFrameWork.add(MenuBar);
        MainFrameWork.add(HomeFrameWork);
        MainFrameWork.add(AddFrameWork);
        MainFrameWork.add(ListFrameWork);
        MainFrameWork.add(RemoveFrameWork);
        MainFrameWork.add(SearchFrameWork);
        MenuBar.add(goToAdd);
        MenuBar.add(goToHome);
        MenuBar.add(goToList);
        MenuBar.add(goToRemove);
        MenuBar.add(goToSearch);
        MenuBar.add(AddMenuButtonALT);
        MenuBar.add(HomeMenuButtonALT);
        MenuBar.add(ListMenuButtonALT);
        MenuBar.add(RemoveMenuButtonALT);
        MenuBar.add(SearchMenuButtonALT);
        MenuBar.add(menuBarWallpaper);
       
        //HomeFramework Insert//
        HomeFrameWork.add(HomeMenuButton);
        HomeFrameWork.add(wordPerDay);
        HomeFrameWork.add(homeDesign);
        
        //AddFrameWork Components Insert//
        AddFrameWork.add(AddMenuButton);
        AddFrameWork.add(submitWord);
        AddFrameWork.add(wordContainer);
        AddFrameWork.add(descriptionScrollPane);
        AddFrameWork.add(addDesign);
        
        //ListFrameWork Components Insert//
        ListFrameWork.add(ListMenuButton);
        ListFrameWork.add(showList);
        ListFrameWork.add(refreshButton);
        ListFrameWork.add(listPanelScroll);
        ListFrameWork.add(listDesign);
       
        //RemoveFrameWork Components Insert//
        RemoveFrameWork.add(RemoveMenuButton);
        RemoveFrameWork.add(removeContainer);
        RemoveFrameWork.add(removeWord);
        RemoveFrameWork.add(removeDesign);

        //SearchFrameWork Components Insert//
        SearchFrameWork.add(SearchMenuButton);
        SearchFrameWork.add(searchButton);
        SearchFrameWork.add(searchWordContainer);
        SearchFrameWork.add(searchScrollPane);
        SearchFrameWork.add(searchDesign);
    }
    
    //MainFrameWork Action Perform//
    private void goToAdd(ActionEvent e)
    {
        MenuBar.setVisible(false);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        HomeFrameWork.setVisible(false);
        
        //Add
        AddFrameWork.setVisible(true);
        AddMenuButton.setVisible(true);
        AddMenuButtonALT.setVisible(true);
        wordContainer.setVisible(true);
        descriptionContainer.setVisible(true);
        submitWord.setVisible(true);
        
        //List
        ListFrameWork.setVisible(false);
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        
        //Remove
        RemoveFrameWork.setVisible(false);
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        
        //Search
        SearchFrameWork.setVisible(false);
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
    }
    private void goToHome(ActionEvent e)
    {
        MenuBar.setVisible(false);
        
        //Home
        HomeMenuButton.setVisible(true);
        HomeMenuButtonALT.setVisible(true);
        HomeFrameWork.setVisible(true);
        wordPerDay.setVisible(true);
        
        //Add
        AddFrameWork.setVisible(false);
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        
        //List
        ListFrameWork.setVisible(false);
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        
        //Remove
        RemoveFrameWork.setVisible(false);
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        
        //Search
        SearchFrameWork.setVisible(false);
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
    }
    private void goToList(ActionEvent e)
    {
        MenuBar.setVisible(false);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        HomeFrameWork.setVisible(false);
        
        //Add
        AddFrameWork.setVisible(false);
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        
        //List
        ListFrameWork.setVisible(true);
        ListMenuButton.setVisible(true);
        ListMenuButtonALT.setVisible(true);
        showList.setVisible(true);
        listPanel.setVisible(true);
        refreshButton.setVisible(true);
        
        
        //Remove
        RemoveFrameWork.setVisible(false);
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        
        //Search
        SearchFrameWork.setVisible(false);
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
    }
    
    private void goToRemove(ActionEvent e)
    {
        MenuBar.setVisible(false);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        HomeFrameWork.setVisible(false);
        
        //Add
        AddFrameWork.setVisible(false);
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        
        //List
        ListFrameWork.setVisible(false);
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        
        //Remove
        RemoveFrameWork.setVisible(true);
        RemoveMenuButton.setVisible(true);
        RemoveMenuButtonALT.setVisible(true);
        removeContainer.setVisible(true);
        removeWord.setVisible(true);
        
        //Search
        SearchFrameWork.setVisible(false);
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
    }
    
    private void goToSearch(ActionEvent e)
    {
        MenuBar.setVisible(false);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        HomeFrameWork.setVisible(false);
        
        //Add
        AddFrameWork.setVisible(false);
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        
        //List
        ListFrameWork.setVisible(false);
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        
        //Remove
        RemoveFrameWork.setVisible(false);
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        
        //Search
        SearchFrameWork.setVisible(true);
        SearchMenuButton.setVisible(true);
        SearchMenuButtonALT.setVisible(true);
        searchWordContainer.setVisible(true);
        searchDescriptionContainer.setVisible(true);
        searchButton.setVisible(true);
    }
    
    private void MenuBarContentEntered(MouseEvent e)
    {
        HomeMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuEntered.png")));
    }
    private void MenuBarContentClicked(MouseEvent e)
    {
        HomeMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void MenuBarContentExited(MouseEvent e)
    {
        HomeMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    private void MenuBarContentPressed(MouseEvent e)
    {
        HomeMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void MenuBarContentReleased(MouseEvent e)
    {
        HomeMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    //Space//
    private void HomeALTClicked(MouseEvent e)
    {
        HomeMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void HomeALTPressed(MouseEvent e)
    {
        HomeMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void HomeALTEntered(MouseEvent e)
    {
        HomeMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuEntered.png")));
    }
    private void HomeALTExited(MouseEvent e)
    {
        HomeMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    private void HomeALTReleased(MouseEvent e)
    {
        HomeMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    //Space//
    private void MenuBarContentAddEntered(MouseEvent e)
    {
        AddMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuEntered.png")));
    }
    private void MenuBarContentAddClicked(MouseEvent e)
    {
        AddMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void MenuBarContentAddExited(MouseEvent e)
    {
        AddMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    private void MenuBarContentAddPressed(MouseEvent e)
    {
        AddMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void MenuBarContentAddReleased(MouseEvent e)
    {
        AddMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    //Space//
    private void AddALTClicked(MouseEvent e)
    {
        AddMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void AddALTPressed(MouseEvent e)
    {
        AddMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void AddALTEntered(MouseEvent e)
    {
        AddMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuEntered.png")));
    }
    private void AddALTExited(MouseEvent e)
    {
        AddMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    private void AddALTReleased(MouseEvent e)
    {
        AddMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    //Space//
    private void ListEntered(MouseEvent e)
    {
        ListMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuEntered.png")));
    }
    private void ListClicked(MouseEvent e)
    {
        ListMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void ListExited(MouseEvent e)
    {
        ListMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    private void ListPressed(MouseEvent e)
    {
        ListMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void ListReleased(MouseEvent e)
    {
        ListMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    //Space//
    private void ListALTClicked(MouseEvent e)
    {
        ListMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void ListALTPressed(MouseEvent e)
    {
        ListMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void ListALTEntered(MouseEvent e)
    {
        ListMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuEntered.png")));
    }
    private void ListALTExited(MouseEvent e)
    {
        ListMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    private void ListALTReleased(MouseEvent e)
    {
        ListMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    //Space//
    private void RemoveEntered(MouseEvent e)
    {
        RemoveMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuEntered.png")));
    }
    private void RemoveClicked(MouseEvent e)
    {
        RemoveMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void RemoveExited(MouseEvent e)
    {
        RemoveMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    private void RemovePressed(MouseEvent e)
    {
        RemoveMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void RemoveReleased(MouseEvent e)
    {
        RemoveMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    //Space//
    private void RemoveALTClicked(MouseEvent e)
    {
        RemoveMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void RemoveALTPressed(MouseEvent e)
    {
        RemoveMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void RemoveALTEntered(MouseEvent e)
    {
        RemoveMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuEntered.png")));
    }
    private void RemoveALTExited(MouseEvent e)
    {
        RemoveMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    private void RemoveALTReleased(MouseEvent e)
    {
        RemoveMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    //Space//
    private void SearchClicked(MouseEvent e)
    {
        SearchMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void SearchPressed(MouseEvent e)
    {
        SearchMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuPressed.png")));
    }
    private void SearchEntered(MouseEvent e)
    {
        SearchMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuEntered.png")));
    }
    private void SearchExited(MouseEvent e)
    {
        SearchMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    private void SearchReleased(MouseEvent e)
    {
        SearchMenuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menuBase.png")));
    }
    //Space//
    private void SearchALTClicked(MouseEvent e)
    {
        SearchMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void SearchALTPressed(MouseEvent e)
    {
        SearchMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuPressed.png")));
    }
    private void SearchALTEntered(MouseEvent e)
    {
        SearchMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuEntered.png")));
    }
    private void SearchALTExited(MouseEvent e)
    {
        SearchMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    private void SearchALTReleased(MouseEvent e)
    {
        SearchMenuButtonALT.setIcon(new ImageIcon(getClass().getResource("/Images/backMenuBase.png")));
    }
    //Space//
    private void goToHomeClicked(MouseEvent e)
    {
        goToHome.setIcon(new ImageIcon(getClass().getResource("/Images/homePressed.png")));
    }
    private void goToHomePressed(MouseEvent e)
    {
        goToHome.setIcon(new ImageIcon(getClass().getResource("/Images/homePressed.png")));
    }
    private void goToHomeEntered(MouseEvent e)
    {
        goToHome.setIcon(new ImageIcon(getClass().getResource("/Images/homeEntered.png")));
    }
    private void goToHomeExited(MouseEvent e)
    {
        goToHome.setIcon(new ImageIcon(getClass().getResource("/Images/homeBase.png")));
    }
    private void goToHomeReleased(MouseEvent e)
    {
        goToHome.setIcon(new ImageIcon(getClass().getResource("/Images/homeBase.png")));
    }
    //Space//
    private void goToAddClicked(MouseEvent e)
    {
        goToAdd.setIcon(new ImageIcon(getClass().getResource("/Images/addPressed.png")));
    }
    private void goToAddPressed(MouseEvent e)
    {
        goToAdd.setIcon(new ImageIcon(getClass().getResource("/Images/addPressed.png")));
    }
    private void goToAddEntered(MouseEvent e)
    {
        goToAdd.setIcon(new ImageIcon(getClass().getResource("/Images/addEntered.png")));
    }
    private void goToAddExited(MouseEvent e)
    {
        goToAdd.setIcon(new ImageIcon(getClass().getResource("/Images/addBase.png")));
    }
    private void goToAddReleased(MouseEvent e)
    {
        goToAdd.setIcon(new ImageIcon(getClass().getResource("/Images/addBase.png")));
    }
    //Space//
    private void goToListClicked(MouseEvent e)
    {
        goToList.setIcon(new ImageIcon(getClass().getResource("/Images/listPressed.png")));
    }
    private void goToListPressed(MouseEvent e)
    {
        goToList.setIcon(new ImageIcon(getClass().getResource("/Images/listPressed.png")));
    }
    private void goToListEntered(MouseEvent e)
    {
        goToList.setIcon(new ImageIcon(getClass().getResource("/Images/listEntered.png")));
    }
    private void goToListExited(MouseEvent e)
    {
        goToList.setIcon(new ImageIcon(getClass().getResource("/Images/listBase.png")));
    }
    private void goToListReleased(MouseEvent e)
    {
        goToList.setIcon(new ImageIcon(getClass().getResource("/Images/listBase.png")));
    }
    //Space//
    private void goToRemoveClicked(MouseEvent e)
    {
        goToRemove.setIcon(new ImageIcon(getClass().getResource("/Images/removePressed.png")));
    }
    private void goToRemovePressed(MouseEvent e)
    {
        goToRemove.setIcon(new ImageIcon(getClass().getResource("/Images/removePressed.png")));
    }
    private void goToRemoveEntered(MouseEvent e)
    {
        goToRemove.setIcon(new ImageIcon(getClass().getResource("/Images/removeEntered.png")));
    }
    private void goToRemoveExited(MouseEvent e)
    {
        goToRemove.setIcon(new ImageIcon(getClass().getResource("/Images/removeBase.png")));
    }
    private void goToRemoveReleased(MouseEvent e)
    {
        goToRemove.setIcon(new ImageIcon(getClass().getResource("/Images/removeBase.png")));
    }  
    //Space//
    private void goToSearchClicked(MouseEvent e)
    {
        goToSearch.setIcon(new ImageIcon(getClass().getResource("/Images/searchPressed.png")));
    }
    private void goToSearchPressed(MouseEvent e)
    {
        goToSearch.setIcon(new ImageIcon(getClass().getResource("/Images/searchPressed.png")));
    }
    private void goToSearchEntered(MouseEvent e)
    {
        goToSearch.setIcon(new ImageIcon(getClass().getResource("/Images/searchEntered.png")));
    }
    private void goToSearchExited(MouseEvent e)
    {
        goToSearch.setIcon(new ImageIcon(getClass().getResource("/Images/searchBase.png")));
    }
    private void goToSearchReleased(MouseEvent e)
    {
        goToSearch.setIcon(new ImageIcon(getClass().getResource("/Images/searchBase.png")));
    }
    //Space//
    private void submitWordClicked(MouseEvent e)
    {
        submitWord.setIcon(new ImageIcon(getClass().getResource("/Images/addBase.png")));
    }
    private void submitWordPressed(MouseEvent e)
    {
        submitWord.setIcon(new ImageIcon(getClass().getResource("/Images/addPressed.png")));
    }
    private void submitWordEntered(MouseEvent e)
    {
        submitWord.setIcon(new ImageIcon(getClass().getResource("/Images/addEntered.png")));
    }
    private void submitWordExited(MouseEvent e)
    {
        submitWord.setIcon(new ImageIcon(getClass().getResource("/Images/addBase.png")));
    }
    private void submitWordReleased(MouseEvent e)
    {
        submitWord.setIcon(new ImageIcon(getClass().getResource("/Images/addBase.png")));
    }
    //Space//
    private void showListClicked(MouseEvent e)
    {
        showList.setIcon(new ImageIcon(getClass().getResource("/Images/listBase.png")));
    }
    private void showListPressed(MouseEvent e)
    {
        showList.setIcon(new ImageIcon(getClass().getResource("/Images/listPressed.png")));
    }
    private void showListEntered(MouseEvent e)
    {
        showList.setIcon(new ImageIcon(getClass().getResource("/Images/listEntered.png")));
    }
    private void showListExited(MouseEvent e)
    {
        showList.setIcon(new ImageIcon(getClass().getResource("/Images/listBase.png")));
    }
    private void showListReleased(MouseEvent e)
    {
        showList.setIcon(new ImageIcon(getClass().getResource("/Images/listBase.png")));
    }
    //Space//
    private void refreshButtonClicked(MouseEvent e)
    {
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/Images/refreshBase.png")));
    }
    private void refreshButtonPressed(MouseEvent e)
    {
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/Images/refreshPressed.png")));
    }
    private void refreshButtonEntered(MouseEvent e)
    {
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/Images/refreshEntered.png")));
    }
    private void refreshButtonExited(MouseEvent e)
    {
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/Images/refreshBase.png")));
    }
    private void refreshButtonReleased(MouseEvent e)
    {
        refreshButton.setIcon(new ImageIcon(getClass().getResource("/Images/refreshBase.png")));
    }
    //Space////Space//
    private void removeWordClicked(MouseEvent e)
    {
        removeWord.setIcon(new ImageIcon(getClass().getResource("/Images/removeBase.png")));
    }
    private void removeWordPressed(MouseEvent e)
    {
        removeWord.setIcon(new ImageIcon(getClass().getResource("/Images/removePressed.png")));
    }
    private void removeWordEntered(MouseEvent e)
    {
        removeWord.setIcon(new ImageIcon(getClass().getResource("/Images/removeEntered.png")));
    }
    private void removeWordExited(MouseEvent e)
    {
        removeWord.setIcon(new ImageIcon(getClass().getResource("/Images/removeBase.png")));
    }
    private void removeWordReleased(MouseEvent e)
    {
        removeWord.setIcon(new ImageIcon(getClass().getResource("/Images/removeBase.png")));
    }  
    //Space//
    private void searchButtonClicked(MouseEvent e)
    {
        searchButton.setIcon(new ImageIcon(getClass().getResource("/Images/searchPressed.png")));
    }
    private void searchButtonPressed(MouseEvent e)
    {
        searchButton.setIcon(new ImageIcon(getClass().getResource("/Images/searchPressed.png")));
    }
    private void searchButtonEntered(MouseEvent e)
    {
        searchButton.setIcon(new ImageIcon(getClass().getResource("/Images/searchEntered.png")));
    }
    private void searchButtonExited(MouseEvent e)
    {
        searchButton.setIcon(new ImageIcon(getClass().getResource("/Images/searchBase.png")));
    }
    private void searchButtonReleased(MouseEvent e)
    {
        searchButton.setIcon(new ImageIcon(getClass().getResource("/Images/searchBase.png")));
    }
    //Space//
    private void wordPerDayClicked(MouseEvent e)
    {
        wordPerDay.setIcon(new ImageIcon(getClass().getResource("/Images/bookBase.png")));
    }
    private void wordPerDayPressed(MouseEvent e)
    {
        wordPerDay.setIcon(new ImageIcon(getClass().getResource("/Images/bookPressed.png")));
    }
    private void wordPerDayEntered(MouseEvent e)
    {
        wordPerDay.setIcon(new ImageIcon(getClass().getResource("/Images/bookEntered.png")));
    }
    private void wordPerDayExited(MouseEvent e)
    {
        wordPerDay.setIcon(new ImageIcon(getClass().getResource("/Images/bookBase.png")));
    }
    private void wordPerDayReleased(MouseEvent e)
    {
        wordPerDay.setIcon(new ImageIcon(getClass().getResource("/Images/bookBase.png")));
    }
    
    //HomeFrameWork Action Peform//
    private void HomeMenuButton(ActionEvent e)
    {
        MenuBar.setVisible(true);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(true);
        goToHome.setVisible(true);
        wordPerDay.setVisible(false);
        
        //Add
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        goToAdd.setVisible(true);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        goToList.setVisible(true);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        goToRemove.setVisible(true);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
        goToSearch.setVisible(true);
    }
    private void HomeMenuALT(ActionEvent e)
    {
        MenuBar.setVisible(false);
        
        //Home
        HomeMenuButton.setVisible(true);
        HomeMenuButtonALT.setVisible(false);
        wordPerDay.setVisible(true);
        goToHome.setVisible(false);
        
        //Add
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        goToAdd.setVisible(false);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        goToList.setVisible(false);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        goToRemove.setVisible(false);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
        goToSearch.setVisible(false);
    }
    
    public void wordPerDay(ActionEvent evt)
    {
        try
        {
            option[0] = new JOptionPane();
            option[0].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[0].showMessageDialog(null,vocabs.getDescriptionHolder(rand.nextInt(vocabs.size()+1))); 
        }
        catch(IllegalArgumentException ee)
        {
            option[1] = new JOptionPane();
            option[1].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[1].showMessageDialog(null,"No data yet.");
        }
    }

    //AddFrameWork Action Perform//
    private void AddMenuButton(ActionEvent e)
    {
        MenuBar.setVisible(true);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        goToHome.setVisible(true);
        
        //Add
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(true);
        wordContainer.setVisible(false);
        descriptionContainer.setVisible(false);
        submitWord.setVisible(false);
        goToAdd.setVisible(true);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        goToList.setVisible(true);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        goToRemove.setVisible(true);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
        goToSearch.setVisible(true);
    }
    private void AddMenuALT(ActionEvent e)
    {
        MenuBar.setVisible(false);
       
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        goToHome.setVisible(false);
        
        //Add
        AddMenuButton.setVisible(true);
        AddMenuButtonALT.setVisible(false);
        wordContainer.setVisible(true);
        descriptionContainer.setVisible(true);
        submitWord.setVisible(true);
        goToAdd.setVisible(false);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        goToList.setVisible(false);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        goToRemove.setVisible(false);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
        goToSearch.setVisible(false);
    }
   
    private void submitWord(ActionEvent e)
    {
        
        if(wordContainer.getText().equals("")||descriptionContainer.getText().equals(""))
        {
            option[2] = new JOptionPane();
            option[2].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[2].showMessageDialog(null,"Please fill the content of the word and description.");
        }
        
        else if(vocabs.wordContain(wordContainer.getText())==true||vocabs.descriptionContain(descriptionContainer.getText())==true)
        {
            option[3] = new JOptionPane();
            option[3].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[3].showMessageDialog(null,"Already in the list.");
            wordContainer.setText("");
            descriptionContainer.setText("");
        }

        else
        {
        //        word = wordContainer.getText();
        //        descript = descriptionContainer.getText();
        //        vocabs.setWordHolder(word);
        //        vocabs.setDescriptionHolder(descript);
        //        wordContainer.setText("");
        //        descriptionContainer.setText("");
            
        try
        {
            String descriptionlist = descriptionContainer.getText();
            pst = con.prepareStatement("insert into wordlist (word) values (?)");
            pst.setString(1,wordContainer.getText());
            pst.executeUpdate();
            pst = con.prepareStatement("insert into descriptionlist (description) values (?)");
            pst.setString(1,descriptionContainer.getText());
            pst.executeUpdate();
            wordContainer.setText("");
            descriptionContainer.setText("");
            
            databaseInteraction();
            
            option[4] = new JOptionPane();
            option[4].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[4].showMessageDialog(null,"Successfully added!");
            
            vocabs.sortAscending();
            vocabs.sortAscendingB();
            wordList = new JTextField[vocabs.size()+1];
            descriptionList = new JTextArea[vocabs.size()+1];
            descriptionListPane = new JScrollPane[vocabs.size()+1];
            numbering = new JLabel[vocabs.size()+1];
            wordListLength=wordList.length;
        }
        //        catch(ClassNotFoundException ex)
        //        {
        //            Logger.getLogger(Vocabulary_Library.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        }
    }

    //ListFrameWork Action Perform//
    private void ListMenuButton(ActionEvent e)
    {
        MenuBar.setVisible(true);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        goToHome.setVisible(true);
        
        //Adds
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        goToAdd.setVisible(true);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(true);
        showList.setVisible(false);
        listPanel.setVisible(false);
        showList.setVisible(false);
        refreshButton.setVisible(false);
        goToList.setVisible(true);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        goToRemove.setVisible(true);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
        goToSearch.setVisible(true);
        
        if (switchB==false)
            {
                for(int i=0;i<=wordListLength-1;i++)
                {
                    listPanel.remove(wordList[i]);
                    listPanel.remove(descriptionListPane[i]);
                    listPanel.remove(numbering[i]);
                    listPanel.setVisible(false);
                    listPanel.setVisible(true);
                }    
            switchB = true; 
            }
    }
    
    private void ListMenuALT(ActionEvent e)
    {
        MenuBar.setVisible(false);
       
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        goToHome.setVisible(false);
        
        //Add
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        goToAdd.setVisible(false);
        
        //List
        ListMenuButton.setVisible(true);
        ListMenuButtonALT.setVisible(false);
        showList.setVisible(true);
        listPanel.setVisible(true);
        showList.setVisible(true);
        refreshButton.setVisible(true);
        goToList.setVisible(false);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        goToRemove.setVisible(false);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
        goToSearch.setVisible(false);
    }
    
    private void showList(ActionEvent evt)
    {
       
        if (switchB==true)
        {
        Integer ii = vocabs.size();
        for(int i=0;i<=ii;i++)
        {
        String number = String.valueOf(i+1);
        wordList[i] = new JTextField();
        wordList[i].setBackground(new Color(0,0,0));
        wordList[i].setFont(new Font("Comic Sans MS",1,20));
        wordList[i].setForeground(new Color(51, 147, 255));
        wordList[i].setOpaque(true);
        wordList[i].setPreferredSize(new Dimension(1, 38));
        wordList[i].setText(vocabs.getWordHolder(i));
        wordList[i].setEnabled(false);
        wordList[i].setVisible(true);
        
        numbering[i] = new JLabel();
        numbering[i].setBackground(new Color(0,0,0));
        numbering[i].setFont(new Font("Comic Sans MS",1,20));
        numbering[i].setForeground(new Color(51, 147, 255));
        numbering[i].setOpaque(true);
        numbering[i].setPreferredSize(new Dimension(1, 38));
        numbering[i].setText(" "+number+". ");
        numbering[i].setBorder(new LineBorder(Color.white,1));
        numbering[i].setEnabled(true);
        numbering[i].setVisible(true);
        
        descriptionList[i] = new JTextArea();
        //        descriptionList[0].setColumns(20);
        //        descriptionList[0].setRows(5);
        descriptionList[i].setBackground(new Color(0,0,0));
        descriptionList[i].setFont(new Font("Comic Sans MS",1,16));
        descriptionList[i].setForeground(new Color(51, 147, 255));
        //        descriptionList[0].append("");
        descriptionList[i].setBorder(new LineBorder(Color.white, 1));    
        descriptionList[i].setPreferredSize(new Dimension(1, 218));
        descriptionList[i].setText(vocabs.getDescriptionHolder(i));
        descriptionList[i].setEnabled(false);
        descriptionList[i].setVisible(true);
        
        descriptionListPane[i] = new JScrollPane();
        descriptionListPane[i].setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionListPane[i].setPreferredSize(new Dimension(1, 218));
        descriptionListPane[i].getViewport().setBackground(Color.black);
        descriptionListPane[i].getViewport().add(descriptionList[i]);
        
        listPanel.add(numbering[i], "split 2, sg a");
        listPanel.add(wordList[i], "wrap, pushx, growx");
        listPanel.add(descriptionListPane[i],"span, wrap, pushx, growx");
        listPanel.setVisible(false);
        listPanel.setVisible(true);
        }
        switchB = false;
        option[5] = new JOptionPane();
        option[5].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        option[5].showMessageDialog(null,"List showed!");
        }
        else if (switchB==false)
        {
            option[6] = new JOptionPane();
            option[6].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[6].showMessageDialog(null,"List already showing!");
        }
        
    }
    
    private void refreshButton(ActionEvent evt)
    {
        
            if (switchB==false)
            {
                for(int i=0;i<=wordListLength-1;i++)
                {
                    listPanel.remove(wordList[i]);
                    listPanel.remove(descriptionListPane[i]);
                    listPanel.remove(numbering[i]);
                    listPanel.setVisible(false);
                    listPanel.setVisible(true);
                }    
            switchB = true;
            option[7] = new JOptionPane();
            option[7].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[7].showMessageDialog(null,"List refreshed!");
                
            }
            else if (switchB==true)
            {
            option[8] = new JOptionPane();
            option[8].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[8].showMessageDialog(null,"List already refreshed!");
            }
    }
    
    //RemoveFrameWork Action Perform//
    private void RemoveMenuButton(ActionEvent e)
    {
        MenuBar.setVisible(true);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        goToHome.setVisible(true);
        
        //Add
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        goToAdd.setVisible(true);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        goToList.setVisible(true);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(true);
        removeContainer.setVisible(false);
        removeWord.setVisible(false);
        goToRemove.setVisible(true);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
        goToSearch.setVisible(true);
    }
    
    private void RemoveMenuALT(ActionEvent e)
    {
        MenuBar.setVisible(false);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        goToHome.setVisible(false);
        
        //Add
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        goToAdd.setVisible(false);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        goToList.setVisible(false);
        
        //Remove
        RemoveMenuButton.setVisible(true);
        RemoveMenuButtonALT.setVisible(false);
        removeContainer.setVisible(true);
        removeWord.setVisible(true);
        goToRemove.setVisible(false);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(false);
        goToSearch.setVisible(false);
    }
 
    private void removeWord(ActionEvent e)
    {
        int determine = 0;
        String descript = "";
        if(removeContainer.getText().equals(""))
        {
            option[10] = new JOptionPane();
            option[10].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[10].showMessageDialog(null,"Please input word to be removed.");
        }
        else if(vocabs.wordContain(removeContainer.getText())==false)
        {
            JOptionPane.showMessageDialog(null,"No data yet.");
        }
        else
        {   
        try
        {
            try
            {    
            vocabs.sortAscending();
            vocabs.sortAscendingB();
            wordList = new JTextField[vocabs.size()+1];
            descriptionList = new JTextArea[vocabs.size()+1];
            descriptionListPane = new JScrollPane[vocabs.size()+1];
            numbering = new JLabel[vocabs.size()+1];
            wordListLength=wordList.length;
            
           
            String del = "DELETE FROM wordlist WHERE word =('" + removeContainer.getText() + "')";
            pst = con.prepareStatement(del);
            pst.executeUpdate();
            
            for(int i = 0;i<=vocabs.size();i++)
            {
                if(removeContainer.getText().equals(vocabs.getWordHolder(i)))
                {
                    determine++;
                    break;
                }
                else if(removeContainer.getText()!=vocabs.getWordHolder(i))
                {
                   determine++;
                }
            }
            String dell = "DELETE FROM descriptionlist WHERE description =('" + vocabs.getDescriptionHolder(determine-1) + "')";
            
            pst = con.prepareStatement(dell);
            pst.executeUpdate();
   
            vocabs.removeDescriptionHolder(removeContainer.getText());
            vocabs.removeWordHolder(removeContainer.getText());
            removeContainer.setText("");
            option[11] = new JOptionPane();
            option[11].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[11].showMessageDialog(null,"Word successfully removed!");
            vocabs.sortAscending();
            vocabs.sortAscendingB();
            wordList = new JTextField[vocabs.size()+1];
            descriptionList = new JTextArea[vocabs.size()+1];
            descriptionListPane = new JScrollPane[vocabs.size()+1];
            numbering = new JLabel[vocabs.size()+1];
            wordListLength=wordList.length;
            }
            catch(IndexOutOfBoundsException w)
            {
                option[12] = new JOptionPane();
                option[12].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                option[12].showMessageDialog(null,"No data yet.");
            }
        }
        catch(SQLException error)
        {
            error.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException ee)
        {
            option[13] = new JOptionPane();
            option[13].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[13].showMessageDialog(null,"No data yet.");
        }
        }
    }

    //SearchFrameWork Action Perform
    private void SearchMenuButton(ActionEvent e)
    {
        MenuBar.setVisible(true);
        
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        goToHome.setVisible(true);
        
        //Add
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        goToAdd.setVisible(true);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        goToList.setVisible(true);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        goToRemove.setVisible(true);
        
        //Search
        SearchMenuButton.setVisible(false);
        SearchMenuButtonALT.setVisible(true);
        searchWordContainer.setVisible(false);
        searchDescriptionContainer.setVisible(false);
        searchButton.setVisible(false);
        goToSearch.setVisible(true);
    }
    
    private void SearchMenuALT(ActionEvent e)
    {
        MenuBar.setVisible(false);
       
        //Home
        HomeMenuButton.setVisible(false);
        HomeMenuButtonALT.setVisible(false);
        goToHome.setVisible(false);
        
        //Add
        AddMenuButton.setVisible(false);
        AddMenuButtonALT.setVisible(false);
        goToAdd.setVisible(false);
        
        //List
        ListMenuButton.setVisible(false);
        ListMenuButtonALT.setVisible(false);
        goToList.setVisible(false);
        
        //Remove
        RemoveMenuButton.setVisible(false);
        RemoveMenuButtonALT.setVisible(false);
        goToRemove.setVisible(false);
        
        //Search
        SearchMenuButton.setVisible(true);
        SearchMenuButtonALT.setVisible(false);
        searchWordContainer.setVisible(true);
        searchDescriptionContainer.setVisible(true);
        searchButton.setVisible(true);
        goToSearch.setVisible(false);
    }
    
    public void searchButton(ActionEvent e)
    {
        try
        {
            boolean switchD = false;
            Integer ii = vocabs.size();
            if(searchWordContainer.getText().equals(""))
            {
                option[14] = new JOptionPane();
                option[14].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                option[14].showMessageDialog(null,"The search box is empty.");
                searchDescriptionContainer.setText("");
            }
            else if(vocabs.wordContain(searchWordContainer.getText())!=true)
            {
                option[15] = new JOptionPane();
                option[15].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                option[15].showMessageDialog(null,"Searched word is not yet in the collection.");
            }
            else if(vocabs.wordContain(searchWordContainer.getText())==true)
            {
            for(int i=0;i<=ii;i++)
            {
                if(vocabs.getWordHolder(i).equals(searchWordContainer.getText()))
                {
                    searchDescriptionContainer.setText(vocabs.getDescriptionHolder(i));
                }   
            }
            }
        }
        catch(Exception et)
        {
            option[16] = new JOptionPane();
            option[16].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            option[16].showMessageDialog(null,"No data yet.");
        }
    }
}
//Unidentified Codex//
class RoundedBorder implements Border
{
    private int radius;
    RoundedBorder(int radius)
    {
        this.radius = radius;
    }
    public Insets getBorderInsets(Component c)
    {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }
    public boolean isBorderOpaque()
    {
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}
//Unidentified Codex End//
public class Vocabulary_Library
{
    public static void main(String[] args) throws Exception
    {
//        String url = "jdbc:derby://localhost:1527/Vocabulary_Library_Database;create=true";
//        String username = "angelopogil";
//        String password = "iloveyou167";
//
//        Class.forName("org.apache.derby.jdbc.ClientDriver");
//        Connection con = DriverManager.getConnection(url,username,password);       
        new MainInterface();
    }
}

class vocabularyInterface
{
    private ArrayList<String> wordHolderr = new ArrayList<String>();
    //    Collections.unmodifiableList(wordHolderr);
    private ArrayList<String> descriptionHolderr = new ArrayList<String>();
    //    Collections.unmodifiableList(descriptionHolderr);
    //    private String wordHolder[] = new String[99];
    //    private String descriptionHolder[] = new String[99];
    private int indexNumber=0;
    public void setWordHolder(String word)
    {
        wordHolderr.add(word);
        //        return wordHolderr;
    }
    public String getWordHolder(int index)
    {
        //Arrays.copyOf(wordHolder,wordHolder.length);
        return wordHolderr.get(index);
        //   this.wordHolderr = wordHolder;
    }
    public void removeWordHolder(String word)
    {
        wordHolderr.remove(word);
        //        return wordHolderr;
    }
        //    public List getWord()
        //    {
        //        Collections.unmodifiableList(wordHolderr);
        //    }

        //    public ArrayList<String> getWordHolderContent(int index)
        //    {//Arrays.copyOf(wordHolder,wordHolder.length);
        //        return new ArrayList<String>(wordHolderr); 
        //    }

    public void setDescriptionHolder(String description)
    {
        descriptionHolderr.add(description);
    }
    public String getDescriptionHolder(int index)
    {
        return descriptionHolderr.get(index);
    }
    public void removeDescriptionHolder(String description)
    {
        descriptionHolderr.remove(description);
    }
    public int size()
    {
        return wordHolderr.size()-1;
    }  
    public ArrayList<String> sortAscending()
    {         
       Collections.sort(this.wordHolderr);         
       return this.wordHolderr;     
    }    
    public ArrayList<String> sortAscendingB()
    {         
       Collections.sort(this.descriptionHolderr);         
       return this.descriptionHolderr;     
    }
    public boolean wordContain(String word)
    {
        return wordHolderr.contains(word);
    }
    public boolean descriptionContain(String word)
    {
        return descriptionHolderr.contains(word);
    }
    /*
    public void setDescriptionHolder(int index, String description)
    {
        descriptionHolder[index] = description;
    }
    public String[] getDescriptionHolder()
    {
        return Arrays.copyOf(descriptionHolder,descriptionHolder.length);
    }
    */

    //    public void setIndexNumber(int indexNumber)
    //    {
    //        this.indexNumber = indexNumber;
    //    }
    //    public int getIndexNumber()
    //    {
    //        return indexNumber;
    //    }
}

class VocabularyArchive implements Comparable<VocabularyArchive>
{
    private String wordHolder;
    private String descriptionHolder;
    VocabularyArchive(String word, String description)
    {
        this.wordHolder = word;
        this.descriptionHolder = description;
    }
    public int compareTo(VocabularyArchive i)
    {
//        return String.compare(wordHolder,i.wordHolder)>String.compare(wordHolder,i.wordHolder)?1:-1;
        return 0;
    }
}