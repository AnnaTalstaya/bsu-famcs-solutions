package mypackage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("Lab2");
        setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);

        Font font = new Font("Verdana", Font.PLAIN, 15);
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);

        FirstPanel firstPanel = new FirstPanel();
        SecondPanel secondPanel = new SecondPanel();
        ThirdPanel thirdPanel = new ThirdPanel();

        tabbedPane.addTab("Task 1", firstPanel);
        tabbedPane.addTab("Task 2", secondPanel);
        tabbedPane.addTab("Task 3", thirdPanel);

        add(tabbedPane);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class FirstPanel extends JPanel {

    DefaultListModel<String> listModel;
    JList<String> myList;

    JPanel panelPic;
    JLabel pic = new JLabel();
    JLabel countryL;
    JLabel cityL;

    String country;

    Font font = new Font("Verdana", Font.BOLD, 18);
    int WIDTH = 850;
    int HEIGHT = 600;


    public FirstPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        listModel = new DefaultListModel();
        myList = new JList(listModel);


        myList.setFont(font);

        Box box1 = new Box(BoxLayout.LINE_AXIS);
        panelPic = new JPanel();
        panelPic.setLayout(new BoxLayout(panelPic, BoxLayout.Y_AXIS));
        panelPic.setSize(new Dimension(WIDTH / 4, HEIGHT));

        Map<String, String> capitals = new TreeMap<String, String>() {{
            put("afghanistan", "Kabul");
            put("albania", "Tirana");
            put("algeria", "Algiers");
            put("american samoa", "Pago Pago");
            put("andorra", "Andorra la Vella");
            put("angola", "Luanda");
            put("anguilla", "The Valley");
            put("antigua and barbuda", "Saint John's");
            put("argentina", "Buenos Aires");
            put("armenia", "Yerevan");
            put("aruba", "Oranjestad");
            put("australia", "Canberra");
            put("austria", "Vienna");
            put("azerbaijan", "Baku");
            put("bahamas", "Nassau");
            put("bahrain", "Manama");
            put("bangladesh", "Dhaka");
            put("barbados", "Bridgetown");
            put("belarus", "Minsk");
            put("belgium", "Brussels");
            put("belize", "Belmopan");
            put("benin", "Porto-Novo");
            put("bermuda", "Hamilton");
            put("bhutan", "Thimphu");
            put("bolivia", "La Paz");
            put("bosnia and herzegovina", "Sarajevo");
            put("botswana", "Gaborone");
            put("brazil", "Brasilia");
            put("british indian ocean territory", "Diego Garcia");
            put("british virgin islands", "Road Town");
            put("brunei", "Bandar Seri Begawan");
            put("bulgaria", "Sofia");
            put("burkina faso", "Ouagadougou");
            put("burma", "Naypyidaw");
            put("burundi", "Bujumbura");
            put("cambodia", "Phnom Penh");
            put("cameroon", "Yaounde");
            put("canada", "Ottawa");
            put("cape verde", "Praia");
            put("cayman islands", "George Town");
            put("central african republic", "Bangui");
            put("chad", "N'Djamena");
            put("chile", "Santiago");
            put("china", "Beijing");
            put("colombia", "Bogota");
            put("comoros", "Moroni");
            put("congo democratic republic", "Kinshasa");
            put("congo republic", "Brazzaville");
            put("cook islands", "Avarua");
            put("costa rica", "San Jose");
            put("cote divoire", "Yamoussoukro");
            put("croatia", "Zagreb");
            put("cuba", "Havana");
            put("cyprus", "Nicosia");
            put("czech republic", "Prague");
            put("denmark", "Copenhagen");
            put("djibouti", "Djibouti");
            put("dominica", "Roseau");
            put("dominican republic", "Santo Domingo");
            put("east timor", "Dili");
            put("egypt", "Cairo");
            put("el salvador", "San Salvador");
            put("england", "London");
            put("equador", "Quito");
            put("equatorial guinea", "Malabo");
            put("eritrea", "Asmara");
            put("estonia", "Tallinn");
            put("ethiopia", "Addis Ababa");
            put("falkland islands", "Stanley");
            put("faroe islands", "Torshavn");
            put("fiji", "Suva");
            put("finland", "Helsinki");
            put("france", "Paris");
            put("french polynesia", "Papeete");
            put("gabon", "Libreville");
            put("gambia", "Banjul");
            put("georgia", "Tbilisi");
            put("germany", "Berlin");
            put("ghana", "Accra");
            put("gibraltar", "Gibraltar");
            put("great britain", "Athens");
            put("greece", "Nuuk");
            put("greenland", "Saint George's");
            put("grenada", "Hagatna");
            put("guam", "Guatemala City");
            put("guatemala", "Saint Peter Port");
            put("guernsey", "Conakry");
            put("guinea", "Bissau");
            put("guinea bissau", "Georgetown");
            put("guyana", "Port-au-Prince");
            put("haiti", "Port-au-Prince");
            put("honduras", "Tegucigalpa");
            put("hong kong", "Hong Kong");
            put("hungary", "Budapest");
            put("iceland", "Reykjavik");
            put("india", "New Delhi");
            put("indonesia", "Jakarta");
            put("iran", "Tehran");
            put("iraq", "Baghdad");
            put("ireland", "Dublin");
            put("isle of man", "Douglas");
            put("israel", "Jerusalem");
            put("italy", "Rome");
            put("jamaica", "Kingston");
            put("japan", "Tokyo");
            put("jersey", "Saint Helier");
            put("jordan", "Amman");
            put("kazakhstan", "Astana");
            put("kenya", "Nairobi");
            put("kiribati", "Tarawa");
            put("kuwait", "Kuwait City");
            put("kyrgyzstan", "Bishkek");
            put("laos", "Vientiane");
            put("latvia", "Riga");
            put("lebanon", "Beirut");
            put("lesotho", "Maseru");
            put("liberia", "Monrovia");
            put("libya", "Tripoli");
            put("liechtenstein", "Vaduz");
            put("lithuania", "Vilnius");
            put("luxembourg", "Luxembourg");
            put("macau", "N/A");
            put("macedonia", "Skopje");
            put("madagascar", "Antananarivo");
            put("malawi", "Lilongwe");
            put("malaysia", "Kuala Lumpur");
            put("maledives", "Male");
            put("mali", "Bamako");
            put("malta", "Valletta");
            put("marshall islands", "Majuro");
            put("martinique", "Fort-de-France");
            put("mauretania", "Nouakchott");
            put("mauritius", "Port Louis");
            put("mexico", "Mexico City");
            put("micronesia", "Palikir");
            put("moldova", "Chisinau");
            put("monaco", "Monaco");
            put("mongolia", "Ulaanbaatar");
            put("montserrat", "Plymouth");
            put("morocco", "Rabat");
            put("mozambique", "Maputo");
            put("namibia", "Windhoek");
            put("nauru", "Yaren");
            put("nepal", "Kathmandu");
            put("netherlands", "Amsterdam");
            put("netherlands antilles", "Willemstad");
            put("new zealand", "Wellington");
            put("nicaragua", "Managua");
            put("niger", "Niamey");
            put("nigeria", "Abuja");
            put("niue", "Alofi");
            put("norfolk island", "Kingston");
            put("northern mariana islands", "Saipan");
            put("north korea", "Pyongyang");
            put("norway", "Oslo");
            put("oman", "Muscat");
            put("pakistan", "Islamabad");
            put("palau", "Melekeok");
            put("panama", "Panama City");
            put("papua new guinea", "Port Moresby");
            put("paraquay", "Asuncion");
            put("peru", "Lima");
            put("philippines", "Manila");
            put("pitcairn islands", "Adamstown");
            put("poland", "Warsaw");
            put("portugal", "Lisbon");
            put("puerto rico", "San Juan");
            put("qatar", "Doha");
            put("romania", "Bucharest");
            put("russia", "Moscow");
            put("rwanda", "Kigali");
            put("saint helena", "Jamestown");
            put("saint kitts and nevis", "Basseterre");
            put("saint lucia", "Castries");
            put("saint pierre and miquelon", "Saint-Pierre");
            put("saint vincent and the grenadines", "Kingstown");
            put("samoa", "Apia");
            put("san marino", "San Marino");
            put("sao tome and principe", "Sao Tome");
            put("saudi arabia", "Riyadh");
            put("scotland", "Edinburgh");
            put("senegal", "Dakar");
            put("serbia montenegro", "Belgrade");
            put("seychelles", "Victoria");
            put("sierra leone", "Freetown");
            put("singapore", "Singapore");
            put("slovakia", "Bratislava");
            put("slovenia", "Ljubljana");
            put("solomon islands", "Honiara");
            put("somalia", "Mogadishu");
            put("south africa", "Pretoria");
            put("south georgia", "King Edward Point");
            put("south korea", "Seoul");
            put("spain", "Madrid");
            put("sri lanka", "Colombo");
            put("sudan", "Khartoum");
            put("suriname", "Paramaribo");
            put("swaziland", "Mbabane");
            put("sweden", "Stockholm");
            put("switzerland", "Bern");
            put("syria", "Damascus");
            put("taiwan", "Taipei");
            put("tajikistan", "Dushanbe");
            put("tanzania", "Dar es Salaam");
            put("thailand", "Bangkok");
            put("tibet", "Lhasa");
            put("togo", "Lome");
            put("tonga", "Nuku'alofa");
            put("trinidad and tobago", "Port of Spain");
            put("tunisia", "Tunis");
            put("turkey", "Ankara");
            put("turkmenistan", "Ashgabat");
            put("turks and caicos islands", "Grand Turk");
            put("tuvalu", "Funafuti");
            put("uganda", "Kampala");
            put("ukraine", "Kyiv");
            put("united arab emirates", "Abu Dhabi");
            put("uruquay", "Montevideo");
            put("usa", "Washington");
            put("uzbekistan", "Tashkent");
            put("vanuatu", "Port-Vila");
            put("vatican city", "Vatican City");
            put("venezuela", "Caracas");
            put("vietnam", "Hanoi");
            put("virgin islands", "Charlotte Amalie");
            put("wales", "Cardiff");
            put("wallis and futuna", "Mata-Utu");
            put("yemen", "Sanaa");
            put("zambia", "Lusaka");
            put("zimbabwe", "Harare");
        }};

        for (Map.Entry<String, String> entry : capitals.entrySet()) {
            listModel.addElement(entry.getKey());
        }


        myList.setCellRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list,
                                                          Object value, int index, boolean isSelected,
                                                          boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list,
                        value, index, isSelected, cellHasFocus);
                JLabel label = (JLabel) component;
                Icon icon = new ImageIcon(".\\flags\\flag_" + value + ".png");
                label.setIcon(icon);
                return label;
            }
        });

        myList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                panelPic.removeAll();

                country = listModel.get(myList.getSelectedIndex());
                countryL = new JLabel("Country : " + country);
                cityL = new JLabel("Capital : " + capitals.get(country));

                countryL.setFont(font);
                cityL.setFont(font);

                //pic = new JLabel(new ImageIcon(".\\flags\\flag_" + country + ".png"));

                Image img = Toolkit.getDefaultToolkit().getImage(".\\flags\\flag_" + country + ".png");
                img = img.getScaledInstance(200, 155, Image.SCALE_SMOOTH);

                pic = new JLabel(new ImageIcon(img));


                panelPic.add(Box.createVerticalStrut(HEIGHT / 3));
                panelPic.add(countryL);
                panelPic.add(cityL);
                panelPic.add(Box.createVerticalStrut(HEIGHT / 40));
                panelPic.add(pic);

                panelPic.revalidate();
                panelPic.repaint();
            }
        });

        box1.add(new JScrollPane(myList));
        box1.add(Box.createHorizontalStrut(5));
        box1.add(new JScrollPane(panelPic));
        add(box1);

    }
}

