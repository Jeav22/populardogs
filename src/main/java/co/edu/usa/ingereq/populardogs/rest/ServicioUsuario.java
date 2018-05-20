package co.edu.usa.ingereq.populardogs.rest;

import co.edu.usa.ingereq.populardogs.facade.UsuarioFachada;
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

@Path("/usuarios")
public class ServicioUsuario {

    UsuarioFachada UsuarioFachada;

    public ServicioUsuario() throws ConexionException {
        UsuarioFachada = new UsuarioFachada();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<UsuarioDto> getUsuarios_JSON() {
        List<Usuario> listaUsuarios = UsuarioFachada.findAll();
        List<UsuarioDto> UsuarioDtos = new ArrayList<UsuarioDto>();
        for (Usuario listaUsuario : listaUsuarios) {
            UsuarioDtos.add(usuarioToDto(listaUsuario));
        }
        return UsuarioDtos;
    }

    @GET
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public UsuarioDto getUsuario(@PathParam("usrNo") int usrNo) {
        return usuarioToDto(UsuarioFachada.get(usrNo));
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public boolean crearUsuario(Usuario usr) {
        try {
            UsuarioFachada.save(usr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Usuario updateUsuario(Usuario usr) {
        return UsuarioFachada.update(usr);
    }

    @DELETE
    @Path("/{usrNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteUsuario(@PathParam("usrNo") int usrNo) {
        Usuario u = UsuarioFachada.get(usrNo);
        UsuarioFachada.delete(u);
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
