package src;

import java.util.ArrayList;
import java.util.List;

public class JsonTool { 
    
    /**
     * json字符串的格式化
     * 
     * @param json
     * @param fillStringUnit
     * @return
     */ 
    public static String formatJson(String json, String fillStringUnit) { 
        if (json == null || json.trim().length() == 0) { 
            return null; 
        } 
         
        int fixedLenth = 0; 
        ArrayList<String> tokenList = new ArrayList<String>(); 
        { 
            String jsonTemp = json; 
            //预读取 
            while (jsonTemp.length() > 0) { 
                String token = getToken(jsonTemp); 
                jsonTemp = jsonTemp.substring(token.length()); 
                token = token.trim(); 
                tokenList.add(token); 
            }            
        } 
         
        for (int i = 0; i < tokenList.size(); i++) { 
            String token = tokenList.get(i); 
            int length = token.getBytes().length; 
            if (length > fixedLenth && i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) { 
                fixedLenth = length; 
            } 
        } 
         
        StringBuilder buf = new StringBuilder(); 
        int count = 0; 
        for (int i = 0; i < tokenList.size(); i++) { 
             
            String token = tokenList.get(i); 
             
            if (token.equals(",")) { 
                buf.append(token); 
                doFill(buf, count, fillStringUnit); 
                continue; 
            } 
            if (token.equals(":")) { 
                buf.append(" ").append(token).append(" "); 
                continue; 
            } 
            if (token.equals("{")) { 
                String nextToken = tokenList.get(i + 1); 
                if (nextToken.equals("}")) { 
                    i++; 
                    buf.append("{ }"); 
                } else { 
                    count++; 
                    buf.append(token); 
                    doFill(buf, count, fillStringUnit); 
                } 
                continue; 
            } 
            if (token.equals("}")) { 
                count--; 
                doFill(buf, count, fillStringUnit); 
                buf.append(token); 
                continue; 
            } 
            if (token.equals("[")) { 
                String nextToken = tokenList.get(i + 1); 
                if (nextToken.equals("]")) { 
                    i++; 
                    buf.append("[ ]"); 
                } else { 
                    count++; 
                    buf.append(token); 
                    doFill(buf, count, fillStringUnit); 
                } 
                continue; 
            } 
            if (token.equals("]")) { 
                count--; 
                doFill(buf, count, fillStringUnit); 
                buf.append(token); 
                continue; 
            } 
             
            buf.append(token); 
            //左对齐 
            if (i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) { 
                int fillLength = fixedLenth - token.getBytes().length; 
                if (fillLength > 0) { 
                    for(int j = 0; j < fillLength; j++) { 
                        buf.append(" "); 
                    } 
                } 
            } 
        } 
        return buf.toString(); 
    } 
     
    private static String getToken(String json) { 
        StringBuilder buf = new StringBuilder(); 
        boolean isInYinHao = false; 
        while (json.length() > 0) { 
            String token = json.substring(0, 1); 
            json = json.substring(1); 
             
            if (!isInYinHao &&  
                    (token.equals(":") || token.equals("{") || token.equals("}")  
                            || token.equals("[") || token.equals("]") 
                            || token.equals(","))) { 
                if (buf.toString().trim().length() == 0) {                   
                    buf.append(token); 
                } 
                 
                break; 
            } 
 
            if (token.equals("\\")) { 
                buf.append(token); 
                buf.append(json.substring(0, 1)); 
                json = json.substring(1); 
                continue; 
            } 
            if (token.equals("\"")) { 
                buf.append(token); 
                if (isInYinHao) { 
                    break; 
                } else { 
                    isInYinHao = true; 
                    continue; 
                }                
            } 
            buf.append(token); 
        } 
        return buf.toString(); 
    } 
	 public static void main(String[] args) {
//			List<String> list = new ArrayList<String>();
//			String json = "{\"BODY\":[{\"Poi\":[{\"Id\":\"1013298677\",\"Lon\":\"444304868\",\"Lat\":\"150350781\",\"Name\":\"东北大学学生会\",\"Distance\":\"179.16\",\"SrcType\":\"1\",\"Address\":\"\",\"Classify1\":\"政府机构及社会团体\",\"Classify2\":\"社会团体\",\"Classify3\":\"社会团体相关\",\"Type\":\"\",\"CommentNum\":\"0\",\"HasDiscount\":\"0\",\"Tel\":\"\"},{\"Id\":\"1013298678\",\"Lon\":\"444304868\",\"Lat\":\"150350781\",\"Name\":\"东北大学研究生会\",\"Distance\":\"179.16\",\"SrcType\":\"1\",\"Address\":\"\",\"Classify1\":\"政府机构及社会团体\",\"Classify2\":\"社会团体\",\"Classify3\":\"社会团体相关\",\"Type\":\"\",\"CommentNum\":\"0\",\"HasDiscount\":\"0\",\"Tel\":\"\"}]}]}";
//			String json = "asdasd!asd{}";
//			JsonTool t = new JsonTool();
//			String k = t.formatJson(json, "\t");
		 String k = (String) AutoProperties.getInstance().get("name");
		 
		System.out.println(k);
	 }
	 
 
    private static void doFill(StringBuilder buf, int count, String fillStringUnit) { 
        buf.append("\n"); 
        for (int i = 0; i < count; i++) { 
            buf.append(fillStringUnit); 
        } 
    } 
} 