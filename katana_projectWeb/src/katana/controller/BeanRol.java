package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import katana.controller.JSFUtil;
import katana.model.entities.UsuRol;
import katana.model.manager.ManagerRol;
import katana.model.manager.ManagerUsuario;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanRol implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerRol managerRol;
	private List<UsuRol> listaRol;
	private UsuRol rol;
	private boolean panelColapsadoRol;
	private UsuRol rolSeleccionado;
	@PostConstruct
	public void inicializar() 
	{
	    listaRol=managerRol.findAllROl();
	    rol=new UsuRol();
	    panelColapsadoRol=true;
	}
	
	/*BEAN PARA usu_rol*/
	
	public void actionListenerColapsarPanelRol() {
		panelColapsadoRol=!panelColapsadoRol;
	}
	public void actionListenerInsertarRol() {
		try {
			managerRol.insertarRol(rol);
			listaRol=managerRol.findAllROl();
			rol=new UsuRol();
			JSFUtil.crearMensajeInfo("Se ha insertado el Rol");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarRol(UsuRol rol) {
		rolSeleccionado=rol;
	}
	public void actionListenerActualizarRol() {
		try {
			managerRol.actualizarRol(rolSeleccionado);
			listaRol=managerRol.findAllROl();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaRol=managerRol.findAllROl();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarRol(int id) {
		managerRol.eliminarRol(id);
		listaRol=managerRol.findAllROl();
		JSFUtil.crearMensajeInfo("Rol eliminado");
	}

	public List<UsuRol> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<UsuRol> listaRol) {
		this.listaRol = listaRol;
	}

	public UsuRol getRol() {
		return rol;
	}

	public void setRol(UsuRol rol) {
		this.rol = rol;
	}

	public boolean isPanelColapsadoRol() {
		return panelColapsadoRol;
	}

	public void setPanelColapsadoRol(boolean panelColapsadoRol) {
		this.panelColapsadoRol = panelColapsadoRol;
	}

	public UsuRol getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(UsuRol rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}
	
	
	
	

}
