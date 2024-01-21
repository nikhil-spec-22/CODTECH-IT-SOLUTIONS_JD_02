import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            numberButtons[i].addActionListener(this);
            panel.add(numberButtons[i]);
        }

        operatorButtons = new JButton[4];
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");

        operatorButtons[0] = addButton;
        operatorButtons[1] = subButton;
        operatorButtons[2] = mulButton;
        operatorButtons[3] = divButton;

        for (int i = 0; i < 4; i++) {
            operatorButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            operatorButtons[i].addActionListener(this);
            panel.add(operatorButtons[i]);
        }

        eqButton = new JButton("=");
        eqButton.setFont(new Font("Arial", Font.PLAIN, 20));
        eqButton.addActionListener(this);

        clrButton = new JButton("C");
        clrButton.setFont(new Font("Arial", Font.PLAIN, 20));
        clrButton.addActionListener(this);

        panel.add(eqButton);
        panel.add(clrButton);

        // Add components to the frame
        add(textField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (int i = 0; i < 10; i++) {
            if (source == numberButtons[i]) {
                textField.setText(textField.getText() + i);
                return;
            }
        }

        if (source == addButton) {
            handleOperator('+');
        } else if (source == subButton) {
            handleOperator('-');
        } else if (source == mulButton) {
            handleOperator('*');
        } else if (source == divButton) {
            handleOperator('/');
        } else if (source == eqButton) {
            calculateResult();
        } else if (source == clrButton) {
            clearCalculator();
        }
    }

    private void handleOperator(char op) {
        num1 = Double.parseDouble(textField.getText());
        operator = op;
        textField.setText("");
    }

    private void calculateResult() {
        num2 = Double.parseDouble(textField.getText());

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Division by zero is not allowed.", "Error", JOptionPane.ERROR_MESSAGE);
                    clearCalculator();
                    return;
                }
                break;
        }

        textField.setText(String.valueOf(result));
    }

    private void clearCalculator() {
        textField.setText("");
        num1 = 0;
        num2 = 0;
        result = 0;
        operator = '\0';
    }
}

