package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ConnexionBD;
import model.Etudiant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FenetreEdit extends JFrame {
    private JTextField nomField, prenomField, emailField, ageField;
    private JButton okayButton;
    private String id;
    private Accueil parent;
    private JPanel contentPane;

    public FenetreEdit(String id, String nom, String prenom, String email, int age, Accueil parent) {
        this.id = id;
        this.parent = parent;

        setTitle("Ajout Etudiant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Set a smaller size
        setResizable(true);
        // Initialize contentPane
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        setContentPane(contentPane);
        setLocationRelativeTo(null); // Center the window
      
      ///-----****** TITLE PANEL ******-----\\\
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // Set background to be transparent
        JLabel title = new JLabel("Edit etudiant");
        title.setFont(new Font("Arial", Font.PLAIN, 35));
        JLabel subTitle = new JLabel("Saisissez les nouveaux informations d'Etudiant .");
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

        ajoutPanel.add(plusLabel);

        ///-----****** contentPane add items ******-----\\\
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false); // Set background to be transparent
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS)); // Use BoxLayout with LINE_AXIS alignment
        headerPanel.add(ajoutPanel); // ajoutPanel will be at the end
        headerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Adjust the height as needed

        headerPanel.add(titlePanel); // titlePanel will be at the start

        contentPane.add(headerPanel, BorderLayout.NORTH); // Add headerPanel to the top of contentPane

        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // Adjust the height as needed

        // Panel for fields and buttons
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Add padding

        // Labels and text fields
        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nomLabel, gbc);

        nomField = new JTextField(nom, 20);
        gbc.gridx = 1;
        panel.add(nomField, gbc);

        JLabel prenomLabel = new JLabel("Prenom:");
        prenomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(prenomLabel, gbc);

        prenomField = new JTextField(prenom, 20);
        gbc.gridx = 1;
        panel.add(prenomField, gbc);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(ageLabel, gbc);

        ageField = new JTextField(String.valueOf(age), 20);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(email, 20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Buttons
        okayButton = new JButton("Save");
        okayButton.setBackground(new Color(173, 216, 230)); // Light blue color
        okayButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(okayButton, gbc);

        // Add panel to contentPane
        contentPane.add(panel);

        // Action Listener for Save button
        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserData();
                dispose(); // Close the window after updating
            }
        });

        setVisible(true);
    }

    // Method to update user data in the database
    private void updateUserData() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String email = emailField.getText();
        int age = Integer.parseInt(ageField.getText());

        ConnexionBD connexionBD = new ConnexionBD();
        try {
            String query = String.format("UPDATE Etudiant SET nom='%s', prenom='%s', email='%s', age=%d WHERE id='%s'",
                    nom, prenom, email, age, id);
            connexionBD.statement.executeUpdate(query);

            // Update the table in Accueil
            parent.updateTable();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
