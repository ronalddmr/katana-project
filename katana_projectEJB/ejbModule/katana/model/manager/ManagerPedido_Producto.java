package katana.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.PedDetallePedido;
import katana.model.entities.PedDivpolitica;
import katana.model.entities.PedEstado;
import katana.model.entities.PedIva;
import katana.model.entities.PedPedido;
import katana.model.entities.UsuUsuario;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerPedido_Producto {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerPedido_Producto() {
        // TODO Auto-generated constructor stub
    }
    
    /**CRUD DE LA TALBLA ped_pedido*/
    public List<PedPedido> findAllPedido(){
    	String consulta="SELECT p FROM PedPedido p";
    	Query q=em.createQuery(consulta, PedPedido.class);
    	return q.getResultList();
    }
    
    public PedPedido findPedidoById(int id) {
    	return em.find(PedPedido.class, id);
    }
    public void insertarPedido(PedPedido pedido, List<PedDetallePedido> lista_detalle, PedDivpolitica ciudad,
    		PedEstado estado, UsuUsuario usuario) throws Exception {
    	java.util.Date fecha = new Date();
        PedPedido ped=new PedPedido();
        ped.setBaseCero(pedido.getBaseCero());
        ped.setBaseImponible(pedido.getBaseImponible());
        ped.setCostoEnvio(pedido.getCostoEnvio());
        ped.setDireccion(pedido.getDireccion());
        ped.setFechaEntrega(ped.getFechaEntrega());
        ped.setFechaPedido(fecha);
        ped.setFormaPago(pedido.getFormaPago());
        ped.setIdentificacion(pedido.getIdentificacion());
        ped.setIvaTotal(pedido.getIvaTotal());
        ped.setPedDetallePedidos(lista_detalle);
        ped.setPedDivpolitica(ciudad);
        ped.setPedEstado(estado);
        ped.setReferencia(pedido.getReferencia());
        ped.setSubtotal(pedido.getSubtotal());
        ped.setTelefono(pedido.getTelefono());
        ped.setTotal(pedido.getTotal());
        ped.setUsuUsuario(usuario);    
    	em.persist(ped);
    }
    public void eliminarPedido(int id) {
    	PedPedido pedido=findPedidoById(id);
    	if(pedido!=null)
    		em.remove(pedido);
    }
    public void actualizarPedido(PedPedido pedido, PedEstado estado) throws Exception {
    	PedPedido e=findPedidoById(pedido.getIdPedido());
    	if(e==null)
    		throw new Exception("No existe ese Pedido");
    	PedPedido ped=new PedPedido();
        ped.setPedEstado(estado);
    	em.merge(e);
    	
    }

    
    
    
}
