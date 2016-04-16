package app.android.stanfeng.com.hipal.utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * AJAX - Asynchronous JavaScript and XML.
 * This class can help developer create async network functionality.
 * Focusing on JSON (JavaScript Object Notation) data structure.
 * One AJAX instance stands for:
 *      "Request to Server -> Waiting Server Response -> Dealing Response" task.
 * Every time when you create an async task you need to override a method called "exec"
 *      which will be executed when response arrived.
 * Also, you need to pass the Object(View, Adapter, or something else) you want to manipulate
 *      to the constructor so that "exec" know who is the target.
 *
 * @author Junwen Feng, Group 5
 * @version 0.1.0
 */
public class AJAX extends AsyncTask<String, Void, JSONArray> {
    private String url;
    private String method;
    private HashMap<String, String> headers;
    private Callback callback;
    private Object target;

    /**
     * Constructor of AJAX
     * @param url - The target URL your request will send
     * @param method - HTTP verb
     * @param headers - Headers
     * @param target - The target Object will be referenced in callback
     * @param callback - A method will be executed after response arrived
     */
    public AJAX (String url, String method, HashMap<String, String> headers, Object target, Callback callback) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.target = target;
        this.callback = callback;
    }

    @Override
    protected JSONArray doInBackground(String... params){
        String result = "";
        BufferedReader reader = null;
        HttpURLConnection urlConnection = null;

        try {
            Uri builtUri = Uri.parse(this.url).buildUpon().build();
            URL url = new URL(builtUri.toString());
            Log.w("Built URL is: ", url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            // Set Method
            urlConnection.setRequestMethod(this.method);
            // Set headers
            for (String key: headers.keySet()) {
                urlConnection.setRequestProperty(key, headers.get(key));
            }
            InputStream inputStream = urlConnection.getInputStream();

            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = reader.readLine()) != null) {
                Log.w("Buffer: ", buffer.toString());
                Log.w("Line: ", line);
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            result = buffer.toString();
            Log.w("Response from server", result);
        }
        catch (IOException e) {
            Log.e("Ajax Call Error",e.toString());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("Fail to close reader", "Error closing frame", e);
                }
            }
        }

        // Turn String to JSON array
        try {
            return parseJSON(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONArray results) {
        callback.exec(target, results);
    }

    private JSONArray parseJSON (String resultStr) throws JSONException{
        JSONArray result = null;
        try {
            result = new JSONArray(resultStr);
        } catch (JSONException e) {
            result = new JSONArray();
            result.put(new JSONObject(resultStr));
        }

        return result;
    }
}
