package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Task;
import pro.sky.telegrambot.repository.TaskRepository;

import static pro.sky.telegrambot.service.ParsNotificationTask.parsMassage;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private TelegramBot telegramBot;
    private final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    public TaskService(TaskRepository taskRepository, TelegramBot telegramBot) {
        this.taskRepository = taskRepository;
        this.telegramBot = telegramBot;
    }

    public void saveTaskInRepository(String messageText, Long idChat) {
        Task task = parsMassage(messageText, idChat, telegramBot);
        if (task != null) {
            taskRepository.save(task);
            logger.info("Сохраняем уведомление для чата: {},  с текстом: {}", idChat, task.getText());
        }
    }


}
