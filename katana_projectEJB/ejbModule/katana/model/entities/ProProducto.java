package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the pro_producto database table.
 * 
 */
@Entity
@Table(name="pro_producto")
@NamedQuery(name="ProProducto.findAll", query="SELECT p FROM ProProducto p")
public class ProProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto", unique=true, nullable=false)
	private Integer idProducto;

	@Column(length=500)
	private String descripcion;

	@Column(nullable=false, length=500)
	private String imagen1;

	@Column(length=500)
	private String imagen2;

	@Column(length=500)
	private String imagen3;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(nullable=false)
	private Boolean personalizado;

	@Column(name="precio_personalizado", precision=10, scale=2)
	private BigDecimal precioPersonalizado;

	//bi-directional many-to-one association to ProCatalogo
	@OneToMany(mappedBy="proProducto")
	private List<ProCatalogo> proCatalogos;

	//bi-directional many-to-one association to ProCamiseta
	@ManyToOne
	@JoinColumn(name="id_camiseta")
	private ProCamiseta proCamiseta;

	//bi-directional many-to-one association to ProColor
	@ManyToOne
	@JoinColumn(name="id_color")
	private ProColor proColor;

	//bi-directional many-to-one association to ProEstilo
	@ManyToOne
	@JoinColumn(name="estilo", nullable=false)
	private ProEstilo proEstilo;

	//bi-directional many-to-one association to ProLogo
	@ManyToOne
	@JoinColumn(name="id_logo")
	private ProLogo proLogo;

	//bi-directional many-to-one association to ProTalla
	@ManyToOne
	@JoinColumn(name="id_talla")
	private ProTalla proTalla;

	//bi-directional many-to-one association to ProTipoProducto
	@ManyToOne
	@JoinColumn(name="id_tipo_producto")
	private ProTipoProducto proTipoProducto;

	//bi-directional many-to-one association to UsuUsuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private UsuUsuario usuUsuario;

	public ProProducto() {
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen1() {
		return this.imagen1;
	}

	public void setImagen1(String imagen1) {
		this.imagen1 = imagen1;
	}

	public String getImagen2() {
		return this.imagen2;
	}

	public void setImagen2(String imagen2) {
		this.imagen2 = imagen2;
	}

	public String getImagen3() {
		return this.imagen3;
	}

	public void setImagen3(String imagen3) {
		this.imagen3 = imagen3;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getPersonalizado() {
		return this.personalizado;
	}

	public void setPersonalizado(Boolean personalizado) {
		this.personalizado = personalizado;
	}

	public BigDecimal getPrecioPersonalizado() {
		return this.precioPersonalizado;
	}

	public void setPrecioPersonalizado(BigDecimal precioPersonalizado) {
		this.precioPersonalizado = precioPersonalizado;
	}

	public List<ProCatalogo> getProCatalogos() {
		return this.proCatalogos;
	}

	public void setProCatalogos(List<ProCatalogo> proCatalogos) {
		this.proCatalogos = proCatalogos;
	}

	public ProCatalogo addProCatalogo(ProCatalogo proCatalogo) {
		getProCatalogos().add(proCatalogo);
		proCatalogo.setProProducto(this);

		return proCatalogo;
	}

	public ProCatalogo removeProCatalogo(ProCatalogo proCatalogo) {
		getProCatalogos().remove(proCatalogo);
		proCatalogo.setProProducto(null);

		return proCatalogo;
	}

	public ProCamiseta getProCamiseta() {
		return this.proCamiseta;
	}

	public void setProCamiseta(ProCamiseta proCamiseta) {
		this.proCamiseta = proCamiseta;
	}

	public ProColor getProColor() {
		return this.proColor;
	}

	public void setProColor(ProColor proColor) {
		this.proColor = proColor;
	}

	public ProEstilo getProEstilo() {
		return this.proEstilo;
	}

	public void setProEstilo(ProEstilo proEstilo) {
		this.proEstilo = proEstilo;
	}

	public ProLogo getProLogo() {
		return this.proLogo;
	}

	public void setProLogo(ProLogo proLogo) {
		this.proLogo = proLogo;
	}

	public ProTalla getProTalla() {
		return this.proTalla;
	}

	public void setProTalla(ProTalla proTalla) {
		this.proTalla = proTalla;
	}

	public ProTipoProducto getProTipoProducto() {
		return this.proTipoProducto;
	}

	public void setProTipoProducto(ProTipoProducto proTipoProducto) {
		this.proTipoProducto = proTipoProducto;
	}

	public UsuUsuario getUsuUsuario() {
		return this.usuUsuario;
	}

	public void setUsuUsuario(UsuUsuario usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

}