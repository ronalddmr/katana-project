package katana.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.AudEmpresa;
import katana.model.entities.PedDivpolitica;
import katana.model.entities.ProColor;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerEmpresa {
	@PersistenceContext
	private EntityManager em;


    /**
     * Default constructor. 
     */
    public ManagerEmpresa() {
        // TODO Auto-generated constructor stub
    }
    
    
    /**CRUD DE LA TALBLA pro_color*/
    public List<AudEmpresa> findAllEmpresa(){
    	String consulta="select o from AudEmpresa o";
    	Query q=em.createQuery(consulta, AudEmpresa.class);
    	return q.getResultList();
    }
    
    public AudEmpresa findEmpresaById(int id) {
    	return em.find(AudEmpresa.class, id);
    }
    public void insertarEmpresa(AudEmpresa empresa, PedDivpolitica locacion) throws Exception {
    	AudEmpresa save_empresa=new AudEmpresa();
    	save_empresa.setNombre(empresa.getNombre());
    	save_empresa.setDireccion(empresa.getDireccion());
    	save_empresa.setReferencia(empresa.getReferencia());
    	save_empresa.setPedDivpolitica(locacion);
    	save_empresa.setTelefono1(empresa.getTelefono1());
    	save_empresa.setTelefono2(empresa.getTelefono2());
    	save_empresa.setMision(empresa.getMision());
    	save_empresa.setVision(empresa.getVision());
        em.persist(save_empresa);
    }
    public void eliminarEmpresa(int id) {
    	AudEmpresa empresa=findEmpresaById(id);
    	if(empresa!=null)
    		em.remove(empresa);
    }
    public void actualizarEmpresa(AudEmpresa empresa) throws Exception {
    	AudEmpresa e=findEmpresaById(empresa.getIdEmpresa());
    	if(e==null)
    		throw new Exception("No existe esta Informacion");
    	e.setNombre(empresa.getNombre());
    	e.setDireccion(empresa.getDireccion());
    	e.setReferencia(empresa.getReferencia());
    	e.setPedDivpolitica(empresa.getPedDivpolitica());
    	e.setTelefono1(empresa.getTelefono1());
    	e.setTelefono2(empresa.getTelefono2());
    	e.setMision(empresa.getMision());
    	e.setVision(empresa.getVision());
    	
    	
    	em.merge(e);
    	
    }
    
    
}
