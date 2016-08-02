package cn.lonecloud.util;

import java.lang.reflect.Field;

import net.sf.json.JSONObject;

public class JsonUtil {
	public static JSONObject changeObjToJson(Object object) throws Exception {
        JSONObject jb = new JSONObject(); //声明json对象
        @SuppressWarnings("rawtypes")
		Class c = object.getClass();  //获取类的类型类
        Field[] fields = c.getDeclaredFields(); //获取属性集合
        Field.setAccessible(fields, true); //在类外面要想获取私有属性的值必须设置
        Object[] name = new Object[fields.length]; //存储变量名
        Object[] value = new Object[fields.length]; //存储变量值
        Object[] type = new Object[fields.length]; //存储变量类型
        for (int i = 0; i < fields.length; i++) { //设置数组的值
            name[i] = fields[i].getName();
            value[i] = fields[i].get(object);
            type[i] = fields[i].getType();
        }

        for (int i = 0; i < name.length; i++) {
            String s1 = type[i].toString(); //变量类型
            String v1 = value[i] + ""; //变量值
            String n1 = name[i].toString(); //变量名
            
            if (s1.contains("String")) {
                if (!v1.equals("null")) {
                    jb.put(n1, v1); //给jsonobject设置key-value
                }
            } else if (s1.contains("long")) {
                jb.put(n1, Integer.parseInt(v1));
            } else if (s1.contains("int")) {
                jb.put(n1, Integer.parseInt(v1));
            }else if (s1.contains("boolean")){
            	jb.put(n1, Boolean.parseBoolean(v1));
            }
        }
        return jb;
    }
}
