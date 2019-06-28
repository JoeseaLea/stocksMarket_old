package com.stocks.market;

import java.io.*;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/28</p>
 * <p>@description : </p>
 */
public class ReadCsvTest {
    public static void main(String[] args) {
        try {
//            BufferedReader reader = new BufferedReader(new FileReader("/Users/joesealea/Downloads/002152.csv"));//换成你的文件名
            DataInputStream in = new DataInputStream(new FileInputStream(new File("/Users/joesealea/Downloads/002152.csv")));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gbk"));
            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                StringBuilder data = new StringBuilder();
                for (String i : item) {
                    data.append(i + " ");
                }
                System.out.println(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
