package katana.model.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.ProColor;
import katana.model.entities.ProProducto;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerProducto {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerProducto() {
        // TODO Auto-generated constructor stub
    }
    
    /**CRUD DE LA TALBLA pro_producto*/
    public List<ProProducto> findAllProducto(){
    	String consulta="SELECT p FROM ProProducto p";
    	Query q=em.createQuery(consulta, ProProducto.class);
    	return q.getResultList();
    }
    public ProProducto findProductoById(int id) {
    	return em.find(ProProducto.class, id);
    }
    
    public void insertarProducto(ProProducto producto) throws Exception {
        ProProducto p=new ProProducto();
        p.setNombre(producto.getNombre()); //si es personalizada el nomnres es "personalizada"
        p.setProTalla(producto.getProTalla());
        p.setProColor(producto.getProColor());
        p.setImagen1(producto.getImagen1());//solo esta es not null
        p.setImagen2(producto.getImagen2());
        p.setImagen3(producto.getImagen3());
        p.setProTipoProducto(producto.getProTipoProducto());        
        p.setPersonalizado(true); // siempre pasarle como personalizada pero el admin debe cambiar a false
        p.setProEstilo(producto.getProEstilo());
        p.setDescripcion(producto.getDescripcion()); // no hace falta
        p.setProCamiseta(producto.getProCamiseta());
        p.setProLogo(producto.getProLogo()); 
        p.setUsuUsuario(producto.getUsuUsuario());
        
        double precioCamiseta = 5; //precio real de la camiseta
        double precioLogo = 9; // ya implica lo que es la mano de obra, materiales consumidos, etc
        
        /*la base de camiseta personalizada a 1 color es $13
        EL envio es otra cosa
        
        De la tabla de pro_producto debe relacionarse a ped_detalle_pedido 
        ya que cuando yo personalizo una camiseta este producto debe pertenecer a un pedido y la manera mas correcta
        es haciendolo mediante la tabla de ped_detalle_pedido, es como un producto que esta en el catalogo solo que se salta del 
        catalogo y va directo al detalle antes de llegar al pedido
       
       
        		
        double precioCamiseta = Double.parseDouble(p.getProCamiseta().getPrecio().toString());
        double precioLogo = Double.parseDouble(p.getProLogo().getPrecio().toString()); 
        
        Segun yo estos dos campos se manadan por defecto ya que solo el usuario final crea con esos campos
        el admi cuando inserta un producto no pone estos dos campos, se van vacios*/
        
        p.setPrecioPersonalizado(BigDecimal.valueOf(precioCamiseta+precioLogo)); //aqui se suma la camiseta + el logo
    	em.persist(p);
    }
    public void eliminarProducto(int id) {
    	ProProducto producto=findProductoById(id);
    	if(producto!=null)
    		em.remove(producto);
    }
    public void actualizarProducto(ProProducto producto) throws Exception {
    	ProProducto p=findProductoById(producto.getIdProducto());
    	if(p==null)
    		throw new Exception("No existe ese producto");
        p.setNombre(producto.getNombre()); //si es personalizada el nomnres es "personalizada"
        p.setProTalla(producto.getProTalla());
        p.setProColor(producto.getProColor());
        p.setImagen1(producto.getImagen1());//solo esta es not null
        p.setImagen2(producto.getImagen2());
        p.setImagen3(producto.getImagen3());
        p.setProTipoProducto(producto.getProTipoProducto());        
        p.setPersonalizado(producto.getPersonalizado()); // siempre pasarle como personalizada pero el admin debe cambiar a false
        p.setProEstilo(producto.getProEstilo());
        p.setDescripcion(producto.getDescripcion()); // no hace falta
        p.setProCamiseta(producto.getProCamiseta());
        p.setProLogo(producto.getProLogo()); 
        p.setUsuUsuario(producto.getUsuUsuario());//quien actualiza el producto
       
        double precioCamiseta = Double.parseDouble(p.getProCamiseta().getPrecio().toString());
        double precioLogo = Double.parseDouble(p.getProLogo().getPrecio().toString());
        
        p.setPrecioPersonalizado(BigDecimal.valueOf(precioCamiseta+precioLogo)); //aqui se suma la camiseta + el logo
    	em.persist(p);
    	em.merge(p);
    	
    }
    
    /**CRUD DE LA TALBLA pro_tipo_producto*/
    public List<ProTipoProducto> findAllTipoProducto(){
    	String consulta="SELECT p FROM ProTipoProducto p";
    	Query q=em.createQuery(consulta, ProTipoProducto.class);
    	return q.getResultList();
    }
    
    public ProTipoProducto findTipoProductoById(int id) {
    	return em.find(ProTipoProducto.class, id);
    }
    public void insertarTipoProducto(ProTipoProducto tipo_producto) throws Exception {
        ProTipoProducto tipo=new ProTipoProducto();
        tipo.setNombre(tipo_producto.getNombre());
    	em.persist(tipo);
    }
    public void eliminarTipoProducto(int id) {
    	ProTipoProducto tipo_producto=findTipoProductoById(id);
    	if(tipo_producto!=null)
    		em.remove(tipo_producto);
    }
    public void actualizarTipoProducto(ProTipoProducto tipo_producto) throws Exception {
    	ProTipoProducto e=findTipoProductoById(tipo_producto.getIdTipoProducto());
    	if(e==null)
    		throw new Exception("No existe ese tipo de producto");
    	e.setNombre(tipo_producto.getNombre());
    	em.merge(e);
    	
    }
    
    
    
}
