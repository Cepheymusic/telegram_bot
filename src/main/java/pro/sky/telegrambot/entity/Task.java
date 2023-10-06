package pro.sky.telegrambot.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private long idChat;
    private String text;
    private LocalDateTime date;
    public LocalDateTime getDate() {
        return date;
    }

    public Task() {

    }
    public Task(long idChat, String text, LocalDateTime date) {
        this.id = id;
        this.idChat = idChat;
        this.text = text;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getIdChat() {
        return idChat;
    }

    public void setIdChat(long idChat) {
        this.idChat = idChat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idChat == task.idChat && Objects.equals(id, task.id) && Objects.equals(text, task.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idChat, text);
    }
}
