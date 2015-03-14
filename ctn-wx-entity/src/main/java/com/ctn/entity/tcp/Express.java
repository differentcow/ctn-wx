package com.ctn.entity.tcp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2015/3/14.
 */
public class Express {

    private String express;

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }


    public static void main(String[] args){
        String express = "${up2pc[5] + 6 eq f:v10(up2pc[end])}";

        if(express.startsWith("${") && express.endsWith("}")){
            express = express.replace("${","").replace("}","").trim();
            System.out.println(express);
            //检验是否判断类型表达式
            boolean isBool = false;
            String sy = "";
            label: for (String symbol :SYMBOLS){
                if (express.indexOf(symbol) != -1){
                    sy = symbol;isBool = true;break label;
                }
            }
            char be = '(';
            char af = ')';
            if(isBool){
                String[] boolExp = express.split(sy);
                char[] bes = boolExp[0].trim().toCharArray();
                char[] afs = boolExp[1].trim().toCharArray();
                List<String> b = new ArrayList<String>();
                StringBuffer sb = new StringBuffer();
                for(char c : afs){
                    if (c != be){
                        sb.append(c);
                    }
                }
            }


        }

    }


    public static final String SYMBOL_PLUS = "+";
    public static final String SYMBOL_DIVIDE = "/";
    public static final String SYMBOL_MULTIPLY = "*";
    public static final String SYMBOL_MINUS = "-";
    public static final String SYMBOL_EQUAL = "eq";
    public static final String SYMBOL_LESS = "lt";
    public static final String SYMBOL_MORE = "gt";
    public static final String SYMBOL_NOT_EQUAL = "nq";//!=
    public static final String SYMBOL_NOT_LESS = "nl";//>=
    public static final String SYMBOL_NOT_MORE = "ng";//<=

    public static final String[] SYMBOLS = new String[]{SYMBOL_EQUAL,SYMBOL_LESS,SYMBOL_MORE,SYMBOL_NOT_EQUAL,SYMBOL_NOT_LESS,SYMBOL_NOT_MORE};

}
