function addNewStudent() {
    var model;
    model = {
        username: $("#username").val(),
        surname: $("#surname").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        year_of_studies: $("#year_of_studies").val(),
        name_of_studies: $("#name_of_studies").val(),
        index_number: $("#index_number").val()
    };
    console.log("post data " + model);
    $.ajax({
        type:"POST",
        url:"/admin/addStudent",
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
        teacher_info: $("#teacher_info").val(),
        lecture_date: $("#lecture_date").val()
    };
    console.log("post data " + model);
    $.ajax({
        type:"POST",
        url:"/admin/addLecture",
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
        url:"/admin/deleteStudent/" + model.id,
        headers: {
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data: JSON.stringify(model),
        success: function (data) {
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
        type:"DELETE",
        url:"/admin/deleteLecture/" + model.id,
        headers: {
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data: JSON.stringify(model),
        success: function (data) {
            console.log("usunieto po id " + data)
        },
        error:function(jqXHR,textStatus,errorThrown){
            console.log("jakies bledy")
        }
    });
}