package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.ConnexionBD;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Accueil extends JFrame {
    private JPanel contentPane;
    private JLabel title, subTitle;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;

    public Accueil() {
        // Initialization code
        initUI();
    }

    private void initUI() {
    	///-----****** frame ******-----\\\

        setTitle("Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setResizable(true );
      ///-----****** contentPane ******-----\\\

        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        contentPane.setBackground(new Color(0xF3D9D0)); // Light pink color
        setContentPane(contentPane);
        ///-----****** TITLE PANEL ******-----\\\
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // Set background to be transparent
        JLabel title = new JLabel("Liste des etudiants");
        title.setFont(new Font("Arial", Font.PLAIN, 35));
        JLabel subTitle = new JLabel("Consulter, modifier, ou supprimer des étudiants, ainsi qu'ajouter de nouveaux étudiants.");
        subTitle.setFont(new Font("Arial", Font.PLAIN, 15));
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(title);
        titlePanel.add(subTitle);

        ///-----****** AJOUT PANEL ******-----\\\
        JPanel ajoutPanel = new JPanel();
        ajoutPanel.setOpaque(false); // Set background to be transparent
        ajoutPanel.setLayout(new BoxLayout(ajoutPanel, BoxLayout.Y_AXIS));
        ImageIcon plusIcon = new ImageIcon("C:\\Users\\nermine\\eclipse-workspace\\gestionEtudiants_MVC\\images\\plus.png");
        JLabel plusLabel = new JLabel(plusIcon);
        ajoutPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        JLabel ajouterLabel = new JLabel("Ajouter Etudiant");
        ajouterLabel.setFont(new Font("Arial", Font.BOLD, 18));
        ajouterLabel.setForeground(new Color(0x36943F)); // Green color
        ajoutPanel.add(plusLabel);
        ajoutPanel.add(ajouterLabel);

        ///-----****** contentPane add items ******-----\\\
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false); // Set background to be transparent
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS)); // Use BoxLayout with LINE_AXIS alignment
        headerPanel.add(titlePanel); // titlePanel will be at the start
        headerPanel.add(Box.createHorizontalGlue()); // Add some spacing between panels
        headerPanel.add(ajoutPanel); // ajoutPanel will be at the end

        contentPane.add(headerPanel, BorderLayout.NORTH); // Add headerPanel to the top of contentPane

        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // Adjust the height as needed

        
        plusLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FenetreAjout fenetreAjout = new FenetreAjout();
                fenetreAjout.setVisible(true);
                System.out.println("FenetreAjout displayed.");
                // Update the table in Accueil when FenetreAjout is closed
                fenetreAjout.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        // Call updateTable method from Accueil class
                        updateTable();
                    }
                });
            }
        });
    	///-----****** table model + scroll  ******-----\\\

        model = new DefaultTableModel();
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        table.setBorder(null);
        table.setRowHeight(35);
        // Change the font and font size of the table cells
        Font cellFont = new Font("Montserrat", Font.PLAIN, 14);
        table.setFont(cellFont);
        // Set column headers' font and size
        Font headerFont = new Font("Montserrat", Font.BOLD, 16);
        table.getTableHeader().setFont(headerFont);
        table.getTableHeader().setPreferredSize(new Dimension(table.getColumnModel().getTotalColumnWidth(), 50));

// Add columns to the model
        model.addColumn("ID");
        model.addColumn("Nom");
        model.addColumn("Prenom");
        model.addColumn("Email");
        model.addColumn("Age");
        model.addColumn("Edit");
        model.addColumn("Delete");

        // Add the table to a scroll pane and then add the scroll pane to the frame
        scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane);

        // Populate the table with data from the database
     // Populate the table with data from the database
        ConnexionBD connexionBD = new ConnexionBD();
        try {
            ResultSet resultSet = connexionBD.statement.executeQuery("SELECT * FROM Etudiant");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                ImageIcon editIcon = new ImageIcon("C:\\Users\\nermine\\eclipse-workspace\\gestionEtudiants_MVC\\images\\editer.png");
                ImageIcon deleteIcon = new ImageIcon("C:\\Users\\nermine\\eclipse-workspace\\gestionEtudiants_MVC\\images\\poubelle.png");
                model.addRow(new Object[]{id, nom, prenom, email, age, editIcon, deleteIcon});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());
                if (col == 5) { // "Edit" column clicked
                    // Show edit window
                    String id = (String) model.getValueAt(row, 0);
                    String nom = (String) model.getValueAt(row, 1);
                    String prenom = (String) model.getValueAt(row, 2);
                    String email = (String) model.getValueAt(row, 3);
                    int age = (int) model.getValueAt(row, 4);
                    new FenetreEdit(id, nom, prenom, email, age, Accueil.this);
                } else if (col == 6) { // "Delete" column clicked
                    // Delete the user
                    String id = (String) model.getValueAt(row, 0);
                    deleteUserData(id);
                }
            }
        });

        // Render the "Edit" column with ImageIcon
        table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    return new JLabel((ImageIcon) value);
                } else {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            }
        });
        
        
     // Render the "Delete" column with ImageIcon
        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    return new JLabel((ImageIcon) value);
                } else {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            }
        });

        // Remove the fixed row height
        table.setRowHeight(50); // Set the desired row height

        // Set the preferred column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Adjust the width as needed
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Adjust the width as needed
        
    }

    // Method to delete the user from the database
    private void deleteUserData(String id) {
        ConnexionBD connexionBD = new ConnexionBD();
        try {
            String query = "DELETE FROM Etudiant WHERE id='" + id + "'";
            connexionBD.statement.executeUpdate(query);

            // Show delete popup
            JOptionPane.showMessageDialog(Accueil.this, "User deleted successfully.");

            // Update the table in Accueil
            updateTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to update the table with data from the database
    public void updateTable() {
        model.setRowCount(0); // Clear previous data
        ConnexionBD connexionBD = new ConnexionBD();
        try {
            ResultSet resultSet = connexionBD.statement.executeQuery("SELECT * FROM Etudiant");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                ImageIcon editIcon = new ImageIcon("C:\\Users\\nermine\\eclipse-workspace\\gestionEtudiants_MVC\\images\\editer.png");
                ImageIcon deleIcon = new ImageIcon("C:\\Users\\nermine\\eclipse-workspace\\gestionEtudiants_MVC\\images\\poubelle.png");

                
                model.addRow(new Object[]{id, nom, prenom, email, age, editIcon, deleIcon});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Accueil accueil = new Accueil();
            accueil.setVisible(true);
        });
    }
}