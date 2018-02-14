package com.handsomezhou.xdesktophelper.baidu.aip.model;

/**
 * Created by handsomezhou on 2018/2/7.
 * NlpItem 键值
 */

public class NlpItemKey {
/**
 * http://ai.baidu.com/docs#/NLP-Java-SDK/bb45f0c5
 * 词法分析 返回数据参数详情
 * +ne	string	是	命名实体类型，命名实体识别算法使用。词性标注算法中，此项为空串
 +pos	string	是	词性，词性标注算法使用。命名实体识别算法中，此项为空串
 */

    private String pos;
    private String ne;

    public NlpItemKey() {
    }

    public NlpItemKey(String pos, String ne) {
        this.pos = pos;
        this.ne = ne;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getNe() {
        return ne;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }
}
