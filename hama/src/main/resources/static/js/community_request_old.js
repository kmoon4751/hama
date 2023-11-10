const postList = document.getElementById("postList");

const getBoards = async () => {const option = {
    method : 'GET',
    headers : {Accept : 'application/json'},
    };
    try {
        const url = '/requestBoard/all';
        const response = await fetch(url,option);
        console.log(response);
        const data = await response.json();
        console.log(data);
        displayPosts(data);
        return data;
    } catch(err){
        console.error(err.message);
     }
};

//const postBoards = async pid => {const option = {
//    method : 'POST',
//    headers : {Accept : 'application/json'},
//    };
//    try {
//        const url = `/requestBoard/${pid}`;
//        const response = await fetch(url,option);
//        console.log(response);
//        const data = await response.json();
//        console.log(data);
//        return data;
//    } catch(err){
//        console.error(err.message);
//     }
//};
async function displayPosts(posts) {
    posts.forEach((post) => {
      const postItem = document.createElement("li");
      postItem.classList.add("post");
      postItem.innerHTML =
      `
      <div class="main_top">
                      <div class="main_info">
                          <div class="pic">
                              <div class="profile">
                              <img src="/view/${post.member_id}">
                              </div>
                          </div>
                          <div class="info">
                              <div class="hope">
                                  <div class="hope_list">
                                      <div class="hope_title"><h3>${post.nickname}</h3></div>
                                      <div class="hope_text">
                                        ${post.hope_text}
                                      </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <div class="info_top_p">
                          <div class="info_top">
                              <div class="job">
                               분야 : ${post.category}
                              </div>
                              <div class="local">
                               지역 : ${post.area}
                              </div>
                              <div class="date">
                               희망날짜 : ${post.hope_date}
                              </div>
                          </div>
                      </div>
                      <button class="button colorbutton">견적서 작성</button>
                      <input type="hidden" id="boardId" value="${post.board_id}">
                  </div>
    </div>
    `;
      postList.appendChild(postItem);
      const $btn = postItem.querySelectorAll('.colorbutton');
      [...$btn].forEach(ele=>{
      ele.addEventListener('click',e=>{
      console.log(post.member_id);
      if(post.member_id == 0){
        alert("자신의 요청에 견적을 작성할 수 없습니다");
      }else{
          location.href = `/createRQ/${post.board_id}`;
      }
          }
        )
      })
    });
  }
getBoards();