package katana.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ped_divpolitica database table.
 * 
 */
@Entity
@Table(name="ped_divpolitica")
@NamedQuery(name="PedDivpolitica.findAll", query="SELECT p FROM PedDivpolitica p")
public class PedDivpolitica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_divpolitica", unique=true, nullable=false, length=20)
	private String idDivpolitica;

	@Column(nullable=false, length=50)
	private String nivel;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(name="ultimo_nivel", nullable=false)
	private Boolean ultimoNivel;

	//bi-directional many-to-one association to AudEmpresa
	@OneToMany(mappedBy="pedDivpolitica")
	private List<AudEmpresa> audEmpresas;

	//bi-directional many-to-one association to PedDivpolitica
	@ManyToOne
	@JoinColumn(name="id_divpoliticap")
	private PedDivpolitica pedDivpolitica;

	//bi-directional many-to-one association to PedDivpolitica
	@OneToMany(mappedBy="pedDivpolitica")
	private List<PedDivpolitica> pedDivpoliticas;

	//bi-directional many-to-one association to PedPedido
	@OneToMany(mappedBy="pedDivpolitica")
	private List<PedPedido> pedPedidos;

	public PedDivpolitica() {
	}

	public String getIdDivpolitica() {
		return this.idDivpolitica;
	}

	public void setIdDivpolitica(String idDivpolitica) {
		this.idDivpolitica = idDivpolitica;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getUltimoNivel() {
		return this.ultimoNivel;
	}

	public void setUltimoNivel(Boolean ultimoNivel) {
		this.ultimoNivel = ultimoNivel;
	}

	public List<AudEmpresa> getAudEmpresas() {
		return this.audEmpresas;
	}

	public void setAudEmpresas(List<AudEmpresa> audEmpresas) {
		this.audEmpresas = audEmpresas;
	}

	public AudEmpresa addAudEmpresa(AudEmpresa audEmpresa) {
		getAudEmpresas().add(audEmpresa);
		audEmpresa.setPedDivpolitica(this);

		return audEmpresa;
	}

	public AudEmpresa removeAudEmpresa(AudEmpresa audEmpresa) {
		getAudEmpresas().remove(audEmpresa);
		audEmpresa.setPedDivpolitica(null);

		return audEmpresa;
	}

	public PedDivpolitica getPedDivpolitica() {
		return this.pedDivpolitica;
	}

	public void setPedDivpolitica(PedDivpolitica pedDivpolitica) {
		this.pedDivpolitica = pedDivpolitica;
	}

	public List<PedDivpolitica> getPedDivpoliticas() {
		return this.pedDivpoliticas;
	}

	public void setPedDivpoliticas(List<PedDivpolitica> pedDivpoliticas) {
		this.pedDivpoliticas = pedDivpoliticas;
	}

	public PedDivpolitica addPedDivpolitica(PedDivpolitica pedDivpolitica) {
		getPedDivpoliticas().add(pedDivpolitica);
		pedDivpolitica.setPedDivpolitica(this);

		return pedDivpolitica;
	}

	public PedDivpolitica removePedDivpolitica(PedDivpolitica pedDivpolitica) {
		getPedDivpoliticas().remove(pedDivpolitica);
		pedDivpolitica.setPedDivpolitica(null);

		return pedDivpolitica;
	}

	public List<PedPedido> getPedPedidos() {
		return this.pedPedidos;
	}

	public void setPedPedidos(List<PedPedido> pedPedidos) {
		this.pedPedidos = pedPedidos;
	}

	public PedPedido addPedPedido(PedPedido pedPedido) {
		getPedPedidos().add(pedPedido);
		pedPedido.setPedDivpolitica(this);

		return pedPedido;
	}

	public PedPedido removePedPedido(PedPedido pedPedido) {
		getPedPedidos().remove(pedPedido);
		pedPedido.setPedDivpolitica(null);

		return pedPedido;
	}

}