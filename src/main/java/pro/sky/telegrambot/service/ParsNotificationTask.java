package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.sky.telegrambot.entity.Task;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsNotificationTask {
    private Logger logger = LoggerFactory.getLogger(ParsNotificationTask.class);

    public static Task parsMassage(String text) {
        Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find() && matcher.groupCount() == 3) {
            String date = matcher.group(1);
            String item = matcher.group(3);


        }
        return null;
    }
}
