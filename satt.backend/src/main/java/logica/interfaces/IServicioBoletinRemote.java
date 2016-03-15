package logica.interfaces;

import dto.Boletin;
import dto.Evento;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jf.ceron10
 */
@Remote
public interface IServicioBoletinRemote {
    public Evento generarEvento(Evento e );
    public List<Boletin> getBoletines();
    public List<Evento> getEventos();
}