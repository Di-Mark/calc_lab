import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JFrame implements ActionListener {
    static JFrame frame;
    static JTextField result;
    static String s = "";

    public static void main(String[] args)  {
        Main listen = new Main();
        frame = new JFrame("Calculator");
        result = new JTextField(16);
        result.setEditable(false);
        result.setText("");
        ArrayList<JButton> btns = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JButton jTempButton = new JButton(Integer.toString(i));
            jTempButton.addActionListener(listen);
            btns.add(jTempButton);
        }
        List<String> operation = Arrays.asList("+", "-", "*", "/", "=", ".", "c","acos");
        JPanel buttons = new JPanel();
        btns.forEach(buttons::add);
        operation.forEach(it -> {
            JButton jTempButton = new JButton(it);
            jTempButton.addActionListener(listen);
            buttons.add(jTempButton);
        });

        GridLayout myGridLayOut = new GridLayout(4, 4);
        buttons.setLayout(myGridLayOut);
        JPanel mainPanel = new JPanel();
        mainPanel.add(result);
        mainPanel.add(buttons);

        frame.add(mainPanel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println(e.getActionCommand());

        if(e.getActionCommand().equals("acos")){
            s = aCosProcessing(s);
        }else {
            if (e.getActionCommand().equals("c")) {
                s = "";
            } else {
                if (e.getActionCommand().equals("=")) {
                    s = processing(s);
                } else {
                    s = s + e.getActionCommand();
                }
            }
        }
                 
        result.setText(s);
    }


    public static String aCosProcessing(String s){
        double a = Double.parseDouble(s);
        if(a >= -1 && a<= 1){
            a = Math.acos(a);
            s = Double.toString(a);
        }else {
            s = "Error";
        }
        return s;
    }
    public static String processing(String s){
        String first_str = "";
        String second_str = "";
        float first_f = 0;
        float second_f = 0;
        String operation = "";
        float result = 0;
        while (!s.substring(0,1).equals("+")&&!s.substring(0,1).equals("-")&&!s.substring(0,1).equals("*")&&!s.substring(0,1).equals("/")){
            if(s.charAt(0) >= '0' && s.charAt(0) <= '9' || s.substring(0,1).equals(".")) {
                first_str = first_str + s.charAt(0);
                s = s.substring(1);
            }
        }
        operation = s.substring(0,1);
        s = s.substring(1);
        second_str = s;
        first_f = Float.parseFloat(first_str);
        second_f = Float.parseFloat(second_str);
        switch (operation){
            case "+":
                result = first_f + second_f;
                break;
            case "-":
                result = first_f - second_f;
                break;
            case"*":
                result = first_f * second_f;
                break;
            case"/":
                result = first_f / second_f;
                break;
        }
        if(Float.toString(result).equals("Infinity")){
            return "Error";
        }else {
        return Float.toString(result);}
    }
}