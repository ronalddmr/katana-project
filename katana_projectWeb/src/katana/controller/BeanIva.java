package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import katana.controller.JSFUtil;
import katana.model.entities.PedIva;
import katana.model.manager.ManagerPedido;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanIva implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPedido managerPedido;
	private List<PedIva> listaIva;
	private PedIva iva;
	private boolean panelColapsado;
	private PedIva ivaSeleccionado;
	@PostConstruct
	public void inicializar() 
	{
	    listaIva=managerPedido.findAllIva();
	    iva=new PedIva();
	    panelColapsado=true;
	}
	
	/*BEAN PARA pro_tipo_producto*/
	
	public void actionListenerColapsarPanel() {
		panelColapsado=!panelColapsado;
	}
	public void actionListenerInsertarIva() {
		try {
			managerPedido.insertarIva(iva);
			listaIva=managerPedido.findAllIva();
			iva=new PedIva();
			JSFUtil.crearMensajeInfo("Se ha insertado un nuevo iva.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarIva(PedIva iva) {
		ivaSeleccionado=iva;
	}
	public void actionListenerActualizarIva() {
		try {
			managerPedido.actualizarIva(iva);
			listaIva=managerPedido.findAllIva();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaIva=managerPedido.findAllIva();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarIva(int id) {
		managerPedido.eliminarIva(id);
		listaIva=managerPedido.findAllIva();
		JSFUtil.crearMensajeInfo("Iva eliminado");
	}

	public List<PedIva> getListaIva() {
		return listaIva;
	}

	public void setListaIva(List<PedIva> listaIva) {
		this.listaIva = listaIva;
	}

	public PedIva getIva() {
		return iva;
	}

	public void setIva(PedIva iva) {
		this.iva = iva;
	}

	public boolean isPanelColapsado() {
		return panelColapsado;
	}

	public void setPanelColapsado(boolean panelColapsado) {
		this.panelColapsado = panelColapsado;
	}

	public PedIva getIvaSeleccionado() {
		return ivaSeleccionado;
	}

	public void setIvaSeleccionado(PedIva ivaSeleccionado) {
		this.ivaSeleccionado = ivaSeleccionado;
	}
	
	
	

}
