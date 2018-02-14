package com.handsomezhou.xdesktophelper.baidu.aip.model;

import java.util.List;

/**
 * Created by handsomezhou on 2018/2/7.
 */

public class NlpLexer {
    /**
     * items : [{"basic_words":["请"],"byte_length":2,"byte_offset":0,"formal":"","item":"请","loc_details":[],"ne":"","pos":"v","uri":""},{"basic_words":["给"],"byte_length":2,"byte_offset":2,"formal":"","item":"给","loc_details":[],"ne":"","pos":"p","uri":""},{"basic_words":["他"],"byte_length":2,"byte_offset":4,"formal":"","item":"他","loc_details":[],"ne":"","pos":"r","uri":""},{"basic_words":["来"],"byte_length":2,"byte_offset":6,"formal":"","item":"来","loc_details":[],"ne":"","pos":"v","uri":""},{"basic_words":["一","瓶"],"byte_length":4,"byte_offset":8,"formal":"","item":"一瓶","loc_details":[],"ne":"","pos":"m","uri":""},{"basic_words":["怡宝"],"byte_length":4,"byte_offset":12,"formal":"","item":"怡宝","loc_details":[],"ne":"ORG","pos":"","uri":""},{"basic_words":["矿泉","水"],"byte_length":6,"byte_offset":16,"formal":"","item":"矿泉水","loc_details":[],"ne":"","pos":"n","uri":""},{"basic_words":["两","瓶"],"byte_length":4,"byte_offset":22,"formal":"","item":"两瓶","loc_details":[],"ne":"","pos":"m","uri":""},{"basic_words":["阿萨姆","奶茶"],"byte_length":10,"byte_offset":26,"formal":"","item":"阿萨姆奶茶","loc_details":[],"ne":"","pos":"nz","uri":""},{"basic_words":["三","瓶"],"byte_length":4,"byte_offset":36,"formal":"","item":"三瓶","loc_details":[],"ne":"","pos":"m","uri":""},{"basic_words":["维","他","奶"],"byte_length":6,"byte_offset":40,"formal":"","item":"维他奶","loc_details":[],"ne":"","pos":"nz","uri":""}]
     * log_id : 3640937809900153000
     * text : 请给他来一瓶怡宝矿泉水两瓶阿萨姆奶茶三瓶维他奶
     */

    private long log_id;
    private String text;
    private List<NlpItem> items;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<NlpItem> getItems() {
        return items;
    }

    public void setItems(List<NlpItem> items) {
        this.items = items;
    }

  
}
