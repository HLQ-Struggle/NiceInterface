package com.niceinterface.struct;

import android.os.Bundle;

import com.niceinterface.bean.FunctionParams;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HLQ on 2017/9/12
 */

public class FunctionParamsBuilder {

    private Bundle mParams;
    private int mIndex = -1;
    private Map mObjectParams = new HashMap(1);

    public FunctionParamsBuilder() {

    }

    public FunctionParams create() {
        FunctionParams instance = new FunctionParams(mParams, mObjectParams);
        return instance;
    }

    public FunctionParamsBuilder putInt(int value) {
        if (mParams == null) {
            mParams = new Bundle(2);
        }
        mParams.putInt((mIndex++) + "", value);
        return this;
    }

    public FunctionParamsBuilder putString(String value) {
        if (mParams == null) {
            mParams = new Bundle(2);
        }
        mParams.putString((mIndex++) + "", value);
        return this;
    }

    public FunctionParamsBuilder putBoolean(boolean value) {
        if (mParams == null) {
            mParams = new Bundle(2);
        }
        mParams.putBoolean((mIndex++) + "", value);
        return this;
    }

    public FunctionParamsBuilder putObject(Object value) {
        if (mObjectParams == null) {
            mObjectParams = new HashMap(1);
        }
        mObjectParams.put((mIndex++) + "", value);
        return this;
    }

}
