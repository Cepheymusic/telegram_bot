package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.entity.Task;
import pro.sky.telegrambot.repository.TaskRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component

public class Scheduler {
    private final TaskRepository taskRepository;
    private final TelegramBot telegramBot;
    private Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    public Scheduler(TaskRepository taskRepository, TelegramBot telegramBot) {
        this.taskRepository = taskRepository;
        this.telegramBot = telegramBot;
    }
    @Scheduled(cron = "0 0/1 * * * *")
    public void sendNotifications() {
        LocalDateTime currentMinute = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        List<Task> tasksToSend = taskRepository.findByDate(currentMinute);

        logger.info("Sending notifications...");

        for (Task task : tasksToSend) {
            logger.info("Sending notification to chatId: {}, text: {}", task.getIdChat(), task.getText());
            telegramBot.execute(new SendMessage(task.getIdChat(), task.getText()));

        }
    }
}

