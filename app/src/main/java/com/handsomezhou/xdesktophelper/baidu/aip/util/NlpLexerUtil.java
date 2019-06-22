package com.handsomezhou.xdesktophelper.baidu.aip.util;

import com.handsomezhou.xdesktophelper.baidu.aip.constant.Abbreviations;
import com.handsomezhou.xdesktophelper.baidu.aip.constant.PartOfSpeech;
import com.handsomezhou.xdesktophelper.baidu.aip.model.Event;
import com.handsomezhou.xdesktophelper.baidu.aip.model.NlpItem;
import com.handsomezhou.xdesktophelper.baidu.aip.model.NlpItemKey;
import com.handsomezhou.xdesktophelper.baidu.aip.model.NlpLexer;
import com.handsomezhou.xdesktophelper.constant.Constant;
import com.handsomezhou.xdesktophelper.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsomezhou on 2018/2/7.
 */

public class NlpLexerUtil {

    /**
     * {@link NlpLexerUtil#filterNlpItemByNlpItemKey(List, List)}
     * 根据词性列表过滤nlpItems
     *
     * @param nlpItems      nlp Items
     * @param partOfSpeechs 词性列表
     * @return
     */
    public static List<NlpItem> filterNlpItemByPartOfSpeech(List<NlpItem> nlpItems, List<String> partOfSpeechs) {
        List<NlpItem> filterNlpItems = new ArrayList<>();
        do {
            if (null == nlpItems || nlpItems.size() <= 0) {
                break;
            }

            if (null == partOfSpeechs || partOfSpeechs.size() <= 0) {
                filterNlpItems.addAll(nlpItems);
                break;
            }

            int nlpItemsSize = nlpItems.size();
            int partOfSpeechsSize = partOfSpeechs.size();
            NlpItem nlpItem = null;
            String partOfSpeech = null;
            for (int i = 0; i < nlpItemsSize; i++) {
                nlpItem = nlpItems.get(i);
                for (int j = 0; j < partOfSpeechsSize; j++) {
                    partOfSpeech = partOfSpeechs.get(j);
                    if (partOfSpeech.equals(nlpItem.getPos())) {
                        filterNlpItems.add(nlpItem);
                        break;
                    }
                }

            }
        } while (false);

        return filterNlpItems;
    }

    /**
     * 根据词性过滤nlpItems
     *
     * @param nlpItems     nlp Items
     * @param partOfSpeech 词性
     * @return
     */
    public static List<NlpItem> filterNlpItemByPartOfSpeech(List<NlpItem> nlpItems, String partOfSpeech) {
        List<NlpItem> filterNlpItems = new ArrayList<>();
        do {
            if (null == nlpItems || nlpItems.size() <= 0) {
                break;
            }

            List<String> partOfSpeechs = new ArrayList<>();
            if (false == CommonUtil.isEmpty(partOfSpeech)) {
                partOfSpeechs.add(partOfSpeech);
            }
            filterNlpItems.addAll(filterNlpItemByPartOfSpeech(nlpItems, partOfSpeechs));
        } while (false);

        return filterNlpItems;
    }


    /**
     * 根据词性过滤nlpItems
     *
     * @param nlpLexer     nlp Lexer
     * @param partOfSpeech 词性
     * @return
     */
    public static List<NlpItem> filterNlpItemByPartOfSpeech(NlpLexer nlpLexer, String partOfSpeech) {
        List<NlpItem> filterNlpItems = new ArrayList<>();
        do {
            if (null == nlpLexer) {
                break;
            }

            filterNlpItems.addAll(filterNlpItemByPartOfSpeech(nlpLexer.getItems(), partOfSpeech));
        } while (false);

        return filterNlpItems;
    }

    /**
     * 根据词性列表过滤nlpItems
     *
     * @param nlpLexer      nlp Lexer
     * @param partOfSpeechs 词性列表
     * @return
     */
    public static List<NlpItem> filterNlpItemByPartOfSpeech(NlpLexer nlpLexer, List<String> partOfSpeechs) {
        List<NlpItem> filterNlpItems = new ArrayList<>();
        do {
            if (null == nlpLexer) {
                break;
            }

            filterNlpItems.addAll(filterNlpItemByPartOfSpeech(nlpLexer.getItems(), partOfSpeechs));
        } while (false);

        return filterNlpItems;
    }


