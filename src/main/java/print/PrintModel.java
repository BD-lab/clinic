package print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterAbortException;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

public abstract class PrintModel {
    
    // Metody zwracające kod HTML i CSS
    protected abstract String getHtml();    
    
    
    private class PrintOut implements Printable{
        JEditorPane editorPane;

        public PrintOut(JEditorPane editorPane) {
            this.editorPane = editorPane;
        }

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            Graphics2D graphics2d = (Graphics2D) graphics;
            graphics2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            double sx = pageFormat.getImageableWidth() / editorPane.getWidth();
            double sy = pageFormat.getImageableHeight() / editorPane.getHeight();
            graphics2d.scale(sx, sy);
            editorPane.print(graphics2d);
            
            return 0;
        }
    }
    
    
    // Metoda drukująca
    public void print(){
        JEditorPane printPane = new JEditorPane();
        printPane.setBounds(new Rectangle(2100,2970));
        printPane.setContentType("text/html");
        
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        htmlEditorKit.setStyleSheet(new PrintStyleSheet());
        Document document = htmlEditorKit.createDefaultDocument();

        printPane.setDocument(document);
        printPane.setText(getHtml());   
        
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        Book book = new Book();
        book.append(new PrintOut(printPane), new PageFormat());
        Boolean ifPrint = printerJob.printDialog(new HashPrintRequestAttributeSet());
        if(ifPrint) {
            printerJob.setPageable(book);
            try {
                printerJob.print(new HashPrintRequestAttributeSet());
                JOptionPane.showMessageDialog(null,"Wydruk zrealizowany");
            } catch (PrinterAbortException e) {
                JOptionPane.showMessageDialog(null,"Wydruk anulowany");
            } catch (PrinterException e) {
                System.out.println(e.getMessage());
            }  
        }
    }
}
