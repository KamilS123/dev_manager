function getAllStudents() {
    $.ajax({
        url: "/admin/students",
        type:"GET",
        success: function(result) {
            $('#displayingPart tbody').empty();
            $.each(result, function (i, list) {
                document.getElementById("lectureTable").style.visibility = "hidden";
                document.getElementById("studentTable").style.visibility = "visible";
                $('#displayingPart #studentTable').append('<tr><td>' + list.id+ '</td><td>' + list.username + '</td><td>' + list.surname + '</td><td>' + list.email + '</td><td>' + list.year_of_studies + '</td><td>' + list.name_of_studies + '</td><td>' + list.index_number +'</td></tr>');
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
                $('#displayingPart #lectureTable').append('<tr><td>' + list.id + '</td><td>' + list.tittle + '</td><td>'+ list.description + '</td><td>' + list.teacher_info + '</td><td>' + list.lecture_date + '</td></tr>');
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