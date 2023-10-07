package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.sky.telegrambot.entity.Task;
import pro.sky.telegrambot.listener.TelegramBotUpdatesListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParsNotificationTask {
    private Logger logger = LoggerFactory.getLogger(ParsNotificationTask.class);

    public static Task parsMassage(String messageText, Long idChat, TelegramBot telegramBot) {
        Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
        Matcher matcher = pattern.matcher(messageText);
        if (matcher.find() && matcher.groupCount() == 3) {
            String dateTime = matcher.group(1);
            String text = matcher.group(3);

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                LocalDateTime date = LocalDateTime.parse(dateTime, formatter);

                if (date.isBefore(LocalDateTime.now())) {
                    telegramBot.execute(new SendMessage(idChat, "Ошибка: не верно задано время"));
                    return null;
                }
                return new Task(idChat, text, date);
            } catch (Exception exception) {
                telegramBot.execute(new SendMessage(idChat, "Ошибка, неверный формат"));
            }
        }
        telegramBot.execute(new SendMessage(idChat, "Ошибка, неверный формат"));
        return null;

    }
}
