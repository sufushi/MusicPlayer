package com.rdc.musicplayer.musicplayer.bean;

import java.util.List;

public class MusicByCategory {

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

            private int total_song_num;
            private int ret_code;
            private String update_time;
            private int color;
            private int cur_song_num;
            private int comment_num;
            private int currentPage;
            private int code;
            private int song_begin;
            private String day_of_year;
            private int totalpage;
            private List<SonglistBean> songlist;

            public int getTotal_song_num() {
                return total_song_num;
            }

            public void setTotal_song_num(int total_song_num) {
                this.total_song_num = total_song_num;
            }

            public int getRet_code() {
                return ret_code;
            }

            public void setRet_code(int ret_code) {
                this.ret_code = ret_code;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }

            public int getColor() {
                return color;
            }

            public void setColor(int color) {
                this.color = color;
            }

            public int getCur_song_num() {
                return cur_song_num;
            }

            public void setCur_song_num(int cur_song_num) {
                this.cur_song_num = cur_song_num;
            }

            public int getComment_num() {
                return comment_num;
            }

            public void setComment_num(int comment_num) {
                this.comment_num = comment_num;
            }

            public int getCurrentPage() {
                return currentPage;
            }

            public void setCurrentPage(int currentPage) {
                this.currentPage = currentPage;
            }

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public int getSong_begin() {
                return song_begin;
            }

            public void setSong_begin(int song_begin) {
                this.song_begin = song_begin;
            }

            public String getDay_of_year() {
                return day_of_year;
            }

            public void setDay_of_year(String day_of_year) {
                this.day_of_year = day_of_year;
            }

            public int getTotalpage() {
                return totalpage;
            }

            public void setTotalpage(int totalpage) {
                this.totalpage = totalpage;
            }

            public List<SonglistBean> getSonglist() {
                return songlist;
            }

            public void setSonglist(List<SonglistBean> songlist) {
                this.songlist = songlist;
            }

            public static class SonglistBean {

                private String songname;
                private int seconds;
                private String albummid;
                private int songid;
                private int singerid;
                private String albumpic_big;
                private String albumpic_small;
                private String downUrl;
                private String url;
                private String singername;
                private int albumid;

                public String getSongname() {
                    return songname;
                }

                public void setSongname(String songname) {
                    this.songname = songname;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public String getAlbummid() {
                    return albummid;
                }

                public void setAlbummid(String albummid) {
                    this.albummid = albummid;
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

                public String getDownUrl() {
                    return downUrl;
                }

                public void setDownUrl(String downUrl) {
                    this.downUrl = downUrl;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getSingername() {
                    return singername;
                }

                public void setSingername(String singername) {
                    this.singername = singername;
                }

                public int getAlbumid() {
                    return albumid;
                }

                public void setAlbumid(int albumid) {
                    this.albumid = albumid;
                }

                @Override
                public String toString() {
                    return "SonglistBean{" +
                            "songname='" + songname + '\'' +
                            ", seconds=" + seconds +
                            ", albummid='" + albummid + '\'' +
                            ", songid=" + songid +
                            ", singerid=" + singerid +
                            ", albumpic_big='" + albumpic_big + '\'' +
                            ", albumpic_small='" + albumpic_small + '\'' +
                            ", downUrl='" + downUrl + '\'' +
                            ", url='" + url + '\'' +
                            ", singername='" + singername + '\'' +
                            ", albumid=" + albumid +
                            '}';
                }
            }
        }
    }
}
