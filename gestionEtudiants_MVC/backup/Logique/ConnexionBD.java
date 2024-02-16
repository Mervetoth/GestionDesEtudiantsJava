package Logique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnexionBD {
    private Connection connexion;
    private Statement instruction;
    private ResultSet resultat;

    // Constructeur pour établir une connexion à la base de données
    public ConnexionBD() {
        try {
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestionEtudiants", "root", "root1234");
            instruction = connexion.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour fermer la connexion BD
    public void arret() {
        try {
            if (resultat != null) resultat.close();
            if (instruction != null) instruction.close();
            if (connexion != null) connexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour afficher la liste des étudiants
    public void getStudents() {
        try {
            resultat = instruction.executeQuery("SELECT * FROM Etudiant");
            while (resultat.next()) {
                System.out.println("ID: " + resultat.getInt("id") + ", Nom: " + resultat.getString("nom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Méthode pour insérer un nouvel étudiant
    public void insertStudent(Etudiant etudiant) {
        try {
            String query = "INSERT INTO Etudiant (nom, prenom, age, email) VALUES ('" +
                           etudiant.getNom() + "', '" +
                           etudiant.getPrenom() + "', " +
                           etudiant.getAge() + ", '" +
                           etudiant.getEmail() + "')";
            instruction.executeUpdate(query);
            System.out.println("Étudiant inséré avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteStudent(int id) {
        try {
            String query = "DELETE FROM Etudiant WHERE id = " + id;
            int rowsAffected = instruction.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Étudiant supprimé avec succès!");
            } else {
                System.out.println("Aucun étudiant trouvé avec l'ID spécifié.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

}
