package katana.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.PedEstado;
import katana.model.entities.PedIva;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerPedido {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerPedido() {
        // TODO Auto-generated constructor stub
    }
    
    /**CRUD DE LA TALBLA pro_tipo_producto*/
    public List<PedIva> findAllIva(){
    	String consulta="SELECT p FROM PedIva p";
    	Query q=em.createQuery(consulta, PedIva.class);
    	return q.getResultList();
    }
    
    public PedIva findIvaById(int id) {
    	return em.find(PedIva.class, id);
    }
    public void insertarIva(PedIva iva) throws Exception {
        PedIva tipo=new PedIva();
        tipo.setCantidad(iva.getCantidad());
        tipo.setFechaInicio(iva.getFechaInicio());
        tipo.setFechaFin(iva.getFechaFin());
        
    	em.persist(tipo);
    }
    public void eliminarIva(int id) {
    	PedIva iva=findIvaById(id);
    	if(iva!=null)
    		em.remove(iva);
    }
    public void actualizarIva(PedIva iva) throws Exception {
    	PedIva e=findIvaById(iva.getIdIva());
    	if(e==null)
    		throw new Exception("No existe ese iva");
    	e.setCantidad(iva.getCantidad());
        e.setFechaInicio(iva.getFechaInicio());
        e.setFechaFin(iva.getFechaFin());
    	em.merge(e);
    	
    }

    
    
    
}
