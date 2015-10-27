package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
  
public class XmlFormat {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        String filepath = "D:/1.xml";
        fileformat(filepath);
//    	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><catalog><?target instruction?><journal title=\"XML Zone\" publisher=\"IBM developerWorks\"><article level=\"Intermediate\" date=\"December-2001\"><title>Java configuration with XML Schema</title><author><firstname>Marcello</firstname><lastname>Vitaletti</lastname></author></article></journal></catalog>";
//    	System.out.println(formatXml(xml));
    }

    /** 
     *  
     * @param filepath  将指定文件xml格式化 
     * @return 
     */  
    public static void fileformat(String filepath){  
        File file = new File(filepath);  
        SAXReader reader = new SAXReader();  
        try {  
            Document news = reader.read(file);
            OutputFormat format = OutputFormat.createPrettyPrint();  
            XMLWriter writer = new XMLWriter(new FileWriter(file),format);  
            writer.write(news);  
            writer.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
    /** 
     *  
     * @param filepath  将指定文件xml格式化 
     * @return 
     */  
    public static String formatString(String strxml){
    	
		return strxml;  
    }
    
    public static String formatXml(String str) {

        StringReader in=null;
        StringWriter out=null;
        try{
            SAXReader reader=new SAXReader();
            //创建一个串的字符输入流
            in=new StringReader(str);
            Document doc=reader.read(in);
            //创建输出格式
            OutputFormat formate=OutputFormat.createPrettyPrint();
            //创建输出
            out=new StringWriter();
            //创建输出流
            XMLWriter writer=new XMLWriter(out,formate);
            //输出格式化的串到目标中,格式化后的串保存在out中。
            writer.write(doc);
        } catch (IOException ioe){
            ;
        } catch (DocumentException de){
            ;
        } finally{
            //关闭流
            quietClose(in);
            quietClose(out);
        }
        return out.toString();
      }    
    
   
    public static void quietClose(Reader reader){
        try{
            if(reader!=null){
                reader.close();
            }
        } catch(IOException ioe){
            ;   
        }

    }

   
    public static void quietClose(Writer writer){
        try{
            if(writer!=null){
                writer.close();
            }
        } catch(IOException ioe){
            ;
        }
    } 
      
}  