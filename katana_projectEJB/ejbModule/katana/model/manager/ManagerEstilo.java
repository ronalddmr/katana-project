package katana.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import katana.model.entities.ProEstilo;



/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerEstilo {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerEstilo() {
        // TODO Auto-generated constructor stub
    }
    
    /**CRUD DE LA TALBLA pro_estilo*/
    public List<ProEstilo> findAllEstilo(){
    	String consulta="SELECT p FROM ProEstilo p";
    	Query q=em.createQuery(consulta, ProEstilo.class);
    	return q.getResultList();
    }
    
    public ProEstilo findEstiloById(int id) {
    	return em.find(ProEstilo.class, id);
    }
    public void insertarEstilo(ProEstilo estilo) throws Exception {
    	ProEstilo save_estilo=new ProEstilo();
    	save_estilo.setNombre(estilo.getNombre());
        em.persist(save_estilo);
    }
    public void eliminarEstilo(int id) {
    	ProEstilo estilo=findEstiloById(id);
    	if(estilo!=null)
    		em.remove(estilo);
    }
    public void actualizarEstilo(ProEstilo estilo) throws Exception {
    	ProEstilo e=findEstiloById(estilo.getIdEstilo());
    	if(e==null)
    		throw new Exception("No existe este color");
    	e.setNombre(estilo.getNombre());
    	em.merge(e);
    	
    }

    
    
    
}
