package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aud_empresa database table.
 * 
 */
@Entity
@Table(name="aud_empresa")
@NamedQuery(name="AudEmpresa.findAll", query="SELECT a FROM AudEmpresa a")
public class AudEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_empresa", unique=true, nullable=false)
	private Integer idEmpresa;

	@Column(nullable=false, length=500)
	private String direccion;

	@Column(nullable=false, length=500)
	private String mision;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(nullable=false, length=100)
	private String referencia;

	@Column(nullable=false, length=15)
	private String telefono1;

	@Column(length=15)
	private String telefono2;

	@Column(nullable=false, length=500)
	private String vision;

	//bi-directional many-to-one association to PedDivpolitica
	@ManyToOne
	@JoinColumn(name="ciudad")
	private PedDivpolitica pedDivpolitica;

	public AudEmpresa() {
	}

	public Integer getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMision() {
		return this.mision;
	}

	public void setMision(String mision) {
		this.mision = mision;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getReferencia() {
		return this.referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getVision() {
		return this.vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public PedDivpolitica getPedDivpolitica() {
		return this.pedDivpolitica;
	}

	public void setPedDivpolitica(PedDivpolitica pedDivpolitica) {
		this.pedDivpolitica = pedDivpolitica;
	}

}