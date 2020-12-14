
package com.emercy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends Activity {
    public static final String JSON_STRING = "{\"Engineers\":[{\"skill\":\"Android\",\"language\":\"Java\",\"years\":\"5\"},{\"skill\":\"iOS\",\"language\":\"Object C\",\"years\":\"2\"},{\"skill\":\"Server\",\"language\":\"php\",\"years\":\"8\"}]}";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.parse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parse(JSON_STRING);
            }
        });
    }

    private void parse(String jsonStr) {
        TextView textView = (TextView) findViewById(R.id.json);
        try {
            JSONObject engineers = new JSONObject(jsonStr);

            JSONArray array = engineers.getJSONArray("Engineers");

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < array.length(); i++) {
                JSONObject engineer = array.getJSONObject(i);
                String skill = engineer.getString("skill");
                String language = engineer.getString("language");
                int years = engineer.getInt("years");

                stringBuilder.append("Engineer ").append(i)
                        .append(": skill is ").append(skill)
                        .append("; language is ").append(language)
                        .append("; years is ").append(years).append("\n");

            }
            textView.setText(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}