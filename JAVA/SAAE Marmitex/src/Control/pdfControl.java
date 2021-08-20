package Control;

//baixar a lib iText e importar no projeto

import Model.Pedido;
import Model.Restaurante;
import Model.Usuario;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.text.Element;


public class pdfControl {
    
    Pedido pedido;
    Restaurante restaurante;
    Usuario usuario;
    
    public pdfControl(){
        
    }
    public pdfControl(Pedido pedido, Restaurante restaurante, Usuario usuario){
        this.pedido = pedido;
        this.restaurante = restaurante;
        this.usuario = usuario;
      
    }
    public boolean CriarDocumentoPDF(Pedido pedido, Restaurante restaurante, Usuario usuario){
        Document documento = new Document();
        try{
            String descricao = pedido.getDescricao().trim();
            
            long linhas = descricao.lines().count();
            
            String fileString = "src/Dados/requisicao.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(fileString));
            documento.open();
            Image image = Image.getInstance("src/imagens/logo.jpg");
            image.scaleToFit(500, 150);
            documento.add(image);
            
            Font T1 = FontFactory.getFont(FontFactory.HELVETICA, 15f, Font.BOLD, BaseColor.BLACK);
            Font T2 = FontFactory.getFont(FontFactory.HELVETICA, 13f, Font.BOLD, BaseColor.BLACK);
            Font T3 = FontFactory.getFont(FontFactory.HELVETICA, 8f, Font.BOLD, new BaseColor(5, 161, 222));
            Font T4 = FontFactory.getFont(FontFactory.HELVETICA, 8f, Font.BOLD, BaseColor.BLACK);
            


            //Paragraph titulo = new Paragraph("Requisição de Marmitex\n");
            //titulo.add(new Chunk("T1",T1));
            Paragraph titulo = new Paragraph(new Chunk("SAAE - Serviço Autônomo de Agua e Esgotos de Mogi Mirim\n" +
                                  "Rua Dr. Arthur Candido de Almeida n.º 114 - Centro | CEP: 13800-309 - Mogi Mirim / SP\n" +
                                                "Tel. (19) 3805-9900 | Fax (19) 3862-4489 | 08000-165195\n",T3 ));
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            
            titulo = new Paragraph(new Chunk("Requisição de Marmitex\n", T1));
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            titulo = new Paragraph("Nº "+pedido.getId()+"\n");
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph(""));
            documento.add(new Paragraph("Ao "+restaurante.getNome()+","));
            documento.add(new Paragraph("Favor fornecer "+pedido.getQuantidade()+" refeiç"+ (pedido.getQuantidade()>1?"ões":"ão")
                                                + " e "+pedido.getQuantidade()+" refrigerante"+(pedido.getQuantidade()>1?"s":"")+" em lata para o SAAE Mogi Mirim.\n"));
            titulo = new Paragraph(new Chunk("Relação de Funcionários\n\n",T2));
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);
            
            if(linhas<=24){
                    documento.add(new Paragraph(descricao));
            }else{
                PdfPTable tabela = new PdfPTable(2);
                tabela.setWidthPercentage(100f);
                Object[] lista = descricao.lines().toArray();
                //descricao.lines().forEach(linha -> {});
                String a;
                for (int i = 0; i < lista.length; i++){
                    a = lista[i].toString();
                    PdfPCell cell =new PdfPCell(new Paragraph(new Chunk(a, T4)));
                    cell.setBorderColor(BaseColor.WHITE);
                    tabela.addCell(cell);
                    
                }
                PdfPCell cell =new PdfPCell();
                cell.setBorderColor(BaseColor.WHITE);
                tabela.addCell(cell);
                documento.add(tabela);
            }
            int l =  24 - Integer.parseInt(linhas+"");
            for (int i = l; i>0; i--){
                documento.add(new Paragraph("\n"));
            }
            
            documento.add(new Paragraph("Portaria: "+usuario.getNome()+"\n"));
            //documento.add(new Paragraph("Data: "+pedido.getDataString()+"\n\n"));
            documento.add(new Paragraph("Data: "+pedido.getDataHoraString()+"\n\n"));
            
            titulo = new Paragraph("Emitente: ____________________________________________________");
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            documento.add(titulo);

            

            /*
            PdfPTable tabela = new PdfPTable(new float[]{10f,5f,3f});
            PdfPCell celula = new PdfPCell(new Phrase("Nome"));
            PdfPCell celula1 = new PdfPCell(new Phrase("Nome1"));
            PdfPCell celula2 = new PdfPCell(new Phrase("Nome2"));
            tabela.addCell(celula);
            tabela.addCell(celula1);
            tabela.addCell(celula2);
            documento.add(tabela);
            */
            documento.close();
            Desktop.getDesktop().open(new File(fileString));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"falha ao imprimir o arquivo. Detalhes: "+e.getMessage()+"\nCausa: "+e.getCause());
        }
        
        return true;
    }
    
    
    
    
    
}
