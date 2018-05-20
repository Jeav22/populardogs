package co.edu.usa.ingereq.populardogs.rest;

import co.edu.usa.ingereq.populardogs.facade.PerroFachada;
import co.edu.usa.ingereq.populardogs.jpa.Perro;
import co.edu.usa.ingereq.populardogs.dto.PerroDto;
import co.edu.usa.ingereq.populardogs.exception.ConexionException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/perros")
public class ServicioPerro {

    PerroFachada PerroFachada;

    public ServicioPerro() throws ConexionException {
        PerroFachada = new PerroFachada();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PerroDto> getPerros_JSON() {
        List<Perro> listaPerros = PerroFachada.findAll();
        List<PerroDto> PerroDtos = new ArrayList<PerroDto>();
        for (Perro listaPerro : listaPerros) {
            PerroDtos.add(perroToDto(listaPerro));
        }
        return PerroDtos;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public PerroDto getPerro(@PathParam("usrNo") int usrNo) {
        return perroToDto(PerroFachada.get(usrNo));
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearPerro(Perro usr) {
        try {
            PerroFachada.save(usr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Perro updatePerro(Perro usr) {
        return PerroFachada.update(usr);
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deletePerro(@PathParam("usrNo") int usrNo) {
        Perro u = PerroFachada.get(usrNo);
        PerroFachada.delete(u);
    }

    public PerroDto perroToDto(Perro Perro) {
        PerroDto PerroDto = new PerroDto();
        PerroDto.setRaza(Perro.getRaza());
        PerroDto.setId(Perro.getId());
        PerroDto.setImagen(Perro.getImagen());
        PerroDto.setDescripcion(Perro.getDescripcion());
        PerroDto.setEnergia(Perro.getEnergia());
        PerroDto.setPersonalidad(Perro.getPersonalidad());
        return PerroDto;
    }
}
