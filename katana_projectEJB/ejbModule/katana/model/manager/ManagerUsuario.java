package katana.model.manager;


import katana.model.entities.UsuUsuarioRol;
import katana.model.entities.ProTalla;
import katana.model.entities.UsuRol;
import katana.model.entities.UsuUsuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



/**
 * Session Bean implementation class ManagerUsuario
 */
@Stateless
@LocalBean
public class ManagerUsuario {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerUsuario() {
        
    }
    
    /**CRUD DE LA TABLA usu_usuario*/
    public List<UsuUsuario> findAllUsuarios(){
    	String consulta="select o from UsuUsuario o";
    	Query q=em.createQuery(consulta, UsuUsuario.class);
    	return q.getResultList();
    }
    
    public UsuUsuario findUsuarioById(int id) {
    	return em.find(UsuUsuario.class, id);
    }
    
    public UsuUsuario findUsuarioByMail(String correo) {
    	UsuUsuario usu = new UsuUsuario();
    	List<UsuUsuario> listaUsu = this.findAllUsuarios();
    	
    	for (int i = 0; i < listaUsu.size(); i++) {
			if (listaUsu.get(i).getCorreo().equals(correo)) {
				usu = listaUsu.get(i);
				break;
			}
		}
    	return usu;
    }
    
    public int findIdUsuarioMayor() 
    {
    	int cont=0;
    	List <UsuUsuario>lista;
    	lista=findAllUsuarios();
    	for (int i = 0; i < lista.size(); i++) {
			if(cont<lista.get(i).getIdUsuario()) 
			{
				cont=lista.get(i).getIdUsuario();
			}
		}
    	
    	return cont;
    }
    
    public void insertarUsuario(UsuUsuario usuario) throws Exception {
    	UsuUsuario aux = new UsuUsuario();
    	aux.setNombre(usuario.getNombre());
    	aux.setApellido(usuario.getApellido());
    	aux.setCorreo(usuario.getCorreo());
    	aux.setPassword(usuario.getPassword());
        em.persist(aux);
    }
    public void eliminarUsuario(int id) {
    	UsuUsuario usuario=findUsuarioById(id);
    	if(usuario!=null)
    		em.remove(usuario);
    }
    public void actualizarUsuario(UsuUsuario usuario) throws Exception {
    	UsuUsuario e=findUsuarioById(usuario.getIdUsuario());
    	e.setApellido(usuario.getApellido());
    	e.setNombre(usuario.getNombre());
    	e.setCorreo(usuario.getCorreo());
    	e.setPassword(usuario.getPassword());
    	em.merge(e);
    }
    
    
    /**CRUD DE LA TABLA usu_usuario_rol*/
    public List<UsuUsuarioRol> findAllUsuariosRol(){
    	String consulta="select u from UsuUsuarioRol u";
    	Query q=em.createQuery(consulta, UsuUsuarioRol.class);
    	return q.getResultList();
    }
    
    public UsuUsuarioRol findUsuarioRolById(int id) {
    	return em.find(UsuUsuarioRol.class, id);
    }
    public void insertarUsuarioRol(UsuRol Rol, UsuUsuario usuario) throws Exception {
    	UsuUsuarioRol aux = new UsuUsuarioRol();
    	aux.setUsuUsuario(usuario);
    	aux.setUsuRol(Rol);
        em.persist(aux);
    }
    public void eliminarUsuarioRol(int id) {
    	UsuUsuarioRol usuario=findUsuarioRolById(id);
    	if(usuario!=null)
    		em.remove(usuario);
    }
    public void actualizarUsuarioRolSeleccionado(UsuUsuarioRol usuarioRol) throws Exception {
    	UsuUsuarioRol e=findUsuarioRolById(usuarioRol.getIdUsuarioRol());
    	//e.setUsuUsuario(usuarioRol.getUsuUsuario()); no modifique el usuario solo el rol
    	e.setUsuRol(usuarioRol.getUsuRol());
    	em.merge(e);
    }
    
}
