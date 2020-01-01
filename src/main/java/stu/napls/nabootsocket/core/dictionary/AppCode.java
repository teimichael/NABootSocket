package stu.napls.nabootsocket.core.dictionary;

import lombok.Getter;

public interface AppCode {

    @Getter
    enum Conversation {
        PRIVATE(0), GROUP(1);

        private final int value;

        Conversation(int value) {
            this.value = value;
        }
    }
}
