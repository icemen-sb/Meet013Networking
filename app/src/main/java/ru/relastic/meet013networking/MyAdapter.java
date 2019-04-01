package ru.relastic.meet013networking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final int STORAGE_LOCAL = 0;
    private static final int STORAGE_NET = 1;

    private  final Context mContext;
    private WeatherEntity mData;

    MyAdapter(Context context, WeatherEntity data) {
        mContext = context;
        mData = (data == null) ? new WeatherEntity() : data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rw_linear_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.mTextViewPos.setText(String.valueOf(i+1));
        myViewHolder.mTextViewDate.setText(mData.getList().get(i).getDateTimeString(null));
        myViewHolder.mTextViewDescription.setText("Температура "
                + mData.getList().get(i).getMain().getTemp(null)
                + '\n'+"(в диапазоне: " + mData.getList().get(i).getMain().getTemp_min(null)
                + " - " + mData.getList().get(i).getMain().getTemp_max(null) + ")"
                + '\n' + mData.getList().get(i).getWeather().get(0).getDescription());
        MyAdapterAsyncImageLoad myAdapterAsyncImageLoad = new MyAdapterAsyncImageLoad (mContext,
                myViewHolder.mImageView, mData.getList().get(i).getWeather().get(0).getIcon_id());
        myAdapterAsyncImageLoad.execute("");
    }

    @Override
    public int getItemCount() {
        int retVal = 0;
        if (mData.getList() != null) {retVal = mData.getList().size();}
        return retVal;
    }

    public void setData(WeatherEntity data) {
        mData = data;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        public TextView mTextViewPos;
        public TextView mTextViewDate;
        public TextView mTextViewDescription;
        public ImageView mImageView;


        public MyViewHolder(View itemView){
            super(itemView);
            mTextViewPos =itemView.findViewById(R.id.textViewPos);
            mTextViewDate =itemView.findViewById(R.id.textViewDate);
            mTextViewDescription =itemView.findViewById(R.id.textViewDescription);
            mImageView =itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)mContext).viewDetails(mData,getAdapterPosition());
                }
            });
        }
    }
}
