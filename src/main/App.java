package main;

import javax.swing.*;

public class App {

    public static void main(String[] args)
    {
        JFrame jFrame=new JFrame("Form1");
        jFrame.setContentPane(new Form1().panel1);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);


    }

}
