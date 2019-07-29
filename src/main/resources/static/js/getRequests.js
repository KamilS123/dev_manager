function getAllStudents() {
    $.ajax({
        url: "/admin/students",
        type:"GET",
        success: function(result) {
            $('#displayingPart tbody').empty();
            $.each(result, function (i, list) {
                document.getElementById("lectureTable").style.visibility = "hidden";
                document.getElementById("studentTable").style.visibility = "visible";
                $('#displayingPart #studentTable').append('<tr><td>' + list.id+ '</td><td>' + list.username + '</td><td>' + list.surname + '</td><td>' + list.email + '</td><td>' + list.yearOfStudies + '</td><td>' + list.nameOfStudies + '</td><td>' + list.indexNumber +'</td></tr>');
            });
            console.log("success ",result);
        },error:function(jqXHR,textStatus,errorThrown) {
        }
    });
}
function getAllLectures() {
    $.ajax({
        url: "/user/lectures",
        type:"GET",
        success: function (result) {
            $('#displayingPart tbody').empty();
            $.each(result, function(i,list) {
                document.getElementById("studentTable").style.visibility = "hidden";
                document.getElementById("lectureTable").style.visibility = "visible";
                $('#displayingPart #lectureTable').append('<tr><td>' + list.id + '</td><td>' + list.tittle + '</td><td>'+ list.description + '</td><td>' + list.teacherInfo + '</td><td>' + list.lectureDate + '</td></tr>');
            });
            console.log("success",result);
        },error:function (jqXHR, textStatus, errorThrown) {
        }
    });
}
function personalStudentAttendencies() {
    $.ajax({
        url: "/user/attendance",
        type:"GET",
        success: function (result) {
            $('#displayingPart tbody').empty();
            $.each(result, function(i,list) {
                document.getElementById("lectureTable").style.visibility = "hidden";
                document.getElementById("personalList").style.visibility = "visible";
                $('#displayingPart #personalList').append('<tr><td>' + list.id + '</td><td>' + list.tittle + '</td><td>'+ list.description + '</td><td>' + list.teacherInfo + '</td><td>' + list.lectureDate + '</td></tr>');
            });
            console.log("success",result);
        },error:function (jqXHR, textStatus, errorThrown) {
        }
    });
}
$(function loggedUser() {
    $.ajax({
        url:"/loggedUser",
        type:"GET",
        success: function(data) {
            console.log(data);
            document.getElementById("logUser").innerHTML = data;
        },error:function(jqXHR,textStatus,errorThrown) {
        }
    });
});

