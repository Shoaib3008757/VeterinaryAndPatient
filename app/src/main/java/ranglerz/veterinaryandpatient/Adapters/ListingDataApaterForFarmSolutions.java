package ranglerz.veterinaryandpatient.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ranglerz.veterinaryandpatient.Models.FarmSolutionData;
import ranglerz.veterinaryandpatient.R;

/**
 * Created by Shoaib Anwar on 16-Apr-18.
 */

public class ListingDataApaterForFarmSolutions extends RecyclerView.Adapter<ListingDataApaterForFarmSolutions.MyViewHolder>{

private ArrayList<FarmSolutionData> blogPostList;
private Activity mContext;
private String title;


    public ListingDataApaterForFarmSolutions(Activity context, ArrayList<FarmSolutionData> adList) {

        this.mContext = context;
        this.blogPostList = adList;
    }

public class MyViewHolder extends RecyclerView.ViewHolder {

    protected TextView tvItemTitle;
    protected ImageView imItemImage;
    protected TextView sample1;
    protected TextView sample2;
    protected TextView sample3;


    public MyViewHolder(final View view) {
        super(view);

        tvItemTitle =  (TextView) view.findViewById(R.id.tv_item_title);
        sample1 =  (TextView) view.findViewById(R.id.tv_line_1);
        sample2 =  (TextView) view.findViewById(R.id.tv_line_2);
        sample3 =  (TextView) view.findViewById(R.id.tv_line_3);


    }

}


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_farm_solutin_layout, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

            Log.e("TAG", "the array size is: " + blogPostList.size());

            FarmSolutionData ad = blogPostList.get(position);


                    holder.tvItemTitle.setText(ad.getTitle());
                    holder.sample1.setText(ad.getSample1());
                    holder.sample2.setText(ad.getSample2());
                    holder.sample3.setText(ad.getSample3());

        }
    }


    @Override
    public int getItemCount() {

        return blogPostList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }


    @Override
    public void onViewDetachedFromWindow(MyViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(2000);
        view.startAnimation(anim);
    }

}
