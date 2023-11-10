const modalText = document.querySelectorAll('.question_text_modal');
const firstModal = document.getElementById('first_modal');
const xMark = document.querySelector(".fa-solid.fa-xmark");
const modalContent = document.getElementById('modalContent');
const modalTextContent = document.getElementById('modalText');

function h_write() {
   const linkText = this.textContent;
   modalContent.textContent = linkText;

   const pContent = this.nextElementSibling.textContent;
   modalTextContent.textContent = pContent;

   firstModal.showModal();
}

function outer(){
   firstModal.close();
}

[...modalText].forEach(curr => {
   curr.addEventListener('click', h_write);
});

xMark.addEventListener('click',outer);

// HTML 요소 선택
const subTitleBtn1 = document.querySelector(".subTitle_btn1");
const subTitleBtn2 = document.querySelector(".subTitle_btn2");
const subTitleBtn3 = document.querySelector(".subTitle_btn3");
const subTitleBtn4 = document.querySelector(".subTitle_btn4");

const forHidden1 = document.querySelector(".forHidden_1");
const forHidden2 = document.querySelector(".forHidden_2");
const forHidden3 = document.querySelector(".forHidden_3");
const forHidden4 = document.querySelector(".forHidden_4");

// 기본값: forHidden_1 보이도록 설정
forHidden1.style.display = "block";

// "subTitle_btn1" 클릭 시 토글
subTitleBtn1.addEventListener("click", () => {
  toggleVisibility(forHidden1);
  hideOtherSections([forHidden2, forHidden3, forHidden4]);
});

// "subTitle_btn2" 클릭 시 토글
subTitleBtn2.addEventListener("click", () => {
  toggleVisibility(forHidden2);
  hideOtherSections([forHidden1, forHidden3, forHidden4]);
});

// "subTitle_btn3" 클릭 시 토글
subTitleBtn3.addEventListener("click", () => {
  toggleVisibility(forHidden3);
  hideOtherSections([forHidden1, forHidden2, forHidden4]);
});

// "subTitle_btn4" 클릭 시 토글
subTitleBtn4.addEventListener("click", () => {
  toggleVisibility(forHidden4);
  hideOtherSections([forHidden1, forHidden2, forHidden3]);
});

// 섹션 표시/숨김을 토글하는 함수
function toggleVisibility(element) {
  const currentDisplay = getComputedStyle(element).display;
  element.style.display = currentDisplay === "block" ? "none" : "block";
}

// 다른 섹션을 숨기는 함수
function hideOtherSections(sections) {
  sections.forEach((section) => {
    section.style.display = "none";
  });
}




