package katana.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.ProColor;
import katana.model.entities.ProEstilo;
import katana.model.entities.ProProducto;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerProducto_katana {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerProducto_katana() {
        // TODO Auto-generated constructor stub
    }
    
    /**CRUD DE LA TALBLA producto*/
    public List<ProProducto> findAllProducto(){
    	String consulta="SELECT p FROM ProProducto p";
    	Query q=em.createQuery(consulta, ProProducto.class);
    	return q.getResultList();
    }
    
    public ProProducto findProductoById(int id) {
    	return em.find(ProProducto.class, id);
    }
    
    public ProProducto findProductoByUltimoProducto() {
    	List<ProProducto> lista_producto=this.findAllProducto();
    	int cont=0;
    		for (ProProducto p: lista_producto) 
        	{
        		if(p.getIdProducto()>cont) 
        		{
        			cont=p.getIdProducto();
        		}
        	} 
    		
    	return em.find(ProProducto.class, cont);
    }
    public void insertarProducto(ProProducto producto, ProTalla talla, ProColor color, ProTipoProducto tipo_producto,
    		ProEstilo estilo) throws Exception {
        ProProducto tipo=new ProProducto();
        tipo.setNombre(producto.getNombre());
        tipo.setProTalla(talla);
        tipo.setProColor(color);
        tipo.setImagen1(producto.getImagen1());
        tipo.setImagen2(producto.getImagen2());
        tipo.setImagen3(producto.getImagen3());
        tipo.setPersonalizado(false);
        tipo.setProTipoProducto(tipo_producto);
        tipo.setProEstilo(estilo);
        tipo.setDescripcion(producto.getDescripcion());
    	em.persist(tipo);
    }
    public void eliminarProducto(int id) {
    	ProProducto producto=findProductoById(id);
    	if(producto!=null)
    		em.remove(producto);
    }
    public void actualizarProducto(ProProducto producto) throws Exception {
    	ProProducto tipo=findProductoById(producto.getIdProducto());
    	if(tipo==null)
    		throw new Exception("No existe ese producto");
    	 tipo.setNombre(producto.getNombre());
    	 tipo.setProTalla(producto.getProTalla());
         tipo.setProColor(producto.getProColor());
         tipo.setImagen1(producto.getImagen1());
         tipo.setImagen2(producto.getImagen2());
         tipo.setImagen3(producto.getImagen3());
         tipo.setProTipoProducto(producto.getProTipoProducto());
         tipo.setProEstilo(producto.getProEstilo());
         tipo.setDescripcion(producto.getDescripcion());
    	em.merge(tipo);
    	
    }
    
    
    
}
