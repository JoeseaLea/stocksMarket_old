package com.stocks.market.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>@author : Joesea Lea</p>
 * <p>@date : 2019/6/24</p>
 * <p>@description : </p>
 */
public class DownloadUtil {

    public static void downloadFile(String remoteFilePath, String localFilePath){

        URL urlFile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        File f = new File(localFilePath);
        try
        {
            urlFile = new URL(remoteFilePath);
            httpUrl = (HttpURLConnection)urlFile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(f));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1)
            {
                bos.write(b, 0, len);
            }
            System.out.println("下载完成");
            bos.flush();
            bis.close();
            httpUrl.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bis.close();
                bos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
