package ranglerz.veterinaryandpatient;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.ProxyInfo;
import android.net.Uri;
import android.opengl.Visibility;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.provider.SyncStateContract;
import android.support.annotation.CheckResult;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Config;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.gotev.uploadservice.MultipartUploadRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import ranglerz.veterinaryandpatient.Config.API;
import ranglerz.veterinaryandpatient.Preferences.Prefs;
import ranglerz.veterinaryandpatient.utils.Utility;

public class ProfileUpdateForVeterinary extends AppCompatActivity {

    Spinner sp_select_qualification;
    LinearLayout ll_when_lad;
    LinearLayout ll_when_dvm;
    LinearLayout ll_dynamic_layout;
    LinearLayout ll_to_inflat;
    RelativeLayout fb_admore;
    LinearLayout ll_main_check;
    RelativeLayout rl_update_profile;

    CheckBox cb_dairy, cb_pet, cb_equine, cb_bird, cb_other;
    LinearLayout ll_inner_cb_dairy, ll_inner_cb_pet, ll_inner_cb_equine, ll_inner_cb_bird, ll_inner_cb_other;

    LinearLayout ll_cb_treatment_nutritionist, ll_cb_breed_surgeon;
    CheckBox cb_treatment, cb_nutritionist;
    CheckBox cb_breeding, cb_surgeon;




    Button bt_document_copi_dvm_mphile;
    Button bt_document_copies_for_experience;

     Button bt_document_copies_common_study;

    LinearLayout ll_inflate_document_for_first;
    LinearLayout ll_inflate_document_for_mphil_document;
    LinearLayout ll_inflate_document_for_experience;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Uri imageUri = null;
    Bitmap bitmap1;

    private int indicatorForGallary = -1;


    EditText et_top_year_of_passing, et_top_instituation_name;
    EditText et_rvmp_mpil_dvm;
    EditText et_deparments_mpil_dvm, et_instituation_name_mpil_dvm, et_passing_year_mpil_dvm;

    ArrayList<String> topDocumentsArray;
    ArrayList<String> mPhileDocuments;

    CheckBox cb_dairy_cow, cb_dairy_buffalo, cb_dairy_sheep, cb_dairy_goat;
    CheckBox cb_pet_dog, cb_pet_cart, cb_pet_parrot, cb_pet_others;
    CheckBox cb_equine_horse, cb_equine_donkey, cb_equine_other;
    CheckBox cb_bird_layer, cb_bird_broiler, cb_bird_other;
    CheckBox cb_other_fisheries, cb_other_wildlife, cb_other_other;

