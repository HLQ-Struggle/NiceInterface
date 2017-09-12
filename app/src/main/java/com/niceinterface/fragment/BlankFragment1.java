package com.niceinterface.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.niceinterface.R;
import com.niceinterface.struct.FunctionParamsBuilder;

import java.util.List;

public class BlankFragment1 extends BaseFragment {

    /**
     * 定义接口
     */
    public static final String INTERFACE = BlankFragment1.class.getName() + "NPNR";
    public static final String INTERFACE_HAVE_RESULT = BlankFragment1.class.getName() + "NPYR";
    public static final String INTERFACE_HAVE_PARAM = BlankFragment1.class.getName() + "YPNR";
    public static final String INTERFACE_HAVE_PARAM_RESULT = BlankFragment1.class.getName() + "YPYR";
    public static final String INTERFACE_HAVE_MORE_PARAM = BlankFragment1.class.getName() + "MP";
    public static final String INTERFACE_HAVE_MORE_PARAM_NO_RESULT = BlankFragment1.class.getName() + "MPNR";
    public static final String INTERFACE_HAVE_MORE_PARAM_RESULT = BlankFragment1.class.getName() + "MPNRYP";

    private Button btn, btn2, btn3, btn4, btn5, btn6, btn7;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_blank, null);
        btn = view.findViewById(R.id.btn_fragment_1);
        btn2 = view.findViewById(R.id.btn_fragment_2);
        btn3 = view.findViewById(R.id.btn_fragment_3);
        btn4 = view.findViewById(R.id.btn_fragment_4);
        btn5 = view.findViewById(R.id.btn_fragment_5);
        btn6 = view.findViewById(R.id.btn_fragment_6);
        btn7 = view.findViewById(R.id.btn_fragment_7);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFunctionManger.invokeFunction(INTERFACE);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFunctionManger.invokeFunction(INTERFACE_HAVE_RESULT);
                String result = mFunctionManger.invokeFunctionWithResult(INTERFACE_HAVE_RESULT, String.class);
                btn2.setText(result);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFunctionManger.invokeFunction(INTERFACE_HAVE_PARAM, 100);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> strList = mFunctionManger.invokeFuncWithResult(INTERFACE_HAVE_PARAM_RESULT, 100, List.class);
                if (strList != null) {
                    String st = "";
                    for (int i = 0; i < strList.size(); i++) {
                        st += strList.get(i) + " ";
                    }
                    btn4.setText(st);
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFunctionManger.invokeFunction(INTERFACE_HAVE_MORE_PARAM,
                        new FunctionParamsBuilder().putString("你好")
                                .putString("我是fragment").putInt(200).create());
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("p", "你好activity");
                bundle.putString("p1", "我是fragment");
                bundle.putInt("p2", 200);
                mFunctionManger.invokeFunction(INTERFACE_HAVE_MORE_PARAM_NO_RESULT, bundle);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("p", "你好activity2");
                bundle.putString("p1", "我是fragment2");
                bundle.putInt("p2", 300);
                List<String> strList = mFunctionManger.invokeFuncWithResult(INTERFACE_HAVE_MORE_PARAM_RESULT, bundle, List.class);
                btn7.setText("接收返回个数：" + strList.size() + " Result1:" + strList.get(0).toString() + " Result2:" + strList.get(1).toString() + " Result3:" + strList.get(2).toString());
            }
        });
    }
}
