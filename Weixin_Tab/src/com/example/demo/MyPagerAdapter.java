package com.example.demo;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class MyPagerAdapter extends FragmentPagerAdapter {
	private List<MyFragment> list;

	public MyPagerAdapter(FragmentManager fm,List<MyFragment> list) {
		super(fm);
		this.list=list;
	}

	@Override
	public Fragment getItem(int position) {
		
		return list.get(position);
	}

	@Override
	public int getCount() {
	
		return list.size();
	}


	
}
