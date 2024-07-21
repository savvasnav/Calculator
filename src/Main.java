import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        new Calculator();

    }

}
class Calculator extends JFrame implements ActionListener {
    JButton[][] button = new JButton[4][4];
    private JTextField display;
    private String currentOperator;
    private double firstNumber ;
    private boolean operatorPressed;
    public Calculator(){
        JFrame jframe = new JFrame();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,500);
        setVisible(true);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 40));
        display.setHorizontalAlignment(SwingConstants.RIGHT); // Correct alignment
        display.setEditable(false);
        add(display, BorderLayout.NORTH);


        //add(display, BorderLayout.NORTH);
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(4,4));
        add(jpanel, BorderLayout.CENTER);
        initializeButtons(jpanel);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")){
            if(operatorPressed){
                display.setText(command);
                operatorPressed = false;
            }
            else {
                display.setText(display.getText()+command);
            }
        }
        else if(command.equals("C")){
            display.setText("");
            currentOperator = null;
            firstNumber = 0;
            operatorPressed = false;
        }
        else if(command.equals("=")){
            double secondNumber = Double.parseDouble(display.getText());
            switch (currentOperator){
                case "+":
                    display.setText(String.valueOf(firstNumber+secondNumber));
                    break;
                case "-":
                    display.setText(String.valueOf(firstNumber-secondNumber));
                    break;
                case "*":
                    display.setText(String.valueOf(firstNumber*secondNumber));
                    break;
                case "/":
                    display.setText(String.valueOf(firstNumber/secondNumber));
                    break;
            }
            currentOperator = null;
            operatorPressed = false;
        }
        else {
            if(!display.getText().isEmpty()){
                firstNumber = Double.parseDouble(display.getText());
                currentOperator = command ;
                operatorPressed = true;
            }
        }
    }
    private void initializeButtons(JPanel jpanel){
        for (int row = 0;row<4;row++){
            for(int col = 0;col <4;col++){
                button [row][col] = new JButton("");
                button [row][col].setFont(new Font("Arial",Font.PLAIN,40));
                button [row][col].setBackground(Color.lightGray);
                button[row][col].setFocusPainted(false);
                button[row][col].addActionListener(this);

               jpanel.add(button[row][col]);
            }
        }
        int i=9;
        for (int row = 0;row<3;row++){
            for(int col = 0;col <3;col++) {
                button [row][col].setText(""+i);
                i--;
            }

        }
                button[0][3].setText("+");
                button[1][3].setText("-");
                button[2][3].setText("/");
                button[3][3].setText("=");
                button[3][0].setText("0");
                button[3][1].setText(".");
                button[3][2].setText("C");
    }
}