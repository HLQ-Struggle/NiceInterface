package com.niceinterface.bean;

import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HLQ on 2017/9/12
 */
public class FunctionParams {

    private Bundle mParams = new Bundle(1);
    private int mIndex = -1;
    private Map mObjectParams = new HashMap(1);

    public FunctionParams(Bundle mParams, Map mObjectParams) {
        this.mParams = mParams;
        this.mObjectParams = mObjectParams;
    }

    public <Param> Param getObject(Class<Param> p) {
        if (mObjectParams == null) {
            return null;
        }
        return p.cast(mObjectParams.get((mIndex++) + ""));
    }

    /**
     * 获取int值
     *
     * @return
     */
    public int getInt() {
        if (mParams != null) {
            return mParams.getInt((mIndex++) + "");
        }
        return 0;
    }

    /**
     * 获取int值
     *
     * @param defalut
     * @return
     */
    public int getInt(int defalut) {
        if (mParams != null) {
            return mParams.getInt((mIndex++) + "");
        }
        return defalut;
    }

    /**
     * 获取字符串
     *
     * @param defalut
     * @return
     */
    public String getString(String defalut) {
        if (mParams != null) {
            return mParams.getString((mIndex++) + "");
        }
        return defalut;
    }

    /**
     * 获取字符串
     *
     * @return
     */
    public String getString() {
        if (mParams != null) {
            return mParams.getString((mIndex++) + "");
        }
        return null;
    }

    /**
     * 获取Boolean值
     *
     * @return 默认返回false
     */
    public boolean getBoolean() {
        if (mParams != null) {
            return mParams.getBoolean((mIndex++) + "");
        }
        return false;
    }

}
