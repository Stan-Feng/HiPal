// Created by Junwen Feng, Anan Wang, Mengyu Wang and Min Cao
package app.android.stanfeng.com.hipal.utils;

import org.json.JSONArray;

/**
 * Interface of callback function
 */
public interface Callback{
    /**
     * @param target - the object will be manipulated in the future
     * @param results - response from server
     */
    void exec(Object target, JSONArray results);
}
