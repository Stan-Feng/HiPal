### Intro
AJAX - Asynchronous JavaScript and XML.

This class can help developer create **async network functionality**, focusing on **JSON (JavaScript Object Notation) data structure**.

One AJAX instance stands for:

      "Request to Server -> Waiting Server Response -> Dealing Response" task.

Every time when you create an async task you need to override a method called **"exec"**, which will be executed when response arrived.

 Also, you need to pass the Object(View, Adapter, or something else) you want to manipulate to the constructor so that "exec" know who is the target.

### How to use
* Copy AJAX.java and Callback.java to your package
* Change the package of both files to your package name
* Create Instance
```java
AJAX request = new AJAX(String YourURL, String, YourMethod, HashMap<String, String> YourHeaders, Object YourTarget, new Callback() {
      @Override
      public void exec(Object target, JSONArray results) {
        //Whatever you want to do after response arrived
      }
  })
  ```
* call request.execute() to send out request

**The param "target" in exec() method is the one you passed in "new AJAX", they are the same one**

**If there's no reference pass the target reference to AJAX constructor, then it does not know who to manipulate**

**The data you received will be parsed to JSONArray, even though it is only one JSON object**

### Limitations
* Only supports "UPDATE" and "POST" HTTP verb for attaching data
* Attaching data structure is only **JSONObject**, not JSONArray, not String....
* Does not deal with response headers
* No error handler for bad response

### Example
* No attaching data example

  Suppose there's an adapter linked to a ListView

  I want to update the view by using the data from server

  ```java
    ArrayAdapter<String> myAdapter = .......;

    // Set HTTP request config
    String method = "GET";
    String url = "http://45.79.1.223:3000/api/posts/";
    HashMap<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "application/json");

    AJAX req = new AJAX(url, method, headers, myAdapter, new Callback() {
        @Override
        // The parameter "target" in this case is corresponded to param "myAdapter" above
        public void exec(Object target, JSONArray results) {
            // Extract "title" property from results
            String[] titles = new String[results.length()];
            ArrayAdapter<String> adapter = (ArrayAdapter<String>) target;

            for (int i = 0; i < results.length(); i++) {
                try {
                    titles[i] = results.getJSONObject(i).getString("title");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            // update adapter
            for (String title: titles) {
                adapter.add(title);
            }
        }
    });

    // Sending the request
    req.execute();
  ```
* Attaching data example
   Sending request for user authentication
 ```java
  String url = "http://45.79.1.223:3000/auth/signin";
  HashMap<String, String> headers = new HashMap<String, String>();
  headers.put("Content-Type", "application/json");
  JSONObject data = new JSONObject();
  try {
      data.put("username", "Junwen Feng");
      data.put("password", "test123");
  } catch (JSONException e) {
      Log.e("JSON parse Error", e.toString());
  }

  // We do nothing on view layer in this example, so set the 5th param to "null"
  AJAX req = new AJAX(url, "POST", headers, data, null, new Callback() {
      @Override
      public void exec(Object target, JSONArray results) {
          // Do nothing, just printout the response token
          Log.w("Auth Response", results.toString());
          // Sample Output:
          // W/Auth Response: [{"response":"\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1NzEwYTRjOWUxODdjYTA0MjQwNTQ3ZjUiLCJpYXQiOjE0NjA4NzcwNzcsImV4cCI6MTQ2MTc0MTA3N30.7cw68UNNm1uzSaS9NFIpCx7aPhsVe7eKmshwHG3DLyk\"\n"}]
      }
  });
  // Sending request
  req.execute();
 ```

**You should call ajaxInstance.execute() then the request will be sent out !!!**