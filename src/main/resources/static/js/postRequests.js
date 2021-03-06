function addNewStudent() {
    var model;
    model = {
        username: $("#username").val(),
        surname: $("#surname").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        yearOfStudies: $("#yearOfStudies").val(),
        nameOfStudies: $("#nameOfStudies").val(),
        indexNumber: $("#indexNumber").val()
    };
    console.log("post data " + model);
    $.ajax({
        type:"POST",
        url:"/admin/newStudent",
        headers: {
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data: JSON.stringify(model),
        success: function(data){
            console.log("!!!!POST RESPONSE " + data);
        },
        error:function(jqXHR,textStatus,errorThrown){
        }
    });
}
function addNewLecture() {
    var model;
    model = {
        tittle: $("#tittle").val(),
        description: $("#description").val(),
        teacherInfo: $("#teacherInfo").val(),
        lectureDate: $("#lectureDate").val()
    };
    console.log("post data " + model);
    $.ajax({
        type:"POST",
        url:"/admin/newLecture",
        headers: {
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data: JSON.stringify(model),
        success: function(data){
            console.log("!!!!POST RESPONSE " + data);
        },
        error:function(jqXHR,textStatus,errorThrown){
        }
    });
}
function deleteStudent() {
    var model;
    model = {
        id: $("#deleteStudentById").val()
    };
    console.log(model);
    $.ajax({
        type:"DELETE",
        url:"/admin/student/" + model.id,
        headers: {
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data: JSON.stringify(model),
        success: function (data) {
            document.getElementById("successConfirmation").innerHTML = "Success";
            console.log("usunieto po id " + data)
        },
        error:function(jqXHR,textStatus,errorThrown){
            console.log("jakies bledy")
        }
    });
}
function deleteLecture() {
    var model;
    model = {
        id: $("#deleteLectureById").val()
    };
    console.log(model);
    $.ajax({
        type: "DELETE",
        url: "/admin/lecture/" + model.id,
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        data: JSON.stringify(model),
        success: function (data) {
            document.getElementById("successConfirmation").innerHTML = "Success";
            console.log("usunieto po id " + data)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("jakies bledy")
        }
    });
}
    function addToAttendancee() {
        var model;
        model = {
            id: $("#addToList").val()
        };
        console.log(model);
        $.ajax({
            type:"POST",
            url:"/user/toList/" + model.id,
            headers: {
                "Content-Type":"application/json",
                "Accept":"application/json"
            },
            data: JSON.stringify(model),
            success: function (data) {
                console.log("added to attendance list " + data)
            },
            error:function(jqXHR,textStatus,errorThrown){
                console.log("jakies bledyfailure adding to attendance list")
            }
        });
    }
