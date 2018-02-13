package com.handsomezhou.xdesktophelper.util;

import com.handsomezhou.xdesktophelper.baidu.aip.model.Event;
import com.handsomezhou.xdesktophelper.baidu.aip.model.NlpItem;
import com.handsomezhou.xdesktophelper.baidu.aip.model.NlpLexer;
import com.handsomezhou.xdesktophelper.baidu.aip.util.NlpLexerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsomezhou on 2018/2/13.
 * 语境分析
 */

public class ContextAnalysisUtil {
    public static String TAG="ProductInfoUtil";
    /**
     * 解析事件
     * @param nlpLexer
     * @return
     */
    public static List<Event> parse(NlpLexer nlpLexer){
        List<Event> events=new ArrayList<>();
        do{
            if(null==nlpLexer){
                break;
            }


            events.addAll(NlpLexerUtil.parseEvent(nlpLexer));

        }while (false);

        return events;
    }

}
