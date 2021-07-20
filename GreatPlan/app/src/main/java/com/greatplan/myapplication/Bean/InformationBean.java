package com.greatplan.myapplication.Bean;

import java.util.List;

/**
 * @Author :jack
 * @Date :2021/7/19
 * @Effect :
 **/
public class InformationBean {


    /**
     * result : [{"collection":53,"id":68,"releaseTime":1573023083000,"share":528,"source":"ITBEAR科技资讯","summary":"11月6日消息，今日在第二届中国国际进口博览会上，诺基亚与中国三大运营商分别签署2020年合作框架协议，总价值157亿元人民币。","thumbnail":"http://www.itbear.com.cn/upload/2020-04/200424143888992.jpg","title":"诺基亚与三大运营商分别签约，总价值157亿元","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":35,"id":67,"releaseTime":1572936683000,"share":36,"source":"ITBEAR科技资讯","summary":"最近，百度李彦宏曾多次演示的小度人工智能音箱入驻了杭州市萧山区的一家盲人推拿店，给盲人按摩师带来了意想不到的帮助。","thumbnail":"http://www.itbear.com.cn/upload/2020-04/2004241500311714.png","title":"这次是杭州！快来看李彦宏的\u201cAI助盲\u201d路线图","whetherAdvertising":2,"whetherCollection":1,"whetherPay":2},{"collection":0,"id":0,"infoAdvertisingVo":{"content":"科大讯飞机器人","id":7,"pic":"http://mobile.bwstudent.com/images/tech/ad/kdxf.png","url":"https://item.jd.com/5275308.html"},"share":0,"whetherAdvertising":1,"whetherCollection":0,"whetherPay":0},{"collection":32,"id":65,"releaseTime":1572763883000,"share":2300,"source":"ITBEAR科技资讯","summary":"双11在即，国内三大智能音箱品牌均面向用户大幅让利。天猫精灵X1和小米AI音箱到手价格均降至199元，带屏音箱天猫精灵CCL和小度在家1S到手价格低至299元，竞争进入肉搏战。","thumbnail":"http://www.itbear.com.cn/upload/2020-04/200424150031177.png","title":"2019年Q3智能音箱数据发布，天猫精灵再次国内第一","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":23,"id":64,"releaseTime":1572677483000,"share":58,"source":"ITBEAR科技资讯","summary":"中国已明确要把区块链作为核心技术自主创新的重要突破口。10月25日，新华社、人民日报等多家集体发布《加快推动区块链技术和产业创新发展》报道，提出加大投入力度，着力攻克一批关键核心技术，加快推动区块链技术和产业创新发展。","thumbnail":"http://img.zhiding.cn/5/473/liiX2naemnf3A.jpg?rand=187","title":"区块链\u201c地位\u201d提升，百度智能云助力国家加快推动区块链产业自主创新","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * collection : 53
         * id : 68
         * releaseTime : 1573023083000
         * share : 528
         * source : ITBEAR科技资讯
         * summary : 11月6日消息，今日在第二届中国国际进口博览会上，诺基亚与中国三大运营商分别签署2020年合作框架协议，总价值157亿元人民币。
         * thumbnail : http://www.itbear.com.cn/upload/2020-04/200424143888992.jpg
         * title : 诺基亚与三大运营商分别签约，总价值157亿元
         * whetherAdvertising : 2
         * whetherCollection : 2
         * whetherPay : 2
         * infoAdvertisingVo : {"content":"科大讯飞机器人","id":7,"pic":"http://mobile.bwstudent.com/images/tech/ad/kdxf.png","url":"https://item.jd.com/5275308.html"}
         */

        private int collection;
        private int id;
        private long releaseTime;
        private int share;
        private String source;
        private String summary;
        private String thumbnail;
        private String title;
        private int whetherAdvertising;
        private int whetherCollection;
        private int whetherPay;
        private InfoAdvertisingVoBean infoAdvertisingVo;

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherAdvertising() {
            return whetherAdvertising;
        }

        public void setWhetherAdvertising(int whetherAdvertising) {
            this.whetherAdvertising = whetherAdvertising;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherPay() {
            return whetherPay;
        }

        public void setWhetherPay(int whetherPay) {
            this.whetherPay = whetherPay;
        }

        public InfoAdvertisingVoBean getInfoAdvertisingVo() {
            return infoAdvertisingVo;
        }

        public void setInfoAdvertisingVo(InfoAdvertisingVoBean infoAdvertisingVo) {
            this.infoAdvertisingVo = infoAdvertisingVo;
        }

        public static class InfoAdvertisingVoBean {
            /**
             * content : 科大讯飞机器人
             * id : 7
             * pic : http://mobile.bwstudent.com/images/tech/ad/kdxf.png
             * url : https://item.jd.com/5275308.html
             */

            private String content;
            private int id;
            private String pic;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
