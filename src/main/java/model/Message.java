package model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String message;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;

	private LocalDateTime localDateTime;

	public Message() {

	}

	public Message(String message, User owner, LocalDateTime localDateTime) {
		this.message = message;
		this.owner = owner;
		this.localDateTime = localDateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public String toString() {
		return "Message{" +
				"message='" + message + '\'' +
				", owner=" + owner.getName() +
				", localDateTime=" + localDateTime +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Message message1 = (Message) o;

		if (!Objects.equals(message, message1.message)) return false;
		return Objects.equals(localDateTime, message1.localDateTime);
	}

	@Override
	public int hashCode() {
		int result = message != null ? message.hashCode() : 0;
		result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
		return result;
	}
}
