package com.ctn.entity.tcp;

import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by barry on 2015/3/14.
 */
public class Express {

    private String value;

    private String type;

    private Integer start;

    private String operate;

    private String lefExpress;

    private String rightExpress;

    private Integer len;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private List<String> leftList;

    private List<String> rightList;

    private List<String> expressList;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    private Map<String,Express> mapEx;

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public List<String> getLeftList() {
        return leftList;
    }

    public List<String> getExpressList() {
        return expressList;
    }

    public List<String> getRightList() {
        return rightList;
    }

    public String getRightExpress() {
        return rightExpress;
    }

    public String getLefExpress() {
        return lefExpress;
    }

    public String getOperate() {
        return operate;
    }

    public String getExpress() {
        return value;
    }

    public Express(){
        mapEx = new HashMap<String,Express>();
    }

    public Express(String express){
        mapEx = new HashMap<String,Express>();
        setValue(express);
    }

    public void setValue(String express){
        this.value = express;
        if(StringUtils.isNotBlank(express)){
            boolean isBool = false;
            label : for (String symbol : SYMBOLS){
                if (express.indexOf(symbol) != -1){
                    isBool = true;
                    operate = symbol;
                    break label;
                }
            }
            if (isBool){
                if (SYMBOL_AND.equals(operate) || SYMBOL_OR.equals(operate)){

                }
                String[] exs = express.split(operate);
                lefExpress = exs[0].trim();
                leftList = decodeExpress(lefExpress);
                rightExpress = exs[1].trim();
                rightList = decodeExpress(rightExpress);
            }else{
                operate = null;
                expressList = decodeExpress(express);
            }
        }
    }


    public static void main(String[] args){
//        String express = "${up2pc[5] + 6 eq f:v10(up2pc[up2pc[5]+7])}";
    }

    private boolean compare(String top,String current){
        List<String> bs1 = Arrays.asList("*", "/", "%");
        List<String> bs2 = Arrays.asList("+","-");
        if (bs1.contains(top) && bs2.contains(current)){
            return true;
        }
        return false;
    }


    private List<String> decodeExpress(String express){
        Stack<String> s = new Stack<String>();
        char[] cs = express.toCharArray();
        List<String> l = new ArrayList<String>();
        StringBuffer sb = new StringBuffer("");
        List<String> bs1 = Arrays.asList("*", "/", "%","+","-");
        List<String> bs2 = Arrays.asList("v10", "this", "v16");
        for (int i = 0; i < cs.length ; i++) {
            String data = String.valueOf(cs[i]);
            if (bs1.contains(data)){
                if (StringUtils.isNotBlank(sb.toString())){
                    l.add(sb.toString());
                }
                sb.setLength(0);
                if (s.isEmpty()){
                    s.push(data);
                }else{
                    String sy = s.lastElement();
                    boolean flag = compare(sy,data);
                    if(flag){
                        l.add(s.pop());
                        s.push(data);
                    }else{
                        s.push(data);
                    }
                }
            }else if(bs2.contains(sb.toString())){
                s.push(sb.toString()+data);
                sb.setLength(0);
            }else if(cs[i] == '('){
                s.push(data);
            }else if(cs[i]==')'){
                if (StringUtils.isNotBlank(sb.toString())){
                    l.add(sb.toString());
                }
                sb.setLength(0);
                String tmp = s.pop();
                while (!"(".equals(tmp) && !tmp.endsWith("(")){
                    l.add(tmp);
                    tmp = s.pop();
                }
                if(tmp.length() > 1 && tmp.endsWith("(")){
                    l.add(tmp);
                }
            }else if(cs[i]==']'){
                if (StringUtils.isNotBlank(sb.toString())){
                    l.add(sb.toString());
                }
                sb.setLength(0);
                String tmp = "";
                while(!(tmp = s.pop()).endsWith("[")){
                    l.add(tmp);
                }
                if(tmp.endsWith("[")){
                    l.add(tmp);
                }
            }else{
                sb.append(cs[i]);
            }
        }
        if (StringUtils.isNotBlank(sb.toString())){
            l.add(sb.toString());
        }
        while(!s.isEmpty()){
            l.add(s.pop());
        }
        return l;
    }

    public static final String SYMBOL_AND = "and";
    public static final String SYMBOL_OR = "or";
    public static final String SYMBOL_EQUAL = "eq";
    public static final String SYMBOL_LESS = "lt";
    public static final String SYMBOL_MORE = "gt";
    public static final String SYMBOL_NOT_EQUAL = "nq";//!=
    public static final String SYMBOL_NOT_LESS = "nl";//>=
    public static final String SYMBOL_NOT_MORE = "ng";//<=

    public static final String[] SYMBOLS = new String[]{SYMBOL_AND,SYMBOL_OR,SYMBOL_EQUAL,SYMBOL_LESS,SYMBOL_MORE,SYMBOL_NOT_EQUAL,SYMBOL_NOT_LESS,SYMBOL_NOT_MORE};

}
