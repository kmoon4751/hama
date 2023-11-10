const $btn = document.querySelectorAll('.colorbutton');
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