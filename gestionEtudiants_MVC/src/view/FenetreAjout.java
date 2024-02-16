package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ConnexionBD;
import model.Etudiant;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreAjout extends JFrame {
    private JLabel nomLabel, prenomLabel, ageLabel, emailLabel;
    private JTextField nomField, prenomField, ageField, emailField;
    private JButton okButton, cancelButton;
    private JPanel contentPane;

    public FenetreAjout() {
        setTitle("Ajout Etudiant");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setResizable(true );
        // Initialize contentPane
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
        setContentPane(contentPane);
        setLocationRelativeTo(null); // Center the window
      
      ///-----****** TITLE PANEL ******-----\\\
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false); // Set background to be transparent
        JLabel title = new JLabel("Ajout etudiant");
        title.setFont(new Font("Arial", Font.PLAIN, 35));
        JLabel subTitle = new JLabel("Saisissez les informations d'Etudiant .");
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

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Add padding

        // Labels and text fields
        nomLabel = new JLabel("Nom:");
        nomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nomLabel, gbc);

        nomField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(nomField, gbc);

        prenomLabel = new JLabel("Prenom:");
        prenomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(prenomLabel, gbc);

        prenomField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(prenomField, gbc);

        ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(ageLabel, gbc);

        ageField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(ageField, gbc);

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

     // Buttons
        okButton = new JButton("OK");
        okButton.setBackground(new Color(173, 216, 230)); // Light blue color
        okButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; // Span across one column
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(okButton, gbc);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(173, 216, 230)); // Light blue color
        cancelButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size
        gbc.gridx = 1; // Move to the next column
        gbc.gridy = 4; // Same row as OK button
        gbc.gridwidth = 1; // Span across one column
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(cancelButton, gbc);


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Extract student information
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String ageText = ageField.getText();
                String email = emailField.getText();

                // Validate age input
                int age = 0;
                try {
                    age = Integer.parseInt(ageText);
                    if (age <= 0) {
                        JOptionPane.showMessageDialog(FenetreAjout.this, "Age must be a positive number.");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FenetreAjout.this, "Please enter a valid age.");
                    return;
                }

                // Validate email input
                if (!isValidEmailAddress(email)) {
                    JOptionPane.showMessageDialog(FenetreAjout.this, "Please enter a valid email address.");
                    return;
                }

                // Create an Etudiant object
                Etudiant etudiant = new Etudiant(nom, prenom, age, email);

                // Insert new student into the database
                ConnexionBD connexionBD = new ConnexionBD();
                connexionBD.insertStudent(etudiant);

                // Show success message
                JOptionPane.showMessageDialog(FenetreAjout.this, "Student added successfully.");

                // Close FenetreAjout window
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close FenetreAjout window
                dispose();
            }
        });

        // Add the panel to the frame
        add(headerPanel);
        add(panel);
        //contentPane.add(titlePanel);
        contentPane.add(Box.createRigidArea(new Dimension(0, 20))); // Adjust the height as needed

        contentPane.add(panel);
        // Pack the frame to fit its contents
        pack();
    }

    // Method to validate email address
    private boolean isValidEmailAddress(String email) {
        // Basic email validation using regex
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
}
