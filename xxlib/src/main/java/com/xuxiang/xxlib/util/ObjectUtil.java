package com.xuxiang.xxlib.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ObjectUtil {

    /**
     * 判断列表是否不为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串拼接转列表
     * @param string
     * @return
     */
    public static ArrayList<String> stringsToList(String string) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }

        String[] split = string.split(",");
        ArrayList<String> resultList = new ArrayList<>();
        for (String s : split) {
            resultList.add(s);
        }
        return resultList;
    }

    /**
     * 列表转字符串拼接
     *
     * @param stringList
     * @return
     */
    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < stringList.size(); i++) {
            result.append(stringList.get(i));
            if (i != stringList.size() - 1)
                result.append(",");
        }
        return result.toString();
    }

}
