package mypackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

//mouseDragged - движение при зажатой кнопке

public class MyFrame extends JFrame {
    MyPanel drawPanel;
    private final int WIDTH = 900, HEIGHT = 800;

    JScrollPane scrollPane;

    private ArrayList<MyLine> lines = new ArrayList<>();
    private MyLine currentLine;
    private Color colorOfBrush = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
    BasicStroke pen;

    PrintWriter printWriter;
    BufferedImage bufferedImage;
    int maxX = 0;
    int maxY = 0;
    int currentY = 0;

    JFileChooser fileChooser;
    File selectedFile;

    public MyFrame() {
        super("Paint");

        try {
            setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);

            drawPanel = new MyPanel();
            drawPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
            //drawPanel.setLayout(new GridLayout());
            //scrollPane = new JScrollPane(drawPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane = new JScrollPane(drawPanel);


            drawPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    currentLine = new MyLine(colorOfBrush);
                    lines.add(currentLine);
                    currentLine.addPoint(e.getPoint());

                    repaint();
                }

            });


            drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    currentLine.addPoint(e.getPoint());
                    repaint();
                }
            });


            //create menu
            Font font = new Font("Verdana", Font.PLAIN, 15);
            JMenuBar menuBar = new JMenuBar(); //bar-полоса

            JMenu fileMenu = new JMenu("File");
            fileMenu.setFont(font);
            menuBar.add(fileMenu);

            JMenuItem openItem = new JMenuItem("Open...");
            openItem.setFont(font);
            fileMenu.add(openItem);

            openItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    fileChooser.setPreferredSize(new Dimension(700, 500));



                    try {
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (.jpg)", "jpg");
                        fileChooser.setFileFilter(filter);
                        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            selectedFile = fileChooser.getSelectedFile();
                            lines.clear();
                            repaint();


                            bufferedImage = ImageIO.read(selectedFile);  //загружаем в буфер картинку
                            drawPanel.setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));


                        }


                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Error", "Error!", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

            JMenu saveMenu = new JMenu("Save as...");
            saveMenu.setFont(font);
            fileMenu.add(saveMenu);

            JMenuItem saveJpg = new JMenuItem(".jpg"); //item - пункт
            saveJpg.setFont(font);
            saveMenu.add(saveJpg);

            saveJpg.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) throws NumberFormatException{
                    fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    fileChooser.setPreferredSize(new Dimension(700, 500));

                    File selectedFile;


                    try {
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images (.jpg)", "jpg");
                        fileChooser.setFileFilter(filter);

                        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                            selectedFile = fileChooser.getSelectedFile();
                            bufferedImage = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                            drawPanel.paintComponent(bufferedImage.getGraphics());  //рисуем в буфере картинку

                            ImageIO.write(bufferedImage, "jpg", selectedFile);

                        }



                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Error", "Error!", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });

            fileMenu.addSeparator();

            JMenuItem exitItem = new JMenuItem("Exit");
            exitItem.setFont(font);
            fileMenu.add(exitItem);

            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            setJMenuBar(menuBar);

            JMenu colorMenue = new JMenu("Color");
            colorMenue.setFont(font);


            JMenuItem backgroundItem = new JMenuItem("Background");
            backgroundItem.setFont(font);
            colorMenue.add(backgroundItem);

            backgroundItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color tempColor = JColorChooser.showDialog(new JFrame(), "Choose a color", backgroundColor);
                    if (tempColor != null) {
                        backgroundColor = tempColor;
                        bufferedImage = null;
                    }
                    repaint();
                }
            });

            JMenuItem brushItem = new JMenuItem("Brush");
            brushItem.setFont(font);
            colorMenue.add(brushItem);

            brushItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Color tempColor = JColorChooser.showDialog(new JFrame(), "Choose a color", colorOfBrush);
                    if (tempColor != null)
                        colorOfBrush = tempColor;
                }
            });

            menuBar.add(colorMenue);


            //my cursor
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage("brush.png");
            Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 31), "Brush");  //custom - заказ
            drawPanel.setCursor(cursor);

            add(scrollPane);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error! " + e.getMessage());
        }


    }

    class MyPanel extends JPanel {
        public MyPanel(LayoutManager layout) {
            super(layout);
        }

        public MyPanel() {
            super();
        }

        @Override
        public void paintComponent(Graphics g) {
            g.setColor(backgroundColor);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            g.drawImage(bufferedImage, 0, 0, this);


            for (MyLine line : lines) {
                Graphics2D gr = (Graphics2D) g;
                pen = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                gr.setStroke(pen);

                line.draw(g);
            }

            revalidate();

        }

    }

    private File ChooseFile(String filtername, String fileext, Boolean open) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));


        if (open) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(filtername, fileext);
            chooser.setFileFilter(filter);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                return chooser.getSelectedFile();
            else
                return null;
        } else {
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                return chooser.getSelectedFile();
            else
                return null;
        }
    }
}
