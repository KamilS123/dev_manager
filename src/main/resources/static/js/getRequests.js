function getAllStudents() {
    $.ajax({
        url: "/admin/getAllStudents",
        type:"GET",
        success: function(result) {
            $('#displayingPart .tables').empty();
            $.each(result, function (i, list) {
                var button = "<td><input type=\"button\" value=\"delete\" name=i></td>\n";
                document.getElementById("studentTable").style.visibility = "visible";
                var student = list.id +" "+ list.username + " " + list.surname + " " + list.email + " " + list.year_of_studies + " " + list.name_of_studies + " " + list.index_number + "</br>"
                $('#displayingPart #studentTable').append('<tr><td>' + list.id+ '</td><td>' + list.username + '</td><td>' + list.surname + '</td><td>' + list.email + '</td><td>' + list.year_of_studies + '</td><td>' + list.name_of_studies + '</td><td>' + list.index_number + button + '</td></tr>');
            });
            console.log("success ",result);
        },error:function(jqXHR,textStatus,errorThrown) {
        }
    });
}
function getAllLectures() {
    $.ajax({
        url: "/student/allLectures",
        type:"GET",
        success: function (result) {
            $('#displayingPart .tables').empty();
            $.each(result, function(i,list) {
                var button = "<td><input type=\"button\" value=\"delete\" name=\"button\"></td>\n";
                document.getElementById("lectureTable").style.visibility = "visible";
                $('#displayingPart #lectureTable').append('<tr><td>' + list.id + '</td><td>' + list.tittle + '</td><td>'+ list.description + '</td><td>' + list.teacher_info + '</td><td>' + list.lecture_date + button + '</td></tr>');
            });
            console.log("success",result);
        },error:function (jqXHR, textStatus, errorThrown) {

        }
    });
}
$(function loggedUser() {
    $.ajax({
        url:"/common/loggedUser",
        type:"GET",
        success: function(data) {
            console.log(data);
            document.getElementById("logUser").innerHTML = data;
        },error:function(jqXHR,textStatus,errorThrown) {
        }
    });
});