const $category = localStorage.getItem('category');
const $textarea = document.querySelector('.chat-text-box');
const $btn = document.querySelector('.button');
const $area_sub = document.getElementById('area_subcat');
const url = 'http://localhost:9080/give/detail'
        document.querySelector('.select_category').innerHTML =
        `<i class="fa-solid fa-star" style="color: #ffd500;"></i> ${$category}  <i class="fa-solid fa-star" style="color: #ffd500;"></i>`;
        $area_sub.addEventListener('click', e=>{
            $btn.disabled = false;
        })
$btn.addEventListener('click', e=>{
    const text_value = $textarea.value;
    localStorage.setItem('text',text_value);
    const $text = localStorage.getItem('text');
    const $date = localStorage.getItem('date');
    const $location = localStorage.getItem('location');

    const H_category = document.getElementById("category");
    const H_location = document.getElementById("location");
    const H_text = document.getElementById("text");
    const H_date = document.getElementById("date");

    H_category.value = $category;
    H_location.value = $location;
    H_text.value = $text;
    H_date.value = $date.replace(/[\s/\\n]/g,'');
})



