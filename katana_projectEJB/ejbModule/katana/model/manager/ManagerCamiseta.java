package katana.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.ProCamiseta;
import katana.model.entities.ProEstilo;
import katana.model.entities.ProLogo;



/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerCamiseta {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerCamiseta() {
        // TODO Auto-generated constructor stub
    }
    
    /**CRUD DE LA TABLA pro_camiseta*/
    public List<ProCamiseta> findAllCamiseta(){
    	String consulta="SELECT p FROM ProCamiseta p";
    	Query q=em.createQuery(consulta, ProCamiseta.class);
    	return q.getResultList();
    }
    
    public ProCamiseta findCamisetaById(int id) {
    	return em.find(ProCamiseta.class, id);
    }
    public void insertarCamiseta(ProCamiseta camiseta) throws Exception {
    	ProCamiseta save_camiseta=new ProCamiseta();
    	save_camiseta.setPrecio(camiseta.getPrecio());
    	save_camiseta.setImagen(camiseta.getImagen());
    	save_camiseta.setImagen2(camiseta.getImagen2());
    	save_camiseta.setImagen3(camiseta.getImagen3());
        em.persist(save_camiseta);
    }
    public void eliminarCamiseta(int id) {
    	ProCamiseta camiseta=findCamisetaById(id);
    	if(camiseta!=null)
    		em.remove(camiseta);
    }
    public void actualizarCamiseta(ProCamiseta camiseta) throws Exception {
    	ProCamiseta c=findCamisetaById(camiseta.getIdCamiseta());
    	if(c==null)
    		throw new Exception("No existe esta camiseta");
    	c.setPrecio(camiseta.getPrecio());
    	c.setImagen(camiseta.getImagen());
    	c.setImagen2(camiseta.getImagen2());
    	c.setImagen3(camiseta.getImagen3());
    	em.merge(c);
    	
    }

    
    
    
}
