package com.lixin.cook;

import java.util.List;

/**
 * Created by lixin on 16/9/20.
 */

public class CookListBean {



    private String msg;


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
        private int curPage;
        private int total;
        /**
         * ctgIds : ["0010001007","0010001015","0010001031"]
         * ctgTitles : 荤菜,红烧,川菜
         * menuId : 00100010070000017731
         * name : 主席红烧肉
         * recipe : {"img":"http://f2.mob.com/null/2015/08/19/1439945926624.jpg","ingredients":"[\"五花肉一斤半洗净切约2厘米见方的块，葱半两切段，老姜一块拍破，花椒二十余粒，八角四、五个粒，三奈四、五粒，冰糖半两，红烧酱油三大匙，盐适量，鲜汤约二斤。\"]","method":"[{\"img\":\"http://f2.mob.com/null/2015/08/19/1439945926890.jpg\",\"step\":\"1.将肉放锅内，加入鲜汤（清水也可）置旺火上烧沸，撇去浮沫\"},{\"img\":\"http://f2.mob.com/null/2015/08/19/1439945927086.jpg\",\"step\":\"2.放入葱、姜、花椒、盐、八角、三奈、冰糖、红烧酱油\"},{\"img\":\"http://f2.mob.com/null/2015/08/19/1439945927411.jpg\",\"step\":\"3.改用微火，一直保持沸面不腾，烧至汁浓肉粑，捡去姜、葱不要，起锅即成\"}]","sumary":"主席红烧肉属于川菜中的一道经典菜，它是四川、重庆许多地区许多人家接待重要客人才做的一道菜。主要原料是猪肉，口味是香，工艺是烧，难度属于中级。","title":"怎样做好川味经典主席红烧肉"}
         * thumbnail : http://f2.mob.com/null/2015/08/19/1439945911619.jpg
         */

        private List<ListBean> list;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private String ctgTitles;
            private String menuId;
            private String name;
            /**
             * img : http://f2.mob.com/null/2015/08/19/1439945926624.jpg
             * ingredients : ["五花肉一斤半洗净切约2厘米见方的块，葱半两切段，老姜一块拍破，花椒二十余粒，八角四、五个粒，三奈四、五粒，冰糖半两，红烧酱油三大匙，盐适量，鲜汤约二斤。"]
             * method : [{"img":"http://f2.mob.com/null/2015/08/19/1439945926890.jpg","step":"1.将肉放锅内，加入鲜汤（清水也可）置旺火上烧沸，撇去浮沫"},{"img":"http://f2.mob.com/null/2015/08/19/1439945927086.jpg","step":"2.放入葱、姜、花椒、盐、八角、三奈、冰糖、红烧酱油"},{"img":"http://f2.mob.com/null/2015/08/19/1439945927411.jpg","step":"3.改用微火，一直保持沸面不腾，烧至汁浓肉粑，捡去姜、葱不要，起锅即成"}]
             * sumary : 主席红烧肉属于川菜中的一道经典菜，它是四川、重庆许多地区许多人家接待重要客人才做的一道菜。主要原料是猪肉，口味是香，工艺是烧，难度属于中级。
             * title : 怎样做好川味经典主席红烧肉
             */

            private RecipeBean recipe;
            private String thumbnail;
            private List<String> ctgIds;

            public String getCtgTitles() {
                return ctgTitles;
            }

            public void setCtgTitles(String ctgTitles) {
                this.ctgTitles = ctgTitles;
            }

            public String getMenuId() {
                return menuId;
            }

            public void setMenuId(String menuId) {
                this.menuId = menuId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public RecipeBean getRecipe() {
                return recipe;
            }

            public void setRecipe(RecipeBean recipe) {
                this.recipe = recipe;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public List<String> getCtgIds() {
                return ctgIds;
            }

            public void setCtgIds(List<String> ctgIds) {
                this.ctgIds = ctgIds;
            }

            public static class RecipeBean {
                private String img;
                private String ingredients;
                private String method;
                private String sumary;
                private String title;

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getIngredients() {
                    return ingredients;
                }

                public void setIngredients(String ingredients) {
                    this.ingredients = ingredients;
                }

                public String getMethod() {
                    return method;
                }

                public void setMethod(String method) {
                    this.method = method;
                }

                public String getSumary() {
                    return sumary;
                }

                public void setSumary(String sumary) {
                    this.sumary = sumary;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
    }
}
