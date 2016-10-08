package com.lixin.cook;

import java.util.List;

/**
 * Created by lixin on 16/10/7.
 */

public class CookMethodRes {


    /**
     * img : http://f2.mob.com/null/2015/08/19/1439945926890.jpg
     * step : 1.将肉放锅内，加入鲜汤（清水也可）置旺火上烧沸，撇去浮沫
     */

    private List<MethodsBean> methods;

    public List<MethodsBean> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodsBean> methods) {
        this.methods = methods;
    }

    public static class MethodsBean {
        private String img;
        private String step;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }
}
