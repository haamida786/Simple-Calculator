import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    JTextField textField;
    double num1, num2, result;
    String operator;

    public Calculator() {
        // Frame settings
        setTitle("Simple Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Text field
        textField = new JTextField();
        textField.setBounds(30, 40, 280, 40);
        textField.setEditable(false);
        add(textField);

        // Buttons
        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", "C", "=", "+"
        };

        int x = 30, y = 100;
        for (int i = 0; i < buttons.length; i++) {
            JButton btn = new JButton(buttons[i]);
            btn.setBounds(x, y, 60, 40);
            btn.addActionListener(this);
            add(btn);

            x += 70;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 60;
            }
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) {
            textField.setText(textField.getText() + input);
        } else if (input.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(textField.getText());
            operator = input;
            textField.setText("");
        } else if (input.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if (num2 == 0) {
                        textField.setText("Error");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
        } else if (input.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