    ArrayList<TempDataClass> experineceViewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update_for_veterinary);

        init();
        spinnerQualificationSelectListener();

        addMoreClickHandler();
        checkCnageListnerForDairy();
        checkCnageListnerForPets();
        checkCnageListnerForEquine();
        checkCnageListnerForBirds();
        checkCnageListnerForOther();
        gettingDocumentsFromGallary();
        btDocumentsForMphil();
        updateButtonClickHandler();
    }

    private void init(){

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        sp_select_qualification = (Spinner) findViewById(R.id.sp_select_qualification);
        ll_when_lad = (LinearLayout) findViewById(R.id.ll_when_lad);
        ll_when_dvm = (LinearLayout) findViewById(R.id.ll_when_dvm);
        ll_to_inflat = (LinearLayout) findViewById(R.id.ll_to_inflat);
        ll_dynamic_layout = (LinearLayout) findViewById(R.id.ll_dynamic_layout);
        fb_admore = (RelativeLayout) findViewById(R.id.fb_admore);

        ll_main_check = (LinearLayout) findViewById(R.id.ll_main_check);

        cb_dairy = (CheckBox) findViewById(R.id.cb_dairy);
        cb_pet = (CheckBox) findViewById(R.id.cb_pet);
        cb_equine = (CheckBox) findViewById(R.id.cb_equine);
        cb_bird = (CheckBox) findViewById(R.id.cb_bird);
        cb_other = (CheckBox) findViewById(R.id.cb_other);

        ll_inner_cb_dairy = (LinearLayout) findViewById(R.id.ll_inner_cb_dairy);
        ll_inner_cb_pet = (LinearLayout) findViewById(R.id.ll_inner_cb_pet);
        ll_inner_cb_equine = (LinearLayout) findViewById(R.id.ll_inner_cb_equine);
        ll_inner_cb_bird = (LinearLayout) findViewById(R.id.ll_inner_cb_bird);
        ll_inner_cb_other = (LinearLayout) findViewById(R.id.ll_inner_cb_other);

        ll_cb_treatment_nutritionist = (LinearLayout) findViewById(R.id.ll_cb_treatment_nutritionist);
        ll_cb_breed_surgeon = (LinearLayout) findViewById(R.id.ll_cb_breed_surgeon);
        cb_treatment = (CheckBox) findViewById(R.id.cb_treatment);
        cb_nutritionist = (CheckBox) findViewById(R.id.cb_nutritionist);
        cb_breeding = (CheckBox) findViewById(R.id.cb_breeding);
        cb_surgeon = (CheckBox) findViewById(R.id.cb_surgeon);

        rl_update_profile = (RelativeLayout) findViewById(R.id.rl_update_profile);
        rl_update_profile.setVisibility(View.GONE);

        bt_document_copi_dvm_mphile = (Button) findViewById(R.id.bt_document_copi_dvm_mphile);

        bt_document_copies_common_study = (Button) findViewById(R.id.bt_document_copies_common_study);
        bt_document_copies_for_experience = (Button) findViewById(R.id.bt_document_copies_for_experience);

        ll_inflate_document_for_first = (LinearLayout) findViewById(R.id.ll_inflate_document_for_first);
        ll_inflate_document_for_mphil_document = (LinearLayout) findViewById(R.id.ll_inflate_document_for_mphil_document);
        ll_inflate_document_for_experience = (LinearLayout) findViewById(R.id.ll_inflate_document_for_experience);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(ProfileUpdateForVeterinary.this,
                R.array.qualification_type, R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_qualification.setAdapter(adapter);


        ll_inflate_document_for_first.removeAllViews();
        ll_inflate_document_for_mphil_document.removeAllViews();
        ll_inflate_document_for_experience.removeAllViews();


        ll_to_inflat.removeAllViews();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.layout_add_more_for_veterinary_update_profile
                , null);

        LinearLayout ll_inflate_document_for_experienceInner = (LinearLayout) rowView.findViewById(R.id.ll_inflate_document_for_experience);

        ll_to_inflat.addView(rowView, ll_to_inflat.getChildCount());
        ImageView im_remove_view = (ImageView) rowView.findViewById(R.id.im_remove_view);
        Button bt_document_copies_for_experience = (Button) rowView.findViewById(R.id.bt_document_copies_for_experience);
        im_remove_view.setVisibility(View.GONE);

        ll_inflate_document_for_experienceInner.removeAllViews();


        int indecator = ll_to_inflat.getChildCount();
        indecator = indecator + 2;
        Log.e("TAG", "the count of view in experience: " + indecator);
        btDynamicButtonForExperienceDocuments(bt_document_copies_for_experience, indecator);


        et_top_year_of_passing = (EditText) findViewById(R.id.et_top_year_of_passing);
        et_top_instituation_name = (EditText)findViewById(R.id.et_top_instituation_name);
        et_rvmp_mpil_dvm = (EditText) findViewById(R.id.et_rvmp_mpil_dvm);
        et_deparments_mpil_dvm = (EditText) findViewById(R.id.et_deparments_mpil_dvm);
        et_instituation_name_mpil_dvm = (EditText) findViewById(R.id.et_instituation_name_mpil_dvm);
        et_passing_year_mpil_dvm = (EditText) findViewById(R.id.et_passing_year_mpil_dvm);


        topDocumentsArray = new ArrayList<>();
        mPhileDocuments = new ArrayList<>();

        cb_dairy_cow = (CheckBox) findViewById(R.id.cb_dairy_cow);
        cb_dairy_buffalo = (CheckBox) findViewById(R.id.cb_dairy_buffalo);
        cb_dairy_sheep = (CheckBox) findViewById(R.id.cb_dairy_sheep);
        cb_dairy_goat = (CheckBox) findViewById(R.id.cb_dairy_goat);

        cb_pet_dog = (CheckBox) findViewById(R.id.cb_pet_dog);
        cb_pet_cart = (CheckBox) findViewById(R.id.cb_pet_cart);
        cb_pet_parrot = (CheckBox) findViewById(R.id.cb_pet_parrot);
        cb_pet_others = (CheckBox) findViewById(R.id.cb_pet_others);

        cb_equine_horse = (CheckBox) findViewById(R.id.cb_equine_horse);
        cb_equine_donkey = (CheckBox) findViewById(R.id.cb_equine_donkey);
        cb_equine_other = (CheckBox) findViewById(R.id.cb_equine_other);

        cb_bird_layer = (CheckBox) findViewById(R.id.cb_bird_layer);
        cb_bird_broiler = (CheckBox) findViewById(R.id.cb_bird_broiler);
        cb_bird_other = (CheckBox) findViewById(R.id.cb_bird_other);

        cb_other_fisheries = (CheckBox) findViewById(R.id.cb_other_fisheries);
        cb_other_wildlife = (CheckBox) findViewById(R.id.cb_other_wildlife);
        cb_other_other = (CheckBox) findViewById(R.id.cb_other_other);

        experineceViewList = new ArrayList<>();


    }

    private void spinnerQualificationSelectListener(){

        sp_select_qualification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0){

                    ll_when_dvm.setVisibility(View.GONE);
                    ll_when_lad.setVisibility(View.GONE);
                    ll_dynamic_layout.setVisibility(View.GONE);
                    ll_main_check.setVisibility(View.GONE);
                    rl_update_profile.setVisibility(View.GONE);
                }
                if (i == 1){

                    ll_when_dvm.setVisibility(View.GONE);
                    ll_when_lad.setVisibility(View.VISIBLE);
                    ll_dynamic_layout.setVisibility(View.VISIBLE);
                    ll_main_check.setVisibility(View.VISIBLE);

                    ll_cb_treatment_nutritionist.setVisibility(View.GONE);
                    cb_surgeon.setVisibility(View.GONE);
                    cb_breeding.setChecked(true);
                    rl_update_profile.setVisibility(View.VISIBLE);
                    et_rvmp_mpil_dvm.setVisibility(View.GONE);


                    //for specialities check
                    if (cb_treatment.isChecked()){

                        cb_treatment.setChecked(false);
                    }
                    if (cb_breeding.isChecked()){
                        cb_breeding.setChecked(true);
                    }
                    if (cb_nutritionist.isChecked()){
                        cb_nutritionist.setChecked(false);
                    }
                    if (cb_surgeon.isChecked()){
                        cb_surgeon.setChecked(false);
                    }

                }
                if (i == 2){

                    ll_when_dvm.setVisibility(View.VISIBLE);
                    ll_when_lad.setVisibility(View.VISIBLE);
                    ll_dynamic_layout.setVisibility(View.VISIBLE);
                    ll_main_check.setVisibility(View.VISIBLE);

                    ll_cb_treatment_nutritionist.setVisibility(View.VISIBLE);
                    cb_surgeon.setVisibility(View.VISIBLE);
                    cb_breeding.setChecked(false);

                    rl_update_profile.setVisibility(View.VISIBLE);
                    et_rvmp_mpil_dvm.setVisibility(View.VISIBLE);

                    if (cb_treatment.isChecked()){

                        cb_treatment.setChecked(false);
                    }
                    if (cb_breeding.isChecked()){
                        cb_breeding.setChecked(false);
                    }
                    if (cb_nutritionist.isChecked()){
                        cb_nutritionist.setChecked(false);
                    }
                    if (cb_surgeon.isChecked()){
                        cb_surgeon.setChecked(false);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addMoreClickHandler(){

        fb_admore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.layout_add_more_for_veterinary_update_profile
                        , null);

                LinearLayout ll_inflate_document_for_experienceInner = (LinearLayout) rowView.findViewById(R.id.ll_inflate_document_for_experience);
                ll_to_inflat.addView(rowView, ll_to_inflat.getChildCount());
                Button bt_document_copies_for_experience = (Button) rowView.findViewById(R.id.bt_document_copies_for_experience);

                ll_inflate_document_for_experienceInner.removeAllViews();
                deleteingView(rowView);

                int indecator = ll_to_inflat.getChildCount();
                indecator = indecator + 2;
                Log.e("TAG", "the count of view in experience: " + indecator);
                btDynamicButtonForExperienceDocuments(bt_document_copies_for_experience, indecator);


            }
        });
    }

    public void deleteingView(final View myView){

        Log.e("TAG", "Image button Clicked: " + ll_to_inflat.getChildCount());
        for (int i = 0; i<ll_to_inflat.getChildCount(); i++){
            final View timingView = ll_to_inflat.getChildAt(i);
            ImageView im_remove_view = (ImageView) timingView.findViewById(R.id.im_remove_view);

            im_remove_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewGroup parent = (ViewGroup) timingView.getParent();
                    parent.removeView(timingView);

                    //ll_to_inflat.removeView((View) (myView).getParent());
                }
            });
        }

    }


    private void checkCnageListnerForDairy(){

        cb_dairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_dairy.isChecked()){
                    ll_inner_cb_dairy.setVisibility(View.VISIBLE);
                    Animation slidToRight = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_right);
                    ll_inner_cb_dairy.startAnimation(slidToRight);

                }else {

                    Animation slidToLeft = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_left);
                    ll_inner_cb_dairy.startAnimation(slidToLeft);
                    slidToLeft.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ll_inner_cb_dairy.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }
            }
        });
    }


    private void checkCnageListnerForPets(){

        cb_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_pet.isChecked()){
                    ll_inner_cb_pet.setVisibility(View.VISIBLE);
                    Animation slidToRight = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_right);
                    ll_inner_cb_pet.startAnimation(slidToRight);

                }else {

                    Animation slidToLeft = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_left);
                    ll_inner_cb_pet.startAnimation(slidToLeft);
                    slidToLeft.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ll_inner_cb_pet.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }
            }
        });
    }

    private void checkCnageListnerForEquine(){

        cb_equine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_equine.isChecked()){
                    ll_inner_cb_equine.setVisibility(View.VISIBLE);
                    Animation slidToRight = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_right);
                    ll_inner_cb_equine.startAnimation(slidToRight);

                }else {

                    Animation slidToLeft = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_left);
                    ll_inner_cb_equine.startAnimation(slidToLeft);
                    slidToLeft.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ll_inner_cb_equine.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }
            }
        });
    }

    private void checkCnageListnerForBirds(){

        cb_bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_bird.isChecked()){
                    ll_inner_cb_bird.setVisibility(View.VISIBLE);
                    Animation slidToRight = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_right);
                    ll_inner_cb_bird.startAnimation(slidToRight);

                }else {

                    Animation slidToLeft = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_left);
                    ll_inner_cb_bird.startAnimation(slidToLeft);
                    slidToLeft.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ll_inner_cb_bird.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }
            }
        });
    }

    private void checkCnageListnerForOther(){

        cb_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_other.isChecked()){
                    ll_inner_cb_other.setVisibility(View.VISIBLE);
                    Animation slidToRight = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_right);
                    ll_inner_cb_other.startAnimation(slidToRight);

                }else {

                    Animation slidToLeft = AnimationUtils.loadAnimation(ProfileUpdateForVeterinary.this, R.anim.slide_to_left);
                    ll_inner_cb_other.startAnimation(slidToLeft);
                    slidToLeft.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ll_inner_cb_other.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                }
            }
        });
    }

    private void gettingDocumentsFromGallary(){

        bt_document_copies_common_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean result = Utility.checkPermission(ProfileUpdateForVeterinary.this);
                if (result){


                    final  Dialog dialog = new Dialog(ProfileUpdateForVeterinary.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custome_dialog_for_getting_photo);

                    Button bt_dialog_from_gallary = (Button)dialog.findViewById(R.id.bt_dialog_from_gallary);
                    Button bt_dialog_from_camera = (Button) dialog.findViewById(R.id.bt_dialog_from_camera);

                    bt_dialog_from_camera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();
                            cameraIntent(1); //to detect that the view is from top document button view

                        }
                    });

                    bt_dialog_from_gallary.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            indicatorForGallary = 1;
                            galleryIntent();
                        }
                    });


                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                    dialog.show();


                }

            }
        });
    }

    private void cameraIntent(final int detector)
    {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        String fileName = getFileName(imageUri);
        if (detector == 1) {
            addViewFileName(fileName, imageUri.toString());
        }
        if (detector == 2) {
            addViewFileNameForMphile(fileName, imageUri.toString());

        }
        if (detector > 2){
            addFileNameForDynamicViewsForExperience(fileName, imageUri.toString(), detector);

        }
        Log.e("TAG", "the upload file name is: " + fileName);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("TAg", "The Request code is: " + requestCode);

        //  if (resultCode == Activity.RESULT_OK) {
        if (requestCode == SELECT_FILE) {

            if (data!=null) {
                onSelectFromGalleryResult(data);
            }
        }
        else if (requestCode == REQUEST_CAMERA) {
            if (data!=null) {
                onCaptureImageResult(data);
            }
        }

    }

    //selecting image from galary
    private void onSelectFromGalleryResult(Intent data) {


        if (data!=null) {
            imageUri = data.getData();
            String fileName = getFileName(imageUri);
            if (indicatorForGallary == 1) {
                addViewFileName(fileName, imageUri.toString());
            }
            if (indicatorForGallary == 2){

                addViewFileNameForMphile(fileName, imageUri.toString());
            }
            if (indicatorForGallary>2){
                addFileNameForDynamicViewsForExperience(fileName, imageUri.toString(), indicatorForGallary);
            }
            Log.e("TAG", "the upload file name is: " + fileName);

            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                //pmdcImageFrameLayout.setVisibility(View.VISIBLE);
                //pmdc_select_picture_layout.setVisibility(View.GONE);
                //iv_pmdc.setImageBitmap(bitmap1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //getting image form camera
    private void onCaptureImageResult(Intent data) {

        try {

            bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            //pmdcImageFrameLayout.setVisibility(View.VISIBLE);
            //pmdc_select_picture_layout.setVisibility(View.GONE);
            //iv_pmdc.setImageBitmap(bitmap1);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void addViewFileName(String fileName, String imageURi){



        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.layout_for_add_file_name
                , null);

        TextView tv_image_uri = (TextView) rowView.findViewById(R.id.tv_image_uri);
        tv_image_uri.setText(imageURi);
        TextView tv_file_name = (TextView) rowView.findViewById(R.id.tv_file_name);
        tv_file_name.setText(fileName);

        Log.e("TAG", "the file uri is: " + imageURi);

        ll_inflate_document_for_first.addView(rowView, ll_inflate_document_for_first.getChildCount());

        deletingImageFileName(rowView);
        btPreviewImage(rowView);
    }

    private void deletingImageFileName(final View myView){

        for (int i = 0; i<ll_inflate_document_for_first.getChildCount(); i++){
            final View rootView = ll_inflate_document_for_first.getChildAt(i);
            ImageView im_remove_view = (ImageView) rootView.findViewById(R.id.iv_crose_file_name);

            im_remove_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewGroup parent = (ViewGroup) rootView.getParent();
                    parent.removeView(rootView);

                    //ll_to_inflat.removeView((View) (myView).getParent());
                }
            });
        }
    }

    private void btPreviewImage(final View previewView){

        for (int i = 0; i<ll_inflate_document_for_first.getChildCount(); i++){
            final View rootView = ll_inflate_document_for_first.getChildAt(i);
            Button d_view_button_preview = (Button) rootView.findViewById(R.id.d_view_button_preview);

            d_view_button_preview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TextView tv_image_uri = (TextView) rootView.findViewById(R.id.tv_image_uri) ;
                    TextView tv_file_name = (TextView) rootView.findViewById(R.id.tv_file_name);
                    Log.e("TAg", "the image page is: " + tv_image_uri.getText().toString());


                    final  Dialog dialog = new Dialog(ProfileUpdateForVeterinary.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custome_image_preview);

                    TextView tv_dialog_image_name = (TextView) dialog.findViewById(R.id.tv_dialog_image_name);
                    ImageView iv_dialog_preview_image = (ImageView) dialog.findViewById(R.id.iv_dialog_preview_image);

                    imageUri = Uri.parse(tv_image_uri.getText().toString());

                    tv_dialog_image_name.setText(tv_file_name.getText().toString());
                    iv_dialog_preview_image.setImageURI(imageUri);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                    dialog.show();

                }
            });
        }

    }

    private void btDocumentsForMphil(){
        bt_document_copi_dvm_mphile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean result = Utility.checkPermission(ProfileUpdateForVeterinary.this);
                if (result){

                    final  Dialog dialog = new Dialog(ProfileUpdateForVeterinary.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custome_dialog_for_getting_photo);

                    Button bt_dialog_from_gallary = (Button)dialog.findViewById(R.id.bt_dialog_from_gallary);
                    Button bt_dialog_from_camera = (Button) dialog.findViewById(R.id.bt_dialog_from_camera);

                    bt_dialog_from_camera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();

                            cameraIntent(2);

                        }
                    });

                    bt_dialog_from_gallary.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            indicatorForGallary = 2;
                            galleryIntent();
                        }
                    });


                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                    dialog.show();


                }
            }
        });
    }

    private void addViewFileNameForMphile(String fileName, String imageURi){



        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.layout_for_add_file_name_mphil
                , null);

        TextView tv_image_uri_mphil = (TextView) rowView.findViewById(R.id.tv_image_uri_mphil);
        tv_image_uri_mphil.setText(imageURi);
        TextView tv_file_name_mphil = (TextView) rowView.findViewById(R.id.tv_file_name_mphil);
        tv_file_name_mphil.setText(fileName);

        ll_inflate_document_for_mphil_document.addView(rowView, ll_inflate_document_for_mphil_document.getChildCount());

        deletingImageFileNameForMphil(rowView);
        btPreviewImageMphile(rowView);
    }

    private void deletingImageFileNameForMphil(final View myView){

        for (int i = 0; i<ll_inflate_document_for_mphil_document.getChildCount(); i++){
            final View rootView = ll_inflate_document_for_mphil_document.getChildAt(i);
            ImageView im_remove_view = (ImageView) rootView.findViewById(R.id.iv_crose_file_name_mphil);

            im_remove_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewGroup parent = (ViewGroup) rootView.getParent();
                    parent.removeView(rootView);


                }
            });
        }
    }


    private void btPreviewImageMphile(final View previewView){

        for (int i = 0; i<ll_inflate_document_for_mphil_document.getChildCount(); i++){
            final View rootView = ll_inflate_document_for_mphil_document.getChildAt(i);
            Button d_view_button_preview_mphil = (Button) rootView.findViewById(R.id.d_view_button_preview_mphil);

            d_view_button_preview_mphil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TextView tv_image_uri_mphil = (TextView) rootView.findViewById(R.id.tv_image_uri_mphil) ;
                    TextView tv_file_name_mphil = (TextView) rootView.findViewById(R.id.tv_file_name_mphil);
                    Log.e("TAg", "the image page is: " + tv_image_uri_mphil.getText().toString());


                    final  Dialog dialog = new Dialog(ProfileUpdateForVeterinary.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custome_image_preview);

                    TextView tv_dialog_image_name = (TextView) dialog.findViewById(R.id.tv_dialog_image_name);
                    ImageView iv_dialog_preview_image = (ImageView) dialog.findViewById(R.id.iv_dialog_preview_image);

                    imageUri = Uri.parse(tv_image_uri_mphil.getText().toString());

                    tv_dialog_image_name.setText(tv_file_name_mphil.getText().toString());
                    iv_dialog_preview_image.setImageURI(imageUri);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                    dialog.show();

                }
            });
        }
    }

    private void btDynamicButtonForExperienceDocuments(Button btExpereiceDocuments, final int detector){
        btExpereiceDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean result = Utility.checkPermission(ProfileUpdateForVeterinary.this);
                if (result){

                    final  Dialog dialog = new Dialog(ProfileUpdateForVeterinary.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custome_dialog_for_getting_photo);

                    Button bt_dialog_from_gallary = (Button)dialog.findViewById(R.id.bt_dialog_from_gallary);
                    Button bt_dialog_from_camera = (Button) dialog.findViewById(R.id.bt_dialog_from_camera);

                    bt_dialog_from_camera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();

                            cameraIntent(detector);

                        }
                    });

                    bt_dialog_from_gallary.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            indicatorForGallary = detector;
                            galleryIntent();
                        }
                    });


                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                    dialog.show();


                }
            }
        });
    }

    private void addFileNameForDynamicViewsForExperience(String fileName, String imageURi, int viewNumber){


        viewNumber = viewNumber-3;

        final View parentViwe = ll_to_inflat.getChildAt(viewNumber);
        Log.e("TAg", "view positino is: " + parentViwe);

        LinearLayout ll_inflate_document_for_experience_inner = (LinearLayout) parentViwe.findViewById(R.id.ll_inflate_document_for_experience);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.layout_for_add_file_name_experience
                    , null);
            TextView tv_image_uri_experience = (TextView) rowView.findViewById(R.id.tv_image_uri_experience);
            tv_image_uri_experience.setText(imageURi);
            TextView tv_file_name_experience = (TextView) rowView.findViewById(R.id.tv_file_name_experience);
            tv_file_name_experience.setText(fileName);


        ll_inflate_document_for_experience_inner.addView(rowView);

            deletingImageFileNameForExperience(rowView);
            btPreviewImageExperience(rowView);

    }

    private void deletingImageFileNameForExperience(final View myView){

        LinearLayout ll_inflate_document_for_experience_inner = (LinearLayout) myView.findViewById(R.id.ll_inflate_document_for_experience);

        for (int i = 0; i<ll_inflate_document_for_experience_inner.getChildCount(); i++){
            final View rootView = ll_inflate_document_for_experience_inner.getChildAt(i);
            ImageView im_remove_view = (ImageView) rootView.findViewById(R.id.iv_crose_file_name_experience);

            im_remove_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ViewGroup parent = (ViewGroup) rootView.getParent();
                    parent.removeView(rootView);

                }
            });
        }
    }


    private void btPreviewImageExperience(final View previewView){

        LinearLayout ll_inflate_document_for_experience_inner = (LinearLayout) previewView.findViewById(R.id.ll_inflate_document_for_experience);

        for (int i = 0; i<ll_inflate_document_for_experience_inner.getChildCount(); i++){
            final View rootView = ll_inflate_document_for_experience_inner.getChildAt(i);
            Button d_view_button_preview_mphil = (Button) rootView.findViewById(R.id.d_view_button_preview_experience);

            d_view_button_preview_mphil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TextView tv_image_uri_mphil = (TextView) rootView.findViewById(R.id.tv_image_uri_experience) ;
                    TextView tv_file_name_mphil = (TextView) rootView.findViewById(R.id.tv_file_name_experience);
                    Log.e("TAg", "the image page is: " + tv_image_uri_mphil.getText().toString());


                    final  Dialog dialog = new Dialog(ProfileUpdateForVeterinary.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custome_image_preview);

                    TextView tv_dialog_image_name = (TextView) dialog.findViewById(R.id.tv_dialog_image_name);
                    ImageView iv_dialog_preview_image = (ImageView) dialog.findViewById(R.id.iv_dialog_preview_image);

                    imageUri = Uri.parse(tv_image_uri_mphil.getText().toString());

                    tv_dialog_image_name.setText(tv_file_name_mphil.getText().toString());
                    iv_dialog_preview_image.setImageURI(imageUri);

                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
                    dialog.show();

                }
            });
        }
    }

    private void updateButtonClickHandler(){

        rl_update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<HashMap<String, String>> precticeDataRecord = new ArrayList<>();

                String topPassingYeaer = et_top_year_of_passing.getText().toString();
                String topInstituation = et_top_instituation_name.getText().toString();
                String toRVMP_or_SVMP = et_rvmp_mpil_dvm.getText().toString();

                    String mPhileDemartment = et_deparments_mpil_dvm.getText().toString();
                    String mPhilePassingYear = et_passing_year_mpil_dvm.getText().toString();
                    String mPhileInstituation = et_instituation_name_mpil_dvm.getText().toString();

                String forDiplomo = "";
                String forExperience = "";
                String forMphile = "";
                String forDairy = "";
                String forPets = "";
                String forEquine = "";
                String forBirds = "";
                String forOther = "";
                String forSpecialist = "";


                String finalData = "";

                System.gc();

                if (topDocumentsArray.size()>0){
                    topDocumentsArray.clear();
                }
                int chiledCountForTopDocuments = ll_inflate_document_for_first.getChildCount();
                Log.e("TAg", "the child count for top documents are: " + chiledCountForTopDocuments);
                if (chiledCountForTopDocuments>0){

                    for (int i = 0; i<chiledCountForTopDocuments;i++){

                        final View mViews = ll_inflate_document_for_first.getChildAt(i);
                        TextView tv_image_uri = (TextView) mViews.findViewById(R.id.tv_image_uri);
                        Log.e("TAg", "the image uri is: " + tv_image_uri.getText().toString());
                        topDocumentsArray.add(tv_image_uri.getText().toString());
                    }
                }//end of for loop for top documents

                //for dairy
                if (cb_dairy.isChecked()){

                    Log.e("TAG", "the checked text for parant is: " + cb_dairy.getText().toString().trim());
                    if (cb_dairy_cow.isChecked()){
                        Log.e("TAG", "the checked text for inner cow is: " + cb_dairy_cow.getText().toString().trim());
                    }
                    if (cb_dairy_buffalo.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_dairy_buffalo is: " + cb_dairy_buffalo.getText().toString().trim());
                    }
                    if (cb_dairy_sheep.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_dairy_sheep is: " + cb_dairy_sheep.getText().toString().trim());
                    }
                    if (cb_dairy_goat.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_dairy_goat is: " + cb_dairy_goat.getText().toString().trim());
                    }
                }

                //for ptets
                if (cb_pet.isChecked()){

                    Log.e("TAG", "the checked text for parant is: " + cb_pet.getText().toString().trim());
                    if (cb_pet_dog.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_pet_dog is: " + cb_pet_dog.getText().toString().trim());
                    }
                    if (cb_pet_cart.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_pet_cart is: " + cb_pet_cart.getText().toString().trim());
                    }
                    if (cb_pet_parrot.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_pet_parrot is: " + cb_pet_parrot.getText().toString().trim());
                    }
                    if (cb_pet_others.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_pet_others is: " + cb_pet_others.getText().toString().trim());
                    }
                }

                //for equine
                if (cb_equine.isChecked()){

                    Log.e("TAG", "the checked text for cb_equine is: " + cb_equine.getText().toString().trim());
                    if (cb_equine_horse.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_equine_horse is: " + cb_equine_horse.getText().toString().trim());
                    }
                    if (cb_equine_donkey.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_equine_donkey is: " + cb_equine_donkey.getText().toString().trim());
                    }
                    if (cb_equine_other.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_equine_other is: " + cb_equine_other.getText().toString().trim());
                    }

                }

                //for birds
                if (cb_bird.isChecked()){

                    Log.e("TAG", "the checked text for cb_bird is: " + cb_bird.getText().toString().trim());
                    if (cb_bird_layer.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_bird_layer is: " + cb_bird_layer.getText().toString().trim());
                    }
                    if (cb_bird_broiler.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_bird_broiler is: " + cb_bird_broiler.getText().toString().trim());
                    }
                    if (cb_bird_other.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_bird_other is: " + cb_bird_other.getText().toString().trim());
                    }

                }

                //for others
                if (cb_other.isChecked()){

                    Log.e("TAG", "the checked text for cb_other is: " + cb_other.getText().toString().trim());
                    if (cb_other_fisheries.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_other_fisheries is: " + cb_other_fisheries.getText().toString().trim());
                    }
                    if (cb_other_wildlife.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_other_wildlife is: " + cb_other_wildlife.getText().toString().trim());
                    }
                    if (cb_other_other.isChecked()){
                        Log.e("TAG", "the checked text for inner cb_other_other is: " + cb_other_other.getText().toString().trim());
                    }

                }

                //for specialities check
                if (cb_treatment.isChecked()){
                    Log.e("TAG", "the checked text for cb_treatment is: " + cb_treatment.getText().toString().trim());
                }
                if (cb_breeding.isChecked()){
                    Log.e("TAG", "the checked text for cb_breeding is: " + cb_breeding.getText().toString().trim());
                }
                if (cb_nutritionist.isChecked()){
                    Log.e("TAG", "the checked text for cb_nutritionist is: " + cb_nutritionist.getText().toString().trim());
                }
                if (cb_surgeon.isChecked()){
                    Log.e("TAG", "the checked text for cb_surgeon is: " + cb_surgeon.getText().toString().trim());
                }

                if (experineceViewList.size()>0){
                    experineceViewList.clear();
                }
                int theExperienceViewCount = ll_to_inflat.getChildCount();
                Log.e("TAg", "the experience View count are: " + theExperienceViewCount);
                for (int i = 0; i<theExperienceViewCount; i++){
                    final View parantView = ll_to_inflat.getChildAt(i);
                    EditText et_experience_from = (EditText) parantView.findViewById(R.id.et_experience_from);
                    EditText et_experience_to = (EditText) parantView.findViewById(R.id.et_experience_to);
                    EditText et_organization_name = (EditText) parantView.findViewById(R.id.et_organization_name);

                    String expFrom = et_experience_from.getText().toString();
                    String expTo = et_experience_to.getText().toString();
                    String expORG_Name = et_organization_name.getText().toString();

                    Log.e("TAg", "the experience from is: " + expFrom);
                    Log.e("TAg", "the experience expTo is: " + expTo);
                    Log.e("TAg", "the experience expORG_Name is: " + expORG_Name);

                    ArrayList<String> urisForImages = new ArrayList<>();

                    LinearLayout ll_inflate_document_for_experience = (LinearLayout) parantView.findViewById(R.id.ll_inflate_document_for_experience);
                    int chiledCountForExperience = ll_inflate_document_for_experience.getChildCount();
                    Log.e("TAg", "the child count for top documents are: " + chiledCountForExperience);
                    if (chiledCountForExperience>0){

                        for (int j = 0; j<chiledCountForExperience;j++){

                            final View mViews = ll_inflate_document_for_experience.getChildAt(j);
                            TextView tv_image_uri_experience = (TextView) mViews.findViewById(R.id.tv_image_uri_experience);
                            Log.e("TAg", "the image uri is: " + tv_image_uri_experience.getText().toString());
                            urisForImages.add(tv_image_uri_experience.getText().toString());
                        }
                    }//end of for loop for experience documents

                    experineceViewList.add(new TempDataClass(expFrom, expTo, expORG_Name, urisForImages));

                }//end of experience dynamic views



                    HashMap<String, String> dataListSimple = new HashMap<>();
                    dataListSimple.put("passing_year", topPassingYeaer);
                    dataListSimple.put("instituatio_name", topInstituation);

                    if (et_rvmp_mpil_dvm.getVisibility() == view.VISIBLE){
                        dataListSimple.put("instituatio_RVMP", toRVMP_or_SVMP);
                    }

                    ArrayList<HashMap<String, String>> deta = new ArrayList<>();
                    deta.add(dataListSimple);

                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray(deta);
                    try {
                        jsonObject.put("Diploma", jsonArray);

                        forDiplomo = jsonObject.toString();

                        Log.e("TAg", "the complex json array to send on server checked: " + forDiplomo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    //for diary
                    if (cb_dairy.isChecked()){
                        ArrayList<HashMap<String, String>> deta1 = new ArrayList<>();
                        HashMap<String, String> dataListSimple2 = new HashMap<>();

                        if (cb_dairy_cow.isChecked()){
                            String cb_dairy_check = cb_dairy_cow.getText().toString();
                            dataListSimple2.put("dairy_cow", cb_dairy_check);
                        }
                        if (cb_dairy_buffalo.isChecked()){
                            String cb_dairy_check = cb_dairy_buffalo.getText().toString();
                            dataListSimple2.put("dairy_buf", cb_dairy_check);
                        }
                        if (cb_dairy_sheep.isChecked()){
                            String cb_dairy_check = cb_dairy_sheep.getText().toString();
                            dataListSimple2.put("dairy_sheep", cb_dairy_check);
                        }
                        if (cb_dairy_goat.isChecked()){
                            String cb_dairy_check = cb_dairy_goat.getText().toString();
                            dataListSimple2.put("dairy_goat", cb_dairy_check);
                        }

                        deta1.add(dataListSimple2);
                        JSONObject jsonObject2 = new JSONObject();
                        JSONArray jsonArray2 = new JSONArray(deta1);
                        try {
                            jsonObject2.put("Dairy", jsonArray2);
                            forDairy = jsonObject2.toString();

                            Log.e("TAg", "the complex json array to send on server checked: " + forDairy);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    //for pet
                    if (cb_pet.isChecked()){
                        ArrayList<HashMap<String, String>> deta1 = new ArrayList<>();
                        HashMap<String, String> dataListSimple2 = new HashMap<>();
                        if (cb_pet_dog.isChecked()){
                            String cb_pet_check = cb_pet_dog.getText().toString();
                            dataListSimple2.put("pet_dog", cb_pet_check);
                        }
                        if (cb_pet_cart.isChecked()){
                            String cb_pet_check = cb_pet_cart.getText().toString();
                            dataListSimple2.put("pet_cat", cb_pet_check);
                        }
                        if (cb_pet_parrot.isChecked()){
                            String cb_pet_check = cb_pet_parrot.getText().toString();
                            dataListSimple2.put("pet_parrot", cb_pet_check);
                        }
                        if (cb_pet_others.isChecked()){
                            String cb_pet_check = cb_pet_others.getText().toString();
                            dataListSimple2.put("pet_other", cb_pet_check);
                        }

                        deta1.add(dataListSimple2);
                        JSONObject jsonObject2 = new JSONObject();
                        JSONArray jsonArray2 = new JSONArray(deta1);
                        try {
                            jsonObject2.put("Pets", jsonArray2);
                            forPets = jsonObject2.toString();

                            Log.e("TAg", "the complex json array to send on server checked: " + forPets);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    //for equine
                    if (cb_equine.isChecked()){
                        ArrayList<HashMap<String, String>> deta1 = new ArrayList<>();
                        HashMap<String, String> dataListSimple2 = new HashMap<>();
                        if (cb_equine.isChecked()){
                            String cb_equine = cb_equine_horse.getText().toString();
                            dataListSimple2.put("equine_horse", cb_equine);
                        }
                        if (cb_equine_donkey.isChecked()){
                            String cb_equine = cb_equine_donkey.getText().toString();
                            dataListSimple2.put("equine_donkey", cb_equine);
                        }
                        if (cb_equine.isChecked()){
                            String cb_equine = cb_equine_other.getText().toString();
                            dataListSimple2.put("equine_other", cb_equine);
                        }

                        deta1.add(dataListSimple2);
                        JSONObject jsonObject2 = new JSONObject();
                        JSONArray jsonArray2 = new JSONArray(deta1);
                        try {
                            jsonObject2.put("Equine", jsonArray2);
                            forEquine = jsonObject2.toString();

                            Log.e("TAg", "the complex json array to send on server checked: " + forEquine);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    //for birds
                    if (cb_bird.isChecked()){
                        ArrayList<HashMap<String, String>> deta1 = new ArrayList<>();
                        HashMap<String, String> dataListSimple2 = new HashMap<>();
                        if (cb_bird_layer.isChecked()){
                            String cb_birds = cb_bird_layer.getText().toString();
                            dataListSimple2.put("bird_layer", cb_birds);
                        }
                        if (cb_bird_broiler.isChecked()){
                            String cb_birds = cb_bird_broiler.getText().toString();
                            dataListSimple2.put("birdr_broiler", cb_birds);
                        }
                        if (cb_bird_other.isChecked()){
                            String cb_birds = cb_bird_other.getText().toString();
                            dataListSimple2.put("bird_other", cb_birds);
                        }

                        deta1.add(dataListSimple2);
                        JSONObject jsonObject2 = new JSONObject();
                        JSONArray jsonArray2 = new JSONArray(deta1);
                        try {
                            jsonObject2.put("Birds", jsonArray2);
                            forBirds = jsonObject2.toString();

                            Log.e("TAg", "the complex json array to send on server checked: " + forBirds);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    //for other
                    if (cb_other.isChecked()){
                        ArrayList<HashMap<String, String>> deta1 = new ArrayList<>();
                        HashMap<String, String> dataListSimple2 = new HashMap<>();
                        if (cb_other_fisheries.isChecked()){
                            String cb_other = cb_other_fisheries.getText().toString();
                            dataListSimple2.put("other_fisheries", cb_other);
                        }
                        if (cb_other_wildlife.isChecked()){
                            String cb_other = cb_other_wildlife.getText().toString();
                            dataListSimple2.put("other_wildlife", cb_other);
                        }
                        if (cb_other_other.isChecked()){
                            String cb_other = cb_other_other.getText().toString();
                            dataListSimple2.put("other_other", cb_other);
                        }


                        deta1.add(dataListSimple2);
                        JSONObject jsonObject2 = new JSONObject();
                        JSONArray jsonArray2 = new JSONArray(deta1);
                        try {
                            jsonObject2.put("Other", jsonArray2);
                            forOther = jsonObject2.toString();

                            Log.e("TAg", "the complex json array to send on server checked: " + forOther);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    //for specialist cheboxes
                if (sp_select_qualification.getSelectedItemId() == 1){

                    ArrayList<HashMap<String, String>> specialistDataList = new ArrayList<>();
                    HashMap<String, String> dataListSpecialist = new HashMap<>();

                    if (cb_breeding.isChecked()){
                        String cb_treat = cb_breeding.getText().toString();
                        dataListSpecialist.put("breeding", cb_treat);
                    }

                    specialistDataList.add(dataListSpecialist);
                    JSONObject jsonObjectSpecialist = new JSONObject();
                    JSONArray jsonArraySpecialist = new JSONArray(specialistDataList);
                    try {
                        jsonObjectSpecialist.put("Specialist", jsonArraySpecialist);
                        forSpecialist = jsonObjectSpecialist.toString();

                        Log.e("TAg", "the complex json array to send on server checked: " + forSpecialist);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                if (sp_select_qualification.getSelectedItemId() == 2){
                    ArrayList<HashMap<String, String>> specialistDataList = new ArrayList<>();
                    HashMap<String, String> dataListSpecialist = new HashMap<>();

                    if (cb_treatment.isChecked()){
                        String cb_treat = cb_treatment.getText().toString();
                        dataListSpecialist.put("treatment", cb_treat);
                    }
                    if (cb_nutritionist.isChecked()){
                        String cb_treat = cb_nutritionist.getText().toString();
                        dataListSpecialist.put("nutritionist", cb_treat);
                    }
                    if (cb_breeding.isChecked()){
                        String cb_treat = cb_breeding.getText().toString();
                        dataListSpecialist.put("breeding", cb_treat);
                    }
                    if (cb_surgeon.isChecked()){
                        String cb_treat = cb_surgeon.getText().toString();
                        dataListSpecialist.put("surgeon", cb_treat);
                    }


                    specialistDataList.add(dataListSpecialist);
                    JSONObject jsonObjectSpecialist = new JSONObject();
                    JSONArray jsonArraySpecialist = new JSONArray(specialistDataList);
                    try {
                        jsonObjectSpecialist.put("Specialist", jsonArraySpecialist);
                        forSpecialist = jsonObjectSpecialist.toString();

                        Log.e("TAg", "the complex json array to send on server checked: " + forSpecialist);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                    //for mPhil
                if (sp_select_qualification.getSelectedItemId() == 2){

                    HashMap<String, String> mPhileData = new HashMap<>();
                    mPhileData.put("department", mPhileDemartment);
                    mPhileData.put("passing_year", mPhilePassingYear);
                    mPhileData.put("instituation_name", mPhileInstituation);

                    ArrayList<HashMap<String, String>> arrayMphileDeta = new ArrayList<>();
                    arrayMphileDeta.add(mPhileData);

                    JSONObject jsonObjectMphile = new JSONObject();
                    JSONArray jsonArrayMphile = new JSONArray(arrayMphileDeta);
                    try {
                        jsonObjectMphile.put("mPhil", jsonArrayMphile);
                        forMphile = jsonObjectMphile.toString();
                        Log.e("TAg", "the complex json array to send on server checked: " + forMphile);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (mPhileDocuments.size()>0){
                    mPhileDocuments.clear();
                }
                int chiledCountForMphileDocuments = ll_inflate_document_for_mphil_document.getChildCount();
                Log.e("TAg", "the child count for top documents are: " + chiledCountForTopDocuments);
                if (chiledCountForMphileDocuments>0){
                    for (int i = 0; i<chiledCountForMphileDocuments;i++){

                        final View mViews = ll_inflate_document_for_mphil_document.getChildAt(i);
                        TextView tv_image_uri_mphil = (TextView) mViews.findViewById(R.id.tv_image_uri_mphil);
                        Log.e("TAg", "the image uri is: " + tv_image_uri_mphil.getText().toString());
                        mPhileDocuments.add(tv_image_uri_mphil.getText().toString());
                    }
                }//end of for loop for mphile documents




               //for experience
                    ArrayList<HashMap<String, String>> deta2 = new ArrayList<>();
                    if (experineceViewList.size()>0){
                    for (int s=0;s<experineceViewList.size();s++){
                        HashMap<String, String> dataListSimple1 = new HashMap<>();
                        String experienceFrom = experineceViewList.get(s).getdFrom();
                        String experienceTo = experineceViewList.get(s).getdTo();
                        String organizationName = experineceViewList.get(s).getdORGName();
                        dataListSimple1.put("experience_from", experienceFrom);
                        dataListSimple1.put("experience_to", experienceTo);
                        dataListSimple1.put("experience_org_name", organizationName);
                        deta2.add(dataListSimple1);
                    }


                        JSONObject jsonObject1 = new JSONObject();
                        JSONArray jsonArray1 = new JSONArray(deta2);
                        try {
                            jsonObject1.put("Experience", jsonArray1);
                            forExperience = jsonObject1.toString();

                            Log.e("TAg", "the complex json array to send on server checked: " + forExperience);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //


                        String finalString = forDiplomo+forDairy+forPets+forEquine+forBirds+forOther+forExperience+forSpecialist;
                        if (sp_select_qualification.getSelectedItemId() == 2){
                            finalString = finalString+forMphile;
                        }
                        Log.e("TAG", "final result is here: " + finalString);
                        ArrayList<String> finalArrayList = new ArrayList<>();
                        finalArrayList.add(forDiplomo);
                        finalArrayList.add(forDairy);
                        finalArrayList.add(forPets);
                        finalArrayList.add(forEquine);
                        finalArrayList.add(forBirds);
                        finalArrayList.add(forOther);
                        finalArrayList.add(forExperience);

                        JSONObject jsonObjectFinal = new JSONObject();

                        try {
                            jsonObjectFinal.put("Data", new JSONArray(finalArrayList));
                            finalData = jsonObjectFinal.toString();

                            Log.e("TAg", "the complex json array to send on server checked final: " + finalData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                    if (experineceViewList.size()>0){
                        for (int x=0;x<experineceViewList.size();x++) {

                            ArrayList<String> imageUriListForExperience = new ArrayList<>();

                            ArrayList<String> tempArray = experineceViewList.get(x).getUris();
                            if (tempArray.size()>0) {
                                for (int s=0;s<tempArray.size();s++) {
                                    imageUriListForExperience.add(tempArray.get(s).toString());
                                }
                            }
                            Log.e("TAG", "Array Size For Documents Experience: " + imageUriListForExperience.size());
                        }
                    }
                Log.e("TAG", "Array Size For Documents Diploma: " + topDocumentsArray.size());
                Log.e("TAg", "Array Size For Documents mPhile: " + mPhileDocuments.size());
               // Log.e("TAG", "Array Size For Documents Experience: " + imageUriListForExperience.size());


                String myID = Prefs.getUserIDFromPref(ProfileUpdateForVeterinary.this);

                //calling service to upload images
                    //upLoadingDataToServer(myID, finalData, topDocumentsArray, forExperience, forSpecialist, forMphile, mPhileDocuments, experineceViewList);




            }//end of overide click buttons
        });
    }

    public class TempDataClass{

        public String getdTo() {
            return dTo;
        }

        public String getdFrom() {
            return dFrom;
        }

        public String getdORGName() {
            return dORGName;
        }

        public ArrayList<String> getUris() {
            return uris;
        }

        String dTo, dFrom, dORGName;
        ArrayList<String> uris;

        public TempDataClass(String dTo, String dFrom, String dORGName, ArrayList<String> uris){

            this.dTo = dTo;
            this.dFrom = dFrom;
            this.dORGName = dORGName;
            this.uris = uris;
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileUpdateForVeterinary.this, DashboardVeterinarian.class));
        finish();
    }


    public void upLoadingDataToServer(String mID, String mDiplomaArray, ArrayList<String> topDocumentsImage, String mExpertiseArray
            ,String specialityArray, String mPhilArray, ArrayList<String> mPhileDocuments
            , ArrayList<TempDataClass> experineceViewList){
        
        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();
            //Creating a multi part request

            MultipartUploadRequest multipartUploadRequest = new MultipartUploadRequest(ProfileUpdateForVeterinary.this, uploadId, "url");

            multipartUploadRequest.addParameter("key", API.SI_KEY); //Adding text parameter to the request
            multipartUploadRequest.addParameter("id", mID);

            multipartUploadRequest.addParameter("diploma", mDiplomaArray);
            for (String diplomaImage : topDocumentsImage){
                multipartUploadRequest.addFileToUpload(diplomaImage, "diplomaImages");
            }

            multipartUploadRequest.addParameter("mPhileArray", mPhilArray);
            for (String mPhileImage : mPhileDocuments){
                multipartUploadRequest.addFileToUpload(mPhileImage, "mPhilImages");
            }

            multipartUploadRequest.addFileToUpload(specialityArray, "sepcialityArray");
            multipartUploadRequest.addFileToUpload(mExpertiseArray, "expertiese");

            for (TempDataClass experience : experineceViewList){
                String mFrom = experience.getdFrom().toString();
                String mTo = experience.getdTo().toString();
                String mOrganizationName = experience.dORGName.toString();
                ArrayList<String> exprienceImages = experience.getUris();

                multipartUploadRequest.addParameter("experinece_from", mFrom);
                multipartUploadRequest.addParameter("experinece_to", mTo);
                multipartUploadRequest.addParameter("experinece_organiztion", mOrganizationName);

                for (String expImages : exprienceImages){

                    multipartUploadRequest.addFileToUpload(expImages, "experienceImage"); //Adding file
                }
            }


            multipartUploadRequest.setMaxRetries(2);
            multipartUploadRequest.startUpload();



        } catch (Exception exc) {
            Toast.makeText(ProfileUpdateForVeterinary.this, exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
