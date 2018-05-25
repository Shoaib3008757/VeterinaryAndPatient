package ranglerz.veterinaryandpatient;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ranglerz.veterinaryandpatient.Fragments.Birds;
import ranglerz.veterinaryandpatient.Fragments.FragmentDairy;
import ranglerz.veterinaryandpatient.Fragments.FragmentOther;
import ranglerz.veterinaryandpatient.Fragments.FragmentPets;
import ranglerz.veterinaryandpatient.Fragments.FregamentEquin;

public class DashboardClient extends DrawerActivityForClient {

  //  private TabLayout tabLayout;
    public static ViewPager viewPager;
    RelativeLayout tab_rl_diary, tab_rl_pets, tab_rl_equine, tab_rl_birds, tab_rl_other;
    android.support.v7.widget.AppCompatImageView tab_iv_dairy, tab_iv_pets, tab_iv_equine, tab_iv_birds, tab_iv_other;
    TextView tab_tv_dairy, tab_tv_pets, tab_tv_equine, tab_tv_birds, tab_tv_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_client_dashboard);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        //inflate your activity layout here!
        View contentView = inflater.inflate(R.layout.activity_client_dashboard, null, false);
        mDrawerLayout.addView(contentView, 0);


       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/

        init();
        onDairyTabClickListener();
        onPetTabClickListener();
        onEquineTabClickListener();
        onBirdsTabClickListener();
        onOtherTabClickListener();
        viewPagerTabChangeListner();
    }

    private void init(){

        tab_rl_diary = (RelativeLayout) findViewById(R.id.tab_rl_diary);
        tab_rl_pets = (RelativeLayout) findViewById(R.id.tab_rl_pets);
        tab_rl_equine = (RelativeLayout) findViewById(R.id.tab_rl_equine);
        tab_rl_birds = (RelativeLayout) findViewById(R.id.tab_rl_birds);
        tab_rl_other = (RelativeLayout) findViewById(R.id.tab_rl_other);

        tab_iv_dairy = (android.support.v7.widget.AppCompatImageView) findViewById(R.id.tab_iv_dairy);
        tab_iv_pets = (android.support.v7.widget.AppCompatImageView) findViewById(R.id.tab_iv_pets);
        tab_iv_equine = (android.support.v7.widget.AppCompatImageView) findViewById(R.id.tab_iv_equine);
        tab_iv_birds = (android.support.v7.widget.AppCompatImageView) findViewById(R.id.tab_iv_birds);
        tab_iv_other = (android.support.v7.widget.AppCompatImageView) findViewById(R.id.tab_iv_other);

        tab_tv_dairy = (TextView) findViewById(R.id.tab_tv_dairy);
        tab_tv_pets = (TextView) findViewById(R.id.tab_tv_pets);
        tab_tv_equine = (TextView) findViewById(R.id.tab_tv_equine);
        tab_tv_birds = (TextView) findViewById(R.id.tab_tv_birds);
        tab_tv_other = (TextView) findViewById(R.id.tab_tv_other);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);


     /*   tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, tabLayout, false);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.tab_title);
            tabTextView.setText(tab.getText());
            tab.setCustomView(relativeLayout);
            //tab.select();
        }

        TabLayout.Tab tab = tabLayout.getTabAt(0); // Count Starts From 0
        tab.select();*/


        tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
        tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
        tab_iv_dairy.setImageResource(R.drawable.ic_dairyblue);
        viewPager.setCurrentItem(0);




    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentDairy(), "Dairy");
        adapter.addFragment(new FragmentPets(), "Pets");
        adapter.addFragment(new FregamentEquin(), "Equine");
        adapter.addFragment(new Birds(), "Birds");
        adapter.addFragment(new FragmentOther(), "Other");
        viewPager.setAdapter(adapter);


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void onDairyTabClickListener(){

        tab_rl_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                tab_iv_dairy.setImageResource(R.drawable.ic_dairyblue);

                tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_pets.setImageResource(R.drawable.ic_pets);

                tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_equine.setImageResource(R.drawable.ic_equine);

                tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_birds.setImageResource(R.drawable.ic_birds);

                tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_other.setImageResource(R.drawable.ic_others);


                viewPager.setCurrentItem(0);

            }
        });
    }

    private void onPetTabClickListener(){

        tab_rl_pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_dairy.setImageResource(R.drawable.ic_dairy);

                tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                tab_iv_pets.setImageResource(R.drawable.ic_petsblue);

                tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_equine.setImageResource(R.drawable.ic_equine);

                tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_birds.setImageResource(R.drawable.ic_birds);

                tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_other.setImageResource(R.drawable.ic_others);


                viewPager.setCurrentItem(1);

            }
        });
    }

    private void onEquineTabClickListener(){

        tab_rl_equine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_dairy.setImageResource(R.drawable.ic_dairy);

                tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_pets.setImageResource(R.drawable.ic_pets);

                tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                tab_iv_equine.setImageResource(R.drawable.ic_equineblue);

                tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_birds.setImageResource(R.drawable.ic_birds);

                tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_other.setImageResource(R.drawable.ic_others);

                viewPager.setCurrentItem(2);

            }
        });
    }

    private void onBirdsTabClickListener(){

        tab_rl_birds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_dairy.setImageResource(R.drawable.ic_dairy);

                tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_pets.setImageResource(R.drawable.ic_pets);

                tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_equine.setImageResource(R.drawable.ic_equine);

                tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                tab_iv_birds.setImageResource(R.drawable.ic_birdsblue);

                tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_other.setImageResource(R.drawable.ic_others);

                viewPager.setCurrentItem(3);

            }
        });
    }

    private void onOtherTabClickListener(){

        tab_rl_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_dairy.setImageResource(R.drawable.ic_dairy);

                tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_pets.setImageResource(R.drawable.ic_pets);

                tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_equine.setImageResource(R.drawable.ic_equine);

                tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                tab_iv_birds.setImageResource(R.drawable.ic_birds);

                tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                tab_iv_other.setImageResource(R.drawable.ic_othersblue);

                viewPager.setCurrentItem(4);

            }
        });
    }

    private void viewPagerTabChangeListner(){

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.e("TAG", "the selected page position is: " + position);

                switch (position){

                    case 0:
                    //if (position == 0){
                    tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                    tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                    tab_iv_dairy.setImageResource(R.drawable.ic_dairyblue);

                    tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_pets.setImageResource(R.drawable.ic_pets);

                    tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_equine.setImageResource(R.drawable.ic_equine);

                    tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_birds.setImageResource(R.drawable.ic_birds);

                    tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_other.setImageResource(R.drawable.ic_others);

                    break;
                //}
                    case 1:
                        //if (position == 1){
                    tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_dairy.setImageResource(R.drawable.ic_dairy);

                    tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                    tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                    tab_iv_pets.setImageResource(R.drawable.ic_petsblue);

                    tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_equine.setImageResource(R.drawable.ic_equine);

                    tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_birds.setImageResource(R.drawable.ic_birds);

                    tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_other.setImageResource(R.drawable.ic_others);

                    break;

                    //}
                    case 2:
                    //if (position == 2){
                    tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_dairy.setImageResource(R.drawable.ic_dairy);

                    tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_pets.setImageResource(R.drawable.ic_pets);

                    tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                    tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                    tab_iv_equine.setImageResource(R.drawable.ic_equineblue);

                    tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_birds.setImageResource(R.drawable.ic_birds);

                    tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_other.setImageResource(R.drawable.ic_others);

                    break;
                //}

                    case 3:
                    //if (position == 3){
                    tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_dairy.setImageResource(R.drawable.ic_dairy);

                    tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_pets.setImageResource(R.drawable.ic_pets);

                    tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_equine.setImageResource(R.drawable.ic_equine);

                    tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                    tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                    tab_iv_birds.setImageResource(R.drawable.ic_birdsblue);

                    tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_other.setImageResource(R.drawable.ic_others);

                    break;
                    //}

                    case 4:
                    //if (position == 4){
                    tab_rl_diary.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_dairy.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_dairy.setImageResource(R.drawable.ic_dairy);

                    tab_rl_pets.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_pets.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_pets.setImageResource(R.drawable.ic_pets);

                    tab_rl_equine.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_equine.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_equine.setImageResource(R.drawable.ic_equine);

                    tab_rl_birds.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tabs_button_style));
                    tab_tv_birds.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    tab_iv_birds.setImageResource(R.drawable.ic_birds);

                    tab_rl_other.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_button_style_after_click));
                    tab_tv_other.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorBlack));
                    tab_iv_other.setImageResource(R.drawable.ic_othersblue);

                    break;
                    //}
            }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
