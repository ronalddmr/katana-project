package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import katana.controller.JSFUtil;
import katana.model.entities.PedDetallePedido;
import katana.model.entities.PedDivpolitica;
import katana.model.entities.PedEstado;
import katana.model.entities.PedPedido;
import katana.model.entities.UsuUsuario;
import katana.model.manager.ManagerDPA;
import katana.model.manager.ManagerDetalle_Pedido;
import katana.model.manager.ManagerEstado;
import katana.model.manager.ManagerPedido_Producto;
import katana.model.manager.ManagerUsuario;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@SessionScoped
public class BeanPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerDPA managerDPA;
	@EJB
	private ManagerPedido_Producto managerPedido;
	@EJB
	private ManagerDetalle_Pedido managerdetalle;
	@EJB
	private ManagerUsuario managerusuario;
	@EJB
	private ManagerEstado managerestado;
	
	private List<PedPedido> listaPedido;
	private List<PedDivpolitica> listaProvincias;
	private List<PedDivpolitica> listaCantones;
	private List<PedDivpolitica> listaCantonesProvincia;
	private List<PedDetallePedido> listadetalle;
	private List<PedEstado> listaestado;
	
	@Inject private BeanDetalle_Producto beandetalle_producto;
	
	private PedPedido pedido;
	private PedPedido pedidoSeleccionado;
	private PedEstado estado;
	private PedDivpolitica locacion;
	private UsuUsuario usuario;
	private String idProvincia;
	private String idCanton;
	
	


	@PostConstruct
	public void inicializar() 
	{
		pedido=new PedPedido();
		usuario=new UsuUsuario();
		estado=new PedEstado();
		locacion = new PedDivpolitica();
		pedidoSeleccionado=new PedPedido();
		pedidoSeleccionado.setPedDivpolitica(locacion);
		
		listaProvincias = managerDPA.findAllProvincias();
		listaCantones = managerDPA.findAllCantones();
		listaPedido = managerPedido.findAllPedido();
		listaestado=managerestado.findAllEstado();

	}
	
	
	
/*Bean para Pedidos*/
	
	public void actionListenerCantones()
	{
		listaCantonesProvincia = managerDPA.findCantonesProvincia(listaCantones, idProvincia);
		
	}
	
	
	
	public void actionListenerInsertarPedido() {
		try {
			
			 listadetalle=beandetalle_producto.getListadetalle();
			  
			 double total=0;
			 double subtotal=0;
			 double ivatotal=0;
			for(int i=0; i<listadetalle.size() ;i++) 
			{
 
				subtotal=subtotal+listadetalle.get(i).getSubtotal().doubleValue();
				ivatotal=ivatotal+listadetalle.get(i).getValorIva().doubleValue();
				total=total+listadetalle.get(i).getTotalDetalle().doubleValue();
			}
			pedido.setSubtotal(new BigDecimal(subtotal));
			pedido.setIvaTotal(new BigDecimal(ivatotal));
			locacion = managerDPA.findDPA(idCanton);
			if(idCanton.equals("1001")) 
			{
				pedido.setTotal(new BigDecimal(total+5));
			}else 
			{
				pedido.setTotal(new BigDecimal(total));
			}
			pedido.setCostoEnvio(new BigDecimal(0));
			pedido.setBaseImponible(new BigDecimal(ivatotal));
			pedido.setBaseCero(new BigDecimal(0));
			usuario=managerusuario.findUsuarioById(1);
			pedido.setUsuUsuario(usuario);
			managerPedido.insertarPedido(pedido, listadetalle, locacion, estado, usuario);
			listaPedido = managerPedido.findAllPedido();
			pedido=managerPedido.findPedidoByUltimoPedido();
			for(int i=0; i<listadetalle.size() ;i++) 
			{
				managerdetalle.poneridPedido_detalle(listadetalle.get(i), pedido);
			}
			pedido=new PedPedido();
			beandetalle_producto.setListadetalle(null);
			beandetalle_producto.setCantidad_carrito(0);
			beandetalle_producto.setValor_compra(0);
			JSFUtil.crearMensajeInfo("El pedido se ha realizado con éxito");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerSeleccionarEmpresa(PedPedido pedido) {
		pedidoSeleccionado=pedido;
		
	}



	public List<PedPedido> getListaPedido() {
		return listaPedido;
	}



	public void setListaPedido(List<PedPedido> listaPedido) {
		this.listaPedido = listaPedido;
	}



	public List<PedDivpolitica> getListaProvincias() {
		return listaProvincias;
	}



	public void setListaProvincias(List<PedDivpolitica> listaProvincias) {
		this.listaProvincias = listaProvincias;
	}



	public List<PedDivpolitica> getListaCantones() {
		return listaCantones;
	}



	public void setListaCantones(List<PedDivpolitica> listaCantones) {
		this.listaCantones = listaCantones;
	}



	public List<PedDivpolitica> getListaCantonesProvincia() {
		return listaCantonesProvincia;
	}



	public void setListaCantonesProvincia(List<PedDivpolitica> listaCantonesProvincia) {
		this.listaCantonesProvincia = listaCantonesProvincia;
	}



	public List<PedDetallePedido> getListadetalle() {
		return listadetalle;
	}



	public void setListadetalle(List<PedDetallePedido> listadetalle) {
		this.listadetalle = listadetalle;
	}



	public PedPedido getPedido() {
		return pedido;
	}



	public void setPedido(PedPedido pedido) {
		this.pedido = pedido;
	}



	public PedPedido getPedidoSeleccionado() {
		return pedidoSeleccionado;
	}



	public void setPedidoSeleccionado(PedPedido pedidoSeleccionado) {
		this.pedidoSeleccionado = pedidoSeleccionado;
	}



	public PedEstado getEstado() {
		return estado;
	}



	public void setEstado(PedEstado estado) {
		this.estado = estado;
	}



	public PedDivpolitica getLocacion() {
		return locacion;
	}



	public void setLocacion(PedDivpolitica locacion) {
		this.locacion = locacion;
	}



	public UsuUsuario getUsuario() {
		return usuario;
	}



	public void setUsuario(UsuUsuario usuario) {
		this.usuario = usuario;
	}



	public String getIdProvincia() {
		return idProvincia;
	}



	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}



	public String getIdCanton() {
		return idCanton;
	}



	public void setIdCanton(String idCanton) {
		this.idCanton = idCanton;
	}



	public List<PedEstado> getListaestado() {
		return listaestado;
	}



	public void setListaestado(List<PedEstado> listaestado) {
		this.listaestado = listaestado;
	}
	
	/*
	 * public void actionListenerActualizarEmpresa() { try { locacion =
	 * managerDPA.findDPA(idCanton);
	 * managerEmpresa.actualizarEmpresa(empresaSeleccionada);
	 * listaEmpresa=managerEmpresa.findAllEmpresa();
	 * JSFUtil.crearMensajeInfo("Datos actualizados"); } catch (Exception e) {
	 * listaEmpresa=managerEmpresa.findAllEmpresa();
	 * JSFUtil.crearMensajeError(e.getMessage()); e.printStackTrace(); } }
	 */



	
	

	

}
