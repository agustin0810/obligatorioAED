import java.util.ArrayList;

public class Empresa {
    private int idEmpresa;
    private String nombreEmpresa;
    private ArrayList<Animal> animales = new ArrayList<Animal>();
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    
    public ArrayList<Animal> getAnimales() {
        return animales;
    }
    public void setAnimales(ArrayList<Animal> animales) {
        this.animales = animales;
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public int getIdEmpresa() {
        return idEmpresa;
    }
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }
    public Empresa(int idEmpresa, String nombreEmpresa) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
    }
    public Empresa(){
        
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.idEmpresa+", "+this.nombreEmpresa;
    }
}
