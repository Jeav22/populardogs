package co.edu.usa.ingereq.populardogs.facade;

import co.edu.usa.ingereq.populardogs.dao.ServiceImpl;
import co.edu.usa.ingereq.populardogs.dao.SingletonConnection;
import co.edu.usa.ingereq.populardogs.exception.ConexionException;
import co.edu.usa.ingereq.populardogs.jpa.Comentario;
import java.util.List;
import javax.persistence.EntityManager;

public class ComentarioFachada extends ServiceImpl<Comentario> {

    public ComentarioFachada() throws ConexionException {
       super(Comentario.class);
        try{
             EntityManager em = SingletonConnection.getConnection();
            super.setEntityManager(em);
        }catch(Exception e){
            e.printStackTrace();
           throw new ConexionException("Problemas en la realizacion de conexion con la base de datos");
        }
    }
    
    @Override
    public List<Comentario> findAll() {
        return super.findAll();
    }
}
