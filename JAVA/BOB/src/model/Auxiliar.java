
package model;

import Exception.ValorInvalidoException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JOptionPane;
import view.jfPrincipal;


//Classe Auxiliar para trabalhar com formatação de Datas, Recebendo um valor de time em milisegundos
public class Auxiliar {
    private static final String  SEM_PERMISSAO = "Usuário sem nível de permissão necessário";
    private static final String CONTROLE_ACESSO = "Controle de Acesso do Sistema";
    private static final String VALORES_INVALIDOS = "Verifique os campos, existe(m) valor(es) inválido(s)!";
    private static final String VALIDACAO = "Validação de informações";
    private static final String SUCESSO = "Salvo com sucesso";
    private static final String FALHA = "Falha ao salvar";
    private static final String INFORMEDATA = "Informe uma data válida! (ex: 30/01/2021)";
    private static final String ID_EXCLUSAO = "Informe o valor do ID para confirmar a exclusão:";
   
    
    Calendar c;
    
    public Auxiliar(){
        c = new GregorianCalendar();
    }
    public Auxiliar(Long timeMilis){
        c = new GregorianCalendar();
        c.setTimeInMillis(timeMilis);
    }
    public Date getDate(){
        return c.getTime();
    }
    public Long getDataMilis(){
        return c.getTimeInMillis();
    }
    public Long getDataMilisAtual(){
        c = new GregorianCalendar();
        return getDataMilis();
    }
    public String getDataString(Long datamilis){
        c.setTimeInMillis(datamilis);
        return getDataString();
    }
    public String getDataString(){
        String diaS="", mesS="", anoS;
        diaS += getDia()<10? "0"+getDia(): getDia();
        mesS += getMes()<9? "0"+(getMes()+1): getMes()+1;
        
        return diaS+"/"+mesS+"/"+getAno();
    }
    public String getDataStringAtual(){
        return getDataString(getDataMilisAtual());
    }
    public int getDia(){
        return c.get(Calendar.DAY_OF_MONTH);
    }
    public int getMes(){
        return c.get(Calendar.MONTH);
    }
    public int getAno(){
        return c.get(Calendar.YEAR);
    }
    public void showMessage(String msg, String titulo, int tipo){
        JOptionPane.showMessageDialog(null,msg,titulo, tipo);
        System.out.println("Titulo: "+ titulo +"\nMensagem: "+msg);
        
    }
    public void showMessageInformacao(String msg, String titulo){
        JOptionPane.showMessageDialog(null,msg,titulo, JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Titulo: "+ titulo +"\nMensagem: "+msg);
    }
    public void showMessageWarning(String msg, String titulo){
        JOptionPane.showMessageDialog(null,msg,titulo, JOptionPane.WARNING_MESSAGE);
        System.out.println("Titulo: "+ titulo +"\nMensagem: "+msg);
    }
    public void showMessageValoresInvalidos(){
        showMessageWarning(VALORES_INVALIDOS, VALIDACAO);
    }
    public String diffDataString(Long atual, Long antiga){
        Auxiliar a1, a2;
        a1 = new Auxiliar(atual);
        a2 = new Auxiliar(antiga);
        
        //LocalDate ld1 = LocalDate.of(2021,1,2);
        //LocalDate ld2 = LocalDate.of(2019,05,30);
        
        LocalDate ld1 = LocalDate.of(a1.getAno(), a1.getMes()+1, a1.getDia());
        LocalDate ld2 = LocalDate.of(a2.getAno(), a2.getMes()+1, a2.getDia());
        
        
        Period p = Period.between(ld2, ld1);
        int ano =p.getYears(), mes = p.getMonths(), dia = p.getDays();
        String resultado = "";
        resultado += (ano > 0) ? (ano+" ano(s) ") : "";
        resultado += (mes > 0) ? (mes+" mes(es) ") : "";
        resultado += (dia > 0) ? (dia+" dia(s)") : "";
        
        return resultado;
    }
    public int diffDiasPassados (long maior, long menor){
        Auxiliar a1, a2;
        try{
            a1 = new Auxiliar(maior);
            a2 = new Auxiliar(menor);

            LocalDate ld1 = LocalDate.of(a1.getAno(), a1.getMes()+1, a1.getDia());
            LocalDate ld2 = LocalDate.of(a2.getAno(), a2.getMes()+1, a2.getDia());
            Period p = Period.between(ld2, ld1);
            
            int ano =p.getYears(), mes = p.getMonths(), dia = p.getDays();
            int resultado = 0;
            resultado += (ano > 0) ? (ano * 365) : 0;
            resultado += (mes > 0) ? (mes * 30) : 0;
            resultado += (dia > 0) ? (dia) : 0;
            resultado = resultado <=0 ? 1: resultado;
            return resultado;
        }catch(Exception e){
            return 0;
        }
    }
    public long adicionarDias (long dataAtual, int diasProxima){
        try{
           // c.add(Calendar.DATE, diasProxima);
           c.setTimeInMillis(dataAtual);
           c.add(Calendar.DATE, diasProxima);
        }catch(Exception e){
            RegistrarLog(e.getMessage(), "AcionarDias");
        }
        return c.getTimeInMillis();
    }
    public long dataMilisMax(long data){
        c.setTimeInMillis(data);
        c.set(getAno(), getMes(), getDia(), 23, 59, 59);
        return c.getTimeInMillis();
    }
    public long dataMilisMin(long data){
        c.setTimeInMillis(data);
        c.set(getAno(), getMes(), getDia(), 0, 0, 0);
        return c.getTimeInMillis();
    }
    
    public String getDataAtualMenosPeriodo(int anos){
        c.set(getAno()-anos, getMes(), getDia());
        return getDataString();
    }
    
    public String diffDataAtualString(Long antiga){
        return diffDataString(new GregorianCalendar().getTimeInMillis(), antiga);
    }
    public void RegistrarLog(String mensagem, String origem){
        System.out.println("Origem: "+origem+"\nMensagem: "+mensagem);
    }
    
    public String StringFloatReais(float numero){
        String retorno = "";
        DecimalFormat df = new DecimalFormat("0.00");
        try{
            retorno = "R$ " + df.format(numero);
        }catch(Exception e){
            System.err.println("Erro ao formatar número: "+numero+ e.getMessage());
        }
        return retorno;
    }
    public String CustoKMString(int km, float custo){
        float valor=0;
        try{
            km = (km<=0) ? 1: km;
            valor = custo/km;
            
        }catch(Exception e){
            valor = 0;
        }
        return StringFloatReais(valor) + " / Km";
    }
    public float CustoKmFloat(int km, float custo){
         float valor=0;
        try{
            km = (km<=0) ? 1: km;
            valor = custo/km;
            
        }catch(Exception e){
            valor = 0;
        }
        return valor;
    }
    public int diferencaKM(int kmMenor, int kmMaior){
        int kmdiff = kmMaior - kmMenor;
        kmdiff = (kmdiff<=0)? 1 : kmdiff;
        return kmdiff;
    }
    public boolean ConfirmarValor(String titulo, String mensagem){
        int retorno;
        retorno = JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION);
        return (retorno==JOptionPane.YES_OPTION? true: false);
    }
    
