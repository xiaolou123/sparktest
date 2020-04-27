package com.xiaolouJava.bigdemo;
import org.json.JSONArray;

public class testJson {
    public static void main(String[] args) throws Exception{
        String data="[{\"id\":1,\"name\":\"zs\"},{\"id\":2,\"name\":\"li\"}]";
        //将字符串转换成json数组
        JSONArray ay= new JSONArray(data);
        //例如获取第一个里面的id和name
//        int id=ay.getJSONObject(0).get("id");
//        String name=ay.getJSONObject(0).get("name");
        //打印
        System.out.println(ay.toString());
    }


}
