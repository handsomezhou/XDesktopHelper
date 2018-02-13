package com.handsomezhou.xdesktophelper.baidu.aip.model;

import java.util.List;

/**
 * Created by zhoujq on 2018/2/7.
 */

public class NlpItem {
        /**
         * basic_words : ["请"]
         * byte_length : 2
         * byte_offset : 0
         * formal :
         * item : 请
         * loc_details : []
         * ne :
         * pos : v
         * uri :
         */

        private int byte_length;
        private int byte_offset;
        private String formal;
        private String item;
        private String ne;
        private String pos;
        private String uri;
        private List<String> basic_words;
        private List<?> loc_details;

        public int getByte_length() {
            return byte_length;
        }

        public void setByte_length(int byte_length) {
            this.byte_length = byte_length;
        }

        public int getByte_offset() {
            return byte_offset;
        }

        public void setByte_offset(int byte_offset) {
            this.byte_offset = byte_offset;
        }

        public String getFormal() {
            return formal;
        }

        public void setFormal(String formal) {
            this.formal = formal;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public String getNe() {
            return ne;
        }

        public void setNe(String ne) {
            this.ne = ne;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public List<String> getBasic_words() {
            return basic_words;
        }

        public void setBasic_words(List<String> basic_words) {
            this.basic_words = basic_words;
        }

        public List<?> getLoc_details() {
            return loc_details;
        }

        public void setLoc_details(List<?> loc_details) {
            this.loc_details = loc_details;
        }

}
