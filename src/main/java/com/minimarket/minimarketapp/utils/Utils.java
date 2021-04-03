package com.minimarket.minimarketapp.utils;

import com.minimarket.minimarketapp.exceptions.MiniMarketException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static final Integer GMT = -3;

    public static void validateIdNull(Integer id, String errorMsg){
        if (id != null) {
            throw new MiniMarketException(errorMsg);
        }
    }

    public static void validateUrlIdEqualsBodyId(int urlId, Integer bodyId, String errorMsg){
        if (bodyId == null || urlId != bodyId.intValue()) {
            throw new MiniMarketException(errorMsg);
        }
    }

    public static JSONObject convertToJSON(Exception e, String context) throws Exception {
        JSONObject responseBody = new JSONObject();
        JSONObject errorTag = new JSONObject();
        responseBody.put("error", errorTag);

        errorTag.put("code", 400);
        errorTag.put("target", context);

        JSONArray detailList = new JSONArray();
        errorTag.put("details", detailList);

        String lastMessage = "";
        Throwable runner = e;
        while (runner!=null) {
            String className =  runner.getClass().getName();
            String msg =  runner.toString();

            runner = runner.getCause();

            JSONObject detailObj = new JSONObject();
            detailObj.put("message",msg);
            int dotPos = className.lastIndexOf(".");
            if (dotPos>0) {
                className = className.substring(dotPos+1);
            }
            detailObj.put("code",className);
            System.out.println("          ERR: "+msg);
            detailList.add(detailObj);
        }

        JSONObject innerError = new JSONObject();
        errorTag.put("innerError", innerError);

        JSONArray stackList = new JSONArray();
        runner = e;
        while (runner != null) {
            for (StackTraceElement ste : runner.getStackTrace()) {
                String line = ste.getFileName() + ":" + ste.getMethodName() + ":" + ste.getLineNumber();
                stackList.add(line);
            }
            stackList.add("----------------");
            runner = runner.getCause();
        }
        errorTag.put("stack", stackList);
        return responseBody;
    }

    public static Date setDateToSave(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, 16);
        return cal.getTime();
    }

    public static Date setTodayDate(){
        Date today = new Date();
        LocalDate localDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        today.setHours(13);
        today.setMinutes(0);
        today.setSeconds(0);
        return today;
    }

    public static Date addHoursToJavaUtilDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 19);
        return calendar.getTime();
    }

}
