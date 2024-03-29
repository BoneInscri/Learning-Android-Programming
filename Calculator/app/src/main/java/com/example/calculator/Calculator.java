package com.example.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {
    BigDecimal x,y,num_ans;
    String num1,num2,op,str_ans;// number1 number2 operation answer
    boolean floatflag1,floatflag2;// 是否是浮点数
    int scale;
    enum state{state_init,state_i1,state_i2,state_res};
    state s;// 状态
    public void clear()
    {
        num1="0";
        num2="";
        op="";
        str_ans="";
        s=state.state_init;
        x=BigDecimal.valueOf(0);
        y=BigDecimal.valueOf(0);
        num_ans=BigDecimal.valueOf(0);
    }
    public Calculator()
    {
        scale=2;
        floatflag1=false;
        floatflag2=false;
        clear();
    }
    public void process(String show)
    {
        // 数字
        if(show.charAt(0)>='0'&&show.charAt(0)<='9')
        {
            switch(s)
            {
                case state_init:
                    if(show.charAt(0)=='0') break;
                    num1=show;
                    s=state.state_i1;
                    break;
                case state_i1:
                    num1+=show;
                    break;
                case state_i2:
                    num2+=show;
                    break;
                case state_res:
                    clear();
                    process(show);
                    break;//如果是最后的状态，继续处理
            }

        }
        // 操作符
        else
        {
            switch(show)
            {
                case "C":
                    clear();
                    break;
                    // 加法和减法处理方式相同
                case "+":
                case "-":
                    switch (s)
                    {
                        case state_init:
                        case state_i1:
                            op=show;
                            s=state.state_i2;// 变为第二个数的输入
                            break;
                        case state_i2:
                            if(num2=="")
                            {
                                op=show;// 重复输入两个加号
                                break;
                            }
                            num1=getRes();// 将这一次的运算结果
                            num2="";
                            str_ans="";
                            op=show;
                            break;
                        case state_res:
                            if(str_ans.contains("error"))
                            {
                                clear();
                                break;
                            }
                            op=show;
                            num1=str_ans;
                            num2="";
                            str_ans="";// 清空
                            s=state.state_i2;// 可能
                            break;
                    }
                    break;
                // 转化到输入第二个数
                case "×":
                case "÷":
                    switch(s)
                    {
                        case state_init:
                        case state_i1:
                            op=show;
                            s=state.state_i2;
                            break;
                        case state_i2:
                            if(num2=="")
                            {
                                op=show;
                                break;
                            }
                            num1=getRes();
                            num2="";
                            str_ans="";
                            op=show;
                            break;
                        case state_res:
                            if(str_ans.contains("error"))
                            {
                                clear();
                                break;
                            }
                            op=show;
                            num1=str_ans;
                            num2="";
                            str_ans="";
                            s=s.state_i2;
                            break;
                    }
                    break;
                case "%":
                    switch(s)
                    {
                        case state_init:
                            break;
                        case state_i1:
                            if(num1.contains("."))// 小数不能取余
                                break;
                            op=show;
                            s=state.state_i2;
                            //因为会得到一个数直接变到输入第二个状态
                            break;
                        case state_i2:
                            if(num2=="")
                            {
                                if(!num1.contains("."))
                                {
                                    op=show;
                                    break;
                                }
                                num1=getRes();
                                num2="";
                                str_ans="";
                                op=show;
                            }
                            break;
                        case state_res:
                            if(str_ans.contains("error"))
                            {
                                clear();
                                break;
                            }
                            if(str_ans.contains("."))
                                break;//?
                            op=show;
                            num1=str_ans;
                            num2="";
                            str_ans="";
                            s=state.state_i2;
                            break;
                    }
                    break;
                case ".":
                    switch (s)
                    {
                        case state_init:
                            num1+=show;
                            s=state.state_i1;
                            break;
                        case state_i1:
                            if(num1.contains(".")) break;
                            else num1+=show;
                            break;
                        case state_i2:
                            if(num2.equals(""))
                            {
                                if(op.equals("%")) break;
                                num2="0.";
                                break;
                            }
                            if(op.equals("%")||num2.contains("."))break;
                            else num2+=show;
                            break;
                        case state_res:
                            break;
                    }
                    break;
                case "=":
                    switch(s)
                    {
                        case state_i1:
                            if(num1.charAt(num1.length()-1)=='.')// 1.0
                                num1+='0';
                            str_ans=num1;
                            num1="";
                            num2="";
                            op="";
                            s=state.state_res;
                            break;
                        case state_i2:
                            if(num2=="") break;// 直接退出即可
                            if(num2.charAt(num2.length()-1)=='.')
                                break;
                            str_ans=getRes();
                            num1="";
                            num2="";
                            op="";
                            s=state.state_res;
                            break;
                        default:
                            break;
                    }
                    break;
                case "Del":
                    switch (s)
                    {
                        case state_init:
                        case state_res:
                            break;
                        case state_i1:
                            num1=num1.substring(0,num1.length()-1);
                            break;
                        case state_i2:
                            if(num2.equals(""))
                            {
                                op="";
                                s=state.state_i1;
                                break;
                            }
                            num2=num2.substring(0,num1.length()-1);
                            break;
                    }

                    break;
            }
        }
    }
    // get
    public String getNum1()
    {
        return num1;
    }
    public String getNum2()
    {
        return num2;
    }
    public String getOp()
    {
        return op;
    }
    public String getAns()
    {
        return str_ans;
    }
    // 获取答案
    public String getRes()
    {
        String ret = null;
        x=new BigDecimal(num1);
        y=new BigDecimal(num2);
        switch(op)
        {
            case "+":
                num_ans=x.add(y);
                ret=num_ans.toString();
                break;
            case "-":
                num_ans=x.subtract(y);
                ret=num_ans.toString();
                break;
            case "×":
                num_ans=x.multiply(y);
                ret=num_ans.toString();
                break;
            case "÷":
                if(y.equals(BigDecimal.valueOf(0)))
                    return ret="error:/by zero";
                num_ans=x.divide(y,scale, RoundingMode.HALF_UP);
                ret=num_ans.toString();
                break;
            case "%":
                if(y.equals(BigDecimal.valueOf(0)))
                    return str_ans="error:/by zero";
                num_ans=x.remainder(y);
                ret=num_ans.toString();
                break;
        }
        return ret;
    }
}

