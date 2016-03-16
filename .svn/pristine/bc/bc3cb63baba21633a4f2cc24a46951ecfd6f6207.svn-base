
package com.radixdigit.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Desc：正则表单时工具类</br>
 * @Filename:RegularUtil.java</br>
 * @Author:zhangshb</br>
 * @Date:2012-7-3上午11:21:31</br>
 */
public class RegularUtil {
    /**
     * 校验日期格式是否合法(正确格式:yyyy-MM-dd)
     * 
     * @param dateStr
     *        需要校验的日期字符串
     * @return 合法 true,不合法false
     */
    public static boolean isDate(String dateStr) {
        if (!StringUtils.isNotEmpty(dateStr)) {
            return false;
        }
        String el = "(\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8])))))";
        Pattern p = Pattern.compile(el);
        Matcher m = p.matcher(dateStr);
        return m.matches();
    }

    /**
     * 校验是否为银行账号(长度19位)
     * 
     * @param numberStr
     *        银行卡账号
     * @return 合法 true,不合法false
     */
    public static boolean isCardNumber(String numberStr) {
        if (!StringUtils.isNotEmpty(numberStr)) {
            return false;
        }
        String el = "[0-9]{4,22}";
        Pattern p = Pattern.compile(el);
        Matcher m = p.matcher(numberStr);
        return m.matches();
    }

    /**
     * 校验是否为银行账户名(长度2-20位)
     * 
     * @param accountStr
     *        账户名字符串
     * @return 合法 true,不合法false
     */
    public static boolean isBankAccount(String accountStr) {
        if (!StringUtils.isNotEmpty(accountStr)) {
            return false;
        }
        if (accountStr.trim().length() < 2 || accountStr.trim().length() > 20) {
            return false;
        }
        return true;
    }

    /**
     * 校验是否为金额(小数点后两位)
     * 
     * @param priceStr
     *        金额字符串
     * @return 合法 true,不合法false
     */
    public static boolean isPrice(String priceStr) {
        if (!StringUtils.isNotEmpty(priceStr)) {
            return false;
        }
        String el = "^[+]?[0-9]+(\\.\\d{0,12})?";
        Pattern p = Pattern.compile(el);
        Matcher m = p.matcher(priceStr);
        return m.matches();
    }

    /**
     * 校验"扣款模式"是否合法(只有三种状态:失败、全部、部分)
     * 
     * @param deductionsModeStr
     *        扣款模式字符串
     * @return 合法 true,不合法false
     */
    public static boolean isDeductionsMode(String deductionsModeStr) {
        if (!StringUtils.isNotEmpty(deductionsModeStr)) {
            return false;
        }
        if (deductionsModeStr.trim().equals("全部")
            || deductionsModeStr.trim().equals("部分")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验"成功/失败标识"是否合法(只有两种状态:失败、成功)
     * 
     * @param successOrFailStr
     * @return 合法 true,不合法false
     */
    public static boolean isSuccessOrFailStr(String successOrFailStr) {
        if (!StringUtils.isNotEmpty(successOrFailStr)) {
            return false;
        }
        if (successOrFailStr.trim().equals("成功")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String args[]) {
        String numberStr = "0.00";
        System.out.println(isPrice(numberStr));
    }
}
