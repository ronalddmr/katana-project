package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usu_rol database table.
 * 
 */
@Entity
@Table(name="usu_rol")
@NamedQuery(name="UsuRol.findAll", query="SELECT u FROM UsuRol u")
public class UsuRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol", unique=true, nullable=false)
	private Integer idRol;

	@Column(length=50)
	private String descripcion;

	@Column(length=50)
	private String nombre;

	//bi-directional many-to-one association to UsuUsuarioRol
	@OneToMany(mappedBy="usuRol")
	private List<UsuUsuarioRol> usuUsuarioRols;

	public UsuRol() {
	}

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<UsuUsuarioRol> getUsuUsuarioRols() {
		return this.usuUsuarioRols;
	}

	public void setUsuUsuarioRols(List<UsuUsuarioRol> usuUsuarioRols) {
		this.usuUsuarioRols = usuUsuarioRols;
	}

	public UsuUsuarioRol addUsuUsuarioRol(UsuUsuarioRol usuUsuarioRol) {
		getUsuUsuarioRols().add(usuUsuarioRol);
		usuUsuarioRol.setUsuRol(this);

		return usuUsuarioRol;
	}

	public UsuUsuarioRol removeUsuUsuarioRol(UsuUsuarioRol usuUsuarioRol) {
		getUsuUsuarioRols().remove(usuUsuarioRol);
		usuUsuarioRol.setUsuRol(null);

		return usuUsuarioRol;
	}

}