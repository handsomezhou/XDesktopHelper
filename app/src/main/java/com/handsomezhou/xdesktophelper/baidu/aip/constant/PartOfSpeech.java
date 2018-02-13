package com.handsomezhou.xdesktophelper.baidu.aip.constant;

/**
 * Created by zhoujq on 2018/2/7.
 * 词性
 */

public interface PartOfSpeech {
    /**
     http://ai.baidu.com/docs#/NLP-Java-SDK/top

     词性	含义	词性	含义	词性	含义	词性	含义
     n	普通名词	f	方位名词	s	处所名词	t	时间名词
     nr	人名	ns	地名	nt	机构团体名	nw	作品名
     nz	其他专名	v	普通动词	vd	动副词	vn	名动词
     a	形容词	ad	副形词	an	名形词	d	副词
     m	数量词	q	量词	r	代词	p	介词
     c	连词	u	助词	xc	其他虚词	w	标点符号
     */

    /**
     * 普通名词
     */
    String n="n";
    /**
     * 方位名词
     */
    String f="f";

    /**
     * 处所名词
     */
    String s="s";

    /**
     * 时间名词
     */
    String t="t";

    /**
     * 人名
     */
    String nr="nr";

    /**
     * 地名
     */
    String ns="ns";

    /**
     * 机构团体名
     */
    String nt="nt";

    /**
     * 作品名
     */
    String nw="nw";

    /**
     * 其他专名
     */
    String nz="nz";

    /**
     * 普通动词
     */
    String v="v";

    /**
     * 动副词
     */
    String vd="vd";

    /**
     * 名动词
     */
    String vn="vn";

    /**
     * 形容词
     */
    String a="a";


    /**
     * 副形词
     */
    String ad="ad";

    /**
     * 名形词
     */
    String an="an";

    /**
     * 副词
     */
    String d="d";

    /**
     * 数量词
     */
    String m="m";

    /**
     * 量词
     */
    String q="q";

    /**
     * 代词
     */
    String r="r";

    /**
     * 介词
     */
    String p="p";

    /**
     * 连词
     */
    String c="c";

    /**
     * 助词
     */
    String u="u";

    /**
     * 其他虚词
     */
    String xc="xc";

    /**
     * 标点符号
     */
    String w="w";
}
