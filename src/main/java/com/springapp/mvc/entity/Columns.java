package com.springapp.mvc.entity;

import com.springapp.common.entity.BaseEntity;

public class Columns extends BaseEntity{

    // 列名
    private String columnName;
    // 列名类型
    private String dataType;
    // 列名备注
    private String columnComment;

    private String columnKey;

    // auto_increment
    private String extra;

    public Columns() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
