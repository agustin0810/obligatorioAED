import java.security.Timestamp;
import java.util.Date;

public class Animal {
    private int idAnimal;
    private char sexo;
    private boolean desparasitado;
    private boolean vacunado;
    private Date fechaDesparasitado;
    private Date fechaVacunado;
    private Animal padre;
    private Animal madre;
    
    public int getIdAnimal() {
        return idAnimal;
    }
    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
    public boolean isDesparasitado() {
        return desparasitado;
    }
    public void setDesparasitado(boolean desparasitado) {
        this.desparasitado = desparasitado;
    }
    public boolean isVacunado() {
        return vacunado;
    }
    public void setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
    }
    public Date getFechaDesparasitado() {
        return fechaDesparasitado;
    }
    public void setFechaDesparasitado(Date fechaDesparasitado) {
        this.fechaDesparasitado = fechaDesparasitado;
    }
    public Date getFechaVacunado() {
        return fechaVacunado;
    }
    public void setFechaVacunado(Date fechaVacunado) {
        this.fechaVacunado = fechaVacunado;
    }
    public Animal getPadre() {
        return padre;
    }
    public void setPadre(Animal padre) {
        this.padre = padre;
    }
    public Animal getMadre() {
        return madre;
    }
    public void setMadre(Animal madre) {
        this.madre = madre;
    }
    public Animal(int idAnimal, char sexo, boolean desparasitado, boolean vacunado, Date fechaDesparasitado,
            Date fechaVacunado, Animal padre, Animal madre) {
        this.idAnimal = idAnimal;
        this.sexo = sexo;
        this.desparasitado = desparasitado;
        this.vacunado = vacunado;
        this.fechaDesparasitado = fechaDesparasitado;
        this.fechaVacunado = fechaVacunado;
        this.padre = padre;
        this.madre = madre;
    }
    public Animal(int idAnimal, char sexo, boolean desparasitado, boolean vacunado, Animal padre, Animal madre) {
        this.idAnimal = idAnimal;
        this.sexo = sexo;
        this.desparasitado = desparasitado;
        this.vacunado = vacunado;
        this.padre = padre;
        this.madre = madre;
    }
    public Animal(){

    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.idAnimal +", "+ this.sexo + ", "+ this.desparasitado +", "+this.vacunado;
    }
}
