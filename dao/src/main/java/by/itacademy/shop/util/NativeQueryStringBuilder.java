package by.itacademy.shop.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NativeQueryStringBuilder {
    private List<String> select;
    private String from;
    private List<String> joins;
    private List<String> where;
    private String orderBy;
    private String limitOffset;

    public NativeQueryStringBuilder() {
        this.select = new LinkedList<>();
        this.from = "";
        this.joins = new LinkedList<>();
        this.where = new LinkedList<>();
        this.orderBy = "";
        this.limitOffset = "";
    }

    public NativeQueryStringBuilder select(String input){
        this.select.add(input);
        return this;
    }
    public NativeQueryStringBuilder select(String[] input){
        this.select.addAll(Arrays.asList(input));
        return this;
    }
    public NativeQueryStringBuilder from(String input){
        this.from=input;
        return this;
    }
    public NativeQueryStringBuilder join(String type, String joinTable, String on){
        this.joins.add(String.format(" %s JOIN %s ON %s ",type,joinTable,on));
        return this;
    }
    public NativeQueryStringBuilder where(String input){
        this.where.add(input);
        return this;
    }
    public NativeQueryStringBuilder and(){
        int index=this.where.size()-1;
        if(index>=0)
            this.where.set(index,this.where.get(index)+" AND ");
        return this;
    }
    public NativeQueryStringBuilder or(){
        int index=this.where.size()-1;
        if(index>=0)
            this.where.set(index,this.where.get(index)+" OR ");
        return this;
    }
    public NativeQueryStringBuilder addBordersToExistingWhere(){
        int index=this.where.size()-1;
        if(index<0)return this;
        this.where.set(index,this.where.get(index)+")");
        this.where.set(0,"("+this.where.get(0));
        return this;
    }
    public NativeQueryStringBuilder orderBy(String input){
        this.orderBy=input;
        return this;
    }
    public NativeQueryStringBuilder limitOffset(int limit, int offset){
        this.limitOffset=String.format(" LIMIT %d OFFSET %d ",limit,offset);
        return this;
    }

    public String build(){
        StringBuilder resString=new StringBuilder()
                .append(" SELECT ").append(this.concatStringsAppendingCommas(this.select))
                .append(" FROM ").append(this.from)
                .append(this.concatStrings(this.joins));
        if(!this.where.isEmpty())resString.append(" WHERE ").append(this.concatStrings(this.where));
        if(this.orderBy!=null && !this.orderBy.isEmpty())resString.append(" ORDER BY ").append(this.orderBy);
        if(this.limitOffset!=null && !this.limitOffset.isEmpty())resString.append(this.limitOffset);
        resString.append(";");
        return resString.toString();
    }
    private String concatStringsAppendingCommas(List<String> source){
        StringBuilder builder=new StringBuilder();
        boolean needComma=false;
        for(String str : source){
            if(needComma)builder.append(", ");
            builder.append(str);
            needComma=true;
        }
        return builder.toString();
    }
    private String concatStrings(List<String> source){
        StringBuilder builder=new StringBuilder();
        source.forEach(e -> builder.append(" ").append(e).append(" "));
        return builder.toString();
    }


}