    public String InputText(String mensagem){
        String retorno = "";
        try{
            retorno = JOptionPane.showInputDialog(null, mensagem, "Preencha a informação", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            RegistrarLog(e.getMessage(), "Auxiliar.InputText");
            retorno = "";
        }finally{
            return retorno;
        }
    }
    public void showMessageConfirmacao(boolean status, String titulo){
        showMessageInformacao(status? SUCESSO: FALHA, titulo);
    }
    
    public long dataStringLong(String dataString)throws Exception{
        if(!dataString.equalsIgnoreCase("  /  /    ")){
          String data[]= dataString.split("/");
          if(data.length==3){
              c = new GregorianCalendar();
              c.set(Integer.parseInt(data[2]), Integer.parseInt(data[1])-1, Integer.parseInt(data[0]));
          }
          return c.getTimeInMillis();
        }  
        return 0;
    }
    public String numeroFormatadoDuasCasasString(float numero){
        
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
       String numeroF = df.format(numero);
       return numeroF;
    }
    /*
    //precisa receber uma string da data no formato dd/mm/aaaa
    public long dataStringLong(String dataString)throws Exception{
            
            int dI[] = new int[3];
            
            if(!dataString.equals("  /  /    ")){
                dI[0]= Integer.parseInt(dataString.substring(0, 2));
                dI[1]= Integer.parseInt(dataString.substring(3, 5));
                dI[2]= Integer.parseInt(dataString.substring(6));
                c = new GregorianCalendar();
                c.set(dI[2], dI[1]-1, dI[0]);
                
                return c.getTimeInMillis();
            }
        return 0; 
    }
    */
    public float StringToFloat(String valor)throws Exception{
        valor = valor.replaceAll(",", ".");
        return Float.parseFloat(valor);
    }

    public void showMessagemSemPermissao() {
        showMessageInformacao( SEM_PERMISSAO+" \nNivel: " +Usuario.TIPOS[jfPrincipal.getNivelUsuario()], CONTROLE_ACESSO );
    }

    public void showMessageDataInvalida() {
        showMessageWarning(INFORMEDATA, VALIDACAO);
    }

    public boolean validarData(String text) {
        try{
            String dia = text.substring(0, 2);
            String mes = text.substring(3, 5);
            String ano = text.substring(6);

            Calendar cl = new GregorianCalendar();
            cl.setLenient(false);
            cl.set(Integer.parseInt(ano), Integer.parseInt(mes)-1, Integer.parseInt(dia));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public String InputTextIDExclusao() {
        return InputText(ID_EXCLUSAO);
    }
    

}
