package com.ctn.entity.tcp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HLJ on 2015/3/14.
 */
public class Mark {

    private boolean isClose;

    private Integer index;

    private String content;

    private List<Mark> mark;

    public List<Mark> getMark() {
        return mark;
    }

    public void setMark(List<Mark> mark) {
        this.mark = mark;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean isClose) {
        this.isClose = isClose;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public static Mark cal(char[] cs,int strat){
        Mark m = new Mark();
        m.setMark(new ArrayList<Mark>());
        StringBuffer s = new StringBuffer("");
        for (int i = strat; i < cs.length; i++){
            if(cs[i] == '('){
                if(s.toString()!=null && !"".equals(s.toString())){
                    if (m.getContent()!=null && !"".equals(m.getContent())){
                        m.setContent(m.getContent()+(s.toString()==null?"":s.toString()));
                    }else{
                        m.setContent(s.toString()==null?"":s.toString());
                    }
                    s.setLength(0);
                }
                Mark tmp = cal(cs,i+1);
                m.getMark().add(tmp);
                if (tmp != null && tmp.getContent()!=null && !"".equals(tmp.getContent())){
                    m.setContent((m.getContent()==null?"":m.getContent())+"["+tmp.hashCode()+"]");
                }
                i = tmp.getIndex();
            }else if(cs[i] == ')'){
                if(s.toString()!=null && !"".equals(s.toString())){
                    if (m.getContent()!=null && !"".equals(m.getContent())){
                        m.setContent(m.getContent()+(s.toString()==null?"":s.toString()));
                    }else{
                        m.setContent(s.toString()==null?"":s.toString());
                    }
                    s.setLength(0);
                }
                m.setIndex(i);
                return m;
            }if(cs[i] == '['){
                if(s.toString()!=null && !"".equals(s.toString())){
                    if (m.getContent()!=null && !"".equals(m.getContent())){
                        m.setContent(m.getContent()+(s.toString()==null?"":s.toString()));
                    }else{
                        m.setContent(s.toString()==null?"":s.toString());
                    }
                    s.setLength(0);
                }
                Mark tmp = cal(cs,i+1);
                m.getMark().add(tmp);
                if (tmp != null && tmp.getContent()!=null && !"".equals(tmp.getContent())){
                    m.setContent((m.getContent()==null?"":m.getContent())+"["+tmp.hashCode()+"]");
                }
                i = tmp.getIndex();
            }else if(cs[i] == ']'){
                if(s.toString()!=null && !"".equals(s.toString())){
                    if (m.getContent()!=null && !"".equals(m.getContent())){
                        m.setContent(m.getContent()+(s.toString()==null?"":s.toString()));
                    }else{
                        m.setContent(s.toString()==null?"":s.toString());
                    }
                    s.setLength(0);
                }
                m.setIndex(i);
                return m;
            }else{
                s.append(cs[i]);
            }
        }
        return m;
    }

    public static List<String> decode(Mark mark,List<String> list){
        List<Mark> ms = mark.getMark();
        for (Mark m : ms){
            if (m.getMark() ==null || m.getMark().isEmpty()){
                String ex = m.getContent();
            }else{
                decode(m,list);
            }
        }
        return list;
    }

    private static void setMap(Map<String,List<Integer>> map,int i,String sy){
        if(map.get(sy) == null){
            map.put(sy,new ArrayList<Integer>());
        }
        map.get(sy).add(i);
        map.put(sy,map.get(sy));
    }

    /*public static List<Param> decode(String ex){
        char[] esx = ex.toCharArray();
        List<Param> ps = new ArrayList<Param>();
        Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
        List<Character> bs1 = Arrays.asList('*','/','%');
        List<Character> bs2 = Arrays.asList('+','-');

        StringBuffer sb = new StringBuffer("");
        Param pp = new Param();
        int mark = 1;
        List<Param> p1 = new ArrayList<Param>();
        List<Param> p2 = new ArrayList<Param>();
        for(int i = 0; i < esx.length; i++){
            if(bs1.contains(esx[i])){
                Param p = new Param();
                p.setVal(sb.toString());
                ps.add(p);
            }else if(bs2.contains(esx[i])){
                if (StringUtils.isNotBlank(sb.toString())){
                    p1.add(new Param(sb.toString()));
//                    p1.add(new Param(String.valueOf(esx[i]),"oper"));
                }
            }else{
                sb.append(esx[i]);
            }
        }


        *//*for (Character c : bs){
            List<Integer> l =  map.get(String.valueOf(c));
            if(l != null && !l.isEmpty()){

            }
        }*//*


        int a1 = ex.indexOf("*");
        if(a1 != -1 && a1 != 0){

        }
        int a2 = ex.indexOf("/");
        int a3 = ex.indexOf("%");

        return null;
    }*/


    public static void main(String[] args){
        String ex = "3+(2+1)+((1+9)+4)";

        char[] cs = ex.toCharArray();
        Mark m = cal(cs,0);
        System.out.print("~~~~~~~~~~~~~~~");

    }


}
