package com.radixdigit.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * 功能、用途、现存BUG: 帮助实现一些通用的字符串处理
 * 
 * @author
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class RmStringHelper {

    @SuppressWarnings("unused")
    private final static File DEFAULT_repository = new File(System
        .getProperty("java.io.tmpdir"));

    public final static String SYSTEM_FILE_SEPARATOR = System
        .getProperty("file.separator");

    public RmStringHelper() {
    }

    /**
     * 将字符串以指定字符串切割后,分配到List中
     * 
     * @param strValue
     *        -->输入字符串
     * @return List
     */
    public static List getTokenizerList(String strValue, String delim) {
        List myList = new ArrayList();
        StringTokenizer stChat = new StringTokenizer(strValue, delim);
        int iLength = stChat.countTokens();
        for (int i = 0; i < iLength; i++) {
            String strTemp = stChat.nextToken();
            if (strTemp == null)
                strTemp = "";
            myList.add(strTemp);
        }
        return myList;
    }

    /**
     * 将String[]中字符串以逗号分割后拼成一个字符串,不带有单引号
     * 
     * @param strArray
     *        -->输入字符串数组
     * @return String
     */
    public static String parseToSQLString(String[] strArray) {
        if (strArray == null || strArray.length == 0)
            return "'notExistId'";
        StringBuilder myStr = new StringBuilder();
        for (int i = 0; i < strArray.length - 1; i++) {
            myStr.append(strArray[i]).append(",");
        }
        myStr.append(strArray[strArray.length - 1]);
        return myStr.toString();
    }

    /**
     * 将String[]中字符串以逗号分割后拼成一个字符串,带有单引号
     * 
     * @param strArray
     *        -->输入字符串数组
     * @return String
     */
    public static String parseToSQLStringComma(String[] strArray) {
        return parseToSQLStringComma((Object[]) strArray);
    }

    /**
     * 将String[]中字符串以逗号分割后拼成一个字符串,带有单引号
     * 
     * @param strArray
     *        -->输入字符串数组
     * @return String
     */
    public static String parseToSQLStringComma(Object[] strArray) {
        if (strArray == null || strArray.length == 0) {
            // return "";
            return "'notExistId'"; // 为了让长度为0的数组返回的sql不报错
        }
        StringBuilder myStr = new StringBuilder();
        for (int i = 0; i < strArray.length - 1; i++) {
            myStr.append("'").append(strArray[i]).append("',");
        }
        myStr.append("'").append(strArray[strArray.length - 1]).append("'");
        return myStr.toString();
    }

    /**
     * 功能: 把"123,234,567"转为new String[]{"123", "234", "567"}
     * 
     * @param str
     * @return
     */
    public static String[] parseStringToArray(String str) {
        return parseStringToArray(str, ",");
    }

    /**
     * 功能: 把"123,234,567"转为new String[]{"123", "234", "567"}
     * 
     * @param str
     * @param splitKey
     * @return
     */
    public static String[] parseStringToArray(String str, String splitKey) {
        String[] returnStrArray = null;
        if (str != null && str.length() > 0) {
            returnStrArray = str.split(",", -1);
        }
        if (returnStrArray == null) {
            returnStrArray = new String[0];
        }
        return returnStrArray;
    }

    /**
     * 将ISO字符串转换为GBK编码的字符串。
     * 
     * @param str
     *        -->输入字符串
     * @return 经编码后的字符串，如果有异常，则返回原编码字符串
     */
    public static String iso2Gbk(String original) {
        if (original != null) {

            try {
                return new String(original.getBytes("iso8859-1"), "gbk");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 将iso-8859-1字符串转换为UTF-8编码的字符串。
     * 
     * @param original
     *        -->输入字符串
     * @return 经编码后的字符串，如果有异常，则返回原编码字符串
     */
    public static String iso2Utf8(String original) {
        if (original != null) {

            try {
                return new String(original.getBytes("iso8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 功能: 把指定字符串original 从encode1 转化到encode2
     * 
     * @param original
     * @param encode1
     * @param encode2
     * @return
     */
    public static String encode2Encode(String original, String encode1,
        String encode2) {
        if (original != null) {
            try {
                return new String(original.getBytes(encode1), encode2);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 功能: 以encode1的编码方式获得original
     * 
     * @param original
     * @param encode1
     * @return
     */
    public static String getStringByEncode(String original, String encode1) {
        if (original != null) {
            try {
                return new String(original.getBytes(), encode1);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 功能: 把指定字符串strSource 中的strFrom 全部替换为strTo
     * 
     * @param strSource
     * @param strFrom
     * @param strTo
     * @return
     */
    public static String replaceAll(String strSource, String strFrom,
        String strTo) {
        String strDest = "";
        int intFromLen = strFrom.length();
        int intPos;

        while ((intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
        }
        strDest = strDest + strSource;

        return strDest;
    }

    /**
     * 功能: 把strSource中的第1个strFrom替换为strTo
     * 
     * @param strSource
     * @param strFrom
     * @param strTo
     * @return
     */
    public static String replaceFirst(String strSource, String strFrom,
        String strTo) {
        String strDest = "";
        int intFromLen = strFrom.length();
        int intPos;

        while ((intPos = strSource.indexOf(strFrom)) != -1) {
            strDest = strDest + strSource.substring(0, intPos);
            strDest = strDest + strTo;
            strSource = strSource.substring(intPos + intFromLen);
            break;
        }
        strDest = strDest + strSource;

        return strDest;
    }

    /**
     * 功能: 过滤Html页面中的敏感字符,用于在script脚本中显示
     * 
     * @param value
     * @return
     */
    public static String replaceStringToScript(Object obj) {
        return replaceStringToScript(obj == null ? "" : obj.toString());
    }

    /**
     * 功能: 过滤Html页面中的敏感字符,用于在script脚本中显示
     * 
     * @param value
     * @return
     */
    public static String replaceStringToScript(String value) {
        return replaceStringByRule(value,
            new String[][] { { "'", "\\'" }, { "\"", "\\\"" },
                { "\\", "\\\\" }, { "\r", "\\r" }, { "\n", "\\n" },
                { "\t", "\\t" }, { "\f", "\\f" }, { "\b", "\\b" } }); // {"\r",
        // "\\u000D"},
        // {"\n",
        // "\\u000A"}
    }

    /**
     * 过滤Html页面中的敏感字符
     * 
     * @param value
     * @return
     */
    public static String replaceStringToHtml(Object obj) {
        return replaceStringToHtml(obj == null ? "" : obj.toString());
    }

    /**
     * 过滤Html页面中的敏感字符
     * 
     * @param value
     * @return
     */
    public static String replaceStringToHtml(String value) {
        return replaceStringByRule(value, new String[][] { { "<", "&lt;" },
            { ">", "&gt;" }, { "&", "&amp;" }, { "\"", "&quot;" },
            { "'", "&#39;" }, { "\n", "<BR>" }, { "\r", "<BR>" } });
    }

    /**
     * 把<替换成&lt;，应对编辑html代码
     * 
     * @param value
     * @return
     */
    public static String replaceStringToEditHtml(String value) {
        if (value == null) {
            value = "";
        }
        return replaceStringByRule(value, new String[][] { { "<", "&lt;" } });
    }

    /**
     * 替换文本头尾的分页标记
     * 
     * @author wangjian
     * @parame value
     * @return
     */
    public static String replaceStringHeadAndEndNativPageFlag(String value) {
        if (value == null || value.equals("")) {
            return "";
        }
        String startFlag = "<div style=\"page-break-after: always\"><span style=\"display: none\">&nbsp;</span></div>";
        String endFlag = "<div style=\"page-break-after: always\"><span style=\"display: none\">&nbsp;</span>\b\n<p>&nbsp;</p>";
        Pattern pattern = Pattern.compile("(" + startFlag + ")|(" + endFlag
            + ")");
        Matcher matcher = pattern.matcher(value);
        StringBuffer sb = new StringBuffer();
        boolean bool = false;
        int i = 0;
        int ii = 0;
        if (value != null && value.trim().startsWith(startFlag)) {
            bool = matcher.find();
            // System.out.println("匹配数:"+matcher.groupCount());
            while (bool) {
                // System.out.println("第"+i+"组的子串内容为： "+matcher.group(0));
                if (i == 0) {
                    matcher.appendReplacement(sb, "");
                }
                i++;
                bool = matcher.find();
            }
            matcher.appendTail(sb);
        } else {
            sb.append(value);
        }

        if (value != null
            && (value.trim().endsWith(startFlag) || value.trim().endsWith(
                endFlag))) {
            String rtns = sb.toString();
            sb.delete(0, sb.length());
            matcher = pattern.matcher(rtns);
            bool = matcher.find();
            while (bool) {
                // System.out.println("第"+ii+"组的子串内容为： "+matcher.group(0));
                if ((i - 2) == ii) {
                    matcher.appendReplacement(sb, "");
                }
                ii++;
                bool = matcher.find();
            }
            matcher.appendTail(sb);
        }
        // System.out.println(sb.toString());
        return sb.toString();

    }

    /**
     * 添加文本头的分页标记
     * 
     * @author wangjian
     * @parame value
     * @return
     */
    public static String insertHeadAndEndNativPageFlag(String value) {
        if (value == null || value.equals("")) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        String startFlag = "<div style=\"page-break-after: always\"><span style=\"display: none\">&nbsp;</span></div>";
        if (value.indexOf(startFlag) != -1) {
            if (!value.startsWith(startFlag)) {
                sb.append(startFlag);
            }
            sb.append(value);
            if (!value.endsWith(startFlag)) {
                sb.append(startFlag);
            }

            return sb.toString();
        } else {
            return value;
        }
    }

    /**
     * 过滤Html页面中的敏感字符，接受过滤的字符列表和转化后的值
     * 
     * @param value
     * @return
     */
    public static String replaceStringByRule(String value, String[][] aString) {
        if (value == null) {
            return ("");
        }
        char content[] = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);

        for (int i = 0; i < content.length; i++) {
            boolean isTransct = false;
            for (int j = 0; j < aString.length; j++) {
                if (String.valueOf(content[i]).equals(aString[j][0])) {
                    result.append(aString[j][1]);
                    isTransct = true;
                    break;
                }
            }
            if (!isTransct) {
                result.append(content[i]);
            }
        }
        return result.toString();
    }

    /**
     * 显示数据前过滤掉null
     * 
     * @param myString
     * @return
     */
    public static String prt(String myString) {
        if (myString != null) {
            return myString;
        } else {
            return "";
        }
    }

    public static String prt(Object obj) {
        if (obj != null) {
            return prt(obj.toString());
        } else {
            return "";
        }
    }

    /**
     * 显示数据前过滤掉null，截取一定位数
     * 
     * @param myString
     * @param index
     *        最大显示的长度
     * @return
     */
    public static String prt(String myString, int index) {
        if (myString != null) {
            if (myString.length() >= index) {
                return myString.substring(0, index);
            } else {
                return myString;
            }
        } else {
            return "";
        }
    }

    public static String prt(Object obj, int index) {
        if (obj != null) {
            return prt(obj.toString(), index);
        } else {
            return "";
        }
    }

    /**
     * 显示数据前过滤掉null，截取一定位数，并加上表示，如省略号
     * 
     * @param myString
     * @param index
     *        最大显示的长度
     * @return
     */
    public static String prt(String myString, int index, String accessional) {
        int accessionalLength = 0;
        if (index < 0) {
            return myString;
        }
        if (accessional == null || "".equals(accessional)) {
            accessional = "...";
        }
        accessionalLength = accessional.length();
        if (myString != null) {
            if (index <= accessionalLength) {
                return myString.substring(0, index);
            } else if (myString.length() >= index - accessionalLength) {
                return myString.substring(0, index - accessionalLength)
                    + accessional;
            } else {
                return myString;
            }
        } else {
            return "";
        }
    }

    public static String prt(Object obj, int index, String accessional) {
        if (obj != null)
            return prt(obj.toString(), index, accessional);
        else {
            return "";
        }
    }

    /**
     * 判断一个数组是否包含一个字符串
     * 
     * @param arrayString
     * @param str
     * @return
     */
    public static boolean ArrayContainString(String[] arrayString, String str) {
        if (arrayString == null || arrayString.length == 0) {
            return false;
        }
        for (int i = 0; i < arrayString.length; i++) {
            if (arrayString[i].equals(str))
                return true;
        }
        return false;
    }

    /**
     * 功能: 把new String[]{"abc", null, "123"}转化为 "abc,123"
     * 
     * @param arrayString
     * @param splitStr
     * @return
     */
    public static String ArrayToString(String[] arrayString, String splitStr) {
        StringBuilder str = new StringBuilder();
        if (arrayString == null || arrayString.length == 0) {
            return str.toString();
        }
        for (int i = 0; i < arrayString.length; i++) {
            if (arrayString[i] != null && arrayString[i].length() > 0) {
                if (str.length() > 0) {
                    str.append(splitStr);
                }
                str.append(arrayString[i]);
            }
        }
        return str.toString();
    }

    public static String log(String str) {
        return log((Object) str);
    }

    public static String log(Object obj) {
        String str = new Timestamp(System.currentTimeMillis()).toString()
            + " QB-RM : " + obj;
        System.out.println(str);
        return str;
    }

    /**
     * 功能: 测试各种编码之间的转化，找出乱码原因
     * 
     * @param original
     * @return
     */
    public static String testAllEncode(String original) {
        return testAllEncode(original, new String[] { "GBK", "iso8859-1",
            "gb2312", "utf-8" });
    }

    /**
     * 功能: 测试各种编码之间的转化，找出乱码原因
     * 
     * @param original
     * @param encode
     * @return
     */
    public static String testAllEncode(String original, String[] encode) {
        String rtValue = "original = " + original + "\n";
        if (encode == null || encode.length < 2) {
            return rtValue;
        }
        for (int i = 0; i < encode.length; i++) {
            rtValue += "\n" + encode[i] + "-->\n";
            for (int j = 0; j < encode.length; j++) {
                if (j != i) {
                    rtValue += encode[i] + "-->" + encode[j] + " = "
                        + encode2Encode(original, encode[i], encode[j]) + "\n";
                }
            }
        }
        return rtValue;
    }

    /**
     * 功能: 对url编码
     * 
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        String rtStr = "";
        try {
            if (url != null && url.length() >= 0) {
                rtStr = URLEncoder.encode(url, "GBK");
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
        return rtStr;
    }

    /**
     * 功能: 把Map中的值依次取出来，以URL传值的方式拼成字符串
     * 
     * @param mValue
     * @return
     */
    public static String encodeUrlParameter(Map mValue) {
        return encodeUrlParameter(mValue, new String[0]);
    }

    /**
     * 功能: 把Map中的值依次取出来，以URL传值的方式拼成字符串
     * 
     * @param mValue
     * @param ignoreName
     *        忽略的field
     * @return
     */
    public static String encodeUrlParameter(Map mValue, String[] ignoreName) {
        String str = "";
        for (Iterator itMValue = mValue.keySet().iterator(); itMValue.hasNext();) {
            String tempKey = String.valueOf(itMValue.next());
            String tempValue = (mValue.get(tempKey) == null) ? "" : String
                .valueOf(mValue.get(tempKey));
            if (tempKey.startsWith("RM") || tempKey.startsWith("RANMIN")) {
                if (!tempKey.equals("RM_PAGE_SIZE")
                    && !tempKey.equals("RM_CURRENT_PAGE")
                    && !tempKey.equals("RM_ORDER_STR")) {
                    continue;
                }
            }
            if (RmStringHelper.ArrayContainString(ignoreName, tempKey)) {
                continue;
            }
            if (str.length() > 0) {
                str += "&";
            }
            str += tempKey + "=" + encodeUrl(tempValue);
        }
        return str;
    }

    /**
     * 功能: 从一个文件中读出字符串
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public static String readStringFromFile(File file) {
        StringBuilder rtStr = new StringBuilder();
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(file));
            String tempStr = "";
            while ((tempStr = in.readLine()) != null) {
                rtStr.append(tempStr).append("\n");
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rtStr.toString();
    }

    /**
     * 功能: 写一个字符串到文件中去
     * 
     * @param str
     * @param file
     */
    public static File writeStringToFile(String str, File file) {
        try {
            BufferedReader in4 = new BufferedReader(new StringReader(str));
            PrintWriter out1 = new PrintWriter(new BufferedWriter(
                new FileWriter(file)));
            String tempStr = null;
            while ((tempStr = in4.readLine()) != null) {
                out1.println(tempStr);
            }
            out1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * /** 功能: 从ruleXml读取到Document
     * 
     * @param ruleXml
     * @return
     * @throws MalformedURLException
     * @throws DocumentException
     */
    public static Document parseXml(String ruleXml)
        throws MalformedURLException, DocumentException {
        if (ruleXml == null || ruleXml.length() == 0) {
            throw new NullPointerException("xml路径是空!");
        }
        SAXReader reader = new SAXReader();
        Document document = null;
        if (ruleXml.startsWith("file:")) {
            document = reader.read(new URL(ruleXml));
        } else if (ruleXml.startsWith("http://")) {
            document = reader.read(new URL(ruleXml));
        } else {
            document = reader.read(new File(ruleXml));
        }

        return document;
    }

    /**
     * 功能: 得到str的首字母大写
     * 
     * @param str
     * @return
     */
    public static String toFirstUpperCase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        } else {
            String firstStr = str.substring(0, 1);
            return firstStr.toUpperCase() + str.substring(1);
        }
    }

    /**
     * 功能: 得到百分比的显示
     * 
     * @param numerator
     * @param denominator
     * @return
     */
    public static String getPercentage(int numerator, int denominator) {
        return getPercentage(numerator * 1.00, denominator * 1.00);
    }

    /**
     * 功能: 得到百分比的显示
     * 
     * @param numerator
     * @param denominator
     * @return
     */
    public static String getPercentage(double numerator, double denominator) {
        double percentage = numerator * 1.00 / denominator;
        if (String.valueOf(percentage).endsWith(String.valueOf(Double.NaN))) {
            return "空";
        }
        percentage = percentage * 100;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(percentage) + "%";
    }

    /**
     * 功能:
     * 
     * @param value
     * @param fractionDigits
     * @return
     */
    public static String defaultFormatDouble(double value, int fractionDigits) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        nf.setMinimumFractionDigits(fractionDigits);
        nf.setMaximumFractionDigits(fractionDigits);
        return nf.format(value);
    }

    /**
     * 功能: 把15位的身份证号码升级为18位
     * 
     * @param oldIdCard
     * @return
     */
    public static String updateIdCard(String oldIdCard) {
        String newIdCard = "";
        StringBuffer tempStrOld = new StringBuffer();
        tempStrOld.append(oldIdCard);
        int cOld[] = new int[17];
        int iSum = 0;
        oldIdCard = oldIdCard.substring(0, 6) + "19"
            + oldIdCard.substring(6, oldIdCard.length());
        try {
            if (oldIdCard.length() != 17) {
                throw new Exception();
            }
            for (int i = 0; i < 17; i++) {
                cOld[i] = Integer.parseInt(String.valueOf(oldIdCard.charAt(i)));
            }
            int wi[] = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
                8, 4, 2 };
            iSum = 0;
            for (int i = 0; i < 17; i++) {
                iSum = iSum + cOld[i] * wi[i];
            }
        } catch (Exception e) {
            throw new RuntimeException("请输入正确格式的身份证号码!");
        }
        int y = iSum % 11;
        String strVer = new String("10X98765432");
        newIdCard = oldIdCard + strVer.substring(y, y + 1);
        return newIdCard;
    }

    /**
     * 功能: 得到指定长度的int的字符串
     * 
     * @param myInt
     * @param length
     * @return
     */
    public static String getFormatLengthInt(int myInt, int length) {
        String str = String.valueOf(myInt);
        if (str.length() < length) {
            int offLength = length - str.length();
            for (int j = 0; j < offLength; j++) {
                str = "0" + str;
            }
        }
        return str;
    }

    public static String getAvg(Object apprcount, Object submitcount) {

        float count = 0;
        float scount = 0;
        float avg = 0;

        if (!"".equals(apprcount) && apprcount != null) {
            count = new Float(apprcount.toString()).floatValue();
        }

        if (!"".equals(submitcount) && submitcount != null) {
            scount = new Float(submitcount.toString()).floatValue();
        }

        if (scount != 0) {
            avg = count / scount * 100;
        }
        return avg + "";
    }

    //    
    // public static String getExtent(List beans,String id,String org_id,String
    // item_id,Object apprcount,Object submitcount,Object avg,Object totalAvg){
    //      
    // boolean onlyflag = true;
    // float onlyResult = 0;
    // int onlyResultSum = 0;
    // float count = 0;
    // float scount = 0;
    // float avgValue = 0;
    // float totalAvgValue = 0;
    //      
    // if(!"".equals(avg) && avg != null){
    // avgValue = new Float(avg.toString()).floatValue();
    // }
    //      
    // if(!"".equals(totalAvg) && totalAvg != null){
    // totalAvgValue = new Float(totalAvg.toString()).floatValue();
    // }
    //      
    //      
    // for(int i = 0;i<beans.size();i++){
    // ApprResultOptionVo vo = (ApprResultOptionVo)beans.get(i);
    // if(!item_id.equals(vo.getItem_id())){
    // if(id.equals(vo.getId()) && org_id.equals(vo.getOrg_id())){
    // onlyflag = false;
    // break;
    // }
    // }
    // }
    //      
    // if(onlyflag){
    //          
    // for(int j = 0;j<beans.size();j++){
    // ApprResultOptionVo vo2 = (ApprResultOptionVo)beans.get(j);
    // item_id = vo2.getItem_id();
    // org_id = vo2.getOrg_id();
    // onlyflag = true;
    //              
    // if(id.equals(vo2.getId())){
    //                  
    // for(int k = 0;k<beans.size();k++){
    //                      
    // ApprResultOptionVo vo3 = (ApprResultOptionVo)beans.get(k);
    // if(!item_id.equals(vo3.getItem_id())){
    // if(id.equals(vo3.getId()) && org_id.equals(vo3.getOrg_id())){
    // onlyflag = false;
    // break;
    // }
    // }
    //                      
    // }
    //                  
    // if(onlyflag){
    //                      
    // if("".equals(vo2.getApprcount()) || vo2.getApprcount() == null){
    // count = 0;
    // }else{
    // count = new Float(vo2.getApprcount()).floatValue();
    // }
    //                      
    // if("".equals(vo2.getSubmitcount()) || vo2.getSubmitcount() == null){
    // scount = 0;
    // }else{
    // scount = new Float(vo2.getSubmitcount()).floatValue();
    // }
    //                      
    // if(scount!=0){
    // onlyResult += count/scount*100;
    // }
    // onlyResultSum++;
    // }
    //                  
    // }
    //              
    // }
    // onlyResult = onlyResult/onlyResultSum;
    // return onlyResult-totalAvgValue+"";
    // }
    //      
    // return avgValue-totalAvgValue+"";
    // }
    // public static String getResult(List beans,String id,String org_id,String
    // item_id,Object apprcount,Object submitcount,Object avg,Object totalAvg){
    //      
    // boolean onlyflag = true;
    // float count = 0;
    // float scount = 0;
    // float result = 0;
    // float onlyResult = 0;
    // int onlyResultSum = 0;
    //      
    // if(!"".equals(apprcount) && apprcount != null){
    // count = new Float(apprcount.toString()).floatValue();
    // }
    //      
    // if(!"".equals(submitcount) && submitcount != null){
    // scount = new Float(submitcount.toString()).floatValue();
    // }
    //      
    // if(scount!=0){
    // result = count/scount*100;
    // }
    //      
    //      
    // float avgValue = 0;
    // float totalAvgValue = 0;
    //      
    // if(!"".equals(avg) && avg != null){
    // avgValue = new Float(avg.toString()).floatValue();
    // }
    //      
    // if(!"".equals(totalAvg) && totalAvg != null){
    // totalAvgValue = new Float(totalAvg.toString()).floatValue();
    // }
    //      
    //      
    // for(int i = 0;i<beans.size();i++){
    // ApprResultOptionVo vo = (ApprResultOptionVo)beans.get(i);
    // if(!item_id.equals(vo.getItem_id())){
    // if(id.equals(vo.getId()) && org_id.equals(vo.getOrg_id())){
    // onlyflag = false;
    // break;
    // }
    // }
    // }
    // if(onlyflag){
    //          
    // for(int j = 0;j<beans.size();j++){
    // ApprResultOptionVo vo2 = (ApprResultOptionVo)beans.get(j);
    // item_id = vo2.getItem_id();
    // org_id = vo2.getOrg_id();
    // onlyflag = true;
    //              
    // if(id.equals(vo2.getId())){
    //                  
    // for(int k = 0;k<beans.size();k++){
    //                      
    // ApprResultOptionVo vo3 = (ApprResultOptionVo)beans.get(k);
    // if(!item_id.equals(vo3.getItem_id())){
    // if(id.equals(vo3.getId()) && org_id.equals(vo3.getOrg_id())){
    // onlyflag = false;
    // break;
    // }
    // }
    //                      
    // }
    //                  
    // if(onlyflag){
    //                      
    // if("".equals(vo2.getApprcount()) || vo2.getApprcount() == null){
    // count = 0;
    // }else{
    // count = new Float(vo2.getApprcount()).floatValue();
    // }
    //                      
    // if("".equals(vo2.getSubmitcount()) || vo2.getSubmitcount() == null){
    // scount = 0;
    // }else{
    // scount = new Float(vo2.getSubmitcount()).floatValue();
    // }
    //                      
    // if(scount!=0){
    // onlyResult += count/scount*100;
    // }
    // onlyResultSum++;
    // }
    //                  
    // }
    //              
    // }
    // onlyResult = onlyResult/onlyResultSum;
    // return result-(onlyResult-totalAvgValue)+"";
    // }
    //      
    // return result-(avgValue-totalAvgValue)+"";
    // }

    public static String getExcelResult(Object apprcount, Object submitcount,
        Object avg, Object totalAvg) {

        float count = 0;
        float scount = 0;
        float result = 0;

        if (!"".equals(apprcount) && apprcount != null) {
            count = new Float(apprcount.toString()).floatValue();
        }

        if (!"".equals(submitcount) && submitcount != null) {
            scount = new Float(submitcount.toString()).floatValue();
        }

        if (scount != 0) {
            result = count / scount * 100;
        }

        float avgValue = 0;
        float totalAvgValue = 0;

        if (!"".equals(avg) && avg != null) {
            avgValue = new Float(avg.toString()).floatValue();
        }

        if (!"".equals(totalAvg) && totalAvg != null) {
            totalAvgValue = new Float(totalAvg.toString()).floatValue();
        }

        return result - 2 * avgValue + 2 * totalAvgValue + "";
    }

    public static String getSummitratio(String submitcount, String groupcount) {

        float submitcountValue = 0;
        float groupcountValue = 0;
        float summitratio = 0;

        if (!"".equals(submitcount) && submitcount != null) {
            submitcountValue = new Float(submitcount.toString()).floatValue();
        }

        if (!"".equals(groupcount) && groupcount != null) {
            groupcountValue = new Float(groupcount.toString()).floatValue();
        }

        if (groupcountValue != 0) {
            summitratio = submitcountValue / groupcountValue * 100;
        }
        return summitratio + "";
    }

    public static String getStackTraceStr(Throwable t, int rows) {
        String str = "";
        for (int i = 0; i < t.getStackTrace().length; i++) {
            if (i >= rows) {
                str += "......\n";
                break;
            }
            str += t.getStackTrace()[i] + "\n";
        }
        return str;
    }

    /**
     * 功能:
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static String getOrOperator(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return null;
        }
        if (str1.length() > str2.length()) {
            return getOrOperator(str2, str1);
        }
        String str = "";
        for (int i = 0; i < str1.length(); i++) {
            if ("1".equals(str1.substring(i, i + 1))
                || "1".equals(str2.substring(i, i + 1))) {
                str += "1";
            } else {
                str += "0";
            }
        }
        if (str2.length() > str1.length()) {
            str += str2.substring(str1.length(), str2.length());
        }
        return str;
    }

    public static String replaceAll4RegexEscape(String s) {
        return s.replaceAll("([\\\\\\$])", "\\\\$1");
    }

    public static void main(String[] args) {
        try {
            // System.out.println("<meta name=\"keywords\" content=\"<a href=http://search.yidaba.com/information?k=高污染企业 \ntarget=_blank>高污染企业</a>&nbsp;<a href=http://search.yidaba.com/information?k=财政补贴 \ntarget=_blank>财政补贴</a>&nbsp;\">\n");
            // System.out.println("<meta name=\"keywords\" content=\"<a href=http://search.yidaba.com/information?k=高污染企业 \ntarget=_blank>高污染企业</a>&nbsp;<a href=http://search.yidaba.com/information?k=财政补贴 \ntarget=_blank>财政补贴</a>&nbsp;\">".replaceFirst("(<meta name=\"keywords\" content=\"[^\"]*?>+[^\"]*?\")(>)",
            // "$1/$2"));
            // System.out.println("<meta name=\"keywords\" content=\"<a href=http://search.yidaba.com/information?k=高污染企业 \ntarget=_blank高污染企业</a&nbsp;<a href=http://search.yidaba.com/information?k=财政补贴 \ntarget=_blank财政补贴</a&nbsp;\">\n");
            // System.out.println("<meta name=\"keywords\" content=\"<a href=http://search.yidaba.com/information?k=高污染企业 \ntarget=_blank高污染企业</a&nbsp;<a href=http://search.yidaba.com/information?k=财政补贴 \ntarget=_blank财政补贴</a&nb>sp;\">\n".replaceFirst("(<meta name=\"keywords\" content=\"[^\"]*?>+[^\"]*?\")(>)",
            // "$1/$2"));
            // String stest = "中文1234 abcd[]()<+>,.~\\";
            // String stest1="%u7B80%u5355";
            // System.out.println(stest);
            // System.out.println(escape(stest));
            // System.out.println(unescape(escape(stest)));
            // System.out.println(unescape(stest1));
            // System.out.println(replaceAll4RegexEscape("e:\\do$wnload\\"));
            // int s=1106;
            // int size=100;
            // int r=s%size;
            // int m=s/size;
            // System.out.println(r+"<>"+m);
            String s = "  name  like '%评论%'   and  style_type  like '%8%'  ";
            System.out.println(RmStringHelper.escape(s));
            System.out.println(RmStringHelper
                .unescape(RmStringHelper.escape(s)));
            System.out.println(RmStringHelper.escapeFileCh(s));
            // Integer k1 = 126;
            // Integer k2 = 126;
            // System.out.println( k1==k2);
            // Integer j1 = 150; Integer j2 = 150; System.out.println( j1==j2);

            // System.out.println(2 * 3 * 5 * 7 & 11 * 13 * 17 * 19 * 23);
            // System.out.println(getTokenizerList("fdsafdsafsadfsdafsa", "a"));
            // String[] a = TimeZone.getAvailableIDs();
            // for (int i = 0; i < a.length; i++) {
            // if (a[i].startsWith(""))
            // System.out.println(a[i]);
            // }
            // String[] abc = { "aaaaaaa", "bbbbb" };
            // System.out.println(parseToSQLString(abc));
            // System.out.println(2 * 3 * 5 * 7 * 11 * 13 * 17 * 19 * 23);
            // System.out.println(((Date) runJavaCode("return new Date();"))
            // .getTime());
            // System.out
            // .println("result="
            // + runJavaScriptCode(readStringFromFile(new File(
            // "e:\\a.js"))) + "********");
            // System.out.println("1\\23\r456\r789\r\n0");
            // System.out.println(replaceStringToScript("123\r456\r789\r\n0"));
            // File download = new File("e:\\swap\\娱乐\\小游戏");
            // System.out.println(fileToString(download));

            // System.out.println(encodeUrl(" ID IN (SELECT B.INFOMATION FROM CMS_INFOMATION_SITE_NODE B where B.SITE_NODE_ID='1000100100000000001')"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 定义字符数组
     * 
     * @author wangjian
     */
    private final static String[] hex = { "00", "01", "02", "03", "04", "05",
        "06", "07", "08", "09", "0A", "0B", "0C", "0D", "0E", "0F", "10", "11",
        "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D",
        "1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
        "2A", "2B", "2C", "2D", "2E", "2F", "30", "31", "32", "33", "34", "35",
        "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41",
        "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D",
        "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59",
        "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65",
        "66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71",
        "72", "73", "74", "75", "76", "77", "78", "79", "7A", "7B", "7C", "7D",
        "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
        "8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95",
        "96", "97", "98", "99", "9A", "9B", "9C", "9D", "9E", "9F", "A0", "A1",
        "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD",
        "AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9",
        "BA", "BB", "BC", "BD", "BE", "BF", "C0", "C1", "C2", "C3", "C4", "C5",
        "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1",
        "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD",
        "DE", "DF", "E0", "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9",
        "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5",
        "F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF" };
    /***
     * 定义字节数组
     * 
     * @author wangjian
     */
    private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01, 0x02, 0x03, 0x04, 0x05,
        0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A,
        0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E,
        0x0F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };

    /**
     * java传到js中加码
     * 
     * @author wangjian
     * @param s
     * @return
     */
    public static String escape(String s) {
        if (s == null || s.trim().equals(""))
            return "";
        StringBuffer sbuf = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int ch = s.charAt(i);
            if (ch == ' ') { // space : map to '+'
                sbuf.append('+');
            } else if ('A' <= ch && ch <= 'Z') { // 'A'..'Z' : as it was
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') { // 'a'..'z' : as it was
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') { // '0'..'9' : as it was
                sbuf.append((char) ch);
            } else if (ch == '-'
                || ch == '_' // unreserved : as it was
                || ch == '.' || ch == '!' || ch == '~' || ch == '*'
                || ch == '\'' || ch == '(' || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch <= 0x007F) { // other ASCII : map to %XX
                sbuf.append('%');
                sbuf.append(hex[ch]);
            } else { // unicode : map to %uXXXX
                sbuf.append('%');
                sbuf.append('u');
                sbuf.append(hex[(ch >>> 8)]);
                sbuf.append(hex[(0x00FF & ch)]);
            }
        }
        return sbuf.toString();
    }

    /**
     * 对中文字符解码后还原中文处理方法
     * 
     * @author wangjian
     * @param s
     *        带有中文需要escape进行处理的字符串：String
     *        s="  name  like '%评论%'   and  style_type  like '%8%'  ";主要有查询条件
     */

    public static String escapeFileCh(String s) {
        if (s == null || s.trim().equals("")) {
            return "";
        }
        // 提取汉字处理
        Map escape_map = new HashMap();
        Pattern p = Pattern.compile("[(\u4e00-\u9fa5)]");
        Matcher m = p.matcher(s);
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        int k = 0;
        String key = null;
        String rexg = null;
        String value = null;
        if (result) {
            while (result) {
                value = m.group(0);
                key = RmStringHelper.escape(value);
                if (k == 0)
                    rexg = "(" + key + ")";
                else
                    rexg = rexg + "|(" + key + ")";
                k++;
                escape_map.put(key, value);
                result = m.find();
            }
            result = false;
            String allStr = (RmStringHelper.escape(s));
            if (escape_map != null && escape_map.size() > 0) {
                p = Pattern.compile(rexg);
                m = p.matcher(allStr);
                result = m.find();
                key = null;
                while (result) {
                    for (int z = 0; z < m.groupCount(); z++) {
                        if (m.group(z) != null && !m.group(z).trim().equals("")) {
                            key = m.group(z);
                        }
                    }
                    m.appendReplacement(sb, (String) escape_map.get(key));
                    result = m.find();
                }
                m.appendTail(sb);
            }
            return sb.toString();
        }

        return RmStringHelper.escape(s);
    }

    /**
     * js传到java 中解码
     * 
     * @author wangjian
     * @param s
     * @return
     */
    public static String unescape(String s) {
        if (s == null || s.trim().equals(""))
            return "";
        StringBuffer sbuf = new StringBuffer();
        int i = 0;
        int len = s.length();
        while (i < len) {
            int ch = s.charAt(i);
            if (ch == '+') { // + : map to ' '
                sbuf.append(' ');
            } else if ('A' <= ch && ch <= 'Z') { // 'A'..'Z' : as it was
                sbuf.append((char) ch);
            } else if ('a' <= ch && ch <= 'z') { // 'a'..'z' : as it was
                sbuf.append((char) ch);
            } else if ('0' <= ch && ch <= '9') { // '0'..'9' : as it was
                sbuf.append((char) ch);
            } else if (ch == '-'
                || ch == '_' // unreserved : as it was
                || ch == '.' || ch == '!' || ch == '~' || ch == '*'
                || ch == '\'' || ch == '(' || ch == ')') {
                sbuf.append((char) ch);
            } else if (ch == '%') {
                int cint = 0;
                if ('u' != s.charAt(i + 1)) { // %XX : map to ascii(XX)
                    cint = (cint << 4) | val[s.charAt(i + 1)];
                    cint = (cint << 4) | val[s.charAt(i + 2)];
                    i += 2;
                } else { // %uXXXX : map to unicode(XXXX)
                    cint = (cint << 4) | val[s.charAt(i + 2)];
                    cint = (cint << 4) | val[s.charAt(i + 3)];
                    cint = (cint << 4) | val[s.charAt(i + 4)];
                    cint = (cint << 4) | val[s.charAt(i + 5)];
                    i += 5;
                }
                sbuf.append((char) cint);
            }
            i++;
        }
        return sbuf.toString();
    }

    /**
     * textarea中转义问题处理
     * 
     * @author wangjian
     * @param sourc
     *        源字符串
     * @param traStr
     *        处理后字符串
     * @return tranStr 返回处理后字符串
     */
    public static String tranTextarea(String sourc) {
        if (sourc == null)
            return (null);
        StringBuffer result = new StringBuffer();
        String tranStr = null;
        for (int i = 0; i < sourc.length(); i++) {
            char ch = sourc.charAt(i);
            if (ch == '<')
                result.append("&lt;");
            else if (ch == '>')
                result.append("&gt;");
            else if (ch == '&')
                result.append("&amp;");
            else if (ch == '"')
                result.append("&quot;");
            // else if (ch == '\r')
            // result.append("<BR>");
            else if (ch == '\n') {
                if (i > 0 && sourc.charAt(i - 1) == '\r') {} else {
                    result.append("<BR>");
                }
            }// else if (ch == '\t')
            // result.append("&nbsp;&nbsp;&nbsp;&nbsp");
            // else if (ch == ' ')
            // result.append("&nbsp;");
            else
                result.append(ch);
        }
        tranStr = result.toString();
        return tranStr;
    }

    /**
     * 格式化url的相对、绝对路径
     * 
     * @param webSiteDomain
     * @param imageUrl
     * @return
     */
    public static String formatUrl(String imageUrl, String webSiteDomain,
        String webPageDir) {
        String newImageUrl = null;
        if (imageUrl.startsWith("/")) {
            newImageUrl = webSiteDomain + imageUrl;
        } else { // ./ ../ image/
            newImageUrl = webPageDir + imageUrl;
        }
        return newImageUrl;
    }

}