package simoes.mario.controlesalarial.modelos;

public class Salario {
	
	private String id;
	private int ano;
	private double valor;
	private int mes;
	
	public Salario(){
		this.id = "";
		this.ano = -1;
		this.mes = -1;
		this.valor = 0.00;
	}
	public Salario(String _id, int _ano, int _mes, double _valor){
		this.id = _id;
		this.ano = _ano;
		this.valor = _valor;
		this.mes = _mes;
	}
	public Salario(int _ano, int _mes, double _valor){
		this.ano = _ano;
		this.valor = _valor;
		this.mes = _mes;
	}
	
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getDescricaoSalario(){
		String s;
		s = "id = " + getId()+", Mes = " + getMes() + ", Ano = " + getAno() + ", valor = "+getValor();
		return s;
	}

	

}
