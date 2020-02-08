package katana.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import katana.model.entities.ProEstilo;
import katana.model.entities.ProLogo;



/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerLogo {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerLogo() {
        // TODO Auto-generated constructor stub
    }
    
    /**CRUD DE LA TABLA pro_logo*/
    public List<ProLogo> findAllLogo(){
    	String consulta="SELECT p FROM ProLogo p";
    	Query q=em.createQuery(consulta, ProLogo.class);
    	return q.getResultList();
    }
    
    public ProLogo findLogoById(int id) {
    	return em.find(ProLogo.class, id);
    }
    public void insertarLogo(ProLogo logo) throws Exception {
    	ProLogo save_logo=new ProLogo();
    	save_logo.setImagen(logo.getImagen());
    	save_logo.setPrecio(logo.getPrecio());
        em.persist(save_logo);
    }
    public void eliminarLogo(int id) {
    	ProLogo logo=findLogoById(id);
    	if(logo!=null)
    		em.remove(logo);
    }
    public void actualizarLogo(ProLogo logo) throws Exception {
    	ProLogo l=findLogoById(logo.getIdLogo());
    	if(l==null)
    		throw new Exception("No existe este color");
    	l.setImagen(logo.getImagen());
    	l.setPrecio(logo.getPrecio());
    	em.merge(l);
    	
    }

    
    
    
}
