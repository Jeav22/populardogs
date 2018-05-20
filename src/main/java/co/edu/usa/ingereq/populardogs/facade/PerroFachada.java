package co.edu.usa.ingereq.populardogs.facade;

import co.edu.usa.ingereq.populardogs.dao.ServiceImpl;
import co.edu.usa.ingereq.populardogs.dao.SingletonConnection;
import co.edu.usa.ingereq.populardogs.exception.ConexionException;
import co.edu.usa.ingereq.populardogs.jpa.Perro;
import java.util.List;
import javax.persistence.EntityManager;

public class PerroFachada extends ServiceImpl<Perro>{
    public PerroFachada() throws ConexionException {
       super(Perro.class);
        try{
             EntityManager em = SingletonConnection.getConnection();
            super.setEntityManager(em);
        }catch(Exception e){
            e.printStackTrace();
           throw new ConexionException("Problemas en la realizacion de conexion con la base de datos");
        }
    }
    
    @Override
    public List<Perro> findAll() {
        return super.findAll();
    }
}
