const category = localStorage.getItem('category');
const $textarea = document.querySelector('.chat-text-box');
const $btn = document.querySelector('.button');
const $area_sub = document.getElementById('area_subcat');
const url = 'http://localhost:9080/give/detail'
        document.querySelector('.select_category').innerHTML =
        '<i class="fa-solid fa-star" style="color: #ffd500;"></i> ' + category + ' <i class="fa-solid fa-star" style="color: #ffd500;"></i>';
        $area_sub.addEventListener('click', e=>{
            $btn.disabled = false;
            })
        $btn.addEventListener('click',e =>{
            const text_value = $textarea.value;
            localStorage.setItem('text',text_value);
            const local = localStorage.getItem('location')
            const dataToPost = JSON.stringify({
                location : localStorage.getItem('location').replace(/[\s/]/g,''),
                category : localStorage.getItem('category').replace(/[\s/]/g,''),
                date : localStorage.getItem('date').replace(/[\s/\\n]/g,''),
                text : localStorage.getItem('text')
            });
            console.log(dataToPost);
            fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type' : 'application/json'
                },
                body: dataToPost,
            })
          .then(response => {
              if (!response.ok) {
                  throw new Error('서버에서 오류 응답을 받았습니다.');
              }
              return response.json();
          })
          .then(dataToPost => {
              console.log('서버응답:', dataToPost);
              location.href = "http://localhost:9080/give/detail/result";
          })
          .catch(error => {
              console.log('오류발생', error);
          });
            })
