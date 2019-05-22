function timer() {
    var time = new Date();
    var hour = time.getHours();
    if (hour<=9)hour = 0 + "" + hour;
    var minutes = time.getMinutes();
    if (minutes<=9)minutes = 0 + "" + minutes;
    var seconds = time.getSeconds();
    if (seconds<=9) seconds = 0 + "" + seconds;
    document.getElementById("clock").innerHTML = hour + ":" + minutes + ":" + seconds;
    setTimeout('timer();',1000);
}