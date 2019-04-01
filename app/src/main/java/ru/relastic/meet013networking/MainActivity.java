package ru.relastic.meet013networking;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean isFirstStart;
    private MyAdapter mAdapter;
    private Messenger mService;
    private MyHandler myHandler = new MyHandler();
    private Messenger mCurrent = new Messenger(myHandler);

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = new Message();
            msg.what = MyService.WHAT_CLIENT_CONNECTED;
            msg.replyTo = mCurrent;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isFirstStart = (savedInstanceState==null);
        initViews();
        initListeners();
        init();
    }
    private void initViews() {
    }

    private void initListeners() {
    }

    private void init( ) {
        RecyclerView mRecyclerView = new RecyclerView(getApplicationContext());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        WeatherEntity data = createData();
        mAdapter = new MyAdapter(this, data);
        mRecyclerView.setAdapter(mAdapter);
        ((ViewGroup)findViewById(R.id.main_frame_layout)).addView(mRecyclerView);
    }

    private WeatherEntity createData() {
        return new WeatherEntity();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(MyService.getIntent(this), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        unbindService(mConnection);
        super.onPause();
    }

    public void viewDetails(WeatherEntity data, int index) {

        startActivity(DetailsActivity.getIntent(MainActivity.this)
                .putExtra("data", RetrofitApiMapper.convertToString(data))
                .putExtra("index", index));
    }


    class MyHandler extends Handler {
        MyHandler() {
            super(Looper.getMainLooper());
        }
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyService.WHAT_MESSAGE_LIST:
                    WeatherEntity data_list = (WeatherEntity)msg.obj;
                    mAdapter.setData(data_list);
                    break;
                case MyService.WHAT_MESSAGE_ITEM:
                    WeatherEntityCurrent data_list_item = (WeatherEntityCurrent)msg.obj;
                    //<...>
                    //startActivity(DetailsActivity.getIntent(MainActivity.this).
                    //        putExtra("item", data_list_item.getBundle("item")));
                    break;
            }
        }
    }
}
