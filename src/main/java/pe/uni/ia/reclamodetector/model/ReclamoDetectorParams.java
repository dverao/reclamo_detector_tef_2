package pe.uni.ia.reclamodetector.model;

public class ReclamoDetectorParams {

    String method;
    Integer codcliente;
    Integer edad;
    Integer antiguedad;
    Integer incNroMes;
    Integer incNroDia;
    Integer motivoInc;
    Integer codProd;
    Integer codPlan;
    Integer codGenero;
    String reclamo;
	public ReclamoDetectorParams() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	  public ReclamoDetectorParams(String method, Integer codcliente, Integer edad, Integer antiguedad, Integer incNroMes,
			Integer incNroDia, Integer motivoInc, Integer codProd, Integer codPlan, Integer codGenero, String reclamo) {
		super();
		this.method = method;
		this.codcliente = codcliente;
		this.edad = edad;
		this.antiguedad = antiguedad;
		this.incNroMes = incNroMes;
		this.incNroDia = incNroDia;
		this.motivoInc = motivoInc;
		this.codProd = codProd;
		this.codPlan = codPlan;
		this.codGenero = codGenero;
		this.reclamo = reclamo;
	}


	public String getMethod() {
		return method;
	}



	public void setMethod(String method) {
		this.method = method;
	}



	public Integer getCodcliente() {
		return codcliente;
	}



	public void setCodcliente(Integer codcliente) {
		this.codcliente = codcliente;
	}



	public Integer getEdad() {
		return edad;
	}



	public void setEdad(Integer edad) {
		this.edad = edad;
	}



	public Integer getAntiguedad() {
		return antiguedad;
	}



	public void setAntiguedad(Integer antiguedad) {
		this.antiguedad = antiguedad;
	}



	public Integer getIncNroMes() {
		return incNroMes;
	}



	public void setIncNroMes(Integer incNroMes) {
		this.incNroMes = incNroMes;
	}



	public Integer getIncNroDia() {
		return incNroDia;
	}



	public void setIncNroDia(Integer incNroDia) {
		this.incNroDia = incNroDia;
	}



	public Integer getMotivoInc() {
		return motivoInc;
	}



	public void setMotivoInc(Integer motivoInc) {
		this.motivoInc = motivoInc;
	}



	public Integer getCodProd() {
		return codProd;
	}



	public void setCodProd(Integer codProd) {
		this.codProd = codProd;
	}



	public Integer getCodPlan() {
		return codPlan;
	}



	public void setCodPlan(Integer codPlan) {
		this.codPlan = codPlan;
	}



	public Integer getCodGenero() {
		return codGenero;
	}



	public void setCodGenero(Integer codGenero) {
		this.codGenero = codGenero;
	}



	public String getReclamo() {
		return reclamo;
	}



	public void setReclamo(String reclamo) {
		this.reclamo = reclamo;
	}



	@Override
    public String toString() {
        return "1," + edad +
                "," + antiguedad +
                "," + incNroMes +
                "," + incNroDia +
                "," + motivoInc +
                "," + codProd +
                "," + codPlan +
                "," + codGenero +
                ",?";
    }
}
