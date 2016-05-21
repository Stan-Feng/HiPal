package app.android.stanfeng.com.hipal;

import org.json.JSONArray;
import org.json.JSONObject;

public class Plan {
    private final String _id;
    private final String name;
    private final String startDate;
    private final String endDate;
    private final Boolean isArchived;
    private final JSONObject city;
    private final JSONObject user;
    private final JSONArray labels;

    public Plan (String _id, String name, String startDate, String endDate, Boolean isArchived,
                 JSONObject city, JSONObject user, JSONArray labels) {
        this._id = _id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isArchived = isArchived;
        this.city = city;
        this.user = user;
        this.labels = labels;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Boolean getIsArchived() {
        return isArchived;
    }

    public JSONObject getCity() {
        return city;
    }

    public JSONObject getUser() {
        return user;
    }

    public JSONArray getLabels() {
        return labels;
    }
}