    /**
     * {@link NlpLexerUtil#filterNlpItemByPartOfSpeech(List, List)}
     *
     * @param nlpItems    nlp Lexer
     * @param nlpItemKeys NlpItem 键值列表
     * @return
     */
    public static List<NlpItem> filterNlpItemByNlpItemKey(List<NlpItem> nlpItems, List<NlpItemKey> nlpItemKeys) {
        List<NlpItem> filterNlpItems = new ArrayList<>();
        do {
            if (null == nlpItems || nlpItems.size() <= 0) {
                break;
            }

            if (null == nlpItemKeys || nlpItemKeys.size() <= 0) {
                filterNlpItems.addAll(nlpItems);
                break;
            }

            int nlpItemsSize = nlpItems.size();
            int nlpItemKeysSize = nlpItemKeys.size();
            NlpItem nlpItem = null;
            NlpItemKey nlpItemKey = null;
            for (int i = 0; i < nlpItemsSize; i++) {
                nlpItem = nlpItems.get(i);
                for (int j = 0; j < nlpItemKeysSize; j++) {
                    nlpItemKey = nlpItemKeys.get(j);

                    if (false == CommonUtil.isEmpty(nlpItem.getPos())) {
                        if (nlpItem.getPos().equals(nlpItemKey.getPos())) {
                            filterNlpItems.add(nlpItem);
                            break;
                        }
                    } else {
                        if (nlpItem.getNe().equals(nlpItemKey.getNe())) {
                            filterNlpItems.add(nlpItem);
                            break;
                        }
                    }
                }

            }
        } while (false);

        return filterNlpItems;
    }

    /**
     * 根据NlpItem 键值列表过滤nlpItems
     *
     * @param nlpLexer    nlp Lexer
     * @param nlpItemKeys NlpItem 键值列表
     * @return
     */
    public static List<NlpItem> filterNlpItemByNlpItemKey(NlpLexer nlpLexer, List<NlpItemKey> nlpItemKeys) {
        List<NlpItem> filterNlpItems = new ArrayList<>();
        do {
            if (null == nlpLexer) {
                break;
            }

            filterNlpItems.addAll(filterNlpItemByNlpItemKey(nlpLexer.getItems(), nlpItemKeys));
        } while (false);

        return filterNlpItems;
    }

