const CODE = {
    success: 200,
    failure: -1,
    unAuth: 401
};

const GLOBAL = {
    socketURL: 'http://localhost:9001/websocket'
};

let stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#record").html("");
}

function connect() {
    const socket = new SockJS(GLOBAL.socketURL);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        const url = stompClient.ws._transport.url;
        console.log(url);
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/user/auth', function (data) {
            data = JSON.parse(data.body);
            // Authentication channel
            if (data.code === CODE.success) {
                $("#my-uuid").val(data.data.uuid);
            } else if (data.code === CODE.unAuth) {
                alert(data.message);
                disconnect();
            } else if (data.code === CODE.failure) {
                alert(data.message);
            }
        });

        // Authentication
        const auth = {
            token: $("#token").val()
        };
        stompClient.send("/to/auth", {}, JSON.stringify(auth));

        // Chat channel
        stompClient.subscribe('/user/single/message', function (data) {
            data = JSON.parse(data.body);
            if (data.code === CODE.success) {
                const message = data.data;
                showRecord(message)
            } else {
                alert(data.message)
            }
        });

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.send("/to/logout", {});
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    const message = {
        sender: $("#my-uuid").val(),
        receiver: $("#target-uuid").val(),
        content: $("#message").val()
    };
    stompClient.send("/to/chat/send", {}, JSON.stringify(message));
}

function showRecord(message) {
    const record = "<tr>" +
        "<td>" + message.sender + "</td>" +
        "<td>" + message.receiver + "</td>" +
        "<td>" + message.content + "</td>" +
        "<td>" + message.timestamp + "</td>" +
        "</tr>";
    $("#record").append(record);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendMessage();
    });
});
