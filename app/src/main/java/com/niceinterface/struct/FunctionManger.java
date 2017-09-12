package com.niceinterface.struct;

import java.util.HashMap;

/**
 * Created by HLQ on 2017/9/12
 */

public class FunctionManger {

    // 定义容器存储对应接口
    private HashMap<String, FunctionWithParam> mFunctionWithParam;
    private HashMap<String, FunctionWithResult> mFunctionWithResult;
    private HashMap<String, FunctionWithNothing> mFunctionWithNothing;
    private HashMap<String, FunctionWithHaving> mFunctionWithHaving;

    public static abstract class Function {
        public String mFunctionName;

        public Function(String functionName) {
            this.mFunctionName = functionName;
        }
    }

    /**
     * 无参数 无返回
     */
    public static abstract class FunctionWithNothing extends Function {
        public FunctionWithNothing(String functionName) {
            super(functionName);
        }

        public abstract void function();
    }

    /**
     * 有参数 无返回
     *
     * @param <Param>
     */
    public static abstract class FunctionWithParam<Param> extends Function {
        public FunctionWithParam(String functionName) {
            super(functionName);
        }

        public abstract void function(Param param);
    }

    /**
     * 有返回 无参数
     *
     * @param <Result>
     */
    public static abstract class FunctionWithResult<Result> extends Function {
        public FunctionWithResult(String functionName) {
            super(functionName);
        }

        public abstract Result function();
    }

    /**
     * 有参数 有返回
     */
    public static abstract class FunctionWithHaving<Result, Param> extends Function {
        public FunctionWithHaving(String functionName) {
            super(functionName);
        }

        public abstract Result function(Param data);
    }

    /**
     * 根据函数名 回调无返回值无参数
     *
     * @param functionName
     */
    public void invokeFunction(String functionName) {
        FunctionWithNothing functionWithNothing = null;
        if (mFunctionWithNothing != null) {
            functionWithNothing = mFunctionWithNothing.get(functionName);
            if (functionWithNothing != null) {
                functionWithNothing.function();
            }
        }
        if (functionWithNothing == null) {
            try {
                throw new FunctionException("没有此函数：" + functionName);
            } catch (FunctionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据函数名 回调有返回值无参数
     *
     * @param functionName
     * @param resultClass
     * @param <Result>
     * @return
     * @throws FunctionException
     */
    public <Result> Result invokeFunctionWithResult(String functionName, Class<Result> resultClass) {
        FunctionWithResult functionWithResult = null;
        if (mFunctionWithResult != null) {
            functionWithResult = mFunctionWithResult.get(functionName);
            if (functionWithResult != null) {
                if (resultClass != null) {
                    return resultClass.cast(functionWithResult.function());
                } else {
                    return (Result) functionWithResult.function();
                }
            }
        }
        if (functionWithResult == null) {
            try {
                throw new FunctionException("没有此函数");
            } catch (FunctionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 回调有参数 无返回函数
     *
     * @param functionName
     * @param param
     * @param <Param>
     */
    public <Param> void invokeFunction(String functionName, Param param) {
        FunctionWithParam functionWithParam = null;
        if (mFunctionWithParam != null) {
            functionWithParam = mFunctionWithParam.get(functionName);
            if (functionWithParam != null) {
                functionWithParam.function(param);
            }
        }
    }

    /**
     * 调用具有参数，同时具有返回值的函数
     *
     * @param funcName
     * @param param
     * @param <Result>
     * @param <Param>
     * @return
     */
    public <Result, Param> Result invokeFuncWithResult(String funcName, Param param, Class<Result> c)  {
        FunctionWithHaving functionWithHaving = null;
        if (mFunctionWithHaving != null) {
            functionWithHaving = mFunctionWithHaving.get(funcName);
            if (functionWithHaving != null) {
                if (c != null) {
                    return c.cast(functionWithHaving.function(param));
                } else {
                    return (Result) functionWithHaving.function(param);
                }
            }
        }
        if (functionWithHaving == null) {
            try {
                throw new FunctionException("没有此函数");
            } catch (FunctionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 添加无参数 无返回
     *
     * @param functionWithNothing
     * @return
     */
    public FunctionManger addFunction(FunctionWithNothing functionWithNothing) {
        if (functionWithNothing == null) {
            return this;
        }
        if (mFunctionWithNothing == null) {
            mFunctionWithNothing = new HashMap<>(1);
        }
        mFunctionWithNothing.put(functionWithNothing.mFunctionName, functionWithNothing);
        return this;
    }

    /**
     * 添加有返回
     *
     * @param functionWithResult
     * @return
     */
    public FunctionManger addFunction(FunctionWithResult functionWithResult) {
        if (functionWithResult == null) {
            return this;
        }
        if (mFunctionWithResult == null) {
            mFunctionWithResult = new HashMap<>(1);
        }
        mFunctionWithResult.put(functionWithResult.mFunctionName, functionWithResult);
        return this;
    }

    /**
     * 添加有参数无返回值
     *
     * @param function
     * @return
     */
    public FunctionManger addFunction(FunctionWithParam function) {
        if (function == null) {
            return this;
        }
        if (mFunctionWithParam == null) {
            mFunctionWithParam = new HashMap<>(1);
        }
        mFunctionWithParam.put(function.mFunctionName, function);
        return this;
    }

    /**
     * 添加既有参数又有返回值
     *
     * @param function
     * @return
     */
    public FunctionManger addFunction(FunctionWithHaving function) {
        if (function == null) {
            return this;
        }
        if (mFunctionWithHaving == null) {
            mFunctionWithHaving = new HashMap<>(1);
        }
        mFunctionWithHaving.put(function.mFunctionName, function);
        return this;
    }

}
