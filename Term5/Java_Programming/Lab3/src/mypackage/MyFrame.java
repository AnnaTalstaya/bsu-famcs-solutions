package mypackage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyFrame extends JFrame {

    private JPanel panel;
    private JPanel panelFunc;
    private JPanel panelInf;

    private JLabel languageTextLabel = new JLabel();
    private JLabel countryTextLabel = new JLabel();
    private JLabel currencyTextLabel = new JLabel();
    private JLabel dateTextLabel = new JLabel();
    private JLabel timeTextLabel = new JLabel();

    private JLabel languageLabel = new JLabel();
    private JLabel countryLabel = new JLabel();
    private JLabel currencyLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    private JLabel timeLabel = new JLabel();

    private JMenuBar menuBar;
    private JMenu language;

    private JTextField textField;
    private JButton go;

    private Locale locale;

    private JRadioButtonMenuItem russianItem = new JRadioButtonMenuItem();
    private JRadioButtonMenuItem englishUSAItem = new JRadioButtonMenuItem();
    private JRadioButtonMenuItem deCHItem = new JRadioButtonMenuItem();
    private JRadioButtonMenuItem frCHItem = new JRadioButtonMenuItem();

    private Currency currency;
    private Date date;
    private DateFormat dateFormat;

    private ClassLoader loader;
    private URL[] urls;

    public MyFrame(){
        super("Interpreter");
        setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(620, 300));

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        panelFunc = new JPanel();
        textField = new JTextField();
        textField.setColumns(10);

        go = new JButton("Fist group");
        go.addActionListener(e -> {
            try {
                Class myClass = Class.forName(textField.getText());
                Method method = myClass.getDeclaredMethod("firstGroup");
                method.invoke(null);

            } catch (ClassNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "Class not found.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NoSuchMethodException e1) {
                JOptionPane.showMessageDialog(null, "No such method in this class", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalAccessException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (InvocationTargetException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NoClassDefFoundError e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        panelInf = new JPanel();
        panelInf.setLayout(new GridLayout(5, 2));

        menuBar = new JMenuBar();

        language = new JMenu("Language");
        language.setFont(new Font("Verdana", Font.PLAIN, 15));
        menuBar.add(language);

        Font font = new Font("Verdana", Font.PLAIN, 13);
        ButtonGroup group = new ButtonGroup();

        File file = new File("A:\\bsu-famcs-solutions\\Term5\\Java_Programming\\Lab3\\Properties");
        try {
            urls = new URL[]{file.toURI().toURL()};
            loader = new URLClassLoader(urls);
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        englishUSAItem.setSelected(true);
        englishUSAItem.setFont(font);
        group.add(englishUSAItem);
        language.add(englishUSAItem);
        locale = new Locale("en", "US");
        languageChanged(ResourceBundle.getBundle("text", locale, loader));

        russianItem.setFont(font);
        group.add(russianItem);
        language.add(russianItem);

        deCHItem.setFont(font);
        group.add(deCHItem);
        language.add(deCHItem);

        frCHItem.setFont(font);
        group.add(frCHItem);
        language.add(frCHItem);

        russianItem.addActionListener(e -> {
            locale = new Locale("ru", "RU");
            languageChanged(ResourceBundle.getBundle("text", locale, loader));
        });

        englishUSAItem.addActionListener(e -> {
            locale = new Locale("en", "US");
            languageChanged(ResourceBundle.getBundle("text", locale, loader));
        });

        deCHItem.addActionListener(e -> {
            locale = new Locale("de", "CH");
            languageChanged(ResourceBundle.getBundle("text", locale, loader));
        });

        frCHItem.addActionListener(e -> {
            locale = new Locale("fr", "CH");
            languageChanged(ResourceBundle.getBundle("text", locale, loader));
        });

        panelInf.add(languageTextLabel);
        panelInf.add(languageLabel);
        panelInf.add(countryTextLabel);
        panelInf.add(countryLabel);
        panelInf.add(currencyTextLabel);
        panelInf.add(currencyLabel);
        panelInf.add(dateTextLabel);
        panelInf.add(dateLabel);
        panelInf.add(timeTextLabel);
        panelInf.add(timeLabel);

        setJMenuBar(menuBar);
        panelFunc.add(textField);
        panelFunc.add(go);
        panel.add(panelFunc);
        panel.add(panelInf);
        add(panel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void languageChanged(ResourceBundle resourceBundle) {
        try {
            MyFrame.this.setTitle(new String(resourceBundle.getString("frameName").getBytes("ISO-8859-1"), "UTF-8"));
            language.setText(new String(resourceBundle.getString("language").getBytes("ISO-8859-1"), "UTF-8"));

            englishUSAItem.setText(new String(resourceBundle.getString("buttonUSA").getBytes("ISO-8859-1"), "UTF-8"));
            russianItem.setText(new String(resourceBundle.getString("buttonRussia").getBytes("ISO-8859-1"), "UTF-8"));
            deCHItem.setText(new String(resourceBundle.getString("button_de_CH").getBytes("ISO-8859-1"), "UTF-8"));
            frCHItem.setText(new String(resourceBundle.getString("button_fr_CH").getBytes("ISO-8859-1"), "UTF-8"));

            languageTextLabel.setText(new String(resourceBundle.getString("language").getBytes("ISO-8859-1"), "UTF-8") + " : ");
            languageLabel.setText(new String(resourceBundle.getString("languageValue").getBytes("ISO-8859-1"), "UTF-8"));
            countryTextLabel.setText(new String(resourceBundle.getString("country").getBytes("ISO-8859-1"), "UTF-8") + " : ");
            countryLabel.setText(new String(resourceBundle.getString("countryValue").getBytes("ISO-8859-1"), "UTF-8"));

            currencyTextLabel.setText(new String(resourceBundle.getString("currency").getBytes("ISO-8859-1"), "UTF-8") + " : ");
            currency = Currency.getInstance(locale);
            currencyLabel.setText(currency + " (" + currency.getSymbol(locale) + ")");

            dateTextLabel.setText(new String(resourceBundle.getString("date").getBytes("ISO-8859-1"), "UTF-8") + " : ");
            dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
            Date date = new Date();
            dateLabel.setText(dateFormat.format(new Date()));

            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            timeTextLabel.setText(new String(resourceBundle.getString("time").getBytes("ISO-8859-1"), "UTF-8") + " : ");
            timeLabel.setText(dateFormat.format(date));


        } catch (UnsupportedEncodingException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
