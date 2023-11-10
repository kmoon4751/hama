document.addEventListener("DOMContentLoaded", function () {
    // 게시글 내용을 담은 배열 생성
    const posts = [
        {
            title: '게시글 제목 1',
            text: '내용 1 Lorem ipsum dolor sit amet ...',
            date: '2023-09-01',
//            views: 0,
//            comments: 0
        },
    ];
    
    // 게시글을 동적으로 생성하고 내용을 변경하는 함수
    function createPostsHTML(posts) {
        let html = '';
    
        for (let i = 0; i < posts.length; i++) {
            const post = posts[i];
            const postHTML = `
                <a href="test.html">
                    <div class="content_commu content${i + 1}">
                        <div class="content_title">${post.title}</div>
                        <div class="content_left">
                            <div class="content_text">${post.text}</div>
                            <div class="content_cnt">
                                <div class="cnt_m">${post.date}</div>
//                                <div class="cnt_wrap">
//                                    <div class="cnt_eye"><i class="fa-solid fa-eye"></i> ${post.views}</div>
//                                    <div class="cnt_re"><i class="fa-regular fa-comment-dots"></i> ${post.comments}</div>
//                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            `;
    
            html += postHTML;
        }
    
        return html;
    }
    
    // 게시글을 화면에 추가
    const contentContainer = document.querySelector('.commu_content');
    contentContainer.innerHTML = createPostsHTML(posts);
    });