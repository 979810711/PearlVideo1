package yjh.com.cn.pearlvideo.okHttpUtlis;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by 979810711 on 2019/3/19.
 */

public class JosnCreate {


    public static JSONObject addJosns(String head, HashMap<Object,Object> hashMap) {
        try {
            JSONObject jsonObjects= new JSONObject(hashMap);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("action", head);
            jsonObject.put("object", jsonObjects);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
         return null;
    }
}
