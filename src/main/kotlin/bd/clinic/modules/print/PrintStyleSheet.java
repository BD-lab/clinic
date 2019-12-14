
package testprint;

import javax.swing.text.html.StyleSheet;

public class PrintStyleSheet extends StyleSheet {
    
    public PrintStyleSheet(){
        addRule("body{width: 1600px; height: 2250px}");         
        addRule("#contentDiv{width: 1600px; height: 2250px;border-style:solid;border-color:black;border-width:1px;}");
        
        addRule("#head{height:600px;width:1600px;border-bottom-style:solid;border-bottom-color:black;border-bottom-width:10px;}");
            addRule("#head1{height:600px;width:400px;float:left;}");
                addRule("#head1a{height:300px;width:400px;font-size:60px;text-align:center;}");
                addRule("#head1b{height:180px;width:400px;font-size:30px;text-align:center;}");
            addRule("#head2{height:600px;width:800px;float:left;font-size:100px;text-align:center;}");
            addRule("#head3{height:600px;width:300px;float:left;font-size:30px;text-align:right;}");

        addRule("#resultsHead{height:100px;width:1600px;border-bottom-style:solid;border-bottom-color:black;border-bottom-width:10px;}");
            addRule("#resultsHead1{height:90px;width:600px;float:left;font-size:50px;text-align:center;padding-top:20px;}");
            addRule("#resultsHead2{height:90px;width:200px;float:left;font-size:50px;text-align:center;padding-top:20px;}");
            addRule("#resultsHead3{height:90px;width:200px;float:left;font-size:50px;text-align:center;padding-top:20px;}");
            addRule("#resultsHead4{height:90px;width:200px;float:left;font-size:50px;text-align:center;padding-top:20px;}");
            addRule("#resultsHead5{height:90px;width:400px;float:left;font-size:50px;text-align:center;padding-top:20px;}");

        addRule("#resultsContent{height:1300px;width:1600px;}");
            addRule("#result{height:78px;width:1600px;border-bottom-style:solid;border-bottom-color:black;border-bottom-width:2px;}");
                addRule("#result1{height:70px;width:600px;float:left;font-size:50px;font-style:bold;text-align:center;padding-top:10px;}");
                addRule("#result2{height:70px;width:200px;float:left;font-size:50px;font-style:bold;text-align:center;padding-top:10px;}");
                addRule("#result3{height:70px;width:200px;float:left;font-size:50px;text-align:center;padding-top:10px;}");
                addRule("#result4{height:70px;width:200px;float:left;font-size:50px;text-align:center;padding-top:10px;}");
                addRule("#result5{height:70px;width:400px;float:left;font-size:50px;text-align:center;padding-top:10px;}");

        addRule("#footer{height:250px;width:1600px;text-align:center;font-size:30px;}");
    }
}
