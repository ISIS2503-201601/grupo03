package logica.interfaces;

import dto.Sensor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jf.ceron10
 */
@Local
public interface IServicioMonitoreoLocal {
    public void actualizarSensor(Sensor s);
    public List<Sensor> getSensores();
}
