package com.adslate.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.adslate.R;
import com.adslate.baseclasses.BaseActivity;
import com.adslate.helperclasses.GalleryHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateCampaign extends BaseActivity implements View.OnClickListener {
    private Spinner mSpinnerCountry,mSpCountryOther,mSpinnerState,mSpStateOther,mSpinnerCity,mSpCityOther,mSpinnerSpaceType,mSpinnerTypeofSpace,mSpPaymentMode,mSpPaymentMode1;
    private ImageView mIvStrtDate,mIvEndDate,mIvBackButton,mIvCamImg1,mIvCamImg2,mIvCamImg3,mIvCamImg4,mIvCamImg5,mIvCamLogo;
    private TextView mTvStartDate,mTvEndDate,mTvAddLocation;
    private ArrayList<String> countryList,stateList,cityList,spaceType,typeOfSpace,paymentModes;
    private LinearLayout mLinLayOtherLoc,mLinLayImgLay2;


    private int REQUEST_CAMERA = 100;
    private int SELECT_FILE = 200;
    private int imageNo = 0;

    DisplayImageOptions mOptions;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign);

        mOptions =  new DisplayImageOptions.Builder().considerExifParams(true).build();

        countryList = new ArrayList<String>();
        countryList.add("Country");
        countryList.add("India");
        countryList.add("Japan");
        countryList.add("U.S");

        stateList = new ArrayList<String>();
        stateList.add("State");
        stateList.add("Karnataka");
        stateList.add("Maharastra");
        stateList.add("Kerela");

        spaceType = new ArrayList<String>();
        spaceType.add("Individual");
        spaceType.add("Enterprise");

        cityList = new ArrayList<String>();
        cityList.add("City");
        cityList.add("Banglore");
        cityList.add("Manglore");
        cityList.add("Mysore");

        typeOfSpace = new ArrayList<String>();
        typeOfSpace.add("Vehicle");
        typeOfSpace.add("Wall");
        typeOfSpace.add("Personal events");

        paymentModes= new ArrayList<String>();
        paymentModes.add("Daily");
        paymentModes.add("Weekly");
        paymentModes.add("Monthly");

        mInitialiseViews();
    }

    private void mInitialiseViews()
    {
        mSpinnerCountry = (Spinner)findViewById(R.id.xSpinnerCountry);
        ArrayAdapter<String> countryListAdapter = new ArrayAdapter<String>(this,R.layout.spinner_value,countryList);
        mSpinnerCountry.setAdapter(countryListAdapter);

        mSpCountryOther = (Spinner)findViewById(R.id.xSpCountryOther);
        ArrayAdapter<String> countryOdrListAdapter = new ArrayAdapter<String>(this,R.layout.spinner_value,countryList);
        mSpCountryOther.setAdapter(countryOdrListAdapter);

        mSpinnerState = (Spinner)findViewById(R.id.xSpinnerState);
        ArrayAdapter<String> stateListAdapter = new ArrayAdapter<String>(this,R.layout.spinner_value,stateList);
        mSpinnerState.setAdapter(stateListAdapter);

        mSpStateOther = (Spinner)findViewById(R.id.xSpStateOther);
        ArrayAdapter<String> stateOdrListAdapter = new ArrayAdapter<String>(this,R.layout.spinner_value,stateList);
        mSpStateOther.setAdapter(stateOdrListAdapter);

        mSpinnerCity = (Spinner)findViewById(R.id.xSpinnerCity);
        ArrayAdapter<String> cityListAdapter = new ArrayAdapter<String>(this,R.layout.spinner_value,cityList);
        mSpinnerCity.setAdapter(cityListAdapter);

        mSpCityOther = (Spinner)findViewById(R.id.xSpCityOther);
        ArrayAdapter<String> cityOdrListAdapter = new ArrayAdapter<String>(this,R.layout.spinner_value,cityList);
        mSpCityOther.setAdapter(cityOdrListAdapter);

        mSpinnerSpaceType = (Spinner)findViewById(R.id.xSpinnerSpaceType);
        ArrayAdapter<String> spaceTypeAdapter = new ArrayAdapter<String>(this,R.layout.spinner_value,spaceType);
        mSpinnerSpaceType.setAdapter(spaceTypeAdapter);

        mSpinnerTypeofSpace = (Spinner)findViewById(R.id.xSpinnerTypeofSpace);
        ArrayAdapter<String> typeofSpace = new ArrayAdapter<String>(this,R.layout.spinner_value,typeOfSpace);
        mSpinnerTypeofSpace.setAdapter(typeofSpace);

        mSpPaymentMode = (Spinner)findViewById(R.id.xSpPaymentMode);
        ArrayAdapter<String> paymentModesAdapter = new ArrayAdapter<String>(this,R.layout.spinner_value,paymentModes);
        mSpPaymentMode.setAdapter(paymentModesAdapter);

        mSpPaymentMode1 = (Spinner)findViewById(R.id.xSpPaymentMode1);
        mSpPaymentMode1.setAdapter(paymentModesAdapter);

        mIvBackButton = (ImageView)findViewById(R.id.xIvBackButton);
        mIvStrtDate = (ImageView)findViewById(R.id.xIvStrtDate);
        mIvEndDate = (ImageView)findViewById(R.id.xIvEndDate);

        mTvStartDate = (TextView)findViewById(R.id.xTvStartDate);
        mTvEndDate = (TextView)findViewById(R.id.xTvEndDate);
        mTvAddLocation = (TextView)findViewById(R.id.xTvAddLocation);

        mIvCamLogo = (ImageView)findViewById(R.id.xIvCamLogo);
        mIvCamImg1 = (ImageView)findViewById(R.id.xIvCamImg1);
        mIvCamImg2 = (ImageView)findViewById(R.id.xIvCamImg2);
        mIvCamImg3 = (ImageView)findViewById(R.id.xIvCamImg3);
        mIvCamImg4 = (ImageView)findViewById(R.id.xIvCamImg4);
        mIvCamImg5 = (ImageView)findViewById(R.id.xIvCamImg5);

        mLinLayOtherLoc = (LinearLayout)findViewById(R.id.xLinLayOtherLoc);
        mLinLayImgLay2 = (LinearLayout)findViewById(R.id.xLinLayImgLay2);


        mIvBackButton.setOnClickListener(this);
        mIvStrtDate.setOnClickListener(this);
        mIvEndDate.setOnClickListener(this);
        mTvAddLocation.setOnClickListener(this);
        mIvCamImg1.setOnClickListener(this);
        mIvCamLogo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view == mIvBackButton)
        {
            finish();
        }
        else if(view == mIvCamLogo)
        {
            imageNo = 6;
            selectImage();
        }
        else if(view == mIvStrtDate)
        {
            startDatePicker();
        }
        else if(view == mIvEndDate)
        {
            EndDatePicker();
        }
        else if(view == mTvAddLocation)
        {
            mLinLayOtherLoc.setVisibility(View.VISIBLE);
        }
        else if(view == mIvCamImg1)
        {
            imageNo = 1;
            selectImage();
        }
        else if(view == mIvCamImg2)
        {
            imageNo = 2;
            selectImage();
        }
        else if(view ==  mIvCamImg3)
        {
            imageNo = 3;
            selectImage();
        }
        else if(view == mIvCamImg4)
        {
            imageNo = 4;
            selectImage();
        }
        else if(view == mIvCamImg5)
        {
            imageNo = 5;
            selectImage();
        }
    }

    private void selectImage()
    {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(CreateCampaign.this);


        builder.setTitle("Add Photo");
        builder.setItems(items, new DialogInterface.OnClickListener()
        {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int i)
            {

                if(items[i].equals("Take Photo"))
                {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                }
                else if(items[i].equals("Choose from Gallery"))
                {

                    Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);

                }

            }

        });
        builder.show();
        builder.setCancelable(true);

    }


    private void EndDatePicker()
    {
        int day,month,year;
        Calendar calender = Calendar.getInstance();
        day = calender.get(Calendar.DAY_OF_MONTH) + 1;
        month = calender.get(Calendar.MONTH);
        year = calender.get(Calendar.YEAR);

        DatePickerDialog endDateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                String endDate = String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear)+"/"+String.valueOf(year);
                mTvEndDate.setText(endDate);

            }
        },year,month,day);
        endDateDialog.show();

    }

    private void startDatePicker()
    {
        int day,month,year;
        Calendar calender = Calendar.getInstance();
        day = calender.get(Calendar.DAY_OF_MONTH);
        month = calender.get(Calendar.MONTH);
        year = calender.get(Calendar.YEAR);

        DatePickerDialog startDateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {

                String startDate = String.valueOf(dayOfMonth)+"/"+String.valueOf(monthOfYear)+"/"+String.valueOf(year);
                mTvStartDate.setText(startDate);
            }
        },year,month,day);
        startDateDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);


        if ((requestCode == SELECT_FILE || requestCode == REQUEST_CAMERA) && resultCode == Activity.RESULT_OK && data != null)
        {

            Uri selectedImage = null;
            String picturePath="";

            final File in;
            try
            {
                selectedImage = data.getData();

                //    picturePath = cursor.getString(columnIndex);
                if (Build.VERSION.SDK_INT < 19)
                {
                    Cursor cursor = null;
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    Log.e("", "" + picturePath);
                }
                else
                {
                    if(selectedImage != null)
                    {
                        picturePath = GalleryHelper.getPath(getApplicationContext(), selectedImage);
                        Log.e("", "" + picturePath);
                    } else
                    {
                        if(requestCode == REQUEST_CAMERA)//in some devices(kitkat, lollipop) i am getting the Uri is null. so i am getting the last image what i took from camera.
                        {
                            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    new String[] { MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.ImageColumns.ORIENTATION }, MediaStore.Images.Media.DATE_ADDED, null, "date_added ASC");
                            if (cursor != null && cursor.moveToLast())
                            {
                                selectedImage = Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
                                picturePath = selectedImage.toString();
                                cursor.close();
                            }
                        }
                    }
                }




                if(imageNo ==1)
                {
                    ImageLoader.getInstance().displayImage(selectedImage.toString(), mIvCamImg1, mOptions);

                    mIvCamImg2.setVisibility(View.VISIBLE);

                    mIvCamImg2.setOnClickListener(this);
                }
                else if(imageNo == 2)
                {
                    ImageLoader.getInstance().displayImage(selectedImage.toString(), mIvCamImg2, mOptions);

                    mIvCamImg3.setVisibility(View.VISIBLE);

                    mIvCamImg3.setOnClickListener(this);
                }
                else if(imageNo == 3)
                {
                    ImageLoader.getInstance().displayImage(selectedImage.toString(), mIvCamImg3, mOptions);

                    mLinLayImgLay2.setVisibility(View.VISIBLE);

                    mIvCamImg4.setVisibility(View.VISIBLE);
                    mIvCamImg4.setOnClickListener(this);

                }
                else if(imageNo == 4)
                {
                    ImageLoader.getInstance().displayImage(selectedImage.toString(), mIvCamImg4, mOptions);

                    mIvCamImg5.setVisibility(View.VISIBLE);
                    mIvCamImg5.setOnClickListener(this);
                }
                else if(imageNo == 5)
                {
                    ImageLoader.getInstance().displayImage(selectedImage.toString(), mIvCamImg5, mOptions);
                }
                else if(imageNo == 6)
                {
                    ImageLoader.getInstance().displayImage(selectedImage.toString(), mIvCamLogo, mOptions);
                }
                else
                {

                }


            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
