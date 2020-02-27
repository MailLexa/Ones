package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientWindow extends JFrame {
    // адрес сервера
    //ClientWindow Allclas;

    private static final String SERVER_HOST = "localhost";
    // порт
    private static final int SERVER_PORT = 3443;
    // клиентский сокет
    private Socket clientSocket;
    // входящее сообщение
    private Scanner inMessage;
    // исходящее сообщение
    private PrintWriter outMessage;
    // следующие поля отвечают за элементы формы
    public JTextField jtfMessage;
    private JTextField jtfName;
    private JTextArea jtaTextAreaMessage;

    // имя клиента
    private String clientName = "";
    private JTextField Label;
    private JTextField Labe2;
    private  WindowListener winListener;
    // получаем имя клиента
    public String getClientName() {
        return this.clientName;
    }

    // конструктор
    public ClientWindow( ) {
       //Allclas = this.Allclas;

        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // Задаём настройки элементов на форме
        setBounds(600, 300, 600, 500);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jtaTextAreaMessage = new JTextArea();
        jtaTextAreaMessage.setEditable(false);
        jtaTextAreaMessage.setLineWrap(true);
        JScrollPane jsp = new JScrollPane(jtaTextAreaMessage);
        add(jsp, BorderLayout.CENTER);
        // label, который будет отражать количество клиентов в чате
        JLabel jlNumberOfClients = new JLabel("Количество клиентов в чате: ");
         Label = new JTextField("Login: ");
         Labe2 = new JTextField("Password: ");



        add(jlNumberOfClients, BorderLayout.NORTH);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel bottomPane2 = new JPanel(new BorderLayout());
        add(bottomPanel, BorderLayout.NORTH);
        add(bottomPane2, BorderLayout.SOUTH);
        JButton jbSendMessage = new JButton("Отправить");
        bottomPanel.add(jbSendMessage, BorderLayout.EAST);
        jtfMessage = new JTextField("Введите ваше сообщение: ");
        bottomPanel.add(jtfMessage, BorderLayout.SOUTH);
        jtfName = new JTextField("Введите ваше имя: ");
        bottomPane2.add(Label,BorderLayout.CENTER);
        bottomPane2.add(Labe2,BorderLayout.NORTH );
        bottomPanel.add(jtfName, BorderLayout.CENTER);
        Fil ReadFile = new Fil();
        ReadFile.TestFile();//загружаем файл
        ReadFile.ReadFile(jtaTextAreaMessage);

        winListener = new TestWindowListener(this);
        addWindowListener(winListener);

        // обработчик события нажатия кнопки отправки сообщения

        jbSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // если имя клиента, и сообщение непустые, то отправляем сообщение
                if (!jtfMessage.getText().trim().isEmpty() && !jtfName.getText().trim().isEmpty()) {
                    clientName = jtfName.getText();
                    sendMsg();
                    // фокус на текстовое поле с сообщением
                    jtfMessage.grabFocus();
                }
            }
        });
        // при фокусе поле сообщения очищается
        jtfMessage.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfMessage.setText("");
            }
        });
        Label.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                Label.setText("");
            }
        });

        Labe2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                Labe2.setText("");
            }
        });

        // при фокусе поле имя очищается
        jtfName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                jtfName.setText("");
            }
        });


        // в отдельном потоке начинаем работу с сервером
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // бесконечный цикл
                    while (true) {
                        // если есть входящее сообщение
                        if (inMessage.hasNext()) {
                            // считываем его
                            String inMes = inMessage.nextLine();
                            String clientsInChat = "Клиентов в чате = ";
                            if (inMes.indexOf(clientsInChat) == 0) {
                                jlNumberOfClients.setText(inMes);
                            } else {
                                // выводим сообщение
                                jtaTextAreaMessage.append(inMes);

                                // добавляем строку перехода
                                jtaTextAreaMessage.append("\n");
                               // Fil WriteFile = new Fil();
                                //WriteFile.one();
                                //WriteFile.setDovanload(jtaTextAreaMessage);

                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
        // добавляем обработчик события закрытия окна клиентского приложения
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    // здесь проверяем, что имя клиента непустое и не равно значению по умолчанию
                    if (!clientName.isEmpty() && clientName != "Введите ваше имя: ") {
                        outMessage.println(clientName + " вышел из чата!");
                    } else {
                        outMessage.println("Участник вышел из чата, так и не представившись!");
                    }
                    // отправляем служебное сообщение, которое является признаком того, что клиент вышел из чата
                    outMessage.println("##session##end##");
                    outMessage.flush();
                    outMessage.close();
                    inMessage.close();
                    clientSocket.close();
                } catch (IOException exc) {

                }
            }
        });
        // отображаем форму
        setVisible(true);
    }

    // отправка сообщения
    public void sendMsg() {
        // формируем сообщение для отправки на сервер
        String messageStr = Labe2.getText()+Label.getText()+"/"+ jtfName.getText() + ": " + jtfMessage.getText();
        // отправляем сообщение
        //System.out.println(Labe2.getText());
        outMessage.println(messageStr);
        outMessage.flush();
        jtfMessage.setText("");
    }

    public JTextArea getmessage(){
        return jtaTextAreaMessage;


    }



}

 class TestWindowListener implements WindowListener {
     //JTextArea name;
     ClientWindow client;


     public TestWindowListener(ClientWindow client) {
        this.client = client;

     }



    public void windowActivated(WindowEvent e) {

    }

    public void windowClosed(WindowEvent e) {


    }

    public void windowClosing(WindowEvent e) {
        System.out.println("windowClosing()");
        Fil Fil1 = new Fil();
        Fil1.LoadFile(client.getmessage());

    }

    public void windowDeactivated(WindowEvent e) {  }

    public void windowDeiconified(WindowEvent e) {  }

    public void windowIconified(WindowEvent e) {  }

    public void windowOpened(WindowEvent e) {  }


 }


