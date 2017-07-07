package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrew Sealy
 * Detail activity for detailed view of business when user clicks on a business
 */
public class DetailViewActivity extends Activity {

    private EditText nameField, businessNumberField, primaryBusinessField, addressField, provinceField;
    private Business receivedBusinessInfo;
    private MyApplicationData appState;


    /**
     * create business function
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");

        nameField = (EditText) findViewById(R.id.name);
        businessNumberField = (EditText) findViewById(R.id.businessNumber);
        primaryBusinessField = (EditText) findViewById(R.id.primaryBusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedBusinessInfo != null){
            nameField.setText(receivedBusinessInfo.name);
            businessNumberField.setText(receivedBusinessInfo.businessNumber);
            primaryBusinessField.setText(receivedBusinessInfo.primaryBusiness);
            addressField.setText(receivedBusinessInfo.address);
            provinceField.setText(receivedBusinessInfo.province);
        }
    }

    /**
     * update function
     * @param v
     */
    public void updateBusiness(View v){

        appState = ((MyApplicationData) getApplicationContext());
        String name = nameField.getText().toString();
        String businessNumber = businessNumberField.getText().toString();
        String primaryBusiness = primaryBusinessField.getText().toString();
        String address = addressField.getText().toString();
        String province = provinceField.getText().toString();
        Business b = new Business(receivedBusinessInfo.uid, name, businessNumber, primaryBusiness, address, province);
        appState.firebaseReference.child(receivedBusinessInfo.uid).setValue(b);
        finish();

    }

    /**
     * delete function
     * @param v
     */
    public void eraseBusiness(View v){
        appState = ((MyApplicationData) getApplicationContext());
        appState.firebaseReference.child(receivedBusinessInfo.uid).removeValue();
        finish();

    }
}
