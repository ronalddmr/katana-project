package katana.model.dto;

import java.util.List;

import javax.ejb.EJB;

import katana.model.entities.PedPedido;
import katana.model.entities.ProProducto;
import katana.model.entities.UsuUsuario;
import katana.model.manager.ManagerUsuario;

/**
 * DTO para el acceso al sistema.
 * @author mrea
 *
 */
public class LoginDTO {
	private String usuario;
	private int codigoUsuario;
	private String tipoUsuario;
	private String rutaAcceso;
	private String apellido;
	private String correo; //este no se si va porque creo que codigoUsuario ya es el correo
	private String imagen;
	private String password;
	private List<PedPedido> pedidos;
	private List<ProProducto> productos;
	
	private UsuUsuario usuarioSeleccionado;
	@EJB
	private ManagerUsuario managerUsuario;
	
	public void actionListenerSeleccionarUsuario(UsuUsuario usuario) {
		usuarioSeleccionado = usuario;
	}
	
	/*
	 * public void actionListenerActualizarUsuario() { try {
	 * managerUsuario.actualizarUsuario(usuarioSeleccionado);
	 * JSFUtil.crearMensajeInfo("Datos actualizados."); } catch (Exception e) {
	 * JSFUtil.crearMensajeError(e.getMessage()); e.printStackTrace(); } }
	 */
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getRutaAcceso() {
		return rutaAcceso;
	}
	public void setRutaAcceso(String rutaAcceso) {
		this.rutaAcceso = rutaAcceso;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<PedPedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedPedido> pedidos) {
		this.pedidos = pedidos;
	}
	public List<ProProducto> getProductos() {
		return productos;
	}
	public void setProductos(List<ProProducto> productos) {
		this.productos = productos;
	}
	
	
	
}
