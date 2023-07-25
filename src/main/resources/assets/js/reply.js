const reply = document.querySelector('.share-box-inner')
function showReply(id){
    const reply = document.getElementById('reply-comment-'+id);
    if(reply.style.display === 'none'){
        reply.style.display = 'block'
    }else {
        reply.style.display = 'none'
    }
}