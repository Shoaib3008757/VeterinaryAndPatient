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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import ranglerz.veterinaryandpatient.utils.Permissions;

public class SaleAnimals extends AppCompatActivity {

    TextView tv_animal_name, animal_type;
    EditText et_product_price;
    RelativeLayout rl_image_1, rl_image_2, rl_image_3, rl_image_4;
    ImageView tv_image_1, tv_image_2, tv_image_3, tv_image_4;
    TextView tx_image_1, tx_image_2, tx_image_3, tx_image_4;
    EditText et_product_description;
    RelativeLayout rl_bt_post;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Uri imageUri = null;
    int INDICATOR = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_animals);

        init();
        getImage1();
        getImage2();
        getImage3();
        getImage4();

        postButtonClickHandler();
    }

    private void init(){

        tv_animal_name = (TextView) findViewById(R.id.tv_animal_name);
        animal_type = (TextView) findViewById(R.id.animal_type);

        et_product_price = (EditText) findViewById(R.id.et_product_price);
        et_product_description = (EditText) findViewById(R.id.et_product_description);

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

        Intent i = getIntent();
        String animalType = i.getExtras().getString("type");
        String animalName = i.getExtras().getString("item");
        animal_type.setText(animalName);
        tv_animal_name.setText(animalType);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(SaleAnimals.this ,R.color.colorBlue)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (animalType.equals("Dairy")){
            getSupportActionBar().setTitle("Sale Dairy");
        }
        if (animalType.equals("Pet")){
            getSupportActionBar().setTitle("Sale Pet");
        }
        if (animalType.equals("Equine")){
            getSupportActionBar().setTitle("Sale Equine");
        }
        if (animalType.equals("Bird")){
            getSupportActionBar().setTitle("Sale Bird");
        }
        if (animalType.equals("Other")){
            getSupportActionBar().setTitle("Sale Wild Animal");
        }


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

        boolean result =  Permissions.requestAppPermissions(SaleAnimals.this);
        if (result) {

            final Dialog dialog = new Dialog(SaleAnimals.this);
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


                String productPrice = et_product_price.getText().toString();
                String productDescription = et_product_description.getText().toString();
                String imageUri1 = tx_image_1.getText().toString();
                String imageUri2 = tx_image_2.getText().toString();
                String imageUri3 = tx_image_3.getText().toString();
                String imageUri4 = tx_image_4.getText().toString();


                final Animation animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);


           if (productPrice.length()==0){
                    Toast.makeText(SaleAnimals.this, "Please Enter Product Price", Toast.LENGTH_SHORT).show();
                    et_product_price.setAnimation(animShake);
                    et_product_price.setError("should not empty");
                }
                else if (imageUri1.equals("Image 1")){
                    Toast.makeText(SaleAnimals.this, "Please Upload Prodcut Image", Toast.LENGTH_SHORT).show();
                    tx_image_1.setError("Upload Image");
                    rl_image_1.setAnimation(animShake);
                }
                else if (productDescription.length() == 0){
                    Toast.makeText(SaleAnimals.this, "Please Enter Short Description about product", Toast.LENGTH_SHORT).show();
                    et_product_description.setAnimation(animShake);
                    et_product_description.setError("should not empty");
                }
                else {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

}
