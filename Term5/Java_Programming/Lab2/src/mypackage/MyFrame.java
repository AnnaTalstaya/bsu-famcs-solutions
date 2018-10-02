package mypackage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenu studioMenu;
    private JMenuItem editItem;
    private JMenuItem createItem;
    private JMenuItem analyzeItem;

    private DefaultTableModel tableModel;
    private JTable table;
    private String[] columnNames = {"Date", "Type", "Price(rub)"};

    private JTextArea analyzeArea;

    private HashMap<String, ArrayList<Order>> inf;
    private ArrayList<Order> orders;
    private ArrayList<Order> currentOrders;

    private final static String DATE_REGEX = "(([1-9][0-9]{0,3}|[0-9]{2})[.]" +
            "((([0]?[13578]|[1][02])[.]([0]?[1-9]|[12][0-9]|[3][01]))|(([0]?[469]|11)[.]([0]?[1-9]|[12][0-9]|30))|(([0]?[2])[.]([0]?[1-9]|[12][0-9]))))";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");

    private JFileChooser fileChooser;
    private File selectedFile = null;
    private ArrayList<Order> editOrders;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private boolean wasChanged = false;

    private int numberInQuarter = 0;
    private int numberInMonth = 0;
    private double sumQuarter = 0;
    private double sumMonth = 0;

    MyFrame() {
        super("Studio");
        setLayout(new BorderLayout());
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
                        saveChanges(null);
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



        JPanel panel = new JPanel();

        createBar();
        createAreas();

        panel.add(new JScrollPane(table));
        panel.add(new JScrollPane(analyzeArea));

        add(panel);
        setJMenuBar(menuBar);
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

        studioMenu = new JMenu("Studio");
        studioMenu.setFont(font);
        menuBar.add(studioMenu);

        createItem = new JMenuItem("Create new");
        createItem.setFont(font);
        studioMenu.add(createItem);

        editItem = new JMenuItem("Edit");
        editItem.setFont(font);
        studioMenu.add(editItem);
        editItem.setEnabled(false);

        analyzeItem = new JMenuItem("Analyze");
        analyzeItem.setFont(font);
        studioMenu.add(analyzeItem);
        analyzeItem.setEnabled(false);

        createItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateFileDialog(MyFrame.this, "Create new studio", null);
            }
        });

        openItem.addActionListener(e -> {
            int n = 0;
            if(wasChanged){
                Object[] options = {"Yes", "No", "Cancel"};
                n = JOptionPane.showOptionDialog(MyFrame.this, "The source file was changed. Do you want to save the changes?",
                        "Question", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                if (n == 0) {
                    saveChanges(null);
                }
            }
            if(n!=2) {
                try {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    fileChooser.setPreferredSize(new Dimension(700, 500));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("File (.bin)", "bin");
                    fileChooser.setFileFilter(filter);

                    if (fileChooser.showOpenDialog(openItem) == JFileChooser.APPROVE_OPTION) {

                        selectedFile = fileChooser.getSelectedFile();
                        FileInputStream fileInputStream = new FileInputStream(selectedFile);
                        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                        try {
                            orders = new ArrayList<>();
                            while (true) {
                                orders.add((Order) objectInputStream.readObject());
                            }
                        } catch (EOFException exc) {
                            objectInputStream.close();
                            fileInputStream.close();
                        }

                        editOrders = new ArrayList<>();
                        for(Order order : orders)
                            editOrders.add(order);

                        showEditStudio();
                        analyzeStudio();
                        editItem.setEnabled(true);
                        analyzeItem.setEnabled(true);
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }

        });

        editItem.addActionListener(e -> {
            new CreateFileDialog(MyFrame.this, "Edit studio", selectedFile);
        });

        analyzeItem.addActionListener(e -> {
            analyzeStudio();
        });

        saveItem.addActionListener(e -> {
            saveChanges(e);
        });

        saveAsItem.addActionListener(e -> {
            saveChanges(e);
        });
    }

    public void saveChanges(ActionEvent event) {

        if (event != null && ((JMenuItem) event.getSource() == saveAsItem)) {

            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setPreferredSize(new Dimension(700, 500));

            FileNameExtensionFilter filter = new FileNameExtensionFilter("File (.bin)", "bin");
            fileChooser.setFileFilter(filter);

            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();

                save(selectedFile);
            }
        } else if (event== null || ((JMenuItem) event.getSource() == saveItem)) {
            save(selectedFile);
        }
    }

    public void save(File file){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Order i : editOrders) {
                objectOutputStream.writeObject(i);
                objectOutputStream.flush();
            }
            objectOutputStream.close();
            fileOutputStream.close();

            wasChanged = false;

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void createAreas() {
        analyzeArea = new JTextArea(15, 29);
        Font font = new Font("Verdana", Font.PLAIN, 23);
        analyzeArea.setFont(font);
        analyzeArea.setEditable(false);

        tableModel = new DefaultTableModel(0, columnNames.length) {
            public Class<?> getColumnClass(int col) {
                if(col == 2) return Double.class;
                else return String.class;
            }

            @Override
            public String getColumnName(int index) {
                return columnNames[index];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setRowSelectionAllowed(true);
        table.setFont(new Font("Verdana", Font.PLAIN, 15));
    }

    public void showEditStudio() {
        int rowCount = tableModel.getRowCount();
        for(int i = 0; i < rowCount; i++)
            tableModel.removeRow(0);

        for (Order order : editOrders)
            tableModel.addRow(new Object[]{order.getDate(), order.getType(), order.getPrice()});
    }

    public void analyzeStudio() {
        analyzeArea.setText(null);
        inf = new HashMap<>();
        for (Order order : editOrders) {
            if (inf.containsKey(order.getDate()) != true) {
                inf.put(order.getDate(), new ArrayList<Order>(Arrays.asList(order)));
            } else {
                inf.get(order.getDate()).add(order);
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormat.format(new Date());

        if (inf.containsKey(currentDate) == true) {
            currentOrders = inf.get(currentDate);
            analyzeArea.append("Orders for " + currentDate + "\n\n");
            analyzeArea.append("Total number of orders : " + currentOrders.size() + "\n");

            TreeMap<String, Integer> countTypes = new TreeMap<>();

            for (Order order : currentOrders) {
                if (countTypes.containsKey(order.getType()) == true) {
                    countTypes.replace(order.getType(), countTypes.get(order.getType()) + 1);
                } else {
                    countTypes.put(order.getType(), 1);
                }
            }

            for (Map.Entry<String, Integer> entry : countTypes.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                analyzeArea.append(key + " : " + value + "\n");
            }

            analyzeArea.append("\n");
        } else {
            analyzeArea.setText(currentDate + "  there are no orders." + "\n\n");
        }

        String[] arr = currentDate.split("[.]");
        int currentMonth = Integer.parseInt(arr[1]);
        int currentYear = Integer.parseInt(arr[2]);
        int currentQuarter = getQuarter(currentMonth);
        numberInQuarter = 0;
        numberInMonth = 0;
        sumQuarter = 0;
        sumMonth = 0;

        editOrders.stream().filter((order) -> order.getQuarter() == currentQuarter && order.getYear() == currentYear)
                .forEach((order) -> {sumQuarter += order.getPrice(); numberInQuarter++;});
        editOrders.stream().filter((order) -> order.getMonth() == currentMonth && order.getYear() == currentYear)
                .forEach((order) -> {sumMonth += order.getPrice(); numberInMonth++;});

        analyzeArea.append("The number of orders in the current quarter : " + numberInQuarter + "\n");
        analyzeArea.append("Money from the current quarter : " + sumQuarter + "\n\n");
        analyzeArea.append("The number of orders in the current month : " + numberInMonth + "\n");
        analyzeArea.append("Money from the current month : " + sumMonth + "\n");
    }

    public int getQuarter(int month) {
        if (month >= 1 && month <= 3)
            return 1;
        else if (month >= 4 && month <= 6)
            return 2;
        else if (month >= 7 && month <= 9)
            return 3;
        else return 4;
    }

    public void setCurrentOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return editOrders;
    }

    public void setOrders(ArrayList<Order> list) {
        editOrders = list;
    }

    public void setWasChanged(boolean wasChanged){
        this.wasChanged = wasChanged;
    }

}
