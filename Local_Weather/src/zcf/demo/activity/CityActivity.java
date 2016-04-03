package zcf.demo.activity;
import zcf.demo.activity.R.layout;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CityActivity extends Activity implements OnClickListener{
	private String weather_wd,weather_tq;
	private TextView city;
	private ProgressDialog progressDialog;
	private String cityName;
	private View ws2_ll_yes;
	private ImageView td_iv_image,tor_iv_image,aftor_iv_image;
	private Button btn_submit_other,ws2_btn_return;
	private TextView td_tv_attr1,td_tv_attr2,td_tv_attr3,td_city,
					 tor_tv_attr1,tor_tv_attr2,tor_tv_attr3,aftor_tv_attr1,aftor_tv_attr2,aftor_tv_attr3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layout.activity_main);
		initView();
		Intent intent=this.getIntent();
		cityName=intent.getStringExtra("city");
		QueryAsyncTask asyncTask = new QueryAsyncTask();
		asyncTask.execute("");
		Toast.makeText(this, cityName,Toast.LENGTH_LONG).show();
		
		
		
	}
	
	
	private void initView() {
		ws2_ll_yes=this.findViewById(R.id.ws2_ll_yes);
		td_iv_image=(ImageView) this.findViewById(R.id.td_iv_image);
		tor_iv_image=(ImageView) this.findViewById(R.id.tor_iv_image);
		aftor_iv_image=(ImageView) this.findViewById(R.id.aftor_iv_image);
		td_tv_attr1=(TextView) this.findViewById(R.id.td_tv_attr1);
		td_tv_attr2=(TextView) this.findViewById(R.id.td_tv_attr2);
		td_tv_attr3=(TextView) this.findViewById(R.id.td_tv_attr3);
		tor_tv_attr1=(TextView) this.findViewById(R.id.tor_tv_attr1);
		tor_tv_attr2=(TextView) this.findViewById(R.id.tor_tv_attr2);
		tor_tv_attr3=(TextView) this.findViewById(R.id.tor_tv_attr3);
		aftor_tv_attr1=(TextView) this.findViewById(R.id.aftor_tv_attr1);
		aftor_tv_attr2=(TextView) this.findViewById(R.id.aftor_tv_attr2);
		aftor_tv_attr3=(TextView) this.findViewById(R.id.aftor_tv_attr3);
		td_city=(TextView) this.findViewById(R.id.td_city);
		ws2_btn_return=(Button) this.findViewById(R.id.ws2_btn_return);
		ws2_btn_return.setOnClickListener(this);
				
	}
	public void todayParse(String weather) {
		String temp = weather.replace("'", "");
		String[] tempArr = temp.split(",");
		String wd="";
		String tq="";
		String fx="";
		if(tempArr.length>0){
			for(int i=0;i<tempArr.length;i++){
				if(tempArr[i].indexOf("t1:")!=-1){
					wd=tempArr[i].substring(3,tempArr[i].length())+"℃";
				}else if(tempArr[i].indexOf("t2:")!=-1){
					wd=wd+"~"+tempArr[i].substring(3,tempArr[i].length())+"℃";
				}else if(tempArr[i].indexOf("d1:")!=-1){
					fx=tempArr[i].substring(3,tempArr[i].length());
				}else if(tempArr[i].indexOf("s1:")!=-1){
					tq=tempArr[i].substring(4,tempArr[i].length());
				}
			}
			
			td_tv_attr1.setText("气温："+wd);
			td_tv_attr2.setText("天气情况："+tq);
			td_tv_attr3.setText("风向："+fx);
			td_iv_image.setImageResource(Utils.imageResoId(tq));
			Intent intent=new Intent("SEND_INFOR");
		    intent.putExtra("city", cityName);
		    intent.putExtra("wd",wd);
		    intent.putExtra("tq",tq);
		    
		}
		
	}
	public void tomorrowParse(String weather) {
		String temp = weather.replace("'", "");
		String[] tempArr = temp.split(",");
		String wd="";
		String tq="";
		String fx="";
		if(tempArr.length>0){
			for(int i=0;i<tempArr.length;i++){
				if(tempArr[i].indexOf("t1:")!=-1){
					wd=tempArr[i].substring(3,tempArr[i].length())+"℃";
				}else if(tempArr[i].indexOf("t2:")!=-1){
					wd=wd+"~"+tempArr[i].substring(3,tempArr[i].length())+"℃";
				}else if(tempArr[i].indexOf("d1:")!=-1){
					fx=tempArr[i].substring(3,tempArr[i].length());
				}else if(tempArr[i].indexOf("s1:")!=-1){
					tq=tempArr[i].substring(4,tempArr[i].length());
				}
			}
			
			tor_tv_attr1.setText("气温："+wd);
			tor_tv_attr2.setText("天气情况："+tq);
			tor_tv_attr3.setText("风向："+fx);
			tor_iv_image.setImageResource(Utils.imageResoId(tq));
		}
			
	}
	public void afterTorrowParse(String weather) {
		String temp = weather.replace("'", "");
		String[] tempArr = temp.split(",");
		String wd="";
		String tq="";
		String fx="";
		if(tempArr.length>0){
			for(int i=0;i<tempArr.length;i++){
				if(tempArr[i].indexOf("t1:")!=-1){
					wd=tempArr[i].substring(3,tempArr[i].length())+"℃";
				}else if(tempArr[i].indexOf("t2:")!=-1){
					wd=wd+"~"+tempArr[i].substring(3,tempArr[i].length())+"℃";
				}else if(tempArr[i].indexOf("d1:")!=-1){
					fx=tempArr[i].substring(3,tempArr[i].length());
				}else if(tempArr[i].indexOf("s1:")!=-1){
					tq=tempArr[i].substring(4,tempArr[i].length());
				}
			}
			
			aftor_tv_attr1.setText("气温："+wd);
			aftor_tv_attr2.setText("天气情况："+tq);
			aftor_tv_attr3.setText("风向："+fx);
			aftor_iv_image.setImageResource(Utils.imageResoId(tq));
		}
	}
	//使用异步通信AsynTask对天气进行查询
		private class QueryAsyncTask extends AsyncTask
		{	
			@Override
			protected void onPostExecute(Object result) {
				if(result!=null)
				{		
					String weatherResult = (String)result;
					if(weatherResult.split(";").length>1){
					   String a  = weatherResult.split(";")[1];
						if(a.split("=").length>1){
							String b = a.split("=")[1];
							String c = b.substring(1,b.length()-1);
							String[] resultArr = c.split("\\}");
							if(resultArr.length>0)
							{
								todayParse(resultArr[0]);
								tomorrowParse(resultArr[1]);
								afterTorrowParse(resultArr[2]);
								td_city.setText(cityName);
								ws2_ll_yes.setVisibility(View.VISIBLE);
							}
					 	 }
					}		
				}
			
			}
			@Override
			protected Object doInBackground(Object... params) {
				//查询天气
				return WeatherService.getWeather(cityName);
			}
			
		}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.ws2_btn_return:
			startActivity(new Intent(CityActivity.this,MainActivity.class));
		}
		
	}
}
