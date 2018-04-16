package com.runoob.test;

import net.sf.json.JSONArray;

/**
 * Created by thinkpad on 2017/5/27.
 */
public class TEST {
    public static  void main(String args){
        String jsonStr = "[ { 'date': '17/5/24', 'open': '10', 'high': '11', 'low': '9.8', 'close': '10.2', 'code': '60001', 'name': 'kl', 'market': 'sa', 'increaseOrDecrease': '1%', 'preAdjClose': '1.2', 'plate': '1'},{'date': '17/5/25', 'open': '10', 'high': '11', 'low': '9.8', 'close': '10.2', 'code': '60001', 'name': 'kl', 'market': 'sa', 'increaseOrDecrease': '1%', 'preAdjClose': '1.2', 'plate': '1'},{'date': '17/5/26', 'open': '10', 'high': '11', 'low': '9.8', 'close': '10.2', 'code': '60001', 'name': 'kl', 'market': 'sa', 'increaseOrDecrease': '1%', 'preAdjClose': '1.2', 'plate': '1'},{'date': '17/5/27', 'open': '10', 'high': '11', 'low': '9.8', 'close': '10.2', 'code': '60001', 'name': 'kl', 'market': 'sa', 'increaseOrDecrease': '1%', 'preAdjClose': '1.2', 'plate': '1'}]";
        JSONArray jsonArray=JSONArray.fromObject(jsonStr);
        System.out.println(jsonStr);
    }
}