    /**
     * @return
     */
    public static List<Event> parseEvent(NlpLexer nlpLexer) {
        List<Event> events = new ArrayList<>();

        do {
            if (null == nlpLexer) {
                break;
            }

            List<NlpItemKey> nlpItemKeys = new ArrayList<>();
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.v, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.vd, null));


            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.m, null));

            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.vn, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.n, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.f, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.s, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.t, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.nr, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.ns, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.nt, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.nw, null));
            nlpItemKeys.add(new NlpItemKey(PartOfSpeech.nz, null));
            nlpItemKeys.add(new NlpItemKey(null, Abbreviations.ORG));
            nlpItemKeys.add(new NlpItemKey(null, Abbreviations.LOC));
            nlpItemKeys.add(new NlpItemKey(null, Abbreviations.ORG));
            nlpItemKeys.add(new NlpItemKey(null, Abbreviations.TIME));

            List<NlpItem> eventnlpItems = NlpLexerUtil.filterNlpItemByNlpItemKey(nlpLexer, nlpItemKeys);
            events.addAll(parseEvent(eventnlpItems));
        } while (false);

        return events;
    }


    /**
     * @param eventnlpItems
     * @return
     */
    public static List<Event> parseEvent(List<NlpItem> eventnlpItems) {
        List<Event> events = new ArrayList<>();

        do {
            if (null == eventnlpItems || eventnlpItems.size() <= 0) {
                break;
            }

            String lastAction = null;
            String lastNum = null;
            String lastUnit = null;
            /**
             * 连续名词进行合并(比如 高德地图,今日头条)
             */
            boolean lastNoun = false;
            for (NlpItem nlpItem : eventnlpItems) {
                if (isVerb(nlpItem)) {
                    lastNoun = false;
                    lastAction = nlpItem.getItem();
                } else if (isNum(nlpItem)) {
                    lastNoun = false;
                    if (nlpItem.getBasic_words().size() <= 1) {
                        lastNum = nlpItem.getItem();
                    } else {
                        lastNum = nlpItem.getBasic_words().get(Constant.ZERO_OF_INTEGER);
                        lastUnit = nlpItem.getBasic_words().get(Constant.ONE_OF_INTEGER);
                    }
                } else {
                    if (isNoun(nlpItem)) {
                       /* if(true==lastNoun){
                            /
                        }*/
                        lastNoun = true;
                        Event event = new Event();
                        if (false == CommonUtil.isEmpty(lastAction)) {
                            event.setAction(lastAction);
                            lastAction = null;
                        }

                        if (false == CommonUtil.isEmpty(lastNum)) {
                            event.setNum(lastNum);
                            event.setUnit(lastUnit);
                            lastNum = null;
                            lastUnit = null;
                        }
                        event.setContext(nlpItem.getItem());
                        events.add(event);
                    } else {
                        lastNoun = false;
                    }
                }
            }

        } while (false);

        return events;
    }

    /**
     * 是否动词
     *
     * @param nlpItem
     * @return
     */
    public static boolean isVerb(NlpItem nlpItem) {
        boolean verb = false;

        do {
            if (null == nlpItem) {
                break;
            }

            if (true == CommonUtil.isEmpty(nlpItem.getPos())) {
                break;
            }

            switch (nlpItem.getPos()) {
                case PartOfSpeech.v:
                case PartOfSpeech.vd:
                    // case PartOfSpeech.vn:
                    verb = true;
                    break;
                default:
                    break;
            }

        } while (false);

        return verb;
    }

    /**
     * 是否数量词
     *
     * @param nlpItem
     * @return
     */
    public static boolean isNum(NlpItem nlpItem) {
        boolean num = false;

        do {
            if (null == nlpItem) {
                break;
            }

            if (PartOfSpeech.m.equals(nlpItem.getPos())) {
                num = true;
                break;
            }
        } while (false);

        return num;
    }

    public static boolean isNoun(NlpItem nlpItem) {
        boolean noun = false;

        do {
            if (null == nlpItem) {
                break;
            }

            if (false == CommonUtil.isEmpty(nlpItem.getPos())) {
                switch (nlpItem.getPos()) {
                    /**
                     * 名动词
                     */
                    case PartOfSpeech.vn:
                        /**
                         * 普通名词
                         */
                    case PartOfSpeech.n:
                        /**
                         * 方位名词
                         */
                    case PartOfSpeech.f:
                        /**
                         * 处所名词
                         */
                    case PartOfSpeech.s:
                        /**
                         * 时间名词
                         */
                    case PartOfSpeech.t:
                        /**
                         * 人名
                         */
                    case PartOfSpeech.nr:
                        /**
                         * 地名
                         */
                    case PartOfSpeech.ns:
                        /**
                         * 机构团体名
                         */
                    case PartOfSpeech.nt:
                        /**
                         * 作品名
                         */
                    case PartOfSpeech.nw:
                        /**
                         * 其他专名
                         */
                    case PartOfSpeech.nz:
                        noun = true;
                        break;
                    default:
                        break;
                }
            } else {
                if (false == CommonUtil.isEmpty(nlpItem.getNe())) {
                    switch (nlpItem.getNe()) {
                        case Abbreviations.ORG:
                        case Abbreviations.LOC:
                        case Abbreviations.PER:
                        case Abbreviations.TIME:
                            noun = true;
                            break;
                        default:
                            break;
                    }
                }
            }


        } while (false);

        return noun;
    }
}
