package in.ac.srmuniv;

import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class BroadCastTimeService extends Service {

	private static final String TAG="BroadcastTime";
	static final String BROADCAST_TIME = "in.ac.srmuniv.displaytime"; 
	public final Handler handler = new Handler();
	Intent i;
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		i = new Intent(BROADCAST_TIME);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		handler.removeCallbacks(update);
		handler.postDelayed(update, 1000);
		return Service.START_NOT_STICKY;
	}
	
	private Runnable update= new Runnable(){

		@Override
		public void run() {
			Log.d(TAG, "Updated Time");
			i.putExtra("hrs", new Date().getHours());
			i.putExtra("min", new Date().getMinutes());
			i.putExtra("sec", new Date().getSeconds());
			sendBroadcast(i);
			handler.postDelayed(this, 1000);
		}
		
	};


	
}
