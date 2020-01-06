const CODE = {
    success: 200,
    failure: -1,
    unAuth: 401
};

const SERVER = 'http://localhost:9010';

const GLOBAL = {
    loginURL: SERVER + '/access/login',
    socketURL: SERVER + '/nabootsocket'
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

    stompClient.connect({}, function () {
        // Parse session id
        const urlSlice = stompClient.ws._transport.url.split('/');
        const sessionId = urlSlice[urlSlice.length - 2];

        // Login by HTTP
        const loginParam = {
            sessionId: sessionId,
            authLogin: {
                username: $('#username').val(),
                password: $('#password').val()
            }
        };
        $.ajax({
            type: "POST",
            url: GLOBAL.loginURL,
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(loginParam),
            success: function (data) {
                if (data.code === CODE.success) {
                    $('#my-uuid').val(data.data.uuid);
                    setConnected(true);
                    console.log('Connected');
                } else {
                    alert(data.message);
                    disconnect();
                }
            }

        });


        // Private chat channel
        stompClient.subscribe('/user/private/message', function (data) {
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
