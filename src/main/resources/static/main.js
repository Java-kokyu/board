function savePost(){
    let title = $('#save-title').val();
    let contents = $('#save-contents').val();
    let username = $('#save-username').val();
    let password = $('#save-password').val();

    let data = {
        "title": title,
        "username": username,
        "contents": contents,
        "password": password
    }

    console.log(data);

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
            }
        })
    } else {
        return;
    }
}

// function showPost(id) {
//     $("#modal-post").addClass("is-active");
//     $.ajax({
//         type: "GET",
//         url: `/api/posts/${id}`,
//         success: function (response){
//             $('#modal-post').empty();
//             let tempModal = addModal(response, id);
//             $('#modal-post').append(tempModal);
//         }
//     })
// }

function addModal(post, id){
    return `<div class="modal-background" onclick='$("#modal-post").removeClass("is-active")'></div>
    <div class="modal-card">
        <header class="modal-card-head">
            <p class="modal-card-title">${post.title}</p>
            <p class="text-sm">${post.username}<span style="color: #cccccc"><br>${post.modifiedAt.substring(0,10)}</span></p>

            <button class="delete" aria-label="close" onclick='$("#modal-post").removeClass("is-active")'></button>
        </header>
        <section class="modal-card-body">
            ${post.contents}
        </section>
        <footer class="modal-card-foot">
            <div class="level-right">
                <div class="level-item">
                    <a class="button is-link" onclick='checkEditPassword(${id})'>수정</a>
                </div>
                <div class="level-item">
                    <a class="button is-sparta is-outlined"
                       onclick='$("#modal-post").removeClass("is-active")'>취소</a>
                </div>
            </div>
        </footer>
    </div>`
}

function checkEditPassword(id){
    $("#modal-post").removeClass("is-active");
    $('#modal-checkPassword').empty();
    let temp_html =  `<div class="modal-background" onclick='$("#modal-checkPassword").removeClass("is-active")'></div>
                        <div class="modal-card">
                            <header class="modal-card-head">
                                <p class="modal-card-title">비밀번호 확인</p>
                                <button class="delete" aria-label="close" onclick='$("#modal-checkPassword").removeClass("is-active")'></button>
                            </header>
                            
                            <section class="modal-card-body">           
                                <label class="label" for="check-password">비밀번호 확인</label>
                                <input type="password" id="check-password" class="input"
                                       placeholder="비밀번호를 입력해주세요." value="">         
                            </section>
                            
                            <footer class="modal-card-foot level-right">
                                    <div class="level-item">
                                        <a class="button is-link" id="checkPasswordBtn">확인</a>
                                    </div>
                                    <div class="level-item">
                                        <a class="button is-sparta is-outlined"
                                           onclick='$("#modal-checkPassword").removeClass("is-active")'>취소</a>
                                    </div>
                            </footer>
                        </div>`
    $('#modal-checkPassword').append(temp_html);
    $('#modal-checkPassword').addClass('is-active');
    $('#checkPasswordBtn').on("click", function (){
        $("#modal-checkPassword").removeClass("is-active")
        let inputPassword = $('#check-password').val();
        let data = {
            "id": id,
            "password": inputPassword
        } //password String
        $.ajax({
            type: "PATCH",
            url: `/api/posts/${id}/checkPassword`,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response){
                $("#modal-checkPassword").removeClass("is-active")
                showEditModal(id, response);
            },
            error: function(){
                alert("비밀번호가 일치하지 않습니다.");
            }
        })
    })
}

function checkDeletePassword(id){
    $('#modal-checkPassword').empty();
    let temp_html =  `<div class="modal-background" onclick='$("#modal-checkPassword").removeClass("is-active")'></div>
                        <div class="modal-card">
                            <header class="modal-card-head">
                                <p class="modal-card-title">비밀번호 확인</p>
                                <button class="delete" aria-label="close" onclick='$("#modal-checkPassword").removeClass("is-active")'></button>
                            </header>
                            
                            <section class="modal-card-body">           
                                <label class="label" for="check-password">비밀번호 확인</label>
                                <input type="password" id="check-password" class="input"
                                       placeholder="비밀번호를 입력해주세요." value="">         
                            </section>
                            
                            <footer class="modal-card-foot level-right">
                                    <div class="level-item">
                                        <a class="button is-link" id="checkPasswordBtn">확인</a>
                                    </div>
                                    <div class="level-item">
                                        <a class="button is-sparta is-outlined"
                                           onclick='$("#modal-checkPassword").removeClass("is-active")'>취소</a>
                                    </div>
                            </footer>
                        </div>`
    $('#modal-checkPassword').addClass('is-active');
    $('#modal-checkPassword').append(temp_html);

    $('#checkPasswordBtn').on("click", function (){
        $("#modal-checkPassword").removeClass("is-active")
        let inputPassword = $('#check-password').val();
        let data = {
            "id": id,
            "password": inputPassword
        }
        $.ajax({
            type: "PATCH",
            url: `/api/posts/${id}/checkPassword`,
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (response){
                $("#modal-checkPassword").removeClass("is-active")
                deletePost(id);
            },
            error: function(){
                alert("비밀번호가 일치하지 않습니다.");
            }
        })
    })
}

