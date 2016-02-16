package logica.interfaces;

import dto.Boletin;
import dto.Evento;
import javax.ejb.Remote;

/**
 *
 * @author jf.ceron10
 */
@Remote
public interface IServicioBoletinRemote {
    public Boletin generarBoletin(Evento e );
}