function uploadFile() {
    const fileInput = document.getElementById("fileInput");
    const allowedTypes = ["image/jpeg", "image/png", "image/gif", "video/mp4", "video/quicktime"];
    const files = fileInput.files;

    for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const fileType = file.type;

        if (!allowedTypes.includes(fileType)) {
            alert("Chỉ chọn các tệp ảnh (JPEG, PNG, GIF) hoặc video (MP4, QuickTime).");
            fileInput.value = ""; // Xóa tệp không hợp lệ khỏi ô chọn tệp
            return false;
        }
    }

    return true;

}
function createPost(e){
    e.preventDefault();
    const form = document.getElementById("postForm");
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());
    console.log(data);
    $.ajax({
        url: "http://localhost:8080/api/posts/create",
        type: "POST",
        headers: {
            'Accept': 'application/json',
        },
        processData: false,
        contentType: false,
        data: formData,
        success: function (response) {
            let postBlock = document.getElementById('post-block')

            console.log("response", response)
            postBlock.insertAdjacentHTML("afterbegin", renderData1(response));
        },
        error: function (error) {
            console.log(error);
        }
    });

}


function handleRenderDatas() {

    $.ajax({
        type: "get",
        dataType: 'json',
        url: "http://localhost:8080/api/posts",
        success: function (datas) {
            if(datas){
                datas.reverse();
            }
            console.log(datas)
            renderDatas(datas)

        },
        error: function (data, textStatus, errorThrown) {
            console.log("Data")
            /* $('#message').html('Email đã tồn tại'); */
        },
    });
}
handleRenderDatas()
function renderDatas(datas) {
    let html = ``;
    datas.forEach((data) => {
        html += renderData1(data)
        // renderComment(data.comments,data.id);
    })
    let postBlock = document.getElementById('post-block')
    postBlock.innerHTML = html;
}

function renderData1(data) {
    let heartIcon = '<img class="heart" src="assets/images/icons/heart.png" alt="">';
    if (data.like === true){
        heartIcon = '<img className="heart-color" src="assets/images/icons/heart-color.png" alt="">';
        }
    // let likesCount = data.likes?  data.likes.length : 0
   let time = timeNow(data.create_date)
    let countComment = data.comments?  data.comments.length : 0
    let mediaHtml = ``;
    let commentBlock = ``;
    if(data.content.media !== null){
    data.content.media.forEach((media) => {
        mediaHtml += `<img src=${media.url} alt="post image">`;
    });
    }
    if(data.comments && data.comments.length > 0){
        data.comments.reverse().forEach(comment => {
            commentBlock += `
                     <div class="form-comment" >
                                <!-- profile picture end -->
                                <div class="profile-thumb">
                                    <a href="#">
                                        <figure class="profile-thumb-middle" >
                                            <img  class="new-photo" src="assets/images/photos/photo1.jpg"/>
                                        </figure>
                                    </a>
                                </div>
                                <!-- profile picture end -->

                                <div class="comment-info" id="commentSection">
                                    <p  class="comment" >${comment.contentComment.text}</p>
                                    <button onclick="showReply(${comment.id})">
                                        <span class="bi bi-reply" >Reply</span>
                                    </button>
                                    <span class="time-comment"></span>
                              </div>

                                    <div class="reply-comment" id="reply-comment-${comment.id}">
                                        <div class="share-box-inner">
                                            <!-- profile picture end -->
                                            <div class="profile-thumb">
                                                <a href="#">
                                                    <figure class="profile-thumb-middle" >
                                                        <img  class="new-photo" id="userReply" src="assets/images/photos/photo1.jpg"/>
                                                    </figure>
                                                </a>
                                            </div>
                                            <!-- profile picture end -->

                                            <div class="share-content-box w-100">
                                                <form class="share-text-box">
                                                    <textarea name="share" id="commentReply" class="share-text-field" placeholder="Say Something"></textarea>
                                                    <button type="submit" class="btn-share" id="submit">SEND</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                </div> `
        });
    }


    return `<div class="card">
                    <!-- post title start -->
                    <div class="post-title d-flex align-items-center">
                        <!-- profile picture end -->
                        <div class="profile-thumb">
                            <a>
                                <figure class="profile-thumb-middle" >
                                    <img  class="new-photo" src="assets/images/photos/photo2.jpg" alt="profile picture">
                                </figure>
                            </a>
                        </div>
                        <!-- profile picture end -->

                        <div class="posted-author">
                            <h6 class="author"><a href="/profile">${data.user.fullName}</a></h6>
                            <span class="post-time">${time}</span>
                        </div>

                        <div class="post-settings-bar">
                            <span></span>
                            <span></span>
                            <span></span>
                            <div class="post-settings arrow-shape">
                                <ul>
                                    <li><button>copy link to adda</button></li>
                                    <li><button>edit post</button></li>
                                    <li><button>embed adda</button></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- post title start -->

                    <div class="post-content">
                        <p class="post-desc">
                            ${data.content.text}
                        </p>
                        <div class="post-thumb-gallery">
                            <figure class="post-thumb img-popup">
                                <a>
                                    ${mediaHtml}
                                </a>
                            </figure>
                        </div>
                        <div class="post-meta">
                            <button class="post-meta-like">
                                ${heartIcon}
                                <span>${data.likeCount} like</span>
                                <strong>201</strong>
                            </button>
                            <ul class="comment-share-meta">
                                <li onclick="showComment(${data.id})">
                                    <button class="post-comment" >
                                        <i class="bi bi-chat-bubble" ></i>
                                        <span id="countComment">${countComment}</span>
                                    </button>
                                </li>
                                <li>
                                    <button class="post-share">
                                        <i class="bi bi-share"></i>
                                        <span>0</span>
                                    </button>
                                </li>
                            </ul>
                        </div>

                        <div class="share-box-inner">
                            <!-- profile picture end -->
                            <div class="profile-thumb">
                                <a href="#">
                                    <figure class="profile-thumb-middle" >
                                        <img  class="new-photo" id="user" src="assets/images/photos/photo1.jpg"/>
                                    </figure>
                                </a>
                            </div>
                            <!-- profile picture end -->

                            <div class="share-content-box w-100">
                                <form class="share-text-box" id="commentForm">
                                    <textarea name="content" id="comment${data.id}" class="share-text-field" placeholder="Say Something"></textarea>
                                    <a type="submit" class="btn-share" onclick="submitComment('${data.id}')" >SEND</a>
                                </form>
                            </div>
                        </div>

                        <div class="comment-container" id="comment-${data.id}" >
                            ${commentBlock}
                                   </div>
                         </div>

                    </div>
                </div>`
}

function timeNow(date) {
// Tạo đối tượng Date từ chuỗi thời gian cụ thể
    let pastDate = new Date(date);

// Tính toán khoảng thời gian giữa thời điểm cụ thể và thời điểm hiện tại (tính bằng mili giây)
    let elapsed = new Date() - pastDate;

// Đổi đơn vị thời gian sang phút và làm tròn về số nguyên
    let elapsedMinutes = Math.round(elapsed / (1000 * 60));

// Hiển thị thời gian đã trôi qua theo đơn vị phù hợp
    if (elapsedMinutes == 0) {
        return  "Vừa xong";
    }
    else if (elapsedMinutes < 60) {
        return elapsedMinutes + " phút trước";
    } else if (elapsedMinutes < 24 * 60) {
        let elapsedHours = Math.round(elapsedMinutes / 60);
        return elapsedHours + " giờ trước";
    } else {
        let elapsedDays = Math.round(elapsedMinutes / (60 * 24));
        return elapsedDays + " ngày trước";
    }
}
