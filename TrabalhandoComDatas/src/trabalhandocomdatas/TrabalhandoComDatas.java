package trabalhandocomdatas;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TrabalhandoComDatas {
    
    public static void main(String[] args) { //Primeiro método chamado - Padrão JAVA
        
        TrabalhandoComDatas tdadas = new TrabalhandoComDatas(); //Criando uma instância da classe com o construtor padrão
        tdadas.ImprimirNoConsole(tdadas.getDataString()); // Mostrar Data Atual
        tdadas.ImprimirNoConsole(tdadas.getHorarioString()); //MostrarHora
        tdadas.setData(30, 1, 2021); //Setando uma data
        tdadas.setHorario(15, 33); //setando um horário
        tdadas.ImprimirNoConsole("Salvar no Banco Dados: "+tdadas.getTimeMilisegundos()); //Sugestão de formato para salvar no BD
        
    }
    
    private int dia, mes, ano, hora, minuto; 
    Calendar calendar; //Classe para trabalhar com datas e horários
    
    public TrabalhandoComDatas(){ //Construtor da Classe (Padrão)
        calendar = new GregorianCalendar(); //Instanciando um Calendar
    }
    public void ImprimirNoConsole(String mensagem){ // Método auxiliar para mostrar mensagens no console
        System.out.println(mensagem);
    }
    public String getDataString(){ // Formatação de String para Datas
        return getDia()+"/"+getMes()+"/"+getAno();
    }
    public String getHorarioString(){ //Formatação de String para Horário
        return getHora()+":"+getMinuto();
    }
    public void setData(int dia, int mes, int ano){ //Método para definir Data
        setDia(dia);
        setMes(mes);
        setAno(ano);
        ImprimirNoConsole("Nova Data definida: "+ getDataString());
    }
    public void setHorario(int hora, int minuto){  //Método para definir horário
        setHora(hora);
        setMinuto(minuto);
        ImprimirNoConsole("Novo horário definido: "+ getHorarioString());
    }
    public void setDataMilisegundos(long timemilis){ //Sugestão para salvar as datas em Milisegundos na base de dados
        calendar.setTimeInMillis(timemilis);
    }
    public long getTimeMilisegundos(){
        return calendar.getTimeInMillis(); //Obtem dados complementos em long (dia, mes, ano, hora, minuto e segundos) útil para salvar no base de dados
    }

    public int getDia() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setDia(int dia) {
        calendar.set(Calendar.DAY_OF_MONTH, dia);
    }

    public int getMes() {
        return calendar.get(Calendar.MONTH)+1; //Motivo do +1:    Mes Janeiro = 0, Fevereiro = 1 ... Dezembro = 11
    }

    public void setMes(int mes) {
        calendar.set(Calendar.MONTH, mes-1); //Motivo do -1: Mes Janeiro = 0, Fevereiro = 1 ... Dezembro = 11
    }

    public int getAno() {
        return calendar.get(Calendar.YEAR);
    }

    public void setAno(int ano) {
        calendar.set(Calendar.YEAR, ano);
    }

    public int getHora() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public void setHora(int hora) {
        calendar.set(Calendar.HOUR_OF_DAY, hora);
    }

    public int getMinuto() {
        return calendar.get(Calendar.MINUTE);
    }

    public void setMinuto(int minuto) {
        calendar.set(Calendar.MINUTE, minuto);
    }

}
