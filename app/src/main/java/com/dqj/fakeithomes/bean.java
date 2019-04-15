package com.dqj.fakeithomes;

import java.util.List;

public class bean {




    private boolean lapin;
    private List<?> toplist;
    private List<NewslistBean> newslist;

    public boolean isLapin() {
        return lapin;
    }

    public void setLapin(boolean lapin) {
        this.lapin = lapin;
    }

    public List<?> getToplist() {
        return toplist;
    }

    public void setToplist(List<?> toplist) {
        this.toplist = toplist;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
      public   NewslistBean(String title,int sid,String postdate,String image){
          this.title=title;
          this.postdate=postdate;
          this.image=image;
          this.sid=sid;



      }
        /**
         * newsid : 413374
         * title : 小米王腾：无线充电鼠标垫已在路上，还能更酷
         * postdate : 2019-03-09T23:54:50.357
         * orderdate : 2019-03-09T23:54:50.357
         * description : 小米9手机搭载了全球首款20W无线闪充，堪比有线快充。现在小米产品总监王腾微博表示，支持无线充电的大鼠标垫已经在路上了，相比网友的一些想法，可能更酷一些。
         * image : http://img.ithome.com/newsuploadfiles/thumbnail/2019/3/413374_240.jpg?r=1552146890357
         * hitcount : 9849
         * commentcount : 101
         * cid : 71
         * sid : 0
         * url : /0/413/374.htm
         * v : 001
         * lapinid : 1829277
         * imagelist : ["http://img.ithome.com/newsuploadfiles/2019/3/20190309_210543_50.jpg@s_2,w_240,h_180","http://img.ithome.com/newsuploadfiles/2019/3/20190306_004559_435.png@s_2,w_240,h_180","http://img.ithome.com/newsuploadfiles/2019/3/20190306_004634_944.png@s_2,w_240,h_180"]
         */

        private int newsid;
        private String title;
        private String postdate;
        private String orderdate;
        private String description;
        private String image;
        private int hitcount;
        private int commentcount;
        private int cid;
        private int sid;
        private String url;
        private String v;
        private int lapinid;
        private List<String> imagelist;

        public int getNewsid() {
            return newsid;
        }

        public void setNewsid(int newsid) {
            this.newsid = newsid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPostdate() {
            return postdate;
        }

        public void setPostdate(String postdate) {
            this.postdate = postdate;
        }

        public String getOrderdate() {
            return orderdate;
        }

        public void setOrderdate(String orderdate) {
            this.orderdate = orderdate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getHitcount() {
            return hitcount;
        }

        public void setHitcount(int hitcount) {
            this.hitcount = hitcount;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }

        public int getLapinid() {
            return lapinid;
        }

        public void setLapinid(int lapinid) {
            this.lapinid = lapinid;
        }

        public List<String> getImagelist() {
            return imagelist;
        }

        public void setImagelist(List<String> imagelist) {
            this.imagelist = imagelist;
        }
    }
}
