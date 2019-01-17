package main;

import main.velocity.VelocityTask;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Form1 extends JPanel implements ActionListener {
    public JPanel panel1;
    private JTextField txtAppName;
    private JTextField txtPackage;
    private JTextField txtProtocol;
    private JTextField txtHost;
    private JTextField txtPort;
    private JCheckBox checkProtect;
    private JButton startButton;
    private JTextField txtPrefix;
    private JComboBox  comboBox1;
     private JTextField conString;
    private JCheckBox checkhideManyToOneColumn;

    public Form1() {


        comboBox1.addItem("MYSQL");

        comboBox1.addItem("MSSQL");


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    String databaseType= String.valueOf(comboBox1.getSelectedItem());
                    VelocityTask VelocityTask=new VelocityTask();
                    VelocityTask.startVelocity(
                            txtAppName.getText(),
                            txtProtocol.getText(),
                            txtHost.getText(),
                            txtPort.getText(),
                            txtPrefix.getText(),
                            txtPackage.getText(),
                            checkProtect.isSelected(),
                            databaseType,
                            conString.getText(),
                            checkhideManyToOneColumn.isSelected()
                    );
                    JOptionPane.showMessageDialog(null,"Done");

                }
                catch (Exception ex)
                {
                    if(ex.getCause()!=null && ex.getCause().getMessage()!=null)
                    {
                        JOptionPane.showMessageDialog(null,  ex.getCause().getMessage()+ "   :  "+  ex.getMessage());

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,    ex.getMessage());

                    }

                }

           }
        });

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.print("fffff");

    }


    static class ActiveComboItem {
        private final Object item;

        public ActiveComboItem(Object item) { this.item = item; }

        @Override
        public boolean equals(Object other) {
            return item == null ? other == null : item.equals(other);
        }

        @Override
        public String toString() {
            return String.format("Database: %s", item); }
    }

    class MyItemListener implements ItemListener {
        // This method is called only if a new item has been selected.
        public void itemStateChanged(ItemEvent evt) {
            JComboBox cb = (JComboBox) evt.getSource();

            Object item = evt.getItem();

            if (evt.getStateChange() == ItemEvent.SELECTED) {
                // Item was just selected
            } else if (evt.getStateChange() == ItemEvent.DESELECTED) {
                // Item is no longer selected
            }
        }
    }
}
