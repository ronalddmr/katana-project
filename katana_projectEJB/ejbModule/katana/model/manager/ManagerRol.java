package katana.model.manager;

import katana.model.entities.UsuRol;
import katana.model.entities.UsuUsuarioRol;

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
public class ManagerRol {
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerRol() {
        
    }
    
    /**CRUD DE LA TABLA usu_rol*/
    public List<UsuRol> findAllROl(){
    	String consulta="select o from UsuRol o";
    	Query q=em.createQuery(consulta, UsuRol.class);
    	return q.getResultList();
    }
    
    public UsuRol findRolById(int id) {
    	return em.find(UsuRol.class, id);
    }
    
    public void insertarRol(UsuRol rol) throws Exception {
    	UsuRol save_rol=new UsuRol();
        save_rol.setNombre(rol.getNombre());
        save_rol.setDescripcion(rol.getDescripcion());
        em.persist(save_rol);
    }
    public void eliminarRol(int id) {
    	UsuRol rol=findRolById(id);
    	if(rol!=null)
    		em.remove(rol);
    }
    public void actualizarRol(UsuRol rol) throws Exception {
    	UsuRol e=findRolById(rol.getIdRol());
    	if(e==null)
    		throw new Exception("No existe el rol especificado");
    	e.setNombre(rol.getNombre());
    	e.setDescripcion(rol.getDescripcion());
    	em.merge(e);
    	
    }
    
    public UsuRol findRolByUser(UsuUsuarioRol usuarioRol) {
    	UsuRol rol = new UsuRol();
    	List<UsuRol> listaRoles = this.findAllROl();
    	
    	for (int i = 0; i < listaRoles.size(); i++) {
			if (listaRoles.get(i).getIdRol() == usuarioRol.getIdUsuarioRol()) { //compara el usuarioRol del Usuario.getUsuarioRol con el rol de la lista
				rol = listaRoles.get(i);
				break;
			}
		}
    	return rol;
    }

}
