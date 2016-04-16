### Example
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

** You should call ajaxInstance.execute() then the request will be sent out !!! **