package com.backend.models;

import java.time.LocalDateTime;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msg_id;
    private String msg_content;
    private String msg_status;
    private LocalDateTime msg_creation_date;
    private Boolean msg_is_edited;
    // message type + attachments variables can be added

    @ManyToOne
    @JoinColumn(name = "msg_chat_id", referencedColumnName = "chat_id")
    @Nullable
    private Chats msg_chat;

    @ManyToOne
    @JoinColumn(name = "msg_created_by_emp_id", referencedColumnName = "emp_id")
    private Employees msg_created_by_employee;

    @ManyToOne
    @JoinColumn(name = "msg_reply_msg_id", referencedColumnName = "msg_id")
    @Nullable
    private Messages msg_reply_message;
}
