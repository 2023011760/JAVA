import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame implements ActionListener {
    private JTextField display;
    private String operator;
    private double num1, num2, result;

    public Main() {

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 48)); // 글자 크기 키우기
        display.setBackground(Color.GREEN); // 연두색 배경
        display.setForeground(Color.BLACK);
        display.setPreferredSize(new Dimension(400, 100)); // 텍스트 필드 크기 설정


        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        JButton addButton = createButton("+");
        JButton subButton = createButton("-");
        JButton mulButton = createButton("×");
        JButton divButton = createButton("÷");
        JButton equalsButton = createButton("=");
        JButton clearButton = createButton("AC");


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        panel.add(clearButton);
        panel.add(new JButton("CE"));
        panel.add(new JButton("%"));
        panel.add(divButton);

        for (int i = 7; i <= 9; i++) panel.add(numberButtons[i]);
        panel.add(mulButton);

        for (int i = 4; i <= 6; i++) panel.add(numberButtons[i]);
        panel.add(subButton);

        for (int i = 1; i <= 3; i++) panel.add(numberButtons[i]);
        panel.add(addButton);

        panel.add(numberButtons[0]);
        panel.add(new JButton("."));
        panel.add(equalsButton);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 500);
        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH);
        this.add(panel);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setBorder(BorderFactory.createRaisedBevelBorder());


        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            if (display.getText().equals("0")) {
                display.setText(command);
            } else {
                display.setText(display.getText() + command);
            }
        } else if (command.equals("AC")) {
            display.setText("0");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.equals("+") || command.equals("-") || command.equals("×") || command.equals("÷")) {
            num1 = Double.parseDouble(display.getText());
            operator = command;
            display.setText("0");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "×":
                    result = num1 * num2;
                    break;
                case "÷":
                    result = num1 / num2;
                    break;
            }
            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
