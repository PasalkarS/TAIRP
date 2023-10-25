import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeAnalyzer extends JFrame implements ActionListener {
    private JLabel[] labels;
    private JTextField[] fields;
    private JLabel lblTotal, lblGrade, lblAverage;
    private JButton btnCalculate, btnClear, btnExit;

    public StudentGradeAnalyzer() {
        setTitle("Student Grade Analyzer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 10));

        labels = new JLabel[] {
                new JLabel("Student ID:"),
                new JLabel("Student Name:"),
                new JLabel("Physics:"),
                new JLabel("Chemistry:"),
                new JLabel("Mathematics:"),
                new JLabel("English:")
        };

        fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            mainPanel.add(labels[i]);
            fields[i] = new JTextField();
            mainPanel.add(fields[i]);
        }

        btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(this);
        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);
        btnExit = new JButton("Exit");
        btnExit.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(btnCalculate);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        lblTotal = new JLabel("Total Marks:");
        lblTotal.setForeground(Color.BLUE);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblTotal);

        lblGrade = new JLabel("Grade:");
        lblGrade.setForeground(Color.BLUE);
        lblGrade.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblGrade);

        lblAverage = new JLabel("Average:");
        lblAverage.setForeground(Color.BLUE);
        lblAverage.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblAverage);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            calculateGrade();
        } else if (e.getSource() == btnClear) {
            clearFields();
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    private void calculateGrade() {
        try {
            double[] scores = new double[fields.length - 2];
            for (int i = 2; i < fields.length; i++) {
                scores[i - 2] = Double.parseDouble(fields[i].getText());
            }

            double total = 0;
            for (double score : scores) {
                total += score;
            }

            double average = total / scores.length;
            String grade;

            if (average >= 90) {
                grade = "A";
            } else if (average >= 75) {
                grade = "B";
            } else if (average >= 60) {
                grade = "C";
            } else if (average >= 45) {
                grade = "D";
            } else {
                grade = "F";
            }

            lblTotal.setText("Total Marks: " + total);
            lblGrade.setText("Grade: " + grade);
            lblAverage.setText("Average: " + average);

            JOptionPane.showMessageDialog(this,
                    "Calculation complete:\nTotal Marks: " + total + "\nGrade: " + grade + "\nAverage: " + average,
                    "Calculation Result",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for numeric fields.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        for (int i = 0; i < fields.length; i++) {
            fields[i].setText("");
        }
        lblTotal.setText("Total Marks:");
        lblGrade.setText("Grade:");
        lblAverage.setText("Average:");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            StudentGradeAnalyzer app = new StudentGradeAnalyzer();
            app.setVisible(true);
        });
    }
}
