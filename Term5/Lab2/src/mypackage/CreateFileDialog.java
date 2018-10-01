package mypackage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class CreateFileDialog extends JDialog {
    private DefaultTableModel tableModel;
    private JTable table;

    private String[] columnNames = {"Date", "Type", "Price(rub)"};

    private JButton okButton;

    private JLabel dateLabel;
    private JLabel typeLabel;
    private JLabel priceLabel;

    private JTextField dateField;
    private JTextField typeField;
    private JTextField priceField;

    private JPanel addPanel;
    private JPanel filePanel;

    private JButton addButton;
    private JButton deleteButton;

    private ArrayList<Order> orders = new ArrayList<>();

    private File file;

    private boolean wasChanged = false;

    private final static String DATE_REGEX =
            "(((([0]?[1-9]|[12][0-9]|[3][01])[.]([0]?[13578]|[1][02]))|" +
                    "(([0]?[1-9]|[12][0-9]|30)[.]([0]?[469]|11))|" +
                    "(([0]?[1-9]|[12][0-9])[.]([0]?[2])))" +
                    "[.]([1-9][0-9]{0,3}|[0-9]{2}))";

    CreateFileDialog(MyFrame frame, String name, File file) {
        super(frame, name);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  //Автоматически скрывает и удаляет диалоговое окно

        this.file = file;

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        filePanel = new JPanel();
        addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(4, 2));

        dateLabel = new JLabel("Date");
        typeLabel = new JLabel("Type");
        priceLabel = new JLabel("Price(rub)");

        dateField = new JTextField("", 10);
        typeField = new JTextField("", 10);
        priceField = new JTextField("", 10);

        dateField.setColumns(10);
        typeField.setColumns(10);
        priceField.setColumns(10);

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");

        okButton = new JButton("Ok");

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

        if (file != null) {
            for(Order order:frame.getOrders())
                orders.add(order);
            for(Order order: orders)
                tableModel.addRow(new Object[]{order.getDate(), order.getType(), order.getPrice()});
        } else {
            orders = new ArrayList<>();

        }

        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setRowSelectionAllowed(true);

        addButton.addActionListener(e -> {
            try {
                if (!Pattern.matches(DATE_REGEX, dateField.getText()))
                    throw new IOException("Incorrect format of date. It should be \"dd.MM.yyyy\" ");
                if (typeField.getText().equals("")) throw new IOException("Incorrect format of type ");
                Order order = new Order(dateField.getText(), typeField.getText(), Double.parseDouble(priceField.getText()));
                orders.add(order);

                tableModel.addRow(new Object[]{order.getDate(), order.getType(), order.getPrice()});

                wasChanged = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Incorrect format of input data.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int ind;
            if((ind = table.getSelectedRow()) != -1){
                tableModel.removeRow(ind);
                orders.remove(ind);
                wasChanged = true;
            }
        });

        okButton.addActionListener(e -> {
            frame.setOrders(orders);

            File myFile = getFile();
            if (myFile == null) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                chooser.setPreferredSize(new Dimension(700, 500));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("File (.bin)", "bin");
                chooser.setFileFilter(filter);

                if (chooser.showSaveDialog(okButton) == JFileChooser.APPROVE_OPTION) {
                    frame.save(chooser.getSelectedFile());
                }
            }else{
                if(wasChanged)
                    frame.setWasChanged(true);
                frame.setCurrentOrders(orders);
                frame.showEditStudio();
                frame.analyzeStudio();
            }

            dispose();
        });

        addPanel.add(dateLabel);
        addPanel.add(dateField);
        addPanel.add(typeLabel);
        addPanel.add(typeField);
        addPanel.add(priceLabel);
        addPanel.add(priceField);
        addPanel.add(addButton);
        addPanel.add(deleteButton);

        filePanel.add(new JScrollPane(table));
        container.add(filePanel, BorderLayout.WEST);
        container.add(addPanel, BorderLayout.EAST);
        container.add(okButton, BorderLayout.SOUTH);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public File getFile() {
        return file;
    }


}
