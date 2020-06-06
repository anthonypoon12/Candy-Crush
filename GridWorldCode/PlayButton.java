package info.gridworld.grid;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
public class PlayButton{
    public static void main(String[] args){
        JFrame frame=new JFrame("");
        frame.setSize(200,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        JButton button = new JButton("PLAY?");
        button.setSize(150,150);
        button.setVisible(true);
        panel.add(button);
    }
}
