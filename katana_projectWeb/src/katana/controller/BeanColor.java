package katana.controller;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import katana.controller.JSFUtil;
import katana.model.entities.ProColor;
import katana.model.manager.ManagerColor;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanColor implements Serializable{
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerColor managerColor;
	private List<ProColor> listaColor;
	private ProColor color;
	private boolean panelColapsado_Color;
	private ProColor colorSeleccionado;

	@PostConstruct
	public void inicializar() 
	{
	
	    listaColor=managerColor.findAllColor();
	    color=new ProColor();
	    panelColapsado_Color=true;
	}
	
	
	
/*BEAN PARA pro_color*/
	
	public void actionListenerColapsarPanel_Color() {
		panelColapsado_Color=!panelColapsado_Color;
	}
	public void actionListenerInsertarColor() {
		try {
			managerColor.insertarColor(color);
			listaColor=managerColor.findAllColor();
			color=new ProColor();
			JSFUtil.crearMensajeInfo("Se ha insertado el Color");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerSeleccionarColor(ProColor Color) {
		colorSeleccionado=Color;
	}
	public void actionListenerActualizarColor() {
		try {
			managerColor.actualizarColor(colorSeleccionado);
			listaColor=managerColor.findAllColor();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			listaColor=managerColor.findAllColor();
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	public void actionListenerEliminarColor(int id) {
		managerColor.eliminarColor(id);
		listaColor=managerColor.findAllColor();
		JSFUtil.crearMensajeInfo("Color eliminado");
	}

	public List<ProColor> getListaColor() {
		return listaColor;
	}

	public void setListaColor(List<ProColor> listaColor) {
		this.listaColor = listaColor;
	}

	public ProColor getColor() {
		return color;
	}

	public void setColor(ProColor color) {
		this.color = color;
	}

	public boolean isPanelColapsado_Color() {
		return panelColapsado_Color;
	}

	public void setPanelColapsado_Color(boolean panelColapsado_Color) {
		this.panelColapsado_Color = panelColapsado_Color;
	}

	public ProColor getColorSeleccionado() {
		return colorSeleccionado;
	}

	public void setColorSeleccionado(ProColor colorSeleccionado) {
		this.colorSeleccionado = colorSeleccionado;
	}
	

}
