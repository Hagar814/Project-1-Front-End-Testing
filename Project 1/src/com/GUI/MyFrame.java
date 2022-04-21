package com.GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyFrame extends JFrame implements ActionListener {
    private JFrame F;

    private JButton btn ;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;

    private JPanel  P1;
    private JPanel  P2;
    private JPanel  P3;
    private JPanel  P4;
    private JPanel  P5;
    private JPanel  P6;
    private JPanel  P7;
    private JPanel  P8;

    private JTable t1;
    private String[] cols={"No","Date","Customer","Total"};
    private String[][] data= new String[4][4];
    private JTable t2;
    private String[] cols2={"No","Item Name","Item Price","Count","Item Total"};
    private String[][] data2= new String[5][5];

    private JMenuBar menu_bar;
    private JMenu file_menu;
    private JMenuItem m_load;
    private JMenuItem m_save;

    private JTextField text1;
    private JTextField text2;

    private JTextArea ta;

    String clipText = new String("");
    public MyFrame () {
       super("Sales Invoice");


        F = new JFrame("Sales Invoice");
        F.setLocation(0, 0);
        F.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ta=new JTextArea();
        setLayout(null);

        menu_bar=new JMenuBar();
        file_menu=new JMenu("File");
        m_load = new JMenuItem("Load File");
        m_load.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_DOWN_MASK));
        m_load.addActionListener(this);
       m_load.setActionCommand("l");
        m_save = new JMenuItem("Save File");
       m_save.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
       m_save.addActionListener(this);
       m_save.setActionCommand("s");
        menu_bar.add(file_menu);
        file_menu.add(m_load);
        file_menu.add(m_save);
        setJMenuBar(menu_bar);

        text1=new JTextField(20);
        text2=new JTextField(20);

        btn=new JButton("Create New Invoice");
        btn2=new JButton("      Delete      ");
        P1 = new JPanel();
        P1.setBounds(0, 650, 750, 120);
        P1.add(btn);
        P1.add(btn2);
        add(P1);

        btn3=new JButton("       Save      ");
        btn4=new JButton("      Cancel     ");
        P2 = new JPanel();
        P2.setBounds(750, 650, 750, 120);
        P2.add(btn3);
        P2.add(btn4);
        add(P2);

        t1=new JTable(data,cols);
        t1.setSize(800,800);
        t1.setLocation(0,0);

        P3 = new JPanel();
        P3.setBounds(0, 0, 750, 1300);
        add(P3);
        P3.add(new JScrollPane(t1));

        t2=new JTable(data2,cols2);
        t2.setSize(800,400);
        t2.setLocation(60,60);

        P4 = new JPanel();
        P4.setBounds(750, 200, 750, 700);
        add(P4);
        P4.add(new JScrollPane(t2));


        P5 = new JPanel();
        P5.setBounds(750, 50, 750, 80);
        add(P5);
        P5.add(new JLabel ("Invoice Date "));
        P5.add(text1);

        P6 = new JPanel();
        P6.setBounds(750, 125, 750, 50);
        add(P6);
        P6.add(new JLabel ("Customer Name"));
        P6.add(text2);

        P7 = new JPanel();
        P7.setBounds(750, 0, 750, 50);
        add(P7);
        P7.add(new JLabel ("Invoice Number"));

        P8 = new JPanel();
        P8.setBounds(750, 175, 750, 50);
        add(P8);
        P8.add(new JLabel ("Invoice Total"));


    }
  @Override
    public void actionPerformed(ActionEvent e)
    {
      switch (e.getActionCommand())
      {
          case"l":
            loadfile();
              break;
          case"s":
            savefile();
              break;
      }
    }
private void loadfile()
    {
        JFileChooser fc = new JFileChooser();
        int result =fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            FileInputStream fis=null;
            try {
           fis = new FileInputStream(path);
            int size = fis.available();
                byte[] b = new byte [size];
                fis.read(b);
                ta.setText(new String(b));
            } catch (FileNotFoundException e) {
                JOptionPane.showInputDialog("File Not found");
            } catch (IOException e) {
                JOptionPane.showInputDialog("ERROR");
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
    }
 private void savefile() {
     JFileChooser fc = new JFileChooser();
     int result = fc.showSaveDialog(this);
     if (result == JFileChooser.APPROVE_OPTION) {
         String path = fc.getSelectedFile().getPath();
         FileOutputStream fos = null;
         try {
             fos = new FileOutputStream(path);
             byte[] b = ta.getText().getBytes();
             fos.write(b);
         } catch (FileNotFoundException e) {
             JOptionPane.showInputDialog("File Not found");
         } catch (IOException e) {
             JOptionPane.showInputDialog("ERROR");
         } finally {
             try {
                 fos.close();
             } catch (IOException e) {
             }
         }
     }

 }
}

