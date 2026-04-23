package ar.edu.utnfc.backend.EjercicioDataBase;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import org.h2.Driver;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityManager;

public class Main {
    
    private static ResultSet ResultSet;

    public static void main(String[] args) throws SQLException {

        /*
        // JDBC
        
        // FORMA 1 - de configurar el driver que quiero utilizar 
        //new Driver();

        // FORMA 2
        // Class.forName("org.h2.Driver");

        // FORMA 3
        DriverManager.registerDriver(new Driver());

        //PRIMER PARAMETRO = cadena de conexion que es libre para cada uno de los drivers
        // SEGUNDO PARAMETRO = usuario
        // TERCER PARAMETRO = contraseña
        // getConnection revuelve un objeto de tipo connection
        
        try(Connection con = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            PreparedStatement query = con.prepareStatement("select * from film where film:id = ?");) {

                query.setLong(1, 5);
                try(ResultSet ResultSet = query.executeQuery();){

                    while (ResultSet.next()){
                    // Procesar cada fila del resultado
                        long id = ResultSet.getLong("id");
                        String titulo = ResultSet.getString("titulo");
                        System.out.println("ID: " + id + ", Título: " + titulo);
                    }
                }
            }
        }   
        */

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("sakilaPU");
            EntityManager em = emf.createEntityManager();

            // primer parametro la clase con la que voy a trabajar
            Film f1 = em.find(Film.class, 5L);

            System.out.println(f1);
            
            em.close();
            emf.close();

            Languaje l = new Languaje();
            l.setName("Español");
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
            System.out.println("Lenguaje insertado: " + l);
            
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
