package com.niceinterface.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.niceinterface.R;
import com.niceinterface.bean.FunctionParams;
import com.niceinterface.fragment.BaseFragment;
import com.niceinterface.fragment.BlankFragment1;
import com.niceinterface.fragment.BlankFragment2;
import com.niceinterface.fragment.BlankFragment3;
import com.niceinterface.struct.FunctionManger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private MainActivity selfActivity = MainActivity.this;

    private BottomNavigationBar mainBar;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private BlankFragment1 bFragment;
    private BlankFragment2 bFragment2;
    private BlankFragment3 bFragment3;

    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void setFunctionForFragment(int fragmentId) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
        BaseFragment baseFragment = (BaseFragment) fragmentManager.findFragmentById(fragmentId);
        baseFragment.setFunctionManger(new FunctionManger().addFunction(new FunctionManger.FunctionWithNothing(BlankFragment1.INTERFACE) {
            @Override
            public void function() {
                Toast.makeText(MainActivity.this, "成功调用无参无返回值方法", Toast.LENGTH_LONG).show();
            }
        }).addFunction(new FunctionManger.FunctionWithResult<String>(BlankFragment1.INTERFACE_HAVE_RESULT) {
            @Override
            public String function() {
                Toast.makeText(MainActivity.this, "成功调用无参有返回值方法", Toast.LENGTH_LONG).show();
                return "恭喜你，调用成功！";
            }
        }).addFunction(new FunctionManger.FunctionWithParam<Integer>(BlankFragment1.INTERFACE_HAVE_PARAM) {
            @Override
            public void function(Integer o) {
                Toast.makeText(MainActivity.this, "成功调用有参无返回值方法 参数值=" + o, Toast.LENGTH_LONG).show();
            }
        }).addFunction(new FunctionManger.FunctionWithHaving<List, Integer>(BlankFragment1.INTERFACE_HAVE_PARAM_RESULT) {

            @Override
            public List function(Integer data) {
                Toast.makeText(MainActivity.this, "成功调用有参有返回值方法 参数值=" + data, Toast.LENGTH_LONG).show();
                List<String> result = new ArrayList<>();
                result.add("1");
                result.add("2");
                result.add("3");
                return result;
            }
        }).addFunction(new FunctionManger.FunctionWithParam<FunctionParams>(BlankFragment1.INTERFACE_HAVE_MORE_PARAM) {

            @Override
            public void function(FunctionParams functionParams) {
                if (functionParams != null) {
                    Toast.makeText(MainActivity.this, "成功调用多个参数的方法 参数值=" + functionParams.getString() + " 参数1=" + functionParams.getString() + " 参数2=" + functionParams.getInt(), Toast.LENGTH_LONG).show();
                }
            }
        }).addFunction(new FunctionManger.FunctionWithParam<Bundle>(BlankFragment1.INTERFACE_HAVE_MORE_PARAM_NO_RESULT) {

            @Override
            public void function(Bundle bundle) {
                if (bundle != null) {
                    Toast.makeText(MainActivity.this, "成功调用多个参数的方法 参数值=" + bundle.getString("p") + " 参数1=" + bundle.getString("p1") + " 参数2=" + bundle.getInt("p2"), Toast.LENGTH_LONG).show();
                }
            }
        }).addFunction(new FunctionManger.FunctionWithHaving<List, Bundle>(BlankFragment1.INTERFACE_HAVE_MORE_PARAM_RESULT) {
            @Override
            public List function(Bundle data) {
                Toast.makeText(MainActivity.this, "成功调用多个参数的方法 参数值=" + data.getString("p") + " 参数1=" + data.getInt("p1") + " 参数2=" + data.getString("p2"), Toast.LENGTH_LONG).show();
                List<String> strList = new ArrayList<>();
                strList.add("HLQ");
                strList.add("LOVE");
                strList.add("Android");
                return strList;
            }
        }));
    }

    private void initView() {

        mainBar = findViewById(R.id.bnb_main_tab);

        bFragment = new BlankFragment1();
        bFragment2 = new BlankFragment2();
        bFragment3 = new BlankFragment3();

        mFragmentList = new ArrayList<>();
        mFragmentList.add(bFragment);
        mFragmentList.add(bFragment2);
        mFragmentList.add(bFragment3);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_layout_content, bFragment);
        fragmentTransaction.commit();
        initBottomNavigationBar();
    }

    private void initBottomNavigationBar() {
        mainBar.addItem(new BottomNavigationItem(R.drawable.icon_main_pre, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.icon_main_search_pre, "你的"))
                .addItem(new BottomNavigationItem(R.drawable.icon_main_mine_pre, "我的"))
                .initialise();

        mainBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fl_layout_content, mFragmentList.get(position));
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    FunctionManger.FunctionWithNothing functionWithNothing = new FunctionManger.FunctionWithNothing(BlankFragment1.INTERFACE) {
        @Override
        public void function() {
            Toast.makeText(selfActivity, "已调用无参数无返回接口", Toast.LENGTH_SHORT).show();
        }
    };

}
