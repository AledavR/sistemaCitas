document.addEventListener("DOMContentLoaded", function() {
    var playButton = document.getElementById("playButton");
    var videoContainer = document.getElementById("videoContainer");
    var leftMain = document.querySelector(".left_main");

    playButton.addEventListener("click", function() {
        leftMain.style.display = "none"; // Ocultar el texto y el botón
        videoContainer.style.display = "flex"; // Mostrar el video
    });
});
