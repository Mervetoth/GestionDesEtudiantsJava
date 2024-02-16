package Graphique;

import javax.swing.*;
import Logique.ConnexionBD;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreSuppression extends JFrame {

    private JLabel idLabel;
    private JTextField idField;
    private JButton supprimerButton, annulerButton;

    public FenetreSuppression() {
        idLabel = new JLabel("ID de l'étudiant à supprimer:");
        idField = new JTextField(10);
        supprimerButton = new JButton("Supprimer");
        annulerButton = new JButton("Annuler");

        setTitle("Suppression d'un étudiant");
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
        setSize(400, 200);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(idLabel);
        add(idField);
        add(supprimerButton);
        add(annulerButton);

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    ConnexionBD connexionBD = new ConnexionBD();
                    connexionBD.deleteStudent(id);
                    JOptionPane.showMessageDialog(null, "Étudiant supprimé avec succès!", "Information", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        new FenetreSuppression();
    }
}
