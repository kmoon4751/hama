
const areaButton = document.getElementById("areaButton");       //지역 카테고리 버튼
const areaModal = document.getElementById("areaModal");         //지역 카테고리 모달창


const busan = document.getElementById("busan");         //지역 > 부산
const ulsan = document.getElementById("ulsan");         //지역 > 울산
const gyeongnam = document.getElementById("gyeongnam"); //지역 > 경남
const seoul = document.getElementById("seoul");         //지역 > 서울
const daegu = document.getElementById("daegu");         //지역 > 대구
const gwangju = document.getElementById("gwangju"); //지역 > 광주
const jeju = document.getElementById("jeju"); //지역 > 제주


const area_subcatContainer = document.getElementById("area_subcat");        //지역 하위 카테고리 모달

areaButton.addEventListener("click", () => {
    serviceModal.style.display = "none";
    areaModal.style.display = "block";              //지역 모달 열 때 서비스 모달 닫기
    area_subcatContainer.style.display = "none"     //지역 모달 열 때 지역 세부 모달 닫기
});


// 클릭한 지역 카테고리를 저장할 변수
let selectedAreaCategory = "";

busan.addEventListener("click", () => {
    // 부산 카테고리를 클릭했을 때 수행할 동작을 추가
    selectedAreaCategory = "부산광역시";
    areaModal.style.display = "none";
    showarea_subcat(["전체", "영도구", "부산진구", "동래구", "해운대구", "사하구", "금정구", "강서구", "연제구", "수영구", "사상구"]);
});

ulsan.addEventListener("click", () => {
    // 울산 카테고리를 클릭했을 때 수행할 동작을 추가
    selectedAreaCategory = "울산광역시";
    areaModal.style.display = "none";
    showarea_subcat(["전체", "중구", "남구", "동구", "북구", "울주군"]);
});

gyeongnam.addEventListener("click", () => {
    // 경남 카테고리를 클릭했을 때 수행할 동작을 추가
    selectedAreaCategory = "경상남도";
    areaModal.style.display = "none";
    showarea_subcat(["창원시", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시"]);
});

seoul.addEventListener("click", () => {
    // 서울 카테고리를 클릭했을 때 수행할 동작을 추가
    selectedAreaCategory = "서울특별시";
    areaModal.style.display = "none";
    showarea_subcat(["전체", "종로구", "용산구", "성동구", "광진구", "동대문구", "성북구", "도봉구", "노원구", "은평구"]);
});

daegu.addEventListener("click", () => {
    // 대구 카테고리를 클릭했을 때 수행할 동작을 추가
    selectedAreaCategory = "대구광역시";
    areaModal.style.display = "none";
    showarea_subcat(["전체", "중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군", "군위군"]);
});

gwangju.addEventListener("click", () => {
    // 광주 카테고리를 클릭했을 때 수행할 동작을 추가
    selectedAreaCategory = "광주광역시";
    areaModal.style.display = "none";
    showarea_subcat(["전체", "동구", "서구", "남구", "북구", "광산구"]);
});

jeju.addEventListener("click", () => {
    // 제주 카테고리를 클릭했을 때 수행할 동작을 추가
    selectedAreaCategory = "제주도";
    areaModal.style.display = "none";
    showarea_subcat(["전체", "제주시", "서귀포시"]);
});
    //지역 카테고리 테그요소 안에 내용
function showarea_subcat(area_subcat) {
    area_subcatContainer.innerHTML = "<h3>세부 카테고리</h3>";
    const ul = document.createElement("ul");
    area_subcat.forEach((area_subcategory) => {
        const li = document.createElement("li");
        li.textContent = `${area_subcategory}`;
        // li 항목 하나하나를 클릭하면 모달 닫김 & 밖 화면 정상화
        li.addEventListener("click", () => {
            // 클릭한 지역 세부 카테고리를 selectedAreaCategory에 저장
            const firstTwoLetters = selectedAreaCategory.slice(0, 2); // 대분류의 앞의 두 글자 추출
            selectedAreaCategory = `${firstTwoLetters}/${area_subcategory}`;
            localStorage.setItem('location',selectedAreaCategory);
            areaButton.innerHTML = `${selectedAreaCategory}`;
            areaModal.style.display = "none";
            area_subcatContainer.style.display = "none";
            layerWrapEle.classList.remove('active');
            areaButton.classList.remove('active');
            areaButton.style.border = "2px solid rgb(0, 146, 250)";
        });
        ul.appendChild(li);
    });
    area_subcatContainer.appendChild(ul);
    // 지역 세부 모달이 열리면 지역 모달 닫기
    area_subcatContainer.style.display = "block";
}

//모달창이 열릴 때 모달창을 제외한 배경색을 회색으로 주기
let btnEle1 = document.querySelector('.service_category');
let btnEle2 = document.querySelector('.area_category');
//배경색을 주기위한 전체화면 박스
let layerWrapEle = document.querySelector('.layer_wrap__category');

// 지역 모달창이 열릴 때 박스에 효과주기
btnEle2.addEventListener('click', e => {
    layerWrapEle.classList.add('active');
    areaButton.classList.add('active');
});

// 외부 박스를 클릭했을 때 박스 효과 리셋
layerWrapEle.addEventListener('click', e => {
    if (e.target === layerWrapEle) {
        layerWrapEle.classList.remove('active');
        serviceButton.classList.remove('active');
        areaButton.classList.remove('active');
    }
});

// 모달 외부 클릭 시 모달 닫기
window.addEventListener("click", (e) => {
    layerWrapEle.addEventListener('click', e => {
        serviceModal.style.display = "none";
        areaModal.style.display = "none";
        area_subcatContainer.style.display = "none";
    })
});