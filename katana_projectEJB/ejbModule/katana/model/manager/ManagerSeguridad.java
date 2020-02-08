package katana.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import katana.model.dto.LoginDTO;
import katana.model.entities.UsuUsuario;
import katana.model.entities.UsuUsuarioRol;
import katana.model.entities.UsuRol;

/**
 * Implementa la logica de manejo de usuarios y autenticacion
 */
@Stateless
@LocalBean
public class ManagerSeguridad {
	@EJB
	private ManagerDAO managerDAO;
	@EJB
	private ManagerUsuario managerUsuario;
	@EJB
	private ManagerRol managerRol;
    /**
     * Default constructor. 
     */
    public ManagerSeguridad() {
        
    }
    
    /**
	 * Metodo que le permite a un usuario acceder al sistema.
	 * @param codigoUsuario Identificador del usuario.
	 * @param clave Clave de acceso.
	 * @return Retorna el tipo de usuario. Puede tener tres valores:
	 * 			 (Cliente),  (Administrador) o (Dueño)
	 * @throws Exception Cuando no coincide la clave proporcionada o si ocurrio
	 * un error con la consulta a la base de datos.
	 */
	public LoginDTO accederSistema(String correoUsuario,String clave) throws Exception{

		UsuUsuario usuario=(UsuUsuario)managerUsuario.findUsuarioByMail(correoUsuario);//busco al usuario por su correo electronico
		System.out.println("USUARIO: " + usuario.getNombre());
		UsuUsuarioRol usuRol = usuario.getUsuUsuarioRols().get(0); //aqui le mando el objeto relacinado a la tabla usuarios
		//le mando en la posicion cero de todas las relaciones que tiene 
		//porque no va a tener mas roles, no se de que otra manera hacerlo pepos :c ya intente full cosas
		UsuRol rol = usuRol.getUsuRol(); //Y ahora de la tabla intermedia cojo y le mando el rol
		System.out.println("ROL: " + rol.getNombre());
		
		if(usuario==null)
			throw new Exception("Error en usuario y/o clave.");
		
		if(!usuario.getPassword().equals(clave))
			throw new Exception("Error en usuario y/o clave.");
		
		LoginDTO loginDTO=new LoginDTO();
		
		loginDTO.setUsuario(usuario.getNombre());
		loginDTO.setUsuario(usuario.getApellido());
		loginDTO.setUsuario(usuario.getCorreo());
		loginDTO.setUsuario(rol.getNombre());

		//dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags web:
		if(rol.getNombre().equals("Cliente"))
			loginDTO.setRutaAcceso("/Usuario_final/user_profile.xhtml");
		else if(rol.getNombre().equals("Administrador") || rol.getNombre().equals("Dueño"))
			loginDTO.setRutaAcceso("/Usuario_administrador/index_admin.xhtml"); //tiene que redirigir al menu que Ervin esta haciendo de admin
		return loginDTO;
	}
	
	public UsuUsuario findUsuarioByMail(String correoUsuario) throws Exception {
		UsuUsuario usuario=(UsuUsuario)managerDAO.findById(UsuUsuario.class, correoUsuario);
		return usuario;
	}
	
	
	public String getNombreUsuario(String correoUsuario) {
		UsuUsuario usuario=(UsuUsuario)managerUsuario.findUsuarioByMail(correoUsuario);
		return usuario.getNombre();
	}

}
