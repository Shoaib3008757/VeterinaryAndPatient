package ranglerz.veterinaryandpatient;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.Permission;
import java.security.cert.CRLException;

import ranglerz.veterinaryandpatient.utils.Permissions;
import ranglerz.veterinaryandpatient.utils.Utility;

public class OrganizationSaleProductActivity extends AppCompatActivity {

    RelativeLayout rl_spiner_product_type, rl_spiner_product_category, rl_spiner_product_sub_category;
    Spinner sp_select_product_type, sp_select_product_category, sp_select_product_sub_category;
    RelativeLayout rl_image_1, rl_image_2, rl_image_3, rl_image_4;
    ImageView tv_image_1, tv_image_2 , tv_image_3, tv_image_4;
    TextView tx_image_1, tx_image_2, tx_image_3, tx_image_4;
    RelativeLayout rl_bt_post;
    EditText et_product_name, et_product_price;
    EditText et_product_description;


    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Uri imageUri = null;
    int INDICATOR = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_sale_product);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getSupportActionBar().setTitle(R.string.title_sale_products);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(OrganizationSaleProductActivity.this ,R.color.colorBlue)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        Permissions.requestAppPermissions(OrganizationSaleProductActivity.this);

        onProdcutTypeSelectListner();

        getImage1();
        getImage2();
        getImage3();
        getImage4();

        postButtonClickHandler();
        spCategorySelectorHandler();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    private void init(){

        rl_spiner_product_type = (RelativeLayout) findViewById(R.id.rl_spiner_product_type);
        rl_spiner_product_category = (RelativeLayout) findViewById(R.id.rl_spiner_product_category);
        rl_spiner_product_sub_category = (RelativeLayout) findViewById(R.id.rl_spiner_product_sub_category);


        sp_select_product_type  = (Spinner) findViewById(R.id.sp_select_product_type);
        ArrayAdapter adapterProductType = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                R.array.product_types, R.layout.spinner_item);
        adapterProductType.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_product_type.setAdapter(adapterProductType);



        sp_select_product_category  = (Spinner) findViewById(R.id.sp_select_product_category);
        /*ArrayAdapter adapterProductCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                R.array.product_categories_for_dairy, R.layout.spinner_item);
        adapterProductCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_product_category.setAdapter(adapterProductCategory);
*/

        sp_select_product_sub_category  = (Spinner) findViewById(R.id.sp_select_product_sub_category);
        /*ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                R.array.product_sub_categories_for_dairy_accesories, R.layout.spinner_item);
        adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sp_select_product_sub_category.setAdapter(adapterProductSubCategory);*/


        rl_image_1 = (RelativeLayout) findViewById(R.id.rl_image_1);
        rl_image_2 = (RelativeLayout) findViewById(R.id.rl_image_2);
        rl_image_3 = (RelativeLayout) findViewById(R.id.rl_image_3);
        rl_image_4 = (RelativeLayout) findViewById(R.id.rl_image_4);

        tv_image_1 = (ImageView) findViewById(R.id.tv_image_1);
        tv_image_2 = (ImageView) findViewById(R.id.tv_image_2);
        tv_image_3 = (ImageView) findViewById(R.id.tv_image_3);
        tv_image_4 = (ImageView) findViewById(R.id.tv_image_4);
        tx_image_1 = (TextView) findViewById(R.id.tx_image_1);
        tx_image_2 = (TextView) findViewById(R.id.tx_image_2);
        tx_image_3 = (TextView) findViewById(R.id.tx_image_3);
        tx_image_4 = (TextView) findViewById(R.id.tx_image_4);

        rl_bt_post = (RelativeLayout) findViewById(R.id.rl_bt_post);
        et_product_name = (EditText) findViewById(R.id.et_product_name);
        et_product_price = (EditText) findViewById(R.id.et_product_price);
        et_product_description = (EditText) findViewById(R.id.et_product_description);


        rl_spiner_product_category.setVisibility(View.GONE);
        rl_spiner_product_sub_category.setVisibility(View.GONE);

    }

    private void getImage1(){
        rl_image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gettingImagesDialog(1);


            }
        });
    }

    private void getImage2(){
        rl_image_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gettingImagesDialog(2);

            }
        });
    }
    private void getImage3(){
        rl_image_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gettingImagesDialog(3);
            }
        });
    }
    private void getImage4(){
        rl_image_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gettingImagesDialog(4);

            }
        });
    }


    private void gettingImagesDialog(final int imageIndicator) {

        boolean result =  Permissions.requestAppPermissions(OrganizationSaleProductActivity.this);
        if (result) {

            final Dialog dialog = new Dialog(OrganizationSaleProductActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custome_dialog_for_getting_photo);

            Button bt_dialog_from_gallary = (Button) dialog.findViewById(R.id.bt_dialog_from_gallary);
            Button bt_dialog_from_camera = (Button) dialog.findViewById(R.id.bt_dialog_from_camera);

            bt_dialog_from_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialog.dismiss();
                    cameraIntent(imageIndicator); //to detect that the view is from top document button view

                }
            });

            bt_dialog_from_gallary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    galleryIntent(imageIndicator);
                }
            });


            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimationTooDouen;
            dialog.show();


        }
    }

    private void cameraIntent(int imageIndicator)
    {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        String fileName = getFileName(imageUri);
        Log.e("TAG", "the upload file name is: " + fileName);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, REQUEST_CAMERA);

        INDICATOR = imageIndicator;
    }

    private void galleryIntent(int imageIndicator)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
        INDICATOR = imageIndicator;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("TAg", "The Request code is: " + requestCode);

        //  if (resultCode == Activity.RESULT_OK) {
        if (requestCode == SELECT_FILE) {

            if (data!=null) {
                try {
                imageUri = data.getData();
                String fileName = getFileName(imageUri);
                Log.e("TAG", "the upload file name is: " + fileName);

                if(INDICATOR == 1){

                        tv_image_1.setImageURI(imageUri);
                        tv_image_1.bringToFront();
                        tx_image_1.setText(imageUri.toString());
                        tx_image_1.setVisibility(View.GONE);

                }
                if(INDICATOR == 2){

                    tv_image_2.setImageURI(imageUri);
                    tv_image_2.bringToFront();
                    tx_image_2.setText(imageUri.toString());
                    tx_image_2.setVisibility(View.GONE);
                }
                if(INDICATOR == 3){
                    tv_image_3.setImageURI(imageUri);
                    tv_image_3.bringToFront();
                    tx_image_3.setText(imageUri.toString());
                    tx_image_3.setVisibility(View.GONE);
                }
                if(INDICATOR == 4){
                    tv_image_4.setImageURI(imageUri);
                    tv_image_4.bringToFront();
                    tx_image_4.setText(imageUri.toString());
                    tx_image_4.setVisibility(View.GONE);
                }
                }catch (OutOfMemoryError e){
                    e.printStackTrace();
                    System.gc();
                }

            }
        }
        else if (requestCode == REQUEST_CAMERA) {
            if (data!=null || imageUri!=null) {
                try{
                String fileName = getFileName(imageUri);
                if(INDICATOR == 1){
                    tv_image_1.setImageURI(imageUri);
                    tv_image_1.bringToFront();
                    tx_image_1.setText(imageUri.toString());
                    tx_image_1.setVisibility(View.GONE);
                }
                if(INDICATOR == 2){
                    tv_image_2.setImageURI(imageUri);
                    tv_image_2.bringToFront();
                    tx_image_2.setText(imageUri.toString());
                    tx_image_2.setVisibility(View.GONE);
                }
                if(INDICATOR == 3){
                    tv_image_3.setImageURI(imageUri);
                    tv_image_3.bringToFront();
                    tx_image_3.setText(imageUri.toString());
                    tx_image_3.setVisibility(View.GONE);
                }
                if(INDICATOR == 4){
                    tv_image_4.setImageURI(imageUri);
                    tv_image_4.bringToFront();
                    tx_image_4.setText(imageUri.toString());
                    tx_image_4.setVisibility(View.GONE);
                }
                }catch (OutOfMemoryError e){
                    e.printStackTrace();
                    System.gc();
                }
            }
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

    private void postButtonClickHandler(){

        rl_bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String spProductType = sp_select_product_type.getSelectedItem().toString();
                String spProdcutCategory = sp_select_product_category.getSelectedItem().toString();
                String spProdcutSubCategory = sp_select_product_sub_category.getSelectedItem().toString();


                int spProductTypePosition = sp_select_product_type.getSelectedItemPosition();
                int spProductCategoryPosition = sp_select_product_category.getSelectedItemPosition();
                int SpProdcutSubCategoryPosition  = sp_select_product_sub_category.getSelectedItemPosition();
                String productName = et_product_name.getText().toString();
                String productPrice = et_product_price.getText().toString();
                String productDescription = et_product_description.getText().toString();
                String imageUri1 = tx_image_1.getText().toString();
                String imageUri2 = tx_image_2.getText().toString();
                String imageUri3 = tx_image_3.getText().toString();
                String imageUri4 = tx_image_4.getText().toString();


                final Animation animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);


                if (spProductTypePosition == 0){
                    Toast.makeText(OrganizationSaleProductActivity.this, "Please Select Product Type", Toast.LENGTH_SHORT).show();
                    ((TextView)sp_select_product_type.getSelectedView()).setError("Please Select Prodcut Type");
                    rl_spiner_product_type.setAnimation(animShake);
                }
                else if (spProductCategoryPosition == 0){
                    Toast.makeText(OrganizationSaleProductActivity.this, "Please Select Product Category", Toast.LENGTH_SHORT).show();
                    ((TextView)sp_select_product_category.getSelectedView()).setError("Please Select Category");
                    rl_spiner_product_category.setAnimation(animShake);
                }
                else if (SpProdcutSubCategoryPosition == 0){
                    Toast.makeText(OrganizationSaleProductActivity.this, "Please Select Product Sub-Category", Toast.LENGTH_SHORT).show();
                    ((TextView)sp_select_product_sub_category.getSelectedView()).setError("Please Select Sub Category");
                    rl_spiner_product_sub_category.setAnimation(animShake);
                }
                else if (productName.length()==0){
                    Toast.makeText(OrganizationSaleProductActivity.this, "Please Enter Product Name", Toast.LENGTH_SHORT).show();
                    et_product_name.setAnimation(animShake);
                    et_product_name.setError("should not empty");
                }
                else if (productPrice.length()==0){
                    Toast.makeText(OrganizationSaleProductActivity.this, "Please Enter Product Price", Toast.LENGTH_SHORT).show();
                    et_product_price.setAnimation(animShake);
                    et_product_price.setError("should not empty");
                }
                else if (imageUri1.equals("Image 1")){
                    Toast.makeText(OrganizationSaleProductActivity.this, "Please Upload Prodcut Image", Toast.LENGTH_SHORT).show();
                    tx_image_1.setError("Upload Image");
                    rl_image_1.setAnimation(animShake);
                }
                else if (productDescription.length() == 0){
                    Toast.makeText(OrganizationSaleProductActivity.this, "Please Enter Short Description about product", Toast.LENGTH_SHORT).show();
                    et_product_description.setAnimation(animShake);
                    et_product_description.setError("should not empty");
                }
                else {
                    Log.i("TAG", "to send data on server selected Product Type: " + spProductType);
                    Log.i("TAG", "to send data on server selected Product Category: " + spProdcutCategory);
                    Log.i("TAG", "to send data on server selected Product Sub Cateogry: " + spProdcutSubCategory);
                    Log.i("TAG", "to send data on server selected product name: " + productName);
                    Log.i("TAG", "to send data on server selected Product price: " + productPrice);
                    Log.i("TAG", "to send data on server selected Product image 1: " + imageUri1);
                    Log.i("TAG", "to send data on server selected Product image 2: " + imageUri2);
                    Log.i("TAG", "to send data on server selected Product image 3: " + imageUri3);
                    Log.i("TAG", "to send data on server selected Product image 4: " + imageUri4);
                    Log.i("TAG", "to send data on server selected Product description: " + productDescription);


                }
            }
        });
    }

    private void onProdcutTypeSelectListner() {

        sp_select_product_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0){
                    rl_spiner_product_category.setVisibility(View.GONE);
                    rl_spiner_product_sub_category.setVisibility(View.GONE);
                }
                if (i==1){

                    sp_select_product_category  = (Spinner) findViewById(R.id.sp_select_product_category);
                    ArrayAdapter adapterProductCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_categories_for_dairy, R.layout.spinner_item);
                    adapterProductCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_category.setAdapter(adapterProductCategory);
                    rl_spiner_product_category.setVisibility(View.VISIBLE);

                }
                if (i==2){

                    sp_select_product_category  = (Spinner) findViewById(R.id.sp_select_product_category);
                    ArrayAdapter adapterProductCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_categories_for_pets, R.layout.spinner_item);
                    adapterProductCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_category.setAdapter(adapterProductCategory);
                    rl_spiner_product_category.setVisibility(View.VISIBLE);

                }

                if (i==3){

                    sp_select_product_category  = (Spinner) findViewById(R.id.sp_select_product_category);
                    ArrayAdapter adapterProductCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_categories_for_equine, R.layout.spinner_item);
                    adapterProductCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_category.setAdapter(adapterProductCategory);
                    rl_spiner_product_category.setVisibility(View.VISIBLE);

                }

                if (i==4){

                    sp_select_product_category  = (Spinner) findViewById(R.id.sp_select_product_category);
                    ArrayAdapter adapterProductCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_categories_for_bird, R.layout.spinner_item);
                    adapterProductCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_category.setAdapter(adapterProductCategory);
                    rl_spiner_product_category.setVisibility(View.VISIBLE);

                }

                if (i==5){

                    sp_select_product_category  = (Spinner) findViewById(R.id.sp_select_product_category);
                    ArrayAdapter adapterProductCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_categories_for_other, R.layout.spinner_item);
                    adapterProductCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_category.setAdapter(adapterProductCategory);
                    rl_spiner_product_category.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void spCategorySelectorHandler(){
        sp_select_product_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (sp_select_product_type.getSelectedItemId() == 1){

                if (i == 1){
                    rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                    ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_sub_categories_for_dairy_accesories, R.layout.spinner_item);
                    adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                }
                if (i == 2){
                    rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                    ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_sub_categories_for_dairy_feed, R.layout.spinner_item);
                    adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                }
                if (i == 3){
                    rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                    ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_sub_categories_for_dairy_animals, R.layout.spinner_item);
                    adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                }
                if (i == 4){
                    rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                    ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_sub_categories_for_dairy_milking_parlour, R.layout.spinner_item);
                    adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                }
                if (i == 5){
                    rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                    ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_sub_categories_for_dairy_medicine_products, R.layout.spinner_item);
                    adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                }
                if (i == 6){
                    rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                    ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                            R.array.product_sub_categories_for_dairy_sheed_construction, R.layout.spinner_item);
                    adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                    sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                }
                    if (i == 7){
                        rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                        ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                                R.array.product_sub_categories_for_dairy_labs, R.layout.spinner_item);
                        adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                    }
                    if (i == 8){
                        rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                        ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                                R.array.product_sub_categories_for_dairy_labour, R.layout.spinner_item);
                        adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                    }
                    if (i == 9){
                        rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                        ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                                R.array.product_sub_categories_for_dairy_land_on_rent, R.layout.spinner_item);
                        adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                    }
                    if (i == 10){
                        rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                        ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                                R.array.product_sub_categories_for_dairy_sprays, R.layout.spinner_item);
                        adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                    }
                    if (i == 11){
                        rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                        ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                                R.array.product_sub_categories_for_dairy_milk_sale_pur, R.layout.spinner_item);
                        adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                    }
                    if (i == 12){
                        rl_spiner_product_sub_category.setVisibility(View.VISIBLE);
                        ArrayAdapter adapterProductSubCategory = ArrayAdapter.createFromResource(OrganizationSaleProductActivity.this,
                                R.array.product_sub_categories_for_dairy_crops_seed, R.layout.spinner_item);
                        adapterProductSubCategory.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        sp_select_product_sub_category.setAdapter(adapterProductSubCategory);

                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
