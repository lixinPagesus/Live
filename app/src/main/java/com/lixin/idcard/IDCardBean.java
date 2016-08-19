package com.lixin.idcard;

/**
 * Created by lixin on 16/8/16.
 */
public class IDCardBean {


    /**
     * msg : success
     * result : {"area":"广西壮族自治区百色市靖西县","birthday":"1980年04月11日","sex":"女"}
     * retCode : 200
     */

    private String msg;
    /**
     * area : 广西壮族自治区百色市靖西县
     * birthday : 1980年04月11日
     * sex : 女
     */

    private ResultBean result;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public static class ResultBean {
        private String area;
        private String birthday;
        private String sex;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
