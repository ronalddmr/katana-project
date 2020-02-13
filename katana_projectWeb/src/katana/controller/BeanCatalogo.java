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
	private List<ProCatalogo> listaProductosVendidos;

	@PostConstruct
	public void inicializar() 
	{
		listaProductosVendidos=new ArrayList<>();
	    listaCatalogo=managerCatalogo.findAllCatalogo();
	    listaMostrar=this.actionListenerListaProductosMostrar();
	    catalogo=new ProCatalogo();
	    if(listaCatalogo.size()<4) 
	    {
	    	for(int i=0; i<listaCatalogo.size();i++) 
	    	{
	    		listaProductosVendidos.add(listaCatalogo.get(i));
	    	}
	    }else 
	    {
	    	listaProductosVendidos.add(listaCatalogo.get(0));
	    	listaProductosVendidos.add(listaCatalogo.get(1));
	    	listaProductosVendidos.add(listaCatalogo.get(2));
	    }
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
		return "/Usuario_final/product_detail?faces-redirect=true";
	}
	
	public String actionListenerProductoDetalle_IniciadoSesion(ProCatalogo cat){
		this.catalogoSeleccionado = cat;
		return "/Usuario_final/product_detail_IniciadoSesion?faces-redirect=true";
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



	public List<ProCatalogo> getListaProductosVendidos() {
		return listaProductosVendidos;
	}



	public void setListaProductosVendidos(List<ProCatalogo> listaProductosVendidos) {
		this.listaProductosVendidos = listaProductosVendidos;
	}

	




	
	
	

}
