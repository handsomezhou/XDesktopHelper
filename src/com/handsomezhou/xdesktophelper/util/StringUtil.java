
package com.handsomezhou.xdesktophelper.util;


import com.handsomezhou.xdesktophelper.model.Constant;

public class StringUtil {
    public static String praseSortKey(String sortKey) {
        if (null == sortKey || sortKey.length() <= 0) {
            return null;
        }

        if ((sortKey.charAt(0) >= Constant.a && sortKey.charAt(0) <= Constant.z)
                || (sortKey.charAt(0) >= Constant.A && sortKey.charAt(0) <= Constant.Z)) {
            return sortKey;
        }

        return String.valueOf(Constant.NUMBER_SIGN) + sortKey;
    }
}
