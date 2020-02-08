package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usu_usuario_rol database table.
 * 
 */
@Entity
@Table(name="usu_usuario_rol")
@NamedQuery(name="UsuUsuarioRol.findAll", query="SELECT u FROM UsuUsuarioRol u")
public class UsuUsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario_rol", unique=true, nullable=false)
	private Integer idUsuarioRol;

	//bi-directional many-to-one association to UsuRol
	@ManyToOne
	@JoinColumn(name="id_rol")
	private UsuRol usuRol;

	//bi-directional many-to-one association to UsuUsuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private UsuUsuario usuUsuario;

	public UsuUsuarioRol() {
	}

	public Integer getIdUsuarioRol() {
		return this.idUsuarioRol;
	}

	public void setIdUsuarioRol(Integer idUsuarioRol) {
		this.idUsuarioRol = idUsuarioRol;
	}

	public UsuRol getUsuRol() {
		return this.usuRol;
	}

	public void setUsuRol(UsuRol usuRol) {
		this.usuRol = usuRol;
	}

	public UsuUsuario getUsuUsuario() {
		return this.usuUsuario;
	}

	public void setUsuUsuario(UsuUsuario usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

}