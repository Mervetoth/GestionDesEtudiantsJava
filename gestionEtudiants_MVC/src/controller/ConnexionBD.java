package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Etudiant;

public class ConnexionBD {
    public Connection connection;
    public Statement statement;
    private ResultSet resultSet;

    public ConnexionBD() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/GestionEtudiants", "root", "root1234");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertStudent(Etudiant etudiant) {
        try {
            String query = "INSERT INTO Etudiant (nom, prenom, age, email) VALUES ('" +
                           etudiant.getNom() + "', '" +
                           etudiant.getPrenom() + "', " +
                           etudiant.getAge() + ", '" +
                           etudiant.getEmail() + "')";
            statement.executeUpdate(query);
            System.out.println("Étudiant inséré avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteStudent(int id) {
        try {
            String query = "DELETE FROM Etudiant WHERE id = " + id;
            int rowsAffected = statement.executeUpdate(query);
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
