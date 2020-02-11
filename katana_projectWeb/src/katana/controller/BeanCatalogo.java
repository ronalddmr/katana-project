package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import katana.controller.JSFUtil;
import katana.model.entities.ProCatalogo;
import katana.model.entities.ProProducto;
import katana.model.manager.ManagerCatalogo;
import katana.model.manager.ManagerProducto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class BeanCatalogo implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerCatalogo managerCatalogo;
	private List<ProCatalogo> listaCatalogo;
	private ProCatalogo catalogo;
	private ProCatalogo catalogoSeleccionado;
	private List<ProCatalogo> listaMostrar;

	@PostConstruct
	public void inicializar() 
	{
		
	    listaCatalogo=managerCatalogo.findAllCatalogo();
	    listaMostrar=this.actionListenerListaProductosMostrar();
	    catalogo=new ProCatalogo();
	}
	
	
	
/*BEAN PARA pro_Catalogo*/
	public void actionListenerSeleccionarCatalogo(ProCatalogo Catalogo) {
		catalogoSeleccionado=Catalogo;
	}
	
	public void actionListenerActualizarCatalogo() {
		try {
			managerCatalogo.actualizarCatalogo(catalogoSeleccionado);
			listaCatalogo=managerCatalogo.findAllCatalogo();
			listaMostrar=this.actionListenerListaProductosMostrar();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaCatalogo=managerCatalogo.findAllCatalogo();
			listaMostrar=this.actionListenerListaProductosMostrar();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionListenerProductoDetalle(ProCatalogo cat){
		this.catalogoSeleccionado = cat;
		return "/Usuario_final/product_detail";
	}
	
	public String actionListenerProductoDetalle_IniciadoSesion(ProCatalogo cat){
		this.catalogoSeleccionado = cat;
		return "/Usuario_final/product_detail_IniciadoSesion";
	}

	public List<ProCatalogo> actionListenerListaProductosMostrar() {
		List<ProCatalogo> lista = new ArrayList<ProCatalogo>();
		for (ProCatalogo c : listaCatalogo) {
			if (c.getMostrar()==true) {
				lista.add(c);
			}
		}
		return lista;
	}


	public List<ProCatalogo> getListaCatalogo() {
		return listaCatalogo;
	}



	public void setListaCatalogo(List<ProCatalogo> listaCatalogo) {
		this.listaCatalogo = listaCatalogo;
	}



	public ProCatalogo getCatalogo() {
		return catalogo;
	}



	public void setCatalogo(ProCatalogo catalogo) {
		this.catalogo = catalogo;
	}



	public ProCatalogo getCatalogoSeleccionado() {
		return catalogoSeleccionado;
	}



	public void setCatalogoSeleccionado(ProCatalogo catalogoSeleccionado) {
		this.catalogoSeleccionado = catalogoSeleccionado;
	}



	public List<ProCatalogo> getListaMostrar() {
		return listaMostrar;
	}



	public void setListaMostrar(List<ProCatalogo> listaMostrar) {
		this.listaMostrar = listaMostrar;
	}

	




	
	
	

}
