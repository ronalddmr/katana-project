package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import katana.controller.JSFUtil;
import katana.model.entities.ProEstilo;
import katana.model.manager.ManagerColor;
import katana.model.manager.ManagerEstilo;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanEstilo implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerEstilo managerEstilo;
	private List<ProEstilo> listaEstilo;
	private ProEstilo estilo;
	private boolean panelColapsado_Estilo;
	private ProEstilo estiloSeleccionado;

	@PostConstruct
	public void inicializar() 
	{
		listaEstilo=managerEstilo.findAllEstilo();
		estilo=new ProEstilo();
		panelColapsado_Estilo=true;
	    
	}
	
/*BEAN PARA pro_estilo*/
	
	public void actionListenerColapsarPanel_Estilo() {
		panelColapsado_Estilo=!panelColapsado_Estilo;
	}
	public void actionListenerInsertarEstilo() {
		try {
			managerEstilo.insertarEstilo(estilo);;
			listaEstilo=managerEstilo.findAllEstilo();
			estilo=new ProEstilo();
			JSFUtil.crearMensajeInfo("Se ha insertado un Estilo");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarEstilo(ProEstilo estilo) {
		estiloSeleccionado=estilo;
	}
	public void actionListenerActualizarEstilo() {
		try {
			managerEstilo.actualizarEstilo(estiloSeleccionado);
			listaEstilo=managerEstilo.findAllEstilo();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaEstilo=managerEstilo.findAllEstilo();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarEstilo(int id) {
		managerEstilo.eliminarEstilo(id);
		listaEstilo=managerEstilo.findAllEstilo();
		JSFUtil.crearMensajeInfo("Estilo eliminado");
	}

	public List<ProEstilo> getListaEstilo() {
		return listaEstilo;
	}

	public void setListaEstilo(List<ProEstilo> listaEstilo) {
		this.listaEstilo = listaEstilo;
	}

	public ProEstilo getEstilo() {
		return estilo;
	}

	public void setEstilo(ProEstilo estilo) {
		this.estilo = estilo;
	}

	public boolean isPanelColapsado_Estilo() {
		return panelColapsado_Estilo;
	}

	public void setPanelColapsado_Estilo(boolean panelColapsado_Estilo) {
		this.panelColapsado_Estilo = panelColapsado_Estilo;
	}

	public ProEstilo getEstiloSeleccionado() {
		return estiloSeleccionado;
	}

	public void setEstiloSeleccionado(ProEstilo estiloSeleccionado) {
		this.estiloSeleccionado = estiloSeleccionado;
	}

	

	

}
