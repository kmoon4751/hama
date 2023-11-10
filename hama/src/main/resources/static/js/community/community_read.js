document.addEventListener("DOMContentLoaded", function () {
    // 고정된 날짜 및 시간 설정
    const currentDate = new Date("2023-10-16 14:30");

    // 날짜를 원하는 형식으로 포맷팅 (예: "년-월-일 시간:분")
    function formatDateTime(date) {
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        const hours = date.getHours().toString().padStart(2, '0');
        const minutes = date.getMinutes().toString().padStart(2, '0');
        return `${year}-${month}-${day} ${hours}:${minutes}`;
    }

    
    // 게시글, 댓글 및 댓글 작성란을 관리하는 데이터 배열 생성
    const content_profile = [
        {
            writerPhoto: "../img/루루팡.png",
            writerNick: "월급루루팡루루피",
            uploadDate: formatDateTime(currentDate), // 설정된 날짜
            viewCount: 100, // 조회수
        }
    ];
    
    const content_text = [
        {
            title: "Lorem, ipsum dolor sit amet consectetur adipisicing elit. Voluptatem, odio?",
            content: `Lorem, ipsum dolor sit amet consectetur adipisicing elit. Et quasi cupiditate in ducimus ipsum vel velit magni delectus minima hic sed quia nemo vitae distinctio tempore, nobis veniam reprehenderit. Sapiente!
Consequuntur, repellat dolorum modi non minus exercitationem reiciendis dicta pariatur ut in ab sapiente nesciunt et eius. At, quia quae asperiores ducimus impedit animi sunt dolore nulla error accusantium aspernatur?
Error vero doloremque totam laborum, laudantium commodi recusandae obcaecati quis fugiat cumque ex maxime magni debitis praesentium incidunt ipsa numquam? Numquam, beatae qui quod ratione tenetur nostrum rem voluptate incidunt.
Voluptas excepturi ullam amet commodi eligendi, aliquam quam tempora quo ducimus eos distinctio molestias est non deserunt quos dolorum consectetur sunt. In aliquam facilis voluptates? Dignissimos fugiat quo iste fugit?
Recusandae eaque nobis laborum blanditiis eius commodi rerum iste atque aspernatur aut ipsum ipsa qui, provident illo laudantium consequatur. Iusto sit dolores deleniti ea officiis recusandae sequi aliquam omnis quasi.`, // 게시글 내용 HTML 또는 텍스트
        }
    ];
    const read_comment = [
        {},
    ];

    // 게시글 및 댓글 HTML 생성 함수
    function createPostsHTML(profile, text, comments) {
        let html = '';

        for (let i = 0; i < profile.length; i++) {
            const postHTML = `
                <div class="read_txt">
                    <div class="content_title">${text[i].title}</div>
                    <div class="content_profile">
                        <div class="profile_1 writerPhoto">
                            <img src="${profile[i].writerPhoto}" alt="작성자사진">
                        </div>
                        <div class="profile_2 writerInfo">
                            <div class="writerNick">${profile[i].writerNick}</div>
                            <div class="upLoad_Cnt">
                                <span class="uploadDate">${profile[i].uploadDate}</span>
                                <div class="viewCnt">
                                    <i class="fa-solid fa-eye" style="color: #cccccc;"></i>
                                    <span>${profile[i].viewCount}</span>
                                </div>
                            </div>
                        </div>
                        <div class="profile_3 menu">
                            <button type="button" class="wrapMenuBtn"><i class="fa-solid fa-ellipsis-vertical"></i></button>
                            <div class="wrapMenu">
                                <button id="modifyBtn">수정하기</button>
                                <span id="wrapMenuBar">|</span>
                                <button id="deleteBtn">삭제하기</button>
                            </div>
                        </div>
                    </div>
                    <div class="content_text">${text[i].content}</div>
                </div>
            `;
            html += postHTML;
        }

        for (let i = 0; i < comments.length; i++) {
            const commentHTML = `
                <div class="read_cmt">
                    <div class="read_comment">
                        <div class="comment_bar">
                            <div><i class="fa-solid fa-comments" style="color: #cccccc;"></i> 댓글
                                <span id="cmtCnt"></span>
                            </div>
                        </div>

                        <div id="comment_section">
                        </div>
                        <div class="comment_writeBox2">
                            <div class=""><input type="text" id="userNickInput"></div>
                            <div class="writeBox3">
                            <input type="text" id="textInput" placeholder="댓글을 입력해주세요" required></input> 
                                <i class="fa-solid fa-paper-plane" id="sendBtn"></i>
                            </div>
                        </div>
                    </div>
                </div>
            `;
            html += commentHTML;
        }

        return html;
    }

    // 게시글을 화면에 추가
    const contentContainer = document.querySelector('.read_txt');
    contentContainer.innerHTML = createPostsHTML(content_profile, content_text, read_comment);


    // 댓글 작성 구현 
    const commentsList = document.getElementById("comment_section");
    const textInput = document.getElementById("textInput");
    const sendBtn = document.getElementById("sendBtn");

    // 댓글을 로컬 스토리지에서 불러오기
    let savedComments = JSON.parse(localStorage.getItem("comments")) || [];

    function renderComments() {
        commentsList.innerHTML = '';
        savedComments.forEach(function (comment, index) {
            const commentContainer = document.createElement("div");
            commentContainer.className = "comment";

            commentContainer.innerHTML = `
                <div class="user_nick_time_del">
                    <span class="userNick">${comment.userNick}</span>
                    <span class="userCmtTime">${comment.userCmtTime}</span>
                    <button type="button" class="delete-comment-button" data-index="${index}">삭제</button>
                    
                </div>
                <div class="userCmt">${comment.userCmt}</div>
            `;

            // 삭제 버튼 클릭 이벤트 핸들러
            commentContainer.querySelector('.delete-comment-button').addEventListener("click", function () {
                // 댓글 삭제
                savedComments.splice(index, 1);

                // 로컬 스토리지 업데이트
                localStorage.setItem("comments", JSON.stringify(savedComments));

                // 화면에서 삭제된 댓글 제거
                commentContainer.remove();

                // 다시 댓글을 그립니다.
                renderComments();
            });

            commentsList.appendChild(commentContainer);
        });
    }

    renderComments();

    sendBtn.addEventListener("click", function () {
        const userNick = userNickInput.value; // 댓글 작성자 닉네임
        const userCmtTime = formatDateTime(new Date()); // 현재 시간을 가져오는 함수 사용 가능
        const userCmt = textInput.value;

        if (userCmt.trim() === "") {
            alert("댓글을 입력하세요");
        } else {
            savedComments.push({
                userNick: userNick,
                userCmtTime: userCmtTime,
                userCmt: userCmt,
            });
            localStorage.setItem("comments", JSON.stringify(savedComments));

            // 새로운 댓글을 추가한 후에 댓글 목록을 다시 그립니다.
            renderComments();
            userNickInput.value = "";
            textInput.value = "";
        }
    });


    const wrapMenuBtn = document.querySelector('.wrapMenuBtn');
    const wrapMenu = document.querySelector('.wrapMenu');

    wrapMenu.style.display = 'none'; // 초기에 메뉴 숨김
    
    wrapMenuBtn.addEventListener('click', () => {
        const isHidden = wrapMenu.style.display === 'none';
        wrapMenu.style.display = isHidden ? 'block' : 'none';
    });

});