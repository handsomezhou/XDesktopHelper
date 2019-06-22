package com.handsomezhou.xdesktophelper.baidu.aip.util;

import com.handsomezhou.xdesktophelper.baidu.aip.constant.Abbreviations;
import com.handsomezhou.xdesktophelper.baidu.aip.constant.PartOfSpeech;
import com.handsomezhou.xdesktophelper.baidu.aip.model.NlpItem;
import com.handsomezhou.xdesktophelper.constant.Constant;
import com.handsomezhou.xdesktophelper.util.CommonUtil;

/**
 * Created by handsomezhou on 2019/6/22.
 */

public class PartOfSpeechUtil {
    public static String getPartOfSpeech(NlpItem nlpItem) {
        String partOfSpeech = Constant.NULL_STRING;

        do {
            if (null == nlpItem) {
                break;
            }

            if (CommonUtil.isEmpty(nlpItem.getPos())) {
                break;
            }

            partOfSpeech = nlpItem.getPos();

        } while (false);

        return partOfSpeech;
    }

    public static String getPartOfSpeechDesc(NlpItem nlpItem) {
        String partOfSpeechDesc = "未知词性";
        if (false == CommonUtil.isEmpty(nlpItem.getPos())) {
            switch (nlpItem.getPos()) {
                case PartOfSpeech.n:
                    partOfSpeechDesc = "普通名词";
                    break;
                case PartOfSpeech.f:
                    partOfSpeechDesc = "方位名词";
                    break;

                case PartOfSpeech.s:
                    partOfSpeechDesc = "处所名词";
                    break;
                case PartOfSpeech.t:
                    partOfSpeechDesc = "时间名词";
                    break;

                case PartOfSpeech.nr:
                    partOfSpeechDesc = "人名";
                    break;
                case PartOfSpeech.ns:
                    partOfSpeechDesc = "地名";
                    break;
                case PartOfSpeech.nt:
                    partOfSpeechDesc = "机构团体名";
                    break;

                case PartOfSpeech.nw:
                    partOfSpeechDesc = "作品名";
                    break;

                case PartOfSpeech.nz:
                    partOfSpeechDesc = "其他专名";
                    break;
                case PartOfSpeech.v:
                    partOfSpeechDesc = "普通动词";
                    break;
                case PartOfSpeech.vd:
                    partOfSpeechDesc = "动副词";
                    break;
                case PartOfSpeech.vn:
                    partOfSpeechDesc = "名动词";
                    break;
                case PartOfSpeech.a:
                    partOfSpeechDesc = "形容词";
                    break;
                case PartOfSpeech.ad:
                    partOfSpeechDesc = "副形词";
                    break;
                case PartOfSpeech.an:
                    partOfSpeechDesc = "名形词";
                    break;

                case PartOfSpeech.d:
                    partOfSpeechDesc = "副词";
                    break;
                case PartOfSpeech.m:
                    partOfSpeechDesc = "数量词";
                    break;
                case PartOfSpeech.q:
                    partOfSpeechDesc = "量词";
                    break;
                case PartOfSpeech.r:
                    partOfSpeechDesc = "代词";
                    break;

                case PartOfSpeech.p:
                    partOfSpeechDesc = "介词";
                    break;
                case PartOfSpeech.c:
                    partOfSpeechDesc = "连词";
                    break;

                case PartOfSpeech.u:
                    partOfSpeechDesc = "助词";
                    break;
                case PartOfSpeech.xc:
                    partOfSpeechDesc = "其他虚词";
                    break;
                case PartOfSpeech.w:
                    partOfSpeechDesc = "标点符号";
                    break;
                default:
                    break;

            }
        } else {
            if (false == CommonUtil.isEmpty(nlpItem.getNe())) {
                switch (nlpItem.getNe()) {
                    case Abbreviations.ORG:
                        partOfSpeechDesc = "机构名";
                        break;
                    case Abbreviations.LOC:
                        partOfSpeechDesc = "地名";
                        break;
                    case Abbreviations.PER:
                        partOfSpeechDesc = "人名";
                        break;
                    case Abbreviations.TIME:
                        partOfSpeechDesc = "时间";
                        break;
                    default:
                        break;
                }
            }

        }

        return partOfSpeechDesc;
    }
}
