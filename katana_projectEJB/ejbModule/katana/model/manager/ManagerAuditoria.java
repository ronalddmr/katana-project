package katana.model.manager;

import java.net.InetAddress;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import katana.model.entities.Bitacora;




@Stateless
@LocalBean
public class ManagerAuditoria {
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerSeguridad managerSeguridad;

	public ManagerAuditoria() {
		
	}
	

	
	/**
	 * Almacena la informacion de un evento en la tabla de auditoria.
	 * @param codigoUsuario Codigo del usuario que genero el evento.
	 * @param clase Clase que genera el evento.
	 * @param metodo Nombre del metodo que genero el evento.
	 * @param descripcion Informacion detallada del evento.
	 * @throws Exception 
	 */
	public void crearEvento(int codigoUsuario,Class clase,String metodo,String descripcion) throws Exception{
		Bitacora evento=new Bitacora();
		//cambio para probar git
		
		//Obtener la DIreccion IP
	       InetAddress direccion = InetAddress.getLocalHost();
           String IP_local = direccion.getHostAddress();//ip como String
		
		//if(codigoUsuario==null )
			//throw new Exception("Error auditoria: debe indicar el codigo del usuario.");
		if(metodo==null||metodo.length()==0)
			throw new Exception("Error auditoria: debe indicar el metodo que genera el evento.");
		System.out.print(codigoUsuario);
		evento.setCodigoUsuario(codigoUsuario);
		evento.setMetodo(clase.getSimpleName()+"/"+metodo);
		evento.setDescripcion(descripcion);
		evento.setFechaEvento(new Date());
		evento.setDireccionIp(IP_local);
		// se puede consultar desde JSF como extraer la ip desde donde accede el cliente
		// que se conecta
		managerDAO.insertar(evento);
	}
	
}
