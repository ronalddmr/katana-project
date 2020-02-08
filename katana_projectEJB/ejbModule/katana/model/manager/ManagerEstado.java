package katana.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.PedEstado;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerEstado {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerEstado() {
        // TODO Auto-generated constructor stub
    }
    

    
    /**CRUD DE LA TALBLA pro_talla*/
    public List<PedEstado> findAllEstado(){
    	String consulta="SELECT p FROM PedEstado p";
    	Query q=em.createQuery(consulta, PedEstado.class);
    	return q.getResultList();
    }
    
    public PedEstado findEstadoById(int id) {
    	return em.find(PedEstado.class, id);
    }
    public void insertarEstado(PedEstado estado) throws Exception {
    	PedEstado aux=new PedEstado();
    	aux.setNombre(estado.getNombre());
    	aux.setDescripcion(estado.getDescripcion());
        em.persist(aux);
    }
    public void eliminarEstado(int id) {
    	PedEstado estado=findEstadoById(id);
    	if(estado!=null)
    		em.remove(estado);
    }
    public void actualizarEstado(PedEstado estado) throws Exception {
    	PedEstado e=findEstadoById(estado.getIdEstado());
    	if(e==null)
    		throw new Exception("No existe este estado");
    	e.setNombre(estado.getNombre());
    	e.setDescripcion(estado.getDescripcion());
    	em.merge(e);
    	
    }

}
