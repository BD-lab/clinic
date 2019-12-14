
package testprint;

import java.sql.Timestamp;
import java.util.ArrayList;

public class PrintResults extends PrintModel {
    
    private static OrderResultDTO order;
    
    public PrintResults(OrderResultDTO order){
        this.order = order;
    }

    @Override
    protected String getHtml() {
        StringBuilder html = new StringBuilder("<html><head></head><body><div id=\"contentDiv\">");
        
        html.append(getHeaderDiv());
        html.append(getResultsHeader());        
        html.append(getResults());
        html.append(getFooter());
        
        
        System.out.println(html);
        return html.append("</div></body></html>").toString();
    }
    
    
    private StringBuilder getFooter(){
        StringBuilder ret = new StringBuilder("<div id=\"footer\">");
            ret.append("<br>Data wydruku: ").append(getCurrentDate()).append("<br><br>Wykonał: Adam Nowak");
        return ret.append("</div>");   
    }
    
    
    private StringBuilder getResults(){
        StringBuilder ret = new StringBuilder("<table>");
        
        for(ExaminationResultDTO e : order.getExaminations()){
            ret.append("<tr style=\"border-bottom-style:solid;border-bottom-color:black;border-bottom-width:2px;\">");
                ret.append("<td><div id=\"result1\">");   
                ret.append("<b>").append(e.getExaminationType()).append("</b>");
                ret.append("</td></div>");

                ret.append("<td><div id=\"result2\">");   
                ret.append("<b>").append(e.getPatientValue()).append("</b>");
                ret.append("</td></div>");

                ret.append("<td><div id=\"result3\">");   
                ret.append(e.getUnit());
                ret.append("</td></div>");

                ret.append("<td><div id=\"result4\">");   
                ret.append((e.getPatientValue() >= e.getMinNormValue() && e.getPatientValue() <= e.getMaxNormValue())? "+":"-");
                ret.append("</td></div>");

                ret.append("<td><div id=\"result5\">");   
                ret.append(e.getMinNormValue()).append(" - ").append(e.getMaxNormValue());
                ret.append("</td></div>");
            ret.append("</tr>");  
        }
        

        return ret.append("</table>");   
    }
    
    private StringBuilder getResultsHeader(){
        StringBuilder ret = new StringBuilder("<table style=\"border-bottom-style:solid;border-bottom-color:black;border-bottom-width:10px;\">");
        
        ret.append("<tr><td><div id=\"resultsHead1\">");   
        ret.append("<b>Badanie</b>");
        ret.append("</td></div>");
        
        ret.append("<td><div id=\"resultsHead2\">");   
        ret.append("<b>Wynik</b>");
        ret.append("</td></div>");
        
        ret.append("<td><div id=\"resultsHead3\">");   
        ret.append("Jedn.");
        ret.append("</td></div>");
        
        ret.append("<td><div id=\"resultsHead4\">");   
        ret.append("Norma");
        ret.append("</td></div>");
        
        ret.append("<td><div id=\"resultsHead5\">");   
        ret.append("Zakresy ref.");
        ret.append("</td></div>");
        
        return ret.append("</tr></table>");
    }

    private StringBuilder getHeaderDiv(){
        StringBuilder ret = new StringBuilder("<table style=\"border-bottom-style:solid;border-bottom-color:black;border-bottom-width:10px;\">");
        String nameAndSurname = order.getPatientDTO().getFirstName() + " " + order.getPatientDTO().getLastName();
        String pesel = order.getPatientDTO().getPesel();
        String orderNumber = order.getOrderNumber();
        
        ret.append("<tr><td><div id=\"head1\">");        
            ret.append("<div id=\"head1a\">");
                ret.append("<br>Laboratorium<br>Analiz<br>Medycznych");
            ret.append("</div>");
            
            ret.append("<div id=\"head1b\">");
                ret.append("<br>ul. Kolorowa 5<br>61-000 Poznan<br>tel. 123 456 789");
            ret.append("<td></div>");    
            
        ret.append("</td></div>");
        
        
        ret.append("<td><div id=\"head2\">");
            ret.append("<br><br><br>Wyniki Badan");
        ret.append("</td></div>");
        
        
        ret.append(getClientDataHeaderDiv(nameAndSurname,pesel,orderNumber));
                
        return ret.append("</tr></table>");
    } 
    
    private StringBuilder getClientDataHeaderDiv(String nameAndSurname, String pesel, String orderNumber){
        StringBuilder ret = new StringBuilder();
        ret.append("<td><div id=\"head3\">");
        
            ret.append("<br><br>Zamawiajacy:<br>").append(nameAndSurname)
                    .append("<br><br>Pesel:<br>").append(pesel)
                    .append("<br><br>Nr zamowienia:<br>").append(orderNumber)
                    .append("<br><br>Data odbioru:<br>").append(getCurrentDate())
                    .append("<br><br>");
            
        ret.append("</td><td><div style=\"width:100px;\"></div></td></div>");
        return ret;
    }
    
    private StringBuilder getCurrentDate(){
        StringBuilder ret = new StringBuilder();
        
        Timestamp date = new Timestamp(System.currentTimeMillis());
        String day = Integer.toString(date.getDate());
        if(day.length()==1) day = "0"+day;
        
        String month = Integer.toString(date.getMonth()+1);
        if(month.length()==1) month = "0"+month;

        return ret.append(day).append("-").append(month).append("-").append(date.getYear()+1900);
    }
}
