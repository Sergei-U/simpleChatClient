import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Sergei Usov
 * @version 1.0.0
 */
public class SimpleChatClientA {
    JTextField outGoing;
    Socket socket;
    PrintWriter writer;

    public void go() {
        JFrame jFrame = new JFrame("Simple Chat Client");
        JPanel mainPanel = new JPanel();
        outGoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(outGoing);
        mainPanel.add(sendButton);

        jFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        setUpNetWorking();
        jFrame.setSize(400,500);
        jFrame.setVisible(true);
        
    }

    private void setUpNetWorking() {
        try {
            socket = new Socket("127.0.0.1", 5000);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Networking established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class SendButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                writer.println(outGoing.getText());
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            outGoing.setText("");
            outGoing.requestFocus();
        }
    }

        public static void main(String[] args) {
            new SimpleChatClientA().go();
        }
}
