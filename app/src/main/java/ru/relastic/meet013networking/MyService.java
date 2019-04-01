package ru.relastic.meet013networking;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class MyService extends Service {
    public static final int WHAT_CLIENT_CONNECTED = 1;
    public static final int WHAT_MESSAGE_LIST = 2;
    public static final int WHAT_MESSAGE_ITEM = 3;
    public static final int WHAT_REQUEST_ITEM = 4;
    public static final int WHAT_REQUEST_LIST = 5;

    public static final String BUNDLE_KEY_PREF = "item_";

    public static final String FIELD_POS = "field_pos";
    public static final String FIELD_ID = "field_id";
    public static final String FIELD_DATE = "field_date";
    public static final String FIELD_DESCRIPT = "field_descript";

    private Messenger mService = new Messenger(new MyServiceHandler());

    private RetrofitApiMapper mRetrofitApiMapper = new RetrofitApiMapper(new RetrofitHelper());

    public MyService() {
    }

    @Override
    public void onCreate() {
        mRetrofitApiMapper = new RetrofitApiMapper(new RetrofitHelper()) ;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mService.getBinder();
    }
    public static Intent getIntent(Context context){
        return new Intent(context, MyService.class);
    }
    private ArrayList<Bundle> getList(){
        ArrayList<Bundle> varRetVal = new ArrayList<>();
        Bundle item;
        for (int i = 1; i<=10; i++) {
            item = new Bundle();
            item.putInt(FIELD_POS,i);
            item.putInt(FIELD_ID,i-1);
            item.putString(FIELD_DATE,"2"+String.valueOf(i)+".03.2019");
            //item
            varRetVal.add(item);
        }
        return varRetVal;
    }
    private Bundle getItem(){
        Bundle varRetVal = new Bundle();
        //<...>
        return varRetVal;
    }

    class MyServiceHandler extends Handler {
        MyServiceHandler() {
            super(Looper.getMainLooper());
        }
        @Override
        public void handleMessage(Message msg) {

            final Messenger client = msg.replyTo;

            long id= 524901L;

            switch (msg.what) {
                case WHAT_CLIENT_CONNECTED:
                case WHAT_REQUEST_LIST:
                    mRetrofitApiMapper.requestWeatherByCitiId(id, RetrofitHelper.APPID, new retrofit2.Callback<WeatherEntity>() {
                        @Override
                        public void onResponse(Call<WeatherEntity> call, Response<WeatherEntity> response) {
                            Message msg = new Message();
                            msg.what = WHAT_MESSAGE_LIST;
                            msg.obj = response.body();
                            msg.replyTo = mService;
                            try {
                                client.send(msg);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherEntity> call, Throwable t) {
                            //
                        }
                    });
                    break;
                case WHAT_REQUEST_ITEM:
                    mRetrofitApiMapper.requestWeatherByCitiIdCurrent(id, RetrofitHelper.APPID, new retrofit2.Callback<WeatherEntityCurrent>() {

                        @Override
                        public void onResponse(Call<WeatherEntityCurrent> call, Response<WeatherEntityCurrent> response) {
                            Message msg = new Message();
                            msg.what = WHAT_MESSAGE_ITEM;
                            msg.obj = response.body();
                            msg.replyTo = mService;
                            try {
                                client.send(msg);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherEntityCurrent> call, Throwable t) {
                            //
                        }
                    });
                    break;
            }
        }
    }


}
