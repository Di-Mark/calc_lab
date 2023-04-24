import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends JFrame implements ActionListener {
    static JFrame frame;                    //создаем экземпляр класса JFrame
    static JTextField result;               //создаем экземпляр класса строчки результата
    static String s = "";                   //строчка для передачи в результат

    public static void main(String[] args)  {
        Main listen = new Main();
        frame = new JFrame("Calculator");           //название окна
        result = new JTextField(16);             //формеруем 16 коллон
        result.setEditable(false);                      //запрещаем писать с клавиатуры в строку
        result.setText("");                             //ставим текст строчки
        ArrayList<JButton> btns = new ArrayList<>();    //делаем список кнопок цифр
        for (int i = 0; i < 10; i++) {
            JButton jTempButton = new JButton(Integer.toString(i));     //интегригуем кнопки цифр в панель
            jTempButton.addActionListener(listen);
            btns.add(jTempButton);
        }
        List<String> operation = Arrays.asList("+", "-", "*", "/", "=", ".", "c","acos");          //список операций
        JPanel buttons = new JPanel();
        btns.forEach(buttons::add);
        operation.forEach(it -> {
            JButton jTempButton = new JButton(it);
            jTempButton.addActionListener(listen);
            buttons.add(jTempButton);
        });

        GridLayout myGridLayOut = new GridLayout(4, 4);                //делим панель на 4 колонны и 4 ряда
        buttons.setLayout(myGridLayOut);
        JPanel mainPanel = new JPanel();
        mainPanel.add(result);                                                  //добавляем строчку на панель
        mainPanel.add(buttons);                                                 //добавляем кнопки на панель

        frame.add(mainPanel);                                                   //добавляем панель со строчкой и кнопками в окно
        frame.setSize(300, 200);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){                                 //обрабатываем события с кнопок
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
        double a = Double.parseDouble(s);               //переводим строчку в число
        if(a >= -1 && a<= 1){
            a = Math.acos(a);                           //юзаем функцию acos на число
            s = Double.toString(a);                     //переводим число в строчку
        }else {
            s = "Error";                                //если число не подходит под функцию выводим error
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
        boolean check = false;
        while (!s.substring(0,1).equals("+")&&!s.substring(0,1).equals("-")&&!s.substring(0,1).equals("*")&&!s.substring(0,1).equals("/")){         //пока символы не является операцией
            if(s.substring(0,1).equals(".")&& check == false){                             //обработка искльчения в числе 2 точки
                check = true;
            } else if (s.substring(0,1).equals(".")&& check == true) {
                return "Error";
            }
            if(s.length() == 1){                                                          //обработка исключения строки без операции
                return "Error";
            }
            if(s.charAt(0) >= '0' && s.charAt(0) <= '9' || s.substring(0,1).equals(".")) {
                first_str = first_str + s.charAt(0);
                s = s.substring(1);
            }
        }
        check = false;
        operation = s.substring(0,1);
        s = s.substring(1);
        while (s.equals("")){
            if(s.substring(0,1).equals(".")&& check == false){                             //обработка искльчения в числе 2 точки второго числа
                check = true;
            } else if (s.substring(0,1).equals(".")&& check == true) {
                return "Error";
            }
        }
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