package com.t2.compassionMeditation;

import com.t2.AndroidSpineServerMainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class StartupActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("BFDemo", this.getClass().getSimpleName() + ".onCreate()"); 		
		
    	// We must call the spine main server activity first so it can establish
    	// bindings to it's service
		Intent intent2 = new Intent(this, AndroidSpineServerMainActivity.class);
		Bundle bundle = new Bundle();
		
		// We must tell AndroidSpineServerMainActivity how to get back to us
		// when it's finished initializing
		bundle.putString("TARGET_NAME","com.t2.biozen.SPLASH");
		
		// *** Note that this package name MUST match the package name of 
		// *** the manifest of the main application
		bundle.putString("PACKAGE_NAME","com.t2");
		
		intent2.putExtras(bundle);	
		
		// Now we start the activity.
		// Once it's initialized all that it needs to then it will call the app specified above
		// Note that it remains on the stack so that in the power down sequence
		// its onDestroy() method will be called and it will tear down the service bindings
		this.startActivityForResult(intent2, BioZenConstants.SPINE_MAINSERVER_ACTIVITY);		
				
		finish();
		
		
		
	}

}