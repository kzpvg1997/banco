
package co.edu.eam.pa.interbancariows;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "InterbancarioWS", targetNamespace = "http://www.eam.edu.co/interbancario")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface InterbancarioWS {


    /**
     * 
     * @param numerodoc
     * @param idbanco
     * @param tipodoc
     * @param nombre
     * @param numerocuenta
     * @return
     *     returns co.edu.eam.pa.interbancariows.RespuestaServicio
     */
    @WebMethod
    @WebResult(name = "respuestaServicio", targetNamespace = "")
    @RequestWrapper(localName = "registrarCuentaAsociada", targetNamespace = "http://www.eam.edu.co/interbancario", className = "co.edu.eam.pa.interbancariows.RegistrarCuentaAsociada")
    @ResponseWrapper(localName = "registrarCuentaAsociadaResponse", targetNamespace = "http://www.eam.edu.co/interbancario", className = "co.edu.eam.pa.interbancariows.RegistrarCuentaAsociadaResponse")
    public RespuestaServicio registrarCuentaAsociada(
        @WebParam(name = "idbanco", targetNamespace = "")
        String idbanco,
        @WebParam(name = "tipodoc", targetNamespace = "")
        TipoDocumentoEnum tipodoc,
        @WebParam(name = "numerodoc", targetNamespace = "")
        String numerodoc,
        @WebParam(name = "nombre", targetNamespace = "")
        String nombre,
        @WebParam(name = "numerocuenta", targetNamespace = "")
        String numerocuenta);

    /**
     * 
     * @param monto
     * @param idbanco
     * @param numerocuenta
     * @return
     *     returns co.edu.eam.pa.interbancariows.RespuestaServicio
     */
    @WebMethod
    @WebResult(name = "respuestaServicio", targetNamespace = "")
    @RequestWrapper(localName = "transferirMonto", targetNamespace = "http://www.eam.edu.co/interbancario", className = "co.edu.eam.pa.interbancariows.TransferirMonto")
    @ResponseWrapper(localName = "transferirMontoResponse", targetNamespace = "http://www.eam.edu.co/interbancario", className = "co.edu.eam.pa.interbancariows.TransferirMontoResponse")
    public RespuestaServicio transferirMonto(
        @WebParam(name = "idbanco", targetNamespace = "")
        String idbanco,
        @WebParam(name = "numerocuenta", targetNamespace = "")
        String numerocuenta,
        @WebParam(name = "monto", targetNamespace = "")
        double monto);

    /**
     * 
     * @return
     *     returns java.util.List<co.edu.eam.pa.interbancariows.Banco>
     */
    @WebMethod
    @WebResult(name = "ListaBancos", targetNamespace = "")
    @RequestWrapper(localName = "listarBancos", targetNamespace = "http://www.eam.edu.co/interbancario", className = "co.edu.eam.pa.interbancariows.ListarBancos")
    @ResponseWrapper(localName = "listarBancosResponse", targetNamespace = "http://www.eam.edu.co/interbancario", className = "co.edu.eam.pa.interbancariows.ListarBancosResponse")
    public List<Banco> listarBancos();

}
