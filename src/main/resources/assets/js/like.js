const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);
// let client = Stomp.client('ws://localhost:8080/ws');
const userInteract = document.getElementById("user-hidden").value


// Đăng ký lắng nghe trên topic "/topic/notifications" để nhận thông báo từ server
// stompClient.connect({}, function (frame) {
//     console.log('socket ==============')
//     console.log(socket)
//     console.log('Connected: ' + frame);
//     stompClient.subscribe('/user/queue/notifications', function (notification) {
//         console.log("notification ===========")
//         console.log(notification)
//         var notificationReceive = JSON.parse(notification.body);
//         console.log('Received notification: ' + notificationReceive.content);
//         alert("hello")
//     });
// });
stompClient.connect({}, function (frame) {
    var userDestination = '/user/' + frame.headers['user-name'] + '/queue/notifications';
    console.log(frame.headers['user-name']+'/queue/notifications' )
    stompClient.subscribe(frame.headers['user-name']+'/queue/notifications' , function (message) {
        var notification = JSON.parse(message.body);
        console.log('Received notification for user: ' + notification.message);
    });
});
function like(postId, likeCount) {
    var heartImage = document.querySelector(`.heart${postId}`);
    var heartColorImage = document.querySelector(`.heart-color${postId}`);
    var likeCountElement = document.getElementById(`likeCount${postId}`);

    // Kiểm tra nếu thẻ heart đang hiển thị thì ẩn nó và hiển thị thẻ heart-color
    if (heartImage.style.display === 'inline-block') {
        heartImage.style.display = 'none';
        heartColorImage.style.display = 'inline-block';
        likeCount++
    } else {
        // Ngược lại, nếu thẻ heart-color đang hiển thị thì ẩn nó và hiển thị thẻ heart
        heartImage.style.display = 'inline-block';
        heartColorImage.style.display = 'none';
        likeCount--
    }

    likeCount = Math.max(0, likeCount);
    likeCountElement.textContent = `${likeCount} like`;
    sendNotification(postId)

    sendApi(postId)

}
function sendApi(postId){
    $.ajax({
        url: "http://localhost:8080/api/likes?idPost=" + postId,
        type: "GET",
    })
}
function sendNotification(postId){
    stompClient.send(`/app/like/${postId}`, {}, {})
}

callback = function(message) {
    // called when the client receives a STOMP message from the server
    if (message.body) {
        alert("got message with body " + message.body)
    } else {
        alert("got empty message");
    }
};

// var subscription = stompClient.subscribe("/queue/notifications", callback);


