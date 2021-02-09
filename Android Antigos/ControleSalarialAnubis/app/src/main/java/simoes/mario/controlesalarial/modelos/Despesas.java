package simoes.mario.controlesalarial.modelos;

public class Despesas {
	
	private String id;
	private String nome;
	private double valor;
	private String id_mes;

	public Despesas(String _id, String _nome, double _valor, String _id_mes){
		this.id = _id;
		this.nome = _nome;
		this.valor= _valor;
		this.id_mes = _id_mes;
		
	}
	public Despesas(String _nome, double _valor, String _id_mes){
		this.nome = _nome;
		this.valor= _valor;
		this.id_mes = _id_mes;
		
	}
	public Despesas(){
		this.id="";
		this.nome="";
		this.valor=0.00;
		this.id_mes="";
		
	}
	public String getDescricao(){
		String s = "";
		s = "DESPESAS -----ID =" + getId() +", Nome =" + getNome() +", valor = " +getValor() + ", id_mes =" + getId_mes();
		return s;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getId_mes() {
		return id_mes;
	}
	public void setId_mes(String id_mes) {
		this.id_mes = id_mes;
	}
	
	
	
	
}
