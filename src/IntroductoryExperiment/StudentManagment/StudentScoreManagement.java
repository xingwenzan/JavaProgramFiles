package IntroductoryExperiment.StudentManagment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentScoreManagement {
    private JTabbedPane tabbedPane1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JButton orderButton;
    private JTextArea textArea1;
    private JButton selectButton;
    private JTextField textField3;
    private JTextArea textArea2;

    Manager manager = new Manager();

    public StudentScoreManagement() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textField1.getText();
                int score = Integer.parseInt(textField2.getText());
                manager.add(id, score);
            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("排序");
                manager.sort();
                for (Student student : manager.getDatabase()) {
                    String out = student.getId() + "\t" + student.getScore() + "\n";
                    textArea1.append(out);
                }
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("成绩查询");
                String id = textField1.getText();
                for (Student student : manager.getDatabase()) {
                    if (student.getId().equals(id)) {
                        String out = student.getId() + "\t" + student.getScore() + "\n";
                        textArea2.append(out);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("学生成绩管理");
        StudentScoreManagement studentScoreManagement = new StudentScoreManagement();
        jFrame.setBounds(100, 100, 300, 300);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.add(studentScoreManagement.tabbedPane1);
        jFrame.setVisible(true);
    }
}
