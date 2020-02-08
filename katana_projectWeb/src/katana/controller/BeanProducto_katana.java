package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import katana.controller.JSFUtil;
import katana.model.entities.ProCatalogo;
import katana.model.entities.ProColor;
import katana.model.entities.ProEstilo;
import katana.model.entities.ProProducto;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;
import katana.model.manager.ManagerCatalogo;
import katana.model.manager.ManagerColor;
import katana.model.manager.ManagerEstilo;
import katana.model.manager.ManagerProducto;
import katana.model.manager.ManagerProducto_katana;
import katana.model.manager.ManagerTalla;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanProducto_katana implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerProducto_katana managerProducto;
	@EJB
	private ManagerProducto managerTipo_Producto;
	@EJB
	private ManagerEstilo managerEstilo;
	@EJB
	private ManagerColor managerColor;
	@EJB
	private ManagerTalla managerTalla;
	@EJB
	private ManagerCatalogo managerCatalogo;
	
	private List<ProProducto> listaProducto;
	private List<ProTipoProducto> listaTipoProducto;
	private List<ProEstilo> listaEstilo;
	private List<ProColor> listaColor;
	private List<ProTalla> listaTalla;
	
	private ProProducto producto;
	private ProTipoProducto tipo_producto;
	private ProEstilo estilo;
	private ProColor color;
	private ProTalla talla;
	private ProCatalogo catalogo;
	
	private boolean panelColapsado;
	
	private ProProducto productoSeleccionado;
	private ProTipoProducto tipo_productoSeleccionado;
	private ProEstilo estiloSeleccionado;
	private ProColor colorSeleccionado;
	private ProTalla tallaSeleccionado;
	
	@PostConstruct
	public void inicializar() 
	{
	    listaProducto=managerProducto.findAllProducto();
	    listaTipoProducto=managerTipo_Producto.findAllTipoProducto();
	    listaEstilo=managerEstilo.findAllEstilo();
	    listaColor=managerColor.findAllColor();
	    listaTalla=managerTalla.findAllTalla();
	    producto=new ProProducto();
	    tipo_producto=new ProTipoProducto();
	    estilo=new ProEstilo();
	    color=new ProColor();
	    talla=new ProTalla();
	    catalogo=new ProCatalogo();
	    panelColapsado=true;
	    productoSeleccionado=new ProProducto();
	    productoSeleccionado.setProTipoProducto(tipo_producto);
	    productoSeleccionado.setProTalla(talla);
	    productoSeleccionado.setProColor(color);
	    productoSeleccionado.setProEstilo(estilo);
	}
	
	/*BEAN PARA pro_producto*/
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	public void actionListenerInsertarProducto() {
		try {
			managerProducto.insertarProducto(producto,talla,color,tipo_producto,estilo);
			listaProducto=managerProducto.findAllProducto();
			producto=managerProducto.findProductoByUltimoProducto();
			managerCatalogo.insertarCatalogo(catalogo, producto);
			producto=new ProProducto();
			tipo_producto=new ProTipoProducto();
		    estilo=new ProEstilo();
		    color=new ProColor();
		    talla=new ProTalla();
			JSFUtil.crearMensajeInfo("Se ha insertado el Producto");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarProducto(ProProducto Producto) {
		productoSeleccionado=Producto;

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
		managerCatalogo.eliminarCatalogo(managerCatalogo.findCatalogoByIdProducto(id).getIdCatalogo());
		managerProducto.eliminarProducto(id);
		listaProducto=managerProducto.findAllProducto();
		JSFUtil.crearMensajeInfo("Producto eliminado con su respectivo catálogo");
	}

	public List<ProProducto> getListaProducto() {
		return listaProducto;
	}

	public void setListaProducto(List<ProProducto> listaProducto) {
		this.listaProducto = listaProducto;
	}

	public List<ProTipoProducto> getListaTipoProducto() {
		return listaTipoProducto;
	}

	public void setListaTipoProducto(List<ProTipoProducto> listaTipoProducto) {
		this.listaTipoProducto = listaTipoProducto;
	}

	public List<ProEstilo> getListaEstilo() {
		return listaEstilo;
	}

	public void setListaEstilo(List<ProEstilo> listaEstilo) {
		this.listaEstilo = listaEstilo;
	}

	public List<ProColor> getListaColor() {
		return listaColor;
	}

	public void setListaColor(List<ProColor> listaColor) {
		this.listaColor = listaColor;
	}

	public List<ProTalla> getListaTalla() {
		return listaTalla;
	}

	public void setListaTalla(List<ProTalla> listaTalla) {
		this.listaTalla = listaTalla;
	}

	public ProProducto getProducto() {
		return producto;
	}

	public void setProducto(ProProducto producto) {
		this.producto = producto;
	}

	public ProTipoProducto getTipo_producto() {
		return tipo_producto;
	}

	public void setTipo_producto(ProTipoProducto tipo_producto) {
		this.tipo_producto = tipo_producto;
	}

	public ProEstilo getEstilo() {
		return estilo;
	}

	public void setEstilo(ProEstilo estilo) {
		this.estilo = estilo;
	}

	public ProColor getColor() {
		return color;
	}

	public void setColor(ProColor color) {
		this.color = color;
	}

	public ProTalla getTalla() {
		return talla;
	}

	public void setTalla(ProTalla talla) {
		this.talla = talla;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public ProProducto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(ProProducto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public ProTipoProducto getTipo_productoSeleccionado() {
		return tipo_productoSeleccionado;
	}

	public void setTipo_productoSeleccionado(ProTipoProducto tipo_productoSeleccionado) {
		this.tipo_productoSeleccionado = tipo_productoSeleccionado;
	}

	public ProEstilo getEstiloSeleccionado() {
		return estiloSeleccionado;
	}

	public void setEstiloSeleccionado(ProEstilo estiloSeleccionado) {
		this.estiloSeleccionado = estiloSeleccionado;
	}

	public ProColor getColorSeleccionado() {
		return colorSeleccionado;
	}

	public void setColorSeleccionado(ProColor colorSeleccionado) {
		this.colorSeleccionado = colorSeleccionado;
	}

	public ProTalla getTallaSeleccionado() {
		return tallaSeleccionado;
	}

	public void setTallaSeleccionado(ProTalla tallaSeleccionado) {
		this.tallaSeleccionado = tallaSeleccionado;
	}
	
	
	

}
