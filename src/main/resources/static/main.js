let username;

$(document).ready(function () {
    if ($.cookie('token')) {
        $.ajaxSetup({
            headers: {
                'Authorization': $.cookie('token')
            }
        })
    }

    $.ajax({
        type: "POST",
        url: "/user/userinfo",
        contentType: "application/json",
        success: function (response) {
            username = response.username;
            $('#username').text(username);

        },
        error: function() {

        }
    })
})

function writePost(){
    if($('#username').text() == ''){
        alert("로그인이 필요합니다.")
        window.location.href = '/user/loginView'
        return;
    }
    $("#modal-write").addClass("is-active");
}

function checkEditable(id){
    let writer = $('#writer').text();
    console.log(username);
    if(username == writer){

        let title = $('#title').text();
        let contents = $('#contents').text();

        let PostRequestDto = {
            "title": title,
            "contents": contents
        }

        $.ajax({
            type: "POST",
            url: `/posts/edit/${id}`,
            data: JSON.stringify(PostRequestDto),
            contentType: "application/json",
            success: function (){
                window.location.href = `/posts/edit/${id}`
            },
            error: function (){
                alret("오류발생")
                window.location.reload();
            }
        })
    }else {
        alert("계정 불일치")
    }
}

function savePost(){
    let title = $('#save-title').val();
    let contents = $('#save-contents').val();

    let data = {
        "title": title,
        "contents": contents,
    }

    $.ajax({
        type: "POST",
        url: "/api/posts",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response){
            alert("게시글 저장 완료!");
            window.location.reload();
        }
    })
}

function deletePost(id) {
    if (confirm("게시글을 삭제하시겠습니까?") == true) {
        $.ajax({
            type: "DELETE",
            url: `api/posts/${id}`,
            success: function (response) {
                alert("게시글이 삭제되었습니다.")
                window.location.reload();
            },
            error: function (){
                alert("계정이 일치하지 않습니다.")
            }
        })
    } else {
        return;
    }
}

function updatePost(id){
    let editTitle = $("#editTitle").val();
    let editContents = $("#editContents").val();

    let data = {
        "title": editTitle,
        "contents": editContents
    }

    $.ajax({
        type: "PATCH",
        url: `/posts/${id}`,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response){
            alert("수정되었습니다.");
            window.location.href = `/posts/${id}`;
        }
    });
}

function showDeleteBtn(index){
    $(`#deleteBtn${index}`).removeClass('delete-post');
}

function hideDeleteBtn(index){
    $(`#deleteBtn${index}`).addClass('delete-post');
}