function showEditModal(id, postEditDto){
    $("#modal-edit").addClass("is-active");
    $("#modal-edit").empty();
    let tempHtml = `<div class="modal-background" onclick='$("#modal-edit").removeClass("is-active")'></div>
                        <div class="modal-content">
                            <div class="box">
                                <article class="media">
                                    <div class="media-content">
                                        <div class="field">
                                            <label class="label" for="edit-title">제목</label>
                    
                                            <p class="control">
                                                <input id="edit-title" class="input"
                                                       placeholder="제목을 입력해주세요." value="${postEditDto.title}" >
                                            </p>
                                        </div>
                                        <div class="field">
                                            <label class="label" for="edit-contents">내용</label>
                    
                                            <p class="control">
                    
                                                <textarea rows="10" id="edit-contents" class="textarea"
                                                          placeholder="내용을 입력해주세요.">${postEditDto.contents}</textarea>
                                            </p>
                                        </div>
                                        <nav class="level is-mobile">
                                            <div class="level-left">
                    
                                            </div>
                                            <div class="level-right">
                                                <div class="level-item">
                                                    <a class="button is-link" onclick='updatePost(${id})'>수정</a>
                                                </div>
                                                <div class="level-item">
                                                    <a class="button is-sparta is-outlined"
                                                       onclick='$("#modal-edit").removeClass("is-active")'>취소</a>
                                                </div>
                                            </div>
                                        </nav>
                                    </div>
                                </article>
                            </div>
                        </div>
                        <button class="modal-close is-large" aria-label="close"
                                onclick='$("#modal-edit").removeClass("is-active")'></button>`

    $("#modal-edit").append(tempHtml);
}

function updatePost(id){
    let editTitle = $("#edit-title").val();
    let editContents = $("#edit-contents").val();

    let data = {
        "title": editTitle,
        "contents": editContents
    }

    $.ajax({
        type: "PUT",
        url: `/api/posts/${id}`,
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function (response){
            alert("수정되었습니다.");
            window.location.reload();
        }
    });
}

function showDeleteBtn(index){
    $(`#deleteBtn${index}`).removeClass('delete-post');
}
function hideDeleteBtn(index){
    $(`#deleteBtn${index}`).addClass('delete-post');
}

// $(document).ready(function (){
//
//     $('#search-input').on('keypress', function (e) {
//         if (e.key == 'Enter') {
//             execSearch();
//         }
//     });
//
//     getPost();
// })
//
// function execSearch() {
//     /**
//      * 검색어 input id: query
//      * 검색결과 목록: #search-result-box
//      * 검색결과 HTML 만드는 함수: addHTML
//      */
//     $('#post-list').empty();
//     // 1. 검색창의 입력값을 가져온다.
//     let query = $('#search-input').val();
//     // 2. 검색창 입력값을 검사하고, 입력하지 않았을 경우 focus.
//     if (query == '') {
//         alert('검색어를 입력해주세요');
//         $('#search-input').focus();
//         return;
//     }
//     $.ajax({
//         type: "GET",
//         url: `/api/search?query=${query}`,
//         success: function (response) {
//             let postList = response;
//             for (let i = 0; i < postList.length; i++) {
//                 let postDto = postList[i];
//                 let tempHtml = addHtml(postDto, postList.length-i);
//                 $('#post-list').append(tempHtml);
//             }
//         }
//     })
// }

// function getPost(){
//     $.ajax({
//         type: "GET",
//         url: `/api/posts?page=0`,
//         success: function (response){
//             let postList = response["content"];
//             let postCount = response["numberOfElements"];
//             let postTotalCount = response.totalElements;
//
//             console.log(postList);
//             console.log(postCount);
//             console.log(postTotalCount);
//
//             // console.log(postCount);
//             $('#post-list').empty();
//             for(let i = 0; i < postCount; i++){
//                 let tempHtml = addHtml(postList[i], postTotalCount-i)
//                 $('#post-list').append(tempHtml);
//             }
//         }
//     })
// }
//
// function addHtml(postDto, i){
//     let title = postDto.title;
//     let username = postDto.username;
//     let modifiedAt = postDto.modifiedAt;
//     let id = postDto.id;
//     let temp_html = `<tr id="col${i}" onclick="showPost(${id})" onmouseover="$('#delete-post').toggleClass('delete-post')">
//                         <th scope="row">${i}</th>
//                         <td>${title}</td>
//                         <td>${username}</td>
//                         <td>${modifiedAt.substring(0, 10)}</td>
//                         <td id="delete-post" onclick="deletePost(${id})"><i class="fa-solid fa-circle-xmark"></i></td>
//                     </tr>`
//     return temp_html
// }
