package in.ac.srmuniv;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ClockTimeActivity extends Activity {
	private static final String TAG="BroadcastTime";
	private Intent in;
	
	private BroadcastReceiver timerec=new BroadcastReceiver(){

		@Override
		public void onReceive(Context c, Intent i) {
			
			//Getting the values from intent and updating log
			
			int h=i.getIntExtra("hrs", 0);
			int m=i.getIntExtra("min", 0);
			int s=i.getIntExtra("sec", 0);
			Log.d(TAG, Integer.toString(h));
			Log.d(TAG, Integer.toString(m));
			Log.d(TAG, Integer.toString(s));
			
			//Printing Time to Textview
			
			TextView th=(TextView) findViewById(R.id.hr);
			TextView tm=(TextView) findViewById(R.id.mn);
			TextView ts=(TextView) findViewById(R.id.sc);
			if(h>=10)
				th.setText(Integer.toString(h)+":");
			else
				th.setText("0"+Integer.toString(h)+":");
			if(m>=10)
				tm.setText(Integer.toString(m)+":");
			else
				tm.setText("0"+Integer.toString(m)+":");
			if(s>=10)
				ts.setText(Integer.toString(s));
			else
				ts.setText("0"+Integer.toString(s));
			
			}
		
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        //Intent for starting the Time display service
        
        in=new Intent(this,BroadCastTimeService.class);
        startService(in);
       //(BroadcastTime.BROADCAST_TIME));
        
        //Code for opening alarm set activity
        
        TextView txth = (TextView) findViewById(R.id.hr);
        TextView txtm = (TextView) findViewById(R.id.mn);
        TextView txts = (TextView) findViewById(R.id.sc);
        
        
        	Typeface fonth = Typeface.createFromAsset(getAssets(), "MyriadPro-Bold.otf");  
            txth.setTypeface(fonth);
            txtm.setTypeface(fonth);
            txts.setTypeface(fonth);
     
    }
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		 super.onResume();
		 registerReceiver(timerec, new IntentFilter("in.ac.srmuniv.displaytime"));
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(timerec);
	}


}