package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrew Sealy
 * Business class containing business attributes
 */

public class Business implements Serializable {

    public  String uid;
    public String name;
    public String businessNumber;
    public String primaryBusiness;
    public String address;
    public String province;

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Constructor
     * @param uid -
     * @param name - required, 2-48 characters
     * @param businessNumber - required, 9-digit number
     * @param primaryBusiness - required, {Fisher, Distributor, Processor, Fish Monger}
     * @param address - <50 characters
     * @param province - {AB, BC, MB, NB, NL, NS, NT, NU, ON, PE, QC, SK, YT, “ “}
     *
     */
    public Business(String uid, String name, String businessNumber, String primaryBusiness, String address, String province){
        this.uid = uid;
        this.name = name;
        this.businessNumber=businessNumber;
        this.primaryBusiness=primaryBusiness;
        this.address=address;
        this.province=province;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name",name);
        result.put("businessNumber", businessNumber);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
