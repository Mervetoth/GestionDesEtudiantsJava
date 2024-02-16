package Graphique;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Logique.ConnexionBD;
import Logique.Etudiant;

public class FenetreAjout extends JFrame {

    private JLabel nomLabel, prenomLabel, ageLabel, emailLabel;
    private JTextField nomField, prenomField, ageField, emailField;
    private JButton okButton, cancelButton;

    public FenetreAjout() {
        nomLabel = new JLabel("Nom");
        prenomLabel = new JLabel("Prénom");
        ageLabel = new JLabel("Âge");
        emailLabel = new JLabel("Email");

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        ageField = new JTextField(20);
        emailField = new JTextField(20);

        okButton = new JButton("OK");
        cancelButton = new JButton("Annuler");

        setTitle("Ajout d'un étudiant");
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        setSize(700, 400);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(nomLabel);
        add(nomField);
        add(prenomLabel);
        add(prenomField);
        add(ageLabel);
        add(ageField);
        add(emailLabel);
        add(emailField);
        add(okButton);
        add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nom = nomField.getText();
                    String prenom = prenomField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String email = emailField.getText();

                    Etudiant etudiant = new Etudiant(nom, prenom, age, email);
                    ConnexionBD connexionBD = new ConnexionBD();
                    connexionBD.insertStudent(etudiant);
                    JOptionPane.showMessageDialog(null, "Ajout avec succès!!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer des données valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        new FenetreAjout();
    }
}
