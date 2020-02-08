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
public class ManagerColor {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerColor() {
        // TODO Auto-generated constructor stub
    }
    
    
    /**CRUD DE LA TALBLA pro_color*/
    public List<ProColor> findAllColor(){
    	String consulta="select o from ProColor o";
    	Query q=em.createQuery(consulta, ProColor.class);
    	return q.getResultList();
    }
    
    public ProColor findColorById(int id) {
    	return em.find(ProColor.class, id);
    }
    public void insertarColor(ProColor color) throws Exception {
    	ProColor save_color=new ProColor();
    	save_color.setNombre(color.getNombre());
        em.persist(save_color);
    }
    public void eliminarColor(int id) {
    	ProColor color=findColorById(id);
    	if(color!=null)
    		em.remove(color);
    }
    public void actualizarColor(ProColor color) throws Exception {
    	ProColor e=findColorById(color.getIdColor());
    	if(e==null)
    		throw new Exception("No existe este color");
    	e.setNombre(color.getNombre());
    	em.merge(e);
    	
    }
    
    
}
