package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;




public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    String text;
    Notepad(){
        setTitle("NotePad");
        
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png"));
       Image icon = notepadIcon.getImage();
        setIconImage(icon);
        
        //menubar and menulist
        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.WHITE);
        
        JMenu file = new JMenu("File");
        file.setFont(new Font("AERIAL",Font.PLAIN,14));
        
        //items in menu
        JMenuItem newdoc = new JMenuItem("New");
           newdoc.addActionListener(this);
        
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        
        
         JMenuItem open = new JMenuItem("Open");
         open.addActionListener(this);
         open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));

          JMenuItem save = new JMenuItem("Save");
          save.addActionListener(this);
         save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
         
          JMenuItem print = new JMenuItem("Print");
          print.addActionListener(this);
         print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
         
          JMenuItem exit = new JMenuItem("Exit");
          exit.addActionListener(this);
         exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.CTRL_MASK));
         
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
       
        menubar.add(file);
        
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("AERIAL",Font.PLAIN,14));
        
        //items in menu
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        
        
         JMenuItem paste = new JMenuItem("Paste");
         paste.addActionListener(this);
         paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));

          JMenuItem cut = new JMenuItem("Cut");
          cut.addActionListener(this);
         cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
         
        JMenuItem selectall = new JMenuItem("Select All");
        selectall.addActionListener(this);
         selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
         
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);
        menubar.add(edit);
       
        
        JMenu helpmenu = new JMenu("Help");
        helpmenu.setFont(new Font("AERIAL",Font.PLAIN,14));
        
        //items in menu
        JMenuItem help = new JMenuItem("About");
        help.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
        
        helpmenu.add(help);
        menubar.add(helpmenu);
        
        
        setJMenuBar(menubar);
        
        
        //text area where we can write
        area = new JTextArea();
        area.setFont(new Font("SansSerif", Font.PLAIN, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        add(area);
        
        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
     public void actionPerformed(ActionEvent ae){
         if(ae.getActionCommand().equals("New")){
        area.setText("");
         }else if(ae.getActionCommand().equals("Open")){
             JFileChooser chooser = new JFileChooser();
             chooser.setAcceptAllFileFilterUsed(false);
             FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");
             chooser.addChoosableFileFilter(restrict);
             
             int action = chooser.showOpenDialog(this);
             if(action!= JFileChooser.APPROVE_OPTION){
                 return;
             }
             File file = chooser.getSelectedFile();
             try{
                 BufferedReader reader = new BufferedReader(new FileReader(file));
                 area.read(reader,null);
             }catch(Exception e){
                 e.printStackTrace();
             }
         }else if(ae.getActionCommand().equals("Save")){
             JFileChooser saveas = new JFileChooser();
             saveas.setApproveButtonText("Save");
             int action = saveas.showOpenDialog(this);
             if(action!= JFileChooser.APPROVE_OPTION){
                 return;
             }
             File filename = new File(saveas.getSelectedFile()+".txt");
             BufferedWriter outFile = null;
             try{
                 outFile = new BufferedWriter(new FileWriter(filename));
                 area.write(outFile);
             }catch(Exception e){
                 e.printStackTrace();
             }
             
         }else if(ae.getActionCommand().equals("Print")){
             try{
                 area.print();
                 
             }catch(Exception e){
                 e.printStackTrace();
                 
             }
             
         }else if(ae.getActionCommand().equals("Exit")){
             System.exit(0);
         }else if(ae.getActionCommand().equals("Copy")){
             text = area.getSelectedText();
         }else if(ae.getActionCommand().equals("Paste")){
             area.insert(text, area.getCaretPosition());
         }else if(ae.getActionCommand().equals("Cut")){
             text = area.getSelectedText();
             area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
         }else if(ae.getActionCommand().equals("Select All")){
             area.selectAll();
         }else if(ae.getActionCommand().equals("About")){
             new About().setVisible(true);
         }
    }
         
 
    public static void main(String[] args) {
        new Notepad();
      
    }
    
}
