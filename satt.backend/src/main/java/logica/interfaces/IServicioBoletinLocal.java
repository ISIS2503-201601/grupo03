package logica.interfaces;

import dto.Boletin;
import dto.Evento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jf.ceron10
 */
@Local
public interface IServicioBoletinLocal {
    public Evento generarEvento(Evento e );
    public List<Boletin> getBoletines();
    public List<Evento> getEventos();
}