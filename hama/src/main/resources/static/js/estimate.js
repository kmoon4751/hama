        // won = '원'글자 , $checks = radio버튼
    const won = document.querySelector('.numberInput_append');
    const $checks = document.querySelectorAll('.checks')
    const $btn = document.querySelector('.button');
    const formatPrice = (target) => {
        target.value = Number(
            // 인풋값을 0~9만 입력하게하고 / 세자리마다 , 를 추가(toLocalString)
            target.value.replace(/[^0-9]/g, '')).toLocaleString();
            // 값이없으면 '원'을 숨기고 버튼 비활성화
            if(target.value === "0"){
                target.value = '';
                won.style.display = 'none';
                $btn.disabled = true;
            }
            // 값이 있으면 '원'을 표시하고 버튼 활성화
            if(target.value.length > 0 ){
                won.style.display = 'block';
                $btn.disabled = false;
            }
            // 체크가 하나도안되있으면 알림창을띄우고 값을 없애고 '원'을 숨기고 버튼 비활성화
            if($checks[0].checked == false && $checks[1].checked ==false){
                alert('비용조건을 체크해주세요.')
                target.value = '';
                won.style.display = 'none';
                $btn.disabled = true;
            }
    }
    for(let i = 0; i < $checks.length; i++){
        $checks[i].addEventListener('click', e=>{
            const type = $checks[i].value;
            localStorage.setItem('costType',type);
        })
    }
    const $file = document.getElementById("file");
    $file.addEventListener('change',e=>{
        const fileName = $file.value;
        document.querySelector(".upload-name").value = fileName;
    });