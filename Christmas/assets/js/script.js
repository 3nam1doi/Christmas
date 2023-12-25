document.addEventListener("DOMContentLoaded", function () {
    const musicPlayer = document.getElementById("musicPlayer");
    const playButton = document.getElementById("playButton");
    const pauseButton = document.getElementById("pauseButton");
    const stopButton = document.getElementById("stopButton");

    playButton.addEventListener("click", function () {
        musicPlayer.play();
    });

    pauseButton.addEventListener("click", function () {
        musicPlayer.pause();
    });

    stopButton.addEventListener("click", function () {
        musicPlayer.pause();
        musicPlayer.currentTime = 0;
    });

     // Lấy ra hộp quà và tin nhắn
     const giftBox = document.querySelector('.gift-box');
     const letter = document.getElementById('letter');
 
     // Ẩn tin nhắn ban đầu
     letter.style.display = 'none';
 
     // Thêm sự kiện khi click vào hộp quà
     giftBox.addEventListener('click', function () {
         // Nếu tin nhắn đang hiển thị, ẩn nó; nếu không, hiển thị tin nhắn
         letter.style.display = (letter.style.display === 'none') ? 'block' : 'none';
     });
});

function makeSnow() {
    const snow = document.createElement('div');
    const size = Math.random() * 4.5 + 3.5;
    snow.className = 'snow';
    snow.style.width = size + 'px';
    snow.style.height = size + 'px';
    snow.style.left = Math.random() * window.innerWidth + 'px';
    snow.style.opacity = size / 8;
    if (size < 7) {
        snow.style.zIndex = -5;
    }
    snow.style.animationDuration =  Math.random() * 3 + 2 + 's';
    document.body.appendChild(snow);
    setTimeout(() => snow.remove(), 5000);
}

setInterval(makeSnow, 40);


