package com.rdc.musicplayer.musicplayer.bean;

import java.util.List;

public class OnLineMusic {

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {

        private int ret_code;
        private PagebeanBean pagebean;

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public PagebeanBean getPagebean() {
            return pagebean;
        }

        public void setPagebean(PagebeanBean pagebean) {
            this.pagebean = pagebean;
        }

        public static class PagebeanBean {

            private int allPages;
            private int ret_code;
            private int currentPage;
            private String notice;
            private int allNum;
            private int maxResult;
            private List<ContentlistBean> contentlist;

            public int getAllPages() {
                return allPages;
            }

            public void setAllPages(int allPages) {
                this.allPages = allPages;
            }

            public int getRet_code() {
                return ret_code;
            }

            public void setRet_code(int ret_code) {
                this.ret_code = ret_code;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public int getAllNum() {
                return allNum;
            }

            public void setAllNum(int allNum) {
                this.allNum = allNum;
            }

            public int getMaxResult() {
                return maxResult;
            }

            public void setMaxResult(int maxResult) {
                this.maxResult = maxResult;
            }

            public List<ContentlistBean> getContentlist() {
                return contentlist;
            }

            public void setContentlist(List<ContentlistBean> contentlist) {
                this.contentlist = contentlist;
            }

            public static class ContentlistBean {

                private String m4a;
                private String media_mid;
                private int songid;
                private int singerid;
                private String albumname;
                private String downUrl;
                private String singername;
                private String songname;
                private String strMediaMid;
                private String albummid;
                private String songmid;
                private String albumpic_big;
                private String albumpic_small;
                private int albumid;

                public String getM4a() {
                    return m4a;
                }

                public void setM4a(String m4a) {
                    this.m4a = m4a;
                }

                public String getMedia_mid() {
                    return media_mid;
                }

                public void setMedia_mid(String media_mid) {
                    this.media_mid = media_mid;
                }

                public int getSongid() {
                    return songid;
                }

                public void setSongid(int songid) {
                    this.songid = songid;
                }

                public int getSingerid() {
                    return singerid;
                }

                public void setSingerid(int singerid) {
                    this.singerid = singerid;
                }

                public String getAlbumname() {
                    return albumname;
                }

                public void setAlbumname(String albumname) {
                    this.albumname = albumname;
                }

                public String getDownUrl() {
                    return downUrl;
                }

                public void setDownUrl(String downUrl) {
                    this.downUrl = downUrl;
                }

                public String getSingername() {
                    return singername;
                }

                public void setSingername(String singername) {
                    this.singername = singername;
                }

                public String getSongname() {
                    return songname;
                }

                public void setSongname(String songname) {
                    this.songname = songname;
                }

                public String getStrMediaMid() {
                    return strMediaMid;
                }

                public void setStrMediaMid(String strMediaMid) {
                    this.strMediaMid = strMediaMid;
                }

                public String getAlbummid() {
                    return albummid;
                }

                public void setAlbummid(String albummid) {
                    this.albummid = albummid;
                }

                public String getSongmid() {
                    return songmid;
                }

                public void setSongmid(String songmid) {
                    this.songmid = songmid;
                }

                public String getAlbumpic_big() {
                    return albumpic_big;
                }

                public void setAlbumpic_big(String albumpic_big) {
                    this.albumpic_big = albumpic_big;
                }

                public String getAlbumpic_small() {
                    return albumpic_small;
                }

                public void setAlbumpic_small(String albumpic_small) {
                    this.albumpic_small = albumpic_small;
                }

                public int getAlbumid() {
                    return albumid;
                }

                public void setAlbumid(int albumid) {
                    this.albumid = albumid;
                }
            }
        }
    }
}
