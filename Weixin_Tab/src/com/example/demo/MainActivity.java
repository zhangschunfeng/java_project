package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import com.jauker.widget.BadgeView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	private ViewPager viewpager;
	private List<MyFragment> fragmentList;
	private TextView id_tv_chat,id_tv_friend,id_tv_contact;
	private ImageView id_iv_tabline;
	private MyPagerAdapter adapter;
	private LinearLayout ll_contact,ll_chat;
	private BadgeView badgeview1 ,badgeview2;
	private int Screen_width_3;
	private int Screen_width;
	private int mCurrentPageIndex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initLine();
		
		initData();
	}
	private void initLine() {
		Display display=this.getWindowManager().getDefaultDisplay();
		DisplayMetrics outMetrics=new DisplayMetrics();
		display.getMetrics(outMetrics);
		Screen_width=outMetrics.widthPixels;
		Screen_width_3=Screen_width/3;
		LayoutParams params=id_iv_tabline.getLayoutParams();
		params.width=Screen_width_3=Screen_width/3;
		id_iv_tabline.setLayoutParams(params);
		
		
		
		
	}
	private void initData() {
		fragmentList=new ArrayList<MyFragment>();
		MyFragment fragment1=new MyFragment(this,"我是第一个fragment");
		MyFragment fragment2=new MyFragment(this,"我是个第二个fragment");
		MyFragment fragment3=new MyFragment(this,"我是第三个fragment");
		fragmentList.add(fragment1);
		fragmentList.add(fragment2);
		fragmentList.add(fragment3);
		adapter=new MyPagerAdapter(this.getSupportFragmentManager(),fragmentList);
		viewpager.setAdapter(adapter);
	    viewpager.setOnPageChangeListener(mListener);
		
	}
	private void initView() {
		viewpager=(ViewPager) this.findViewById(R.id.viewpager);
		id_tv_chat=(TextView) this.findViewById(R.id.id_tv_chat);
		id_tv_friend=(TextView) this.findViewById(R.id.id_tv_friend);
		id_tv_contact=(TextView) this.findViewById(R.id.id_tv_contact);
		ll_chat=(LinearLayout) this.findViewById(R.id.id_ll_chat);
		ll_contact=(LinearLayout) this.findViewById(R.id.id_ll_contact);
		
		id_iv_tabline=(ImageView) this.findViewById(R.id.id_iv_tabline);
		
		badgeview1=new BadgeView(this);
		badgeview1.setBadgeCount(4);
		ll_contact.addView(badgeview1);
		badgeview2=new BadgeView(this);
		badgeview2.setText("tips");
		ll_chat.addView(badgeview2);
		


	}
	private OnPageChangeListener mListener=new OnPageChangeListener()
	{
	
		@Override
		public void onPageScrollStateChanged(int arg0) {
			
			
		}

		//
		//position :当前页面，及你点击滑动的页面
		//arg1:当前页面偏移的百分比
		//arg2:当前页面偏移的像素位置
		@Override
		public void onPageScrolled(int position, float offposition , int arg2) {
			Log.e("---------","position="+position+"...offposition="+offposition+"....arg2="+arg2);
			LinearLayout.LayoutParams lp=(android.widget.LinearLayout.LayoutParams) id_iv_tabline.getLayoutParams();
			if(position==0&&mCurrentPageIndex==0)//0->1
			{
				lp.leftMargin=(int) (Screen_width_3*mCurrentPageIndex +offposition*Screen_width_3);
			}
			else if(mCurrentPageIndex==1&&position==0)
			{
				lp.leftMargin=(int) (Screen_width_3*mCurrentPageIndex +(offposition-1)*Screen_width_3);
			}
			else if(mCurrentPageIndex==1&&position==1)
			{
				lp.leftMargin=(int) (Screen_width_3*mCurrentPageIndex +offposition*Screen_width_3);
			}
			else if(mCurrentPageIndex==2&position==1)
			{
				lp.leftMargin=(int) (Screen_width_3*mCurrentPageIndex +(offposition-1)*Screen_width_3);
			}
			id_iv_tabline.setLayoutParams(lp);	
			
		}

		@Override
		public void onPageSelected(int position) {
			resetTextView();
			switch(position)
			{
			case 0:
				id_tv_chat.setTextColor(Color.parseColor("#008000"));
				break;
			case 1:
				id_tv_friend.setTextColor(Color.parseColor("#008000"));
				break;
			case 2:
				id_tv_contact.setTextColor(Color.parseColor("#008000"));
				break;
				
			}
			mCurrentPageIndex = position;	
		}

		private void resetTextView()
		{
			id_tv_chat.setTextColor(Color.BLACK);
			id_tv_contact.setTextColor(Color.BLACK);
			id_tv_friend.setTextColor(Color.BLACK);
		}
		
	};
	
}
