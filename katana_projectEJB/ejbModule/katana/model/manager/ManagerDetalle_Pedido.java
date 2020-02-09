package katana.model.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.PedDetallePedido;
import katana.model.entities.PedIva;
import katana.model.entities.PedPedido;
import katana.model.entities.ProCatalogo;
import katana.model.entities.ProColor;
import katana.model.entities.ProProducto;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerDetalle_Pedido {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerDetalle_Pedido() {
        // TODO Auto-generated constructor stub
    }
    
    
    /**CRUD DE LA TALBLA ped_detalle_pedido*/
    public List<PedDetallePedido> findAllDetallePedido(){
    	String consulta="SELECT p FROM PedDetallePedido p";
    	Query q=em.createQuery(consulta, PedDetallePedido.class);
    	return q.getResultList();
    }
    
    public PedDetallePedido findDetallePedidoById(int id) {
    	return em.find(PedDetallePedido.class, id);
    }
    
    public List<PedDetallePedido> findDetallePedidoByIdPedido(int id) {
    	List<PedDetallePedido> lista_detalle=this.findAllDetallePedido();
    	List<PedDetallePedido> lista_detalle_pedido=new ArrayList<>();
    	PedDetallePedido detalle=new PedDetallePedido();
    	for(int i=0; i<lista_detalle.size();i++) 
    	{
    		detalle=lista_detalle.get(i);
    		if(detalle.getPedPedido().getIdPedido()==id) 
    		{
    			lista_detalle_pedido.add(lista_detalle.get(i));
    		}
    	}
    	return lista_detalle;
    }
    
    public PedDetallePedido findDetaleByUltimoDetalle() {
    	List<PedDetallePedido> lista_detalle=this.findAllDetallePedido();
    	int cont=0;
    		for (PedDetallePedido p: lista_detalle) 
        	{
        		if(p.getIdDetalle()>cont) 
        		{
        			cont=p.getIdDetalle();
        		}
        	} 
    		
    	return em.find(PedDetallePedido.class, cont);
    }
    
	/*
	 * public void insertarDetallePedido(PedDetallePedido detalle, PedPedido pedido,
	 * PedIva iva, ProCatalogo catalogo) throws Exception { PedDetallePedido
	 * save_detalle=new PedDetallePedido();
	 * save_detalle.setCantidadDetalle(detalle.getCantidadDetalle());
	 * save_detalle.setPedIva(iva); save_detalle.setValorIva(detalle.getValorIva());
	 * save_detalle.setPedPedido(pedido);
	 * save_detalle.setPrecioDescuento(detalle.getPrecioDescuento());
	 * save_detalle.setPrecioProducto(detalle.getPrecioDescuento());
	 * save_detalle.setProCatalogo(catalogo);
	 * save_detalle.setSubtotal(detalle.getSubtotal());
	 * save_detalle.setTotalDetalle(detalle.getTotalDetalle());
	 * em.persist(save_detalle); }
	 */
    
    public void insertarDetalle(PedDetallePedido detalle, PedIva iva, ProCatalogo catalogo) throws Exception {
    	PedDetallePedido save_detalle=new PedDetallePedido();
    	save_detalle.setCantidadDetalle(detalle.getCantidadDetalle());
    	save_detalle.setPedIva(iva);
    	save_detalle.setValorIva(detalle.getValorIva());
    	save_detalle.setPrecioDescuento(detalle.getPrecioDescuento());
    	save_detalle.setPrecioProducto(detalle.getPrecioDescuento());
    	save_detalle.setProCatalogo(catalogo);
    	save_detalle.setSubtotal(detalle.getSubtotal());
    	save_detalle.setTotalDetalle(detalle.getTotalDetalle());
        em.persist(save_detalle);
    }
    public void eliminarDetallePedido(int id) {
    	PedDetallePedido detalle=findDetallePedidoById(id);
    	if(detalle!=null)
    		em.remove(detalle);
    }
    public void actualizarDetalle(PedDetallePedido detalle) throws Exception {
    	PedDetallePedido e=findDetallePedidoById(detalle.getIdDetalle());
    	if(e==null)
    		throw new Exception("No existe este detalle");
    	e.setCantidadDetalle(detalle.getCantidadDetalle());
    	e.setValorIva(detalle.getValorIva());
    	e.setPrecioDescuento(detalle.getPrecioDescuento());
    	e.setPrecioProducto(detalle.getPrecioDescuento());
    	e.setSubtotal(detalle.getSubtotal());
    	e.setTotalDetalle(detalle.getTotalDetalle());
    	em.merge(e);
    	
    }
    public boolean actualizarDetalleCAntidad(List<PedDetallePedido> listadetalle,List<PedDetallePedido> listadetalle2, BigDecimal cantidad, ProCatalogo catalogo) throws Exception {
    	for(int i=0; i<listadetalle.size() ;i++) 
        {
    		
	            if(catalogo.getIdCatalogo()==listadetalle.get(i).getProCatalogo().getIdCatalogo()) 
	            {
	            	
	            	BigDecimal Valor1 = listadetalle.get(i).getCantidadDetalle();
	            	BigDecimal Valor2 = cantidad;
	            	System.out.println("Valor1 "+Valor1);
	            	System.out.println("Valor2 "+Valor2);
	            	Valor2 = Valor2.add(Valor1);
	            	System.out.println("Valor Total "+Valor2);
	            	listadetalle.get(i).setCantidadDetalle(Valor2);
	            	System.out.println("Lo que tiene la lista "+listadetalle.get(i).getCantidadDetalle());
	            	listadetalle2=listadetalle;
	            	em.merge(listadetalle.get(i));
	            	return false;
	            }
        }
    	return true;
    	
    }
    public void poneridPedido_detalle(PedDetallePedido detalle, PedPedido pedido) throws Exception
    {
    	PedDetallePedido e=findDetallePedidoById(detalle.getIdDetalle());
    	if(e==null)
    		throw new Exception("No existe este detalle");
    	e.setPedPedido(pedido);
    	em.merge(e);
    	
    }
}