class SecondPanel extends JPanel {
    Integer sum;
    int NUMBER = 5;
    JTable table;
    MyTableModel tableModel;

    String[] columnNames = {
            "Flag",
            "Description",
            "Price",
            "Choose"
    };

    Object data[][] = {
            {new ImageIcon(".\\flags\\flag_barbados.png"), "Amazing island", 3000, new Boolean(false)},
            {new ImageIcon(".\\flags\\flag_great britain.png"), "Amazing country", 2730, new Boolean(false)},
            {new ImageIcon(".\\flags\\flag_australia.png"), "Amazing country", 3450, new Boolean(false)},
            {new ImageIcon(".\\flags\\flag_japan.png"), "Amazing country", 1240, new Boolean(false)},
            {new ImageIcon(".\\flags\\flag_monaco.png"), "Amazing country", 2568, new Boolean(false)},
            {null, "Total : ", "0", new Boolean(false)},
    };

    public SecondPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        tableModel = new MyTableModel(data, columnNames);

        table = new JTable(tableModel);

        table.setRowHeight(97);

        table.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                int sum = 0;

                for (int i = 0; i < tableModel.getRowCount() - 1; i++)
                    if ((boolean) tableModel.getValueAt(i, 3) == true)
                        sum += (Integer) tableModel.getValueAt(i, 2);
                tableModel.setValueAt(sum, tableModel.getRowCount() - 1, 2);
            }
        });

        JButton button = new JButton("Add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewItem(null, "New item");
            }
        });

        add(new JScrollPane(table));
        add(button);
    }

    class MyTableModel extends DefaultTableModel {

        public MyTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return ImageIcon.class;
                case 1:
                    return String.class;
                case 2:
                    return Integer.class;
                case 3:
                    return Boolean.class;
                default:
                    return null;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex != 0 && columnIndex != 1 && rowIndex != this.getRowCount() - 1)
                return true;
            else return false;
        }

    }

    class NewItem extends JDialog {
        JTextField textDescription = new JTextField("", 8);
        JTextField textPrice = new JTextField("", 8);
        JButton chooseFlag = new JButton("Choose flag");

        public NewItem(JFrame parent, String name) {
            super(parent, name);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  //Автоматически скрывает и удаляет диалоговое окно

            Container container = this.getContentPane();
            container.setLayout(new GridLayout(4, 2));

            container.add(new JLabel("Description"));
            container.add(textDescription);

            container.add(new JLabel("Price"));
            container.add(textPrice);

            JLabel label = new JLabel();
            final ImageIcon[] imageIcon = new ImageIcon[1];

            chooseFlag.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("flags"));
                    fileChooser.setPreferredSize(new Dimension(700, 500));
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (.png)", "png");
                    fileChooser.setFileFilter(filter);

                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        imageIcon[0] = new ImageIcon(selectedFile.getAbsolutePath());
                        label.setIcon(imageIcon[0]);
                    }
                }
            });

            container.add(label);
            container.add(chooseFlag);

            JButton ok = new JButton("Ok");
            container.add(ok);

            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (textDescription.getText().equals(""))
                            throw new NumberFormatException("Enter the name!");
                        if (textPrice.getText().equals(""))
                            throw new NumberFormatException("Enter the price!");
                        if (imageIcon[0] == null)
                            throw new NumberFormatException("Choose the flag!");

                        Object[] newRow = {imageIcon[0], textDescription.getText(), Integer.parseInt(textPrice.getText()), new Boolean(false)};

                        boolean isIn = false;
                        for (int i = 0; i < tableModel.getRowCount() - 1; i++) {
                            for (int j = 0; j < tableModel.getColumnCount(); j++)
                                if (data[i][j].equals(newRow[j])) {
                                    isIn = true;
                                    break;
                                }
                        }
                       // if (isIn)
                            tableModel.insertRow(tableModel.getRowCount() - 1, newRow);

                        dispose(); // for closing

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

                    }
                }
            });

            pack();
            setLocationRelativeTo(null);
            setVisible(true);

        }

    }
}


