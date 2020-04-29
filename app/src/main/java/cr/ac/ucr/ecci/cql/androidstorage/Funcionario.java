package cr.ac.ucr.ecci.cql.androidstorage;

import android.os.Parcel;

import java.util.Date;

public class Funcionario extends Persona {

    private int unidadBase;
    private int puestoBase;
    private double salarioBase;

    public Funcionario(String identificacion, String correo, String nombre, String primerApellido,
                       String segundoApellido, String telefono, String celular, Date fechaNacimiento,
                       String tipo, String genero, int unidadBase,
                       int puestoBase, double salarioBase) {

        super(identificacion, correo, nombre, primerApellido, segundoApellido, telefono, celular, fechaNacimiento, tipo, genero);

        this.unidadBase = unidadBase;
        this.puestoBase = puestoBase;
        this.salarioBase = salarioBase;
    }

    public int getUnidadBase() {
        return unidadBase;
    }

    public void setUnidadBase(int unidadBase) {
        this.unidadBase = unidadBase;
    }

    public int getPuestoBase() {
        return puestoBase;
    }

    public void setPuestoBase(int puestoBase) {
        this.puestoBase = puestoBase;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Funcionario(Parcel in, int unidadBase, int puestoBase, double salarioBase) {
        super(in);
        this.unidadBase = unidadBase;
        this.puestoBase = puestoBase;
        this.salarioBase = salarioBase;
    }
}
