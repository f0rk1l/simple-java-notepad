import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class simple_java_notepad implements ActionListener {

    static JTextArea textArea = new JTextArea();

    static String path = JOptionPane.showInputDialog("Enter file path");

    static boolean is_dark_theme = false;

    static String readFile() {

        try {

            FileReader fileReader = new FileReader(path);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder builder = new StringBuilder();

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n");
            }

            return builder.toString();


        } catch (Exception exception) { return ""; }

    }

    public static void main(String[] args)
    {

        try {
            if (!new File(path).exists()) {
                JOptionPane.showMessageDialog(null, "This file is not exists");
                System.exit(-1);
            }
        } catch (NullPointerException ex) {
            System.exit(-1);
        }

        JFrame frame = new JFrame();

        JButton button = new JButton("Save");

        JButton button_2 = new JButton("Open file");

        JButton change_font_button = new JButton("Change font");

        textArea.setText(readFile());

        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Font font = new Font("Arial", Font.ITALIC, 30);

        JPanel panel = new JPanel();

        textArea.setFont(font);

        button.addActionListener(new org.example.Main());

        button_2.addActionListener(new org.example.Main.changeFilePathListener());

        change_font_button.addActionListener(new org.example.Main.changeFontListener());

        panel.add(button);

        panel.add(button_2);

        panel.add(change_font_button);

        frame.getContentPane().add(BorderLayout.NORTH, panel);

        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setTitle("f0rk1l text editor");

        frame.setIconImage(new ImageIcon("C:\\Users\\User\\Desktop\\coding\\django-1\\django_1\\url_handler\\static\\url_handler\\images\\Bank.png").getImage());

        frame.setSize(1000, 1000);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {

        try {

            FileWriter writeFile = new FileWriter(path, false);

            String text = textArea.getText().toString();

            writeFile.write(text);

            JOptionPane.showMessageDialog(null, "Text writed");

            writeFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static class changeFilePathListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            path = JOptionPane.showInputDialog("Enter file path");

        }

    }

    static class changeFontListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String name = JOptionPane.showInputDialog("Enter name of font");

            try {
                int size = Integer.parseInt(JOptionPane.showInputDialog("Enter size of font"));

                textArea.setFont(new Font(name, Font.ITALIC, size));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Font size must be a number");
            }

        }

    }

}