package ranglerz.veterinaryandpatient.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ranglerz.veterinaryandpatient.Models.FarmSolutionData;
import ranglerz.veterinaryandpatient.R;

/**
 * Created by User-10 on 18-Apr-18.
 */

public class ListingAdapterCheckupRequest extends RecyclerView.Adapter<ListingAdapterCheckupRequest.MyViewHolder>{

    private ArrayList<FarmSolutionData> blogPostList;
    private Activity mContext;
    private String title;


    public ListingAdapterCheckupRequest(Activity context, ArrayList<FarmSolutionData> adList) {

        this.mContext = context;
        this.blogPostList = adList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvItemTitle;
        protected ImageView imItemImage;
        protected TextView sample1;
        protected TextView sample2;
        protected TextView sample3;
        protected TextView tv_viwe_more;

        protected RelativeLayout rl_main_layout;
        protected RelativeLayout bt_item_detail;


        public MyViewHolder(final View view) {
            super(view);

            tvItemTitle =  (TextView) view.findViewById(R.id.tv_item_title);
            sample1 =  (TextView) view.findViewById(R.id.tv_line_1);
            sample2 =  (TextView) view.findViewById(R.id.tv_line_2);
            sample3 =  (TextView) view.findViewById(R.id.tv_line_3);
            rl_main_layout = (RelativeLayout) view.findViewById(R.id.rl_main_layout);
            bt_item_detail = (RelativeLayout) view.findViewById(R.id.bt_item_detail);
            tv_viwe_more = (TextView) view.findViewById(R.id.tv_viwe_more);
            tv_viwe_more.bringToFront();

        }

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_farm_solutin_layout, null);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_layout_checkup_request, null);
        MyViewHolder mh = new MyViewHolder(v);
        return mh;

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

            Log.e("TAG", "the array size is: " + blogPostList.size());

            FarmSolutionData ad = blogPostList.get(position);


            holder.tvItemTitle.setText(ad.getTitle());
            holder.sample1.setText(ad.getSample1());
            holder.sample2.setText(ad.getSample2());
            holder.sample3.setText(ad.getSample3());

            holder.bt_item_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Dialog dialog = new Dialog(mContext);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_request_detial);
                    ImageView imageView2 = (ImageView) dialog.findViewById(R.id.imageView2);
                    TextView tv_line_1 = (TextView) dialog.findViewById(R.id.tv_line_1);
                    TextView tv_line_2 = (TextView) dialog.findViewById(R.id.tv_line_2);
                    TextView tv_line_3 = (TextView) dialog.findViewById(R.id.tv_line_3);
                    Button bt_dialog_accpet = (Button) dialog.findViewById(R.id.bt_dialog_accpet);
                    Button bt_dialog_ignor = (Button) dialog.findViewById(R.id.bt_dialog_ignor);

                    tv_line_1.setText(holder.sample1.getText().toString());
                    tv_line_2.setText(holder.sample2.getText().toString());
                    tv_line_3.setText(holder.sample3.getText().toString());

                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                    dialog.show();


                }
            });


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
