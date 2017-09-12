package com.niceinterface.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.niceinterface.activity.BaseActivity;
import com.niceinterface.activity.MainActivity;
import com.niceinterface.struct.FunctionManger;

/**
 * Created by HLQ on 2017/9/8
 */
public class BaseFragment extends Fragment {

    protected BaseActivity mBaseActivity;

    protected FunctionManger mFunctionManger;

    public void setFunctionManger(FunctionManger functionManger) {
        this.mFunctionManger = functionManger;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mBaseActivity = (BaseActivity) context;
            mBaseActivity.setFunctionForFragment(getId());
        }
    }
}
