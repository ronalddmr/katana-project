package katana.model.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import katana.model.entities.PedDivpolitica;
import katana.model.entities.ProColor;
import katana.model.entities.ProTalla;
import katana.model.entities.ProTipoProducto;


/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerDPA {
	@PersistenceContext
	private EntityManager em;

    public ManagerDPA() {
        // TODO Auto-generated constructor stub
    }
    
    public List<PedDivpolitica> findAllProvincias()
    {
    	String consulta = "SELECT p FROM PedDivpolitica p WHERE p.nivel = '1'";
    	Query q  = em.createQuery(consulta, PedDivpolitica.class);
    	return q.getResultList();
    }
    
    public List<PedDivpolitica> findAllCantones()
    {
    	String consulta = "SELECT p FROM PedDivpolitica p WHERE p.nivel = '2'";
    	Query q = em.createQuery(consulta, PedDivpolitica.class);
    	return q.getResultList();
    }

    public List<PedDivpolitica> findCantonesProvincia(List<PedDivpolitica> cantones, String idProvincia)
    {
    	List<PedDivpolitica> cantonesProvincias = new ArrayList<>();
    	
    	for (int i = 0; i < cantones.size(); i++)
    	{
    		if (cantones.get(i).getIdDivpolitica().substring(0, 2).equals(idProvincia))
    		{
				cantonesProvincias.add(cantones.get(i));
			}
		}
    	
    	return cantonesProvincias;
    }

    public PedDivpolitica findDPA(String iddpa)
    {
    	PedDivpolitica provincia = em.find(PedDivpolitica.class, iddpa);
    	return provincia;
    }
}