class ThirdPanel extends JPanel {
    private Scanner scanner;
    private ArrayList<Student> students = new ArrayList<>();
    private Set<Integer> coursesT = new TreeSet<>();
    private Set<Course> courses = new TreeSet<>();
    private Set<Group> groupsSet;
    private Set<String> namesSet;

    private JButton addButton;
    private JButton deleteButton;
    private JButton editButton;
    private DefaultMutableTreeNode root;

    private JTree tree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode nCourse;
    private DefaultMutableTreeNode nGroup;

    private JLabel label;


    public ThirdPanel() {

        try {
            scanner = new Scanner(new File("input.txt"));
            while (scanner.hasNext()) {
                students.add(new Student(scanner.nextInt(), scanner.nextInt(), scanner.next() + " " + scanner.next()));
            }


            root = new DefaultMutableTreeNode("Students");
            for (Student student : students) {
                courses.add(new Course(student.getCourse()));
            }

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            treeModel = new DefaultTreeModel(root);

            for (Course course : courses) {
                for (Student student : students) {
                    if (course.getCourse() == student.getCourse())
                        course.addGroup(new Group(student.getGroup()));
                }
            }


            for (Course course : courses) {
                groupsSet = course.getGroups();
                for (Group group : groupsSet) {
                    for (Student student : students) {
                        if (course.getCourse() == student.getCourse() && group.getGroup() == student.getGroup())
                            course.setGroupNames(group, student.getName());
                    }
                }
            }


            for (Course course : courses) {
                coursesT.add(course.getCourse());
            }

            //putting in the tree !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            for (Course c : courses) {
                nCourse = new DefaultMutableTreeNode(c.getCourse() + " course");
                groupsSet = c.getGroups();


                for (Group grSet : groupsSet) {
                    nGroup = new DefaultMutableTreeNode(grSet.getGroup() + " group");

                    namesSet = grSet.getNames();
                    for (String name : namesSet)
                        nGroup.add(new DefaultMutableTreeNode(name));

                    nCourse.add(nGroup);
                }
                root.add(nCourse);
            }


            tree = new JTree(treeModel);
            tree.setRootVisible(true);
            tree.setEditable(true);


            MyCellRenderer renderer = new MyCellRenderer(); //renderer - визуализатор
            tree.setCellRenderer(renderer);
            tree.setEditable(false);

            addButton = new JButton("Add");
            addButton.addActionListener(e -> new NewStudent(null, "New student"));

            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                try {
                    if(selectedNode == null) throw new NullPointerException("Choose the student!");

                    if (selectedNode.getChildCount() == 0 && selectedNode.getLevel() == 3) {
                        /*if (selectedNode.getParent().getParent().getChildCount() == 1 && selectedNode.getParent().getChildCount() == 1)
                            treeModel.removeNodeFromParent((DefaultMutableTreeNode) selectedNode.getParent().getParent());
                        if (selectedNode.getParent().getChildCount() == 1)
                            treeModel.removeNodeFromParent((DefaultMutableTreeNode) selectedNode.getParent());*/
                        treeModel.removeNodeFromParent(selectedNode);
                    }
                } catch(NullPointerException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
            });

