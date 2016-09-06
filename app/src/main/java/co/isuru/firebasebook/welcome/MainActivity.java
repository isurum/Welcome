package co.isuru.firebasebook.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Obtain the Firebase Analytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //Sets whether analytics collection is enabled for this app on this device.
        mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        //Sets the minimum engagement time required before starting a session. The default value is 10000 (10 seconds). Let's make it 20 seconds.
        mFirebaseAnalytics.setMinimumSessionDuration(20000);

        Button buyNowBtn = (Button) findViewById(R.id.buy_now);
        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String id = "1year";
                String name = "Annual membership subscription";
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
                bundle.putString(FirebaseAnalytics.Param.CURRENCY, "GBR");
                bundle.putString(FirebaseAnalytics.Param.PRICE, "199.00");
                //Logs an app event.
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_open_notifications:
                Intent startNotificationsActivityIntent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(startNotificationsActivityIntent);
                return true;
            case R.id.sign_up:
                Intent signupIntent = new Intent(MainActivity.this, SignupActivty.class);
                startActivity(signupIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
