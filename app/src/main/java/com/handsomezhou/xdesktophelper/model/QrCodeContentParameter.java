package com.handsomezhou.xdesktophelper.model;

import java.io.Serializable;

/**
 * Created by handsomezhou.
 */

public class QrCodeContentParameter implements Serializable {
    private String mContent;

    public QrCodeContentParameter() {
    }

    public QrCodeContentParameter(String content) {
        mContent = content;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
}
