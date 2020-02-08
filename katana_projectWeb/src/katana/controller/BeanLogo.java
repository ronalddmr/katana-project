package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import katana.controller.JSFUtil;
import katana.model.entities.ProEstilo;
import katana.model.entities.ProLogo;
import katana.model.manager.ManagerColor;
import katana.model.manager.ManagerEstilo;
import katana.model.manager.ManagerLogo;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanLogo implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerLogo managerLogo;
	private List<ProLogo> listaLogo;
	private ProLogo logo;
	private boolean panelColapsado_Logo;
	private ProLogo logoSeleccionado;

	@PostConstruct
	public void inicializar() 
	{
		listaLogo=managerLogo.findAllLogo();
		logo=new ProLogo();
		panelColapsado_Logo=true;
	    
	}
	
/*BEAN PARA pro_logo*/
	
	public void actionListenerColapsarPanel_Logo() {
		panelColapsado_Logo=!panelColapsado_Logo;
	}
	public void actionListenerInsertarLogo() {
		try {
			managerLogo.insertarLogo(logo);;
			listaLogo=managerLogo.findAllLogo();
			logo=new ProLogo();
			JSFUtil.crearMensajeInfo("Se ha insertado un Logo");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarLogo(ProLogo logo) {
		logoSeleccionado=logo;
	}
	public void actionListenerActualizarLogo() {
		try {
			managerLogo.actualizarLogo(logoSeleccionado);
			listaLogo=managerLogo.findAllLogo();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaLogo=managerLogo.findAllLogo();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarLogo(int id) {
		managerLogo.eliminarLogo(id);
		listaLogo=managerLogo.findAllLogo();
		JSFUtil.crearMensajeInfo("Logo eliminado");
	}

	public ManagerLogo getManagerLogo() {
		return managerLogo;
	}

	public void setManagerLogo(ManagerLogo managerLogo) {
		this.managerLogo = managerLogo;
	}

	public List<ProLogo> getListaLogo() {
		return listaLogo;
	}

	public void setListaLogo(List<ProLogo> listaLogo) {
		this.listaLogo = listaLogo;
	}

	public ProLogo getLogo() {
		return logo;
	}

	public void setLogo(ProLogo logo) {
		this.logo = logo;
	}

	public boolean isPanelColapsado_Logo() {
		return panelColapsado_Logo;
	}

	public void setPanelColapsado_Logo(boolean panelColapsado_Logo) {
		this.panelColapsado_Logo = panelColapsado_Logo;
	}

	public ProLogo getLogoSeleccionado() {
		return logoSeleccionado;
	}

	public void setLogoSeleccionado(ProLogo logoSeleccionado) {
		this.logoSeleccionado = logoSeleccionado;
	}

	
	

	

}
