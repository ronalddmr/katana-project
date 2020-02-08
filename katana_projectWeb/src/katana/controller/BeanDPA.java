package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import katana.controller.JSFUtil;
import katana.model.entities.ProColor;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;
import katana.model.manager.ManagerProducto;
import katana.model.manager.ManagerTalla;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanDPA implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerTalla managerTalla;

	private List<ProTalla> listaTalla;

	private ProTalla talla;

	private boolean panelColapsado_Talla;

	private ProTalla tallaSeleccionada;
	@PostConstruct
	public void inicializar() 
	{
	    listaTalla=managerTalla.findAllTalla();
	    talla=new ProTalla();
	    panelColapsado_Talla=true;
	}	
	
/*BEAN PARA pro_talla*/
	
	public void actionListenerColapsarPanel_Talla() {
		panelColapsado_Talla=!panelColapsado_Talla;
	}
	public void actionListenerInsertarTalla() {
		try {
			managerTalla.insertarTalla(talla);
			listaTalla=managerTalla.findAllTalla();
			talla = new  ProTalla();
			JSFUtil.crearMensajeInfo("Se ha insertado la talla.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarTalla(ProTalla talla) {
		tallaSeleccionada=talla;
	}
	public void actionListenerActualizarTalla() {
		try {
			managerTalla.actualizarTalla(tallaSeleccionada);
			listaTalla=managerTalla.findAllTalla();
			JSFUtil.crearMensajeInfo("Datos actualizados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarTalla(int id) {
		managerTalla.eliminarTalla(id);
		listaTalla=managerTalla.findAllTalla();
		JSFUtil.crearMensajeInfo("Talla eliminada.");
	}

	public List<ProTalla> getListaTalla() {
		return listaTalla;
	}

	public void setListaTalla(List<ProTalla> listaTalla) {
		this.listaTalla = listaTalla;
	}

	public ProTalla getTalla() {
		return talla;
	}

	public void setTalla(ProTalla talla) {
		this.talla = talla;
	}


	public ProTalla getTallaSeleccionada() {
		return tallaSeleccionada;
	}

	public void setTallaSeleccionada(ProTalla tallaSeleccionada) {
		this.tallaSeleccionada = tallaSeleccionada;
	}

	public boolean isPanelColapsado_Talla() {
		return panelColapsado_Talla;
	}

	public void setPanelColapsado_Talla(boolean panelColapsado_Talla) {
		this.panelColapsado_Talla = panelColapsado_Talla;
	}

}
