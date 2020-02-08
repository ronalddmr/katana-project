package katana.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import katana.controller.JSFUtil;
import katana.model.entities.UsuRol;
import katana.model.entities.UsuUsuario;
import katana.model.entities.UsuUsuarioRol;
import katana.model.manager.ManagerUsuario;
import katana.model.manager.ManagerRol;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanUsuario implements Serializable {
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerUsuario managerUsuario;
	@EJB
	private ManagerRol managerRol;
	
	/*USUARIO*/
	private List<UsuUsuario> listaUsuario;
	private UsuUsuario usuario;
	private boolean panelColapsado_usuario;
	private UsuUsuario usuarioSeleccionado;
	
	/*ROL*/
	private List<UsuRol> listaRol;
	private UsuRol rol;
	private UsuRol rolSeleccionado;
	
	/*USUARIO-ROL: relacion entre las 2 tablas */
	private List<UsuUsuarioRol> listaUsuarioRol;
	private UsuUsuarioRol usuarioRol;
	private UsuUsuarioRol usuarioRolSeleccionado;

	@PostConstruct
	public void inicializar() {
		/*USUARIO*/
		listaUsuario = managerUsuario.findAllUsuarios();
		usuario = new UsuUsuario();
		panelColapsado_usuario = true;
		/*ROL*/
		rol=new UsuRol();
		/*USUARIO-ROL*/
		listaUsuarioRol = managerUsuario.findAllUsuariosRol();
		usuarioRol = new UsuUsuarioRol();
		
	}

	/* BEAN PARA usu_usuario */

	
	 public void actionListenerColapsarPanel_usuario() { panelColapsado_usuario =
	 !panelColapsado_usuario; }
	 

	public String actionListenerInsertarUsuario() {
		/*
		 * ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		 * String requestPath=ec.getRequestPathInfo();
		 */
		try {
			
			managerUsuario.insertarUsuario(usuario);
			listaUsuario = managerUsuario.findAllUsuarios();
			usuario=managerUsuario.findUsuarioById(managerUsuario.findIdUsuarioMayor());
			/*El numero 2 es porque en mi caso */
			rol=managerRol.findRolById(2);
			managerUsuario.insertarUsuarioRol(rol, usuario);
			listaUsuarioRol = managerUsuario.findAllUsuariosRol();
			usuario = new UsuUsuario();
			rol=new UsuRol();
			JSFUtil.crearMensajeInfo("Registro con exito.");			
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/clientes/index.xhtml?faces-redirect=true";
	}

	public void actionListenerSeleccionarUsuario(UsuUsuario usuario) {
		usuarioSeleccionado = usuario;
	}

	public void actionListenerActualizarUsuario() {
		try {
			managerUsuario.actualizarUsuario(usuarioSeleccionado);
			listaUsuario = managerUsuario.findAllUsuarios();
			JSFUtil.crearMensajeInfo("Datos actualizados.");
		} catch (Exception e) {
			listaUsuario = managerUsuario.findAllUsuarios();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarUsuario(int id) {
		managerUsuario.eliminarUsuario(id);
		listaUsuario = managerUsuario.findAllUsuarios();
		JSFUtil.crearMensajeInfo("Usuario eliminado.");
	}

	public List<UsuUsuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<UsuUsuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public UsuUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuUsuario usuario) {
		this.usuario = usuario;
	}

	public boolean isPanelColapsado_usuario() {
		return panelColapsado_usuario;
	}

	public void setPanelColapsado_usuario(boolean panelColapsado_usuario) {
		this.panelColapsado_usuario = panelColapsado_usuario;
	}

	public UsuUsuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(UsuUsuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	/* Para Registrar administradores */
	public void actionListenerInsertarUsuarioAdministrador() {
		try {
			
			managerUsuario.insertarUsuario(usuario);
			listaUsuario = managerUsuario.findAllUsuarios();
			usuario=managerUsuario.findUsuarioById(managerUsuario.findIdUsuarioMayor());
			/*El numero 2 es porque en mi caso */
			rol=managerRol.findRolById(3);
			managerUsuario.insertarUsuarioRol(rol, usuario);
			listaUsuarioRol = managerUsuario.findAllUsuariosRol();
			usuario = new UsuUsuario();
			rol=new UsuRol();
			JSFUtil.crearMensajeInfo("Registro con exito.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}


	/*
	 * public void actionListenerColapsarPanel_usuariorol() {
	 * panelColapsado_usuario_rol = !panelColapsado_usuario_rol; }
	 */

	/*public void actionListenerInsertarUsuarioRol(int id) {
		try {
			UsuRol auxRol = new UsuRol();
			auxRol = managerRol.findRolById(id);

			UsuUsuarioRol auxUsuRol = new UsuUsuarioRol();

			auxUsuRol.setUsuUsuario(usuario);
			auxUsuRol.setUsuRol(auxRol);
			//managerUsuario.insertarUsuarioRol(auxUsuRol);
			listaUsuarioRol = managerUsuario.findAllUsuariosRol();
			JSFUtil.crearMensajeInfo("Registro con exito.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}*/
	 

	public void actionListenerSeleccionarUsuarioRol(UsuUsuarioRol usuarioRol) {
		usuarioRolSeleccionado = usuarioRol;
	}

	public void actionListenerActualizarUsuarioRol() {
		try {
			
			UsuUsuarioRol aux = new UsuUsuarioRol();
			
			rol = usuarioRolSeleccionado.getUsuRol();
			usuario = usuarioRolSeleccionado.getUsuUsuario();
			
			aux.setUsuRol(rol);
			aux.setUsuUsuario(usuario);
			managerUsuario.actualizarUsuarioRolSeleccionado(aux);
			
			listaUsuarioRol = managerUsuario.findAllUsuariosRol();
			usuarioRol = new UsuUsuarioRol();
			rol = new UsuRol();
			JSFUtil.crearMensajeInfo("Datos actualizados.");
		} catch (Exception e) {
			listaUsuarioRol = managerUsuario.findAllUsuariosRol();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarUsuarioRol(int id) {
		managerUsuario.eliminarUsuarioRol(id);
		listaUsuarioRol = managerUsuario.findAllUsuariosRol();
		JSFUtil.crearMensajeInfo("UsuarioRol eliminado.");
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

	public UsuRol getRolSeleccionado() {
		return rolSeleccionado;
	}

	public void setRolSeleccionado(UsuRol rolSeleccionado) {
		this.rolSeleccionado = rolSeleccionado;
	}

	public List<UsuUsuarioRol> getListaUsuarioRol() {
		return listaUsuarioRol;
	}

	public void setListaUsuarioRol(List<UsuUsuarioRol> listaUsuarioRol) {
		this.listaUsuarioRol = listaUsuarioRol;
	}

	public UsuUsuarioRol getUsuarioRol() {
		return usuarioRol;
	}

	public void setUsuarioRol(UsuUsuarioRol usuarioRol) {
		this.usuarioRol = usuarioRol;
	}

	public UsuUsuarioRol getUsuarioRolSeleccionado() {
		return usuarioRolSeleccionado;
	}

	public void setUsuarioRolSeleccionado(UsuUsuarioRol usuarioRolSeleccionado) {
		this.usuarioRolSeleccionado = usuarioRolSeleccionado;
	}

	/*
	 * public boolean isPanelColapsado_usuario_rol() { return
	 * panelColapsado_usuario_rol; }
	 * 
	 * public void setPanelColapsado_usuario_rol(boolean panelColapsado_usuario_rol)
	 * { this.panelColapsado_usuario_rol = panelColapsado_usuario_rol; }
	 */
	
	

}
