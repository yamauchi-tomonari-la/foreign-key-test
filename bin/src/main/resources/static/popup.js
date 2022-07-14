//appends an "active" class to .popup and .popup-content when the "Open" button is clicked
$(".open").on("click", function(){
  $(".popup-overlay, .popup-content").addClass("active");
});

//removes the "active" class to .popup and .popup-content when the "Close" button is clicked 
$(".close, .popup-overlay").on("click", function(){
  $(".popup-overlay, .popup-content").removeClass("active");
});

// <!-- 【ここから】新しいウィンドでブラウザを起動し、ポップアップ表示 -->
// <button onclick="openWin()">新しいウィンドウで<br>ポップアップを表示</button>
// <script>
var myWindow;
function openWin() {
    myWindow = window.open("", "myWindow", "width=200,height=100");
    myWindow.document.write("<p>ポップアップ表示だよ</p>");
}
// </script>
// <!-- 【ここまで】新しいウィンドでブラウザを起動し、ポップアップ表示 -->
// <br>
// <br>
// <hr>
// <!-- 【ここから】CSSを活用したポップアップ表示 -->
// <style>
// .popup {
//     position: relative;
//     display: inline-block;
//     cursor: pointer;
//     -webkit-user-select: none;
//     -moz-user-select: none;
//     -ms-user-select: none;
//     user-select: none;
// }
// .popup .popuptext {
//     visibility: hidden;
//     width: 160px;
//     background-color: #ff0000;
//     color: #fff;
//     text-align: center;
//     border-radius: 6px;
//     padding: 8px 0;
//     position: absolute;
//     z-index: 1;
//     bottom: 125%;
//     left: 50%;
//     margin-left: -80px;
// }
// .popup .popuptext::after {
//     content: "";
//     position: absolute;
//     top: 100%;
//     left: 50%;
//     margin-left: -5px;
//     border-width: 5px;
//     border-style: solid;
//     border-color: #555 transparent transparent transparent;
// }
// .popup .show {
//     visibility: visible;
//     -webkit-animation: fadeIn 1s;
//     animation: fadeIn 1s;
// }
// @-webkit-keyframes fadeIn {
//     from {opacity: 0;} 
//     to {opacity: 1;}
// }
// @keyframes fadeIn {
//     from {opacity: 0;}
//     to {opacity:1 ;}
// }
// </style>
// <body style="text-align:center">
// <h2>CSSを使ったポップアップ</h2>
// <div class="popup" onclick="myFunction()">ここをクリックしてみて下さい！
//   <span class="popuptext" id="myPopup">PopUp!!!</span>
// </div>
// <script>
function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
}
// </script>
// <!-- 【ここまで】CSSを活用したポップアップ表示 -->
