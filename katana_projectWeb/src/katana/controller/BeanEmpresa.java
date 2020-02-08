package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import katana.controller.JSFUtil;
import katana.model.entities.AudEmpresa;
import katana.model.entities.PedDivpolitica;
import katana.model.manager.ManagerDPA;
import katana.model.manager.ManagerEmpresa;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanEmpresa implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerEmpresa managerEmpresa;
	@EJB
	private ManagerDPA managerDPA;
	private List<AudEmpresa> listaEmpresa;
	private List<PedDivpolitica> listaProvincias;
	private List<PedDivpolitica> listaCantones;
	private List<PedDivpolitica> listaCantonesProvincia;
	
	
	private AudEmpresa empresa;
	private AudEmpresa empresaSeleccionada;
	
	private PedDivpolitica locacion;
	private String idProvincia;
	private String idCanton;

	@PostConstruct
	public void inicializar() 
	{
		empresa = new AudEmpresa();
		locacion = new PedDivpolitica();
		empresaSeleccionada = new AudEmpresa();
		empresaSeleccionada.setPedDivpolitica(locacion);
		
		listaProvincias = managerDPA.findAllProvincias();
		listaCantones = managerDPA.findAllCantones();
		listaEmpresa = managerEmpresa.findAllEmpresa();
		
	
	}
	
	
	
/*Bean para Empresa*/
	
	public void actionListenerCantones()
	{
		listaCantonesProvincia = managerDPA.findCantonesProvincia(listaCantones, idProvincia);
	}
	
	
	
	public void actionListenerInsertarEmpresa() {
		try {
			locacion = managerDPA.findDPA(idCanton);
			managerEmpresa.insertarEmpresa(empresa, locacion);
			listaEmpresa=managerEmpresa.findAllEmpresa();
			empresa=new AudEmpresa();
			JSFUtil.crearMensajeInfo("Ingreso Exitoso");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerSeleccionarEmpresa(AudEmpresa Empresa) {
		empresaSeleccionada=Empresa;
		
	}
	
	public void actionListenerActualizarEmpresa() {
		try {
			locacion = managerDPA.findDPA(idCanton);
			managerEmpresa.actualizarEmpresa(empresaSeleccionada);
			listaEmpresa=managerEmpresa.findAllEmpresa();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaEmpresa=managerEmpresa.findAllEmpresa();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}



	public ManagerEmpresa getManagerEmpresa() {
		return managerEmpresa;
	}



	public void setManagerEmpresa(ManagerEmpresa managerEmpresa) {
		this.managerEmpresa = managerEmpresa;
	}



	public ManagerDPA getManagerDPA() {
		return managerDPA;
	}



	public void setManagerDPA(ManagerDPA managerDPA) {
		this.managerDPA = managerDPA;
	}



	public List<PedDivpolitica> getListaprovincias() {
		return listaProvincias;
	}



	public void setListaprovincias(List<PedDivpolitica> listaprovincias) {
		this.listaProvincias = listaprovincias;
	}



	public List<PedDivpolitica> getListaCantones() {
		return listaCantones;
	}



	public void setListaCantones(List<PedDivpolitica> listaCantones) {
		this.listaCantones = listaCantones;
	}



	public List<PedDivpolitica> getListaCantonesProvincia() {
		return listaCantonesProvincia;
	}



	public void setListaCantonesProvincia(List<PedDivpolitica> listaCantonesProvincia) {
		this.listaCantonesProvincia = listaCantonesProvincia;
	}



	public List<AudEmpresa> getListaEmpresa() {
		return listaEmpresa;
	}



	public void setListaEmpresa(List<AudEmpresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}



	public AudEmpresa getEmpresa() {
		return empresa;
	}



	public void setEmpresa(AudEmpresa empresa) {
		this.empresa = empresa;
	}


	public AudEmpresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}



	public void setEmpresaSeleccionada(AudEmpresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}



	public PedDivpolitica getLocacion() {
		return locacion;
	}



	public void setLocacion(PedDivpolitica locacion) {
		this.locacion = locacion;
	}



	public String getIdProvincia() {
		return idProvincia;
	}



	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}



	public String getIdCanton() {
		return idCanton;
	}



	public void setIdCanton(String idCanton) {
		this.idCanton = idCanton;
	}
	

	

}
