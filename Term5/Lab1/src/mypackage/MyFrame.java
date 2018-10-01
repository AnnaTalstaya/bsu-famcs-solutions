package mypackage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.event.KeyEvent.*;

public class MyFrame extends JFrame {

    private JMenuBar menuBar;

    private JFileChooser fileChooser;
    private File selectedFile;
    private File translationFile;
    private File rulesFile;

    private final int ALPHABET_SIZE = 33;
    private String pathToRules = "rules-of-translation/rule1.txt";

    private HashMap<String, String> symbols;
    private HashMap<String, String> copySymbols;
    private Pattern pattern = Pattern.compile("^[А-Яа-яЁё]$", Pattern.UNICODE_CHARACTER_CLASS);
    private Matcher matcher;

    private JTextArea areaCyrillic;
    private JTextArea areaLatin;

    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenuItem saveTranslationItem;
    private JMenuItem saveAsTranslationItem;

    private boolean wasChanged = false;

    public MyFrame() {
        super("Translator");
        setDefaultCloseOperation(MyFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                if (wasChanged) {
                    Object[] options = {"Yes", "No", "Cancel"};
                    int n = JOptionPane.showOptionDialog(e.getWindow(), "The source file was changed. Do you want to save the changes?",
                            "Question", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        saveChanges(null, selectedFile);
                        e.getWindow().setVisible(false);
                        System.exit(0);
                    }
                    else if (n == 1) {
                        e.getWindow().setVisible(false);
                        System.exit(0);
                    }
                }else{
                    e.getWindow().setVisible(false);
                    System.exit(0);
                }
            }
        });

        createBar();
        createAreas();
        rulesFile = new File(pathToRules);
        createRules(rulesFile);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.add(new JScrollPane(areaCyrillic));
        panel.add(new JScrollPane(areaLatin));

        setJMenuBar(menuBar);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void createBar() {
        Font font = new Font("Verdana", Font.PLAIN, 15);
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(font);
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open...");
        openItem.setFont(font);
        fileMenu.add(openItem);

        saveItem = new JMenuItem("Save");
        saveItem.setFont(font);
        fileMenu.add(saveItem);

        saveAsItem = new JMenuItem("Save as...");
        saveAsItem.setFont(font);
        fileMenu.add(saveAsItem);

        saveTranslationItem = new JMenuItem("Save translation");
        saveTranslationItem.setFont(font);
        fileMenu.add(saveTranslationItem);

        saveAsTranslationItem = new JMenuItem("Save translation as...");
        saveAsTranslationItem.setFont(font);
        fileMenu.add(saveAsTranslationItem);

        JMenu rulesMenu = new JMenu("Rules of translation");
        rulesMenu.setFont(font);
        menuBar.add(rulesMenu);

        JMenuItem seeRules = new JMenuItem("See current rules");
        seeRules.setFont(font);
        rulesMenu.add(seeRules);

        JMenuItem changeRules = new JMenuItem("Change the rules");
        changeRules.setFont(font);
        rulesMenu.add(changeRules);

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = 0;
                if (wasChanged) {
                    Object[] options = {"Yes", "No", "Cancel"};
                    n = JOptionPane.showOptionDialog(MyFrame.this, "The source file was changed. Do you want to save the changes?",
                            "Question", JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (n == 0) {
                        saveChanges(e, selectedFile);
                        wasChanged = false;
                    }
                }
                if (n != 2) {
                    areaCyrillic.setText(null);
                    areaLatin.setText(null);
                    wasChanged = false;

                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    fileChooser.setPreferredSize(new Dimension(700, 500));


                    FileNameExtensionFilter filter = new FileNameExtensionFilter("File (.txt)", "txt");
                    fileChooser.setFileFilter(filter);
                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        readAndTranslateFile(selectedFile);

                    }
                }

            }
        });

        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(e, selectedFile);
            }
        });
        saveAsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(e, selectedFile);
            }
        });

        saveTranslationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(e, translationFile);
            }
        });
        saveAsTranslationItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges(e, translationFile);
            }
        });

        seeRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Process process = Runtime.getRuntime().exec("cmd /c notepad.exe " + rulesFile.getAbsolutePath());
                    process.waitFor();

                    createRules(rulesFile);
                    update();
                } catch (UnsupportedEncodingException e1) {
                    JOptionPane.showMessageDialog(MyFrame.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (FileNotFoundException e1) {
                    JOptionPane.showMessageDialog(MyFrame.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(MyFrame.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException e1) {
                    JOptionPane.showMessageDialog(MyFrame.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        changeRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                fileChooser.setPreferredSize(new Dimension(700, 500));

                FileNameExtensionFilter filter = new FileNameExtensionFilter("File (.txt)", "txt");
                fileChooser.setFileFilter(filter);
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    rulesFile = fileChooser.getSelectedFile();
                    createRules(rulesFile);
                    update();
                }

            }
        });
    }

    public void createAreas() {
        areaCyrillic = new JTextArea(15, 20);
        areaLatin = new JTextArea(15, 20);

        Font font = new Font("Verdana", Font.PLAIN, 30);
        areaCyrillic.setFont(font);
        areaLatin.setFont(font);

        areaCyrillic.setLineWrap(true);
        areaLatin.setLineWrap(true);

        areaCyrillic.setWrapStyleWord(true);
        areaLatin.setWrapStyleWord(true);

        areaLatin.setEditable(false);

        areaCyrillic.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                wasChanged = true;
                update();
            }
        });

    }

    public void readAndTranslateFile(File selectedFile) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(selectedFile.getAbsolutePath()), "Cp1251"));

            String nextString;
            String key;
            while ((nextString = br.readLine()) != null) {
                for (int i = 0; i < nextString.length(); i++) {
                    key = Character.toString(nextString.charAt(i));
                    if (symbols.containsKey(key)) {
                        areaLatin.append(symbols.get(key));
                    } else {
                        areaLatin.append(key);
                    }
                    areaCyrillic.append(key);
                }
            }
            areaCyrillic.setCaretPosition(0);
            areaLatin.setCaretPosition(areaCyrillic.getCaretPosition());
        } catch (UnsupportedEncodingException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void createRules(File selectedFile) {
        try {
            copySymbols = symbols;
            symbols = new HashMap<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(selectedFile.getAbsolutePath()), "Cp1251"));

            String nextString;
            while ((nextString = br.readLine()) != null) {
                String[] strings = nextString.split("-|( - )|( -)|(- )");

                if (strings.length == 2) {
                    String key = strings[0];
                    String val = strings[1];
                    matcher = pattern.matcher(key);
                    if (matcher.matches() && !symbols.containsKey(key)) {
                        symbols.put(key, val);
                    } else throw new IOException("Incorrect format of file with rules");
                } else throw new IOException("Incorrect format of file with rules");

            }
            if (symbols.size() != ALPHABET_SIZE * 2)
                throw new IOException("Incorrect format of file with rules");

        } catch (UnsupportedEncodingException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            symbols = copySymbols;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            symbols = copySymbols;
        }
    }

    public void saveChanges(ActionEvent event, File file){

        if (event!=null && (file == null || (JMenuItem)event.getSource() == saveAsItem || (JMenuItem)event.getSource() == saveAsTranslationItem)){
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setPreferredSize(new Dimension(700, 500));


            FileNameExtensionFilter filter = new FileNameExtensionFilter("File (.txt)", "txt");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            }

            if(file!=null){
                if((JMenuItem)event.getSource() == saveItem)
                    selectedFile = file;
                else if ((JMenuItem)event.getSource() == saveTranslationItem)
                    translationFile = file;
            }
        }
        else if(file!=null){
            try {
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath()), "cp1251"));
                if(event!=null && ((JMenuItem)event.getSource() == saveTranslationItem || (JMenuItem)event.getSource() == saveAsTranslationItem))
                    out.append(areaLatin.getText());
                else out.append(areaCyrillic.getText());
                out.close();
                wasChanged = false;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void update(){
        areaLatin.setText(null);
        String text = areaCyrillic.getText();
        String key;
        for (int i = 0; i < text.length(); i++) {
            key = Character.toString(text.charAt(i));
            if (symbols.containsKey(key)) {
                areaLatin.append(symbols.get(key));
            } else {
                areaLatin.append(key);
            }
        }
        areaLatin.setCaretPosition(areaCyrillic.getCaretPosition());
    }

}
