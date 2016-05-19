package app.android.stanfeng.com.hipal;

public class User {
    private final String id;
    private final String wechatID;
    private final String username;
    private final String nickname;
    private final String signature;
    private final String city;
    private final String gender;
    private final int age;

    public User (String username, String nickname, String id, String wechatID, String signature, String city, String gender, int age) {
        this.username = username;
        this.nickname = nickname;
        this.id = id;
        this.wechatID = wechatID;
        this.signature = signature;
        this.city = city;
        this.gender = gender;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getId() {
        return id;
    }

    public String getSignature() {
        return signature;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return nickname + " " + age + " " + gender;
    }

    public String getWechatID() {
        return wechatID;
    }
}
