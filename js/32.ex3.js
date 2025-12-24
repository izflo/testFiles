const box = document.querySelector('div'); 
const [blueBtn, redBtn, biggerBtn, smallerBtn, blueClsBtn, redClsBtn] = document.querySelectorAll('button');

blueBtn.addEventListener('click', () => {
    if(box.hasAttribute('class')) box.removeAttribute('class');
    box.style.backgroundColor = `blue`;
});

redBtn.addEventListener('click', () => {
    if(box.hasAttribute('class')) box.removeAttribute('class');
    box.style.backgroundColor = `red`;

});

biggerBtn.addEventListener('click', () => {    
    let width = getComputedStyle(box).width.match(/\d+[^a-z]/);
    let height =  getComputedStyle(box).height.match(/\d+[^a-z]/);
    box.style.width = `${width*2}px`;
    box.style.height = `${height*2}px`;
});

smallerBtn.addEventListener('click', () => {
    let width = getComputedStyle(box).width.match(/\d+[^a-z]/);
    let height =  getComputedStyle(box).height.match(/\d+[^a-z]/);
    box.style.width = `${width/2}px`;
    box.style.height = `${height/2}px`;
});

blueClsBtn.addEventListener('click', () => {
    if(box.hasAttribute('style')) box.removeAttribute('style');
    box.setAttribute('class', 'blue');
});

redClsBtn.addEventListener('click', () => {
    if(box.hasAttribute('style')) box.removeAttribute('style');
    box.setAttribute('class', 'red');

});

