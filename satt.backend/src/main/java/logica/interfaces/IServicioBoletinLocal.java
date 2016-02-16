package logica.interfaces;

import dto.Boletin;
import dto.Evento;
import javax.ejb.Local;

/**
 *
 * @author jf.ceron10
 */
@Local
public interface IServicioBoletinLocal {
    public Boletin generarBoletin(Evento e );
}