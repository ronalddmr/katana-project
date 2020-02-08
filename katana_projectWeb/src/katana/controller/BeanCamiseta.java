package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import katana.controller.JSFUtil;
import katana.model.entities.ProCamiseta;
import katana.model.entities.ProEstilo;
import katana.model.entities.ProLogo;
import katana.model.manager.ManagerCamiseta;
import katana.model.manager.ManagerColor;
import katana.model.manager.ManagerEstilo;
import katana.model.manager.ManagerLogo;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanCamiseta implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerCamiseta managerCamiseta;
	private List<ProCamiseta> listaCamiseta;
	private ProCamiseta camiseta;
	private boolean panelColapsado_Camiseta;
	private ProCamiseta camisetaSeleccionado;

	@PostConstruct
	public void inicializar() 
	{
		listaCamiseta=managerCamiseta.findAllCamiseta();
		camiseta=new ProCamiseta();
		panelColapsado_Camiseta=true;
	    
	}
	
/*BEAN PARA pro_camiseta*/
	
	public void actionListenerColapsarPanel_Camiseta() {
		panelColapsado_Camiseta=!panelColapsado_Camiseta;
	}
	public void actionListenerInsertarCamiseta() {
		try {
			managerCamiseta.insertarCamiseta(camiseta);;
			listaCamiseta=managerCamiseta.findAllCamiseta();
			camiseta=new ProCamiseta();
			JSFUtil.crearMensajeInfo("Se ha insertado un Camiseta");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarCamiseta(ProCamiseta camiseta) {
		camisetaSeleccionado=camiseta;
	}
	public void actionListenerActualizarCamiseta() {
		try {
			managerCamiseta.actualizarCamiseta(camisetaSeleccionado);
			listaCamiseta=managerCamiseta.findAllCamiseta();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaCamiseta=managerCamiseta.findAllCamiseta();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarCamiseta(int id) {
		managerCamiseta.eliminarCamiseta(id);
		listaCamiseta=managerCamiseta.findAllCamiseta();
		JSFUtil.crearMensajeInfo("Camiseta eliminada");
	}

	public ManagerCamiseta getManagerCamiseta() {
		return managerCamiseta;
	}

	public void setManagerCamiseta(ManagerCamiseta managerCamiseta) {
		this.managerCamiseta = managerCamiseta;
	}

	public List<ProCamiseta> getListaCamiseta() {
		return listaCamiseta;
	}

	public void setListaCamiseta(List<ProCamiseta> listaCamiseta) {
		this.listaCamiseta = listaCamiseta;
	}

	public ProCamiseta getCamiseta() {
		return camiseta;
	}

	public void setCamiseta(ProCamiseta camiseta) {
		this.camiseta = camiseta;
	}

	public boolean isPanelColapsado_Camiseta() {
		return panelColapsado_Camiseta;
	}

	public void setPanelColapsado_Camiseta(boolean panelColapsado_Camiseta) {
		this.panelColapsado_Camiseta = panelColapsado_Camiseta;
	}

	public ProCamiseta getCamisetaSeleccionado() {
		return camisetaSeleccionado;
	}

	public void setCamisetaSeleccionado(ProCamiseta camisetaSeleccionado) {
		this.camisetaSeleccionado = camisetaSeleccionado;
	}
	




}

	