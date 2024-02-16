//package Graphique;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.sql.*;
//
//public class Accueil extends JFrame {
//
//    public Accueil() {
//        setTitle("Accueil");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1000, 700);
//        setResizable(true);
//
//        JPanel contentPane = new JPanel();
//        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
//        contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
//        contentPane.setBackground(new Color(0xF3D9D0)); // Light pink color
//        setContentPane(contentPane);
//
//        JLabel title = new JLabel("Student Management");
//        title.setFont(new Font("Arial", Font.PLAIN, 35));
//        title.setAlignmentX(JLabel.LEFT_ALIGNMENT);
//
//        JLabel subTitle = new JLabel("Student Information: Click on a row to view more details.");
//        subTitle.setFont(new Font("Arial", Font.PLAIN, 20));
//        subTitle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
//
//        contentPane.add(title);
//        contentPane.add(subTitle);
//
//        // Create a table model to hold the data
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//        table.setBorder(null);
//        table.setRowHeight(35);
//        // Change the font and font size of the table cells
//        Font cellFont = new Font("Montserrat", Font.PLAIN, 14);
//        table.setFont(cellFont);
//
//        // Set default renderer to handle cell styling
//        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
//        cellRenderer.setFont(cellFont);
//        table.setDefaultRenderer(Object.class, cellRenderer);
//
//        // Set column headers' font and size
//        Font headerFont = new Font("Montserrat", Font.BOLD, 16);
//        table.getTableHeader().setFont(headerFont);
//        // Change the height of the first row
//        table.setRowHeight(50); // Set the height to 50 pixels (adjust as needed)
//
//        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
//        if (defaults.get("Table.alternateRowColor") == null)
//            defaults.put("Table.alternateRowColor", new Color(240, 240, 240));
//        // Add columns to the model
//        model.addColumn("ID");
//        model.addColumn("Nom");
//        model.addColumn("Prenom");
//        model.addColumn("Email");
//        model.addColumn("Age");
//        model.addColumn("Actions");
//
//        // Change the height of the header row
//        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 50));
//
//        // Change font and color of the header row
//        table.getTableHeader().setForeground(Color.DARK_GRAY); // Set color to blue
//
//
//        // Fetch data from the database and populate the table
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestionEtudiants", "root", "root1234");
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Etudiant");
//            while (rs.next()) {
//                model.addRow(new Object[]{rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("age")});
//            }
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Center align the content in the columns
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//        }
//
//        // Add the table to a scroll pane and then add the scroll pane to the frame
//        JScrollPane scrollPane = new JScrollPane(table);
//        contentPane.add(scrollPane);
//        // Create a clickable image
//        ImageIcon iconEdit = new ImageIcon("C:\\Users\\nermine\\eclipse-workspace\\gestionEtudiants_MVC\\images\\poubelle.png");
//        JLabel editButton = new JLabel(iconEdit);
//        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        editButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                // Handle click event here
//                JOptionPane.showMessageDialog(Accueil.this, "Image clicked!");
//            }
//        });
//        contentPane.add(editButton); // Add the label to the content pane
//
//        setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(Accueil::new);
//    }
//}
