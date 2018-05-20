package co.edu.usa.ingereq.populardogs.rest;

import co.edu.usa.ingereq.populardogs.facade.ComentarioFachada;
import co.edu.usa.ingereq.populardogs.jpa.Comentario;
import co.edu.usa.ingereq.populardogs.dto.ComentarioDto;
import co.edu.usa.ingereq.populardogs.dto.UsuarioDto;
import co.edu.usa.ingereq.populardogs.exception.ConexionException;
import co.edu.usa.ingereq.populardogs.jpa.Usuario;
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

@Path("/comentarios")
public class ServicioComentario {

    ComentarioFachada ComentarioFachada;

    public ServicioComentario() throws ConexionException {
        ComentarioFachada = new ComentarioFachada();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<ComentarioDto> getComentarios_JSON() {
        List<Comentario> listaComentarios = ComentarioFachada.findAll();
        List<ComentarioDto> ComentarioDtos = new ArrayList<ComentarioDto>();
        for (Comentario listaComentario : listaComentarios) {
            ComentarioDtos.add(comentarioToDto(listaComentario));
        }
        return ComentarioDtos;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ComentarioDto getComentario(@PathParam("usrNo") int usrNo) {
        return comentarioToDto(ComentarioFachada.get(usrNo));
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearComentario(Comentario usr) {
        try {
            ComentarioFachada.save(usr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Comentario updateComentario(Comentario usr) {
        return ComentarioFachada.update(usr);
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteComentario(@PathParam("usrNo") int usrNo) {
        Comentario u = ComentarioFachada.get(usrNo);
        ComentarioFachada.delete(u);
    }

    public ComentarioDto comentarioToDto(Comentario Comentario) {
        ComentarioDto ComentarioDto = new ComentarioDto();
        ComentarioDto.setUsuario(usuarioToDto(Comentario.getUsuario()));
        ComentarioDto.setId(Comentario.getId());
        ComentarioDto.setImagen(Comentario.getImagen());
        ComentarioDto.setFecha(Comentario.getFecha());
        ComentarioDto.setComentario(Comentario.getComentario());
        return ComentarioDto;
    }

    public UsuarioDto usuarioToDto(Usuario u) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNombre(u.getNombre());
        usuarioDto.setCorreo(u.getCorreo());
        usuarioDto.setImagen(u.getImagen());
        usuarioDto.setId(u.getId());
        return usuarioDto;
    }
}
