package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import katana.controller.JSFUtil;
import katana.model.entities.PedDetallePedido;
import katana.model.entities.PedIva;
import katana.model.entities.PedPedido;
import katana.model.entities.ProCatalogo;
import katana.model.entities.ProColor;
import katana.model.manager.ManagerColor;
import katana.model.manager.ManagerDetalle_Pedido;
import katana.model.manager.ManagerPedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanDetalle_Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerDetalle_Pedido manager_detalle;
	@EJB
	private ManagerPedido manager_iva;
	
	private List<PedDetallePedido> listadetalle_completa;
	private List<PedDetallePedido> listadetalle=new ArrayList<>();
	private PedDetallePedido detalle;
	private PedPedido pedido;
	private PedIva Iva;
	private PedDetallePedido detalleSeleccionado;
	private ProCatalogo catalogo;
	private int cantidad_carrito=0;
	private double valor_compra=0;

	@PostConstruct
	public void inicializar() 
	{
	    detalle=new PedDetallePedido();
	    pedido=new PedPedido();
	    Iva=new PedIva();
	    catalogo=new ProCatalogo();
	}
	
	
	
/*BEAN PARA ped_detalle_pedido*/
	

	public void actionListenerInsertarDetalle(ProCatalogo catalogoSeleccionado) {
		try {
			catalogo=catalogoSeleccionado;
			Iva=manager_iva.findIvaById(1);
			detalle.setSubtotal(new BigDecimal(this.subtotalDetalle(detalle.getCantidadDetalle().intValue(), catalogo.getPrecio().doubleValue())));
			detalle.setPrecioProducto(catalogo.getPrecio());
			detalle.setPrecioDescuento(catalogo.getDescuento());
			detalle.setValorIva(new BigDecimal(detalle.getSubtotal().doubleValue()*Iva.getCantidad().doubleValue()));
			detalle.setTotalDetalle(new BigDecimal(detalle.getSubtotal().doubleValue()-catalogo.getDescuento().doubleValue()+detalle.getValorIva().doubleValue()));
			manager_detalle.insertarDetalle(detalle,Iva, catalogo);
			detalle=manager_detalle.findDetaleByUltimoDetalle();
			listadetalle.add(detalle);
			this.valorcompra();
			cantidad_carrito=listadetalle.size();
			/*
			 * for(int i=0; i<listadetalle.size() ;i++) { System.out.
			 * println("--------------------------------------->>>>Este es el ingresado "
			 * +listadetalle.get(i).getIdDetalle()); }
			 */
			
			JSFUtil.crearMensajeInfo("Se ha añadido al carrito");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarDetalle(int id) 
	{ 
		manager_detalle.eliminarDetallePedido(id);
		for(int i=0; i<listadetalle.size() ;i++) 
		{
			if(listadetalle.get(i).getIdDetalle()==id) 
			{
				listadetalle.remove(i);
				this.valorcompra();
				cantidad_carrito=listadetalle.size();
				break;
			}	
		}
	    listadetalle_completa=manager_detalle.findAllDetallePedido();
	    JSFUtil.crearMensajeInfo("Producto eliminado del carrito"); 
	}
	
	
	
	
	public double subtotalDetalle(int cantidad, double precio) 
	{
		return cantidad*precio;
	}
	
	public void valorcompra() 
	{
		valor_compra=0;
		for(int i=0; i<listadetalle.size() ;i++) 
		{
			valor_compra=valor_compra+listadetalle.get(i).getTotalDetalle().doubleValue();
		}
	}
	



	public int getCantidad_carrito() {
		return cantidad_carrito;
	}



	public void setCantidad_carrito(int cantidad_carrito) {
		this.cantidad_carrito = cantidad_carrito;
	}



	public List<PedDetallePedido> getListadetalle_completa() {
		return listadetalle_completa;
	}



	public void setListadetalle_completa(List<PedDetallePedido> listadetalle_completa) {
		this.listadetalle_completa = listadetalle_completa;
	}



	public List<PedDetallePedido> getListadetalle() {
		return listadetalle;
	}



	public void setListadetalle(List<PedDetallePedido> listadetalle) {
		this.listadetalle = listadetalle;
	}



	public PedDetallePedido getDetalle() {
		return detalle;
	}



	public void setDetalle(PedDetallePedido detalle) {
		this.detalle = detalle;
	}



	public PedPedido getPedido() {
		return pedido;
	}



	public void setPedido(PedPedido pedido) {
		this.pedido = pedido;
	}



	public PedIva getIva() {
		return Iva;
	}



	public void setIva(PedIva iva) {
		Iva = iva;
	}



	public PedDetallePedido getDetalleSeleccionado() {
		return detalleSeleccionado;
	}



	public void setDetalleSeleccionado(PedDetallePedido detalleSeleccionado) {
		this.detalleSeleccionado = detalleSeleccionado;
	}



	public ProCatalogo getCatalogo() {
		return catalogo;
	}



	public void setCatalogo(ProCatalogo catalogo) {
		this.catalogo = catalogo;
	}



	public double getValor_compra() {
		return valor_compra;
	}



	public void setValor_compra(double valor_compra) {
		this.valor_compra = valor_compra;
	}
	
	
	/*
	 * public void actionListenerSeleccionarColor(ProColor Color) {
	 * colorSeleccionado=Color; }
	 * 
	 * public void actionListenerActualizarColor() { try {
	 * managerColor.actualizarColor(colorSeleccionado);
	 * listaColor=managerColor.findAllColor();
	 * JSFUtil.crearMensajeInfo("Datos actualizados"); } catch (Exception e) {
	 * listaColor=managerColor.findAllColor();
	 * JSFUtil.crearMensajeError(e.getMessage()); e.printStackTrace(); } }
	 */
	  
	 
	
	


	

}
