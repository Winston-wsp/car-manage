package cn.edu.bdu.carmanage.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileTransformObjectUtils {

    public static List fileTransformObject(String url,Class clazz){
        ArrayList list = new ArrayList();
        try {
            // 读入TXT文件
            File filename = new File("C:\\Users\\WU\\Desktop\\2020-08-11_奥维思测试.txt");
            FileInputStream fis = new FileInputStream(filename);
            InputStreamReader reader = new InputStreamReader(fis,"UTF-8"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = br.readLine();
            Field[] fields = clazz.getDeclaredFields();
            while (line != null) {
                Object object = clazz.newInstance();
                line = br.readLine(); // 一次读入一行数据
                if (line==null){
                    break;
                }
                String[] split = line.split(",", -1);
                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];
                    field.setAccessible(true);
                    //判断对象属性类型 如果类型为Integer类型转换为Integer类型赋值
                    //如果不是Integer类型转换为其他string类型赋值
                    if (field.getType() == Integer.class) {
                        field.set(object, new Integer(split[j].toString()));
                    } else if (field.getType() == Double.class) {
                        field.set(object, new Double(split[j].toString()));
                    } else if (field.getType() == Date.class) {
                        field.set(object, new SimpleDateFormat("yyyy-MM-dd").parse(split[j].toString()));
                    } else {
                        field.set(object, split[j].toString());
                    }
                }
                list.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}