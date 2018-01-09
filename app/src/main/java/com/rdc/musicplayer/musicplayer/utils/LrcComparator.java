package com.rdc.musicplayer.musicplayer.utils;

import java.util.Comparator;

import com.rdc.musicplayer.musicplayer.bean.Lyrics;


public class LrcComparator implements Comparator<Lyrics>{

	@Override
	public int compare(Lyrics o1, Lyrics o2) {


		return (int) (o1.getStart() - o2.getStart());
	}

}
