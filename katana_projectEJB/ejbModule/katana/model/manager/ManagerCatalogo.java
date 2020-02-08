package katana.model.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.ProCatalogo;
import katana.model.entities.ProProducto;



/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerCatalogo {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerCatalogo() {
        // TODO Auto-generated constructor stub
    }
    
    /**CRUD DE LA TALBLA catálogo*/
    public List<ProCatalogo> findAllCatalogo(){
    	String consulta="SELECT p FROM ProCatalogo p";
    	Query q=em.createQuery(consulta, ProCatalogo.class);
    	return q.getResultList();
    }
    
    public ProCatalogo findCatalogoById(int id) {
    	return em.find(ProCatalogo.class, id);
    }
    
    public ProCatalogo findCatalogoByIdProducto(int id) {
    	
    	List<ProCatalogo> lista_catalogo=this.findAllCatalogo();
    	ProCatalogo a= new ProCatalogo();
    	for(int i=0; i<lista_catalogo.size(); i++  ) 
    	{
    		if(lista_catalogo.get(i).getProProducto().getIdProducto()==id) 
    		{
    			a=lista_catalogo.get(i);
    			break;
    		}
    	}
    	return a;
    }
    
    public void insertarCatalogo(ProCatalogo catalogo, ProProducto producto) throws Exception {
    	java.util.Date fecha = new Date();
        ProCatalogo cata=new ProCatalogo();
        cata.setCantidad(1);
        cata.setCantidadVendida(0);
        cata.setDescuento(BigDecimal.valueOf(0.0));
        cata.setFecha(fecha);
        cata.setPrecio(BigDecimal.valueOf(10));
        cata.setStockMinimo(1);
        cata.setStockMaximo(20);
        cata.setMostrar(false);
        cata.setProProducto(producto);
    	em.persist(cata);
    }

	
	  public void eliminarCatalogo(int id) 
	  { ProCatalogo catalogo=findCatalogoById(id); 
	  if(catalogo!=null) 
		  em.remove(catalogo); 
	  }
	 
    public void actualizarCatalogo(ProCatalogo catalogo) throws Exception {
    	java.util.Date fecha = new Date();
    	ProCatalogo cata=findCatalogoById(catalogo.getIdCatalogo());
    	if(cata==null)
    		throw new Exception("No existe ese catálogo");
    	cata.setCantidad(catalogo.getCantidad());
        cata.setCantidadVendida(catalogo.getCantidadVendida());
        cata.setDescuento(catalogo.getDescuento());
        cata.setFecha(fecha);
        cata.setPrecio(catalogo.getPrecio());
        cata.setStockMinimo(catalogo.getStockMinimo());
        cata.setStockMaximo(catalogo.getStockMaximo());
        cata.setMostrar(catalogo.getMostrar());
    	em.merge(cata);
    	
    }
    
    
    
}
