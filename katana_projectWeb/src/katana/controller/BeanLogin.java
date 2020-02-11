package katana.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import katana.model.dto.LoginDTO;
import katana.model.manager.ManagerAuditoria;
/*import katana.model.manager.ManagerAuditoria;*/
//FALTA DE IMPLEMENTAR ESTO EN EL DIAGRAMA DE LA BASE DE DATOS PORQUE EN EL MANAGER DE AUDITORIA HACE FALTA LAS ENTITIES 
//QUE CORRESPONEN A LA AUDITORIA
import katana.model.manager.ManagerSeguridad;
import katana.model.util.ModelUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

@Named
@javax.enterprise.context.SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String correoUsuario;
	private String clave;
	private String tipoUsuario;
	private boolean acceso;
	@EJB
	private ManagerSeguridad managerSeguridad;
	@EJB
	private ManagerAuditoria managerAuditoria;

	private LoginDTO loginDTO;

	@PostConstruct
	public void inicializar() {
		loginDTO=new LoginDTO();
	}
	/**
	 * Action que permite el acceso al sistema.
	 * @return
	 */
	public String accederSistema(){
		acceso=false;
		try {
			loginDTO=managerSeguridad.accederSistema(correoUsuario, clave);
			nombre = managerSeguridad.getNombreUsuario(correoUsuario);
			//verificamos el acceso del usuario:
			tipoUsuario=loginDTO.getTipoUsuario();
			//redireccion dependiendo del tipo de usuario:
			managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(),this.getClass(), "Acceder Sistema", "Acceso a login");

			/*
			 * managerAuditoria.crearEvento(codigoUsuario, this.getClass(),
			 * "accederSistema", "Acceso a login");
			 */
			return loginDTO.getRutaAcceso()+"?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}
	
	public String accederSistemaCarrito(){
		acceso=false;
		try {
			loginDTO=managerSeguridad.accederSistemaCarrito(correoUsuario, clave);
			nombre = managerSeguridad.getNombreUsuario(correoUsuario);
			//verificamos el acceso del usuario:
			tipoUsuario=loginDTO.getTipoUsuario();
			//redireccion dependiendo del tipo de usuario:
			managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(),this.getClass(), "Acceder Sistema", "Acceso a login");

			/*
			 * managerAuditoria.crearEvento(codigoUsuario, this.getClass(),
			 * "accederSistema", "Acceso a login");
			 */
			return loginDTO.getRutaAcceso()+"?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}
	
	/**
	 * Finaliza la sesion web del usuario.
	 * @return
	 */
	public String salirSistema(){
		System.out.println("salirSistema");
		try {
			managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(),this.getClass(), "Salir Sistema", "Cerrar Sesion");
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/landingPage.xhtml?faces-redirect=true";
	}
	
	public void actionVerificarLogin(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath=ec.getRequestPathInfo();
		try {
			//si no paso por login:
			if(loginDTO==null || ModelUtil.isEmpty(loginDTO.getRutaAcceso())){
				ec.redirect(ec.getRequestContextPath() + "/index.html");
			}else{
				//validar las rutas de acceso:
				if(requestPath.contains("/supervisor") && loginDTO.getRutaAcceso().startsWith("/supervisor"))
					return;
				if(requestPath.contains("/vendedor") && loginDTO.getRutaAcceso().startsWith("/vendedor"))
					return;
				//caso contrario significa que hizo login pero intenta acceder a ruta no permitida:
				ec.redirect(ec.getRequestContextPath() + "/index.html");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String actionReporte() {
		Map<String, Object> parametros = new HashMap<String, Object>();
		/*
		 * parametros.put("p_titulo_principal",p_titulo_principal);
		 * parametros.put("p_titulo",p_titulo);
		 */
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		String ruta = servletContext.getRealPath("auditoria/auditorias.jasper");
		System.out.println(ruta);
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment;filename=auditoria.pdf");
		response.setContentType("application/pdf");
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = null;												// clave de la base de datos
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/katana", "postgres", "sasql");
			JasperPrint impresion = JasperFillManager.fillReport(ruta, parametros, connection);
			JasperExportManager.exportReportToPdfStream(impresion, response.getOutputStream());
			context.getApplication().getStateManager().saveView(context);
			System.out.println("reporte generado.");
			context.responseComplete();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	

	public String getCorreoUsuario() {
		return correoUsuario;
	}
	public void setCorreoUsuario(String correoUsuario) {
		this.correoUsuario = correoUsuario;
	}
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isAcceso() {
		return acceso;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public LoginDTO getLoginDTO() {
		return loginDTO;
	}
	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}
	
	
	
	
	
}
