
package com.emercy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.parse_xml).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmlPullParserFactory pullParserFactory = null;

                try {
                    try {
                        pullParserFactory = XmlPullParserFactory.newInstance();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                    XmlPullParser parser = pullParserFactory.newPullParser();

                    InputStream in_s = getApplicationContext().getAssets().open("heros.xml");
                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(in_s, null);

                    ArrayList<Hero> heros = parseXML(parser);

                    String text = "";

                    for (Hero hero : heros) {

                        text += "id : " + hero.getId() + " name : " + hero.getName() + " description : " + hero.getDescription() + "\n";
                    }

                    Log.d("\nXML Parser", text);

                } catch (XmlPullParserException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ArrayList<Hero> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<Hero> heros = null;
        int eventType = parser.getEventType();
        Hero hero = null;

        // 判断是否结束
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    // 处理开始标签，在开始的时候创建英雄List
                    heros = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    // 处理tag开始，在这里接收英雄及英雄属性
                    name = parser.getName();
                    if (name.equals("hero")) {
                        hero = new Hero();
                        hero.id = parser.getAttributeValue(null, "id");
                    } else if (hero != null) {
                        if (name.equals("name")) {
                            hero.name = parser.nextText();
                        } else if (name.equals("description")) {
                            hero.description = parser.nextText();
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    // 标签结束，将英雄添加到英雄列表
                    name = parser.getName();
                    if (name.equalsIgnoreCase("hero") && hero != null) {
                        heros.add(hero);
                    }
            }
            // 处理下一个标签
            eventType = parser.next();
        }
        return heros;
    }
}