package com.example.beacon;

/**
 * Created by petro on 26-Nov-16.
 */

public interface BeaconIF {

    public String getUUID();

    public void setUUID(String uuid);

    public Integer getMajor();

    public void setMajor(int major);

    public Integer getMinor();

    public void setMinor(int minor);

}
