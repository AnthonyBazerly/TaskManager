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
    private Long msgId;
    private String msgContent;
    private String msgStatus;
    private LocalDateTime msgCreationDate;
    private Boolean msgIsEdited;
    // message type + attachments variables can be added

    @ManyToOne
    @JoinColumn(name = "msgChatId", referencedColumnName = "chatId")
    @Nullable
    private Chats msgChat;

    @ManyToOne
    @JoinColumn(name = "msgCreatedByEmpId", referencedColumnName = "empId")
    private Employees msgCreatedByEmployee;

    @ManyToOne
    @JoinColumn(name = "msgReplyMsgId", referencedColumnName = "msgId")
    @Nullable
    private Messages msgReplyMessage;
}
