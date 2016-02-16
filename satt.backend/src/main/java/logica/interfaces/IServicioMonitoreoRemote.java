package logica.interfaces;

import dto.Sensor;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author jf.ceron10
 */
@Remote
public interface IServicioMonitoreoRemote {
    public void actualizarSensor(Sensor s);
    public List<Sensor> getSensores();
}