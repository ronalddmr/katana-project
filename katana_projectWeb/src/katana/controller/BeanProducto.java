package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import katana.controller.JSFUtil;
import katana.model.entities.ProColor;
import katana.model.entities.ProProducto;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;
import katana.model.manager.ManagerProducto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Named
@SessionScoped
public class BeanProducto implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerProducto managerProducto;
	private List<ProTipoProducto> listaTipoProducto;
	private ProTipoProducto tipo_producto;
	private boolean panelColapsado;
	private ProTipoProducto tipo_productoSeleccionado;
	private List<ProProducto> listaProducto;
	private ProProducto producto;
	private ProProducto productoSeleccionado;
	@PostConstruct
	public void inicializar() 
	{
	    listaTipoProducto=managerProducto.findAllTipoProducto();
	    listaProducto = managerProducto.findAllProducto();
	    tipo_producto=new ProTipoProducto();
	    producto = new ProProducto();
	    panelColapsado=true;
	}
	
	/*BEAN PARA pro_producto*/
	public void actionListenerInsertarProducto() {
		try {
			
			managerProducto.insertarProducto(producto);
			listaProducto=managerProducto.findAllProducto();
			producto=new ProProducto();
			JSFUtil.crearMensajeInfo("Se ha insertado el Producto");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerSeleccionarProducto(ProProducto producto) {
		productoSeleccionado=producto;
	}
	
	public void actionListenerActualizarProducto() {
		try {
			managerProducto.actualizarProducto(productoSeleccionado);
			listaProducto=managerProducto.findAllProducto();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaProducto=managerProducto.findAllProducto();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarProducto(int id) {
		managerProducto.eliminarProducto(id);
		listaProducto=managerProducto.findAllProducto();
		JSFUtil.crearMensajeInfo("Producto eliminado");
	}
	
	/*BEAN PARA pro_tipo_producto*/
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	public void actionListenerInsertarTipoProducto() {
		try {
			managerProducto.insertarTipoProducto(tipo_producto);
			listaTipoProducto=managerProducto.findAllTipoProducto();
			tipo_producto=new ProTipoProducto();
			JSFUtil.crearMensajeInfo("Se ha insertado el Tipo de Producto");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarTipoProducto(ProTipoProducto tipoProducto) {
		tipo_productoSeleccionado=tipoProducto;
	}
	public void actionListenerActualizarTipoProducto() {
		try {
			managerProducto.actualizarTipoProducto(tipo_productoSeleccionado);
			listaTipoProducto=managerProducto.findAllTipoProducto();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaTipoProducto=managerProducto.findAllTipoProducto();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarTipoProducto(int id) {
		managerProducto.eliminarTipoProducto(id);
		listaTipoProducto=managerProducto.findAllTipoProducto();
		JSFUtil.crearMensajeInfo("Tipo de Producto eliminado");
	}
	public List<ProTipoProducto> getListaTipoProducto() {
		return listaTipoProducto;
	}
	public void setListaTipoProducto(List<ProTipoProducto> listaTipoProducto) {
		this.listaTipoProducto = listaTipoProducto;
	}
	public ProTipoProducto getTipo_producto() {
		return tipo_producto;
	}
	public void setTipo_producto(ProTipoProducto tipo_producto) {
		this.tipo_producto = tipo_producto;
	}
	public boolean isPanelColapsado() {
		return panelColapsado;
	}
	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}
	public ProTipoProducto getTipo_productoSeleccionado() {
		return tipo_productoSeleccionado;
	}
	public void setTipo_productoSeleccionado(ProTipoProducto tipo_productoSeleccionado) {
		this.tipo_productoSeleccionado = tipo_productoSeleccionado;
	}

	public ProProducto getProducto() {
		return producto;
	}

	public void setProducto(ProProducto producto) {
		this.producto = producto;
	}

	public List<ProProducto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<ProProducto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public ProProducto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(ProProducto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}
	
	
	
	
	
	
	

}
