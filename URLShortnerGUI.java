import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class URLShortenerGUI extends JFrame {

    HashMap<String,String> urlDB = new HashMap<>();
    JTextField longUrl, shortUrl, lookupField;

    public URLShortenerGUI(){

        setTitle("🔗 Java URL Shortener");
        setSize(550,380);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Simple URL Shortener",JLabel.CENTER);
        title.setBounds(50,10,450,40);
        title.setFont(new Font("Segoe UI",Font.BOLD,22));
        title.setForeground(Color.BLUE);
        add(title);

        JLabel longLbl = new JLabel("Enter Long URL:");
        longLbl.setBounds(40,80,150,25);
        add(longLbl);

        longUrl = new JTextField();
        longUrl.setBounds(180,80,320,25);
        add(longUrl);

        JButton shortenBtn = new JButton("Shorten");
        shortenBtn.setBounds(210,120,130,30);
        shortenBtn.setBackground(Color.GREEN);
        shortenBtn.addActionListener(e -> generateShortURL());
        add(shortenBtn);

        JLabel shortLbl = new JLabel("Short URL:");
        shortLbl.setBounds(40,170,150,25);
        add(shortLbl);

        shortUrl = new JTextField();
        shortUrl.setEditable(false);
        shortUrl.setBounds(180,170,320,25);
        add(shortUrl);

        JLabel lookupLbl = new JLabel("Enter Short Code:");
        lookupLbl.setBounds(40,220,150,25);
        add(lookupLbl);

        lookupField = new JTextField();
        lookupField.setBounds(180,220,320,25);
        add(lookupField);

        JButton redirectBtn = new JButton("Redirect");
        redirectBtn.setBounds(210,260,130,30);
        redirectBtn.setBackground(Color.ORANGE);
        redirectBtn.addActionListener(e -> redirectURL());
        add(redirectBtn);
    }

    void generateShortURL(){

        String originalUrl = longUrl.getText();

        if(originalUrl.isBlank()){
            JOptionPane.showMessageDialog(this,"Enter URL first!");
            return;
        }

        String shortCode = generateCode();

        urlDB.put(shortCode, originalUrl);

        shortUrl.setText("https://short.ly/" + shortCode);

        JOptionPane.showMessageDialog(this,"Short URL Generated ✅");
    }

    void redirectURL(){

        String input = lookupField.getText();

        if(input.isEmpty()){
            JOptionPane.showMessageDialog(this,"Enter short code!");
            return;
        }

        input = input.replace("https://short.ly/","");

        String result = urlDB.get(input);

        if(result == null){
            JOptionPane.showMessageDialog(this,"URL Not Found ❌");
        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Redirecting to:\n" + result);
        }
    }

    String generateCode(){

        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<6;i++){
            sb.append(chars.charAt(r.nextInt(chars.length())));
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new URLShortenerGUI().setVisible(true);
        });
    }
}
