package com.handsomezhou.xdesktophelper.model.database;

import com.android.commontools.util.TimeUtil;

import org.litepal.crud.DataSupport;

/**
 * Created by handsomezhou on 2019/12/1.
 */

public class AppStartRecord extends DataSupport {
    public static final String KEY_KEY="key";

    private String key;
    private long startTime;

    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private String createTime= TimeUtil.getLogTime();
    /**
     * 更新时间
     */
    private String updateTime= TimeUtil.getLogTime();

    public AppStartRecord() {
    }

    public AppStartRecord(String key, long startTime) {
        this.key = key;
        this.startTime = startTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
