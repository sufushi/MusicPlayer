package com.rdc.musicplayer.musicplayer.utils;

import android.util.Log;

import com.rdc.musicplayer.musicplayer.bean.Lyrics;
import com.rdc.musicplayer.musicplayer.bean.Music;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LrcUtil {

    public static List<Lyrics> parseStr2List(String lrcStr) {
        List<Lyrics> list = new ArrayList<>();
        String lrcText = lrcStr.replaceAll("&#58;", ":")
                .replaceAll("&#10;", "\n")
                .replaceAll("&#46;", ".")
                .replaceAll("&#32;", " ")
                .replaceAll("&#45;", "-")
                .replaceAll("&#13;", "\r").replaceAll("&#39;", "'");
        String[] split = lrcText.split("\n");
        Log.e("error", "split.length=" + split.length);
        for (int i = 0; i < split.length; i++) {
            String lrc = split[i];
            if (lrc.contains(".")) {
                String min = lrc.substring(lrc.indexOf("[") + 1, lrc.indexOf("[") + 3);
                String seconds = lrc.substring(lrc.indexOf(":") + 1, lrc.indexOf(":") + 3);
                String mills = lrc.substring(lrc.indexOf(".") + 1, lrc.indexOf(".") + 3);
                long startTime = Long.valueOf(min) * 60 * 1000 + Long.valueOf(seconds) * 1000 + Long.valueOf(mills) * 10;
                String text = lrc.substring(lrc.indexOf("]") + 1);
                if ("".equals(text)) {
                    text = "music";
                }
                Lyrics lyrics = new Lyrics();
                lyrics.setStart(startTime);
                lyrics.setLrc(text);
                list.add(lyrics);
                if (list.size() > 1) {
                    list.get(list.size() - 2).setEnd(startTime);
                }
                if (i == split.length - 1) {
                    list.get(list.size() - 1).setEnd(startTime + 100000);
                }
            }
        }
        return list;
    }

    public static final String lrcRootPath = android.os.Environment
            .getExternalStorageDirectory().toString()
            + "/MusicPlayer/Lyrics/";

    /**
     * 从本地读取歌词
     */
    public static List<Lyrics> loadLrc(Music music) {

        List<Lyrics> lrcList = new ArrayList<>();

        String path = music.getUrl();

        // 得到歌词文件路径
        String lrcPathString = path.substring(0, path.lastIndexOf(".")) + ".lrc";
        int index = lrcPathString.lastIndexOf("/");

        String parentPath;
        String lrcName;

        parentPath = lrcPathString.substring(0, index);
        lrcName = lrcPathString.substring(index);

        File file = new File(lrcPathString);

        // 匹配MusicPlayer/Lyrics
        if (!file.exists()) {
            file = new File(getLrcPath(music.getSongName(), music.getSingerName()));
        }

        // 匹配Lyrics
        if (!file.exists()) {
            file = new File(parentPath + "/../" + "Lyrics/" + lrcName);
        }

        // 匹配lyric
        if (!file.exists()) {
            file = new File(parentPath + "/../" + "lyric/" + lrcName);
        }

        // 匹配Lyric
        if (!file.exists()) {
            file = new File(parentPath + "/../" + "Lyric/" + lrcName);
        }

        // 匹配lyrics
        if (!file.exists()) {
            file = new File(parentPath + "/../" + "lyrics/" + lrcName);
        }

        if (file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fin, "utf-8");
                BufferedReader br = new BufferedReader(isr);

                String s;
                while ((s = br.readLine()) != null) {
                    handleOneLine(s, lrcList);
                }
                // 按时间排序
                Collections.sort(lrcList, new LrcComparator());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lrcList;
    }

    public static String loadLyrics(Music music) {

        String path = music.getUrl();

        // 得到歌词文件路径
        String lrcPathString = path.substring(0, path.lastIndexOf(".")) + ".lrc";
        int index = lrcPathString.lastIndexOf("/");

        String parentPath;
        String lrcName;

        parentPath = lrcPathString.substring(0, index);
        lrcName = lrcPathString.substring(index);

        File file = new File(lrcPathString);

        // 匹配MusicPlayer/Lyrics
        if (!file.exists()) {
            file = new File(getLrcPath(music.getSongName(), music.getSingerName()));
        }

        // 匹配Lyrics
        if (!file.exists()) {
            file = new File(parentPath + "/../" + "Lyrics/" + lrcName);
        }

        // 匹配lyric
        if (!file.exists()) {
            file = new File(parentPath + "/../" + "lyric/" + lrcName);
        }

        // 匹配Lyric
        if (!file.exists()) {
            file = new File(parentPath + "/../" + "Lyric/" + lrcName);
        }

        // 匹配lyrics
        if (!file.exists()) {
            file = new File(parentPath + "/../" + "lyrics/" + lrcName);
        }

        String lrc = "";
        if (file.exists()) {
            try {
                FileInputStream fin = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fin, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String s = "";
                while ((s = br.readLine()) != null) {
                    lrc += s + "\n";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lrc;
    }


    public static List<Lyrics> parseLrcStr(String Lyrics) {
        List<Lyrics> lrclists = new ArrayList<>();
        String lines[] = Lyrics.split("\n");
        for (String line : lines) {
            handleOneLine(line, lrclists);
        }
        return lrclists;
    }

    static void handleOneLine(String line, List<Lyrics> lrclists) {
        String s = line.replace("[", ""); // 去掉左边括号
        String lrcData[] = s.split("]");

        // 这句是歌词
        if (lrcData[0].matches("^\\d{2}:\\d{2}.\\d+$")) {
            int len = lrcData.length;
            int end = lrcData[len - 1].matches("^\\d{2}:\\d{2}.\\d+$") ? len
                    : len - 1;

            for (int i = 0; i < end; i++) {
                Lyrics Lyrics = new Lyrics();
                int lrcTime = TimeFormatUtil.getLrcMillTime(lrcData[i]);
                Lyrics.setStart(lrcTime);
                if (lrcData.length == end)
                    Lyrics.setLrc(""); // 空白行
                else
                    Lyrics.setLrc(lrcData[len - 1]);
                lrclists.add(Lyrics);
            }
        }
    }

    public static void writeLrcToLoc(String title, String artist, String lrcContext) {
        FileWriter writer = null;
        try {
            File file = new File(getLrcPath(title, artist));
            if (!file.exists()) {
                file.createNewFile();
                writer = new FileWriter(getLrcPath(title, artist));
                writer.write(lrcContext);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据歌名和艺术家生产的系统默认歌词文件位置路径
     *
     * @param title
     * @param artist
     * @return
     */
    public static String getLrcPath(String title, String artist) {
        return lrcRootPath + title + " - " + artist + ".lrc";
    }
}
