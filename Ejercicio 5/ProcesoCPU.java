// ProcesoCPU.java
// Clase que representa un proceso intensivo de CPU


public class ProcesoCPU extends Proceso
{
    private int operacionesCalculadas;
    private String tipoCalculo;
    
    public ProcesoCPU(int pid, String nombre, String tipoCalculo)
    {
        super(pid, nombre);
        this.tipoCalculo = tipoCalculo;
        this.operacionesCalculadas = 0;
    }
    
    public ProcesoCPU(String nombre, String tipoCalculo)
    {
        super(nombre);
        this.tipoCalculo = tipoCalculo;
        this.operacionesCalculadas = 0;
    }
    
    // Getters y Setters
    public int getOperacionesCalculadas()
    {
        return operacionesCalculadas;
    }
    
    public String getTipoCalculo()
    {
        return tipoCalculo;
    }
    
    public void setTipoCalculo(String tipoCalculo)
    {
        this.tipoCalculo = tipoCalculo;
    }
    
    @Override
    public void ejecutar()
    {
        operacionesCalculadas += (int)(Math.random() * 1000) + 500; // Simula cálculos intensivos
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " | Tipo: CPU | Cálculo: " + tipoCalculo + " | Operaciones: " + operacionesCalculadas;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof ProcesoCPU)) return false;
        ProcesoCPU otro = (ProcesoCPU) obj;
        return this.tipoCalculo.equals(otro.tipoCalculo);
    }
}