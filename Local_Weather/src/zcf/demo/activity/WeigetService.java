package zcf.demo.activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;







import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.RemoteViews;



public class WeigetService extends Service {
	private LocationClient mLocationClient=null;
	private BDLocationListener myListener;
	private String cityName;
	private String wd;
	private String tq;
	private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyƒÍMM‘¬dd ,HH:mm");
	private Timer timer;
	private RemoteViews view;
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
	@Override
	public void onCreate() {
	
		super.onCreate();

		IntentFilter filter=new IntentFilter();
		filter.addAction("SEND_INFOR");
		this.registerReceiver(new BroadcastReceiver()
		{
                   	@Override
			public void onReceive(Context context, Intent intent) {
                   		if(intent.getAction().equalsIgnoreCase("SEND_INFOR"))
                   		{
							 cityName=intent.getStringExtra("city");
							 wd=intent.getStringExtra("wd");
							 tq=intent.getStringExtra("tq");							
                   		}	
			}
			
		}, filter);
		timer=new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run() {	
				
				updateView();
			}
			
		}, 0,1000);
		
	}
	protected void updateView() {
		String str=dateFormat.format(new Date());
		String dateInfor[]=str.split(",");
		
		view=new RemoteViews(getPackageName(),R.layout.weiget);
		view.setTextViewText(R.id.city, cityName);
		view.setTextViewText(R.id.showtime, dateInfor[1]);
		view.setTextViewText(R.id.showyear, dateInfor[0]);
		view.setTextViewText(R.id.weather, wd);
		view.setTextViewText(R.id.weather_detail,tq);
	  //  setIamgeResource(tq);
		AppWidgetManager manager=AppWidgetManager.getInstance(this);
		ComponentName cn=new ComponentName(this,MyWeigetProviderInfo.class);
		manager.updateAppWidget(cn,view);
		
		
	}
	@Override
	public void onDestroy() {
		timer=null;
		super.onDestroy();
	}
	void  setIamgeResource(String weather){
		view.setImageViewResource(R.id.weather_img,R.drawable.s_2);
		if(weather.indexOf("∂‡‘∆")!=-1||weather.indexOf("«Á")!=-1){//∂‡‘∆◊™«Á£¨“‘œ¬¿‡Õ¨ indexOf:∞¸∫¨◊÷¥Æ
			view.setImageViewResource(R.id.weather_img,R.drawable.s_1);}
        else if(weather.indexOf("∂‡‘∆")!=-1&&weather.indexOf("“ı")!=-1){
        	view.setImageViewResource(R.id.weather_img,R.drawable.s_2); }
        else if(weather.indexOf("“ı")!=-1&&weather.indexOf("”Í")!=-1){
        	view.setImageViewResource(R.id.weather_img,R.drawable.s_3);}
        else if(weather.indexOf("«Á")!=-1&&weather.indexOf("”Í")!=-1){
        	view.setImageViewResource(R.id.weather_img,R.drawable.s_12);}
        else if(weather.indexOf("«Á")!=-1&&weather.indexOf("ŒÌ")!=-1){
        	view.setImageViewResource(R.id.weather_img,R.drawable.s_12);}
        else if(weather.indexOf("«Á")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_13);}
        else if(weather.indexOf("∂‡‘∆")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_2);}
        else if(weather.indexOf("’Û”Í")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_3);}
        else if(weather.indexOf("–°”Í")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_3);}
        else if(weather.indexOf("÷–”Í")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_4);}
        else if(weather.indexOf("¥Û”Í")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_5);}
        else if(weather.indexOf("±©”Í")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_5);}
        else if(weather.indexOf("±˘±¢")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_6);}
        else if(weather.indexOf("¿◊’Û”Í")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_7);}
        else if(weather.indexOf("–°—©")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_8);}
        else if(weather.indexOf("÷–—©")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_9);}
        else if(weather.indexOf("¥Û—©")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_10);}
        else if(weather.indexOf("±©—©")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_10);}
        else if(weather.indexOf("—Ô…≥")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_11);}
        else if(weather.indexOf("…≥≥æ")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_11);}
        else if(weather.indexOf("ŒÌ")!=-1){view.setImageViewResource(R.id.weather_img,R.drawable.s_12);}
	}
}
