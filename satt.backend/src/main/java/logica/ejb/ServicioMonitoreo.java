/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.ejb;

import dto.Sensor;
import java.util.List;
import javax.ejb.Stateless;
import logica.interfaces.IServicioMonitoreoLocal;
import logica.interfaces.IServicioMonitoreoRemote;
import logica.interfaces.IServicioPersistenciaLocal;
import persistencia.ServicioPersistencia;

/**
 *
 * @author jf.ceron10
 */
@Stateless
public class ServicioMonitoreo implements IServicioMonitoreoLocal, IServicioMonitoreoRemote{

    public ServicioMonitoreo() {
        persistencia=new ServicioPersistencia();
    }

    private IServicioPersistenciaLocal persistencia;
    
    @Override
    public void actualizarSensor(Sensor s) {
        persistencia.update(s);
    }

    @Override
    public List<Sensor> getSensores() {
        return persistencia.findAll(Sensor.class);
    }
}