            editButton = new JButton("Edit");
            editButton.addActionListener(e -> {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                try {
                    if (selectedNode == null) throw new NullPointerException("Choose the student!");

                    if (selectedNode.getLevel() == 3) {
                        new EditStudent(null, "Edit",
                                selectedNode.getParent().getParent().toString(),
                                selectedNode.getParent().toString(),
                                selectedNode.toString(),
                                selectedNode);
                    }
                }catch(NullPointerException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                }
            });

            add(new JScrollPane(tree));
            JPanel b = new JPanel();
            b.setLayout(new BoxLayout(b, BoxLayout.LINE_AXIS));
            b.add(addButton);
            b.add(deleteButton);
            b.add(editButton);
            add(b);


        } catch (FileNotFoundException e) {
            e.getMessage();
        }

    }


    class MyCellRenderer extends DefaultTreeCellRenderer {
        final ImageIcon ICON_UNIVER = new ImageIcon("univer.jpg");
        final ImageIcon ICON_COURSE = new ImageIcon("course.png");
        final ImageIcon ICON_GROUP = new ImageIcon("group.png");
        final ImageIcon ICON_STUDENT = new ImageIcon("student.png");

        public MyCellRenderer() {
            label = new JLabel();
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            Object o = ((DefaultMutableTreeNode) value).getUserObject();
            if (((DefaultMutableTreeNode) value).getLevel() == 0) {
                label.setIcon(ICON_UNIVER);
                label.setText(value.toString());
            }
            if (((DefaultMutableTreeNode) value).getLevel() == 1) {
                label.setIcon(ICON_COURSE);
                label.setText(value.toString());
            }
            if (((DefaultMutableTreeNode) value).getLevel() == 2) {
                label.setIcon(ICON_GROUP);
                label.setText(value.toString());
            }
            if (((DefaultMutableTreeNode) value).getLevel() == 3) {
                label.setIcon(ICON_STUDENT);
                label.setText(value.toString());
            }
            if (selected)
                label.setOpaque(true);
            else label.setOpaque(false);

            return label;
        }

    }

    class NewStudent extends JDialog {
        JTextField textCourse = new JTextField("", 8);
        JTextField textGroup = new JTextField("", 8);
        JTextField textName = new JTextField("", 8);

        public NewStudent(JFrame parent, String name) {
            super(parent, name);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  //Автоматически скрывает и удаляет диалоговое окно

            Container container = this.getContentPane();
            container.setLayout(new GridLayout(4, 2));

            container.add(new JLabel("Course"));
            container.add(textCourse);

            container.add(new JLabel("Group"));
            container.add(textGroup);

            container.add(new JLabel("Name"));
            container.add(textName);

            JButton ok = new JButton("Ok");
            container.add(ok);

            ok.addActionListener(e -> {
                try {
                    if (textCourse.getText().equals(""))
                        throw new NumberFormatException("Enter the name!");
                    if (textGroup.getText().equals(""))
                        throw new NumberFormatException("Enter the price!");
                    if (textName.getText().equals(""))
                        throw new NumberFormatException("Enter the name!");

                    addStudent(textCourse.getText(), textGroup.getText(), textName.getText());

                    dispose(); // for closing . tr- размещать

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

                }
            });

            pack();
            setLocationRelativeTo(null);
            setVisible(true);

        }

    }

    void addStudent(String textCourse, String textGroup, String textName) {
        DefaultMutableTreeNode nodeCourse = new DefaultMutableTreeNode(textCourse + " course");
        DefaultMutableTreeNode nodeGroup = new DefaultMutableTreeNode(textGroup + " group");
        DefaultMutableTreeNode nodeName = new DefaultMutableTreeNode(textName);

        boolean foundCourse = false, foundGroup = false, foundName = false;
        int indCourse = 0, indGroup = 0, indName = 0;
        for (int i = 0; i < root.getChildCount(); i++) {
            if ((root.getChildAt(i).toString()).equals(nodeCourse.toString())) {
                indCourse = i;
                foundCourse = true;
                break;
            }
        }

        //if course is find
        if (foundCourse) {
            for (int i = 0; i < root.getChildAt(indCourse).getChildCount(); i++) {
                if (root.getChildAt(indCourse).getChildAt(i).toString().equals(nodeGroup.toString())) {
                    indGroup = i;
                    foundGroup = true;
                    break;
                }
            }
            if (foundGroup) {
                for (int i = 0; i < root.getChildAt(indCourse).getChildAt(indGroup).getChildCount(); i++)
                    if (root.getChildAt(indCourse).getChildAt(indGroup).getChildAt(i).toString().equals(nodeName.toString())) {
                        foundName = true;
                        break;
                    }

                if (!foundName)
                    treeModel.insertNodeInto(nodeName, (DefaultMutableTreeNode) root.getChildAt(indCourse).getChildAt(indGroup), root.getChildAt(indCourse).getChildAt(indGroup).getChildCount());
            } else {
                treeModel.insertNodeInto(nodeGroup, (DefaultMutableTreeNode) root.getChildAt(indCourse), root.getChildAt(indCourse).getChildCount());
                treeModel.insertNodeInto(nodeName, nodeGroup, 0);
            }
        } else {
            treeModel.insertNodeInto(nodeCourse, root, root.getChildCount());
            treeModel.insertNodeInto(nodeGroup, nodeCourse, 0);
            treeModel.insertNodeInto(nodeName, nodeGroup, 0);
        }
    }

    class EditStudent extends JDialog {
        private JTextField textCourse;
        private JTextField textGroup;
        private JTextField textName;

        public EditStudent(JFrame parent, String name, String course, String group, String nameStud, DefaultMutableTreeNode node) {
            super(parent, name);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);  //Автоматически скрывает и удаляет диалоговое окно

            textCourse = new JTextField(course.substring(0, course.length() - 7), 8);
            textGroup = new JTextField(group.substring(0, group.length() - 6), 8);
            textName = new JTextField(nameStud, 8);

            Container container = this.getContentPane();
            container.setLayout(new GridLayout(4, 2));

            container.add(new JLabel("Course"));
            container.add(textCourse);

            container.add(new JLabel("Group"));
            container.add(textGroup);

            container.add(new JLabel("Name"));
            container.add(textName);

            JButton edit = new JButton("Edit");
            container.add(edit);

            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (textCourse.getText().equals(""))
                            throw new NumberFormatException("Enter the name!");
                        if (textGroup.getText().equals(""))
                            throw new NumberFormatException("Enter the price!");
                        if (textName.getText().equals(""))
                            throw new NumberFormatException("Enter the name!");

                        if (textCourse.getText().equals(course.substring(0, course.length() - 7)) && textGroup.getText().equals(group.substring(0, group.length() - 6)) && !textName.getText().equals(nameStud)) {
                            node.setUserObject(textName.getText());
                            treeModel.reload(node);
                            dispose(); // for closing . tr- размещать
                        } else if (!textName.equals(nameStud)) {
                            addStudent(textCourse.getText(), textGroup.getText(), textName.getText());
                            dispose(); // for closing . tr- размещать
                            treeModel.removeNodeFromParent(node);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

                    }
                }
            });

            pack();
            setLocationRelativeTo(null);
            setVisible(true);

        }

    }

}
