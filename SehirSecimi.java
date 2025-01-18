import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SehirSecimi extends JFrame implements ActionListener {
    private JButton[] fromButtons;
    private JButton[] toButtons;
    private JLabel denemeLabel;
    private JLabel bfsLabel;

    private Integer fromValue = null;
    private Integer toValue = null;

    private final List<String> sehirler = new ArrayList<>(List.of(
            "Istanbul", "Ankara", "Izmir", "Bursa", "Adana", "Gaziantep", "Konya", "Diyarbakir",
            "Antalya", "Mersin", "Kayseri", "Urfa", "Malatya", "Samsun", "Denizli", "Batman", "Trabzon"
    ));

    public SehirSecimi() {
        setTitle("Şehir Seçimi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(1600, 500));
        pack();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel fromLabel = new JLabel("From:");
        add(fromLabel, gbc);

        fromButtons = new JButton[17];
        for (int i = 0; i < 17; i++) {
            fromButtons[i] = new JButton(sehirler.get(i));
            fromButtons[i].setActionCommand(Integer.toString(i));
            fromButtons[i].addActionListener(this);

            gbc.gridx = i + 1;
            add(fromButtons[i], gbc);
        }
        

        gbc.gridx = 0;
        gbc.gridy = 1;

        JLabel toLabel = new JLabel("To:");
        add(toLabel, gbc);

        toButtons = new JButton[17];
        for (int i = 0; i < 17; i++) {
            toButtons[i] = new JButton(sehirler.get(i));
            toButtons[i].setActionCommand(Integer.toString(i));
            toButtons[i].addActionListener(this);

            gbc.gridx = i + 1;
            add(toButtons[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 18;
        denemeLabel = new JLabel();
        bfsLabel = new JLabel();
        add(denemeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(bfsLabel, gbc);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        


    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        JButton button = (JButton) e.getSource();

        if (isButtonInArray(button, fromButtons)) {
            fromValue = Integer.parseInt(command);
        } else if (isButtonInArray(button, toButtons)) {
            toValue = Integer.parseInt(command);
        }

        updateDenemeLabelText();
        System.out.println(fromValue);
        System.out.println(toValue);
    }

    private void updateDenemeLabelText() {
        String text = "";
        String text2 = "";
        if (fromValue != null && toValue != null) {
            if(GraphReader.pathBFS(sehirler.get(fromValue),sehirler.get(toValue)).isEmpty()){
                System.out.println("no path");
            }else{
                text += "From : " + sehirler.get(fromValue) + ", To : " + sehirler.get(toValue) + " --> (BFS) Shortest distance : "+Integer.toString(GraphReader.getDistanceBFS(sehirler.get(fromValue),sehirler.get(toValue)))+" km  Shortest path (BFS) : " + GraphReader.pathBFS(sehirler.get(fromValue),sehirler.get(toValue));

            }
            
        } else {
            text += "Henüz her iki şehir de seçilmedi.";
        }
        denemeLabel.setText(text);
        if (fromValue != null && toValue != null) {
            if(GraphReader.pathBFS(sehirler.get(fromValue),sehirler.get(toValue)).isEmpty()){
                System.out.println("no path");
            }else{
                text2 += "From : " + sehirler.get(fromValue) + ", To : " + sehirler.get(toValue) + " --> (DFS) Shortest distance : "+Integer.toString(GraphReader.getDistanceDFS(sehirler.get(fromValue),sehirler.get(toValue)))+" km  Shortest path (DFS) : " + GraphReader.pathDFS(sehirler.get(fromValue),sehirler.get(toValue));

            }
            
        } else {
            text2 += "Henüz her iki şehir de seçilmedi.";
        }
        bfsLabel.setText(text2);

    }

    private boolean isButtonInArray(JButton button, JButton[] array) {
        for (JButton btn : array) {
            if (button.equals(btn)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SehirSecimi());
    }
}
