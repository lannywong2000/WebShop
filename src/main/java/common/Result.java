package common;

import java.io.Serializable;

public class Result implements Serializable {
    private boolean flag;
    private Object data;
    private String msg;
    private Object data2;
    private Object data3;

    public Result() {}

    public Result(boolean flag) {this.flag = flag;}

    public Result(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public Result(boolean flag, Object data, String msg) {
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }

    public Result(boolean flag, Object data, Object data2, String msg) {
        this.flag = flag;
        this.data = data;
        this.data2 = data2;
        this.msg = msg;
    }

    public Result(boolean flag, Object data, Object data2, Object data3, String msg) {
        this.flag = flag;
        this.data = data;
        this.data2 = data2;
        this.data3 = data3;
        this.msg = msg;
    }

    public boolean getFlag() {return flag;}

    public boolean isFlag() {
        return flag;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }

    public void setFlag(boolean flag) {this.flag = flag;}

    public Object getData() {return data;}

    public void setData(Object data) {this.data = data;}

    public String getMsg() {return msg;}

    public void setMsg(String msg) {this.msg = msg;}
}
