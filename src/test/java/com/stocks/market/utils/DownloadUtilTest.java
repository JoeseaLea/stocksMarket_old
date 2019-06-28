package com.stocks.market.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class DownloadUtilTest {

    @Test
    public void downloadFile() {
        String remoteFilePath="http://quotes.money.163.com/service/chddata.html?code=1002152&start=20070813&end=20190621&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP";
        String localFilePath="/Users/joesealea/Downloads/002152.csv";
        DownloadUtil.downloadFile(remoteFilePath, localFilePath);
    }
}