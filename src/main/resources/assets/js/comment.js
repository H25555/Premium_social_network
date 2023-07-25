
const commentsCont = document.querySelector('#comment-container')
const submit = document.querySelector('#comment-submit')
function showComment(id) {
    const comment = document.getElementById('comment-'+id);
    if(comment.style.display === 'none'){
        comment.style.display = 'block'
    }else {
        comment.style.display = 'none'
    }

}

function getCommentsForPost(postId) {
    $.ajax({
        url: `/api/comments`,
        type: 'GET',
        data: {
            postId: postId

        },
        success: function (comments) {
            const commentsContainer = $(`#comment_${postId}`);
            commentsContainer.empty();

            $.each(comments, function (index, comment) {
                console.log(comment)
                // Hiển thị nội dung của bình luận
                const commentDiv = document.createElement('div');
                commentDiv.innerHTML = `
                         <div class="profile-thumb">
                                    <a href="#">
                                         <figure class="profile-thumb-middle" >
                                            <img  class="new-photo" src="assets/images/photos/photo1.jpg"/>
                                        </figure>
                                     </a>
                                 </div>
                                 <!-- profile picture end -->
                                <div class="comment-info">
                                     <p  class="comment">
                                        ${comment.user.username}
                                  </p>
                                     <button onclick="showReply()">
                                      <span class="bi bi-reply">Reply</span>
                                    </button>
                                         <span class="time-comment"></span>
                            </div>
                        <p>${comment.user.username}: ${comment.content.text}</p>
                    `;

                commentsContainer.append(commentDiv);
            });
        },
        error: function () {
            alert('Error getting comments');
        }
    });
}

function submitComment(id){
    let time = timeNow(id.comment_date)
    // lấy user
    // const userForm = userName.value
    // lấy bình luận
    const comment = document.querySelector('#comment'+id)
    const value = comment.value
    // nếu nhập vào là rỗng
    if(value !== ''){
        $.ajax({
            url: '/api/comments',
            type: 'POST',
            data: JSON.stringify({
                content: value,
                id: id
            }),
            contentType: 'application/json',
            success: function (response) {
                console.log(response)
                // Đã thêm mới bình luận thành công, cập nhật lại danh sách bình luận
                // getCommentsForPost(id);

                // Xóa nội dung trong textarea sau khi thêm bình luận thành công

                document.querySelector('#comment-'+id).style.display = 'block'
                document.querySelector('#comment'+id).value=''

                const divNew = document.createElement('div')
                divNew.classList ="form-comment"
                // 'comment-container'
                divNew.innerHTML += `

                                    <div class="profile-thumb">
                                    <a href="#">
                                        <figure class="profile-thumb-middle" >
                                            <img  class="new-photo" src="assets/images/photos/photo1.jpg"/>
                                        </figure>
                                    </a>
                                </div>
                                <!-- profile picture end -->

                                <div class="comment-info" id="commentSection">
                                    <p  class="comment" >${response.contentComment.text}</p>
                                    <button onclick="showReply(${response.id})">
                                        <span class="bi bi-reply" >Reply</span>
                                    </button>
                                    <span class="time-comment">${time}</span>
                              </div>

                                    <div class="reply-comment" id="reply-comment-${response.id}">
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
                                                    <button type="submit" class="btn-share" id="submit">Sent</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                            `
                document.querySelector('#comment-'+id).insertAdjacentElement('afterbegin', divNew);
                let countComment = +document.getElementById("countComment").textContent;
                document.getElementById("countComment").innerText = countComment + 1;
            },
            //thêm mới vào div
            error: function () {
                alert('Không thể thêm bình luận!');
            }
        });
    }
}

