package katana.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.ProColor;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerTalla {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerTalla() {
        // TODO Auto-generated constructor stub
    }
    

    
    /**CRUD DE LA TALBLA pro_talla*/
    public List<ProTalla> findAllTalla(){
    	String consulta="SELECT p FROM ProTalla p";
    	Query q=em.createQuery(consulta, ProTalla.class);
    	return q.getResultList();
    }
    
    public ProTalla findTallaById(int id) {
    	return em.find(ProTalla.class, id);
    }
    public void insertarTalla(ProTalla talla) throws Exception {
    	ProTalla aux=new ProTalla();
    	aux.setDescripcion(talla.getDescripcion());
        em.persist(aux);
    }
    public void eliminarTalla(int id) {
    	ProTalla talla=findTallaById(id);
    	if(talla!=null)
    		em.remove(talla);
    }
    public void actualizarTalla(ProTalla talla) throws Exception {
    	ProTalla e=findTallaById(talla.getIdTalla());
    	if(e==null)
    		throw new Exception("No existe esta talla");
    	e.setDescripcion(talla.getDescripcion());
    	em.merge(e);
    	
    }

}
