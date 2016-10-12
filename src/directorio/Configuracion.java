package directorio;

public interface Configuracion {
    
    //Activa el modo debug.
    public final boolean _DEBUG_MODE = true;
    
    //Se determina cual Look & Feel se utilizará.
    public final int _LF_DEFAULT = 0;
    public final int _LF_NIMBUS = 1;
    public final int _LOOK_AND_FEEL = _LF_NIMBUS;
}
